import React from 'react'
import { Rating } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const Turf = ({ turf}) => {
    const navigate = useNavigate()
    const handleClick = () => {
      navigate(`/schedule/${turf.id}`)
    };
    
    return (
        <button onClick={handleClick}>
        <div className="max-w-sm bg-white rounded-lg border border-gray-200 shadow-md dark:bg-gray-800 dark:border-gray-700">
          <div >
            <img
              className="rounded-t-lg w-full h-32"
              src={turf.imageLinks[0]}
              alt=""
            />
          </div>
          <div className="p-5 flex flex-col">
            <div >
              <h5 className="mb-2 text-xl font-bold tracking-tight text-gray-900 dark:text-white">
                {turf.name}
              </h5>
            </div>
            <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">
              {turf.address}
            </p>
            <div className="flex justify-between items-center">
              <span className="font-semibold text-gray-500 text-lg">{turf.turfType === "SevenASide" ? "Sân 7" : "Sân 5"}</span>
              <Rating name="read-only" value={turf.rating} readOnly size="small" />
            </div>
          </div>
        </div>
      </button>
    )
}
export default Turf
