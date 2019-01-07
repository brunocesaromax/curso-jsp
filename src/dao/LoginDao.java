package dao;

import connection.SingleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

    private Connection connection;

    public LoginDao(){
        connection = SingleConnection.getConnection();
    }

    public boolean validarLogin(String login, String senha) throws Exception{

        String sql = "select * from usuario where login = '"+login+"' and senha = '"+senha+"' ";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            return true; // possui usuário
        }else{
            return false; // não possui usuário
        }


    }

}
