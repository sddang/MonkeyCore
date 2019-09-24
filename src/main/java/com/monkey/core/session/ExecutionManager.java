
/**
 * Session package is where monkey manage the execution sessions
 */
package com.monkey.core.session;

import java.util.HashMap;
import java.util.Map;

import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.web.Hub;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;

import com.monkey.api.enumeration.Brands;
import com.monkey.api.enumeration.SupportedLanguage;
import com.monkey.core.config.ConfigurationBuilder;
import com.monkey.core.config.MonkeyConfig;
import com.monkey.core.driver.MonkeyDriver;
import com.monkey.core.driver.MonkeyDriverSetup;
import com.monkey.core.enumeration.BrowserMode;
import com.monkey.core.enumeration.SupportedCountries;
import com.monkey.core.enumeration.SupportedVarEnv;
import com.monkey.core.exception.ExceptionCode;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.utils.MonkeyConstants;
import com.monkey.services.ScreenShotTaker;
import com.monkey.services.log.LogTrackerEvent;
import com.monkey.services.report.ExtentReport;
import com.monkey.services.report.MonkeyExtentReport;

import bsh.This;


public class ExecutionManager implements ISuiteListener {
	private static final ThreadLocal<MonkeyDriver> sessionDriver = new InheritableThreadLocal<MonkeyDriver>();
	private static MonkeyConfig configuration;

	public static Hub hub;
	public static SelfRegisteringRemote node;
	public static boolean isFirstLaunch;

	private static MonkeyExtentReport report;
	private static Map<String, String> executionContext = new HashMap<String, String>();
	private static String protocol;

	/**
	 * Return the current monkey driver for each test. The concurrency is
	 * managed in this class. This method is very used when ever we want to
	 * interact with Selenium
	 * 
	 * @return
	 */

	public static MonkeyDriver getMonkeyDriver() {
		if (ExecutionManager.sessionDriver.get() == null) {
			throw new MonkeyException(ExceptionCode.SESSION_COULD_NOT_START,
					"Driver is not instantiated yet, there is a problem some where");
		}
		return ExecutionManager.sessionDriver.get();
	}
	
	public static boolean monkeyDriverStarted() {
        return ExecutionManager.sessionDriver.get() != null;
	}

	/**
	 * Put the driver in the ThreaLocal Set. This method will be called each
	 * time we build profil new driver (for each test)
	 * 
	 * @param driver
	 */
	public static void put(final MonkeyDriver driver) {
        ExecutionManager.sessionDriver.set(driver);
	}
	
	public static void removeMonkeyDriver() {
        ExecutionManager.sessionDriver.remove();
	}
	
	/**
	 * Get the configuration Object of the execution
	 * 
	 * @return
	 */
	public static MonkeyConfig getConfiguration() {
		return ExecutionManager.configuration;
	}

	/**
	 * This Method is called before starting all the tests, It setup the
	 * execution environment switch to the configuration
	 */
	@Override
	public void onStart(final ISuite suite) {
        ExecutionManager.caclulateAndInitExectionContext();
		final String configFile = suite.getXmlSuite().getAllParameters().get(MonkeyConstants.CONFIG_FILE_TESTNG_PARAM_NAME);
        ExecutionManager.setProtocol(suite.getXmlSuite().getAllParameters().get(MonkeyConstants.PROTOCOL_TESTNG_PARAM_NAME));
		configuration = ConfigurationBuilder.getmonkeyConfiguration(configFile, ExecutionManager.getProtocol());
		ScreenShotTaker.cleanUpScreenShotDir();
        ExecutionManager.report = ExtentReport.createReport(suite.getName());
		MonkeyDriverSetup.launchGridPlatform(ExecutionManager.getConfiguration());
	}

