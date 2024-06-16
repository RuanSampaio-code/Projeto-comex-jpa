package br.alura.comex.Teste;

import br.alura.comex.models.Cliente;
import br.alura.comex.Service.ClienteService;

import java.util.ArrayList;
import java.util.List;

public class TesteListandoCliente {
    public static void main(String[] args) {
        List<Cliente> listaDeClientes = new ArrayList<>();
        ClienteService service = new ClienteService();

        listaDeClientes = service.listarCadastroDeClientes();

        for (Cliente cliente : listaDeClientes) {
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
        }

    }
}
