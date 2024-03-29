package com.monkey.core.task.web.workstation;

import com.monkey.api.Wait;
import com.monkey.api.web.browser.Window;
import com.monkey.api.web.workstation.KeyBoard;
import com.monkey.api.web.workstation.Navigator;
import com.monkey.core.enumeration.BrowserMode;
import com.monkey.core.enumeration.Protocol;
import com.monkey.core.enumeration.SupportedVarEnv;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.AbstractTask;
import com.monkey.services.data.DataMapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class NavigationTask extends AbstractTask {

    private String name;
    private String filePath;
    private String url;
    private String description;

    /**
     * Execute the events related the navigation on the browser using the
     * selenium implementation
     */
    @Override
    public void execute() {

        switch (this.name) {
            case Navigator.OPEN_URL:
                this.description = "Open URL [" + this.getUrl() + "]";
                final String url = this.formatURL();
                ExecutionManager.getMonkeyDriver().get(url);
                // Read information related to the window size switch the device
                final BrowserMode browserSize = BrowserMode
                        .valueOf(ExecutionManager.getExecutionContext().get(SupportedVarEnv.browserMode.name()));
                switch (browserSize) {
                    case desktop:
                        Window.maximizeWindow();
                        break;
                    case smartphone:
                        // http://www.paintcodeapp.com/news/ultimate-guide-to-iphone-resolutions
                        // resolution Iphone 6-portrait
                        // If is added for the case to launch tests on Appium-Browser
                        if (!ExecutionManager.getConfiguration().getProtocol().name().equals(Protocol.appium.name())) {
                            Window.setSize(400, 800);
                        }
                        break;
                    case tablet:
                        // resolution ipad-protrait
                        Window.setSize(1024, 800);
                        break;
                }
                break;
            case Navigator.CLOSE_WINDOW:
                this.description = "Close window ";
                final String currentWindow = ExecutionManager.getMonkeyDriver().getWindowHandle();
                final Set<String> AllWindows = ExecutionManager.getMonkeyDriver().getWindowHandles();
                for (final String win : AllWindows) {
                    if (win.equals(currentWindow)) {
                        ExecutionManager.getMonkeyDriver().switchTo().window(currentWindow).close();
                    }
                }
                break;
            case Navigator.GET_HTTP_REQUEST_HEADER:
                this.description = "Get http request header";
                throw (new MonkeyException(ExceptionCode.NOT_SUPPORTED_METHOD, Navigator.GET_HTTP_REQUEST_HEADER));
            case Navigator.GET_RESPONSE_CODE:
                this.description = "Get response";
                throw (new MonkeyException(ExceptionCode.NOT_SUPPORTED_METHOD, Navigator.GET_HTTP_REQUEST_HEADER));
            case Navigator.GO_BACK:
                this.description = "Go back";
                ExecutionManager.getMonkeyDriver().navigate().back();
                break;
            case Navigator.REFRESH_PAGE:
                this.description = "refresh page";
                ExecutionManager.getMonkeyDriver().navigate().refresh();
                break;
            case Navigator.UPLOAD_FILE:
                this.description = "Upload file [ " + this.filePath + " ]";
                this.uploadFile();
                break;
        }
    }

    /**
     * Upload the file on the browser
     */
    private void uploadFile() {
        URL url = Thread.currentThread().getContextClassLoader()
                .getResource(DataMapper.getSessionMapper().mapData(this.filePath));
        if (url == null) {
            try {
                url = new URL(DataMapper.getSessionMapper().mapData(this.filePath));
            } catch (final MalformedURLException e) {
                throw (new MonkeyException(ExceptionCode.MAL_FORMED_URL,
                        DataMapper.getSessionMapper().mapData(this.filePath)));
            }
        }
        try {
            final WebElement element = this.getElement().getWebElement();
            final LocalFileDetector localFileDetector = new LocalFileDetector();
            ((RemoteWebElement) element).setFileDetector(localFileDetector);
            int i = 0;
            while (i < 5) {
                try {
                    element.sendKeys(url.getPath());
                    break;
                } catch (final Exception e) {
                    i = i + 1;
                    Wait.implicitWait(5);
                    if (i == 1 || i == 2) {
                        KeyBoard.pageDown();
                    } else {
                        KeyBoard.pageUp();
                    }
                }
            }
            Wait.implicitWait(3);
        } catch (final RuntimeException e) {
            throw (new MonkeyException(ExceptionCode.FAIL_TO_UPLOAD_FILE,
                    DataMapper.getSessionMapper().mapData(this.filePath)));
        }
    }

    /**
     * Format the URL before opening it. The Start url specified in the
     * configuration will be used as root of relative urls
     *
     * @return
     */
    private String formatURL() {
        String tempURL = DataMapper.getSessionMapper().mapData(this.url);
        if (!tempURL.startsWith("http://") && !tempURL.startsWith("https://")) {
            final String appURL = ExecutionManager.getMonkeyDriver().getCurrentUrl();
            if (appURL.endsWith("/")) {
                if (tempURL.startsWith("/")) {
                    tempURL = appURL.substring(0, appURL.length() - 1) + tempURL;
                } else {
                    tempURL = appURL + tempURL;
                }
            } else {
                if (tempURL.startsWith("/")) {
                    tempURL = appURL + tempURL;
                }
                // else {
                // tempURL = appURL + "/" + tempURL;
                // }
            }

        }
        return tempURL;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * @param filePath
     */
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
    }

}
