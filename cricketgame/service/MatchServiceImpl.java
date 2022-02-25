package com.game.cricketgame.service;

import com.game.cricketgame.helper.Teams;
import com.game.cricketgame.helper.UtilClass;
import com.game.cricketgame.pojo.Baller;
import com.game.cricketgame.pojo.Batsman;
import com.game.cricketgame.pojo.Match;
import com.game.cricketgame.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchServiceImpl implements MatchService {
  private final BatsmanService batsmanService;
  private final BallerService ballerService;
  private final TossService tossService;
  private final MatchRepository matchRepository;

  @Autowired
  public MatchServiceImpl(
      BatsmanService batsmanService,
      BallerService ballerService,
      TossService tossService,
      MatchRepository matchRepository) {
    this.batsmanService = batsmanService;
    this.ballerService = ballerService;
    this.tossService = tossService;
    this.matchRepository = matchRepository;
  }

  @Override
  public void saveMatch() {
    Match match =
        new Match(
            1L, Teams.firstTeamName, Teams.secondTeamName, Teams.numberOfOvers, null, null, 0);
    matchRepository.save(match);
  }

  @Override
  public String getManOfTheMatch() {
    Match match = matchRepository.findById(1L).orElse(null);
    return match.getManOfTheMatch();
  }

  @Override
  public String getWinnerTeam() {
    Match match = matchRepository.findById(1L).orElse(null);
    return match.getWinnerOfTheMatch();
  }

  @Override
  public Match getMatch() {
    return matchRepository.findById(1L).orElse(null);
  }

  @Override
  public void startMatch() {
    int runsScoredByFirstTeam = startFirstInnings();
    int runsScoredBySecondTeam = startSecondInnings(runsScoredByFirstTeam);
    Match match = matchRepository.findById(1L).orElse(null);
    if (runsScoredByFirstTeam > runsScoredBySecondTeam) {
      match.setWinnerOfTheMatch(tossService.getFirstBattingTeamName());
    } else if (runsScoredByFirstTeam < runsScoredBySecondTeam) {
      match.setWinnerOfTheMatch(tossService.getSecondBattingTeamName());
    } else {
      match.setWinnerOfTheMatch("DRAW");
    }
    matchRepository.save(match);
  }

  private int startSecondInnings(int target) {
    Match match = matchRepository.findById(1L).orElse(null);
    int totalRunsScored = 0;
    List<Batsman> secondBattingTeam = getSecondBattingTeam();
    List<Baller> secondBallingTeam = getSecondBallingTeam();
    int runsScoredByPlayer = 0;
    int ballsPlayedByPlayer = 0;
    int wicketsLeft = 10;
    int runsScoredInOver = 0;
    int wicketsInOver = 0;
    int numberOfBallers = secondBallingTeam.size();
    int ballersIndex = 0;
    int battersIndex = 0;
    for (int i = 0; i < (Teams.numberOfOvers * 6) + 1; i++) {
      if (totalRunsScored > target) {
        if (runsScoredByPlayer > match.getHighestRunsScored()) {
          match.setHighestRunsScored(runsScoredByPlayer);
          match.setManOfTheMatch(secondBattingTeam.get(battersIndex).getName());
        }
        matchRepository.save(match);
        secondBattingTeam.get(battersIndex).setRunsScored(runsScoredByPlayer);
        secondBattingTeam.get(battersIndex).setBallsPlayed(ballsPlayedByPlayer);
        secondBattingTeam
            .get(battersIndex)
            .setStrikeRate((runsScoredByPlayer / (double) ballsPlayedByPlayer) * 100);
        secondBallingTeam
            .get(ballersIndex)
            .setOverBalled(secondBallingTeam.get(ballersIndex).getOverBalled() + 1);
        secondBallingTeam
            .get(ballersIndex)
            .setWicketsTaken(secondBallingTeam.get(ballersIndex).getWicketsTaken() + wicketsInOver);
        secondBallingTeam
            .get(ballersIndex)
            .setRunsGiven(secondBallingTeam.get(ballersIndex).getRunsGiven() + runsScoredInOver);
        batsmanService.savePerformance(secondBattingTeam);
        ballerService.savePerformance(secondBallingTeam);

        return totalRunsScored;
      }
      int giveRandomNumber = UtilClass.random.nextInt(8);
      ballsPlayedByPlayer += 1;
      if (giveRandomNumber == 7) {
        if (runsScoredByPlayer > match.getHighestRunsScored()) {
          match.setHighestRunsScored(runsScoredByPlayer);
          match.setManOfTheMatch(secondBattingTeam.get(battersIndex).getName());
        }
        secondBattingTeam.get(battersIndex).setRunsScored(runsScoredByPlayer);
        secondBattingTeam.get(battersIndex).setBallsPlayed(ballsPlayedByPlayer);
        secondBattingTeam
            .get(battersIndex)
            .setStrikeRate((runsScoredByPlayer / (double) ballsPlayedByPlayer) * 100);
        wicketsInOver += 1;
        battersIndex += 1;
        wicketsLeft -= 1;
        runsScoredByPlayer = 0;
        ballsPlayedByPlayer = 0;
      } else {
        runsScoredByPlayer += giveRandomNumber;
        runsScoredInOver += giveRandomNumber;
        totalRunsScored += giveRandomNumber;
      }
      if (wicketsLeft == 0) {
        secondBallingTeam
            .get(ballersIndex)
            .setOverBalled(secondBallingTeam.get(ballersIndex).getOverBalled() + 1);
        secondBallingTeam
            .get(ballersIndex)
            .setRunsGiven(secondBallingTeam.get(ballersIndex).getRunsGiven() + runsScoredInOver);
        secondBallingTeam
            .get(ballersIndex)
            .setWicketsTaken(secondBallingTeam.get(ballersIndex).getWicketsTaken() + wicketsInOver);
        batsmanService.savePerformance(secondBattingTeam);
        ballerService.savePerformance(secondBallingTeam);
        return totalRunsScored;
      }

      if (i % 6 == 0) {
        secondBallingTeam
            .get(ballersIndex)
            .setOverBalled(secondBallingTeam.get(ballersIndex).getOverBalled() + 1);
        secondBallingTeam
            .get(ballersIndex)
            .setRunsGiven(secondBallingTeam.get(ballersIndex).getRunsGiven() + runsScoredInOver);
        secondBallingTeam
            .get(ballersIndex)
            .setWicketsTaken(secondBallingTeam.get(ballersIndex).getWicketsTaken() + wicketsInOver);
        runsScoredInOver = 0;
        wicketsInOver = 0;
        ballersIndex = (battersIndex + 1) % numberOfBallers;
      }
    }
    if (runsScoredByPlayer != 0) {
      secondBattingTeam.get(battersIndex).setRunsScored(runsScoredByPlayer);
      secondBattingTeam.get(battersIndex).setBallsPlayed(ballsPlayedByPlayer);
      secondBattingTeam
          .get(battersIndex)
          .setStrikeRate((runsScoredByPlayer / (double) ballsPlayedByPlayer) * 100);
      secondBallingTeam
          .get(ballersIndex)
          .setOverBalled(secondBallingTeam.get(ballersIndex).getWicketsTaken() + wicketsInOver);
      secondBallingTeam
          .get(ballersIndex)
          .setRunsGiven(secondBallingTeam.get(ballersIndex).getRunsGiven() + runsScoredInOver);
      if (runsScoredByPlayer > match.getHighestRunsScored()) {
        match.setHighestRunsScored(runsScoredByPlayer);
        match.setManOfTheMatch(secondBattingTeam.get(battersIndex).getName());
      }
      matchRepository.save(match);
    }
    matchRepository.save(match);
    batsmanService.savePerformance(secondBattingTeam);
    ballerService.savePerformance(secondBallingTeam);
    return totalRunsScored;
  }

  private List<Baller> getSecondBallingTeam() {
    String secondBallingTeamName = tossService.getSecondBallingTeamName();
    Iterable<Baller> ballerIterable =
        ballerService.getBallersOfSecondBallingTeam(secondBallingTeamName);
    return UtilClass.iterableToCollection(ballerIterable);
  }

  private List<Batsman> getSecondBattingTeam() {
    String secondBattingTeamName = tossService.getSecondBattingTeamName();
    Iterable<Batsman> batsmanIterable =
        batsmanService.getBatsmanOfSecondBattingTeam(secondBattingTeamName);
    return UtilClass.iterableToCollection(batsmanIterable);
  }

  private int startFirstInnings() {
    Match match = matchRepository.findById(1L).orElse(null);
    int totalRunsScored = 0;
    List<Batsman> firstBattingTeam = getFirstBattingTeam();
    List<Baller> firstBallingTeam = getFirstBallingTeam();
    int runsScoredByPlayer = 0;
    int ballsPlayedByPlayer = 0;
    int wicketsLeft = 10;
    int runsScoredInOver = 0;
    int wicketsInOver = 0;
    int numberOfBallers = firstBallingTeam.size();
    int ballersIndex = 0;
    int battersIndex = 0;
    for (int i = 1; i < (Teams.numberOfOvers * 6) + 1; i++) {
      int giveRandomNumber = UtilClass.random.nextInt(8);
      ballsPlayedByPlayer += 1;
      if (giveRandomNumber == 7) {
        if (runsScoredByPlayer > match.getHighestRunsScored()) {
          match.setHighestRunsScored(runsScoredByPlayer);
          match.setManOfTheMatch(firstBattingTeam.get(battersIndex).getName());
        }
        matchRepository.save(match);
        firstBattingTeam.get(battersIndex).setRunsScored(runsScoredByPlayer);
        firstBattingTeam.get(battersIndex).setBallsPlayed(ballsPlayedByPlayer);
        firstBattingTeam
            .get(battersIndex)
            .setStrikeRate((runsScoredByPlayer / (double) ballsPlayedByPlayer) * 100);
        wicketsInOver += 1;
        battersIndex += 1;
        wicketsLeft -= 1;
        runsScoredByPlayer = 0;
        ballsPlayedByPlayer = 0;
      } else {
        runsScoredByPlayer += giveRandomNumber;
        runsScoredInOver += giveRandomNumber;
        totalRunsScored += giveRandomNumber;
      }
      if (wicketsLeft == 0) {
        firstBallingTeam
            .get(ballersIndex)
            .setOverBalled(firstBallingTeam.get(ballersIndex).getOverBalled() + 1);
        firstBallingTeam
            .get(ballersIndex)
            .setRunsGiven(firstBallingTeam.get(ballersIndex).getRunsGiven() + runsScoredInOver);
        firstBallingTeam
            .get(ballersIndex)
            .setWicketsTaken(firstBallingTeam.get(ballersIndex).getWicketsTaken() + wicketsInOver);
        batsmanService.savePerformance(firstBattingTeam);
        ballerService.savePerformance(firstBallingTeam);
        return totalRunsScored;
      }

      if (i % 6 == 0) {
        firstBallingTeam
            .get(ballersIndex)
            .setOverBalled(firstBallingTeam.get(ballersIndex).getOverBalled() + 1);
        firstBallingTeam
            .get(ballersIndex)
            .setRunsGiven(firstBallingTeam.get(ballersIndex).getRunsGiven() + runsScoredInOver);
        firstBallingTeam
            .get(ballersIndex)
            .setWicketsTaken(firstBallingTeam.get(ballersIndex).getWicketsTaken() + wicketsInOver);
        runsScoredInOver = 0;
        wicketsInOver = 0;
        ballersIndex = (battersIndex + 1) % numberOfBallers;
      }
    }
    if (runsScoredByPlayer != 0) {
      firstBattingTeam.get(battersIndex).setRunsScored(runsScoredByPlayer);
      firstBattingTeam.get(battersIndex).setBallsPlayed(ballsPlayedByPlayer);
      firstBattingTeam
          .get(battersIndex)
          .setStrikeRate((runsScoredByPlayer / (double) ballsPlayedByPlayer) * 100);
      firstBallingTeam
          .get(ballersIndex)
          .setOverBalled(firstBallingTeam.get(ballersIndex).getWicketsTaken() + wicketsInOver);
      firstBallingTeam
          .get(ballersIndex)
          .setRunsGiven(firstBallingTeam.get(ballersIndex).getRunsGiven() + runsScoredInOver);
      if (runsScoredByPlayer > match.getHighestRunsScored()) {
        match.setHighestRunsScored(runsScoredByPlayer);
        match.setManOfTheMatch(firstBattingTeam.get(battersIndex).getName());
      }
      matchRepository.save(match);
    }
    matchRepository.save(match);
    batsmanService.savePerformance(firstBattingTeam);
    ballerService.savePerformance(firstBallingTeam);
    return totalRunsScored;
  }

  private List<Baller> getFirstBallingTeam() {
    String firstBallingTeamName = tossService.getFirstBallingTeamName();
    Iterable<Baller> ballerIterable =
        ballerService.getBallerOfFirstBallingTeam(firstBallingTeamName);
    return UtilClass.iterableToCollection(ballerIterable);
  }

  private List<Batsman> getFirstBattingTeam() {
    String firstBattingTeamName = tossService.getFirstBattingTeamName();
    Iterable<Batsman> batsmanIterable =
        batsmanService.getBatsmanOfFirstBattingTeam(firstBattingTeamName);
    return UtilClass.iterableToCollection(batsmanIterable);
  }
}
