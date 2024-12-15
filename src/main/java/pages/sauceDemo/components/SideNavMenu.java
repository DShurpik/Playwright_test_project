package pages.sauceDemo.components;

import com.microsoft.playwright.Page;
import pages.BaseComponent;

public class SideNavMenu extends BaseComponent {
    public SideNavMenu(final Page page) {
        super(page);
    }

    public void clickOnLogout() {
        page.click("#logout_sidebar_link");
    }
}
