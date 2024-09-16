package com.cricket.score.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Score {
	/**
     * The unique ID of the match.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;
    
    /**
     * The name of Team A.
     */
    private String teamA;
    
    /**
     * The name of Team B.
     */
    private String teamB;
    

    /**
     * The current score of the match.
     */
    private String score;
    
    /**
     * The number of overs played.
     */
    private String overs;
    
    // Getters and Setters
    public Long getMatchId() {
        return matchId;
    }
    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
    public String getTeamA() {
        return teamA;
    }
    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }
    public String getTeamB() {
        return teamB;
    }
    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public String getOvers() {
        return overs;
    }
    public void setOvers(String overs) {
        this.overs = overs;
    }
}
