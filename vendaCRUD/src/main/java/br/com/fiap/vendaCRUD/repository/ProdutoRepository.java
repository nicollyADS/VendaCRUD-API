package br.com.fiap.vendaCRUD.repository;

import br.com.fiap.vendaCRUD.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
