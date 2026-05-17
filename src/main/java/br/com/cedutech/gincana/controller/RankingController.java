package br.com.cedutech.gincana.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cedutech.gincana.dto.RankingAlunoDTO;
import br.com.cedutech.gincana.dto.RankingTurmaDTO;
import br.com.cedutech.gincana.service.RankingService;

@RestController
@RequestMapping("/ranking")
@CrossOrigin
public class RankingController {

    private final RankingService service;

    public RankingController(RankingService service) {
        this.service = service;
    }

    @GetMapping("/alunos")
    public List<RankingAlunoDTO> rankingAlunos() {
        return service.rankingPorAluno();
    }

    @GetMapping("/turmas")
    public List<RankingTurmaDTO> rankingTurmas() {
        return service.rankingPorTurma();
    }

    @GetMapping("/vencedora")
    public RankingTurmaDTO turmaVencedora() {
        return service.turmaVencedora().orElse(null);
    }
}
