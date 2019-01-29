package dao;

import beans.Telefone;
import beans.TipoTelefone;
import connection.SingleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDao {

    private Connection connection;

    public TelefoneDao() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(Telefone telefone) {

        try {
            String sql = "insert into telefone(numero,tipo,usuario) values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, telefone.getNumero());
            statement.setLong(2, telefone.getTipo().ordinal());
            statement.setLong(3, telefone.getUsuario());
            statement.execute();
            connection.commit();

        } catch (SQLException e) {

            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
    }

    /*Listar telefones de um usuario*/
    public List<Telefone> listar(Long user) {

        List<Telefone> telefones = new ArrayList<>();

        try {
            String sql = "select * from telefone where usuario = "+user+" order by id";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(resultSet.getLong("id"));
                telefone.setNumero(resultSet.getString("numero"));
                telefone.setTipo(TipoTelefone.valor(resultSet.getLong("tipo")));
                telefone.setUsuario(resultSet.getLong("usuario"));
                telefones.add(telefone);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return telefones;
    }

    public void delete(Long id) {

        try {

            String sql = "delete from telefone where id= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

}
