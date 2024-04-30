package br.com.fiap.vendaCRUD.dto.fornecedorDto;

import br.com.fiap.vendaCRUD.model.Fornecedor;

public record DetalhesFornecedorDto(Long id, String nome, String telefone) {

    public DetalhesFornecedorDto(Fornecedor fornecedor){
        this(fornecedor.getId(), fornecedor.getNome(), fornecedor.getTelefone());
    }
}
