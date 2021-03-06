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
            String sql = "insert into usuario(login,senha,nome,telefone,cep,rua,bairro,cidade,estado) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, beanCursoJSP.getLogin());
            statement.setString(2, beanCursoJSP.getSenha());
            statement.setString(3, beanCursoJSP.getNome());
            statement.setString(4, beanCursoJSP.getTelefone());
            statement.setString(5, beanCursoJSP.getCep());
            statement.setString(6, beanCursoJSP.getRua());
            statement.setString(7, beanCursoJSP.getBairro());
            statement.setString(8, beanCursoJSP.getCidade());
            statement.setString(9, beanCursoJSP.getEstado());

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
                beanCursoJSP.setTelefone(resultSet.getString("Telefone"));
                beanCursoJSP.setCep(resultSet.getString("cep"));
                beanCursoJSP.setRua(resultSet.getString("rua"));
                beanCursoJSP.setBairro(resultSet.getString("bairro"));
                beanCursoJSP.setCidade(resultSet.getString("cidade"));
                beanCursoJSP.setEstado(resultSet.getString("estado"));
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
                beanCursoJSP.setTelefone(resultSet.getString("Telefone"));
                beanCursoJSP.setCep(resultSet.getString("cep"));
                beanCursoJSP.setRua(resultSet.getString("rua"));
                beanCursoJSP.setBairro(resultSet.getString("bairro"));
                beanCursoJSP.setCidade(resultSet.getString("cidade"));
                beanCursoJSP.setEstado(resultSet.getString("estado"));

                return beanCursoJSP;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    public void atualizar(BeanCursoJSP usuario) {

        try {
            String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ?, cep = ?,rua = ?, bairro = ?, cidade = ?, estado = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getLogin());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getNome());
            preparedStatement.setString(4, usuario.getTelefone());
            preparedStatement.setString(5, usuario.getCep());
            preparedStatement.setString(6, usuario.getRua());
            preparedStatement.setString(7, usuario.getBairro());
            preparedStatement.setString(8, usuario.getCidade());
            preparedStatement.setString(9, usuario.getEstado());
            preparedStatement.setLong(10, usuario.getId());
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

    public BeanCursoJSP buscarBySenha(String senha) {

        try {

            String sql = "select id from usuario where senha = '" + senha + "'";
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
