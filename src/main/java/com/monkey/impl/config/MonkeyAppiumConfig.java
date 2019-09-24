
package com.monkey.impl.config;

import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.monkey.core.config.MonkeyConfig;
import com.monkey.impl.drivers.DriverType;



public class MonkeyAppiumConfig extends MonkeyConfig {

	/**
	 * DOC : http://appium.io/slate/en/master/?ruby#appium-server-capabilities
	 */
	// appium / Selendroid
	private String automationName;
	private String platformName;
	private String platformVersion;
	private String deviceName;
	private String app;
	private String browserName;
	private String newCommandTimeout;
	private boolean autoLaunch;
	private String language;
	private String locale;
	private String udid;

	// ios
	private String xcodeOrgId;
	private String xcodeSigningId;
	private String useNewWDA;
	private String startIWDP;

	// LANDSCAPE / PORTRAIT
	private String orientation;
	private boolean autoWebview;
	private boolean noReset;
	private boolean fullReset;
	private String bundleId;
	private String appPackage;
	private String appActivity;
	// KeyBoard properties
	private boolean unicodeKeyboard;
	private boolean resetKeyboard;
	private String sendKeyStrategy;

	public String getAutomationName() {
		return this.automationName;
	}

	public void setAutomationName(final String automationName) {
		this.automationName = automationName;
	}

	public String getPlatformName() {
		return this.platformName;
	}

	public void setPlatformName(final String platformName) {
		this.platformName = platformName;
	}

	public String getPlatformVersion() {
		return this.platformVersion;
	}

	public void setPlatformVersion(final String platformVersion) {
		this.platformVersion = platformVersion;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(final String deviceName) {
		this.deviceName = deviceName;
	}

	public String getApp() {
		return this.app;
	}

	public void setApp(final String app) {
		this.app = app;
	}

	public String getBrowserName() {
		return this.browserName;
	}

	public void setBrowserName(final String browserName) {
		this.browserName = browserName;
	}

	public String getNewCommandTimeout() {
		return this.newCommandTimeout;
	}

	public void setNewCommandTimeout(final String newCommandTimeout) {
		this.newCommandTimeout = newCommandTimeout;
	}

	public boolean isAutoLaunch() {
		return this.autoLaunch;
	}

	public void setAutoLaunch(final boolean autoLaunch) {
		this.autoLaunch = autoLaunch;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

	public String getLocale() {
		return this.locale;
	}

	public void setLocale(final String locale) {
		this.locale = locale;
	}

	public String getUdid() {
		return this.udid;
	}

	public void setUdid(final String udid) {
		this.udid = udid;
	}

	public String getOrientation() {
		return this.orientation;
	}

	public void setOrientation(final String orientation) {
		this.orientation = orientation;
	}

	public boolean isAutoWebview() {
		return this.autoWebview;
	}

	public void setAutoWebview(final boolean autoWebview) {
		this.autoWebview = autoWebview;
	}

	public boolean isNoReset() {
		return this.noReset;
	}

	public void setNoReset(final boolean noReset) {
		this.noReset = noReset;
	}

	public boolean isFullReset() {
		return this.fullReset;
	}

	public void setFullReset(final boolean fullReset) {
		this.fullReset = fullReset;
	}

	public String getBundleId() {
		return this.bundleId;
	}

	public void setBundleId(final String bundleId) {
		this.bundleId = bundleId;
	}

	public String getAppPackage() {
		return this.appPackage;
	}

	public void setAppPackage(final String appPackage) {
		this.appPackage = appPackage;
	}

	public String getAppActivity() {
		return this.appActivity;
	}

	public void setAppActivity(final String appActivity) {
		this.appActivity = appActivity;
	}

	public DesiredCapabilities getCapabilities() {

		final HashMap<String, Object> map = new HashMap<>();
		map.put("useNewWDA", this.useNewWDA);
		map.put("startIWDP", this.startIWDP);
		map.put("xcodeOrgId", this.xcodeOrgId);
		map.put("xcodeSigningId", this.xcodeSigningId);
		map.put("browserName", this.browserName);
		map.put("platformVersion", this.platformVersion);
		map.put("platformName", this.platformName);
		map.put("deviceName", this.deviceName);
		map.put("bundleId", this.bundleId);
		map.put("newCommandTimeout", this.newCommandTimeout);
		map.put("sendKeyStrategy", this.sendKeyStrategy);
		map.put("hubUrl", this.getHubUrl());
		map.put("automationName", this.getAutomationName());
		map.put("xcodeOrgId", this.getXcodeOrgId());
		map.put("xcodeSigningId", this.getXcodeSigningId());
		map.put("udid", this.getUdid());
		map.put("platformName", this.getPlatformName());
		map.put("platformVersion", this.getPlatformVersion());
		map.put("deviceName", this.getDeviceName());
		map.put("autoLaunch", this.isAutoLaunch());
		map.put("language", this.getLanguage());
		map.put("locale", this.getLocale());
		map.put("orientation", this.getOrientation());
		map.put("autoWebview", this.isAutoWebview());
		map.put("noReset", this.isNoReset());
		map.put("fullReset", this.isFullReset());

		if (this.driverType.equals(DriverType.iosDriver)) {
			map.put("useNewWDA", this.getUseNewWDA());
			map.put("bundleId", this.getBundleId());
		}

		if (this.driverType.equals(DriverType.androidDriver)) {
			map.put("appPackage", this.appPackage);
			map.put("appActivity", this.appActivity);
		}
		return new DesiredCapabilities(map);
	}

	public boolean isUnicodekeyboard() {
		return this.unicodeKeyboard;
	}

	public void setUnicodekeyboard(final boolean unicodekeyboard) {
		unicodeKeyboard = unicodekeyboard;
	}

	public boolean isResetkeyboard() {
		return this.resetKeyboard;
	}

	public void setResetkeyboard(final boolean resetkeyboard) {
		resetKeyboard = resetkeyboard;
	}

	public String isSendKeyStrategy() {
		return this.sendKeyStrategy;
	}

	public void setSendKeyStrategy(final String sendKeyStrategy) {
		this.sendKeyStrategy = sendKeyStrategy;
	}

	public String getXcodeOrgId() {
		return this.xcodeOrgId;
	}

	public void setXcodeOrgId(final String xcodeOrgId) {
		this.xcodeOrgId = xcodeOrgId;
	}

	public String getXcodeSigningId() {
		return this.xcodeSigningId;
	}

	public void setXcodeSigningId(final String xcodeSigningId) {
		this.xcodeSigningId = xcodeSigningId;
	}

	public String getUseNewWDA() {
		return this.useNewWDA;
	}

	public void setUseNewWDA(final String useNewWDA) {
		this.useNewWDA = useNewWDA;
	}

	public String getStartIWDP() {
		return this.startIWDP;
	}

	public void setStartIWDP(final String startIWDP) {
		this.startIWDP = startIWDP;
	}

}
