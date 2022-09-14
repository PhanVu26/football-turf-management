import axios from "axios";
const account = JSON.parse(window.localStorage.getItem('account'));
export default axios.create({
  baseURL: process.env.REACT_APP_BASE_URL,
  headers: {
    "Content-type": "application/json",
    "Authorization": `Bearer ${account?.token}`
  }
});