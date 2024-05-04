package br.com.fiap.vendaCRUD.dto.vendaDto;
import br.com.fiap.vendaCRUD.dto.usuarioDto.DetalhesUsuarioDto;
import br.com.fiap.vendaCRUD.model.Venda;
import java.time.LocalDateTime;

public record DetalhesVendaDto(Long id, Integer quantidade, LocalDateTime dataVenda, DetalhesUsuarioDto detalhesUsuario) {

    public DetalhesVendaDto(Venda venda){
        this(venda.getId(), venda.getQuantidade(), venda.getDataVenda(), new DetalhesUsuarioDto(venda.getUsuario()));
    }
}
