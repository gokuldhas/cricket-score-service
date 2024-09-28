package com.cricket.score;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the ScoreController API.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CricketScoreServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests fetching live score by valid match ID.
     * 
     * @throws Exception If the request fails.
     */
    @Test
    @Order(3)
    public void shouldReturnLiveScore() throws Exception {
        // Precondition: Ensure there is a score with matchId 1 in your database or through a data.sql script
        mockMvc.perform(get("/api/scores/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.teamA").value("India"))
            .andExpect(jsonPath("$.teamB").value("Australia"))
            .andDo(print());
    }

    /**
     * Tests whether a 404 is returned for an invalid match ID.
     * 
     * @throws Exception If the request fails.
     */
    @Test
    @Order(2)
    public void shouldReturnNotFoundForInvalidMatchId() throws Exception {
    	 String updatedScore = "{\"teamA\":\"India\",\"teamB\":\"Australia\",\"score\":\"300/6\",\"overs\":\"45.0\"}";

         mockMvc.perform(put("/api/scores/999")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(updatedScore))
             .andExpect(status().isNotFound())  // Expecting HTTP 404 Not Found
             .andDo(print());
    }

    /**
     * Tests fetching all live scores.
     * 
     * @throws Exception If the request fails.
     */
    @Test
    @Order(6)
    public void shouldReturnAllScores() throws Exception {
        mockMvc.perform(get("/api/scores"))
            .andExpect(status().isOk())          // Expecting HTTP 200 OK
            .andExpect(jsonPath("$.length()").isNotEmpty())  // Ensure that scores list is not empty
            .andDo(print());
    }

    /**
     * Tests creating a new score.
     * 
     * @throws Exception If the request fails.
     */
    @Test
    @Order(1)
    public void shouldCreateNewScore() throws Exception {
        // Test data for creating a new score
        String newScore = "{\"teamA\":\"Pakistan\",\"teamB\":\"New Zealand\",\"score\":\"250/5\",\"overs\":\"40.3\"}";

        mockMvc.perform(post("/api/scores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newScore))
            .andExpect(status().isOk())          // Expecting HTTP 200 OK on successful creation
            .andExpect(jsonPath("$.teamA").value("Pakistan"))  // Verifying the new entry's data
            .andExpect(jsonPath("$.teamB").value("New Zealand"))
            .andExpect(jsonPath("$.score").value("250/5"))
            .andExpect(jsonPath("$.overs").value("40.3"))
            .andDo(print());
    }

    /**
     * Tests updating an existing score by match ID.
     * 
     * @throws Exception If the request fails.
     */
    @Test
    @Order(4)
    public void shouldUpdateScore() throws Exception {
        String newScore = "{\"teamA\":\"India\",\"teamB\":\"Australia\",\"score\":\"300/6\",\"overs\":\"45.0\"}";

        mockMvc.perform(put("/api/scores/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newScore))
            .andExpect(status().isOk())          // Expecting HTTP 200 OK
            .andExpect(jsonPath("$.teamA").value("India"))  // Verifying the updated entry's data
            .andExpect(jsonPath("$.teamB").value("Australia"))
            .andExpect(jsonPath("$.score").value("300/6"))
            .andExpect(jsonPath("$.overs").value("45.0"))
            .andDo(print());
    }

    /**
     * Tests deleting an existing score by match ID.
     * 
     * @throws Exception If the request fails.
     */
    @Test
    @Order(5)
    public void shouldDeleteScore() throws Exception {
        // Ensure there is a score with matchId 1
        mockMvc.perform(delete("/api/scores/1"))
            .andExpect(status().isNoContent())  // Expecting HTTP 204 No Content after successful deletion
            .andDo(print());
    }
}
