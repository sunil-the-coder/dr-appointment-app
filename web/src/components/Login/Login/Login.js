import React, { useContext, useState } from 'react';
import { makeStyles } from '@material-ui/core';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import firebase from 'firebase/app';
import 'firebase/auth';
import { useHistory, useLocation } from 'react-router-dom';
import { UserContext } from '../../../App';
import loginBG from '../../../images/loginBG.png';

const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        backgroundColor:'rgba(95,190,154,0.99)',
        alignItems: 'center',
        padding: theme.spacing(2),
        width: '540px',
        '& .MuiTextField-root': {
            margin: theme.spacing(1),
            width: '400px',
        },
        '& .MuiButtonBase-root': {
            margin: theme.spacing(2),
        },
    },
}));
const Login = () => {
    const [loggedInUser, setLoggedInUser] = useContext(UserContext);
    console.log(loggedInUser);
    const history = useHistory();
    const location = useLocation();
    const { from } = location.state || { from: { pathname: '/' } };
    const classes = useStyles();
    // create state variables for each input
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [isLogin, setIsLogin] = useState(true);

    const signUpSubmit = () => {
        console.log(firstName, lastName, email, password);
        setLoggedInUser({ name: "Mangesh Shinde", email:"mangeshshinde@gmail.com"});
        sessionStorage.setItem('token', "SGVsbG8sIHdvcmxkIQ==");
        history.replace(from);
    };

    function loginSubmit() {
        console.log(email, password);
        setLoggedInUser({ name: "Mangesh Shinde", email:email, password: password});
        sessionStorage.setItem('token', "SGVsbG8sIHdvcmxkIQ==");
        history.replace(from);
    }

    function toSignup() {
        setIsLogin(!isLogin);
    }

    return (
        <div className="login-page container">
            <div className="row align-items-center" style={{ height: '100vh' }}>
                <div className="col-md-6 shadow p-3">
                        {!isLogin?
                            <form className={classes.root} onSubmit={signUpSubmit}>
                                <TextField
                                    label="First Name"
                                    variant="filled"
                                    required
                                    value={firstName}
                                    onChange={e => setFirstName(e.target.value)}
                                />
                                <TextField
                                    label="Last Name"
                                    variant="filled"
                                    required
                                    value={lastName}
                                    onChange={e => setLastName(e.target.value)}
                                />
                                <TextField
                                    label="Email"
                                    variant="filled"
                                    type="email"
                                    required
                                    value={email}
                                    onChange={e => setEmail(e.target.value)}
                                />
                                <TextField
                                    label="Password"
                                    variant="filled"
                                    type="password"
                                    required
                                    value={password}
                                    onChange={e => setPassword(e.target.value)}
                                />
                                <div>
                                    <Button variant="contained" onClick={toSignup}>
                                        Login
                                    </Button>
                                    <Button type="submit" variant="contained" color="primary">
                                        Signup
                                    </Button>
                                </div>
                            </form>
                            :
                            <form className={classes.root} onSubmit={loginSubmit}>
                                <TextField
                                    label="Email"
                                    variant="filled"
                                    type="email"
                                    required
                                    value={email}
                                    onChange={e => setEmail(e.target.value)}
                                />
                                <TextField
                                    label="Password"
                                    variant="filled"
                                    type="password"
                                    required
                                    value={password}
                                    onChange={e => setPassword(e.target.value)}
                                />
                                <div>
                                    <Button type="submit" variant="contained" color="primary">
                                        Login
                                    </Button>
                                    <Button variant="contained" onClick={toSignup}>
                                        Signup
                                    </Button>
                                </div>
                            </form>
                        }

                    </div>
                <div className="col-md-6 d-none d-md-block align-self-end">
                    <img className="img-fluid" src={loginBG} alt="" />
                </div>
            </div>
        </div>
    );
};

export default Login;
