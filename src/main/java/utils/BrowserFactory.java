package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import static config.ConfigurationManager.config;

public enum BrowserFactory {
    /**Enum BrowserFactory содержит три типа браузеров: CHROMIUM, FIREFOX, и WEBKIT.
     Каждый из этих типов имеет абстрактный метод createInstance, который создает экземпляр браузера.
     Метод options() возвращает настройки для запуска браузера (например, headless и slowMo из
     конфигурации).*/

    CHROMIUM {
        public Browser createInstance(final Playwright playwright) {
            return playwright.chromium().launch(options());
        }
    },

    FIREFOX {
        @Override
        public Browser createInstance(final Playwright playwright) {
            return playwright.firefox().launch(options());
        }
    },
    WEBKIT {
        @Override
        public Browser createInstance(final Playwright playwright) {
            return playwright.webkit().launch(options());
        }
    };

    public BrowserType.LaunchOptions options() {
        return new BrowserType.LaunchOptions()
                // данные из конфиг файла
                .setHeadless(config().headless())
                .setSlowMo(config().slowMotion());
    }

    public abstract Browser createInstance(final Playwright playwright);
}
