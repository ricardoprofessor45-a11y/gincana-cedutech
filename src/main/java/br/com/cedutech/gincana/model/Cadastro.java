package br.com.cedutech.gincana.model;

import java.time.LocalDate;

public class Cadastro {

    private long id;
    private Aluno aluno;
    private JovemConvidado jovemConvidado;
    private Responsavel responsavel;
    private LocalDate dataCadastro;

    // Construtor vazio (OBRIGATÓRIO para Jackson)
    public Cadastro() {
    }

    /* =========================
       GETTERS
    ========================= */

    public long getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public JovemConvidado getJovemConvidado() {
        return jovemConvidado;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    /* =========================
       SETTERS
    ========================= */

    public void setId(long id) {
        this.id = id;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setJovemConvidado(JovemConvidado jovemConvidado) {
        this.jovemConvidado = jovemConvidado;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    /**
     * IMPORTANTE:
     * - No POST: dataCadastro pode ser setada automaticamente
     * - No PUT: se vier null, mantemos a data antiga
     */
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
