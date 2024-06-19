package br.alura.comex.interfaces;

import br.alura.comex.models.Cliente;
import br.alura.comex.models.Produto;

import java.util.List;

public interface ClienteInterfaceDao {

    void cadastra(Cliente cliente);

    List<Cliente> listarTodos();

    Cliente buscarID(Long id);

    void alterar(Long id, Cliente cliente);

    void remover(Long id);
}
