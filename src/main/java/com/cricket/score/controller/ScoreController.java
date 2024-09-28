package com.cricket.score.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cricket.score.model.Score;
import com.cricket.score.service.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import com.cricket.score.exception.MatchNotFoundException;

/**
 * REST controller that exposes APIs for managing cricket scores.
 */
@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "http://localhost:8080")  // Allow CORS for this controller
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    /**
     * Retrieves a list of all scores.
     * 
     * @return A list of scores.
     */
    @Operation(summary = "Get all live scores", description = "Retrieve a list of all live scores")
    @GetMapping
    public List<Score> getAllScores() {
        return scoreService.getAllScores();
    }

    /**
     * Retrieves the score by match ID.
     * 
     * @param matchId The ID of the match.
     * @return The score for the match.
     */
    @Operation(summary = "Get score by match ID", description = "Retrieve score for a match by its ID")
    @GetMapping("/{matchId}")
    public ResponseEntity<Score> getScoreByMatchId(@PathVariable Long matchId) {
        return ResponseEntity.ok(scoreService.getScoreByMatchId(matchId));
    }

    /**
     * Creates a new score entry.
     * 
     * @param score The score object.
     * @return The created score.
     */
    @Operation(summary = "Create a new score entry", description = "Create a new score entry for a match")
    @PostMapping
    public ResponseEntity<Score> createScore(@RequestBody Score score) {
        return ResponseEntity.ok(scoreService.createScore(score));
    }

    /**
     * Updates the score by match ID.
     * 
     * @param matchId The ID of the match.
     * @param scoreDetails The new score details.
     * @return The updated score.
     */
    @Operation(summary = "Update score by match ID", description = "Update the score of a match by its ID")
    @PutMapping("/{matchId}")
    public ResponseEntity<Score> updateScore(@PathVariable Long matchId, @RequestBody Score scoreDetails) {
        try {
            Score updatedScore = scoreService.updateScore(matchId, scoreDetails);
            return ResponseEntity.ok(updatedScore);
        } catch (MatchNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Return 404 if the match is not found
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // General error handling
        }
    }


    /**
     * Deletes a score by match ID.
     * 
     * @param matchId The ID of the match to delete.
     * @return No content.
     */
    @Operation(summary = "Delete score by match ID", description = "Delete the score entry of a match by its ID")
    @DeleteMapping("/{matchId}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long matchId) {
        scoreService.deleteScore(matchId);
        return ResponseEntity.noContent().build();
    }

    // Exception handler for MatchNotFoundException
    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<String> handleMatchNotFoundException(MatchNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
