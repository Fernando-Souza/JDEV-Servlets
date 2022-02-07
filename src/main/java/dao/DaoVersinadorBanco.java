package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;

public class DaoVersinadorBanco implements Serializable {

    private static final long serialVersionUID = 1L;

    private Connection connection;

    public DaoVersinadorBanco() {

        connection = SingleConnection.getConnection();
    }

    public void gravarArquivoSQLRodado(String nome_file) {

        String sql = "INSERT INTO versionadorbanco(arquivo_sql) VALUES (?);";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, nome_file);
            ps.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public boolean isArquivoSqlRodado(String nomedoArquivo) {

        String sql = "SELECT count(1)>0 as rodado FROM versionadorbanco WHERE arquivo_sql=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, nomedoArquivo);

            ResultSet rs = ps.executeQuery();

            rs.next();

            return rs.getBoolean("rodado");

        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }

}
