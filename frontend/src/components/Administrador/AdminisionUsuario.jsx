import { Button, Card, Col, Container, Row } from "react-bootstrap";
import useRequireAuth from "../Login/useRequireAuth"
import { CheckSquare, FileEarmarkText, XSquare } from "react-bootstrap-icons";
import { useNavigate } from "react-router-dom";
import UserService from "../../services/user.service";
import { useEffect, useState } from "react";

const AdminisionUsuario = (props) => {
    useRequireAuth();
    const peticioneslist = props.peticioneslist;
    const navigate = useNavigate();
    const [flip, setFlip] = useState(true);

    useEffect(() => {
        
    }, [flip]);

    const base64ToBlob = (base64, contentType = 'application/pdf', sliceSize = 512) => {
        const byteCharacters = atob(base64);
        const byteArrays = [];

        for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
            const slice = byteCharacters.slice(offset, offset + sliceSize);

            const byteNumbers = new Array(slice.length);
            for (let i = 0; i < slice.length; i++) {
                byteNumbers[i] = slice.charCodeAt(i);
            }

            const byteArray = new Uint8Array(byteNumbers);
            byteArrays.push(byteArray);
        }

        return new Blob(byteArrays, { type: contentType });
    };

    const downloadUrl = (pdf) => {
        const pdfBlob = base64ToBlob(pdf)
        return URL.createObjectURL(pdfBlob)
    };

    const handleReject = async (id) => {
        const a = await UserService.deletePeticion(id);
        setFlip(!flip);
        navigate("/admisionregistro");
    };

    const handleAccept = async (e, id) => {
        setFlip(!flip);
        navigate("/admisionregistro")
    };

    return (
        <Container style={{ maxHeight: '100vh', overflowY: 'scroll' }}>
            <Row className="my-2">
                <Col className="h2">
                    Admisión de usuarios
                </Col>
            </Row>
            <Row >
                {peticioneslist.slice().reverse().map((peticionItem) => (
                    <Col key={peticionItem.id} lg={6}>
                        <Card className="my-1">
                            <Card.Body style={{ maxWidth: '90%' }}>
                                <Card.Title>Datos provisionales</Card.Title>
                                <Card.Subtitle className="text-muted">
                                    <p>
                                        Nº teléfono: {peticionItem.tlfNumber}<br />
                                        DNI: {peticionItem.dni}<br />
                                        Ubicación: {peticionItem.piso}{peticionItem.letra}<br />
                                    </p>
                                </Card.Subtitle>
                            </Card.Body>
                            <div style={{
                                position: 'absolute',
                                right: '140px',
                                top: '50%',
                                transform: 'translateY(-50%)',
                                fontSize: '2rem',
                            }}>
                                <a href={downloadUrl(peticionItem.adjunto)} download="document.pdf" target="_blank" rel="noopener noreferrer">
                                    <FileEarmarkText color="royalblue" />
                                </a>
                            </div>
                            <div style={{
                                position: 'absolute',
                                right: '80px',
                                top: '50%',
                                transform: 'translateY(-50%)',
                                fontSize: '2rem'
                            }}>
                                <CheckSquare color="green" onClick={() => { }} />
                            </div>
                            <div style={{
                                position: 'absolute',
                                right: '20px',
                                top: '50%',
                                transform: 'translateY(-50%)',
                                fontSize: '2rem'
                            }}>
                                <XSquare color="red" onClick={() => handleReject(peticionItem.id)} />
                            </div>
                        </Card>
                    </Col>
                ))}
            </Row>
        </Container>
    );
}

export default AdminisionUsuario;