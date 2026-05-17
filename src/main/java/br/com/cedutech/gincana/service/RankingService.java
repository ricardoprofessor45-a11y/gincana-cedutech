package br.com.cedutech.gincana.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.cedutech.gincana.dto.RankingAlunoDTO;
import br.com.cedutech.gincana.dto.RankingTurmaDTO;
import br.com.cedutech.gincana.model.Cadastro;
import br.com.cedutech.gincana.repository.CadastroRepository;

@Service
public class RankingService {

    private final CadastroRepository repository = new CadastroRepository();

    // 🔹 Ranking por aluno
    public List<RankingAlunoDTO> rankingPorAluno() {

        List<Cadastro> cadastros = repository.findAll();

        Map<String, List<Cadastro>> porAluno = cadastros.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getAluno().getMatricula()
                ));

        List<RankingAlunoDTO> ranking = new ArrayList<>();

        for (List<Cadastro> lista : porAluno.values()) {
            Cadastro c = lista.get(0);
            ranking.add(new RankingAlunoDTO(
                    c.getAluno().getNome(),
                    c.getAluno().getMatricula(),
                    c.getAluno().getTurma(),
                    lista.size()
            ));
        }

        ranking.sort(Comparator.comparingLong(RankingAlunoDTO::getTotalCadastros).reversed());
        return ranking;
    }

    // 🔹 Ranking por turma
    public List<RankingTurmaDTO> rankingPorTurma() {

        List<Cadastro> cadastros = repository.findAll();

        Map<String, Long> totalPorTurma = cadastros.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getAluno().getTurma(),
                        Collectors.counting()
                ));

        return totalPorTurma.entrySet().stream()
                .map(e -> new RankingTurmaDTO(e.getKey(), e.getValue()))
                .sorted(Comparator.comparingLong(RankingTurmaDTO::getTotalCadastros).reversed())
                .collect(Collectors.toList());
    }

    // 🔹 Turma vencedora
    public Optional<RankingTurmaDTO> turmaVencedora() {
        return rankingPorTurma().stream().findFirst();
    }
}
