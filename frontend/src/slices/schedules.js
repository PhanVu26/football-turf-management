import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import ScheduleService from "../services/ScheduleService";

const initialState = [];

export const retrieveSchedules = createAsyncThunk(
  "schedules/retrieve",
  async () => {
    const res = await ScheduleService.getAll();
    return res.data;
  }
);

export const updateScheduleStatus = createAsyncThunk(
  "schedules/update",
  async ({id, data}) => {
    const res = await ScheduleService.updateScheduleStatus(id, data);
    return res.data;
  }
);

const scheduleSlice = createSlice({
  name: "schedule",
  initialState,
  extraReducers: {
    [retrieveSchedules.fulfilled]: (state, action) => {
      return [...action.payload];
    },
    [updateScheduleStatus.fulfilled]: (state, action) => {
      const index = state.findIndex(schedule => schedule.id === action.payload.id);
      state[index] = {
        ...state[index],
        ...action.payload,
      };
    },
  },
});

const { reducer } = scheduleSlice;
export default reducer;