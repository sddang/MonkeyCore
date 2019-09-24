
/**
 * Package used to manage the Selenium driver life cycle
 */
package com.monkey.core.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.internal.utils.configuration.GridNodeConfiguration;
import org.openqa.grid.shared.GridNodeServer;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.remote.server.SeleniumServer;

import com.monkey.core.config.MonkeyConfig;
import com.monkey.core.enumeration.SupportedVarEnv;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.session.ExecutionManager;
import com.monkey.impl.config.MonkeyAppiumConfig;
import com.monkey.impl.config.MonkeySeleniumConfig;
import com.monkey.impl.drivers.DriverType;
import com.monkey.impl.drivers.MonkeyAndroidDriver;
import com.monkey.impl.drivers.MonkeyIosDriver;
import com.monkey.impl.drivers.MonkeySeleniumDriver;
import com.monkey.services.data.DataMapper;
import com.monkey.services.log.LogTrackerEvent;

public class MonkeyDriverSetup {

	public static final String IGNORE_UNREQUESTED_POPUPS = "Ignore Unrequested Popups";

	private static final String EXE = ".exe";
	private static final String WIN = "win";
	private static final String OS_NAME = "os.name";
	private static final String WEBDRIVER_IE_DRIVER = "webdriver.ie.driver";
	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String WEBDRIVER_FIREFOX_DRIVER = "webdriver.gecko.driver";

	private static final String WEBDRIVER_IE_DRIVER_Value = "src/main/resources/org/openqa/grid/common/drivers/IEDriverServer";
	private static final String WEBDRIVER_CHROME_DRIVER_Value = "src/main/resources/org/openqa/grid/common/drivers/chromedriver";
	private static final String WEBDRIVER_FIREFOX_DRIVER_Value = "src/main/resources/org/openqa/grid/common/drivers/geckodriver";

	private static final Object INTERNET_EXPLORER = "internet explorer";
	private static final String HUB_CONFIG_JSON = "config/HubConfig.json";
	private static final String NODE_CONFIG_JSON = "config/NodeConfig.json";

	// ----------------Driver setup----------------------------------