	public static void caclulateAndInitExectionContext() {

		String environment = System.getenv(SupportedVarEnv.environment.name());
		String brand = System.getenv(SupportedVarEnv.brand.name());
		String language = System.getenv(SupportedVarEnv.language.name());
		final String country;
		String browserMode = System.getenv(SupportedVarEnv.browsermode.name());
		String localization = System.getenv(SupportedVarEnv.localization.name());
		
		final String contient;
		LogTrackerEvent.trace(This.class.getName(), "Test suite Initial Context : [" + environment + ", " + brand + ", "
				+ language + ", " + localization + ", " + browserMode + "]");

		if (environment == null || environment.equals("")) {
			LogTrackerEvent.trace(This.class.getName(),
					"Warning :  Variable <environment> not set in launch configuration");
			environment = "rec2";
		}
		if (brand == null || brand.equals("")) {
			LogTrackerEvent.trace(This.class.getName(), "Warning :  Variable <brand> not set in launch configuration");
			brand = Brands.getRandomBrand().name();
		}
		if (language == null || language.equals("")) {
			LogTrackerEvent.trace(This.class.getName(),
					"Warning :  Variable <language> not set in launch configuration");
			language = SupportedLanguage.getRandomLanguage().name();
		}
		if (browserMode == null || browserMode.equals("")) {
			LogTrackerEvent.trace(This.class.getName(),
					"Warning : Variable <browsermode> not set in launch configuration");
			browserMode = BrowserMode.getRandomBrowserMode().name();

		}
		Brands.setDefaultBrand(Brands.valueOf(brand));
		BrowserMode.setDefaultBrowserMode(BrowserMode.valueOf(browserMode));
		SupportedLanguage.setDefaultLangauge(SupportedLanguage.valueOf(Brands.getsupportedLanguage(language)));
		if (localization == null || localization.equals("")) {
			LogTrackerEvent.trace(This.class.getName(),
					"Warning :  Variable <localization> not set in launch configuration");
			localization = SupportedLanguage.getDefaultLocalization();
		}
		country = SupportedLanguage.getDefaultCountry().name();
		contient = SupportedCountries.getContient(SupportedCountries.valueOf(country)).name();

        ExecutionManager.executionContext.put(SupportedVarEnv.environment.name(), environment);
        ExecutionManager.executionContext.put(SupportedVarEnv.brand.name(), Brands.getCalculatedBrand().name().replaceAll("_", "-"));
        ExecutionManager.executionContext.put(SupportedVarEnv.language.name(),
				SupportedLanguage.getCalculatedLangauge().name().replaceAll("_", "-"));
        ExecutionManager.executionContext.put(SupportedVarEnv.browsermode.name(), BrowserMode.getCalculatedBrowserMode().name());
        ExecutionManager.executionContext.put(SupportedVarEnv.localization.name(), localization);
        ExecutionManager.executionContext.put(SupportedVarEnv.country.name(), country);
        ExecutionManager.executionContext.put(SupportedVarEnv.continent.name(), contient);

		LogTrackerEvent.trace(This.class.getName(), "Test suite will be executed on the calculated Context : ["
				+ environment + ", " + brand + ", " + language + ", " + localization + ", " + browserMode + "]");

	}

	public void cleanUpResults(final ISuite suite) {
		LogTrackerEvent.trace(getClass().getName(),
				"Cleaning up duplicate test failures in suite '" + suite.getName() + "' ...");
		Map<String, ISuiteResult> results = suite.getResults();
		int removedFailures = 0;
		for (final ISuiteResult result : results.values()) {
			ITestContext testContext = result.getTestContext();

			removedFailures += TestListenerUtil.cleanUpDuplicateFailures(testContext);
		}
		LogTrackerEvent.trace(ExecutionManager.class.getName(),
				"Removed " + removedFailures + " duplicate test failure(s) from suite '" + suite.getName() + "'");
	}

	/**
	 * This method is called at the end of the execution
	 */
	@Override
	public void onFinish(final ISuite suite) {
		MonkeyDriverSetup.stopGridPlatform(ExecutionManager.getConfiguration());
        this.cleanUpResults(suite);
		ExtentReport.closeExtentReport();
	}

	public static MonkeyExtentReport getReport() {
		return ExecutionManager.report;
	}

	public static Map<String, String> getExecutionContext() {
		return ExecutionManager.executionContext;
	}

	public static void setExecutionContext(final Map<String, String> executionContext) {
		ExecutionManager.executionContext = executionContext;
	}

	public static String getProtocol() {
		return ExecutionManager.protocol;
	}

	public static void setProtocol(final String protocol) {
		ExecutionManager.protocol = protocol;
	}

}
