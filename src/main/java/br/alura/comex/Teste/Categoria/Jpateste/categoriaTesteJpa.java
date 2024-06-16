package br.alura.comex.Teste.Categoria.Jpateste;

import br.alura.comex.Dao.jpa.JpaCategoriaDao;
import br.alura.comex.models.Categoria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class categoriaTesteJpa {

    public static void main(String[] args) {

        EntityManagerFactory comex = Persistence.createEntityManagerFactory("comex");
        EntityManager entityManager = comex.createEntityManager();

        Categoria automotiva = new Categoria();
        automotiva.setNome("Automotiva");
        automotiva.setDescricao("Utensílios para seu automóvel!");

        JpaCategoriaDao dao = new JpaCategoriaDao(entityManager);
        dao.cadastra(automotiva);

        entityManager.close();
        comex.close();

    }
}
