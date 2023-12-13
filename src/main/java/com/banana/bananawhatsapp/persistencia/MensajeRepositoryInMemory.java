package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.util.List;

@Setter
@Getter
public class MensajeRepositoryInMemory implements IMensajeRepository{

    private String urlConn;
    @Override
    public Mensaje crear(Mensaje mensaje) throws SQLException {
        return null;
    }

    @Override
    public List<Mensaje> obtener(Usuario usuario, Usuario otroUsuario) throws SQLException {
        return null;
    }

    @Override
    public boolean borrarTodos(Usuario usuario) throws SQLException {
        return false;
    }
}
