package br.com.fiap.vendaCRUD.controller;

import br.com.fiap.vendaCRUD.dto.vendaDto.AtualizacaoVendaDto;
import br.com.fiap.vendaCRUD.dto.vendaDto.CadastroVendaDto;
import br.com.fiap.vendaCRUD.dto.vendaDto.DetalhesVendaDto;
import br.com.fiap.vendaCRUD.model.Venda;
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
@RequestMapping("vendas")
public class VendaController {
    @Autowired
    private VendaRepository vendaRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesVendaDto>> get(Pageable pageable){
        var venda = vendaRepository.findAll(pageable)
                .stream().map(DetalhesVendaDto::new).toList();
        return ResponseEntity.ok(venda);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesVendaDto> get(@PathVariable("id")Long id){
        var venda = vendaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesVendaDto(venda));
    }


    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        vendaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesVendaDto> put(@PathVariable("id")Long id,
                                                  @RequestBody @Valid AtualizacaoVendaDto dto){
        var venda = vendaRepository.getReferenceById(id);
        venda.atualizarInformacoesVenda(dto);
        return ResponseEntity.ok(new DetalhesVendaDto(venda));
    }
}
