package com.cricket.score.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cricket.score.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
