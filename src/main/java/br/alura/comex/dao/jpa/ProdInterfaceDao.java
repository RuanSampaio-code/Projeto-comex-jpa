package br.alura.comex.dao.jpa;

import br.alura.comex.models.Categoria;
import br.alura.comex.models.Produto;

import java.util.List;

public interface ProdInterfaceDao {
    void cadastra(Produto produto);

    List<Produto> listarTodos();

    Produto buscarID(Long id);

    void alterar(Long id, Categoria categoriaAlterar);

    void remover(Long id);
}
