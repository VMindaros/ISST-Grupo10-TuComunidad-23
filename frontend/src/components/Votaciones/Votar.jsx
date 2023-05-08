import React, { useState } from 'react';
import { Link, useNavigate, useParams } from "react-router-dom";
import { Button, Form, Container, Col, Card } from 'react-bootstrap';
import useRequireAuth from '../Login/useRequireAuth';



const Votar = (props) => {
    useRequireAuth();
    const [vote, setVote] = useState('Abstencion');
    const navigate = useNavigate();
    let{juntaId} = useParams();
    const votoslist = props.votoslist;
    const userData = props?.userData;
    const userId = userData?.id; // a cambiar por el id de usuario de la sesion activa


    const handleVote = (e) => {
        setVote(e.target.value);
      };
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        if (vote.trim()) {
            const votosItem = {
                voto: vote,
                juntaId: juntaId,
                // TODO: cambiar cuando se establezca sesiones de usuario
                userId: userId,
            };

            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('user')).token},
                body: JSON.stringify({
                    ...votosItem
                    
                }),
            };
            
            await fetch('http://localhost:8080/juntas/votos', requestOptions);
            setVote('');
            navigate('/juntas')
            
        }
    }; 

    let haVotado = false;

    for (let i = 0; i < votoslist.length; i++) {
      const votoItem = votoslist[i];
      if (votoItem.userId === userId && votoItem.juntaId === parseInt(juntaId)) {
        haVotado = true;
        break;
      }
    }
    
    return (
        <div className="contenedor-flexbox">
            <Container>
                {haVotado ? (
                    <Card className='text-center' style={{ marginTop: '8rem', backgroundColor: 'black'}}>
                        <Card.Body>
                            <h2 style={{ fontStyle: 'italic', color: '#ff6666' }}>Atención: Usted ya ha votado en esta junta.</h2>
                            <img src='/logocasastres.jpeg' alt='Imagen de votación' className="rounded-circle" width="100" height="100"/>
                        </Card.Body>
                    </Card>
                ) : (
                    <Form onSubmit={handleSubmit}>
                        <Form.Group className='mb-3'>
                            <Form>
                                <h1>Elige una opción</h1>
                                <Form.Group as={Col}>
                                    <Form.Check
                                        type="radio"
                                        label="A favor"
                                        name="voto"
                                        value="A favor"
                                        checked={vote === 'favor'}
                                        onChange={handleVote}
                                    />
                                </Form.Group>

                                <Form.Group as={Col}>
                                    <Form.Check
                                        type="radio"
                                        label="En contra"
                                        name="voto"
                                        value="En contra"
                                        checked={vote === 'contra'}
                                        onChange={handleVote}
                                    />
                                </Form.Group>

                                <Form.Group as={Col}>
                                    <Form.Check
                                        type="radio"
                                        label="Abstención"
                                        name="voto"
                                        value="Abstencion"
                                        checked={vote === 'abstencion'}
                                        onChange={handleVote}
                                    />
                                </Form.Group>
                            </Form>
                        </Form.Group>
                        <Button variant='success' type='submit' style={{ width: '7rem' }}>
                            Votar
                        </Button>{'  '}
                        <Link to={"/juntas"}>
                            <Button variant='danger' type='Volver' className='w-4' style={{ width: '7rem' }}>
                                Cancelar
                            </Button>
                        </Link>
                    </Form>
                )}
            </Container>
        </div>
    );
};

export default Votar;