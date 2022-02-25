package com.game.cricketgame.service;

import com.game.cricketgame.helper.Teams;
import com.game.cricketgame.helper.UtilClass;
import com.game.cricketgame.pojo.Toss;
import com.game.cricketgame.repository.TossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TossServiceImpl implements TossService {
  private final TossRepository tossRepository;

  @Autowired
  public TossServiceImpl(TossRepository tossRepository) {
    this.tossRepository = tossRepository;
  }

  @Override
  public void tossTheCoin() {
    int giveRandomNumber = UtilClass.random.nextInt(2);
    Toss toss = new Toss();
    toss.setId(1L);

    if (giveRandomNumber == 0) {
      toss.setTossWinnerTeam(Teams.firstTeamName);
    } else {
      toss.setTossWinnerTeam(Teams.secondTeamName);
    }

    giveRandomNumber = UtilClass.random.nextInt(2);
    if (giveRandomNumber == 0) {
      toss.setFirstBattingTeamName(Teams.firstTeamName);
      toss.setSecondBattingTeamName(Teams.secondTeamName);
      toss.setFirstBallingTeam(Teams.secondTeamName);
      toss.setSecondBallingTeam(Teams.firstTeamName);
    } else {
      toss.setFirstBattingTeamName(Teams.secondTeamName);
      toss.setSecondBattingTeamName(Teams.firstTeamName);
      toss.setFirstBallingTeam(Teams.firstTeamName);
      toss.setSecondBallingTeam(Teams.secondTeamName);
    }
    tossRepository.save(toss);
  }

  @Override
  public String getTossWinner() {
    Toss toss = tossRepository.findById(1L).orElse(null);
    return toss.getTossWinnerTeam();
  }

  @Override
  public Toss getTossResult() {
    return tossRepository.findById(1L).orElse(null);
  }

  @Override
  public String getFirstBattingTeamName() {
    Toss toss = tossRepository.findById(1L).orElse(null);
    return toss.getFirstBattingTeamName();
  }

  @Override
  public String getFirstBallingTeamName() {
    Toss toss = tossRepository.findById(1L).orElse(null);
    return toss.getFirstBallingTeam();
  }

  @Override
  public String getSecondBattingTeamName() {
    Toss toss = tossRepository.findById(1L).orElse(null);
    return toss.getSecondBattingTeamName();
  }

  @Override
  public String getSecondBallingTeamName() {
    Toss toss = tossRepository.findById(1L).orElse(null);
    return toss.getSecondBallingTeam();
  }
}
