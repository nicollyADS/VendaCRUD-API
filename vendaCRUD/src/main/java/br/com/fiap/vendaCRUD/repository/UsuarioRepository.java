package br.com.fiap.vendaCRUD.repository;

import br.com.fiap.vendaCRUD.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
