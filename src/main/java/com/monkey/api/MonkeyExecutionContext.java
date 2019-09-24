package com.monkey.api;

import java.util.Map;

import com.monkey.api.enumeration.Brands;
import com.monkey.core.enumeration.BrowserMode;
import com.monkey.core.enumeration.SupportedVarEnv;
import com.monkey.core.session.ExecutionManager;
import com.monkey.impl.config.MonkeySeleniumConfig;
import com.monkey.impl.drivers.DriverType;

public class MonkeyExecutionContext {
	
	public static Map<String, String> getExecutionContext(){
		return ExecutionManager.getExecutionContext();
	}

	public static String getLanguage(){
		return ExecutionManager.getExecutionContext().get(SupportedVarEnv.language.name()).replace("-", "_");
	}
	
	public static String getBrand(){
		return ExecutionManager.getExecutionContext().get(SupportedVarEnv.brand.name()).replace("-", "_");
	}
	
	public static String getEnvironment(){
		return ExecutionManager.getExecutionContext().get(SupportedVarEnv.environment.name());
	}
	
	public static String getBrowserMode(){
		return ExecutionManager.getExecutionContext().get(SupportedVarEnv.browsermode.name());
	}
	
	public static boolean isTabletMode() {
		return MonkeyExecutionContext.getBrowserMode().equals(BrowserMode.tablette.name());
	}

	public static boolean isDesktopMode() {
		return MonkeyExecutionContext.getBrowserMode().equals(BrowserMode.desktop.name());
	}

	public static boolean isSmartphoneMode() {
		return MonkeyExecutionContext.getBrowserMode().equals(BrowserMode.smartphone.name());
	}

	public static String getRootUrl() {
		return ExecutionManager.getConfiguration().getRootUrl();
	}

	public static String getSecuredRootUrl() {
		return ExecutionManager.getConfiguration().getSecuredRootUrl();
	}
	
	public static String getBaseUrl() {
		return ExecutionManager.getConfiguration().getBaseUrl();
	}

	public static String getSecuredBaseUrl() {
		
		return ExecutionManager.getConfiguration().getSecuredBaseUrl();
	}

	public static boolean brandHasnewLook() {
		return Brands.hasNewLookBrand(Brands.getCalculatedBrand());
	}
	
	public static String getContinent(){
		 return ExecutionManager.getExecutionContext().get(SupportedVarEnv.continent.name());
	}
	
	public static String getCountry(){
		 return ExecutionManager.getExecutionContext().get(SupportedVarEnv.country.name());
	}
	
	public static String getLocalisation(){
		 return ExecutionManager.getExecutionContext().get(SupportedVarEnv.localization.name());
	}
	
	public static void fireGeaolocalitionUrl(){
		if (!Brands.hasNewLookBrand(
				Brands.valueOf(ExecutionManager.getExecutionContext().get(SupportedVarEnv.brand.name())))) {
			ExecutionManager.getMonkeyDriver().get(ExecutionManager.getConfiguration().getGeolocalisationUrl());
			
		}else{
			ExecutionManager.getMonkeyDriver().get(ExecutionManager.getConfiguration().getRootUrl());
		}
	}
	
	public static boolean isIOS() {
		return ExecutionManager.getConfiguration().getDriverType().equals(DriverType.iosDriver);
	}

	public static boolean isAndroid() {
		return ExecutionManager.getConfiguration().getDriverType().equals(DriverType.androidDriver);
	}
	
}
