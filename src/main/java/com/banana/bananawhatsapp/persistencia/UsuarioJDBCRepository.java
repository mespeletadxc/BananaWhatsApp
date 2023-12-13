package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.Set;

@Setter
@Getter
public class UsuarioJDBCRepository implements IUsuarioRepository{

    private String urlConn;
    @Override
    public Usuario crear(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario values (NULL,?,?,?,?)";

        try (
                Connection conn = DriverManager.getConnection(urlConn);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            usuario.valido();
            stmt.setString(4, usuario.getNombre());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(2, usuario.getAlta().toString());
            stmt.setBoolean(1, usuario.isActivo());

            int rows = stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                usuario.setId(genKeys.getInt(1));
            } else {
                throw new SQLException("Usuario creado erroneamente!!!");
            }

            stmt.close();
            System.out.println("Nuevo usuario creado: " + usuario);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsuarioException("Error en el alta de usuario");

        }

        return usuario;




    }

    @Override
    public Usuario actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario u SET u.activo = ?, u.alta = ?, u.email = ?, u.nombre = ?  WHERE u.id = ?";
        try (
                Connection conn = DriverManager.getConnection(urlConn);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            usuario.valido();
            stmt.setString(4, usuario.getNombre());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(2, usuario.getAlta().toString());
            stmt.setBoolean(1, usuario.isActivo());
            stmt.setInt(5, usuario.getId());

            int rows = stmt.executeUpdate();
            stmt.close();
            System.out.println("Usuario actualizado: " + usuario);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsuarioException("Error en el update de usuario");

        }
        return usuario;
    }

    @Override
    public boolean borrar(Usuario usuario) throws SQLException {
        //DELETE FROM `usuario` WHERE `id`=3
        String sql = "DELETE FROM usuario WHERE id = ?";


        try (
                Connection conn = DriverManager.getConnection(urlConn);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setInt(1, usuario.getId());

            int rows = stmt.executeUpdate();

            stmt.close();
            System.out.println("Usuario eliminado: " + usuario);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsuarioException("Error en el delete de usuario");

        }
        return true;


    }

    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException {
        return null;
    }
    @Override
    public Usuario bajaMensajes(Usuario usuario) throws SQLException{
        if (usuario.isActivo()){
            String sql = "UPDATE usuario u SET u.activo = false WHERE ID = ?";
            try (
                    Connection conn = DriverManager.getConnection(urlConn);
                    PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ) {
                usuario.valido();

                stmt.setInt(1, usuario.getId());
                int rows = stmt.executeUpdate();
                stmt.close();
                System.out.println("Usuario dado de baja: " + usuario);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new UsuarioException("Error en l baja de usuario");

            }

        }
        else{
            System.out.println("Usuario ya esta dado de baja");

        }
        return usuario;

    }
}
