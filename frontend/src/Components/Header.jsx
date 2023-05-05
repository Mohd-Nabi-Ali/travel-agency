import {Link, NavLink, useNavigate} from "react-router-dom";
import '../css/header.css'
import {Col, Container, Nav, Navbar, Row} from "react-bootstrap";
function Header(){
    let navigate = useNavigate();
    return(
        <header className='header' style={{backgroundColor: "lightgoldenrodyellow"}}>
            <Navbar collapseOnSelect expand="lg">
                <Container>
                    <Nav.Link as={NavLink} className="navLink" to="/"><h2>VOYAGE</h2></Nav.Link>
                    <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                    <Navbar.Collapse id="responsive-navbar-nav">
                            <Nav className="me-auto">
                                {localStorage.getItem("user") !== null &&
                                    <Nav.Link as={NavLink} className="navLink" to="/profile" style={({isActive}) =>
                                        isActive
                                            ? {
                                                color: 'brown',
                                                // background: 'orange'
                                            }
                                            : {color: 'green'}}
                                    ><h5>Личный кабинет</h5></Nav.Link>
                                }
                            </Nav>
                        {localStorage.getItem("user") === null &&
                            <Nav>
                                <Nav.Link as={NavLink} className="navLink" to="/login" style={({ isActive }) =>
                                    isActive
                                        ? {
                                            color: '#fff',
                                            background: 'orange'
                                        }
                                        : { color: 'green'}}
                                ><h5>Войти</h5></Nav.Link>
                                <Nav.Link as={NavLink} className="navLink" to="/registration" style={({ isActive }) =>
                                    isActive
                                        ? {
                                            color: '#fff',
                                            background: 'orange'
                                        }
                                        : { color: 'green'}}
                                ><h5>Регистрация</h5></Nav.Link>
                            </Nav>
                        }
                        {localStorage.getItem("user") !== null &&
                            <Nav>
                                <Nav.Link as={NavLink} className="navLink" onClick={()=>{localStorage.clear(); navigate("/login")}} to="/login" style={({ isActive }) =>
                                    isActive
                                        ? {
                                            color: '#fff',
                                            background: 'orange'
                                        }
                                        : { color: 'red'}}
                                ><h5>Выйти</h5></Nav.Link>
                            </Nav>
                        }
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </header>
    );
}
export default Header;
