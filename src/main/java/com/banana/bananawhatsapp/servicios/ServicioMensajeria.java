package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IMensajeRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Setter
public class ServicioMensajeria implements IServicioMensajeria{

    @Autowired
    private IServicioMensajeria srvMensajeria;

    @Autowired
    private IMensajeRepository repoMensajeria;
    @Override
    public Mensaje enviarMensaje(Usuario remitente, Usuario destinatario, String texto) throws UsuarioException, MensajeException, SQLException {

        Mensaje mensaje = new Mensaje(null, remitente, destinatario, texto, LocalDate.now());
        repoMensajeria.crear(mensaje);
        return mensaje;
    }

    @Override
    public List<Mensaje> mostrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException, SQLException {
        return repoMensajeria.obtener(remitente,destinatario);
    }

    @Override
    public boolean borrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException, SQLException {
        return repoMensajeria.borrarTodos(remitente,destinatario);
    }
}
