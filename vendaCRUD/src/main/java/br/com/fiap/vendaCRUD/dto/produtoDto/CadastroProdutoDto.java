package br.com.fiap.vendaCRUD.dto.produtoDto;

import br.com.fiap.vendaCRUD.model.TipoProduto;

public record CadastroProdutoDto(String nome, String descricao, Double preco, TipoProduto tipoProduto) {

}
