package br.com.cedutech.gincana.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cedutech.gincana.model.Cadastro;
import br.com.cedutech.gincana.repository.CadastroRepository;

@Service
public class CadastroService {

    private final CadastroRepository repository = new CadastroRepository();

    public Cadastro cadastrar(Cadastro cadastro) {
        cadastro.setId(System.currentTimeMillis());
        cadastro.setDataCadastro(LocalDate.now());
        repository.save(cadastro);
        return cadastro;
    }

    public List<Cadastro> listarTodos() {
        return repository.findAll();
    }

    public Cadastro atualizar(Cadastro cadastro) {
        Cadastro existente = repository.findById(cadastro.getId());

        if (existente == null) {
            throw new RuntimeException("Cadastro não encontrado.");
        }

        if (cadastro.getDataCadastro() == null) {
            cadastro.setDataCadastro(existente.getDataCadastro());
        }

        repository.update(cadastro);
        return cadastro;
    }

    /* =========================
       DELETE
    ========================= */
    public void deletar(long id) {
        repository.deleteById(id);
    }
    
    
    public void excluir(long id) {
        repository.deleteById(id);
    }

}
