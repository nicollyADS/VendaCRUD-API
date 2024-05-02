package br.com.fiap.vendaCRUD.dto.enderecoDto;

import jakarta.validation.constraints.NotBlank;

public record CadastroEnderecoDto (

        @NotBlank(message = "A rua não pode estar em branco")
        String rua,

        @NotBlank(message = "O número não pode estar em branco")
        String numero,

        @NotBlank(message = "A cidade não pode estar em branco")
        String cidade,

        @NotBlank(message = "O estado não pode estar em branco")
        String estado,

        @NotBlank(message = "O cep não pode estar em branco")
        String cep) {
}
