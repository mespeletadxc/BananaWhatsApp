package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

@Setter
public class ServicioUsuarios implements IServicioUsuarios{
    @Autowired
    private IServicioUsuarios srvUsuarios;

    @Autowired
    private IUsuarioRepository repoUsuarios;
    @Override
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException, SQLException {
        repoUsuarios.crear(usuario);
        return usuario;
    }

    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException, SQLException {
        repoUsuarios.borrar(usuario);
        return true;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException, SQLException {
        repoUsuarios.actualizar(usuario);
        return usuario;
    }

    @Override
    public Usuario obtenerPosiblesDesinatarios(Usuario usuario, int max) throws UsuarioException {
        return null;
    }
}
