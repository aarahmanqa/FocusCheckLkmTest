package com.ahamed.focuschecklkmselenide;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FocusCheckLkmTest {

    @Test
    public void test() {
        Configuration.browserSize = "max";
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true).savePageSource(true));
        Selenide.open("https://focus-check-lkm-001.web.app/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Configuration.timeout = 3000;

        $x("//input[@placeholder='Your Name']").setValue("https://github.com/aarahmanqa");
        $(byText("Start Game")).click();

        while (true) {
            SelenideElement numberToBeHitElement = $x("//li[contains(text(),'HIT ')]/span");
            if (!numberToBeHitElement.exists()) {
                break;
            }
            String numberToBeHit = numberToBeHitElement.getText();
            SelenideElement currentNumberElement = $x("//p[contains(@class,'text-black-900')][text()='" + numberToBeHit + "']");
            if (!currentNumberElement.exists()) {
                break;
            }
            currentNumberElement.click();
        }
        System.out.println($(byText("score :")).getText());
    }
}