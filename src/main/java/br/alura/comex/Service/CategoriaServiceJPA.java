package br.alura.comex.Service;

import br.alura.comex.interfaces.CatInterfaceDao;
import br.alura.comex.dao.jpa.JpaCategoriaDao;
import br.alura.comex.models.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaServiceJPA implements CatInterfaceDao {

    private JpaCategoriaDao jpaCategoriaDao;

    public CategoriaServiceJPA(JpaCategoriaDao jpaCategoriaDao) {
        this.jpaCategoriaDao = jpaCategoriaDao;
    }

    public CategoriaServiceJPA(EntityManager entityManager) {
        this.jpaCategoriaDao = new JpaCategoriaDao(entityManager);
    }


    @Override
    public void cadastra(Categoria categoria) {
        jpaCategoriaDao.cadastra(categoria);

    }

    @Override
    public List<Categoria> listarTodas() {

        return this.jpaCategoriaDao.listarTodas();
    }

    @Override
    public Categoria buscarID(Long id) {
        Categoria busca = jpaCategoriaDao.buscarID(id);
        if(busca==null){
            System.out.println("Categoria não encontrada com o ID informado.");

        }
        return busca;
    }

    @Override
    public void alterar(Long id, Categoria categoriaAlterar) {

        jpaCategoriaDao.alterar(id,categoriaAlterar);

    }

    @Override
    public void remover(Long id) {
        jpaCategoriaDao.remover(id);
    }
}
