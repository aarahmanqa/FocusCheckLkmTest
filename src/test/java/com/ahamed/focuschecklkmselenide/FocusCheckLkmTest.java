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
        SelenideLogger.addListener("allure", new AllureSelenide());
        Selenide.open("https://focus-check-lkm-001.web.app/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Configuration.timeout = 3000;

        $x("//input[@placeholder='Your Name']").setValue("Ahamed");
        $(byText("Start Game")).click();

        while(true) {
            String numberToBeHit = $x("//li[contains(text(),'HIT ')]/span").getText();
            System.out.println("Number to be hit: " + numberToBeHit);
            boolean clickDone = false;
            int size = $$x("//p[contains(@class,'text-black-900')]").size();
            for(int i=0; i<size; i++) {
                String currentNumber = $$x("//p[contains(@class,'text-black-900')]").get(i).getText();
                System.out.println("Current Number : " + currentNumber);
                if (currentNumber.equals(numberToBeHit)) {
                    $$x("//p[contains(@class,'text-black-900')]").get(i).click();
                    clickDone = true;
                    break;
                }
            }
            if(!clickDone) {
                throw new RuntimeException("Unable to perform click");
            }
        }
    }
}
