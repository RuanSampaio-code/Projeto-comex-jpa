package br.alura.comex.Teste.Cliente;

import br.alura.comex.Service.CategoriaServiceJPA;
import br.alura.comex.Service.ProdutoServiceJPA;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class ProdutoTesteJpa {

    private static Scanner teclado = new Scanner(System.in);
    private static ProdutoServiceJPA produtoServiceJPA;
    public static void main(String[] args) {

        //cria uma instância de EntityManagerFactory. , utilizando o nome da unidade de persistência fornecida
        EntityManagerFactory comex = Persistence.createEntityManagerFactory("comex");

        // Usa a EntityManagerFactory para criar um EntityManager
        // O EntityManager é usado para realizar operações de persistência no banco de dados
        EntityManager entityManager = comex.createEntityManager();

        // Cria uma instância de JpaCategoriaDao passando o EntityManager como argumento
        //JpaCategoriaDao dao = new JpaCategoriaDao(entityManager);
        produtoServiceJPA = new ProdutoServiceJPA(entityManager);
        System.out.println("BEM VINDOS AO PROJETO COMEX - CADASTRO DE Produtos");

        var opc = exibirMenu();

        while ( opc != 6){
            switch (opc) {
                case 1:
                    listarTodosProdutos();
                    break;
                case 2:
                    criarProduto();
                    break;
                case 3:
                    deletarProduto();
                    break;
                case 4:
                    //atualizarCategoria();
                    break;
                case 5:
                    //buscarCategoria();
                    break;
            }

            opc = exibirMenu();
        }

        entityManager.close();
        comex.close();

    }
    private static int exibirMenu(){

        System.out.println("""
                Escolha uma opcao :
                1 - Listar todos as Categorias
                2 - Criar uma Categorias
                3 - Deletar uma Categoria
                4 - Atualizar uma Categoria
                5 - Listar uma Categoria
                6 - Finalizar operacoes
       
                """);

        return teclado.nextInt();

    }
    private static void listarTodosProdutos() {
        List<Produto> listaDeProdutos = produtoServiceJPA.listarTodos();

        listaDeProdutos.stream()
                .forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

    private static void criarProduto(){
        System.out.println("CRIANDO NOVO PRODUTO");
        // Solicita e armazena os dados do usuário
        teclado.nextLine();
        //System.out.println("Digite o Nome: ");
        System.out.println("Digite o Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Digite A descrisã1o: ");
        String descrisao = teclado.nextLine();

        System.out.println("Digite o valor: ");
        Double valor = teclado.nextDouble();

        Produto novoProduto = new Produto(nome, descrisao,valor);

        produtoServiceJPA.cadastra(novoProduto);
    }

    private static void deletarProduto(){
        System.out.print("Digite o ID do produto que você deseja alterar: ");
        Long id = teclado.nextLong();
        teclado.nextLine(); // Consumir a quebra de linha deixada pelo nextLong()

        // Buscar a categoria pelo ID
        Produto produto = produtoServiceJPA.buscarID(id);

        if (produto == null) {
            System.out.println("produto não encontrada com o ID informado.");
        } else {
            // Realizar a alteração
            System.out.print("Digite o novo nome da categoria: ");
            String nome = teclado.nextLine().trim(); // Use trim() para remover espaços em branco extras

            System.out.print("Digite a nova descrição da categoria: ");
            String descricao = teclado.nextLine().trim(); // Use trim() para remover espaços em branco extras

            System.out.print("Digite a nova descrição da categoria: ");
            Double valor = teclado.nextDouble();

            // Atualizar os dados da categoria
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setPreco(valor);

            // Chamar o método de serviço para atualizar no banco de dados
            produtoServiceJPA.alterar(id, produto);

            System.out.println("Categoria atualizada com sucesso.");
        }
    }
}