	public static void start() {
		final MonkeyConfig config = ExecutionManager.getConfiguration();
		LogTrackerEvent.trace(MonkeyDriverSetup.class.getName(), "Start new " + config.getDriverType());

		MonkeyDriver driver = null;

		try {
			switch (config.getDriverType()) {
			case webDriver:
				driver = new MonkeySeleniumDriver(new URL(config.getHubUrl()), config.getCapabilities());
				break;
			case androidDriver:
					driver = new MonkeyAndroidDriver(new URL(config.getHubUrl()), config.getCapabilities());
				break;
			case iosDriver:
					driver = new MonkeyIosDriver(new URL(config.getHubUrl()), config.getCapabilities());
				break;
			}
		} catch (final MalformedURLException e) {
			throw new MonkeyException(ExceptionCode.MAL_FORMED_URL, config.getHubUrl());
		} catch (final UnreachableBrowserException e) {
			throw new MonkeyException(ExceptionCode.SESSION_COULD_NOT_START, e.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
			throw new MonkeyException(ExceptionCode.SESSION_COULD_NOT_START, e.getMessage());
		}

		ExecutionManager.put(driver);
	}

	public static void set(final String dataFilePath, final String testFileName) {
		final MonkeyConfig config = ExecutionManager.getConfiguration();
		MonkeyDriverSetup.setDataMapper(ExecutionManager.getMonkeyDriver(), dataFilePath);
		MonkeyDriverSetup.setLanguage(ExecutionManager.getMonkeyDriver(), config);
		ExecutionManager.getMonkeyDriver().setTestFileName(testFileName);
		if (config.getDriverType().name().equals(DriverType.webDriver.name())) {
			ExecutionManager.getConfiguration().fireUpdateGeoUrlForOldBrands();
		}
	}

	private static void setDataMapper(final MonkeyDriver driver, final String dataFilePath) {
		final DataMapper dataMapper = new DataMapper();
		if (dataFilePath != null) {
			LogTrackerEvent.trace(MonkeyDriverSetup.class.getName(), "Set data mapping file : " + dataFilePath);
			dataMapper.setPath(dataFilePath);
			dataMapper.initMapper();
		}
		driver.setDataMapper(dataMapper);
	}

	private static void setLanguage(final MonkeyDriver driver, final MonkeyConfig config) {
		String language = "fr";

		switch (config.getProtocol()) {
		case selenium:
			final String calculatedLanguage = ExecutionManager.getExecutionContext().get(SupportedVarEnv.language.name());
			language = calculatedLanguage;
			break;

		case appium:
			if (!((MonkeyAppiumConfig) config).getLanguage().isEmpty()) {
				language = ((MonkeyAppiumConfig) config).getLanguage();
			}
			break;
		}

		LogTrackerEvent.trace(MonkeyDriverSetup.class.getName(), "Set data language : " + language);
		driver.setLanguage(language);
	}

	public static void closeDriver() {
		final MonkeyDriver driver = ExecutionManager.getMonkeyDriver();
		final MonkeyConfig config = ExecutionManager.getConfiguration();
		if (driver != null) {
			LogTrackerEvent.trace(MonkeyDriverSetup.class.getName(), "Close driver");
			try {
				switch (config.getProtocol()) {
				case selenium:
					driver.close();
					break;
				case appium:
					driver.quit();
					break;
				}
			} catch (final SessionNotCreatedException e) {
				new MonkeyException(ExceptionCode.SESSION_NOT_FOUND_EXCEPTION, e.getMessage());
			} finally {
				ExecutionManager.removeMonkeyDriver();
			}

		}
	}

	// ----------------Selenium Grid Setup----------------------------------
	/**
	 * Build the grid platform switch the configuration. If the execution is
	 * locally monkey will build profil new Grid locally . If the execution is
	 * remotely monkey will do no thing just connect tests to the remote Hub
	 * 
	 * @param configuration
	 */
	public static void launchGridPlatform(final MonkeyConfig configuration) {
		if (configuration.getDriverType() == DriverType.webDriver)
			try {
				LogTrackerEvent.trace(MonkeyDriverSetup.class.getName(), "LaunchGridPlatform");
				if (configuration.isLocalExecution()) {
					MonkeyDriverSetup.setupDrivers((MonkeySeleniumConfig) configuration);
					ExecutionManager.hub = new Hub(GridHubConfiguration.loadFromJSON(MonkeyDriverSetup.HUB_CONFIG_JSON));
					ExecutionManager.hub.start();
					final GridNodeConfiguration nodeConfig = GridNodeConfiguration.loadFromJSON(MonkeyDriverSetup.NODE_CONFIG_JSON);
					final RegistrationRequest req = new RegistrationRequest(nodeConfig);
					//req.build(nodeConfig);					
					ExecutionManager.node = new SelfRegisteringRemote(req);
					final GridNodeServer server = new SeleniumServer(req.getConfiguration());
					ExecutionManager.node.setRemoteServer(server);
					ExecutionManager.node.startRemoteServer();
					ExecutionManager.node.startRegistrationProcess();
					Thread.sleep(1000);
					LogTrackerEvent.trace(MonkeyDriverSetup.class.getName(),
							"\n\n START LOCAL GRID -------------------------------\n");
				} else {
					LogTrackerEvent.trace(MonkeyDriverSetup.class.getName(),
							"\n\n USE REMOTE GRID -------------------------------\n");
				}

			} catch (final Exception e) {
				throw new MonkeyException(ExceptionCode.STARTING_LOCAL_GRID_ERROR, e.getCause(), e.getMessage());
			}
	}

	/**
	 * Stop the Grid if the execution is locally
	 * 
	 * @param configuration
	 */
	public static void stopGridPlatform(final MonkeyConfig configuration) {
		if (configuration.getDriverType() == DriverType.webDriver) {
			if (configuration.isLocalExecution()) {
				LogTrackerEvent.trace(MonkeyDriverSetup.class.getName(),
						"\n\n STOPPING LOCAL GRID -------------------------------");
				ExecutionManager.node.deleteAllBrowsers();
				ExecutionManager.node.stopRemoteServer();
				LogTrackerEvent.trace(MonkeyDriverSetup.class.getName(), "--- DONE -------------------------------\n");
			}
		}
	}

	// ---------------Third Party Drivers
	// Setup----------------------------------
	/**
	 * Setup the driver of selenium with all the input configuration
	 * 
	 * @param configuartion
	 */
	private static void setupDrivers(final MonkeySeleniumConfig configuartion) {
		final String os = System.getProperty(MonkeyDriverSetup.OS_NAME).toLowerCase().substring(0, 3);
		final String chromeDriver = MonkeyDriverSetup.WEBDRIVER_CHROME_DRIVER_Value;
		final String ieDriver = MonkeyDriverSetup.WEBDRIVER_IE_DRIVER_Value;
		final String ffDriver = MonkeyDriverSetup.WEBDRIVER_FIREFOX_DRIVER_Value;

		// *********** CHROME SETUP
		if (chromeDriver != null) {
			final String chromeBinary = chromeDriver + (os.equals(MonkeyDriverSetup.WIN) ? MonkeyDriverSetup.EXE : "");
			System.setProperty(MonkeyDriverSetup.WEBDRIVER_CHROME_DRIVER, chromeBinary);
		}
		
		// *********** FF SETUP
				if (ffDriver != null) {
					final String ffBinary = ffDriver + (os.equals(MonkeyDriverSetup.WIN) ? MonkeyDriverSetup.EXE : "");
					System.setProperty(MonkeyDriverSetup.WEBDRIVER_FIREFOX_DRIVER, ffBinary);
				}

		// *********** IE SETUP
		if (ieDriver != null) {
			if (!os.equals(MonkeyDriverSetup.WIN)) {
				if (MonkeyDriverSetup.INTERNET_EXPLORER.equals(configuartion.getBrowserName())) {
				}
			} else {
				final String ieBinary = ieDriver + MonkeyDriverSetup.EXE;
				System.setProperty(MonkeyDriverSetup.WEBDRIVER_IE_DRIVER, ieBinary);
			}
		}

	}
	
}
