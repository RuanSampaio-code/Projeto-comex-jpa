package br.alura.comex.Service;

import br.alura.comex.Dao.CategoriaDao;
import br.alura.comex.Dao.ProdutoDao;
import br.alura.comex.exception.EntidadeNaoEncontradaException;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    private ProdutoDao produtoDao;

    //construtor

    public ProdutoService() {
        this.produtoDao = new ProdutoDao();
    }

    public List<Produto> listarCadastroDeProdutos(){
        List<Produto> lista = new ArrayList<>();

        lista = this.produtoDao.listaTodos();
        //System.out.println(lista);
        return lista;
    }


    public void efetuaCadastroDeProdutos(Produto novoProduto){
        this.produtoDao.cadastra(novoProduto);
    }


    public void excluiprodutoPeloId(Long id) {
        Produto produtoParaExcluir = produtoDao.pesquisaPorId(id);
        if (produtoParaExcluir == null) {
            throw new EntidadeNaoEncontradaException("Cliente não está cadastrado: " + id);
        }

        this.produtoDao.excluir(produtoParaExcluir);

    }

    public Produto buscaID(Long id) {
        Produto produtoParaAlterar = produtoDao.pesquisaPorId(id);

        if (produtoParaAlterar == null){
            throw new EntidadeNaoEncontradaException("Cliente não está cadastrado: " );
        }

        return produtoParaAlterar;
    }

    public void alteracaoDeProduto(Long id, Produto produto) throws EntidadeNaoEncontradaException{

        Produto produtoParaalterar = produtoDao.pesquisaPorId(id);

        if (produtoParaalterar == null){
            throw new EntidadeNaoEncontradaException("Categria não está cadastrado: " );
        }

        produtoDao.alteracao(id, produto);

    }
}
