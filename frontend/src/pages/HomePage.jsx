import React, { useEffect, useState } from 'react'
import NavBar from '../components/NavBar/NavBar'
import Home from '../components/Home/Home'
import SideBar from '../components/SideBar/SideBar'
import TurfService from "../services/TurfService";

const HomePage = () => {
  const [position, setPosition] = useState()
  const [searchText, setSearchText] = useState("")
  const [type, setType] = useState(3);
  useEffect(() => {
    navigator.geolocation.getCurrentPosition(function (position) {
      setPosition(position)
    });
  }, [])

  const [turfs, setTurfs] = useState([]);
  useEffect(() => {
    TurfService.getMainTurfs().then((response) => {
      const { data } = response;
      setTurfs(data);
    });
  }, []);

  return (
    <div className="flex flex-col h-screen">
      <NavBar 
        type={type} 
        setType={setType} 
        setTurfs={setTurfs} 
        position={position} 
        searchText={searchText} 
        setSearchText = {setSearchText}/>
      <div className="flex flex-1 h-full">
        <SideBar />
        <Home turfs={turfs} />
      </div>
    </div>
  )
}

export default HomePage
