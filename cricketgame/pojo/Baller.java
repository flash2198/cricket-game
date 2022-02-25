package com.game.cricketgame.pojo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "baller")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Baller {
  @Id private Long id;
  private String name;
  private String teamName;
  private int overBalled;
  private int runsGiven;
  private int wicketsTaken;
}
