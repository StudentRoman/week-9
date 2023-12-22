package edu.penzgtu.oop.services;

import java.util.ArrayList;

import edu.penzgtu.oop.models.Book;
import edu.penzgtu.oop.models.Customer;
import edu.penzgtu.oop.models.CustomerCart;
import edu.penzgtu.oop.repositories.CartRepository;

/**
 * Класс CartServiceImpl является реализацией CartService Класс CartServiceImpl представляет собой
 * сервис для работы с корзиной пользователя. Он обеспечивает методы для добавления книг в корзину,
 * удаления книг из корзины и поиска корзин пользователей.
 */
public class CartServiceImpl implements CartService {
  /** Переменная представляет собой репозиторий для работы с корзиной. */
  private final CartRepository cartRepository = new CartRepository();

  /**
   * Метод, который проверяет, содержит ли список книг указанную книгу по ID.
   *
   * @param bookId ID-идентификатор книги, которую нужно найти.
   * @param books Список Book, в котором нужно найти книгу.
   * @return true, если книга найдена в списке, false - в противном случае.
   */
  private boolean findBookInList(int bookId, ArrayList<Book> books) {
    return books != null && books.stream().anyMatch(item -> item.getId() == bookId);
  }

  /**
   * Метод для получения списка всех пользовательских корзин.
   *
   * @return Список CustomerCart или null, если корзины не найдены.
   */
  @Override
  public ArrayList<CustomerCart> findAll() {
    return cartRepository.findAll();
  }

  /**
   * Метод для получения корзины пользователя по имени.
   *
   * @param customerName Имя для поиска корзины пользователя.
   * @return Объект CustomerCart или null, если корзина не найдена.
   */
  @Override
  public CustomerCart findByName(String customerName) {
    return cartRepository.findByName(customerName);
  }

  /**
   * Метод для создания корзины пользователя.
   *
   * @param customer Объект Customer, который создал корзину.
   */
  @Override
  public void createCart(Customer customer) {
    cartRepository.insertOne(customer);
  }

  /**
   * Метод для добавления кинги в корзину пользователя.
   *
   * @param customerName Имя для поиска корзины пользователя.
   * @param book Объект Book, которую нужно добавить в корзину.
   */
  @Override
  public void addItem(String customerName, Book book) {
    CustomerCart cart = cartRepository.findByName(customerName);

    boolean isCartExist = this.findBookInList(book.getId(), cart.getList());

    if (!isCartExist) {
      cartRepository.updateOne(customerName, book);
    } else {
      System.out.println("Товар уже в корзине");
    }
  }

  /**
   * Метод для удаления книги из корзины пользователя.
   *
   * @param customerName Имя для поиска корзины пользователя.
   * @param book Объект Book, которую нужно удалить из корзины.
   */
  @Override
  public void deleteItem(String customerName, Book book) {
    cartRepository.deleteOne(customerName, book);
  }
}
