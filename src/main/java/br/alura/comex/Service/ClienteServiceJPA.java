package br.alura.comex.Service;

import br.alura.comex.dao.jpa.JpaClienteDao;
import br.alura.comex.interfaces.ClienteInterfaceDao;
import br.alura.comex.models.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteServiceJPA implements ClienteInterfaceDao {

    private static JpaClienteDao jpaClienteDao;

    public ClienteServiceJPA(EntityManager entityManager) {
    }

    @Override
    public void cadastra(Cliente cliente) {
        jpaClienteDao.cadastra(cliente);
    }

    @Override
    public List<Cliente> listarTodos() {

       List<Cliente> todosCliente = jpaClienteDao.listarTodos();


        return todosCliente;
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
