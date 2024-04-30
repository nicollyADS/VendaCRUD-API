package br.com.fiap.vendaCRUD.controller;
import br.com.fiap.vendaCRUD.dto.produtoDto.AtualizacaoProdutoDto;
import br.com.fiap.vendaCRUD.dto.produtoDto.CadastroProdutoDto;
import br.com.fiap.vendaCRUD.dto.produtoDto.DetalhesProdutoDto;
import br.com.fiap.vendaCRUD.model.Produto;
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
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesProdutoDto>> get(Pageable pageable){
        var produto = produtoRepository.findAll(pageable)
                .stream().map(DetalhesProdutoDto::new).toList();
        return ResponseEntity.ok(produto);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesProdutoDto> get(@PathVariable("id")Long id){
        var produto = produtoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesProdutoDto(produto));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesProdutoDto> post(@RequestBody @Valid CadastroProdutoDto produtoDto,
                                                    UriComponentsBuilder uriBuilder){
        var produto = new Produto(produtoDto);
        produtoRepository.save(produto);
        var uri = uriBuilder.path("produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesProdutoDto(produto));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesProdutoDto> put(@PathVariable("id")Long id,
                                                   @RequestBody @Valid AtualizacaoProdutoDto dto){
        var produto = produtoRepository.getReferenceById(id);
        produto.atualizarInformacoesProduto(dto);
        return ResponseEntity.ok(new DetalhesProdutoDto(produto));
    }
}
