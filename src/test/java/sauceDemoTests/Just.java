package sauceDemoTests;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import pages.BaseTest;

import static config.ConfigurationManager.config;

public class Just extends BaseTest {
    @Test
    public void test1() {
        System.out.println("App Name: " + config().appName());
        System.out.println("Log Level: " + config().logLevel());

        Allure.step("App Name: " + config().appName());
        Allure.step("Log Level: " + config().logLevel());
    }
}
