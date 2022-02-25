package com.game.cricketgame.repository;

import com.game.cricketgame.pojo.Toss;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TossRepository extends ElasticsearchRepository<Toss, Long> {}
