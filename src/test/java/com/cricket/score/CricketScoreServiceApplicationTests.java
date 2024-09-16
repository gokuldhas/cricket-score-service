package com.cricket.score;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
     * Tests whether the live score is returned for a valid match ID.
     * 
     * @throws Exception If the request fails.
     */
    @Test
    public void shouldReturnLiveScore() throws Exception {
        mockMvc.perform(get("/api/scores/1"))
            .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFoundForInvalidMatchId() throws Exception {
        mockMvc.perform(get("/api/scores/999"))  // Using a non-existent matchId
            .andExpect(status().isNotFound());   // Expecting 404 Not Found
    }
}
