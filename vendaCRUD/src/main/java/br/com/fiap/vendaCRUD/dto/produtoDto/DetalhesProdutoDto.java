package br.com.fiap.vendaCRUD.dto.produtoDto;
import br.com.fiap.vendaCRUD.dto.fornecedorDto.DetalhesFornecedorDto;
import br.com.fiap.vendaCRUD.model.Produto;
import br.com.fiap.vendaCRUD.model.TipoProduto;

public record DetalhesProdutoDto(Long id, String nome, String descricao, Double preco, TipoProduto tipoProduto, DetalhesFornecedorDto detalhesFornecedor) {

    public DetalhesProdutoDto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getTipoProduto(), new DetalhesFornecedorDto(produto.getFornecedor()));
    }
}
