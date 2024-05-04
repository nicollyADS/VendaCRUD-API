package br.com.fiap.vendaCRUD.model;

import br.com.fiap.vendaCRUD.dto.produtoDto.AtualizacaoProdutoDto;
import br.com.fiap.vendaCRUD.dto.produtoDto.CadastroProdutoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="TAB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto")
    @SequenceGenerator(name = "produto", sequenceName = "sq_produto", allocationSize = 1)
    @Column(name="id_produto")
    private Long id;

    @Column(name="nm_produto", length = 100, nullable = false)
    private String nome;

    @Column(name="ds_produto", length = 500,  nullable = false)
    private String descricao;

    @Column(name="vl_produto", nullable = false)
    private Double preco;

    @Enumerated(EnumType.STRING)
    @Column(name="tp_produto",  nullable = false)
    private TipoProduto tipoProduto;


    //relacionamentos

    //fornecedor e produto
    @ManyToOne
    @JoinColumn(name="id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    //venda e produto - tabela associativa
    @ManyToMany(mappedBy = "produtos")
    private List<Venda> vendas;


    public Produto(CadastroProdutoDto produtoDto, Fornecedor fornecedor) {
        nome = produtoDto.nome();
        descricao = produtoDto.descricao();
        preco = produtoDto.preco();
        tipoProduto = produtoDto.tipoProduto();
        this.fornecedor = fornecedor;
    }

    public void atualizarInformacoesProduto(AtualizacaoProdutoDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.descricao() != null)
            descricao = dto.descricao();
        if (dto.preco() != null)
            preco = dto.preco();
        if (dto.tipoProduto() != null)
            tipoProduto = dto.tipoProduto();
    }


}
