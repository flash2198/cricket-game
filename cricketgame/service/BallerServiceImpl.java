package com.game.cricketgame.service;

import com.game.cricketgame.hepler.Teams;
import com.game.cricketgame.pojo.Baller;
import com.game.cricketgame.repository.BallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BallerServiceImpl implements BallerService {
  private final BallerRepository ballerRepository;

  @Autowired
  public BallerServiceImpl(BallerRepository ballerRepository) {
    this.ballerRepository = ballerRepository;
  }

  @Override
  public void saveFirstTeam() {
    List<Baller> list =
        List.of(
            new Baller(1L, "bhumra", Teams.firstTeamName, 0, 0, 0),
            new Baller(2L, "hardik", Teams.firstTeamName, 0, 0, 0),
            new Baller(3L, "Bkumar", Teams.firstTeamName, 0, 0, 0),
            new Baller(4L, "ishan", Teams.firstTeamName, 0, 0, 0));
    ballerRepository.saveAll(list);
  }

  @Override
  public void saveSecondTeam() {
    List<Baller> list =
        List.of(
            new Baller(5L, "moin", Teams.secondTeamName, 0, 0, 0),
            new Baller(6L, "nabeel", Teams.secondTeamName, 0, 0, 0),
            new Baller(7L, "maeen", Teams.secondTeamName, 0, 0, 0),
            new Baller(8L, "bashar", Teams.secondTeamName, 0, 0, 0),
            new Baller(9L, "Osama bin laden", Teams.secondTeamName, 0, 0, 0));
    ballerRepository.saveAll(list);
  }

  @Override
  public Iterable<Baller> findAll() {
    return ballerRepository.findAll();
  }

  @Override
  public Baller findById(Long id) {
    return ballerRepository.findById(id).orElse(null);
  }

  @Override
  public void deleteAllBallers() {
    ballerRepository.deleteAll();
  }

  @Override
  public Iterable<Baller> getBallerOfFirstBallingTeam(String firstBallingTeamName) {
    return ballerRepository.findByTeamName(firstBallingTeamName);
  }

  @Override
  public void savePerformance(List<Baller> firstBallingTeam) {
    ballerRepository.saveAll(firstBallingTeam);
  }

  @Override
  public Iterable<Baller> getBallersOfSecondBallingTeam(String secondBallingTeamName) {
    return ballerRepository.findByTeamName(secondBallingTeamName);
  }
}
