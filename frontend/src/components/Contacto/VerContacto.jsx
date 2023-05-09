import { Col, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";
import Card from 'react-bootstrap/Card';

const VerContacto = (props) => {
    const usuarioslist = props.usuarioslist;
    return (
        <div className="contenedor-flexbox">
            <Container style={{ maxHeight: '100vh', overflowY: 'scroll' }}>
                <Row className="my-2">
                    <Col>
                        <h2>Contactos</h2>
                    </Col>
                </Row>

                <Row className="my-2">

                    {usuarioslist.map((usuarioItem) => {
                        if (usuarioItem.roles.some((role) => role.nombre === 'ROLE_PRESIDENTE')) {
                            return (
                                <Col>
                                    <Card className="flex-fill text-wrap my-1" style={{ backgroundColor: '#f2f2f2' }}>
                                        <Card.Body className="d-flex flex-column justify-content-center text-center">
                                            <Card.Title href="#">Presidente</Card.Title>
                                            <Card.Subtitle className="mb-2 text-muted">
                                                <p>Contactos del presidente</p>
                                            </Card.Subtitle>
                                            {usuarioItem.tlfNumber && (
                                                <Card.Text className="text-truncate">
                                                    <b>Teléfono:</b> {usuarioItem.tlfNumber}
                                                </Card.Text>
                                            )}
                                            {/*}
      <Card.Text className="text-truncate">
        <b>Piso:</b> {usuarioItem.datosVecino.piso}{usuarioItem.datosVecino.letra}
      </Card.Text>
      {*/}
                                            <img src="/logocasastres.jpeg" alt="Imagen de perfil" className="mx-auto rounded-circle" width="50" height="50" style={{ marginTop: '-10px' }} />
                                        </Card.Body>
                                    </Card>
                                </Col>
                            );
                        }
                        return null;
                    })}

                    {usuarioslist.map((usuarioItem) => {
                        if (usuarioItem.roles.some((role) => role.nombre === 'ROLE_ADMIN')) {
                            return (
                                <Col>
                                    <Card className="flex-fill text-wrap my-1" style={{ backgroundColor: '#f2f2f2' }}>
                                        <Card.Body className="d-flex flex-column justify-content-center text-center">
                                            <Card.Title href="#">Administrador</Card.Title>
                                            <Card.Subtitle className="mb-2 text-muted">
                                                <p>Contactos del administrador</p>
                                            </Card.Subtitle>
                                            {usuarioItem.tlfNumber && (
                                                <Card.Text className="text-truncate">
                                                    <b>Teléfono:</b> {usuarioItem.tlfNumber}
                                                </Card.Text>
                                            )}
                                            {/*}
      <Card.Text className="text-truncate">
        <b>Piso:</b> {usuarioItem.datosVecino.piso}{usuarioItem.datosVecino.letra}
      </Card.Text>
      {*/}
                                            <img src="/logocasastres.jpeg" alt="Imagen de perfil" className="mx-auto rounded-circle" width="50" height="50" style={{ marginTop: '-10px' }} />
                                        </Card.Body>
                                    </Card>
                                </Col>
                            );
                        }
                        return null;
                    })}


                </Row>
            </Container>
        </div>

    );
};

export default VerContacto;