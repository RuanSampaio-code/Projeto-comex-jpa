package br.alura.comex.Teste;

import br.alura.comex.models.Cliente;
import br.alura.comex.Service.ClienteService;
import br.alura.comex.exception.EntidadeNaoEncontradaException;

public class TesteAlteraçãoDeCliente {
    public static void main(String[] args) throws EntidadeNaoEncontradaException {


        Cliente novoCliente = new Cliente();
        novoCliente.setId(1L);
        novoCliente.setNome("Cliente alterado 2x");
        novoCliente.setEmail("alterado@gmail.com");
        novoCliente.setTelefone("(61) 99999-9999");
        novoCliente.setCpf("666.777.888-99");
        novoCliente.setLogradouro("EPTG - Deck Bar");
        novoCliente.setBairro("bairro alt");
        novoCliente.setCidade("Cidade da alteracao");
        novoCliente.setEstado("DF");
        novoCliente.setCep("72000-000");

        Long id =  novoCliente.getId();

        ClienteService service = new ClienteService();
        service.alteracaoDeCliente(id,novoCliente);
        System.out.printf("Cliente altrado com sucesso");
    }
}
