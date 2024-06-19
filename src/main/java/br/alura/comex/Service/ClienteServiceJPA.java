package br.alura.comex.Service;

import br.alura.comex.dao.jpa.JpaClienteDao;
import br.alura.comex.interfaces.ClienteInterfaceDao;
import br.alura.comex.models.Cliente;

import java.util.List;

public class ClienteServiceJPA implements ClienteInterfaceDao {

    private static JpaClienteDao jpaClienteDao;
    @Override
    public void cadastra(Cliente cliente) {
        jpaClienteDao.cadastra(cliente);
    }

    @Override
    public List<Cliente> listarTodos() {
        return null;
    }

    @Override
    public Cliente buscarID(Long id) {
        return null;
    }

    @Override
    public void alterar(Long id, Cliente cliente) {

    }

    @Override
    public void remover(Long id) {

    }
}
