package br.com.uniamerica.pizzaria.pizarria.controller;


import br.com.uniamerica.pizzaria.pizarria.dto.UsuarioDTO;
import br.com.uniamerica.pizzaria.pizarria.entity.UsuarioEntity;
import br.com.uniamerica.pizzaria.pizarria.repository.UsuarioRepository;
import br.com.uniamerica.pizzaria.pizarria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final UsuarioEntity usuario = this.usuarioRepository.findById(id).orElse(null);
        return usuario == null
                ? ResponseEntity.badRequest().body("Nenhum usuário encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(usuario);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta() {
        return ResponseEntity.ok(this.usuarioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario (@RequestBody final UsuarioDTO usuario) {
        try {
            this.usuarioService.validaUsuario(usuario);
            return ResponseEntity.ok("Usuario cadastrado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarUsuario (@PathVariable("id") final Long id, @RequestBody final UsuarioEntity usuario) {
        try {
            this.usuarioService.editaUsuario(id, usuario);
            return ResponseEntity.ok("Usuario atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {
        try {
            this.usuarioService.deletaUsuario(id);
            return ResponseEntity.ok("Usuário excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}