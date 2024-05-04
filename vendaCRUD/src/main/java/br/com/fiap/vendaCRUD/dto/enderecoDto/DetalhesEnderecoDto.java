package br.com.fiap.vendaCRUD.dto.enderecoDto;
import br.com.fiap.vendaCRUD.dto.usuarioDto.DetalhesUsuarioDto;
import br.com.fiap.vendaCRUD.model.Endereco;

public record DetalhesEnderecoDto(Long id, String rua, String numero, String cidade, String estado, String cep, DetalhesUsuarioDto detalhesUsuario) {

    public DetalhesEnderecoDto(Endereco endereco){
        this(endereco.getId(), endereco.getRua(), endereco.getNumero(), endereco.getCidade(), endereco.getEstado(), endereco.getCep(), new DetalhesUsuarioDto(endereco.getUsuario()));
    }
}
