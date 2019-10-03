package com.monkey.api.web.workstation;

import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.enumeration.SupportedBrowsers;
import com.monkey.core.page.LocalisationHelper;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.task.web.workstation.NavigationTask;
import com.monkey.impl.config.MonkeySeleniumConfig;

/**
 * This class manage the actions that we can do on the Navigator.
 */
public class Navigator {


    public static final String GET_HTTP_REQUEST_HEADER = "getHttpRequestHeader";
    public static final String GO_BACK = "goBack";
    public static final String CLOSE_WINDOW = "closeWindow";
    public static final String OPEN_URL = "openUrl";
    public static final String GET_RESPONSE_CODE = "getResponseCode";
    public static final String REFRESH_PAGE = "refreshPage";
    public static final String UPLOAD_FILE = "uploadFile";


    /**
     * Open the given Url
     *
     * @param url
     */
    public static void openUrl(final String url) {
        final NavigationTask navigationTask = new NavigationTask();
        navigationTask.setName(Navigator.OPEN_URL);
        navigationTask.setUrl(url);
        navigationTask.runTask();
    }

    /**
     * Close the current window
     */
    public static void closeWindow() {
        final NavigationTask navigationTask = new NavigationTask();
        navigationTask.setName(Navigator.CLOSE_WINDOW);
        navigationTask.runTask();
    }

    /**
     * Go back
     */
    public static void goBack() {
        final NavigationTask navigationTask = new NavigationTask();
        navigationTask.setName(Navigator.GO_BACK);
        navigationTask.runTask();
    }

    /**
     * This method is not implemented yet.
     * It should returns the header of the current request.
     */
    public static void getHttpRequestHeader() {
        final NavigationTask navigationTask = new NavigationTask();
        navigationTask.setName(Navigator.GET_HTTP_REQUEST_HEADER);
        navigationTask.runTask();
    }

    /**
     * This method is not implemented yet.
     * It should returns the code of the request.
     */
    public static void getResponseCode() {
        final NavigationTask navigationTask = new NavigationTask();
        navigationTask.setName(Navigator.GET_RESPONSE_CODE);
        navigationTask.runTask();
    }

    /**
     * refresh the current page
     */
    public static void refreshPage() {
        final NavigationTask navigationTask = new NavigationTask();
        navigationTask.setName(Navigator.REFRESH_PAGE);
        navigationTask.runTask();
    }

    /**
     * Upload profil file with the given file path
     *
     * @param TestElement the element that should be clicked to upload the file
     * @param filePath    the path of the file
     */
    public static void uploadFile(final MonkeyWebElement TestElement, final String filePath) {
        final NavigationTask navigationTask = new NavigationTask();
        navigationTask.setName(Navigator.UPLOAD_FILE);
        navigationTask.setElement(TestElement);
        navigationTask.setFilePath(filePath);
        navigationTask.runTask();
    }

    /**
     * Return the name of the current Browser : Chrome, Firefox ...
     *
     * @return
     */
    public static String getBrowserName() {
        return ((MonkeySeleniumConfig) (ExecutionManager.getConfiguration())).getBrowserName();
    }

    /**
     * Verify that the given browserName is equals to the current browser name
     *
     * @param browserName
     * @return
     */
    public static boolean isCurrentBrowser(final SupportedBrowsers browserName) {
        return Navigator.getBrowserName().equals(browserName.name());
    }

    /**
     * Return the current URL of the browser
     *
     * @return
     */
    public static String getCurrentUrl() {
        return ExecutionManager.getMonkeyDriver().getCurrentUrl();
    }

    /**
     * Verify if the text is present in the page
     *
     * @param inputText
     * @return
     */
    public static boolean isTextPresent(final String inputText) {
        return LocalisationHelper.isTextPresent(inputText);
    }

    /**
     * Return the title of the browser
     *
     * @return
     */
    public static String getTitle() {
        return ExecutionManager.getMonkeyDriver().getTitle();
    }
}
