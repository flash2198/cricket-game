package com.game.cricketgame.service;

import com.game.cricketgame.pojo.Match;

public interface MatchService {
  public void saveMatch();

  public String getManOfTheMatch();

  public String getWinnerTeam();

  public Match getMatch();

  public void startMatch();
}
