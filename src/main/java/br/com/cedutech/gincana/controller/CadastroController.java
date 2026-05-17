package br.com.cedutech.gincana.controller;

import br.com.cedutech.gincana.model.Cadastro;
import br.com.cedutech.gincana.service.CadastroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastros")
@CrossOrigin
public class CadastroController {

    private final CadastroService service;

    public CadastroController(CadastroService service) {
        this.service = service;
    }

    /* =========================
       CREATE
    ========================= */
    @PostMapping
    public Cadastro cadastrar(@RequestBody Cadastro cadastro) {
        return service.cadastrar(cadastro);
    }

    /* =========================
       READ
    ========================= */
    @GetMapping
    public List<Cadastro> listar() {
        return service.listarTodos();
    }

    /* =========================
       UPDATE
    ========================= */
    @PutMapping("/{id}")
    public Cadastro atualizar(@PathVariable long id, @RequestBody Cadastro cadastro) {
        cadastro.setId(id);
        return service.atualizar(cadastro);
    }

    /* =========================
       DELETE (APENAS UM!)
    ========================= */
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable long id) {
        service.excluir(id);
    }
}
