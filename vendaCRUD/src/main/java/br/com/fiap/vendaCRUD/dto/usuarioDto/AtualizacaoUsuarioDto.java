package br.com.fiap.vendaCRUD.dto.usuarioDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizacaoUsuarioDto(

        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "O email deve estar em um formato válido")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        String senha,

        @NotNull(message = "A data de nascimento não pode estar em branco")
        LocalDate dataNascimento,

        @NotBlank(message = "O telefone não pode estar em branco")
        String telefone,

        @NotBlank(message = "O sexo biológico não pode estar em branco")
        String sexoBiologico) {
}
