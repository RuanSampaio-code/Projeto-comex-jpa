package br.alura.comex.Teste.Categoria;

import br.alura.comex.Service.CategoriaService;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Cliente;

import java.util.Scanner;

public class TesteCategoria {

    private final static CategoriaService categoriaService = new CategoriaService();
    private static  Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
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

    private static void atualizarCategoria() {
        System.out.println("Digite o id do cliente que voce procura:");
        Long id = teclado.nextLong();

        categoriaService.buscaID(id);
        teclado.nextLine();

        System.out.println("Digite o Nome: ");
        String nome = teclado.nextLine();

        Categoria categoriaAlterada = new Categoria(null ,  nome);

        //clienteService.efetuaCadastroDeCliente( new Cliente( null,cpf,  nome,  email,  telefone,  logradouro,  bairro,  cidade,  estado,  cep));
        categoriaService.alteracaoDeCategoria(id,categoriaAlterada);
        System.out.printf("Cliente altrado com sucesso");



    }

    private static void deletarCategoria() {
        System.out.printf("Exclusão de cadastro");
        System.out.println("Digite o id da categoria que deseja excluir: ");
        Long id = teclado.nextLong();

         categoriaService.excluiClientePeloId(id);


        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void criarCategoria() {
        System.out.println("CRIANDO NOVA CATEGORIA");
        // Solicita e armazena os dados do usuário
        teclado.nextLine();
        //System.out.println("Digite o Nome: ");
        System.out.println("Digite o Nome: ");
        String nome = teclado.nextLine();

        categoriaService.efetuaCadastroDeCategoria(new Categoria(null, nome));
    }

    private static void listarTodasCategorias() {
        var listaDeCategorias = categoriaService.listarCadastroDeCategorias();

        listaDeCategorias.stream()
                .forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

    private static void buscarCategoria() {
        System.out.println("Digite o id do cliente que voce procura:");
        Long id = teclado.nextLong();
        //1 Cliente cliente = clienteService.buscaID(id);
        Categoria categoria = categoriaService.buscaID(id);

        System.out.println("ID: " + categoria.getId());
        System.out.println("NOME: " + categoria.getNome());


        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }


}
