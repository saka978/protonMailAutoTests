package protonAutotests.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MailMainPage {
  SelenideElement welcomePopUpCloseBtn = $(".pm-modalHeader [data-name='close']"),
      labelsAndFoldersButton = $("#tour-label-settings");

  public MailMainPage closeWelcomePopup() {
    welcomePopUpCloseBtn.click();
    return this;
  }

  public FoldersAndLabelsPage navigateToFolderAndLabelsPage() {
    labelsAndFoldersButton.click();
    return new FoldersAndLabelsPage();
  }
}
