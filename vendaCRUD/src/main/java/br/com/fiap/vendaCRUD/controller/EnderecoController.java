package br.com.fiap.vendaCRUD.controller;


import br.com.fiap.vendaCRUD.dto.enderecoDto.AtualizacaoEnderecoDto;
import br.com.fiap.vendaCRUD.dto.enderecoDto.CadastroEnderecoDto;
import br.com.fiap.vendaCRUD.dto.enderecoDto.DetalhesEnderecoDto;
import br.com.fiap.vendaCRUD.model.Endereco;
import br.com.fiap.vendaCRUD.repository.EnderecoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesEnderecoDto>> get(Pageable pageable){
        var endereco = enderecoRepository.findAll(pageable)
                .stream().map(DetalhesEnderecoDto::new).toList();
        return ResponseEntity.ok(endereco);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesEnderecoDto> get(@PathVariable("id")Long id){
        var endereco = enderecoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesEnderecoDto(endereco));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEnderecoDto> post(@RequestBody @Valid CadastroEnderecoDto enderecoDto,
                                                    UriComponentsBuilder uriBuilder){
        var endereco = new Endereco(enderecoDto);
        enderecoRepository.save(endereco);
        var uri = uriBuilder.path("enderecos/{id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoDto(endereco));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        enderecoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesEnderecoDto> put(@PathVariable("id")Long id,
                                                        @RequestBody @Valid AtualizacaoEnderecoDto dto){
        var endereco = enderecoRepository.getReferenceById(id);
        endereco.atualizarInformacoesEndereco(dto);
        return ResponseEntity.ok(new DetalhesEnderecoDto(endereco));
    }



}
