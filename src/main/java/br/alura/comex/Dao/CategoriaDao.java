package br.alura.comex.Dao;

import br.alura.comex.banco.ConnectionFactory;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    private ConnectionFactory connectionFactory = new ConnectionFactory();
    public List<Categoria> listaTodos() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        Connection pegaConexao = connectionFactory.criaConexao();

        try {
            PreparedStatement ps = pegaConexao.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");

                Categoria categoria = new Categoria(id, nome);

                lista.add(categoria);
            }
            //executa
            ps.execute();

            //fecha conexao
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void cadastra(Categoria novaCategoria) {

        //Query de cadastro
        String sql = """
                     insert into categoria
                        (nome) 
                     values
                        (?)
                     """;

        try {
            //pega conexao
            Connection pegaConcexao = connectionFactory.criaConexao();

            //roda query no banco de dados
            PreparedStatement ps = pegaConcexao.prepareStatement(sql);

            //seta os campos
            ps.setString(1, novaCategoria.getNome());

            //executa
            ps.execute();

            //fecha conexao
            ps.close();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Categoria pesquisaPorId(Long id) {
        String sql = "select * from categoria where id = ?";

        //busca da conexao
        //Connection pegaconexao = connectionFactory.criaConexao();

        try {

            //busca da conexao
            Connection pegaconexao = connectionFactory.criaConexao();
            //rodando a query
            PreparedStatement ps = pegaconexao.prepareStatement(sql);

            //setando paramentro
            ps.setLong(1,id);

            //resultado da query
            ResultSet resultSet = ps.executeQuery();

            Categoria categoria = null;

            if (resultSet.next()) {
                //cliente = montaCliente(resultSet);
                //Apartir do resultset vamos popular variaives
                Long idencontrado = resultSet.getLong("id");
                String nome = resultSet.getString("nome");


                //instaciar cliente
                categoria = new Categoria(idencontrado, nome);
            }

            ps.close();
            resultSet.close();

            return categoria;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void excluir(Categoria categoriaParaExcluir) {
        String sql = "delete from categoria where id = ?";

        Connection pegaconexao = connectionFactory.criaConexao();
        try {
            PreparedStatement ps = pegaconexao.prepareStatement(sql);
            ps.setLong(1, categoriaParaExcluir.getId());

            ps.execute();
            ps.close();
        }catch (SQLException e){
            throw new RuntimeException("Error ao excluir cliente",  e);

        }


    }

    public void alteracao(Long id, Categoria clienteParaAlterar) {

        String sql = """
                     update categoria set 
                        nome = ?
                        
                     where id = ?
                     """;

        Connection pegaconexao = connectionFactory.criaConexao();


        try {
            PreparedStatement ps = pegaconexao.prepareStatement(sql);
            ps.setString(1, clienteParaAlterar.getNome());

            ps.setLong(2, id);


            ps.execute();
            ps.close();

        }catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }
}
