
package com.monkey.core.config;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.monkey.api.enumeration.Brands;
import com.monkey.core.enumeration.BrowserMode;
import com.monkey.core.enumeration.Protocol;
import com.monkey.core.enumeration.SupportedVarEnv;
import com.monkey.core.session.ExecutionManager;
import com.monkey.impl.drivers.DriverType;


public abstract class MonkeyConfig {

	private Protocol protocol;

	// execution config
	protected DriverType driverType;
	private long timeOut;
	private int retryTestingCount;
	private boolean highlightActiveElement;
	private boolean localExecution;
	private boolean generateFunctionalDoc;
	private boolean generateTechnicalDoc;
	private boolean extentx;
	private boolean verbose;
	private String hubUrl;

	public Protocol getProtocol() {
		return this.protocol;
	}

	public void setProtocol(final Protocol protocol) {
		this.protocol = protocol;
	}

	public DriverType getDriverType() {
		return this.driverType;
	}

	public void setDriverType(final DriverType type) {
        driverType = type;
	}

	/**
	 * Return the timeOut used when searching or firing events
	 * 
	 * @return
	 */
	public long getTimeOut() {
		return this.timeOut;
	}

	/**
	 * 
	 * @param timeOut
	 */
	public void setTimeOut(final long timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * Return retry times count to execute tests. IF it is > 1 monkey retry
	 * executing tests on failure cases until test is passing or throws one
	 * error
	 * 
	 * @return
	 */
	public int getRetryTestingCount() {
		return this.retryTestingCount;
	}

	/**
	 * 
	 * @param retryTestingCount
	 */
	public void setRetryTestingCount(final int retryTestingCount) {
		this.retryTestingCount = retryTestingCount;
	}

	/**
	 * Highlight current element if it returns true
	 * 
	 * @return
	 */
	public boolean isHighlightActiveElement() {
		return this.highlightActiveElement;
	}

	/**
	 * 
	 * @param highlightActiveElement
	 */
	public void setHighlightActiveElement(final boolean highlightActiveElement) {
		this.highlightActiveElement = highlightActiveElement;
	}

	/**
	 * When it returns true, the monkey Framework will setup the Selenium grid
	 * locally
	 * 
	 * @return the localExecution
	 */
	public boolean isLocalExecution() {
		return this.localExecution;
	}

	/**
	 * 
	 * @param localExecution
	 */
	public void setLocalExecution(final boolean localExecution) {
		this.localExecution = localExecution;
	}
	
	public boolean isGenerateFunctionalDoc() {
		return this.generateFunctionalDoc;
	}

	public void setGenerateFunctionalDoc(final boolean generateFunctionalDoc) {
		this.generateFunctionalDoc = generateFunctionalDoc;
	}

	public boolean isGenerateTechnicalDoc() {
		return this.generateTechnicalDoc;
	}

	public void setGenerateTechnicalDoc(final boolean generateTechnicalDoc) {
		this.generateTechnicalDoc = generateTechnicalDoc;
	}

	public String getHubUrl() {
		return this.hubUrl;
	}

	public void setHubUrl(final String hubUrl) {
		this.hubUrl = hubUrl;
	}
	
	public abstract DesiredCapabilities getCapabilities();
	
	public boolean isVerbose() {
		return this.verbose;
	}

	public void setVerbose(final boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isExtentx() {
		return this.extentx;
	}

	public void setExtentx(final boolean extentx) {
		this.extentx = extentx;
	}
	

	public void fireUpdateGeoUrlForOldBrands() {
		if (!Brands.hasNewLookBrand(
				Brands.valueOf(ExecutionManager.getExecutionContext().get(SupportedVarEnv.brand.name())))) {
			ExecutionManager.getMonkeyDriver().get(this.getGeolocalisationUrl());
		}
	}

	public String getGeolocalisationUrl() {
		final String brand = ExecutionManager.getExecutionContext().get(SupportedVarEnv.brand.name()).replaceAll("_", "-");
		final String calculatedLanguage = ExecutionManager.getExecutionContext().get(SupportedVarEnv.language.name())
				.replaceAll("_", "-");
		final String env = ExecutionManager.getExecutionContext().get(SupportedVarEnv.environment.name());
		final String prefix = env.equals("prod") ? "www." : env + "-www.";

//		String url = "http://" + prefix + brand + ".com/geo/setZone.jsp?lang=" + calculatedLanguage + "&country="
//				+ calculatedLanguage;
//		String url = "http://" + prefix + brand + ".com/" + calculatedLanguage + "/home/index.shtml";
		final String url = "http://" + prefix + brand + ".com/" + calculatedLanguage + "/geoloc/selectdisplayzone.jshtml";
		return url;

	}

	public String getRootUrl() {
		final String url;
		final String brand = ExecutionManager.getExecutionContext().get(SupportedVarEnv.brand.name()).replaceAll("_", "-");
		final String calculatedLanguage = ExecutionManager.getExecutionContext().get(SupportedVarEnv.language.name())
				.replaceAll("_", "-");
		final String env = ExecutionManager.getExecutionContext().get(SupportedVarEnv.environment.name());

		if (this.isSmartphone() || this.isTablette()) {
			final String prefix = env.equals("prod") ? "m." : env + "-m.";
			url = "http://" + prefix + brand + ".com/" + calculatedLanguage + "/home/index.shtml";
		} else {
			final String prefix = env.equals("prod") ? "www." : env + "-www.";

			url = "http://" + prefix
					+ brand + ".com/" + calculatedLanguage + "/"
					+ ExecutionManager.getExecutionContext().get(SupportedVarEnv.localization.name()) + "/index.shtml";
		}

		return url;
	}

	protected boolean isTablette() {
		return ExecutionManager.getExecutionContext().get(SupportedVarEnv.browsermode.name())
				.equals(BrowserMode.tablette.name());
	}

	protected boolean isSmartphone() {
		return ExecutionManager.getExecutionContext().get(SupportedVarEnv.browsermode.name())
				.equals(BrowserMode.smartphone.name());
	}

	protected boolean isBrowser() {
		return ExecutionManager.getExecutionContext().get(SupportedVarEnv.browsermode.name())
				.equals(BrowserMode.desktop.name());
	}

	public String getBaseUrl() {
		final String url;
		final String brand = ExecutionManager.getExecutionContext().get(SupportedVarEnv.brand.name()).replaceAll("_", "-");
		final String calculatedLanguage = ExecutionManager.getExecutionContext().get(SupportedVarEnv.language.name())
				.replaceAll("_", "-");
		final String env = ExecutionManager.getExecutionContext().get(SupportedVarEnv.environment.name());
		final String prefix;
		if (this.isSmartphone() || this.isTablette()) {
			prefix = env.equals("prod") ? "m." : env + "-m.";
			url = "http://" + prefix + brand + ".com/" + calculatedLanguage;
		} else {
			prefix = env.equals("prod") ? "www." : env + "-www.";
			url = "http://" + prefix + brand + ".com/" + calculatedLanguage;
		}
		return url;
	}

	public String getSecuredRootUrl() {
		final String env = ExecutionManager.getExecutionContext().get(SupportedVarEnv.environment.name());
		final boolean isProd = env.equals("prod");
		if(isProd){
			return this.getRootUrl().replace("http", "https").replace("m.", "secure-m.");
		}
		return this.getRootUrl().replace("http", "https").replace("-m.", "-secure-m.");
	}

	public String getSecuredBaseUrl() {
		final String env = ExecutionManager.getExecutionContext().get(SupportedVarEnv.environment.name());
		final boolean isProd = env.equals("prod");
		if(isProd){
			return this.getRootUrl().replace("http", "https").replace("m.", "secure-m.");
		}
		return this.getRootUrl().replace("http", "https").replace("-m.", "-secure-m.");
	}

}