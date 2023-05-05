import {Button, Table} from "react-bootstrap";
import React from "react";
import {useHistory, useNavigate} from "react-router-dom";

const Tours = (props) => {
    let navigate = useNavigate();
    return(
        <Table striped bordered hover responsive style={{padding:'30',background:"white",margin:'30'}}>
            <thead>
            <tr>
                <th><h4 className="mid">ID тура</h4></th>
                <th><h4 className="mid">Пункт отправления</h4></th>
                <th><h4 className="mid">Пункт назначения</h4></th>
                <th><h4 className="mid">Стоимость тура</h4></th>
                <th><h4 className="mid">Дата отправления</h4></th>
                <th><h4 className="mid">Кол-во путёвок</h4></th>
                <th><h4 className="mid">Действие</h4></th>
            </tr>
            </thead>
            <tbody>
            {props.tours?.map(tour => (
                <tr key={tour.id}>
                    <td>{tour.id}</td>
                    <td>{tour.start}</td>
                    <td>{tour.finish}</td>
                    <td>{tour.price}</td>
                    <td>{tour.date}</td>
                    <td>{tour.count}</td>
                    <td>
                        <Button className="btn btn-success" onClick={() => navigate("/tours/"+tour.id,{tour})}>Узнать детали</Button>
                    </td>
                </tr>
                ))}
            </tbody>
        </Table>
    )
}
export default Tours;