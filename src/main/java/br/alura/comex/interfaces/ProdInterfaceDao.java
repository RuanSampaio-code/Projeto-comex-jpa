package br.alura.comex.interfaces;

import br.alura.comex.models.Categoria;
import br.alura.comex.models.Produto;

import java.util.List;

public interface ProdInterfaceDao {
    void cadastra(Produto produto);

    List<Produto> listarTodos();

    Produto buscarID(Long id);

    void alterar(Long id, Produto produto);

    void remover(Long id);
}
