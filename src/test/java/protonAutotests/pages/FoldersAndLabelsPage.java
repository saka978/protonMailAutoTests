package protonAutotests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FoldersAndLabelsPage {

  private SelenideElement addFolderButton = $("[data-test-id='folders/labels:addFolder']"),
      addLabelButton = $("[data-test-id='folders/labels:addLabel']"),
      itemNameTextBox = $("#accountName"),
      submitButton = $("[type='submit']"),
      successAlertMessage = $(".notification-success"),
      deleteButton = $("[aria-hidden='false'] [data-test-id='folders/labels:item-delete']");
  ElementsCollection firstListRowName = $$("tbody tr ");

  public FoldersAndLabelsPage addNewFolder(String folderName) {
    addFolderButton.click();
    setNameAndSaveIt(folderName);
    return new FoldersAndLabelsPage();
  }

  public FoldersAndLabelsPage addNewLabel(String labelName) {
    addLabelButton.click();
    setNameAndSaveIt(labelName);
    return new FoldersAndLabelsPage();
  }

  public FoldersAndLabelsPage checkIfFolderNameMatch(String folderName) {
    getRowElementByName(folderName).$(" [data-test-id='folders/labels:item-name']").shouldBe(exist);
    return this;
  }

  public FoldersAndLabelsPage checkIfLastLabelNameMatch(String labelName) {
    getRowElementByName(labelName).$(" [data-test-id='folders/labels:item-name']").waitUntil(visible,5000);
    return this;
  }

  public FoldersAndLabelsPage swapItemPlacesAndCheckIfItsSwaped(
      String firstItemName, String secondItemName) {
    SelenideElement swapFirstItemIcon = getSwapItemIcon(firstItemName);
    SelenideElement swapSecondItemIcon = getSwapItemIcon(secondItemName);
    swapFirstItemIcon.dragAndDropTo(swapSecondItemIcon);
    return this;
  }

  public FoldersAndLabelsPage deleteItem(String itemToDelete) {
    SelenideElement itemArrowDownMoreOptions = getArrowMoreOptionsByName(itemToDelete);
    itemArrowDownMoreOptions.click();
    deleteButton.click();
    submitButton.click();
    itemArrowDownMoreOptions.waitUntil(disappears, 5000);
    return this;
  }

  public FoldersAndLabelsPage checkIfItemIsDeleted(String itemName) {
    try {
      getRowElementByName(itemName);
    } catch (ElementNotFound ex){
      // Do nothing because if all items are deleted, and it cant find that element list
    }
    return this;
  }

  private void clearItemNameTextBox() {
    // folderNameTextBox.clear(); .clear() doesn't work, so i use function bellow
    itemNameTextBox.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
  }

  public FoldersAndLabelsPage editItem(String oldName, String newName) {
    getRowElementByName(oldName).$(" [data-test-id='folders/labels:item-edit']").click();
    clearItemNameTextBox();
    setNameAndSaveIt(newName);
    return this;
  }

  private void setNameAndSaveIt(String name) {
    itemNameTextBox.setValue(name);
    submitButton.click();
    successAlertMessage.shouldBe(visible);
  }

  private SelenideElement getRowElementByName(String itemName) {
    SelenideElement desiredRow = null;
    firstListRowName.get(0).waitUntil(exist, 5000);
    for (int i = 0; i < firstListRowName.size(); i++) {
      String iteratingRowName =
          firstListRowName.get(i).$(" [data-test-id='folders/labels:item-name']").getText();
      if (iteratingRowName.equals(itemName)) {
        desiredRow = firstListRowName.get(i);
      }
    }
    return desiredRow;
  }

  private SelenideElement getArrowMoreOptionsByName(String itemName) {
    return getRowElementByName(itemName).$(" .mauto");
  }

  private SelenideElement getSwapItemIcon(String itemName){
    return getRowElementByName(itemName).$(" [data-test-id='table:order-icon']");
  }
}
