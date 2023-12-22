package edu.penzgtu.oop.services;

import java.util.ArrayList;

import edu.penzgtu.oop.models.Book;
import edu.penzgtu.oop.repositories.CatalogRepository;

/**
 * Класс CatalogServiceImpl является реализацией CatalogService Класс CatalogServiceImpl
 * представляет сервис каталога книг. Он обеспечивает методы для получения списка и поиска книг.
 */
public class CatalogServiceImpl implements CatalogService {
  /** Переменная представляет собой репозиторий для работы с каталогом книг. */
  private final CatalogRepository catalogRepository = new CatalogRepository();

  /**
   * Метод для получения списка всех книг в каталоге.
   *
   * @return Список Book или null, если книги не найдены.
   */
  @Override
  public ArrayList<Book> findAll() {
    return catalogRepository.findAll();
  }

  /**
   * Метод для поиска книги по ее названию.
   *
   * @param bookId ID-идентификатор книги, которую нужно найти.
   * @return Объект Book или null, если книга не найдена.
   */
  @Override
  public Book findById(int bookId) {
    return catalogRepository.findById(bookId);
  }
}
