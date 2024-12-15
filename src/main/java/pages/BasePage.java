package pages;

import com.microsoft.playwright.Page;

import static config.ConfigurationManager.config;

public abstract class BasePage {
    protected Page page;

    // задается тайм аут для работы на страницах
    public void setAndConfigurePage(final Page page) {
        this.page = page;
        page.setDefaultTimeout(config().timeout());
    }

    // Для инициализации одинаковых компонентов на страницах
    public void initComponents() {
    }
}
