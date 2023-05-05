import React, { useState } from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import {Button, Col, Container, Row, Stack} from 'react-bootstrap';
import '../css/login.css';
const Registration = () => {
    let navigate = useNavigate();
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [lastName, setLastName] = useState("");
    const [firstName, setFirstName] = useState("");
    const [passwordConfirm, setPasswordConfirm] = useState("");
    const [middleName, setMiddleName] = useState("");
    const [email, setEmail] = useState("");
    const [age, setAge] = useState("");
    const [phone, setPhone] = useState("");
    const [passportData, setPassportData] = useState("");
    const onSubmitLogin = async e => {
        e.preventDefault();
        try {
            const body = {
                username: userName,
                password: password,
                passportdata: passportData,
                firstname: firstName,
                middlename: middleName,
                lastname: lastName,
                age: age,
                phone: phone,
                email: email,
                passwordConfirm: passwordConfirm
            };
            await axios.post("/api/auth/registration", body);
            navigate("/login")
        } catch (err) {
            console.error(err.message);
        }
    };
    const redirectToRegistration = () => {
        navigate("/registration")
    };
    return (
        <Container style={{marginTop: '50px'}}>
            <Row className="align-items-center justify-content-center" md="auto" xs="auto" xl="auto">
                <Col md="auto" xs="auto">
                    <div  className="login">
                        <Stack gap={3} className="mx-auto">
                            <h1 className="text-center mt-5">Создайте аккаунт</h1>
                            <form className="mt-5" onSubmit={onSubmitLogin}>
                                <Stack gap={3} className="mx-auto">
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={userName}
                                        placeholder="Логин"
                                        required
                                        onChange={e => setUserName(e.target.value)}
                                    />
                                    <input
                                        type="password"
                                        className="form-control"
                                        value={password}
                                        placeholder="Пароль"
                                        required
                                        onChange={e => setPassword(e.target.value)}
                                    />
                                    <input
                                        type="password"
                                        className="form-control"
                                        value={passwordConfirm}
                                        placeholder="Повторите пароль"
                                        required
                                        onChange={e => setPasswordConfirm(e.target.value)}
                                    />
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={firstName}
                                        placeholder="Имя"
                                        required
                                        onChange={e => setFirstName(e.target.value)}
                                    />
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={lastName}
                                        placeholder="Фамилия"
                                        required
                                        onChange={e => setLastName(e.target.value)}
                                    />
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={middleName}
                                        placeholder="Отчетство"
                                        required
                                        onChange={e => setMiddleName(e.target.value)}
                                    />
                                    <input
                                        type="number"
                                        className="form-control"
                                        value={age}
                                        placeholder="Возраст"
                                        required
                                        onChange={e => setAge(e.target.value)}
                                    />
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={passportData}
                                        placeholder="Паспортные данные"
                                        required
                                        onChange={e => setPassportData(e.target.value)}
                                    />
                                    <input
                                        type="tel"
                                        className="form-control"
                                        value={phone}
                                        placeholder="Телефон"
                                        required
                                        onChange={e => setPhone(e.target.value)}
                                    />
                                    <input
                                        type="email"
                                        className="form-control"
                                        value={email}
                                        placeholder="Почта"
                                        required
                                        onChange={e => setEmail(e.target.value)}
                                    />
                                    <Button className="btn btn-success" type="submit">Создать аккаунт</Button>
                                </Stack>
                            </form>
                            <Link to="/login">Уже есть аккаунт?</Link>
                        </Stack>
                    </div>
                </Col>
            </Row>
        </Container>

    );
};

export default Registration;
