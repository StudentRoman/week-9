package edu.penzgtu.oop.services;

import java.util.ArrayList;

import edu.penzgtu.oop.models.Book;

public interface CatalogService {
  ArrayList<Book> findAll();

  public Book findById(int bookId);
}
