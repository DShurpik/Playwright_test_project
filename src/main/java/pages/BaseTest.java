package pages;

import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.qameta.allure.Attachment;
import com.microsoft.playwright.*;
import pages.sauceDemo.LoginPage;
import utils.BasePageFactory;
import utils.BrowserManager;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static config.ConfigurationManager.config;

public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    protected LoginPage loginPage;
    private boolean needVideo;

    @BeforeSuite
    public void initBrowser() {
        playwright = Playwright.create();
        browser = BrowserManager.getBrowser(playwright);

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Platform", System.getProperty("os.name"))
                        .put("Version", System.getProperty("os.version"))
                        .put("Browser", config().browser().toUpperCase())
                        .put("Context URL", config().baseUrl())
                        .build(),
                config().allureResultsDir() + "/");
    }

    @BeforeMethod
    public void createContext() {
        if (config().video()) {
            browserContext = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get(config().baseTestVideoPath())));
        } else {
            browserContext = browser.newContext();
        }
        page = browserContext.newPage();
        loginPage = createInstance(LoginPage.class);
    }

    @AfterMethod
    public void attach(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            needVideo = true;
            captureScreenshotOnFailure();
        }

        if (config().video() && needVideo) {
            captureVideo();
        }
        browserContext.close();
        needVideo = false;
    }

    @AfterSuite
    public void close() {
        browser.close();
        playwright.close();
    }

    @Attachment(value = "Test Video", type = "video/webm")
    @SneakyThrows
    private byte[] captureVideo() {
        return Files.readAllBytes(page.video().path());
    }

    @Attachment(value = "Failed Test Case Screenshot", type = "image/png")
    private byte[] captureScreenshotOnFailure() {
        return page.screenshot();
    }

    protected <T extends BasePage> T createInstance(Class<T> basePage) {
        return BasePageFactory.createInstance(page, basePage);
    }
}
