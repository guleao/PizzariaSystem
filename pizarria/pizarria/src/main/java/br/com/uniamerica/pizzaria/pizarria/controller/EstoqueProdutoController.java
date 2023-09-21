package br.com.uniamerica.pizzaria.pizarria.controller;

import br.com.uniamerica.pizzaria.pizarria.dto.EstoqueProdutoDTO;

import br.com.uniamerica.pizzaria.pizarria.entity.EstoqueProdutos;
import br.com.uniamerica.pizzaria.pizarria.repository.EstoqueProdutoRepository;
import br.com.uniamerica.pizzaria.pizarria.service.EstoqueProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/estoqueproduto")
public class EstoqueProdutoController {

    @Autowired
    private EstoqueProdutoRepository estoqueProdutoRepository;

    @Autowired
    private EstoqueProdutoService estoqueProdutoService;

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueProdutos> findByIdPath(@PathVariable("id") final Long id) {
        final EstoqueProdutos estoqueProduto = this.estoqueProdutoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(estoqueProduto);
    }

    @GetMapping("/lista")
    public ResponseEntity<List <EstoqueProdutos>> listaCompleta() {
        return ResponseEntity.ok(this.estoqueProdutoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody final EstoqueProdutoDTO estoqueProduto) {
        try {
            this.estoqueProdutoService.validaEstoque(estoqueProduto);
            return ResponseEntity.ok("Estoque cadastrado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") final Long id, @RequestBody final EstoqueProdutos estoqueProduto) {
        try {
            this.estoqueProdutoService.editaEstoque(id, estoqueProduto);
            return ResponseEntity.ok("Estoque atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") final Long id
    ) {
        try {
            this.estoqueProdutoService.deletarProduto(id);
            return ResponseEntity.ok("Estoque excluido com sucesso.");
        } catch (Exception e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }

}