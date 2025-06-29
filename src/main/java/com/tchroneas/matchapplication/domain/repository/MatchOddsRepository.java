package com.tchroneas.matchapplication.domain.repository;

import com.tchroneas.matchapplication.domain.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {

    List<MatchOdds> findByMatch_Id(Long matchId);
}
