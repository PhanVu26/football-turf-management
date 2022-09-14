import http from "../http-common";
const getScheduleById = turfId => {
  return http.get(`/api/schedule/getScheduleInAMonth?turfId=${turfId}}`);
};
const updateScheduleStatus = (id, data) => {
  return http.put(`/slots/${id}`, data);
};
const ScheduleService = {
  getScheduleById,
  updateScheduleStatus
};
export default ScheduleService;