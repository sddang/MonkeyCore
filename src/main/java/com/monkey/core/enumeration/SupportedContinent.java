package com.monkey.core.enumeration;

public enum SupportedContinent {
	AMERICA, AFRICA, EUROPE, ASIA;

	private static SupportedContinent defaultContinent = SupportedContinent.EUROPE;


	public static SupportedContinent getCalculatedContinent() {
		return SupportedContinent.defaultContinent;
	}

	public static void setDefaultBrowserMode(final SupportedContinent defaultContinent) {
		SupportedContinent.defaultContinent = defaultContinent;
	}
}
