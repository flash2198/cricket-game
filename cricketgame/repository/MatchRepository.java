package com.game.cricketgame.repository;

import com.game.cricketgame.pojo.Match;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends ElasticsearchRepository<Match, Long> {}
