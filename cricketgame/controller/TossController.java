package com.game.cricketgame.controller;

import com.game.cricketgame.pojo.Toss;
import com.game.cricketgame.service.TossService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TossController {
  private final TossService tossService;

  public TossController(TossService tossService) {
    this.tossService = tossService;
  }

  @PostMapping("/tossTheCoin")
  public String tossTheCoin() {
    tossService.tossTheCoin();
    return "Coin in the air";
  }

  @GetMapping("/getTossWinner")
  public String getTossWinner() {
    return tossService.getTossWinner();
  }

  @GetMapping("/getTossResult")
  public Toss getTossResult() {
    return tossService.getTossResult();
  }
}
