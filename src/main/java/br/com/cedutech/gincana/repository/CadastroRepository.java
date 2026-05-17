package br.com.cedutech.gincana.repository;

import java.util.List;

import br.com.cedutech.gincana.model.Cadastro;
import br.com.cedutech.gincana.storage.JsonStorage;

public class CadastroRepository {

    private final JsonStorage<Cadastro> storage =
            new JsonStorage<>("cadastros.json", Cadastro.class);

    /* =========================
       READ ALL
    ========================= */
    public List<Cadastro> findAll() {
        return storage.readAll();
    }

    /* =========================
       FIND BY ID
    ========================= */
    public Cadastro findById(long id) {
        return storage.readAll()
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /* =========================
       CREATE
    ========================= */
    public void save(Cadastro cadastro) {
        List<Cadastro> cadastros = storage.readAll();
        cadastros.add(cadastro);
        storage.saveAll(cadastros);
    }

    /* =========================
       UPDATE
    ========================= */
    public void update(Cadastro cadastroAtualizado) {

        List<Cadastro> cadastros = storage.readAll();

        for (int i = 0; i < cadastros.size(); i++) {
            if (cadastros.get(i).getId() == cadastroAtualizado.getId()) {
                cadastros.set(i, cadastroAtualizado);
                storage.saveAll(cadastros);
                return;
            }
        }

        throw new RuntimeException("Cadastro não encontrado para atualização. ID=" + cadastroAtualizado.getId());
    }

    /* =========================
       DELETE
    ========================= */
    public void deleteById(long id) {

        List<Cadastro> cadastros = storage.readAll();

        boolean removido = cadastros.removeIf(c -> c.getId() == id);

        if (!removido) {
            throw new RuntimeException("Cadastro não encontrado para exclusão. ID=" + id);
        }

        storage.saveAll(cadastros);
    }
}
