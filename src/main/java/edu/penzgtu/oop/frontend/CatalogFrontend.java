package edu.penzgtu.oop.frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import edu.penzgtu.oop.models.Book;
import edu.penzgtu.oop.services.CatalogService;
import edu.penzgtu.oop.services.CatalogServiceImpl;

/**
 * Графический интерфейс для каталога книг. Позволяет пользователям просматривать товары и добавлять
 * книги в корзину.
 */
public class CatalogFrontend {
  /** Переменная которая предоставляет методы для управления элементами каталога. */
  private static final CatalogService catalogService = new CatalogServiceImpl();

  /** Метод для начального отображения элементов. */
  public static void show() {
    showTable();
    showMenu();
    choiceMenu();
  }

  /** Метод для получения введенного пользователем номера меню. */
  private static void choiceMenu() {
    int choice = getUserChoiceInput();

    switch (choice) {
      case 1 -> CartFrontend.addProductToCart();
      case 2 -> CartFrontend.deleteProductFromCart();
    }
  }

  /** Метод для отображения меню с опциями для выбора пользователем. */
  private static void showMenu() {
    System.out.println("[1] - Добавить книгу в корзину по ID");
    System.out.println("[0] - Вернуться в главное меню");
  }

  /** Метод для отображения книг в корзине пользователя в табличном формате. */
  private static void showTable() {
    ArrayList<Book> books = catalogService.findAll();

    System.out.println("Каталог книг: ");
    System.out.println(
        AsciiTable.getTable(
            books,
            Arrays.asList(
                new Column().header("ID").with(book -> Integer.toString(book.getId())),
                new Column().header("Название").with(Book::getName),
                new Column().header("Цена").with(book -> String.format("%s ₽", book.getPrice())),
                new Column().header("Автор").with(book -> book.getAuthor().getName()),
                new Column().header("Год").with(book -> Integer.toString(book.getYear())))));
  }

  /** Метод для получения пользовательского ввода. */
  private static int getUserChoiceInput() {
    Scanner scanner = new Scanner(System.in);

    return scanner.nextInt();
  }
}
