package br.com.fiap.vendaCRUD.dto.fornecedorDto;

import jakarta.validation.constraints.NotBlank;

public record CadastroFornecedorDto(

        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @NotBlank(message = "O telefone não pode estar em branco")
        String telefone) {
}
