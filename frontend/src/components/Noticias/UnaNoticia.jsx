import { useState } from "react";
import { Button, Col, Image, Row, Container } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";
import useRequireAuth from '../Login/useRequireAuth';
import UserService from '../../services/user.service';




const UnaNoticia = (props) => {
    useRequireAuth();
    const [isExpanded, setIsExpanded] = useState(false);

    const toggleImageSize = () => {
        setIsExpanded(!isExpanded);
    }

    const smallSize = { maxWidth: '10em', cursor: 'pointer' };
    const largeSize = { maxWidth: '100%', cursor: 'pointer' };
    const imageSize = isExpanded ? largeSize : smallSize;

    let { noticiaId } = useParams();
    let position = noticiaId;
    const noticiaslist = props.noticiaslist;

    return (
        <Container style={{ maxHeight: '100vh', overflowY: 'scroll' }}>
            <Row className="text-break">
                <Col>
                    <p className="news-title"><b>{noticiaslist[position - 1].titulo}</b></p>
                    <p className="news-description">{noticiaslist[position - 1].descripcion}</p>
                </Col>
            </Row>
            <Row className="justify-content-center my-3 mx-5">
                {noticiaslist[position - 1].adjunto && (
                    <Image
                        src={`data:image/*;base64,${noticiaslist[position - 1].adjunto}`}
                        rounded="true"
                        thumbnail="true"
                        className="justify-center"
                        style={imageSize}
                        onClick={toggleImageSize}
                    />
                )}
            </Row>
            <Row className="mb-3">
                <div className="news-button">
                    <Link to={'/noticias'}>
                        <Button>Volver</Button>
                    </Link>
                </div>
                <div className="d-flex justify-content-center">
                <img src="/logocasastres.jpeg" alt="Imagen de perfil" className="mx-auto rounded-circle" width="80" height="80" style={{ marginTop: '320px' }} />
                </div>
            </Row>
        </Container>
    );
};

export default UnaNoticia;