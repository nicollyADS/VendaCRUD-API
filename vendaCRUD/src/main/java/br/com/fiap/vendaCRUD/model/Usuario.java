package br.com.fiap.vendaCRUD.model;


import br.com.fiap.vendaCRUD.dto.usuarioDto.AtualizacaoUsuarioDto;
import br.com.fiap.vendaCRUD.dto.usuarioDto.CadastroUsuarioDto;
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
@Table(name="T_USUARIO")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
    @SequenceGenerator(name = "usuario", sequenceName = "sq_usuario", allocationSize = 1)
    @Column(name="id_usuario")
    private Long id;

    @Column(name="nm_usuario", length = 100,  nullable = false)
    private String nome;

    @Column(name="ds_email", length = 100,  nullable = false)
    private String email;

    @Column(name="ds_senha", length = 50,  nullable = false)
    private String senha;

    //relacionamentos

    //usuario e dados usuario
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private DadosUsuario dadosUsuario;

    //usuario e endereco
    @OneToMany(mappedBy = "usuario")
    private List<Endereco> enderecos;

    //usuario e venda
    @OneToMany(mappedBy = "usuario")
    private List<Venda> vendas;


    public Usuario(CadastroUsuarioDto usuarioDto) {
        nome = usuarioDto.nome();
        email = usuarioDto.email();
        senha = usuarioDto.senha();
    }

    public void atualizarInformacoesUsuario(AtualizacaoUsuarioDto dto) {
        if (dto.nome() != null)
            nome = dto.nome();
        if (dto.email() != null)
            email = dto.email();
        if(dto.email() != null)
            senha = dto.senha();
    }


}
