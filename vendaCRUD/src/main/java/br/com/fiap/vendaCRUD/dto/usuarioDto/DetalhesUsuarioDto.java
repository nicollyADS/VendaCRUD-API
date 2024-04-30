package br.com.fiap.vendaCRUD.dto.usuarioDto;
import br.com.fiap.vendaCRUD.model.Usuario;

public record DetalhesUsuarioDto(Long id, String nome, String email, String senha) {

    public DetalhesUsuarioDto(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}
