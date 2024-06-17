package br.alura.comex.Service;

import br.alura.comex.dao.jpa.JpaProdutoDao;
import br.alura.comex.dao.jpa.ProdInterfaceDao;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Produto;

import java.util.List;

public class ProdutoServiceJPA implements ProdInterfaceDao {

    private JpaProdutoDao jpaProdutoDao;
    @Override
    public void cadastra(Produto produto) {
        this.jpaProdutoDao = jpaProdutoDao;
    }

    @Override
    public List<Produto> listarTodos() {
        return this.jpaProdutoDao.listarTodos();
    }

    @Override
    public Produto buscarID(Long id) {
        Produto busca = jpaProdutoDao.buscarID(id);
        if(busca==null){
            System.out.println("Categoria não encontrada com o ID informado.");

        }
        return busca;
    }

    @Override
    public void alterar(Long id, Categoria produtiAlterar) {
        jpaProdutoDao.alterar(id,produtiAlterar);
    }

    @Override
    public void remover(Long id) {
        jpaProdutoDao.remover(id);
    }
}
