package edu.penzgtu.oop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Publisher {
  private int id;
  private String name;
  private int year;
}
