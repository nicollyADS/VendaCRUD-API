package br.com.fiap.vendaCRUD.controller;
import br.com.fiap.vendaCRUD.dto.enderecoDto.CadastroEnderecoDto;
import br.com.fiap.vendaCRUD.dto.enderecoDto.DetalhesEnderecoDto;
import br.com.fiap.vendaCRUD.dto.usuarioDto.AtualizacaoUsuarioDto;
import br.com.fiap.vendaCRUD.dto.usuarioDto.CadastroUsuarioDto;
import br.com.fiap.vendaCRUD.dto.usuarioDto.DetalhesUsuarioDto;
import br.com.fiap.vendaCRUD.dto.vendaDto.CadastroVendaDto;
import br.com.fiap.vendaCRUD.dto.vendaDto.DetalhesVendaDto;
import br.com.fiap.vendaCRUD.model.Endereco;
import br.com.fiap.vendaCRUD.model.Usuario;
import br.com.fiap.vendaCRUD.model.Venda;
import br.com.fiap.vendaCRUD.repository.EnderecoRepository;
import br.com.fiap.vendaCRUD.repository.UsuarioRepository;
import br.com.fiap.vendaCRUD.repository.VendaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesUsuarioDto>> get(Pageable pageable){
        var usuario = usuarioRepository.findAll(pageable)
                .stream().map(DetalhesUsuarioDto::new).toList();
        return ResponseEntity.ok(usuario);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesUsuarioDto> get(@PathVariable("id")Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuario));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> post(@RequestBody  @Valid CadastroUsuarioDto usuarioDto,
                                                      UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(usuarioDto);
        usuarioRepository.save(usuario);
        var uri = uriBuilder.path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDto(usuario));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> put(@PathVariable("id")Long id,
                                                     @RequestBody @Valid AtualizacaoUsuarioDto dto){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizarInformacoesUsuario(dto);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuario));
    }


    // POST USUARIO ENDERECO
    @PostMapping("{id}/enderecos")
    @Transactional
    public ResponseEntity<DetalhesEnderecoDto> post(@PathVariable("id") Long id,
                                                    @RequestBody @Valid CadastroEnderecoDto enderecoDto,
                                                    UriComponentsBuilder uriBuilder){
        var usuario = usuarioRepository.getReferenceById(id);
        var endereco = new Endereco(enderecoDto, usuario);
        enderecoRepository.save(endereco);
        var uri = uriBuilder.path("enderecos/{id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoDto(endereco));
    }

    // POST USUARIO VENDA
    @PostMapping("{id}/vendas")
    @Transactional
    public ResponseEntity<DetalhesVendaDto> post(@PathVariable("id") Long id,
                                                 @RequestBody @Valid CadastroVendaDto vendaDto,
                                                 UriComponentsBuilder uriBuilder){
        var usuario = usuarioRepository.getReferenceById(id);
        var venda = new Venda(vendaDto, usuario);
        vendaRepository.save(venda);
        var uri = uriBuilder.path("vendas/{id}").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesVendaDto(venda));
    }

    // GET USUARIO ENDERECO

    // GET USUARIO VENDA
}
