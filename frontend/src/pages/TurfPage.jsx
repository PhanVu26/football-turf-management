import React from 'react'
import NavBar from '../components/NavBar/NavBar'
import SideBar from '../components/SideBar/SideBar'
import TurfDetail from '../components/TurfDetail/TurfDetail'

const TurfPage = () => {
    return (
        <div className='flex flex-col h-screen'>
            <NavBar />
            <div className="flex flex-1 h-full">
                <SideBar />
                <TurfDetail />
            </div>
        </div>
    )
}

export default TurfPage
