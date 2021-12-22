package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Telefone;
import beans.UsuarioBean;
import connection.SingleConnection;

public class TelefoneDao {

    private Connection conn;

    public TelefoneDao() {

        this.conn = SingleConnection.getConnection();
    }

    public void salvarTelefone(Telefone telefone) {

        String sql = "INSERT INTO telefone (numero,usuario_pai_id, usuario_cad_id) VALUES (?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, telefone.getNumero());
            ps.setLong(2, telefone.getUsuario_pai_id().getId());
            ps.setLong(3, telefone.getUsuario_cad_id().getId());

            ps.execute();
            conn.commit();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void deleteTelefone(Long id) {

        String sql = "DELETE FROM telefone WHERE id=?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public List<Telefone> listaTelefone(Long useId) {

        List<Telefone> telefones = new ArrayList<>();
        UsuarioDao usuario = new UsuarioDao();

        String sql = "SELECT * FROM telefone WHERE usuario_pai_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, useId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String numero = rs.getString("numero");
                Long usuario_pai_id = rs.getLong("usuario_pai_id");
                Long usuario_cad_id = rs.getLong("usuario_cad_id");

                UsuarioBean usuariopai = usuario.consultarById(usuario_pai_id.toString(), usuario_cad_id);
                UsuarioBean usuariocad = usuario.consultarById(usuario_cad_id.toString());

                telefones.add(new Telefone(id, numero, usuariopai, usuariocad));

            }

            conn.commit();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return telefones;

    }

    public List<Telefone> listaTelefone(String idUser) {

        List<Telefone> telefones = new ArrayList<>();
        UsuarioDao usuario = new UsuarioDao();

        String sql = "SELECT * FROM telefone WHERE id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, Long.parseLong(idUser));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String numero = rs.getString("numero");
                Long usuario_pai_id = rs.getLong("usuario_pai_id");
                Long usuario_cad_id = rs.getLong("usuario_cad_id");

                UsuarioBean usuariopai = usuario.consultarById(usuario_pai_id.toString(), usuario_cad_id);
                UsuarioBean usuariocad = usuario.consultarById(usuario_cad_id.toString());

                telefones.add(new Telefone(id, numero, usuariopai, usuariocad));

            }

            conn.commit();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return telefones;

    }

    public boolean existeFone(String fone, Long idUser) {

        String sql = "select count(1)>0 as existe from telefone where usuario_pai_id=? and numero=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, idUser);
            ps.setString(2, fone);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getBoolean("existe");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

}
