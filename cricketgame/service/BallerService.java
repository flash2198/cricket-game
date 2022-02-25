package com.game.cricketgame.service;

import com.game.cricketgame.pojo.Baller;

import java.util.List;

public interface BallerService {
  public void saveFirstTeam();

  public void saveSecondTeam();

  public Iterable<Baller> findAll();

  public Baller findById(Long id);

  public void deleteAllBallers();

  public Iterable<Baller> getBallerOfFirstBallingTeam(String firstBallingTeamName);

  public void savePerformance(List<Baller> firstBallingTeam);

  public Iterable<Baller> getBallersOfSecondBallingTeam(String secondBallingTeamName);
}
