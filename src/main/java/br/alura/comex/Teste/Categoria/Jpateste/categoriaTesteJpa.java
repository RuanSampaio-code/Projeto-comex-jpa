package br.alura.comex.Teste.Categoria.Jpateste;


import br.alura.comex.Service.CategoriaServiceJPA;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class categoriaTesteJpa {
    private static Scanner teclado = new Scanner(System.in);
    private static CategoriaServiceJPA categoriaServiceJPA;

    public static void main(String[] args) {

        //cria uma instância de EntityManagerFactory. , utilizando o nome da unidade de persistência fornecida
        EntityManagerFactory comex = Persistence.createEntityManagerFactory("comex");

        // Usa a EntityManagerFactory para criar um EntityManager
        // O EntityManager é usado para realizar operações de persistência no banco de dados
        EntityManager entityManager = comex.createEntityManager();


        // Cria uma instância de JpaCategoriaDao passando o EntityManager como argumento
        //JpaCategoriaDao dao = new JpaCategoriaDao(entityManager);
        categoriaServiceJPA = new CategoriaServiceJPA(entityManager);
        System.out.println("BEM VINDOS AO PROJETO COMEX - CADASTRO DE CATEGORIAS");
        var opc = exibirMenu();

        while ( opc != 6){
            switch (opc) {
                case 1:
                    listarTodasCategorias();
                    break;
                case 2:
                    criarCategoria();
                    break;
                case 3:
                    deletarCategoria();
                    break;
                case 4:
                    atualizarCategoria();
                    break;
                case 5:
                    buscarCategoria();
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
    private static void listarTodasCategorias() {
        List<Categoria> listaDeCategorias = categoriaServiceJPA.listarTodas();

        listaDeCategorias.stream()
                .forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

    private static void criarCategoria(){
        System.out.println("CRIANDO NOVA CATEGORIA");
        // Solicita e armazena os dados do usuário
        teclado.nextLine();
        //System.out.println("Digite o Nome: ");
        System.out.println("Digite o Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Digite A descrisão: ");
        String descrisao = teclado.nextLine();

        Categoria novaCategoria = new Categoria(nome, descrisao);

        categoriaServiceJPA.cadastra(novaCategoria);
    }

    private static void deletarCategoria(){
        System.out.println("Exclusão de cadastro");
        System.out.println("Digite o id da categoria que deseja excluir: ");
        Long id = teclado.nextLong();

        //Categoria categoriaencontrada = categoriaServiceJPA.buscarID(id);

        categoriaServiceJPA.remover(id);
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }

    public static void atualizarCategoria() {
        // Busca pelo ID
        System.out.print("Digite o ID da categoria que você deseja alterar: ");
        Long id = teclado.nextLong();
        teclado.nextLine(); // Consumir a quebra de linha deixada pelo nextLong()

        // Buscar a categoria pelo ID
        Categoria categoriaAlterar = categoriaServiceJPA.buscarID(id);

        if (categoriaAlterar == null) {
            System.out.println("Categoria não encontrada com o ID informado.");
        } else {
            // Realizar a alteração
            System.out.print("Digite o novo nome da categoria: ");
            String nome = teclado.nextLine().trim(); // Use trim() para remover espaços em branco extras

            System.out.print("Digite a nova descrição da categoria: ");
            String descricao = teclado.nextLine().trim(); // Use trim() para remover espaços em branco extras

            // Atualizar os dados da categoria
            categoriaAlterar.setNome(nome);
            categoriaAlterar.setDescricao(descricao);

            // Chamar o método de serviço para atualizar no banco de dados
            categoriaServiceJPA.alterar(id, categoriaAlterar);

            System.out.println("Categoria atualizada com sucesso.");
        }
    }

    public static void buscarCategoria(){
        System.out.print("Digite o ID da categoria que você deseja buscar: ");
        Long id = teclado.nextLong();

        Categoria categoriabuscada = categoriaServiceJPA.buscarID(id);
        System.out.println(categoriabuscada);
    }


}
