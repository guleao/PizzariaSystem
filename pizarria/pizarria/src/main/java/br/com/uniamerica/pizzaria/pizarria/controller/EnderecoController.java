package br.com.uniamerica.pizzaria.pizarria.controller;

import br.com.uniamerica.pizzaria.pizarria.dto.EnderecoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.Endereco;
import br.com.uniamerica.pizzaria.pizarria.repository.EnderecoRepository;
import br.com.uniamerica.pizzaria.pizarria.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findByIdPath(@PathVariable("id") final Long id) {
        final Endereco endereco = this.enderecoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping("/lista")
    public ResponseEntity<List <Endereco>> listaCompleta() {
        return ResponseEntity.ok(this.enderecoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<String> cadastraEndereco (@RequestBody final EnderecoDTO enderecoDTO) {
        try {
            this.enderecoService.validaEndereco(enderecoDTO);
            return ResponseEntity.ok("Endereco cadastrado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "Error: " + e.getCause().getCause().getMessage();
            return ResponseEntity.internalServerError().body(errorMessage);
        } catch (Exception e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarEndereco (@PathVariable("id") final Long id, @RequestBody final Endereco endereco) {
        try {
            this.enderecoService.editaEndereco(id,endereco);
            return ResponseEntity.ok("Endereco atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaEndereco (@PathVariable("id") final Long id) {
        try {
            this.enderecoService.deletaEndereco(id);
            return ResponseEntity.ok("Endereco excluido com sucesso.");
        } catch (Exception e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }

}