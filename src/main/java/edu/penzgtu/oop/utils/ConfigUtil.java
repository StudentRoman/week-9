package edu.penzgtu.oop.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.Getter;

/**
 * Класс ConfigUtil предназначен для чтения конфигурационных файлов и предоставления доступа к
 * параметрам конфигурации.
 */
public class ConfigUtil {
  /** Переменная для получения конфигурационного файла. */
  @Getter private static Properties config = null;

  /**
   * Метод, который устанавливает конфигурационный файл.
   *
   * @param configName Имя файла конфигурации для чтения.
   * @throws IOException Исключение, если файл не найден
   */
  public static void setConfig(String configName) throws IOException {
    InputStream inputStream = null;

    try {
      Properties prop = new Properties();
      inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(configName);

      if (inputStream != null) {
        prop.load(inputStream);
      } else {
        throw new FileNotFoundException("Файл " + configName + "' не найден");
      }

      config = prop;
    } catch (Exception e) {
      System.out.println("Ошибка: " + e);
    } finally {
      inputStream.close();
    }
  }
}
