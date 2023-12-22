package edu.penzgtu.oop.dataparsers;

public interface DataParser<T> {

  T parse();

  <K> void create(K newData);
}
