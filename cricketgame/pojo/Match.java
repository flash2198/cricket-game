package com.game.cricketgame.pojo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "match")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
  @Id private Long id;
  private String firstTeamName;
  private String secondTeamName;
  private int numberOfOvers;
  private String winnerOfTheMatch;
  private String manOfTheMatch;
  private int highestRunsScored;
}
