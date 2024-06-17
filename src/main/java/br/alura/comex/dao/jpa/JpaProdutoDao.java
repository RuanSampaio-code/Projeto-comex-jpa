package br.alura.comex.dao.jpa;

import br.alura.comex.interfaces.ProdInterfaceDao;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaProdutoDao implements ProdInterfaceDao {
    private EntityManager manager;

    public JpaProdutoDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void cadastra(Produto produto) {
        // Inicia uma transação no EntityManager
        manager.getTransaction().begin();

        // Persiste a entidade Categoria no banco de dados
        manager.persist(produto);

        // Confirma a transação, aplicando todas as operações de persistência
        manager.getTransaction().commit();

        System.out.println("Cadastro realizado com sucesso");

    }

    @Override
    public List<Produto> listarTodos(){
        // Declara uma variável para armazenar o resultado da consulta


        // Define a consulta JPQL para selecionar todas as categorias
        String jpql = "SELECT p FROM Produto p";

        // Cria uma consulta do tipo TypedQuery usando a string JPQL e a classe Categoria
        List<Produto> produtos = manager.createQuery(jpql, Produto.class).getResultList();


        // Retorna a lista de todas as categorias
        return produtos;

    }

    @Override
    public Produto buscarID(Long id){
        var produtoaEncontrado = manager.find(Produto.class, id);

//        if (categoriaEncontrada==null){
//            System.out.println("Categoria nao encontradas");
//
//        }

        //System.out.println(categoriaEncontrada);
        return produtoaEncontrado;
    }

    @Override
    public void alterar(Long id, Categoria produtoAlterar) {
        try {
            manager.getTransaction().begin();

            // Buscar a categoria pelo ID antes de tentar mesclar
            Produto produtoExistente = buscarID(id);

            if (produtoExistente == null) {
                System.out.println("Categoria não encontrada com o ID informado.");
            } else {
                // Mesclar (merge) a entidade atualizada com a existente no contexto persistente
                // Isso vai atualizar a categoria no banco de dados
                produtoExistente.setNome(produtoAlterar.getNome());
                produtoExistente.setDescricao(produtoAlterar.getDescricao());

                manager.merge(produtoExistente);
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

            Produto produtoDelete = buscarID(id);

            if (produtoDelete == null) {
                System.out.println("Categoria não encontrada com o ID informado.");
            } else {
                manager.remove(produtoDelete);
                manager.getTransaction().commit();
                System.out.println("Exclusão realizada com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar excluir a categoria: " + e.getMessage());
            manager.getTransaction().rollback(); // Rollback da transação em caso de erro
        }
    }
}
