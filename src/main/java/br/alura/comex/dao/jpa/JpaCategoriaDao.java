package br.alura.comex.dao.jpa;

import br.alura.comex.interfaces.CatInterfaceDao;
import br.alura.comex.models.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaCategoriaDao implements CatInterfaceDao {

    private EntityManager manager;

    public JpaCategoriaDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void cadastra(Categoria categoria) {
        // Inicia uma transação no EntityManager
        manager.getTransaction().begin();

        // Persiste a entidade Categoria no banco de dados
        manager.persist(categoria);

        // Confirma a transação, aplicando todas as operações de persistência
        manager.getTransaction().commit();

        System.out.println("Cadastro realizado com sucesso");

    }

    @Override
    public List<Categoria> listarTodas(){
        // Declara uma variável para armazenar o resultado da consulta


        // Define a consulta JPQL para selecionar todas as categorias
        String jpql = "SELECT c FROM Categoria c";

        // Cria uma consulta do tipo TypedQuery usando a string JPQL e a classe Categoria
        List<Categoria> categorias = manager.createQuery(jpql, Categoria.class).getResultList();


        // Retorna a lista de todas as categorias
        return categorias;

    }

    @Override
    public Categoria buscarID(Long id){
        var categoriaEncontrada = manager.find(Categoria.class, id);

//        if (categoriaEncontrada==null){
//            System.out.println("Categoria nao encontradas");
//
//        }

        //System.out.println(categoriaEncontrada);
        return categoriaEncontrada;
    }

    @Override
    public void alterar(Long id, Categoria categoriaAlterar) {
        try {
            manager.getTransaction().begin();

            // Buscar a categoria pelo ID antes de tentar mesclar
            Categoria categoriaExistente = buscarID(id);

            if (categoriaExistente == null) {
                System.out.println("Categoria não encontrada com o ID informado.");
            } else {
                // Mesclar (merge) a entidade atualizada com a existente no contexto persistente
                // Isso vai atualizar a categoria no banco de dados
                categoriaExistente.setNome(categoriaAlterar.getNome());
                categoriaExistente.setDescricao(categoriaAlterar.getDescricao());

                manager.merge(categoriaExistente);
                manager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar atualizar a categoria: " + e.getMessage());
            manager.getTransaction().rollback(); // Rollback da transação em caso de erro
        }
    }

    @Override
    // No serviço CategoriaServiceJPA
    public void remover(Long id) {
        try {
            manager.getTransaction().begin();

            Categoria categoriaDelete = buscarID(id);

            if (categoriaDelete == null) {
                System.out.println("Categoria não encontrada com o ID informado.");
            } else {
                manager.remove(categoriaDelete);
                manager.getTransaction().commit();
                System.out.println("Exclusão realizada com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar excluir a categoria: " + e.getMessage());
            manager.getTransaction().rollback(); // Rollback da transação em caso de erro
        }
    }

}
