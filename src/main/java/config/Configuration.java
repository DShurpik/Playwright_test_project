package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "classpath:config.properties",
        "classpath:allure.properties",
        "classpath:config-${env}.properties"})
public interface Configuration extends Config {

    @Key("allure.results.directory")
    String allureResultsDir();

    @Key("base.url")
    String baseUrl();

    @Key("base.test.video.path")
    String baseTestVideoPath();

    String browser();

    boolean headless();

    @Key("slow.motion")
    int slowMotion();

    int timeout();

    boolean video();

    @Key("app.name")
    String appName();

    @Key("log.level")
    String logLevel();
}
