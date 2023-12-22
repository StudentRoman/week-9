package edu.penzgtu.oop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Book {
  private int id;
  private String name;
  private Author author;
  private int year;
  private Publisher publisher;
  private String ISBN;
  private double price;
}
