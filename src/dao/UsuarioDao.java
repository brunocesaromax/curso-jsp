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

    public void salvar(BeanCursoJSP beanCursoJSP)  {

        try {
            String sql = "insert into usuario(login,senha) values (?,?)";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(sql);
            statement.setString(1, beanCursoJSP.getLogin());
            statement.setString(2, beanCursoJSP.getSenha());
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

    public List<BeanCursoJSP> listar(){

        List<BeanCursoJSP> usuarios = new ArrayList<>();

        try {
            String sql = "select * from usuario";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                BeanCursoJSP beanCursoJSP = new BeanCursoJSP();
                beanCursoJSP.setLogin(resultSet.getString("login"));
                beanCursoJSP.setSenha(resultSet.getString("senha"));
                usuarios.add(beanCursoJSP);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

}
