package br.com.uniamerica.pizzaria.pizarria.controller;

import br.com.uniamerica.pizzaria.pizarria.dto.LoginDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import br.com.uniamerica.pizzaria.pizarria.repository.LoginRepository;
import br.com.uniamerica.pizzaria.pizarria.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginService loginService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Login login = this.loginRepository.findById(id).orElse(null);
        return login == null
                ? ResponseEntity.badRequest().body("Nenhum login encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(login);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta() {
        return ResponseEntity.ok(this.loginRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarLogin (@RequestBody final LoginDTO login) {
        try {
            this.loginService.validaLogin(login);
            return ResponseEntity.ok("Login realizado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarLogin (@PathVariable("id") final Long id, @RequestBody final Login login) {
        try {
          this.loginService.editaLogin(id,login);
            return ResponseEntity.ok("Login atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLogin(@PathVariable("id") final Long id) {
        try {
            this.loginService.deletaLogin(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
