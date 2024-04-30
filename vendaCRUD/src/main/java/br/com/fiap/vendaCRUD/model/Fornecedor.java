package br.com.fiap.vendaCRUD.model;

import br.com.fiap.vendaCRUD.dto.fornecedorDto.AtualizacaoFornecedorDto;
import br.com.fiap.vendaCRUD.dto.fornecedorDto.CadastroFornecedorDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="T_FORNECEDOR")
@EntityListeners(AuditingEntityListener.class)
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornecedor")
    @SequenceGenerator(name = "fornecedor", sequenceName = "sq_fornecedor_", allocationSize = 1)
    @Column(name="id_fornecedor")
    private Long id;

    @Column(name="nm_fornecedor", length = 100,  nullable = false)
    private String nome;

    @Column(name="nr_telefone", length = 100,  nullable = false)
    private String telefone;


    //relacionamentos
    //fornecedor e produto


    public Fornecedor(CadastroFornecedorDto fornecedorDto) {
        nome = fornecedorDto.nome();
        telefone = fornecedorDto.telefone();
    }

    public void atualizarInformacoesFornecedor(AtualizacaoFornecedorDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.telefone() != null)
            telefone = dto.telefone();

    }

}
