package br.alura.comex.Teste.Produto;

import br.alura.comex.Service.ClienteService;
import br.alura.comex.Service.ProdutoService;
import br.alura.comex.exception.EntidadeNaoEncontradaException;
import br.alura.comex.models.Cliente;
import br.alura.comex.models.Produto;

import java.util.Scanner;

public class TesteProduto {
    //Atributos da classe de chamda
    private final static ProdutoService produtoService = new ProdutoService();
    private static Scanner teclado = new Scanner(System.in);


    public static void main(String[] args) {

        //OPCAO NO TECLADO
        System.out.println("BEM VINDOS AO PROJETO COMEX - CADASTRO DE PRODUTOS");
        var opc = exibirMenu();

        while (opc != 6){

            try{
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
                        atualizarProduto();
                        break;
                    case 5:
                        buscarProduto();
                        break;
                }

            }catch (EntidadeNaoEncontradaException e){
                throw  new EntidadeNaoEncontradaException("Error");
            }

            opc = exibirMenu();


        }


        //instanciando novo cliente
//            Cliente novoCliente = new Cliente();
//
//            //setando atributos
//            novoCliente.setNome("Cliente C");
//            novoCliente.setEmail("CC@gmail.com");
//            novoCliente.setTelefone("(61) 65465-1019");
//            novoCliente.setCpf("666.777.888-99");
//            novoCliente.setLogradouro("Lougradou teste");
//            novoCliente.setBairro("Teste 1 s");
//            novoCliente.setCidade("São luiis");
//            novoCliente.setEstado("MA");
//            novoCliente.setCep("65865-000");
//
//
//            //chama classe cliente service para
//            ClienteService service = new ClienteService();
//            service.efetuaCadastroDeCliente(novoCliente);
//
//            System.out.println("Cliente cadastrado com sucesso!");

    }


    private static int exibirMenu(){
        System.out.println("""
                Escolha uma opcao :
                1 - Listar todos os produtos
                2 - Criar um produto 
                3 - Deletar um produto
                4 - Atualizar um produto
                5 - Listar um produto
                6 - Finalizar operacoes
       
                """);
        return teclado.nextInt();

    }

    private static void criarProduto() {
        System.out.println("CRIANDO NOVO PRODUTO");
        // Solicita e armazena os dados do usuário
        teclado.nextLine();
        //System.out.println("Digite o Nome: ");
        System.out.println("Digite o Nome do produto: ");
        String nome = teclado.nextLine();

        System.out.println("Digite a descrição do produto:");
        String descrisao = teclado.nextLine();

        System.out.println("Digite o Preco: ");
        Double preco = teclado.nextDouble();

        produtoService.efetuaCadastroDeProdutos(new Produto(null, nome,descrisao,preco));
        System.out.println("Produto adicionado com sucesso");
    }


    private static void atualizarProduto() {
        //clienteService.alteracaoDeCliente();

        //Busca pelo ID
        System.out.printf("Digite o ID do produto que voce deseja alterar as infromações:");
        Long id = teclado.nextLong();

        //Retorna se existe cliente com este numero
        produtoService.buscaID(id);

        teclado.nextLine();

        System.out.println("Digite o novo Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Digite a nova descrisão:");
        String descrisao = teclado.nextLine();

        System.out.println("Digite o novo valor do produo: ");
        Double preco= teclado.nextDouble();

        Produto produtoNovo = new Produto(null, nome,descrisao,preco);

        produtoService.alteracaoDeProduto(id,produtoNovo);
        System.out.printf("Produto alterado com sucesso");

    }

    private static void deletarProduto() {

        System.out.println("Exclusão de cadastro");
        System.out.println("Digite o id do Produto que deseja excluir: ");
        Long id = teclado.nextLong();

        produtoService.excluiprodutoPeloId(id);



        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }


    private static void listarTodosProdutos() {

        var produtos = produtoService.listarCadastroDeProdutos();
        if (produtos.isEmpty()){
            System.out.println("A tabela está vazia");
        }
        produtos.stream()
                .forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }


    private static void buscarProduto() {

        System.out.println("Digite o id do produto que voce procura:");
        Long id = teclado.nextLong();
        //1 Cliente cliente = clienteService.buscaID(id);
        Produto produto = produtoService.buscaID(id);

        System.out.println("ID: " + produto.getId());
        System.out.println("NOME: " + produto.getNome());
        System.out.println("DESCRISAO: " + produto.getDescricao());
        System.out.println("PREÇO: " + produto.getPreco());

        System.out.println("========================================");
        System.out.println();
    }

}
