package br.com.uniamerica.pizzaria.pizarria.controller;

import br.com.uniamerica.pizzaria.pizarria.dto.EnderecoDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.Endereco;
import br.com.uniamerica.pizzaria.pizarria.repository.EnderecoRepository;
import br.com.uniamerica.pizzaria.pizarria.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Endereco endereco = this.enderecoRepository.findById(id).orElse(null);
        return endereco == null
                ? ResponseEntity.badRequest().body("Nenhum endereco encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(endereco);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta() {
        return ResponseEntity.ok(this.enderecoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastraEndereco (@RequestBody final EnderecoDTO enderecoDTO) {
        try {
            this.enderecoService.validaEndereco(enderecoDTO);
            return ResponseEntity.ok("Endereco cadastrado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarEndereco (@PathVariable("id") final Long id, @RequestBody final Endereco endereco) {
        try {
            this.enderecoService.editaEndereco(id,endereco);
            return ResponseEntity.ok("Endereco atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaEndereco (@PathVariable("id") final Long id) {
        try {
            this.enderecoService.deletaEndereco(id);
            return ResponseEntity.ok("Endereco excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
