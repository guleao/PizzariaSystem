package br.com.uniamerica.pizzaria.pizarria.controller;


import br.com.uniamerica.pizzaria.pizarria.dto.SaboresDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.SaboresEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.SaboresRepository;
import br.com.uniamerica.pizzaria.pizarria.service.SaboresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sabores")
public class SaboresController {

    @Autowired
    private SaboresRepository saboresRepository;

    @Autowired
    private SaboresService saboresService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final SaboresEntity sabores = this.saboresRepository.findById(id).orElse(null);
        return sabores == null
                ? ResponseEntity.badRequest().body("Nenhum sabor encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(sabores);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta() {
        return ResponseEntity.ok(this.saboresRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final SaboresDTO sabores) {
        try {
            this.saboresService.validaSabor(sabores);
            return ResponseEntity.ok("Sabor cadastrado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final SaboresEntity sabores) {
        try {
            this.saboresService.editaSabor(id, sabores);
            return ResponseEntity.ok("Sabor atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") final Long id
    ) {
        try {
            this.saboresService.deletaSabor(id);
            return ResponseEntity.ok("Sabor excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}

