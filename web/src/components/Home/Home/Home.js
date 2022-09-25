import React from 'react';
import Appointment from '../Appointment/Appointment';
import Contact from '../Contact/Contact';
import Doctors from '../Doctors/Doctors';
import Header from '../Header/Header';
import Services from '../Services/Services';
import Testimonials from '../Testimonials/Testimonials';

const Home = () => (
    <div>
        <Header />
        <Services />
        <Appointment />
        <Testimonials />
        <Doctors />
        <Contact />
    </div>
);

export default Home;
