package com.cricket.score.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cricket.score.model.Score;
import com.cricket.score.repository.ScoreRepository;
import com.cricket.score.exception.MatchNotFoundException;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    /**
     * Retrieves all scores.
     * 
     * @return A list of all scores in the database.
     */
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    /**
     * Retrieves a score by its match ID.
     * 
     * @param matchId The unique ID of the match.
     * @return The score of the specified match.
     * @throws MatchNotFoundException If the match with the given ID is not found.
     */
    public Score getScoreByMatchId(Long matchId) {
        return scoreRepository.findById(matchId)
            .orElseThrow(() -> new MatchNotFoundException("Match not found"));
    }
    
    /**
     * Creates a new score entry.
     * 
     * @param score The score object to be created.
     * @return The saved score object.
     */
    public Score createScore(Score score) {
        return scoreRepository.save(score);
    }

    /**
     * Updates the score of a match.
     * 
     * @param matchId The ID of the match to update.
     * @param scoreDetails The updated score details.
     * @return The updated score object.
     * @throws MatchNotFoundException If the match with the given ID is not found.
     */
    public Score updateScore(Long matchId, Score scoreDetails) {
        Score score = scoreRepository.findById(matchId)
            .orElseThrow(() -> new MatchNotFoundException("Match not found"));
        score.setScore(scoreDetails.getScore());
        score.setOvers(scoreDetails.getOvers());
        return scoreRepository.save(score);
    }

    /**
     * Deletes a score entry by its match ID.
     * 
     * @param matchId The ID of the match to delete.
     */
    public void deleteScore(Long matchId) {
        scoreRepository.deleteById(matchId);
    }
}
