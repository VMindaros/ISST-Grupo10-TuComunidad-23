import { Button, Col, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { Link } from "react-router-dom";
import Card from 'react-bootstrap/Card';
import useRequireAuth from '../Login/useRequireAuth';
import VerVoto from '../Votaciones/VerVoto';



const VerJuntas = (props) => {
    useRequireAuth();
    const juntaslist = props.juntaslist
    const votoslist = props.votoslist
    const userData = props.userData

    let compruebaRole = false;
    if (userData?.roles.includes('ROLE_ADMIN') || userData?.roles.includes('ROLE_PRESIDENTE')) {
        compruebaRole = true;
    }

    return (
        <Container style={{ maxHeight: '100vh', overflowY: 'scroll' }}>

                <Row className="my-2">
                <Col>
                    <h2>Juntas</h2>
                    { compruebaRole ? 
                    <Link to={'/juntas/crear'}>
                    <Button>Crear nueva junta</Button>
                    </Link>
                    : null  }
                </Col>
                </Row>

            {juntaslist.slice().reverse().map((juntaItem) => (
                <Row className="my-2" key={juntaItem.id}>
                    <Col>
                        <Card>
                            <Card.Body>
                                <Card.Title href="#">{juntaItem.titulo}</Card.Title>
                                <Card.Subtitle className="text-muted">
                                    <p>Creado el {juntaItem.fechaCreacion} por Presidente</p>
                                </Card.Subtitle>
                                <Card.Text>{juntaItem.descripcion}</Card.Text>
                                {}
                                { parseInt(juntaItem.votacionActiva) === parseInt('1') ? (
                                    <Link to={`/juntas/${juntaItem.id}`}>
                                    <Button variant='success' type='submit' style={{ width: '7rem' }}>Votar</Button>
                                    </Link>
                                ) : (
                                    <p className="text-muted mb-0" style={{ fontStyle: "italic" }}>La votación no esta activa para esta junta o ha expirado.</p>
                                )}
                            </Card.Body>
                           <VerVoto votoslist={votoslist} juntaslist={juntaslist} juntaId={juntaItem.id} votacionActiva={parseInt(juntaItem.votacionActiva)}/> 
                        </Card>
                    </Col>
                </Row>
            ))}
        </Container>
    );
};

export default VerJuntas;