package dao;

import beans.Produto;
import connection.SingleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    private Connection connection;

    public ProdutoDao() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(Produto produto) {

        try {
            String sql = "insert into produto(nome,quantidade,valor) values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, produto.getNome());
            statement.setLong(2, produto.getQuantidade());
            statement.setDouble(3, produto.getValor());
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

    public List<Produto> listar() {

        List<Produto> produtos = new ArrayList<>();

        try {
            String sql = "select * from produto order by id";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Produto Produto = new Produto();
                Produto.setId(resultSet.getLong("id"));
                Produto.setNome(resultSet.getString("nome"));
                Produto.setQuantidade(resultSet.getLong("quantidade"));
                Produto.setValor(resultSet.getDouble("valor"));
                produtos.add(Produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public void delete(Long id) {

        try {

            String sql = "delete from produto where id= ?";
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

    public Produto buscar(Long id) {

        try {

            String sql = "select * from produto where id = '" + id + "'";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Produto Produto = new Produto();
                Produto.setId(resultSet.getLong("id"));
                Produto.setNome(resultSet.getString("nome"));
                Produto.setQuantidade(resultSet.getLong("quantidade"));
                Produto.setValor(resultSet.getDouble("valor"));

                return Produto;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    public void atualizar(Produto produto) {

        try {
            String sql = "update produto set nome = ?, quantidade= ?, valor = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setLong(2, produto.getQuantidade());
            preparedStatement.setDouble(3, produto.getValor());
            preparedStatement.setLong(4,produto.getId());
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

    public Produto buscarByNome(String nome) {

        try {

            String sql = "select id from produto where nome = '" + nome + "'";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Produto Produto = new Produto();
                Produto.setId(resultSet.getLong("id"));
                return Produto;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;

    }

}
