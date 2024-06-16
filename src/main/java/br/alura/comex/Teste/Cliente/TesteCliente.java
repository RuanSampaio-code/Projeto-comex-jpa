package br.alura.comex.Teste.Cliente;

import br.alura.comex.models.Cliente;
import br.alura.comex.Service.ClienteService;
import br.alura.comex.exception.EntidadeNaoEncontradaException;

import java.util.Scanner;

public class TesteCliente {

    //Atributos da classe de chamada
    private final static ClienteService clienteService = new ClienteService();
    private static Scanner teclado = new Scanner(System.in);


    public static void main(String[] args) {

        //OPCAO NO TECLADO
        System.out.println("BEM VINDOS AO PROJETO COMEX - CADASTRO DE CLIENTES");
        var opc = exibirMenu();

        while (opc != 6){

            try{
                switch (opc) {
                    case 1:
                        listarTodosClientes();
                        break;
                    case 2:
                        criarCliente();
                        break;
                    case 3:
                        deletarCliente();
                        break;
                    case 4:
                        atualizarCliente();
                        break;
                    case 5:
                        buscarCliente();
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
                1 - Listar todos os clientes
                2 - Criar um cliente 
                3 - Deletar um cliente
                4 - Atualizar um cliente
                5 - Listar um cliente
                6 - Finalizar operacoes
       
                """);
        return teclado.nextInt();

    }

    private static void criarCliente() {
        System.out.println("CRIANDO NOVO CLIENTE");
        // Solicita e armazena os dados do usuário
        teclado.nextLine();
        //System.out.println("Digite o Nome: ");
        System.out.println("Digite o Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Digite o CPF:");
        String cpf = teclado.nextLine();

        System.out.println("Digite o Email: ");
        String email = teclado.nextLine();

        System.out.println("Digite o Telefone: ");
        String telefone = teclado.nextLine();

        System.out.println("Digite o Logradouro: ");
        String logradouro = teclado.nextLine();

        System.out.println("Digite o Bairro: ");
        String bairro = teclado.nextLine();

        System.out.println("Digite a Cidade: ");
        String cidade = teclado.nextLine();

        System.out.println("Digite o Estado: ");
        String estado = teclado.nextLine();

        System.out.println("Digite o CEP: ");
        String cep = teclado.nextLine();



        System.out.println("Cliente cadastrado com sucesso");
    }



    private static void atualizarCliente() {
        //clienteService.alteracaoDeCliente();

        //Busca pelo ID
        System.out.printf("Digite o ID do cliente que voce deseja alterar as infromações:");
        Long id = teclado.nextLong();

        //Retorna se existe cliente com este numero
        clienteService.buscaID(id);

        teclado.nextLine();

        System.out.println("Digite o Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Digite o CPF:");
        String cpf = teclado.nextLine();

        System.out.println("Digite o Email: ");
        String email = teclado.nextLine();

        System.out.println("Digite o Telefone: ");
        String telefone = teclado.nextLine();

        System.out.println("Digite o Logradouro: ");
        String logradouro = teclado.nextLine();

        System.out.println("Digite o Bairro: ");
        String bairro = teclado.nextLine();

        System.out.println("Digite a Cidade: ");
        String cidade = teclado.nextLine();

        System.out.println("Digite o Estado: ");
        String estado = teclado.nextLine();

        System.out.println("Digite o CEP: ");
        String cep = teclado.nextLine();

        Cliente novoCliente = new Cliente(null ,cpf,  nome,  email,  telefone,  logradouro,  bairro,  cidade,  estado,  cep);

        //clienteService.efetuaCadastroDeCliente( new Cliente( null,cpf,  nome,  email,  telefone,  logradouro,  bairro,  cidade,  estado,  cep));
        clienteService.alteracaoDeCliente(id,novoCliente);
        System.out.printf("Cliente altrado com sucesso");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

    private static void deletarCliente() {

        System.out.printf("Exclusão de cadastro");
        System.out.println("Digite o id do cliente que deseja excluir: ");
        Long id = teclado.nextLong();

        clienteService.excluiClientePeloId(id);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }


    private static void listarTodosClientes() {

        var clientes = clienteService.listarCadastroDeClientes();
        clientes.stream()
                        .forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }


    private static void buscarCliente() {

        System.out.println("Digite o id do cliente que voce procura:");
        Long id = teclado.nextLong();
       //1 Cliente cliente = clienteService.buscaID(id);
        Cliente cliente = clienteService.buscaID(id);

        System.out.println("ID: " + cliente.getId());
        System.out.println("NOME: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("EMAIL: " + cliente.getEmail());
        System.out.println("TELEFONE: " + cliente.getTelefone());
        System.out.println("LOGRADOURO: " + cliente.getLogradouro());
        System.out.println("BAIRRO: " + cliente.getBairro());
        System.out.println("CIDADE: " + cliente.getCidade());
        System.out.println("ESTADO: " + cliente.getEstado());
        System.out.println("CEP: " + cliente.getCep());
        System.out.println("========================================");
        System.out.println();

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }


}
