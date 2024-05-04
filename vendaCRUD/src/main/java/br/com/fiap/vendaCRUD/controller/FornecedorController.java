package br.com.fiap.vendaCRUD.controller;

import br.com.fiap.vendaCRUD.dto.fornecedorDto.AtualizacaoFornecedorDto;
import br.com.fiap.vendaCRUD.dto.fornecedorDto.CadastroFornecedorDto;
import br.com.fiap.vendaCRUD.dto.fornecedorDto.DetalhesFornecedorDto;
import br.com.fiap.vendaCRUD.dto.produtoDto.CadastroProdutoDto;
import br.com.fiap.vendaCRUD.dto.produtoDto.DetalhesProdutoDto;
import br.com.fiap.vendaCRUD.model.Fornecedor;
import br.com.fiap.vendaCRUD.model.Produto;
import br.com.fiap.vendaCRUD.repository.FornecedorRepository;
import br.com.fiap.vendaCRUD.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("fornecedores")
public class FornecedorController {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesFornecedorDto>> get(Pageable pageable){
        var fornecedor = fornecedorRepository.findAll(pageable)
                .stream().map(DetalhesFornecedorDto::new).toList();
        return ResponseEntity.ok(fornecedor);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesFornecedorDto> get(@PathVariable("id")Long id){
        var fornecedor = fornecedorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesFornecedorDto(fornecedor));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesFornecedorDto> post(@RequestBody @Valid CadastroFornecedorDto fornecedorDto,
                                                    UriComponentsBuilder uriBuilder){
        var fornecedor = new Fornecedor(fornecedorDto);
        fornecedorRepository.save(fornecedor);
        var uri = uriBuilder.path("fornecedores/{id}").buildAndExpand(fornecedor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesFornecedorDto(fornecedor));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        fornecedorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesFornecedorDto> put(@PathVariable("id")Long id,
                                                   @RequestBody @Valid AtualizacaoFornecedorDto dto){
        var fornecedor = fornecedorRepository.getReferenceById(id);
        fornecedor.atualizarInformacoesFornecedor(dto);
        return ResponseEntity.ok(new DetalhesFornecedorDto(fornecedor));
    }

    //POST FORNECEDOR PRODUTO
    @PostMapping("{id}/produtos")
    @Transactional
    public ResponseEntity<DetalhesProdutoDto> post(@PathVariable("id") Long id,
                                                       @RequestBody @Valid CadastroProdutoDto produtoDto,
                                                       UriComponentsBuilder uriBuilder){
        var fornecedor = fornecedorRepository.getReferenceById(id);
        var produto = new Produto(produtoDto, fornecedor);
        produtoRepository.save(produto);
        var uri = uriBuilder.path("produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesProdutoDto(produto));
    }




}
