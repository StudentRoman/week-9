package edu.penzgtu.oop.dataparsers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Класс JSONDataParser является реализацией DataParser. Класс JSONDataParser представляет парсер
 * для работы с данными в формате JSON. Он позволяет читать данные из файла JSON, создавать объекты
 * на основе этих данных и сохранять данные в файл.
 *
 * @param <T> Тип объекта, с которым работает парсер.
 */
public class JSONDataParser<T> implements DataParser<T> {
  private final String dataPath;
  private final Class<T> model;

  /**
   * Создает объект JSONDataParser для работы с данными в формате JSON.
   *
   * @param dataPath Путь к файлу JSON.
   * @param model Класс, с которым работает парсер.
   */
  public JSONDataParser(String dataPath, Class<T> model) {
    this.dataPath = dataPath;
    this.model = model;
  }

  /**
   * Метод для получения данных из файла JSON.
   *
   * @return Объект, созданный на основе данных из файла JSON или null, если файл не найден.
   */
  @Override
  public T parse() {
    try (FileReader reader = new FileReader(this.dataPath)) {
      Gson gson = new Gson();

      return gson.fromJson(reader, this.model);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }

    return null;
  }

  /**
   * Метод для добавления новых данных в файл JSON.
   *
   * @param newData Данные, которые необходимо добавить.
   * @param <K> Тип объекта для добавления.
   */
  @Override
  public <K> void create(K newData) {
    try (FileWriter writer = new FileWriter(this.dataPath)) {
      Gson gson = new GsonBuilder().create();
      gson.toJson(newData, writer);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
