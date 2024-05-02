package br.com.fiap.vendaCRUD.dto.usuarioDto;
import br.com.fiap.vendaCRUD.model.Usuario;

import java.time.LocalDate;

public record DetalhesUsuarioDto(Long id, String nome, String email, String senha, LocalDate dataNascimento, String telefone, String sexoBiologico) {

    public DetalhesUsuarioDto(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getDadosUsuario().getDataNascimento(), usuario.getDadosUsuario().getTelefone(), usuario.getDadosUsuario().getSexoBiologico());
    }
}
