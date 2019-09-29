
package com.monkey.impl.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.monkey.core.config.MonkeyConfig;
import com.monkey.core.enumeration.SupportedBrowsers;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;


public class MonkeySeleniumConfig extends MonkeyConfig {

    private static final String REQUIRE_WINDOW_FOCUS = "requireWindowFocus";
    private static final String ENABLE_NATIVE_EVENTS = "EnableNativeEvents";

    private String browserName;
    private String version;
    private String platform;
    private String proxyUrl;

    /**
     * Return the browser name of the test execution
     *
     * @return
     */
    public String getBrowserName() {
        return this.browserName;
    }

    /**
     * @param browserName
     */
    public void setBrowserName(final String browserName) {
        this.browserName = browserName;
    }

    /**
     * Return the version of the configured browser
     *
     * @return
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * @param version
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * Return the configured platform or ANY.
     *
     * @return
     */
    public String getPlatform() {
        return this.platform;
    }

    /**
     * @param platform
     */
    public void setPlatform(final String platform) {
        this.platform = platform;
    }


    /**
     * Return the configured proxy url
     *
     * @return
     */
    public String getProxyUrl() {
        return this.proxyUrl;
    }

    /**
     * @param proxyUrl
     */
    public void setProxyUrl(final String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    public DesiredCapabilities getCapabilities() {
        if (SupportedBrowsers.ie.name().equals(this.browserName)) {
            this.browserName = "internet explorer";
        }
        final DesiredCapabilities capabilities = new DesiredCapabilities(this.browserName, this.version, this.getPlateform());
        this.addCapabilitiesInfo(capabilities);
        return capabilities;
    }

    private Platform getPlateform() {
        Platform platformAny = Platform.ANY;
        if (!StringUtils.isEmpty(this.platform)) {
            try {
                platformAny = Platform.valueOf(this.platform);
            } catch (final MonkeyException e) {
                throw new MonkeyException(ExceptionCode.UNKNOWN_PLATFORM, this.platform);
            }
        }
        return platformAny;
    }

    private void addCapabilitiesInfo(final DesiredCapabilities capabilities) {
        switch (SupportedBrowsers.valueOf(this.browserName.replace(" ", ""))) {
            case firefox:
                capabilities.setJavascriptEnabled(true);
                final FirefoxProfile profile = new FirefoxProfile();
//		        profile.setEnableNativeEvents(true);
                profile.setPreference("browser.download.folderList", 0);
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("browser.download.manager.focusWhenStarting", false);
                profile.setPreference("browser.download.useDownloadDir", true);
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                profile.setPreference("browser.download.manager.closeWhenDone", true);
                profile.setPreference("browser.download.manager.showAlertOnComplete", false);
                profile.setPreference("browser.download.manager.useWindow", false);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                profile.setPreference("security.enable_java", true);
                profile.setPreference("plugin.state.java", 2);
                // String language = System.getProperty(USER_LANGUAGE);
                // profile.setPreference(INTL_ACCEPT_LANGUAGES, language);
                capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

                break;
            case ie:
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setCapability(MonkeySeleniumConfig.ENABLE_NATIVE_EVENTS, true);
                capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                capabilities.setCapability(MonkeySeleniumConfig.REQUIRE_WINDOW_FOCUS, false);
                capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
                capabilities.setCapability("browserstack.ie.enablePopups", "true");
                break;
            case chrome:
                capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
//              capabilities.setCapability("chrome.switches", Arrays.asList("--disable-extensions"));
                final ChromeOptions options = new ChromeOptions();
//                options.addArguments("--ignore-certificate-errors=yes");
                capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-ssl-errors=yes"));
                //options.addArguments(Arrays.asList("allow-running-insecure-content", "ignore-certificate-errors"));
                options.addArguments("--disable-extensions");
//                options.addArguments("chrome.switches","--disable-extensions");
                //options.addArguments("--disable-plugins");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--allow-insecure-localhost=yes");
                options.addArguments("--ignore-urlfetcher-cert-requests=yes");
                options.addArguments("disable-infobars");
               options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
//			    options.addArguments("--test-type");
//			    options.addArguments("--allow-running-insecure-content");
//              options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("load-extension"));
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, false);
                capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, false);
                // capabilities.setCapability("chrome.switches",
                // Arrays.asList("--disable-plugins"));
                break;
            default:
                break;
        }

        if (StringUtils.isNoneEmpty(this.proxyUrl)) {
            final Proxy proxy = new Proxy();
            proxy.setHttpProxy(this.proxyUrl);
            capabilities.setCapability(CapabilityType.PROXY, proxy);
        }
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability("browserstack.debug", true);
        capabilities.setCapability("unexpectedAlertBehaviour", "ignore");
    }

}
