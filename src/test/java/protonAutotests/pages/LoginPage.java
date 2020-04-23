package protonAutotests.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    static SelenideElement usernameTextBox = $("#username"),
    passwordTextBox = $("#password"),
    loginButton = $("#login_btn");

    public MailMainPage login(String username, String password){
        usernameTextBox.setValue(username);
        passwordTextBox.setValue(password);
        loginButton.click();
        return new MailMainPage();
    }
}
