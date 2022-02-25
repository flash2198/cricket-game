package com.game.cricketgame.pojo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "batsman")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batsman {
  @Id private Long id;
  private String name;
  private String teamName;
  private int runsScored;
  public int ballsPlayed;
  private double strikeRate;
}
