import "./App.css";
import {LoginPage, HomePage, SchedulePage} from "./pages";
import { Routes, Route, BrowserRouter } from "react-router-dom";
import TurfPage from "./pages/TurfPage";


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/turf/:id" element={<TurfPage />} />
        <Route path="/schedule/:turfId" element={<SchedulePage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
