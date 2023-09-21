package br.com.uniamerica.pizzaria.pizarria.controller;

import br.com.uniamerica.pizzaria.pizarria.dto.LoginDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.Login;
import br.com.uniamerica.pizzaria.pizarria.repository.LoginRepository;
import br.com.uniamerica.pizzaria.pizarria.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginService loginService;

    @GetMapping("/{id}")
    public ResponseEntity<Login> findByIdPath(@PathVariable("id") final Long id) {
        final Login login = this.loginRepository.findById(id).orElse(null);
        return ResponseEntity.ok(login);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Login>> listaCompleta() {
        return ResponseEntity.ok(this.loginRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<String> cadastrarLogin (@RequestBody final LoginDTO login) {
        try {
            this.loginService.validaLogin(login);
            return ResponseEntity.ok("Login realizado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarLogin (@PathVariable("id") final Long id, @RequestBody final Login login) {
        try {
            this.loginService.editaLogin(id,login);
            return ResponseEntity.ok("Login atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLogin(@PathVariable("id") final Long id) {
        try {
            this.loginService.deletaLogin(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        } catch (Exception e) {
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }
    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }
}