package br.com.fiap.vendaCRUD.model;

import br.com.fiap.vendaCRUD.dto.enderecoDto.AtualizacaoEnderecoDto;
import br.com.fiap.vendaCRUD.dto.enderecoDto.CadastroEnderecoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="T_DADOS_USUARIO")
@EntityListeners(AuditingEntityListener.class)
public class DadosUsuario {

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco")
     @SequenceGenerator(name = "endereco", sequenceName = "sq_dados_usuario", allocationSize = 1)
     @Column(name="id_dados_usuario")
     private Long id;

     @Column(name="dt_nascimento", nullable = false)
     private LocalDate dataNascimento;

     @Column(name="nr_telefone", length = 20, nullable = false)
     private String telefone;

     @Column(name="ds_sexo_bio", length = 20, nullable = false)
     private String sexoBiologico;


     //relacionamentos
    //usuario e dados usuario
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
