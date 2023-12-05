package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Setter
public class ServicioUsuarios implements IServicioUsuarios{
    private IServicioUsuarios repoUsuarios;
    @Override
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException {
        repoUsuarios.crearUsuario(usuario);
        return usuario;
    }

    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException {
        repoUsuarios.borrarUsuario(usuario);
        return true;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {
        repoUsuarios.actualizarUsuario(usuario);
        return usuario;
    }

    @Override
    public Usuario obtenerPosiblesDesinatarios(Usuario usuario, int max) throws UsuarioException {
        return null;
    }
}