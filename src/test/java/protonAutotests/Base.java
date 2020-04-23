package protonAutotests;

import static com.codeborne.selenide.Selenide.*;

public class Base {

  private static String baseUrl = "https://beta.protonmail.com/";

  public static void openPage(String urlExtension) {
    open(baseUrl + urlExtension);
  }
}
