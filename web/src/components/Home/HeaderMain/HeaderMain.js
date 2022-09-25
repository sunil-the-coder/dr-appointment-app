import React from 'react';
import { Button, Container, Row } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import chair from '../../../images/Mask Group 1.png';
import './HeaderMain.css';

const HeaderMain = () => (
    <main>
        <Container>
            <Row style={{ height: '400px' }} className="d-flex align-items-center">
                <div className="col-md-4 offset-md-1">
                    <h1>
                        Book Your Appointment Here
                    </h1>
                    <Button as={Link} to="/appointment">
                        Get Appointment
                    </Button>
                </div>
                <div className="col-md-6">
                    <img src={chair} alt="" className="img-fluid" />
                </div>
            </Row>
        </Container>
    </main>
);

export default HeaderMain;
