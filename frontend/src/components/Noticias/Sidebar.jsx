import React from 'react';
import {
    CDBSidebar,
    CDBSidebarContent,
    CDBSidebarFooter,
    CDBSidebarHeader,
    CDBSidebarMenu,
    CDBSidebarMenuItem,
} from 'cdbreact';
import { NavLink } from 'react-router-dom';

const Sidebar = (props) => {
        const userData = props.userData

    let compruebaRole = false;
    if (userData?.roles.includes('ROLE_ADMIN') || userData?.roles.includes('ROLE_PRESIDENTE')) {
        compruebaRole = true;
    }

    const cerrarSesion = () => {

        localStorage.setItem('user', null);

      };

    return (
        <div style={{ overflowY: 'scroll initial', height: '100vh' }}>
            <CDBSidebar textColor="#fff" backgroundColor="#000000">
                <CDBSidebarHeader prefix={<i className="fa fa-bars fa-large"></i>}>
                    <a href="/" className="text-decoration-none" style={{ color: 'inherit' }}>
                        tuComunidad
                    </a>
                </CDBSidebarHeader>

                <CDBSidebarContent className="sidebar-content">
                    <CDBSidebarMenu>
                        <NavLink to="/noticias" activeclassname="activeClicked">
                            <CDBSidebarMenuItem icon="columns">Noticias</CDBSidebarMenuItem>
                        </NavLink>
                        <NavLink to="/sugerencias" activeclassname="activeClicked">
                            <CDBSidebarMenuItem icon="table">Sugerencias</CDBSidebarMenuItem>
                        </NavLink>
                        <NavLink to="/juntas" activeclassname="activeClicked">
                            <CDBSidebarMenuItem icon="poll-h">Juntas</CDBSidebarMenuItem>
                        </NavLink>
                        <NavLink to="/contacto" activeclassname="activeClicked">
                            <CDBSidebarMenuItem icon="exclamation-circle">Contacto</CDBSidebarMenuItem>
                        </NavLink>
                        { compruebaRole ? 
                        <div>
                        <hr/>
                        <NavLink to="/gestionusuarios" activeclassname="activeClicked">
                            <CDBSidebarMenuItem icon="users-cog">Gestión de usuarios</CDBSidebarMenuItem>
                        </NavLink>
                        <NavLink to="/admisionregistro">
                            <CDBSidebarMenuItem icon="check">Admisión de registros</CDBSidebarMenuItem>
                        </NavLink>
                        <hr/>
                        </div>
                        : <div><hr/></div>}
                        <NavLink>
                            <CDBSidebarMenuItem icon="door-closed" onClick={cerrarSesion}>Cerrar sesión</CDBSidebarMenuItem>
                        </NavLink>
                    </CDBSidebarMenu>
                </CDBSidebarContent>

                <CDBSidebarFooter style={{ display: 'flex', justifyContent: 'center' }}>                    
                        <img
                            src="/logocasastres.jpeg"
                            alt="Descripción de la imagen"
                            style={{ maxWidth: '70%', maxHeight: '70%', margin: 'auto 0' }}
                        />
                </CDBSidebarFooter>

            </CDBSidebar>
        </div>
    );
};

export default Sidebar;