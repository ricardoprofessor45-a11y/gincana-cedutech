package br.com.cedutech.gincana.dto;

public class RankingAlunoDTO {

    private String nomeAluno;
    private String matricula;
    private String turma;
    private long totalCadastros;

    public RankingAlunoDTO(String nomeAluno, String matricula, String turma, long totalCadastros) {
        this.nomeAluno = nomeAluno;
        this.matricula = matricula;
        this.turma = turma;
        this.totalCadastros = totalCadastros;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getTurma() {
        return turma;
    }

    public long getTotalCadastros() {
        return totalCadastros;
    }
}

