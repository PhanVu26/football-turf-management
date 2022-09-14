import { configureStore } from '@reduxjs/toolkit'
import turfReducer from './slices/turfs';
import scheduleReducer from './slices/schedules';
const reducer = {
  turfs: turfReducer,
  schedules: scheduleReducer
}
const store = configureStore({
  reducer: reducer,
  devTools: true,
})
export default store;