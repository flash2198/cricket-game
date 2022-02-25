package com.game.cricketgame.repository;

import com.game.cricketgame.pojo.Baller;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallerRepository extends ElasticsearchRepository<Baller, Long> {
  Iterable<Baller> findByTeamName(String firstBallingTeamName);
}
