package br.alura.comex.Teste;

import br.alura.comex.Dao.ClienteDao;
import br.alura.comex.Service.ClienteService;
import br.alura.comex.banco.ConnectionFactory;
import br.alura.comex.exception.EntidadeNaoEncontradaException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteExclusaoDeCliente {
    //private ConnectionFactory conn = new ConnectionFactory();
    public static void main(String[] args) throws EntidadeNaoEncontradaException {
        ClienteService service = new ClienteService();
        service.excluiClientePeloId(2L);
        System.out.println("Cliente excluído com sucesso!");
    }
}
