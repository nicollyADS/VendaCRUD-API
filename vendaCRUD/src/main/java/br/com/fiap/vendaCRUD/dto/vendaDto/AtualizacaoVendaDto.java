package br.com.fiap.vendaCRUD.dto.vendaDto;

import java.time.LocalDate;

public record AtualizacaoVendaDto(Integer quantidade, LocalDate dataVenda) {
}
