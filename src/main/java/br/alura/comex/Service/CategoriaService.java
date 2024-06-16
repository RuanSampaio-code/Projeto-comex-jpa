package br.alura.comex.Service;

import br.alura.comex.Dao.CategoriaDao;
import br.alura.comex.exception.EntidadeNaoEncontradaException;
import br.alura.comex.models.Categoria;
import br.alura.comex.models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class CategoriaService {

    private CategoriaDao categoriaDao;

    //construtor

    public CategoriaService() {
        this.categoriaDao = new CategoriaDao();
    }

    public List<Categoria> listarCadastroDeCategorias(){
        List<Categoria> lista = new ArrayList<>();

        if (lista.isEmpty()){
            System.out.println("A tabela está vazia");
        }
        lista = this.categoriaDao.listaTodos();
        return lista;
    }


    public void efetuaCadastroDeCategoria(Categoria novaCategoria){
        this.categoriaDao.cadastra(novaCategoria);
    }


    public void excluiClientePeloId(Long id) {
        Categoria CategoriaParaExcluir = categoriaDao.pesquisaPorId(id);
        if (CategoriaParaExcluir == null) {
            throw new EntidadeNaoEncontradaException("Cliente não está cadastrado: " + id);
        }

        this.categoriaDao.excluir(CategoriaParaExcluir);

    }

    public Categoria buscaID(Long id) {
        Categoria CategoriaParaAlterar = categoriaDao.pesquisaPorId(id);

        if (CategoriaParaAlterar == null){
            throw new EntidadeNaoEncontradaException("Cliente não está cadastrado: " );
        }

        return CategoriaParaAlterar;
    }

    public void alteracaoDeCategoria(Long id, Categoria categoria) throws EntidadeNaoEncontradaException{

        Categoria categoriaParaalterar = categoriaDao.pesquisaPorId(id);

        if (categoriaParaalterar == null){
            throw new EntidadeNaoEncontradaException("Categria não está cadastrado: " );
        }

        categoriaDao.alteracao(id, categoria);


    }
}
