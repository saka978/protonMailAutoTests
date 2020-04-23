package protonAutotests;

import com.codeborne.selenide.Selenide;
import org.junit.*;
import org.junit.runners.MethodSorters;
import protonAutotests.pages.FoldersAndLabelsPage;
import protonAutotests.pages.LoginPage;

import static protonAutotests.utils.StringUtils.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LabelAndFolderTests extends Base {

  FoldersAndLabelsPage foldersAndLabelsPage = new FoldersAndLabelsPage();
  LoginPage loginPage = new LoginPage();
  static String newFolderName="FOLDER" + generateRandomNumber(),
      editedFolderName="FOLDER" + generateRandomNumber(),
      newLabelName="LABEL" + generateRandomNumber(),
      editedLabelName="LABEL" + generateRandomNumber();

  @Before
  public void login() {
    openPage("");
    loginPage.login("lukmakauto", "auto123").closeWelcomePopup().navigateToFolderAndLabelsPage();
  }

  @Test
  public void stage_A_checkIfAddNewFolderWorks() {
    foldersAndLabelsPage.addNewFolder(newFolderName).checkIfFolderNameMatch(newFolderName);
  }

  @Test
  public void stage_B_checkIfEditFolderWorks() {
    foldersAndLabelsPage.editItem(newFolderName,editedFolderName).checkIfFolderNameMatch(editedFolderName);
  }

  @Test
  public void stage_C_checkIfAddNewLabelWorks() {
    foldersAndLabelsPage.addNewLabel(newLabelName).checkIfLastLabelNameMatch(newLabelName);
  }

  @Test
  public void stage_D_checkIfEditLabelWorks() {
    foldersAndLabelsPage.editItem(newLabelName,editedLabelName).checkIfLastLabelNameMatch(editedLabelName);
  }

  @Test
  public void stage_E_checkIfSwapingWorks() {
    foldersAndLabelsPage.swapItemPlacesAndCheckIfItsSwaped(editedLabelName,editedFolderName);
  }

  @Test
  public void stage_F_checkIfDeleteFolderWorks() {
    foldersAndLabelsPage.deleteItem(editedFolderName).checkIfItemIsDeleted(editedFolderName);
  }

  @Test
  public void stage_G_checkIfDeleteLabelWroks() {
    foldersAndLabelsPage.deleteItem(editedLabelName).checkIfItemIsDeleted(editedLabelName);
  }
  @After
  public void closePage(){
    Selenide.closeWebDriver();
  }
}
