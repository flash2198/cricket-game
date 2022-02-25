package com.game.cricketgame.controller;

import com.game.cricketgame.pojo.Batsman;
import com.game.cricketgame.service.BatsmanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class BatsmanController {
  private final BatsmanService batsmanService;

  @Autowired
  public BatsmanController(BatsmanService batsmanService) {
    this.batsmanService = batsmanService;
  }

  @PostMapping("/saveFirstTeamBatsman")
  public String saveFirstTeam() {
    batsmanService.saveFirstTeam();
    return "Successfully stored";
  }

  @PostMapping("/saveSecondTeamBatsman")
  public String saveSecondTeam() {
    batsmanService.saveSecondTeam();
    return "Successfully stored";
  }

  @GetMapping("/batsman/{id}")
  public Batsman findById(@PathVariable("id") Long id) {
    return batsmanService.findById(id);
  }

  @GetMapping("/batsman")
  public Iterable<Batsman> findAll() {
    return batsmanService.findAll();
  }

  @DeleteMapping("/deleteBatsman")
  public String deleteBatsman() {
    batsmanService.deleteBatsman();
    return "Deleted successfully";
  }
}
