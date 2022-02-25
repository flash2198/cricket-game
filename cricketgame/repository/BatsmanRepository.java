package com.game.cricketgame.repository;

import com.game.cricketgame.pojo.Batsman;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatsmanRepository extends ElasticsearchRepository<Batsman, Long> {
  Iterable<Batsman> findByTeamName(String teamName);
}
