package dao;

import beans.BeanCursoJSP;
import connection.SingleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection connection;

    public UsuarioDao() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(BeanCursoJSP beanCursoJSP) {

        try {
            String sql = "insert into usuario(login,senha,nome,telefone) values (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, beanCursoJSP.getLogin());
            statement.setString(2, beanCursoJSP.getSenha());
            statement.setString(3, beanCursoJSP.getNome());
            statement.setString(4, beanCursoJSP.getTelefone());
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

    public List<BeanCursoJSP> listar() {

        List<BeanCursoJSP> usuarios = new ArrayList<>();

        try {
            String sql = "select * from usuario order by id";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BeanCursoJSP beanCursoJSP = new BeanCursoJSP();
                beanCursoJSP.setId(resultSet.getLong("id"));
                beanCursoJSP.setLogin(resultSet.getString("login"));
                beanCursoJSP.setSenha(resultSet.getString("senha"));
                beanCursoJSP.setNome(resultSet.getString("nome"));
                beanCursoJSP.setTelefone(resultSet.getString("telefone"));
                usuarios.add(beanCursoJSP);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public void delete(Long id) {

        try {

            String sql = "delete from usuario where id= ?";
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

    public BeanCursoJSP buscar(Long id) {

        try {

            String sql = "select * from usuario where id = '" + id + "'";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                BeanCursoJSP beanCursoJSP = new BeanCursoJSP();
                beanCursoJSP.setId(resultSet.getLong("id"));
                beanCursoJSP.setLogin(resultSet.getString("login"));
                beanCursoJSP.setSenha(resultSet.getString("senha"));
                beanCursoJSP.setNome(resultSet.getString("nome"));
                beanCursoJSP.setTelefone(resultSet.getString("telefone"));

                return beanCursoJSP;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    public void atualizar(BeanCursoJSP usuario) {

        try {
            String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getLogin());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getNome());
            preparedStatement.setString(4, usuario.getTelefone());
            preparedStatement.setLong(5, usuario.getId());
            preparedStatement.executeUpdate();
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

    public BeanCursoJSP buscarByLogin(String login) {

        try {

            String sql = "select id from usuario where login = '" + login + "'";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                BeanCursoJSP beanCursoJSP = new BeanCursoJSP();
                beanCursoJSP.setId(resultSet.getLong("id"));
                return beanCursoJSP;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;

    }

}
