import React, { useState, useRef } from "react";
import { Link, useNavigate } from 'react-router-dom';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import AuthService from "../../services/auth.service";
import { Button } from "react-bootstrap";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        Por favor, rellene este campo.
      </div>
    );
  }
};

const Login = () => {
  let navigate = useNavigate();

  const form = useRef();
  const checkBtn = useRef();


  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const handleLogin = (e) => {
    e.preventDefault();
    setMessage("");
    setLoading(true);
    form.current.validateAll();
    if (checkBtn.current.context._errors.length === 0) {
      AuthService.login(username, password).then(
        () => {
          navigate("/noticias");
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setLoading(false);
          setMessage(resMessage);
        }
      );
    } else {
      setLoading(false);
    }
  };

  return (
    <div
      className="d-flex flex-column align-items-center justify-content-center"
      style={{
        background: "linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url('/fondo-back.jpg')",
        backgroundPosition: "center",
        backgroundSize: "cover",
        height: "100vh",
        width: "100vw",
        position: "relative"
      }}
    >
      <div className="wrapper" style={{ marginTop: '50px', textAlign: 'center', zIndex: 1 }}>
        <div className="title-image mb-4">
          <h2 style={{ color: 'white' }}>¡Bienvenido a TuComunidad!</h2>
        </div>
        <div className="card" style={{ zIndex: 1, backgroundColor: "black" }}>
          <div className="card-body">
            <div className="row align-items-center">
              <div className="col-md-6">
                <img
                  src="/logocasastres.jpeg"
                  alt="profile-img"
                  className="profile-img-card"
                  style={{ maxWidth: '50%', maxHeight: '50%', margin: 'auto 0', borderRadius: '50%' }}
                />
              </div>
              <div className="col-md-6">
                <Form onSubmit={handleLogin} ref={form}>
                  <div className="form-group">
                    <label htmlFor="username" style={{ color: 'white' }}>Usuario</label>
                    <Input
                      type="text"
                      className="form-control"
                      name="username"
                      value={username}
                      onChange={onChangeUsername}
                      validations={[required]}
                    />
                  </div>

                  <div className="form-group">
                    <label htmlFor="password" style={{ color: 'white' }}>Contraseña</label>
                    <Input
                      type="password"
                      className="form-control"
                      name="password"
                      value={password}
                      onChange={onChangePassword}
                      validations={[required]}
                    />
                  </div>

                  <div className="form-group">
                    <button className="btn btn-primary btn-block" disabled={loading} style={{ marginTop: '10px' }}>
                      {loading && <span className="spinner-border spinner-border-sm"></span>}
                      <span>Login</span>
                    </button>
                  </div>

                  {message && (
                    <div className="form-group">
                      <div className="alert alert-danger" role="alert">
                        {message}
                      </div>
                    </div>
                  )}
                  <CheckButton style={{ display: "none" }} ref={checkBtn} />
                </Form>
              </div>
            </div>
          </div>
          <Link to={"/peticionregistro"}>
            <Button variant="success" className="mt-2 mb-3 mx-5">
              ¿Aún no eres miembro? Haz una petición de registro
            </Button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Login;