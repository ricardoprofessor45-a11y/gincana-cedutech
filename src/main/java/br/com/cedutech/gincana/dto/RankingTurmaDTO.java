package br.com.cedutech.gincana.dto;

public class RankingTurmaDTO {

    private String turma;
    private long totalCadastros;

    public RankingTurmaDTO(String turma, long totalCadastros) {
        this.turma = turma;
        this.totalCadastros = totalCadastros;
    }

    public String getTurma() {
        return turma;
    }

    public long getTotalCadastros() {
        return totalCadastros;
    }
}
