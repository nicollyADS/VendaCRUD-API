package br.com.fiap.vendaCRUD.dto.vendaDto;
import br.com.fiap.vendaCRUD.model.Venda;
import java.time.LocalDate;

public record DetalhesVendaDto(Long id, Integer quantidade, LocalDate dataVenda) {

    public DetalhesVendaDto(Venda venda){
        this(venda.getId(), venda.getQuantidade(), venda.getDataVenda() );
    }
}
