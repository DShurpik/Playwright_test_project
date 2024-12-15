package sauceDemoTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BaseTest;
import pages.sauceDemo.ProductsPage;

public class LoginTest extends BaseTest {

    @Test
    public void testCorrectLoginCredentials() {
        ProductsPage productsPage = loginPage.loginAs("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitle().innerHTML() , "Products");
    }

    @Test
    public void testLockedOutUser() {
        loginPage.loginAs("wrong", "fake");
        Assert.assertEquals(loginPage.getErrorMessage().textContent(),
                "Epic sadface: Username and password do not match any user in this service");
    }
}
