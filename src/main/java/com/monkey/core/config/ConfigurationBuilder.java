

package com.monkey.core.config;

/**
 * The package used to build the configurations
 */
import java.io.IOException;

import com.monkey.core.enumeration.Protocol;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.utils.JsonParser;
import com.monkey.core.utils.MonkeyConstants;
import com.monkey.impl.config.MonkeyAppiumConfig;
import com.monkey.impl.config.MonkeySeleniumConfig;
import com.monkey.services.log.LogTrackerEvent;


public class ConfigurationBuilder {

	private static ConfigurationBuilder instance;

	/**
	 * Default constructor
	 */
	private ConfigurationBuilder() {

	}

	/**
	 * Return the instance of this singleton
	 * 
	 * @return
	 */
	public static ConfigurationBuilder getInstance() {
		if (ConfigurationBuilder.instance == null) {
            ConfigurationBuilder.instance = new ConfigurationBuilder();
		}
		return ConfigurationBuilder.instance;
	}

	/**
	 * Build that configuration that will be used by monkey. It override the
	 * default configuration by the custom one
	 * 
	 * @param path
	 * @return
	 */
	public static MonkeyConfig getmonkeyConfiguration(final String configFilePath, final String protocol) {
		if (configFilePath == null) {
			throw new MonkeyException(ExceptionCode.TESTNG_PARAM_NOT_FOUND,
					MonkeyConstants.CONFIG_FILE_TESTNG_PARAM_NAME);
		}
		if (protocol == null) {
			throw new MonkeyException(ExceptionCode.TESTNG_PARAM_NOT_FOUND,
					MonkeyConstants.PROTOCOL_TESTNG_PARAM_NAME);
		}

		LogTrackerEvent.trace(ConfigurationBuilder.class.getName(),
				"monkey " + protocol + " configuration file path : " + configFilePath);
		
		MonkeyConfig config = null;
		try {
			switch (Protocol.valueOf(protocol)) {
			case selenium:
				config = new JsonParser<MonkeySeleniumConfig>().parse(configFilePath, MonkeySeleniumConfig.class);
				config.setProtocol(Protocol.selenium);
				break;
			case appium:
				config = new JsonParser<MonkeyAppiumConfig>().parse(configFilePath, MonkeyAppiumConfig.class);
				config.setProtocol(Protocol.appium);
				break;
			}
		} catch (final IllegalArgumentException e) {
			throw new MonkeyException(ExceptionCode.TESTNG_PARAM_UNSUPPORTED,
					MonkeyConstants.PROTOCOL_TESTNG_PARAM_NAME, Protocol.selenium + " or " + Protocol.appium);
		} catch (final IOException e) {
			throw (new MonkeyException(ExceptionCode.JSON_MAPPING_ERROR, e.getMessage()));
		}

		return config;
	}

}
