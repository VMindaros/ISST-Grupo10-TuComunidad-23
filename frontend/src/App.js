import React, { useEffect, useState } from 'react';
import { Route, Routes, Navigate, useLocation } from 'react-router-dom';
import CrearNoticias from './components/Noticias/CrearNoticias';
import VerNoticias from './components/Noticias/VerNoticias';
import Sidebar from './components/Noticias/Sidebar';
import VerSugerencias from './components/Sugerencias/VerSugerencias';
import CrearSugerencias from './components/Sugerencias/CrearSugerencias';
import VerJuntas from './components/Juntas/VerJuntas';
import CrearJunta from './components/Juntas/CrearJunta';
import Votar from './components/Votaciones/Votar';
import './App.css';
import VerContacto from './components/Contacto/VerContacto';
import CrearComentario from './components/Comentarios/CrearComentario';
import UnaNoticia from './components/Noticias/UnaNoticia';
import Login from './components/Login/Login';
import Register from './components/Login/Register';
import UserService from './services/user.service';
import { useRef } from 'react';
import GestionUsuario from './components/Administrador/GestionUsuario';
import VerUnUsuario from './components/Administrador/VerUnUsuario';
import PeticionRegistro from './components/Login/PeticionRegistro';
import AdminisionUsuario from './components/Administrador/AdminisionUsuario';

function App() {
    const location = useLocation();
    const [noticiaslist, setNoticiaslist] = useState([]);
    const [sugerenciaslist, setSugerenciaslist] = useState([]);
    const [comentariolist, setComentariolist] = useState([]);
    const [juntaslist, setJuntaslist] = useState([]);
    const [votoslist, setVotosList] = useState([]);
    const [usuarioslist, setUsuarioslist] = useState([]);
    const [peticioneslist, setPeticioneslist] = useState([]);
    const user = useRef(null); 
    

    useEffect(() => {
        if (!user.current || (JSON.parse(localStorage.getItem('user').token !== user.current.token))) {
            user.current = JSON.parse(localStorage.getItem('user'));
        }

        if (user.current && user.current.token) {
            const formatDate = (isoDateString) => {
                const date = new Date(isoDateString);
                const day = String(date.getDate()).padStart(2, '0');
                const month = String(date.getMonth() + 1).padStart(2, '0');
                const year = date.getFullYear();
                return `${day}-${month}-${year}`;
            };
    
            const fetchNoticias = async () => {
                const noticiasData = await UserService.getNoticias();
                const formattedNoticiasData = noticiasData.map((noticiaItem) => {
                    return {
                        ...noticiaItem,
                        fechaCreacion: formatDate(noticiaItem.fechaCreacion),
                    };
                });
                setNoticiaslist(formattedNoticiasData);
            };
    
            const fetchSugerencias = async () => {
                const sugerenciasData = await UserService.getSugerencias();
                // Format the date for each fetched item
                const formattedSugerenciasData = sugerenciasData.map((sugerenciaItem) => {
                    return {
                        ...sugerenciaItem,
                        fechaCreacion: formatDate(sugerenciaItem.fechaCreacion),
                    };
                });
                setSugerenciaslist(formattedSugerenciasData);
            };
    
            const fetchComentario = async () => {
                const comentarioData = await UserService.getComentarios();
                // Format the date for each fetched item
                const formattedComentarioData = comentarioData.map((comenatrioItem) => {
                    return {
                        ...comenatrioItem
                    };
                });
                setComentariolist(formattedComentarioData);
            };

            const fetchJuntas = async () => {
                const juntasData = await UserService.getJuntas();
                // Format the date for each fetched item
                const formattedJuntasData = juntasData.map((juntaItem) => {
                    return {
                        ...juntaItem,
                        fechaCreacion: formatDate(juntaItem.fechaCreacion),
                    };
                });
                setJuntaslist(formattedJuntasData);
            };

            const fetchVotos = async () => {
                const votosData = await UserService.getVotos();
                // Format the date for each fetched item
                const formattedVotosData = votosData.map((votoItem) => {
                    return {
                        ...votoItem
                    };
                });
                setVotosList(formattedVotosData);
            };

            const fetchUsuarios = async () => {
                const usuariosData = await UserService.getUsuarios();
                setUsuarioslist(usuariosData);
            }

            const fetchPeticiones = async () => {
                const peticionesData = await UserService.getPeticiones();
                setPeticioneslist(peticionesData);
            }

            fetchUsuarios();
            fetchNoticias();
            fetchSugerencias();
            fetchComentario();
            fetchJuntas();
            fetchVotos();
            fetchPeticiones();

            const intervalNoticias = setInterval(fetchNoticias, 10000);
            const intervalSuerencias = setInterval(fetchSugerencias, 10000);
            const intervalComentario = setInterval(fetchComentario, 10000);
            const intervalJuntas = setInterval(fetchJuntas, 10000);
            const intervalVotos = setInterval(fetchVotos, 10000);
            const intervalUsuarios = setInterval(fetchUsuarios, 10000);
            const intervalPeticiones = setInterval(fetchPeticiones, 10000);

            return () => {
                clearInterval(intervalNoticias);
                clearInterval(intervalSuerencias);
                clearInterval(intervalComentario);
                clearInterval(intervalJuntas);
                clearInterval(intervalVotos);
                clearInterval(intervalUsuarios);
                clearInterval(intervalPeticiones);
            };
        }
    }, [location]);



    return (
        <div className='contenedor-flexbox'>
                {user.current && user.current.token && <Sidebar userData = {user.current}/>}
                <Routes>
                    <Route path="/" element={<Navigate replace to="/noticias" />}></Route>
                    <Route path="/noticias" element={<VerNoticias noticiaslist={noticiaslist} userData = {user.current}/>} />
                    <Route path="/noticias/:noticiaId" element={<UnaNoticia noticiaslist={noticiaslist} />} />
                    <Route path="/noticias/crear" element={<CrearNoticias userData={user.current} />} />
                  
                    <Route path="/sugerencias" element={<VerSugerencias sugerenciaslist={sugerenciaslist} comentariolist={comentariolist} />} />
                    <Route path="/sugerencias/crear" element={<CrearSugerencias userData={user.current}/>} />
                    <Route path="/sugerencias/:sugerenciaId" element={<CrearComentario userData={user.current}/>} />
                    
                    <Route path="/juntas" element={<VerJuntas juntaslist={juntaslist} votoslist={votoslist} userData={user.current}  />} />
                    <Route path="/juntas/crear" element={<CrearJunta userData={user.current} />} />
                    <Route path="/juntas/:juntaId" element={<Votar votoslist={votoslist} userData={user.current}/>} />
                   
                    <Route path="/gestionusuarios" element={<GestionUsuario usuarioslist={usuarioslist}/>}/>
                    <Route path="/gestionusuarios/:usuarioId" element={<VerUnUsuario usuarioslist={usuarioslist}/>}/>

                    <Route path="/contacto" element={<VerContacto usuarioslist={usuarioslist}/>} />
                    <Route path="/login" element={<Login location={location}/>} />
                    <Route path="/peticionregistro" element={<PeticionRegistro />}/>
                    <Route path="/register" element={<Register/>} />
                    <Route path="/admisionregistro" element={<AdminisionUsuario peticioneslist={peticioneslist}/>}/>
                    <Route path="/*" element={<Navigate to="/noticias"/>} />
                </Routes>
        </div>
    );
}

export default App;