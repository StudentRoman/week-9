package edu.penzgtu.oop.repositories;

import java.util.ArrayList;
import java.util.Objects;

import edu.penzgtu.oop.dataparsers.JSONDataParser;
import edu.penzgtu.oop.models.Book;
import edu.penzgtu.oop.models.Cart;
import edu.penzgtu.oop.models.Customer;
import edu.penzgtu.oop.models.CustomerCart;
import edu.penzgtu.oop.utils.ConfigUtil;

/**
 * Класс CartRepository представляет репозиторий корзины книг. Данный класс наследуется от {@link
 * Cart}. Он обеспечивает методы для сохранения, обновления и удаления информации о книгах в корзине
 * и поиска книг в базе данных.
 */
public class CartRepository extends Cart {
  /** Переменная представляет собой JSON-парсер для обработки данных о корзине из файла. */
  private static final JSONDataParser<Cart> cartParser =
      new JSONDataParser<>(ConfigUtil.getConfig().getProperty("cartDataPath"), Cart.class);

  /** Конструктор для инициализации базы данных. */
  public CartRepository() {
    super(cartParser.parse().getCart());
  }

  /**
   * Метод для получения списка всех пользовательских корзин из базы данных.
   *
   * @return Список CustomerCart или null, если корзины не найдены.
   */
  public ArrayList<CustomerCart> findAll() {
    return this.getCart();
  }

  /**
   * Метод для получения корзины пользователя по имени из базы данных.
   *
   * @param customerName Имя для поиска корзины пользователя.
   * @return Объект CustomerCart или null, если корзина не найдена.
   */
  public CustomerCart findByName(String customerName) {
    return this.getCart() == null
        ? null
        : this.getCart().stream()
            .filter(item -> Objects.equals(item.getCustomer().getName(), customerName))
            .findFirst()
            .orElse(null);
  }

  /**
   * Метод для добавления корзины пользователя в базу данных.
   *
   * @param customer Объект Customer, который создал корзину.
   */
  public void insertOne(Customer customer) {
    CustomerCart customerCart = new CustomerCart(new ArrayList<>(), 0.0, customer);

    this.getCart().add(customerCart);
    cartParser.create(this);
  }

  /**
   * Метод для обновления корзины в базе данных.
   *
   * @param customerName Имя для поиска корзины пользователя.
   * @param book Объект Book, которую нужно добавить в корзину.
   */
  public void updateOne(String customerName, Book book) {
    this.getCart().stream()
        .filter(item -> Objects.equals(item.getCustomer().getName(), customerName))
        .findFirst()
        .ifPresent(customerCart -> customerCart.addItem(book));
    cartParser.create(this);
  }

  /**
   * Метод для удаления книги из корзины в базе данных.
   *
   * @param customerName Имя для поиска корзины пользователя.
   * @param book Объект Book, которую нужно удалить из корзины.
   */
  public void deleteOne(String customerName, Book book) {
    this.getCart().stream()
        .filter(item -> Objects.equals(item.getCustomer().getName(), customerName))
        .findFirst()
        .ifPresent(customerCart -> customerCart.deleteItem(book));
    cartParser.create(this);
  }
}
