import { Button, Col, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import { Link } from "react-router-dom";
import Card from 'react-bootstrap/Card';
import { ArrowRight } from "react-bootstrap-icons";
import useRequireAuth from "../Login/useRequireAuth";

const VerNoticias = (props) => {
    useRequireAuth();
    const noticiaslist = props.noticiaslist;
    const userData = props.userData

    let compruebaRole = false;
    if (userData?.roles.includes('ROLE_ADMIN') || userData?.roles.includes('ROLE_PRESIDENTE')) {
        compruebaRole = true;
    }

    return (
        <Container style={{ maxHeight: '100vh', overflowY: 'scroll' }}>
            <Row className="my-2">
                <Col>
                    <h2>Últimas noticias</h2>
                    { compruebaRole ? 
                    <Link to={'/noticias/crear'}>
                        <Button>Crear nueva noticia</Button>
                    </Link>
                    : null }
                </Col>
            </Row>
            <Row>
                {noticiaslist.slice().reverse().map((noticiaItem) => (
                    <Col key={noticiaItem.id} md={6}>
                        <Link to={`/noticias/${noticiaItem.id}`} style={{ color: 'black', textDecoration: 'none' }}>
                            <Card className="my-1">
                                <Card.Body style={{ maxWidth: '90%' }}>
                                    <Card.Title href="#">{noticiaItem.titulo}</Card.Title>
                                    <Card.Subtitle className="text-muted">
                                        <p>Creado el {noticiaItem.fechaCreacion} por Administrador</p>
                                    </Card.Subtitle>
                                    {/*}
                                        <Card.Text>{noticiaItem.descripcion}</Card.Text>
                                        {*/}
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
    );
};

export default VerNoticias;