import React, { useState } from 'react';
import { Button, Container, Form } from 'react-bootstrap';
import { Link, useNavigate } from "react-router-dom";
import useRequireAuth from '../Login/useRequireAuth';

const CrearJunta = (props) => {
    useRequireAuth();
    const [titulo, setTitulo] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const [votacionActiva, setvotacionActiva] = useState('1');
    const [error, setError] = useState('');
    const userData = props.userData
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!titulo.trim() || !descripcion.trim()) {
            setError('Por favor, rellene todos los campos');
            return;
        }
    
        const juntasItem = {
            titulo,
            descripcion,
            fechaCreacion: new Date().toISOString(),
            votacionActiva,
        };
    
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('user')).token},
            body: JSON.stringify({
                ...juntasItem
            }),
        };
    
        await fetch('http://localhost:8080/juntas', requestOptions);
        setTitulo('');
        setDescripcion('');
        navigate('/juntas')
    };



    let compruebaRole = false;
    if (userData?.roles.includes('ROLE_ADMIN') || userData?.roles.includes('ROLE_PRESIDENTE')) {
        compruebaRole = true;
    }
    return (
        <div className='mx-4 my-4'>
            { compruebaRole ?
                <Container className='mx-4 my-4'>
                    <h2>Nueva junta</h2>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group className='mb-3'>
                            <Form.Label className='h4'>Título</Form.Label>
                            <Form.Control as="textarea" rows={2} onChange={(e) => setTitulo(e.target.value)}></Form.Control>
                        </Form.Group>
                        {error && (
                            <div className="alert alert-danger mt-3" role="alert">
                                {error}
                            </div>
                        )}

                        <Form.Group className='mb-3'>
                            <Form.Label className='h4'>Descripción</Form.Label>
                            <Form.Control as="textarea" rows={10} onChange={(e) => setDescripcion(e.target.value)}></Form.Control>
                        </Form.Group>
                        {error && (
                            <div className="alert alert-danger mt-3" role="alert">
                                {error}
                            </div>
                        )}
                        <select  onChange={(e) => setvotacionActiva(e.target.value)} className="form-select mb-3">
                            <option value="">¿Quiere activar la votación? Por defecto será activa</option>
                            <option value="1">Si</option>
                            <option value="0">No</option>
                        </select>
                        <Button variant='success' type='submit' style={{ width: '7rem' }}>
                            Crear
                        </Button>{'  '}

                        <Link to={"/juntas"}>
                            <Button variant='danger' type='Volver' className='w-4' style={{ width: '7rem' }}>
                                Cancelar
                            </Button>
                        </Link>
                    </Form>
                </Container>
            : null }
        </div>
    );
};

export default CrearJunta;