package com.game.cricketgame.service;

import com.game.cricketgame.pojo.Batsman;

import java.util.List;

public interface BatsmanService {

  public Batsman findById(Long id);

  public Iterable<Batsman> findAll();

  public void saveFirstTeam();

  public void saveSecondTeam();

  public void deleteBatsman();

  public Iterable<Batsman> getBatsmanOfFirstBattingTeam(String teamName);

  public void savePerformance(List<Batsman> firstBattingTeam);

  public Iterable<Batsman> getBatsmanOfSecondBattingTeam(String secondBattingTeamName);
}
