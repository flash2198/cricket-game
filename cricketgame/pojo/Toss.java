package com.game.cricketgame.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "toss")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Toss {
  @Id private Long id;
  private String firstBattingTeamName;
  private String secondBattingTeamName;
  private String firstBallingTeam;
  private String secondBallingTeam;
  private String tossWinnerTeam;
}
