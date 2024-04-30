package br.com.fiap.vendaCRUD.repository;

import br.com.fiap.vendaCRUD.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
