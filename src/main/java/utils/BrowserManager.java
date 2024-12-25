package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

import static config.ConfigurationManager.config;

public class BrowserManager {
    // для получения браузера для базового класса
    /** В этом классе происходит выбор конкретного браузера из перечисленных в BrowserFactory,
     * основываясь на значении, полученном из конфигурации.
     BrowserFactory.valueOf(config().browser().toUpperCase()) – получаем тип браузера из конфига
     и вызываем createInstance(playwright) для создания экземпляра браузера.*/
    public static Browser getBrowser(final Playwright playwright) {
        return BrowserFactory.valueOf(config().browser().toUpperCase()).createInstance(playwright);
    }
}
