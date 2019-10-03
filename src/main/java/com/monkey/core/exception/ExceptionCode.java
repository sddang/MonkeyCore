/**
 * The package used to manage the exceptions
 */

package com.monkey.core.exception;

public enum ExceptionCode {
    JSON_MAPPING_ERROR, UNKNOWN_PLATFORM, MAL_FORMED_URL, STARTING_LOCAL_GRID_ERROR, NOT_SUPPORTED_METHOD, FAIL_TO_UPLOAD_FILE, NOT_ACCESSIBLE_FILE_ERROR, MAL_FORMED_FILE_ERROR, MAPPED_KEY_NOTFOUND_EXCEPTION, MAPPED_LANGUAGE_NOTFOUND_EXCEPTION, INITIALIZATION_TESTELEMENTS_ERROR, INITIALIZATION_EMBEDDED_PAGES_ERROR, INITIALIZATION_EMBEDDED_COMPONENT_ERROR, DISABLED_ELEMENT_FIRE_EVENT_ERROR, SCREENSHOT_GENERATION_ERROR, FIELD_NOT_FOUND_EXCEPTION, DOC_GENERATION_ERROR, ERROR_WHEN_CREATING_FILE, LIST_OUT_OF_BOUND_EXCEPTION, NO_TEXT_ELEMENT_FOUND_IN_LIST, TESTNG_PARAM_UNSUPPORTED, TESTNG_PARAM_NOT_FOUND, WEB_VIEW_CONTEXT_NOT_FOUND, WEB_VIEW_CONTEXT_INDEX_NOT_FOUND, WRONG_WINDOW_NUM_TO_SWITCH, MAX_SCROOL_REACH, PERCENT_EXCEPTION, SESSION_COULD_NOT_START, PARSE_EXCEPTION, SESSION_NOT_FOUND_EXCEPTION;

    /**
     * Build the message from the exception code and there params
     *
     * @param code
     * @param param
     * @return
     */
    public static String getMessage(final ExceptionCode code, final String... param) {

        switch (code) {

            case SESSION_NOT_FOUND_EXCEPTION:
                return "Can't close the driver. The session is not found!!";
            case ERROR_WHEN_CREATING_FILE:
                return "ERROR happened when creating or writing in the file << " + param[0] + " >>. Reason can be : "
                        + (param[1] != null ? param[1] : "UNKNOWN");
            case INITIALIZATION_EMBEDDED_COMPONENT_ERROR:
                return "Fail to load injected embeded Component for class: " + param[0] + " , reason can be : "
                        + (param[1] != null ? param[1] : "UNKNOWN");
            case INITIALIZATION_EMBEDDED_PAGES_ERROR:
                return "Fail to load injected embeded pages for class: " + param[0] + " , reason can be : "
                        + (param[1] != null ? param[1] : "UNKNOWN");
            case DOC_GENERATION_ERROR:
                return "Can't generate documentation on test << " + param[0] + " >> for cause : \n " + param[1];
            case FIELD_NOT_FOUND_EXCEPTION:
                return "Can't locate element with criteria: " + param[0];
            case SCREENSHOT_GENERATION_ERROR:
                return "Problem happened when taken screenShot!!! " + param[0];
            case DISABLED_ELEMENT_FIRE_EVENT_ERROR:
                return "Fail to fire an event on profil disabled element : " + param[0];
            case INITIALIZATION_TESTELEMENTS_ERROR:
                return "Fail to load test elements for the class : " + param[0] + " reason can be : "
                        + (param[1] != null ? param[1] : "UNKNOWN");
            case MAPPED_KEY_NOTFOUND_EXCEPTION:
                return "The given key << " + param[0] + " >> is not found in the mapped data!!!";
            case MAPPED_LANGUAGE_NOTFOUND_EXCEPTION:
                return "The language << " + param[0] + " >> match no language in the mapped data!!!";
            case MAL_FORMED_FILE_ERROR:
                return "File not found or malformed : " + param[0] + " !!!";
            case NOT_ACCESSIBLE_FILE_ERROR:
                return "File access error : " + param[0] + " !!!";
            case FAIL_TO_UPLOAD_FILE:
                return "Enable to uplad File : " + param[0] + " !!!";
            case NOT_SUPPORTED_METHOD:
                return "Method not supported yet : " + param[0] + " !!!";
            case JSON_MAPPING_ERROR:
                return "Problem When reading the configuration file: << " + (param[0] != null ? param[0]
                        : "cause is undefined, but my be there is no grid up. Verify your config!!!") + " >>!";
            case UNKNOWN_PLATFORM:
                return "Unknown platform : << " + param[0] + " >>!";
            case MAL_FORMED_URL:
                return "MalFormed URL : << " + param[0] + " >>!";
            case STARTING_LOCAL_GRID_ERROR:
                return "Problem when strating local grid  !!! \n" + " Cause : " + param[0] + "\n Message: " + param[1];
            case LIST_OUT_OF_BOUND_EXCEPTION:
                return "List is empty or index is out of bound. Index : " + param[0] + " Size : " + param[1];
            case NO_TEXT_ELEMENT_FOUND_IN_LIST:
                return "No text element << " + param[0] + " >> found in the list of elements : " + param[1];
            case TESTNG_PARAM_NOT_FOUND:
                return "Param not found in the testNG running file. You have to define parameter : '" + param[0] + "'";
            case TESTNG_PARAM_UNSUPPORTED:
                return "Param << " + param[0] + " >> in " + "testNG running file should be equal to << " + param[1] + " >>";
            case WEB_VIEW_CONTEXT_NOT_FOUND:
                return "Impossible to switch to web view " + "context. Maybe no web view present at this moment "
                        + "and/or for android application verify this code " + "line is present in your application "
                        + "<<WebView.setWebContentsDebuggingEnabled(true);>>";
            case WEB_VIEW_CONTEXT_INDEX_NOT_FOUND:
                return "Impossible to switch to web view " + "context num << " + param[0] + " >>. Context : " + param[1];
            case WRONG_WINDOW_NUM_TO_SWITCH:
                return "Impossible to witch window. The " + "window num choosed doesn't exist and have to set "
                        + "between << " + param[0] + " >> & << " + param[1] + " >>";
            case MAX_SCROOL_REACH:
                return "Max scroll reach (" + param[0] + ") without find element";
            case PERCENT_EXCEPTION:
                return "Percent value must be between 0 and 1 : << " + param[0] + " >>";
            case SESSION_COULD_NOT_START:
                return "Session issue : " + param[0];
            case PARSE_EXCEPTION:
                return "Parse exception : " + param[0];
            default:
                break;
        }

        return null;
    }
}
