package br.com.fiap.vendaCRUD.repository;

import br.com.fiap.vendaCRUD.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
