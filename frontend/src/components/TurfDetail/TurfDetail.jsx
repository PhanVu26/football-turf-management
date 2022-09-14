import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import TurfService from "../../services/TurfService";
import Turf from "./Turf"

const TurfDetail = () => {
  const [turfs, setTurfs] = useState([])
  let { id } = useParams();
  useEffect(() => {
    TurfService.getTurfsInMainTurf(id).then((response) => {
      const { data } = response;
      setTurfs(data);
    });
  }, [id])

  return (
    <div className="width-screen flex justify-center">
      <div className="flex flex-col gap-6  mx-6 mb-6">
        <div className="text-2xl font-bold mt-3">Danh sách sân bóng:</div>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
          {
            turfs.map((turf, index) => (
              <Turf key={index} turf={turf} />
            ))
          }
        </div>
      </div>
    </div>
  )
}

export default TurfDetail
