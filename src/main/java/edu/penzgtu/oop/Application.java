package edu.penzgtu.oop;

import java.io.IOException;

import edu.penzgtu.oop.frontend.MainFrontend;
import edu.penzgtu.oop.utils.ConfigUtil;

public class Application {
  public static void main(String[] args) throws IOException {
    ConfigUtil.setConfig("config.properties");
    MainFrontend.show();
  }
}
