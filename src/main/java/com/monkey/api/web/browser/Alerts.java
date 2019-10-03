package com.monkey.api.web.browser;

import com.monkey.core.task.web.browser.AlertTask;
import org.openqa.selenium.NoAlertPresentException;

/**
 * This class manage the actions allowed for the Alert component
 */
public class Alerts {

    public static final String SENDKEY_TO_ALERT = "sendkeyToAlert";
    public static final String GET_ALERT_LABEL = "getAlertLabel";
    public static final String DISMISS_ALERT = "dismissAlert";
    public static final String ACCEPT_ALERT = "acceptAlert";

    /**
     * Confirm the accept option of the alert
     */
    public static void acceptAlert() {
        final AlertTask alertTask = new AlertTask();
        alertTask.setName(Alerts.ACCEPT_ALERT);
        alertTask.runTask();
    }

    /**
     * Confir the dismiss option of the alert
     */
    public static void dismissAlert() {
        final AlertTask alertTask = new AlertTask();
        alertTask.setName(Alerts.DISMISS_ALERT);
        alertTask.runTask();
    }

    /**
     * Return the content of the alert
     */
    public static void getAlertLabel() {
        final AlertTask alertTask = new AlertTask();
        alertTask.setName(Alerts.GET_ALERT_LABEL);
        alertTask.runTask();
    }

    /**
     * Send the given value
     *
     * @param keysToSend
     */
    public static void sendkeyToAlert(final String keysToSend) {
        final AlertTask alertTask = new AlertTask();
        alertTask.setName(Alerts.SENDKEY_TO_ALERT);
        alertTask.setKeysToSend(keysToSend);
        alertTask.runTask();
    }

    public static boolean isAlertPresent() {
        boolean presentFlag = false;
        try {
            // Check the presence of alert
            Alerts.getAlertLabel();
            presentFlag = true;

        } catch (final NoAlertPresentException ex) {
            // Alert not present
            ex.printStackTrace();
        }

        return presentFlag;

    }
}
