package br.com.uniamerica.pizzaria.pizarria.controller;


import br.com.uniamerica.pizzaria.pizarria.dto.ProdutosDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.ProdutosEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.ProdutosRepository;
import br.com.uniamerica.pizzaria.pizarria.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutosController {

    @Autowired
    private ProdutosRepository produtoRepository;

    @Autowired
    private ProdutosService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosEntity> findByIdPath(@PathVariable("id") final Long id) {
        final ProdutosEntity produto = this.produtoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/lista")
    public ResponseEntity<List <ProdutosEntity>> listaCompleta() {
        return ResponseEntity.ok(this.produtoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody final ProdutosDTO produto) {
        try {
            this.produtoService.validarProduto(produto);
            return ResponseEntity.ok("Produto cadastrado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") final Long id, @RequestBody final ProdutosEntity produto) {
        try {
            this.produtoService.editarProduto(id, produto);
            return ResponseEntity.ok("Produto atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") final Long id) {
        try {
            this.produtoService.deletarProduto(id);
            return ResponseEntity.ok("Produto excluido com sucesso.");
        } catch (Exception e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }
    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }
}