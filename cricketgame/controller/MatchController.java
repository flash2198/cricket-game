package com.game.cricketgame.controller;

import com.game.cricketgame.pojo.Match;
import com.game.cricketgame.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MatchController {
  private final MatchService matchService;

  @Autowired
  public MatchController(MatchService matchService) {
    this.matchService = matchService;
  }

  @PostMapping("/saveMatch")
  public String saveMatch() {
    matchService.saveMatch();
    return "Successfully stored";
  }

  @GetMapping("/match/getManOfTheMatch")
  public String getManOfTheMatch() {
    return matchService.getManOfTheMatch();
  }

  @GetMapping("/match/getWinnerTeam")
  public String getWinnerTeam() {
    return matchService.getWinnerTeam();
  }

  @GetMapping("/match")
  public Match getMatch() {
    return matchService.getMatch();
  }

  @PostMapping("/startTheMatch")
  public String startMatch() {
    matchService.startMatch();
    return "Match Has Started";
  }
}
