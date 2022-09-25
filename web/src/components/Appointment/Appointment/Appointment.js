import React, { useState } from 'react';
import NavBar from '../../Shared/NavBar/NavBar';
import AppointmentHeader from '../AppointmentHeader/AppointmentHeader';
import BookAppointment from '../BookAppointment/BookAppointment';

const Appointment = () => {
    const [selectedDate, setSelectedDate] = useState(new Date());
    const handleDateChange = (date) => {
        setSelectedDate(date);
    };
    return (
        <div>
            <NavBar />
            <AppointmentHeader handleDateChange={handleDateChange} />
            <BookAppointment date={selectedDate} />
        </div>
    );
};

export default Appointment;
