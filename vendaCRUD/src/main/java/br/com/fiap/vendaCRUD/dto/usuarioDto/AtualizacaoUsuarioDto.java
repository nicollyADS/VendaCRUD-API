package br.com.fiap.vendaCRUD.dto.usuarioDto;

import java.time.LocalDate;

public record AtualizacaoUsuarioDto(

        String nome,

        String email,

        String senha,

        LocalDate dataNascimento,

        String telefone,

        String sexoBiologico) {
}
