package br.alura.comex.Teste.Cliente;

import br.alura.comex.Service.ClienteServiceJPA;
import br.alura.comex.Service.ProdutoServiceJPA;
import br.alura.comex.dao.jpa.JpaClienteDao;
import br.alura.comex.models.Cliente;
import br.alura.comex.models.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class ClienteTesteJpa {
    private static Scanner teclado = new Scanner(System.in);
    private static ClienteServiceJPA clienteServiceJPA;
    public static void main(String[] args) {

        //cria uma instância de EntityManagerFactory. , utilizando o nome da unidade de persistência fornecida
        EntityManagerFactory comex = Persistence.createEntityManagerFactory("comex");

        // Usa a EntityManagerFactory para criar um EntityManager
        // O EntityManager é usado para realizar operações de persistência no banco de dados
        EntityManager entityManager = comex.createEntityManager();

        // Cria uma instância de JpaCategoriaDao passando o EntityManager como argumento
        //JpaCategoriaDao dao = new JpaCategoriaDao(entityManager);
        clienteServiceJPA = new ClienteServiceJPA(entityManager);
        System.out.println("BEM VINDOS AO PROJETO COMEX - CADASTRO DE CLIENTES");

        var opc = exibirMenu();

        while ( opc != 6){
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

            opc = exibirMenu();
        }

        entityManager.close();
        comex.close();

    }
    private static int exibirMenu(){

        System.out.println("""
                Escolha uma opcao :
                1 - Listar todos as produtos
                2 - Criar um produto
                3 - Deletar uma Categoria
                4 - Atualizar uma Categoria
                5 - Listar uma Categoria
                6 - Finalizar operacoes
       
                """);

        return teclado.nextInt();

    }
    private static void listarTodosClientes() {
        List<Cliente> listaDeClientes = clienteServiceJPA.listarTodos();

        listaDeClientes.stream()
                .forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

    private static void deletarCliente(){
        System.out.print("Digite o ID do produto que você deseja deletar: ");
        Long id = teclado.nextLong();

        clienteServiceJPA.remover(id);

    }

    private static void criarCliente(){
        System.out.println("CRIANDO NOVO CLINETE");
        System.out.println("Digite o CPF: ");
        String cpf = teclado.nextLine();

        System.out.println("Digite o nome: ");
        String nome = teclado.nextLine();

        System.out.println("Digite o email: ");
        String email = teclado.nextLine();

        System.out.println("Digite o telefone: ");
        String telefone = teclado.nextLine();

        System.out.println("Digite o logradouro: ");
        String logradouro = teclado.nextLine();

        System.out.println("Digite o bairro: ");
        String bairro = teclado.nextLine();

        System.out.println("Digite a cidade: ");
        String cidade = teclado.nextLine();

        System.out.println("Digite o estado: ");
        String estado = teclado.nextLine();

        System.out.println("Digite o CEP: ");
        String cep = teclado.nextLine();

        // Criando o novo cliente com todos os campos
        Cliente novoCliente = new Cliente(cpf, nome, email, telefone, logradouro, bairro, cidade, estado, cep);
        clienteServiceJPA.cadastra(novoCliente);
    }

    private static void atualizarCliente(){
        System.out.print("Digite o ID do cliente que você deseja alterar: ");
        Long id = teclado.nextLong();
        teclado.nextLine(); // Consumir a quebra de linha deixada pelo nextLong()

        // Buscar a categoria pelo ID
        Cliente cliente = clienteServiceJPA.buscarID(id);

        if (cliente == null) {
            System.out.println("produto não encontrada com o ID informado.");
        } else {
            // Realizar a alteração
//            System.out.print("Digite o novo nome da categoria: ");
//            String nome = teclado.nextLine().trim(); // Use trim() para remover espaços em branco extras
//
//            System.out.print("Digite a nova descrição da categoria: ");
//            String descricao = teclado.nextLine().trim(); // Use trim() para remover espaços em branco extras
//
//            System.out.print("Digite a nova descrição da categoria: ");
//            Double valor = teclado.nextDouble();

            System.out.println("Digite o CPF: ");
            String cpf = teclado.nextLine();

            System.out.println("Digite o nome: ");
            String nome = teclado.nextLine();

            System.out.println("Digite o email: ");
            String email = teclado.nextLine();

            System.out.println("Digite o telefone: ");
            String telefone = teclado.nextLine();

            System.out.println("Digite o logradouro: ");
            String logradouro = teclado.nextLine();

            System.out.println("Digite o bairro: ");
            String bairro = teclado.nextLine();

            System.out.println("Digite a cidade: ");
            String cidade = teclado.nextLine();

            System.out.println("Digite o estado: ");
            String estado = teclado.nextLine();

            System.out.println("Digite o CEP: ");
            String cep = teclado.nextLine();

            // Atualizar os dados da categoria
            // Atualizar os dados do cliente
            cliente.setCpf(cpf);
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setTelefone(telefone);
            cliente.setLogradouro(logradouro);
            cliente.setBairro(bairro);
            cliente.setCidade(cidade);
            cliente.setEstado(estado);
            cliente.setCep(cep);
            cliente.setNome(nome);


            // Chamar o método de serviço para atualizar no banco de dados
            clienteServiceJPA.alterar(id, cliente);

            System.out.println("Categoria atualizada com sucesso.");
        }
    }

    private static void buscarCliente(){

        System.out.print("Digite o ID do produto que você deseja buscar: ");
        Long id = teclado.nextLong();

        Cliente produtobuscado = clienteServiceJPA .buscarID(id);

        System.out.println(produtobuscado);
    }

}


