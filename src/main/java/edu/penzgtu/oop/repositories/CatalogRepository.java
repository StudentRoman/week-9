package edu.penzgtu.oop.repositories;

import java.util.ArrayList;

import edu.penzgtu.oop.dataparsers.JSONDataParser;
import edu.penzgtu.oop.models.Book;
import edu.penzgtu.oop.models.Catalog;
import edu.penzgtu.oop.utils.ConfigUtil;

/**
 * Класс CatalogRepository представляет репозиторий каталога книг. Данный класс наследуется от
 * {@link Catalog}. Он обеспечивает методы для получения списка книг и информации о конкретной книге
 * из базы данных.
 */
public class CatalogRepository extends Catalog {
  /** Переменная представляет собой JSON-парсер для обработки данных о книгах из файла. */
  private static final JSONDataParser<Catalog> catalogParser =
      new JSONDataParser<>(ConfigUtil.getConfig().getProperty("catalogDataPath"), Catalog.class);

  /** Конструктор для инициализации базы данных. */
  public CatalogRepository() {
    super(catalogParser.parse().getBooks());
  }

  /**
   * Метод для получения списка всех книг из базы данных.
   *
   * @return Список Book
   */
  public ArrayList<Book> findAll() {
    return this.getBooks();
  }

  /**
   * Метод для поиска книги по ее названию в базе данных.
   *
   * @return Объект Book или null, если книга не найдена.
   */
  public Book findById(int bookId) {
    return this.getBooks().stream().filter(book -> book.getId() == bookId).findFirst().orElse(null);
  }
}
