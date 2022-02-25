package com.game.cricketgame.service;

import com.game.cricketgame.hepler.Teams;
import com.game.cricketgame.pojo.Batsman;
import com.game.cricketgame.repository.BatsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatsmanServiceImpl implements BatsmanService {
  private final BatsmanRepository batsmanRepository;

  @Autowired
  public BatsmanServiceImpl(BatsmanRepository batsmanRepository) {
    this.batsmanRepository = batsmanRepository;
  }

  @Override
  public Batsman findById(Long id) {
    return batsmanRepository.findById(id).get();
  }

  @Override
  public Iterable<Batsman> findAll() {
    return batsmanRepository.findAll();
  }

  @Override
  public void saveFirstTeam() {
    List<Batsman> list =
        List.of(
            new Batsman(1L, "sachin", Teams.firstTeamName, 0, 0, 0.0d),
            new Batsman(2L, "virat", Teams.firstTeamName, 0, 0, 0.0d),
            new Batsman(3L, "dhoni", Teams.firstTeamName, 0, 0, 0.0d),
            new Batsman(4L, "dravid", Teams.firstTeamName, 0, 0, 0.0d),
            new Batsman(5L, "ganguly", Teams.firstTeamName, 0, 0, 0.0d),
            new Batsman(6L, "raina", Teams.firstTeamName, 0, 0, 0.0d),
            new Batsman(7L, "hardik", Teams.firstTeamName, 0, 0, 0.0d),
            new Batsman(8L, "bhumra", Teams.firstTeamName, 0, 0, 0.0d),
            new Batsman(9L, "Bkumar", Teams.firstTeamName, 0, 0, 0.0d),
            new Batsman(10L, "ishan", Teams.firstTeamName, 0, 0, 0.0d));
    batsmanRepository.saveAll(list);
  }

  @Override
  public void saveSecondTeam() {
    List<Batsman> list =
        List.of(
            new Batsman(11L, "babar", Teams.secondTeamName, 0, 0, 0.0d),
            new Batsman(12L, "inziham", Teams.secondTeamName, 0, 0, 0.0d),
            new Batsman(13L, "salman", Teams.secondTeamName, 0, 0, 0.0d),
            new Batsman(14L, "amir", Teams.secondTeamName, 0, 0, 0.0d),
            new Batsman(15L, "junaid", Teams.secondTeamName, 0, 0, 0.0d),
            new Batsman(16L, "moin", Teams.secondTeamName, 0, 0, 0.0d),
            new Batsman(17L, "nabeel", Teams.secondTeamName, 0, 0, 0.0d),
            new Batsman(18L, "maeen", Teams.secondTeamName, 0, 0, 0.0d),
            new Batsman(19L, "bashar", Teams.secondTeamName, 0, 0, 0.0d),
            new Batsman(20L, "Osama bin laden", Teams.secondTeamName, 0, 0, 0.0d));
    batsmanRepository.saveAll(list);
  }

  @Override
  public void deleteBatsman() {
    batsmanRepository.deleteAll();
  }

  @Override
  public Iterable<Batsman> getBatsmanOfFirstBattingTeam(String teamName) {
    return batsmanRepository.findByTeamName(teamName);
  }

  @Override
  public void savePerformance(List<Batsman> firstBattingTeam) {
    batsmanRepository.saveAll(firstBattingTeam);
  }

  @Override
  public Iterable<Batsman> getBatsmanOfSecondBattingTeam(String secondBattingTeamName) {
    return batsmanRepository.findByTeamName(secondBattingTeamName);
  }
}
