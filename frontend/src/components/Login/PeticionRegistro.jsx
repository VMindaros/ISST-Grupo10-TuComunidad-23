import { useState } from "react";
import { Container, Button, Col, Row, Form } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";

const PeticionRegistro = (props) => {
    const [tlfNumber, setTlfNumber] = useState("");
    const [password, setPassword] = useState("");
    const [piso, setPiso] = useState(null);
    const [letra, setLetra] = useState("");
    const [dni, setDni] = useState("");
    const [adjunto, setAdjunto] = useState(null)

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        let adjuntoBytes = null;
        if (adjunto) {
            adjuntoBytes = await toByteArray(adjunto);
        }

        const peticionItem = {
            tlfNumber,
            password,
            piso,
            letra: letra.toUpperCase(),
            dni,
        }

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify({
                ...peticionItem,
                adjunto: adjuntoBytes ? Array.from(adjuntoBytes) : null
            })
        }

        await fetch("http://localhost:8080/peticionregistro", requestOptions);

        setTlfNumber("");
        setPassword("");
        setPiso(null);
        setLetra("");
        setDni("");
        setAdjunto(null);

        navigate("/login");
    };

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        if (file && file.type === 'application/pdf') {
            setAdjunto(file);
        } else {
            setAdjunto(null);
        }
    };

    const toByteArray = (file) => (
        new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsArrayBuffer(file);
            reader.onload = () => resolve(new Uint8Array(reader.result));
            reader.onerror = (error) => reject(error);
        })
    )

    return (
        <div className="d-flex flex-column align-items-center justify-content-center"
            style={{
                background: "linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url('/fondo-back.jpg')",
                backgroundPosition: "center",
                backgroundSize: "cover",
                height: "100vh",
                width: "100vw",
            }}>

            <Container className="wrapper py-4 w-50 my-3" style={{ textAlign: 'center', zIndex: 1, backgroundColor: 'rgba(0, 0, 0, 0.5)', borderRadius: '10px', }}>
                <Row className="justify-content-center">
                    <Col md={6} className="h3 w-100 text-center text-white mb-4">
                        Petición de registro a tuComunidad
                    </Col>
                </Row>
                <Row className="h4 justify-content-center text-center text-white mb-4">
                    <Col lg={6} className="h4 text-center text-white mb-3">
                        Tras rellenar el formulario, el administrador recibirá tu solicitud y será quien la acepta o rechace
                    </Col>
                </Row>
                <Row className="justify-content-center">
                    <Col md={6}>
                        <Form onSubmit={handleSubmit} className="justify-content-center">
                            <Form.Group className="mb-2">
                                <Form.Label className="h5 text-white">Número de teléfono</Form.Label>
                                <Form.Control type="text" value={tlfNumber} onChange={(e) => setTlfNumber(e.target.value)} maxLength={9} required></Form.Control>
                            </Form.Group>

                            <Form.Group className="mb-2">
                                <Form.Label className="h5 text-white">Contraseña</Form.Label>
                                <Form.Control type="password" value={password} onChange={(e) => setPassword(e.target.value)} maxLength={20} required></Form.Control>
                            </Form.Group>

                            <Form.Group className="mb-2">
                                <Form.Label className="h5 text-white">DNI</Form.Label>
                                <Form.Control type="text" value={dni} onChange={(e) => setDni(e.target.value)} maxLength={9} required></Form.Control>
                            </Form.Group>

                            <Row className="mb-2">
                                <Col>
                                    <Form.Group>
                                        <Form.Label className="h5 text-white">Piso</Form.Label>
                                        <Form.Control type="number" value={piso} onChange={(e) => setPiso(e.target.value)} min={0} max={10} required></Form.Control>
                                    </Form.Group>
                                </Col>
                                <Col>
                                    <Form.Group>
                                        <Form.Label className="h5 text-white">Letra</Form.Label>
                                        <Form.Control type="text" value={letra.toUpperCase()} onChange={(e) => setLetra(e.target.value)} maxLength={1} required></Form.Control>
                                    </Form.Group>
                                </Col>
                            </Row>

                            <Form.Group>
                                <Form.Label className="h5 text-white">Documento de propiedad (PDF)</Form.Label>
                                <Form.Control type="file" onChange={handleFileChange} accept="application/pdf" required></Form.Control>
                            </Form.Group>

                            <div className="d-flex flex-column">
                                <Button variant='success' type='submit' className="w-100 my-2">
                                    Enviar
                                </Button>
                                <Link to={"/login"}>
                                    <Button variant='danger' type='Volver' className="w-100">
                                        Cancelar
                                    </Button>
                                </Link>
                            </div>
                        </Form>
                    </Col>
                </Row>
            </Container>

        </div>
    );
}

export default PeticionRegistro;