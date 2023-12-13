package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import lombok.Getter;
import lombok.Setter;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class MensajeJDBCRepository implements IMensajeRepository {

    private String urlConn;
    @Override
    public Mensaje crear(Mensaje mensaje) throws SQLException {

        String sql = "INSERT INTO mensaje values (NULL,?,?,?,?)";

        try (
                Connection conn = DriverManager.getConnection(urlConn);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            mensaje.valido();

            stmt.setInt(4,mensaje.getDestinatario().getId());
            stmt.setInt(3,mensaje.getRemitente().getId());
            stmt.setString(2, mensaje.getFecha().toString());
            stmt.setString(1, mensaje.getCuerpo());

            int rows = stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                mensaje.setId(genKeys.getInt(1));
            } else {
                throw new SQLException("Mensaje creado erroneamente!!!");
            }

            stmt.close();
            System.out.println("Nuevo mensaje creado: " + mensaje);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new MensajeException("Error en el alta de mensaje");

        }

        return mensaje;
    }

    @Override
    public List<Mensaje> obtener(Usuario usuario, Usuario otroUsuario) throws SQLException {
        List<Mensaje> mensajes = new ArrayList<>();

       // String sql = "SELECT * FROM mensaje m WHERE (m.remitente = ? AND m.destinatario = ?)";

         String sql = "SELECT m.*, u_remitente.*, u_destinatario.* " +
                 "FROM mensaje m " +
                 "JOIN usuario u_remitente ON m.from_user = u_remitente.id " +
                 "JOIN usuario u_destinatario ON m.to_user = u_destinatario.id " +
                 "WHERE (m.from_user = ? AND m.to_user = ?) OR (m.from_user = ? AND m.to_user = ?)";


        try (
                Connection conn = DriverManager.getConnection(urlConn);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            //stmt.setInt(1, usuario.getId());
            //stmt.setInt(2, otroUsuario.getId());
            stmt.setInt(1, usuario.getId());
            stmt.setInt(2, otroUsuario.getId());
            stmt.setInt(3, otroUsuario.getId());
            stmt.setInt(4, usuario.getId());
            ResultSet rs = stmt.executeQuery();

            /*while (rs.next()) {
                mensajes.add(
                        new Mensaje(
                                rs.getInt("id"),
                                rs.getInt("remitente"),
                                rs.getInt("destinatario"),
                                rs.getString("cuerpo"),
                                rs.getDate("fecha").toLocalDate()

                        )
                );*/
            while (rs.next()) {
                Usuario remitente = new Usuario(
                        rs.getInt("u_remitente.id"),
                        rs.getString("u_remitente.nombre"),
                        rs.getString("u_remitente.email"),
                        rs.getDate("u_remitente.alta").toLocalDate(),
                        rs.getBoolean("u_remitente.activo")

                );

                Usuario destinatario = new Usuario(
                        rs.getInt("u_destinatario.id"),
                        rs.getString("u_destinatario.nombre"),
                        rs.getString("u_destinatario.email"),
                        rs.getDate("u_destinatario.alta").toLocalDate(),
                        rs.getBoolean("u_destinatario.activo")

                );

                mensajes.add(
                        new Mensaje(
                                rs.getInt("m.id"),
                                remitente,
                                destinatario,
                                rs.getString("m.cuerpo"),
                                rs.getDate("m.fecha").toLocalDate()
                        )
                );
            }

        } catch (MensajeException e) {
            e.printStackTrace();
            throw new MensajeException("Error al obtener mensajes");
        }

        return mensajes;

    }

    @Override
    public boolean borrarTodos(Usuario usuario) throws SQLException {
        return false;
    }
}
