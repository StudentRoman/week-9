package edu.penzgtu.oop.frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import lombok.Getter;
import lombok.Setter;

import edu.penzgtu.oop.models.Book;
import edu.penzgtu.oop.models.Customer;
import edu.penzgtu.oop.models.CustomerCart;
import edu.penzgtu.oop.services.CartService;
import edu.penzgtu.oop.services.CartServiceImpl;
import edu.penzgtu.oop.services.CatalogService;
import edu.penzgtu.oop.services.CatalogServiceImpl;

/**
 * Графический интерфейс для корзины покупок. Класс CartFrontend обрабатывает отображение товаров в
 * корзине покупок и управление ими. Он предоставляет методы для добавления, удаления и обновления
 * товаров в корзине, а также методы для отображения содержимого каталога и расчета общей стоимости.
 */
public class CartFrontend {
  /** Переменная которая предоставляет методы для управления элементами корзины. */
  private static final CartService cartService = new CartServiceImpl();

  /** Переменная которая предоставляет методы для управления элементами каталога. */
  private static final CatalogService catalogService = new CatalogServiceImpl();

  /** Инициализированный пользователь в системе. */
  @Setter @Getter private static Customer currentCustomer;

  /** Метод для начального отображения элементов. */
  public static void show() {
    showTable();
    showMenu();
    choiceMenu();
  }

  /**
   * Метод для инициализации корзины клиента.
   *
   * @param username Имя клиента, чья корзина должна быть инициализирована.
   */
  public static void initCustomerCart(String username) {
    CustomerCart currentCart = cartService.findByName(username);

    if (currentCart == null) {
      int customerId = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
      Customer newCustomer = new Customer(customerId, username);

      setCurrentCustomer(newCustomer);
      cartService.createCart(newCustomer);
    } else {
      System.out.println("Ваша корзина уже существует");
      setCurrentCustomer(currentCart.getCustomer());
      showTable();
    }
  }

  /** Метод для добавления кинги в корзину пользователя. */
  public static void addProductToCart() {
    System.out.println("Введите ID книги");
    int bookId = getUserChoiceInput();
    Book book = catalogService.findById(bookId);

    cartService.addItem(currentCustomer.getName(), book);
  }

  /** Метод для удаления книги из корзины пользователя. */
  public static void deleteProductFromCart() {
    System.out.println("Введите ID книги");
    int bookId = getUserChoiceInput();
    Book book = catalogService.findById(bookId);

    cartService.deleteItem(currentCustomer.getName(), book);
  }

  /** Метод для получения общей стоймости книг в корзине пользователя. */
  private static void getTotalPrice() {
    System.out.println("Общая цена");
    CustomerCart currentCart = cartService.findByName(currentCustomer.getName());

    System.out.println(currentCart.getTotalPrice());
  }

  /** Метод для получения введенного пользователем номера меню. */
  private static void choiceMenu() {
    int choice = getUserChoiceInput();

    switch (choice) {
      case 1 -> deleteProductFromCart();
      case 2 -> getTotalPrice();
    }
  }

  /** Метод для отображения меню с опциями для выбора пользователем. */
  private static void showMenu() {
    System.out.println("[1] - Удалить книгу из корзины по ID");
    System.out.println("[2] - Общая стоймость");
    System.out.println("[0] - Вернуться в главное меню");
  }

  /** Метод для отображения книг в корзине пользователя в табличном формате. */
  private static void showTable() {
    ArrayList<Book> books = cartService.findByName(currentCustomer.getName()).getList();

    System.out.println("Ваша корзина: ");
    if (books.isEmpty()) {
      System.out.println("В корзине ничего нет");
    } else {
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
  }

  /** Метод для получения пользовательского ввода. */
  private static int getUserChoiceInput() {
    Scanner scanner = new Scanner(System.in);

    return scanner.nextInt();
  }
}
