import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import TurfService from "../services/TurfService";

const initialState = [];

export const retrieveTurfs = createAsyncThunk(
  "turfs/retrieve",
  async () => {
    const res = await TurfService.getAll();
    return res.data;
  }
);



const turfSlice = createSlice({
  name: "turf",
  initialState,
  reducers: {
    // add your non-async reducers here
    login: (state, action) => {
      state.account = action.payload
    },
  },
  
  extraReducers: {
    [retrieveTurfs.fulfilled]: (state, action) => {
      return [...action.payload];
    },

  },
});

const { reducer } = turfSlice;
export default reducer;