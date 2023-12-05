package com.banana.bananawhatsapp.controladores;

import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.servicios.IServicioUsuarios;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;

@Controller
@Setter
@Getter
public class ControladorUsuarios {

    @Autowired
    private IServicioUsuarios servicioUsuarios;

    public Usuario alta(Usuario nuevo) throws SQLException{
        try {
            Usuario usuario = servicioUsuarios.crearUsuario(nuevo);
            System.out.println("Usuario creado: " + nuevo);
            return usuario;
        } catch (Exception e) {
            System.out.println("Ha habido un error: " + e.getMessage());
            throw e;
        }

    }

    public Usuario actualizar(Usuario usuario) throws SQLException{
        try {
            servicioUsuarios.actualizarUsuario(usuario);
            System.out.println("Usuario actualizado: " + usuario);
            return usuario;
        } catch (Exception e) {
            System.out.println("Ha habido un error: " + e.getMessage());
            throw e;
        }

    }

    public boolean baja(Usuario usuario) throws SQLException{
        try {
            boolean isOK = servicioUsuarios.borrarUsuario(usuario);
            System.out.println("Usuario borrado: " + usuario);
            return isOK;
        } catch (Exception e) {
            System.out.println("Ha habido un error: " + e.getMessage());
            throw e;
        }
    }


}
