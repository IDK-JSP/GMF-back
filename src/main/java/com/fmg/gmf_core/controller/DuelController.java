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
    public ResponseEntity<List<DuelDto>> getAllDuel(){
        return ResponseEntity.ok(duelDao.findAllDuel());
    }

    @PostMapping("/new")
    public ResponseEntity<Duel> saveDuel(@RequestBody Duel duel){
        return ResponseEntity.ok(duelDao.save(duel));
    }
    @PostMapping("/vote")
    public ResponseEntity<Vote> saveVote(@RequestBody Vote vote){
        return ResponseEntity.ok(voteDao.save(vote));
    }
}
