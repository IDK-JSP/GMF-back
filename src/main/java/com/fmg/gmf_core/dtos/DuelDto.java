package com.fmg.gmf_core.dtos;

import com.fmg.gmf_core.entitys.Duel;
import com.fmg.gmf_core.entitys.Vote;
import org.springframework.stereotype.Component;

import java.util.List;


public class DuelDto {
    private Duel duel;
    private List<Vote> votes;
    private int totalVote;
    private int totalRightVote;

    public DuelDto(Duel duel, List<Vote> votes, int totalVote, int totalRightVote) {
        this.duel = duel;
        this.votes = votes;
        this.totalVote = totalVote;
        this.totalRightVote = totalRightVote;
    }

    public Duel getDuel() {
        return duel;
    }

    public void setDuel(Duel duel) {
        this.duel = duel;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public int getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(int totalVote) {
        this.totalVote = totalVote;
    }

    public int getTotalRightVote() {
        return totalRightVote;
    }

    public void setTotalRightVote(int totalRightVote) {
        this.totalRightVote = totalRightVote;
    }
}
