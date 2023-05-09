import { Col, Row } from "react-bootstrap";
import { Container } from "react-bootstrap";

import Card from 'react-bootstrap/Card';

import useRequireAuth from '../Login/useRequireAuth';


const VerComentario = (props) => {
    useRequireAuth();
    const comentariosList = props.comentariolist;
    const id = props.sugerenciaId;
    return (
        <div className="contenedor-flexbox">
            <Container>
                {comentariosList.map((comentarioItem) => (
                    <div key={comentarioItem.id}>
                        {(comentarioItem.sugerenciaId === id ?
                            <Row className="my-2" >
                                <Col>
                                    <Card className="flex-fill text-wrap" style={{ backgroundColor: '#f2f2f2' }}>
                                        <Card.Body >
                                            <Card.Subtitle className="text-muted">Vecino respondió:</Card.Subtitle>
                                            <Card.Text className="text-truncate"style={{fontWeight: 700}}>{comentarioItem.descripcion}</Card.Text>
                                        </Card.Body>
                                    </Card>
                                </Col>
                            </Row>
                            : null)}
                    </div>
                ))}
            </Container>
        </div>
    );
};

export default VerComentario;