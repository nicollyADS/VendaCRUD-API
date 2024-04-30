package br.com.fiap.vendaCRUD.model;

import br.com.fiap.vendaCRUD.dto.vendaDto.AtualizacaoVendaDto;
import br.com.fiap.vendaCRUD.dto.vendaDto.CadastroVendaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="T_VENDA")
@EntityListeners(AuditingEntityListener.class)
public class Venda{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda")
    @SequenceGenerator(name = "venda", sequenceName = "sq_venda", allocationSize = 1)
    @Column(name="id_venda")
    private Long id;

    @Column(name="qt_venda",  nullable = false)
    private Integer quantidade;

    @Column(name="dt_venda",  nullable = false)
    private LocalDate dataVenda;

    //relacionamentos
    //venda e produto - tabela associativa
    @ManyToMany
    @JoinTable(name="T_PRODUTO_VENDA",
            joinColumns = @JoinColumn(name="id_venda"),
            inverseJoinColumns = @JoinColumn(name="id_produto"))
    private List<Produto> produtos;


    //venda e usuario
    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    private Usuario usuario;



    public Venda(CadastroVendaDto vendaDto) {
        quantidade = vendaDto.quantidade();
        dataVenda = vendaDto.dataVenda();
    }

    public void atualizarInformacoesVenda(AtualizacaoVendaDto dto) {
        if (dto.quantidade() != null)
            quantidade = dto.quantidade();
        if (dto.dataVenda() != null)
            dataVenda = dto.dataVenda();
    }

}
