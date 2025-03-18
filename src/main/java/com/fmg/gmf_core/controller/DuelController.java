package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.DuelDao;
import com.fmg.gmf_core.daos.VoteDao;
import com.fmg.gmf_core.dtos.DuelDto;
import com.fmg.gmf_core.entitys.Duel;
import com.fmg.gmf_core.entitys.Vote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/duel")
public class DuelController {
    private final DuelDao duelDao;
    private final VoteDao voteDao;

    public DuelController(DuelDao duelDao, VoteDao voteDao) {
        this.duelDao = duelDao;
        this.voteDao = voteDao;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Duel>> getAllDuel(){
        return ResponseEntity.ok(duelDao.findAllDuel());
    }
    @GetMapping("/details/{id_duel}")
    public ResponseEntity<DuelDto> getDuelDetails(@PathVariable("id_duel") int id){
        // Créer une nouvelle instance de DuelDto
        Duel duel = duelDao.findDuelById(id);
        List<Vote> votes = voteDao.getAllDuelVote(id);
        int totalVote = voteDao.totalDuelVote(id);
        int totalRightVote = voteDao.totalDuelRightVote(id);

        // Créer le DTO et le retourner
        DuelDto duelDto = new DuelDto(duel, votes, totalVote, totalRightVote);
        return ResponseEntity.ok(duelDto);
    }
    @PostMapping("/new")
    public ResponseEntity<Duel> saveDuel(@RequestBody Duel duel){
        return ResponseEntity.ok(duelDao.save(duel));
    }
}
