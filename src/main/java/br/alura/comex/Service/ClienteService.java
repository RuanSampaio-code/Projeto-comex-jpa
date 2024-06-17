package br.alura.comex.Service;

import br.alura.comex.models.Cliente;
import br.alura.comex.dao.ClienteDao;
import br.alura.comex.exception.EntidadeNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {



    //classe para efetuar as operações no banco
    private ClienteDao clienteDao;

    public ClienteService(){
        this.clienteDao = new ClienteDao();
    }

    public void efetuaCadastroDeCliente(Cliente novoCliente){
        this.clienteDao.cadastra(novoCliente);
    }

    public List<Cliente> listarCadastroDeClientes(){
        List<Cliente> lista = new ArrayList<>();
        lista = this.clienteDao.listaTodos();
        return lista;
    }

    //alteração de cliente
    public void alteracaoDeCliente(Long id, Cliente clienteAlterado) throws EntidadeNaoEncontradaException{

        Cliente clienteparaAlterar = clienteDao.pesquisaPorId(id);

        if (clienteparaAlterar == null){
            throw new EntidadeNaoEncontradaException("Cliente não está cadastrado: " + clienteAlterado);
        }

        clienteDao.alteracao(id, clienteAlterado);


    }

    public void excluiClientePeloId(Long id) throws EntidadeNaoEncontradaException {
        Cliente clienteParaExcluir = clienteDao.pesquisaPorId(id);
        if (clienteParaExcluir == null) {
            throw new EntidadeNaoEncontradaException("Cliente não está cadastrado: " + id);
        }

        this.clienteDao.excluir(clienteParaExcluir);

    }

    public Cliente buscaID(Long id){

        Cliente clienteparaAlterar = clienteDao.pesquisaPorId(id);

        if (clienteparaAlterar == null){
            throw new EntidadeNaoEncontradaException("Cliente não está cadastrado: " );
        }

        return clienteparaAlterar;

    }


}
