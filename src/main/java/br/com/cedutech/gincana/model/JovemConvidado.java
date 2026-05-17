package br.com.cedutech.gincana.model;

public class JovemConvidado {

    private String nome;
    private int idade;
    private String periodo; // MANHA, TARDE, NOITE
    private String telefone;
    private boolean excecaoIdade;

    public JovemConvidado() {}

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean isExcecaoIdade() {
        return excecaoIdade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
        this.excecaoIdade = idade < 13 || idade > 18;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
