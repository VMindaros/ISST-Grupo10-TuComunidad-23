import useRequireAuth from "../Login/useRequireAuth";
import { Link } from "react-router-dom";
import { Container, Row, Col } from "react-bootstrap";
import Card from 'react-bootstrap/Card';
import { ArrowRight } from "react-bootstrap-icons";

const GestionUsuario = (props) => {
    useRequireAuth();
    const usuarioslist = props.usuarioslist;

    const RolesList = ({ roles }) => (
        <ul>
            {roles.sort((a, b) => a.nombre.localeCompare(b.nombre))
                .map((role, index) => (
                    <li key={index}>{role.nombre}</li>
                ))}
        </ul>
    );

    return (
        <Container style={{ maxHeight: '100vh', overflowY: 'scroll' }}>
            <Row className="my-2">
                <Col>
                    <h2>Gestión de usuarios</h2>
                </Col>
            </Row>
            <Row>
                {usuarioslist.slice().reverse()
                    .filter((usuarioItem) => !usuarioItem.roles.some((role) => role.nombre === "ROLE_ADMIN"))
                    .map((usuarioItem) => (
                        <Col key={usuarioItem.id} lg={4}>
                            {/* Poner un link para modificar */}
                            <Link to={`/gestionusuarios/${usuarioItem.id}`} style={{ color: 'black', textDecoration: 'none' }}>
                                <Card className="my-1">
                                    <Card.Body stlye={{ maxWidth: '90%' }}>
                                        <Card.Title>{usuarioItem.tlfNumber}</Card.Title>
                                        <Card.Subtitle className="text-muted">
                                            <p>DNI: {usuarioItem.datosVecino.dni}<br />
                                                Dirección: {usuarioItem.datosVecino.piso}{usuarioItem.datosVecino.letra}<br />
                                                Rol: <RolesList roles={usuarioItem.roles} /></p>
                                        </Card.Subtitle>
                                    </Card.Body>
                                    <div className="arrow" style={{
                                        position: 'absolute',
                                        right: '20px',
                                        top: '50%',
                                        transform: 'translateY(-50%)',
                                        fontSize: '2rem'
                                    }}>
                                        <ArrowRight color="royalblue" />
                                    </div>
                                </Card>
                            </Link>
                        </Col>
                    ))}
            </Row>
        </Container>
    )
}

export default GestionUsuario;