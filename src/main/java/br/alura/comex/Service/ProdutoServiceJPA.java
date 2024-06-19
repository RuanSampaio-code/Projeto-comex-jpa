package br.alura.comex.Service;

import br.alura.comex.dao.jpa.JpaProdutoDao;
import br.alura.comex.interfaces.ProdInterfaceDao;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoServiceJPA implements ProdInterfaceDao {

    private JpaProdutoDao jpaProdutoDao;

    public ProdutoServiceJPA(EntityManager entityManager) {
        this.jpaProdutoDao = new JpaProdutoDao(entityManager);
    }

    @Override
    public void cadastra(Produto produto) {
        jpaProdutoDao.cadastra(produto);
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
    public void alterar(Long id, Produto produto) {
        jpaProdutoDao.alterar(id, produto);
    }

    @Override
    public void remover(Long id) {
        jpaProdutoDao.remover(id);
    }
}
