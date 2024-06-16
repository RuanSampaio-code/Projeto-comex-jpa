package br.alura.comex.Dao;

import br.alura.comex.banco.ConnectionFactory;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    ConnectionFactory connectionFactory = new ConnectionFactory();
    public List<Produto> listaTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        Connection pegaConexao = connectionFactory.criaConexao();

        try {
            PreparedStatement ps = pegaConexao.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String descrisao = resultSet.getString("descricao");
                Double preco = resultSet.getDouble("preco");

                Produto produto = new Produto(id, nome,descrisao,preco);

                lista.add(produto);
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

    public void cadastra(Produto novoProduto) {
        //Query de cadastro
        String sql = """
                     insert into produtos
                        (nome, descricao,preco) 
                     values
                        (?,?,?)
                     """;

        try {
            //pega conexao
            Connection pegaConcexao = connectionFactory.criaConexao();

            //roda query no banco de dados
            PreparedStatement ps = pegaConcexao.prepareStatement(sql);

            //seta os campos
            ps.setString(1, novoProduto.getNome());
            ps.setString(2, novoProduto.getDescricao());
            ps.setDouble(3, novoProduto.getPreco());

            //executa
            ps.execute();

            //fecha conexao
            ps.close();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Produto pesquisaPorId(Long id) {
        String sql = "select * from produtos where id = ?";

        //busca da conexao
        //Connection pegaconexao = connectionFactory.criaConexao();

        try {

            //busca da conexao
            Connection pegaconexao = connectionFactory.criaConexao();
            //rodando a query
            PreparedStatement ps = pegaconexao.prepareStatement(sql);

            //setando paramentro
            ps.setLong(1, id);

            //resultado da query
            ResultSet resultSet = ps.executeQuery();

            Produto produto = null;

            if (resultSet.next()) {
                //cliente = montaCliente(resultSet);
                //Apartir do resultset vamos popular variaives
                Long idencontrado = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String descrisao = resultSet.getString("descricao");
                Double preco = resultSet.getDouble("preco");


                //instaciar cliente
                produto = new Produto(id, nome, descrisao, preco);
            }

            ps.close();
            resultSet.close();

            return produto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(Produto produtoParaExcluir) {
        String sql = "delete from produtos where id = ?";

        Connection pegaconexao = connectionFactory.criaConexao();
        try {
            PreparedStatement ps =  pegaconexao.prepareStatement(sql);
            ps.setLong(1, produtoParaExcluir.getId());

            ps.execute();
            ps.close();
        }catch (SQLException e){
            throw new RuntimeException("Error ao excluir cliente",  e);

        }
        System.out.println("Produto exclido com sucesso");

    }

    public void alteracao(Long id, Produto produto) {
        String sql = """
                     update produtos set 
                        nome = ?,
                        descrisao, ?,
                        preco = ?
                        
                     where id = ?
                     """;

        Connection pegaconexao = connectionFactory.criaConexao();


        try {
            PreparedStatement ps = pegaconexao.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getPreco());
            ps.setLong(4, id);


            ps.execute();
            ps.close();

        }catch (SQLException e){
            throw new RuntimeException("Erro ao atualizar produto.", e);
        }
    }

   // public void excluir(Produto produtoParaExcluir)

}
