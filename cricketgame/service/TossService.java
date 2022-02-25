package com.game.cricketgame.service;

import com.game.cricketgame.pojo.Toss;

public interface TossService {
  public void tossTheCoin();

  public String getTossWinner();

  public Toss getTossResult();

  public String getFirstBattingTeamName();

  public String getFirstBallingTeamName();

  public String getSecondBattingTeamName();

  public String getSecondBallingTeamName();
}
