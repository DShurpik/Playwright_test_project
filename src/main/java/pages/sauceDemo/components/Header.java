package pages.sauceDemo.components;

import com.microsoft.playwright.Page;
import pages.BaseComponent;

public final class Header extends BaseComponent {
    public Header(final Page page) {
        super(page);
    }

    public void clickOnHamburgerIcon() {
        page.click("#react-burger-menu-btn");
    }

    public void clickOnCart() {
        page.locator("//a[@data-test='shopping-cart-link']").click();
    }
}
