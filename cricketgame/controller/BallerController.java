package com.game.cricketgame.controller;

import com.game.cricketgame.pojo.Baller;
import com.game.cricketgame.service.BallerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BallerController {
  private final BallerService ballerService;

  @Autowired
  public BallerController(BallerService ballerService) {
    this.ballerService = ballerService;
  }

  @PostMapping("/saveFirstTeamBallers")
  public String saveFirstTeam() {
    ballerService.saveFirstTeam();
    return "Successfully stored";
  }

  @PostMapping("/saveSecondTeamBallers")
  public String saveSecondTeam() {
    ballerService.saveSecondTeam();
    return "Successfully stored";
  }

  @GetMapping("/baller")
  public Iterable<Baller> findAll() {
    return ballerService.findAll();
  }

  @GetMapping("/baller/{id}")
  public Baller findById(@PathVariable("id") Long id) {
    return ballerService.findById(id);
  }

  @DeleteMapping("/deleteBallers")
  public String deleteAllBallers() {
    ballerService.deleteAllBallers();
    return "Deleted successfully";
  }
}
