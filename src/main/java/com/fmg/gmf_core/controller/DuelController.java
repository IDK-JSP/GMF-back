package com.fmg.gmf_core.controller;

import com.fmg.gmf_core.daos.DuelDao;
import com.fmg.gmf_core.daos.VoteDao;
import com.fmg.gmf_core.dtos.DuelDto;
import com.fmg.gmf_core.entitys.Duel;
import com.fmg.gmf_core.entitys.Vote;
import com.fmg.gmf_core.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/duel")
public class DuelController {
    private final DuelDao duelDao;
    private final VoteDao voteDao;
    private final JwtUtil jwtUtil;

    public DuelController(DuelDao duelDao, VoteDao voteDao, JwtUtil jwtUtil) {
        this.duelDao = duelDao;
        this.voteDao = voteDao;
        this.jwtUtil = jwtUtil;
    }
    @GetMapping("/all")
    public ResponseEntity<List<DuelDto>> getAllDuel(){
        return ResponseEntity.ok(duelDao.findAllDuel());
    }

    @PostMapping("/new")
    public ResponseEntity<Duel> saveDuel(@RequestBody Duel duel, @RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
        String email = jwtUtil.getEmailFromToken(token);
        duel.setEmail(email);
        return ResponseEntity.ok(duelDao.save(duel));
    }
    @PostMapping("/vote")
    public ResponseEntity<Vote> saveVote(@RequestBody Vote vote, @RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);  // Supprime "Bearer " (7 caractères)
        String email = jwtUtil.getEmailFromToken(token);
        vote.setEmail(email);
        return ResponseEntity.ok(voteDao.save(vote));
    }
}
