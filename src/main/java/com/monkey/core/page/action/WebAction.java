
/**
 * This package is the core of the framework
 */
package com.monkey.core.page.action;

public interface WebAction {
    /**
     * Fire the click event of the element
     */
    void click();

    /**
     * Fire the click event of the element for not enabled elements
     */
    void click(boolean allowClickOnDisabledElement);

    /**
     * Add profil value to the element
     */
    void addValue();

    /**
     * Set profil value to the element
     */
    void type();

    /**
     * Set profil value to the element
     */
    void type(boolean iosSendKey);

    /**
     * Clear the element
     */
    void clear();

    /**
     * Clear the element - clear if the element till has value
     */
    void clear(boolean checkCleaned);

    /**
     * Check or uncheck the radio element
     */
    void radioCheck();

    /**
     * Check or uncheck the check element
     */
    void check(boolean enabled);

    /**
     * Fire the hover event of the element
     */
    void hover();

    /**
     * Fire the submit event of the element
     */
    void submit();

    /**
     * Select an option of the select element by value and by index
     */
    void select();

    /**
     * Fire the doubleClick event of the element
     */
    void doubleClick();

    /**
     * Launch the keyboard action: ENTER
     */
    void setConfirmation();

}

