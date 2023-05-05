import Tours from "./Tours";
import {
    Button,
    Card,
    CardImg,
    Form,
    Col,
    Container, FormControl,
    InputGroup,
    ListGroup,
    ListGroupItem,
    Modal,
    Row,
    Stack
} from "react-bootstrap";
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import React from 'react';
import axios from "axios";
function MyVerticallyCenteredModal(props) {
    let navigate = useNavigate();
    const [user, setUser] = useState([]);
    const getUser = async () => {
        try {
            let token = JSON.parse(localStorage.getItem("user"));
            await axios.get("/api/user/info",{
                headers: {
                    'Authorization': `Bearer ${token}`
                }}).then((response) => {
                setUser(response.data);
            })
        } catch (err) {
            console.error(err.message);
        }
    };
    const bookTour = async () => {
        try {
            let token = JSON.parse(localStorage.getItem("user"));
            console.log(props.tour);
            await axios.post("/api/user/booking",
                {
                    "id": props.tour.id
                },
                {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }}
            ).then((response) => {
                navigate("/profile")
            })
        } catch (err) {
            console.error(err.message);
        }
    };
    useEffect(() => {
        getUser();
    }, []);
    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Подтверждение бронирования
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <h4>Проверьте, все ли данные верны</h4>
                <p><b>Тур</b></p>
                <ListGroup className="list-group-flush">
                    <ListGroupItem>Пункт отправления: {props.tour.start}</ListGroupItem>
                    <ListGroupItem>Пункт назначения: {props.tour.finish}</ListGroupItem>
                    <ListGroupItem>Дата отправления: {props.tour.date}</ListGroupItem>
                </ListGroup>
                <p><b>Турист</b></p>
                <Container>
                    <Row>
                        <Col>
                            <Form.Label htmlFor="basic-url">Имя</Form.Label>
                            <InputGroup className="mb-3">
                                <FormControl id="basic-url" aria-describedby="basic-addon3" value = {user.firstname} />
                            </InputGroup>
                            <Form.Label htmlFor="basic-url2">Отчетство</Form.Label>
                            <InputGroup className="mb-3">
                                <FormControl id="basic-url2" aria-describedby="basic-addon3" value = {user.middlename} />
                            </InputGroup>
                        </Col>
                        <Col>
                            <Form.Label htmlFor="basic-url">Фамилия</Form.Label>
                            <InputGroup className="mb-3">
                                <FormControl id="basic-url" aria-describedby="basic-addon3" value = {user.lastname} />
                            </InputGroup>
                            <Form.Label htmlFor="basic-url4">Серия и номер паспорта</Form.Label>
                            <InputGroup className="mb-3">
                                <FormControl id="basic-url4" aria-describedby="basic-addon3" value = {user.passportdata} />
                            </InputGroup>
                        </Col>
                    </Row>
                </Container>


            </Modal.Body>
            <Modal.Footer>
                <h4>Стоимость тура: {props.tour.price} рублей</h4>
                <Button variant="success" onClick={bookTour}>
                    Подтвердить
                </Button>
            </Modal.Footer>
        </Modal>
    );
}
const TourDetails = () => {
    let navigate = useNavigate();
    const { id } = useParams();
    const [tour, setTour] = useState([]);
    const [image, setImage] = useState("");
    const [modalShow, setModalShow] = React.useState(false);
    const getTour = async () => {
        try {
            await axios.get("/api/tours/"+id).then((response) => {
                setTour(response.data);

            })
        } catch (err) {
            console.error(err.message);
        }
    };

    useEffect(() => {
        getTour();
    }, []);
    return(
        <Container>
            <Row className="justify-content-center">
                <Col md="auto" xs="auto" xl="6">
                    <Card>
                        <Card.Body>
                            {tour.description &&
                                <Card.Img variant="top" src={tour.description.img} />
                            }
                            <Card.Title>{tour.start} - {tour.finish}</Card.Title>
                            <ListGroup className="list-group-flush">
                                <ListGroupItem>Пункт отправления: {tour.start}</ListGroupItem>
                                <ListGroupItem>Пункт назначения: {tour.finish}</ListGroupItem>
                                <ListGroupItem>Стоимость тура: {tour.price}</ListGroupItem>
                                <ListGroupItem>Дата отправления: {tour.date}</ListGroupItem>
                                <ListGroupItem>Кол-во путёвок: {tour.count}</ListGroupItem>
                            </ListGroup>
                            <Button variant="success" onClick={() => setModalShow(true)}>Забронировать</Button>
                            <MyVerticallyCenteredModal tour={tour}
                                                       show={modalShow}
                                                       onHide={() => setModalShow(false)}
                            />
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    )
}
export default TourDetails;