package br.com.fiap.vendaCRUD.repository;

import br.com.fiap.vendaCRUD.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
