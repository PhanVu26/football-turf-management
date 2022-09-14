import React from "react";
import { useNavigate } from "react-router-dom"
import { Rating } from '@mui/material';

const CardCustom = ({ turf: mainTurf }) => {
  const navigator = useNavigate()
  const handleClick = () => {
    navigator(`/turf/${mainTurf.id}`)
  }
  return (
    <button onClick={handleClick}>
      <div className="max-w-sm bg-white rounded-lg border border-gray-200 shadow-md dark:bg-gray-800 dark:border-gray-700">
        <div>
          <img
            className="rounded-t-lg w-full h-32"
            src={mainTurf.imageLink[0]}
            alt=""
          />
        </div>
        <div className="p-5 flex flex-col">
          <div >
            <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 dark:text-white truncate ">
              {mainTurf.name}
            </h5>
          </div>
          <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">
            {mainTurf.address}
          </p>
          <div className="flex justify-between items-center">
            <span className="font-semibold text-red-500 text-lg">150k/h</span>
            <Rating name="read-only" value={4} readOnly size="small" />
          </div>
        </div>
      </div>
    </button>
  );
};

export default CardCustom;
