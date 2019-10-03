package com.monkey.services.data;

import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.session.ExecutionManager;
import com.monkey.core.utils.JsonParser;

import java.io.IOException;

public class DataMapper extends JsonParser<TestData> {

    private TestData testData;
    private String path;

    /**
     * Get the mapper of current execution context
     *
     * @return
     */
    public static DataMapper getSessionMapper() {
        return ExecutionManager.getMonkeyDriver().getDataMapper();
    }

    /**
     * Read the Data file
     */
    public void initMapper() {
        try {
            this.testData = this.parse(this.getPath(), TestData.class);
        } catch (final IOException e) {
            throw new MonkeyException(ExceptionCode.MAL_FORMED_FILE_ERROR, this.getPath(), e.getMessage());
        }
    }

    /**
     * Map the data with the given key
     *
     * @param inputData
     * @return
     */
    public String mapData(final String inputData) {
        if (inputData == null || this.testData == null || this.testData.isEmpty()) {
            return inputData;
        }

        if ((!inputData.startsWith("#")) || inputData.length() == 1) {
            return inputData;
        }

        final String language = ExecutionManager.getMonkeyDriver().getLanguage();
        final DataByLanguage dataByLangage = this.testData.get(language);
        if (dataByLangage == null) {
            throw new MonkeyException(ExceptionCode.MAPPED_LANGUAGE_NOTFOUND_EXCEPTION, language);
        }

        final String key = inputData.substring(1);
        final String value = dataByLangage.get(key);
        if (value == null) {
            throw new MonkeyException(ExceptionCode.MAPPED_KEY_NOTFOUND_EXCEPTION, key);
        }
        return value;
    }

    /**
     * retrun the path
     *
     * @return
     */
    public String getPath() {
        return this.path;
    }

    /**
     * the path to set
     *
     * @param path
     */
    public void setPath(final String path) {
        this.path = path;
    }

}
