package br.alura.comex.Dao.jpa;

import br.alura.comex.models.Categoria;

import javax.persistence.EntityManager;

public class JpaCategoriaDao {

    private EntityManager manager;

    public JpaCategoriaDao(EntityManager manager) {
        this.manager = manager;
    }

    public void cadastra(Categoria categoria) {
        manager.getTransaction().begin();
        manager.persist(categoria);
        manager.getTransaction().commit();
    }
}
