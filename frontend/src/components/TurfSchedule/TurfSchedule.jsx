import React, { useState, useCallback, buildMessage, useRef, useEffect } from 'react'
import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";
import "react-big-calendar/lib/css/react-big-calendar.css";
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import { useParams } from 'react-router-dom';
import TurfService from "../../services/TurfService";
import { HubConnectionBuilder, LogLevel } from '@microsoft/signalr'


moment.locale('en-GB');
const localizer = momentLocalizer(moment);

export default function TurfSchedule() {
  const clickRef = useRef(null)
  const [isOpenModal, setIsOpenModal] = useState(false);
  const [selectedEvent, setSelectedEvent] = useState({});
  const [schedules, setSchedules] = useState([])
  const [status, setStatus] = useState("");

  let { turfId } = useParams();
  useEffect(() => {
    TurfService.getScheduleByTurfId(turfId).then((response) => {
      const { data } = response;
      setSchedules([...data]);
    });
    joinRoom(turfId)
  }, [turfId])
  const tmp = [...schedules]
  const myEventsList = tmp.map((schedule) => (
    {
      ...schedule,
      start: new Date(schedule.start),
      end: new Date(schedule.end),
      title: schedule?.customer?.username
    }));

  const onSelectEvent = useCallback((calEvent) => {
    window.clearTimeout(clickRef?.current)
    setIsOpenModal(true)
    setSelectedEvent({ ...calEvent });
    setStatus(calEvent.status)
  }, [])

  const handleChangeStatus = (event) => {
    setStatus(event.target.value)
  };

  const handleClose = () => {
    setIsOpenModal(false);
  };

  const [connection, setConnection] = useState();

  const joinRoom = async (turfId) => {
    try {

      const connection = new HubConnectionBuilder().withUrl(`${process.env.REACT_APP_BASE_URL}/chatHub`)
        .configureLogging(LogLevel.Information)
        .build();

      connection.on("UpdateSchedule", (scheduleResponse) => {
        TurfService.getScheduleByTurfId(turfId).then((response) => {
          const { data } = response;
          console.log(data);
          setSchedules([...data]);
        });
      })

      connection.onclose(e => {
        setConnection()
      })

      await connection.start();
      await connection.invoke('AddToGroupAsync', turfId);
      setConnection(connection);
    } catch (err) {
      console.error(err)
    }
  }

  const handleUpdate = async () => {
    try {
      await connection.invoke('UpdateStatusTurfAsync', 
      {
        ScheduleId: selectedEvent.id,
        Status: status
      } );
    } catch (err) {
      console.error(err)
    }
    setIsOpenModal(false);
  }

  return (
    <div className="App">
      {/* <svg data-testid="AccessTimeIcon"></svg> */}
      <Dialog open={isOpenModal} onClose={handleClose}>
        <DialogTitle>Booking details</DialogTitle>
        <DialogContent>
          <DialogContentText>
            {moment(selectedEvent.start).format('HH:mm') + '-' + moment(selectedEvent.end).format('HH:mm')}
          </DialogContentText>
          <DialogContentText>
            {selectedEvent.title}
          </DialogContentText>
          <FormControl sx={{ m: 1, minWidth: 240 }} size="small">
            <InputLabel id="demo-select-small">Status</InputLabel>
            <Select
              labelId="demo-select-small"
              id="demo-select-small"
              value={status}
              label="Age"
              onChange={handleChangeStatus}
            >
              <MenuItem value={"Booked"}>Booked</MenuItem>
              <MenuItem value={"Pending"}>Pending</MenuItem>
            </Select>
          </FormControl>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleUpdate}>Update</Button>
        </DialogActions>
      </Dialog>
      <Calendar
        selectable
        localizer={localizer}
        events={myEventsList}
        style={{ height: 700 }}
        step={15}
        defaultView={'week'}
        defaultDate={new Date()}
        eventPropGetter={(myEventsList) => {
          const backgroundColor = myEventsList.status === "Booked" ? 'red' : 'blue';
          return { style: { backgroundColor } }
        }}
        onSelectEvent={onSelectEvent}
      />
    </div>
  );


}


