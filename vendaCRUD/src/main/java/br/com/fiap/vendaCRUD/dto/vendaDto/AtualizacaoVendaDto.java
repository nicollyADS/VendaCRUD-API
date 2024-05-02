package br.com.fiap.vendaCRUD.dto.vendaDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record AtualizacaoVendaDto(

        @NotNull(message = "A quantidade não pode estar em branco")
        @Positive(message = "A quantidade deve ser um número positivo")
        Integer quantidade) {
}
