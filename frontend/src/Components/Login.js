import React, { useState } from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import {Button, Col, Container, Row, Stack} from 'react-bootstrap';
import '../css/login.css';
import {login} from '../Actions/auth';
const Login = () => {
    let navigate = useNavigate();
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const onSubmitLogin = async e => {
        e.preventDefault();
        const body = { username: userName,password: password };
        try {
            const response = await axios.post("/api/auth/login", {
                email: body.username,
                password: body.password
            }).then(response => {
                if (response.data) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                    navigate("/profile")
                }
                setError("Неправильный ввод данных");
            })
        } catch (err) {
            console.error(err.message);
            setError("Неправильный ввод данных");
        }
    };
    const redirectToRegistration = () => {
        navigate("/registration")
    };
    return (
        <Container fluid  className="vh-100 d-flex flex-column ">
            <Row className="align-items-center justify-content-center" md="auto" xs="auto" xl="auto">
                <Col md="auto" xs="auto">
                <div  className="login">
                <Stack gap={3} className="mx-auto">
                <h1 className="text-center mt-5">Войдите в систему</h1>
                <form className="mt-5" onSubmit={onSubmitLogin}>
                    <Stack gap={3} className="mx-auto">
                        <h4>{error}</h4>
                    <input
                        type="text"
                        className="form-control"
                        value={userName}
                        placeholder="Логин"
                        required
                        onChange={e => setUserName(e.target.value)}
                    />
                    <input
                        type="Пароль"
                        className="form-control"
                        value={password}
                        placeholder="Пароль"
                        required
                        onChange={e => setPassword(e.target.value)}
                    />
                    <Button className="btn btn-success" type="submit">Войти</Button>
                    </Stack>
                </form>
                {/*<p onClick={() => redirectToRegistration()} style={{color:"blue"}}>Зарегистрироваться</p>*/}
                <Link to="/registration">Еще нет аккаунта?</Link>
                </Stack>
                </div>
                </Col>
            </Row>
        </Container>

    );
};

export default Login;
