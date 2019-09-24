package com.monkey.api.enumeration;

import java.util.Random;

import com.monkey.core.enumeration.SupportedCountries;

public enum SupportedLanguage {
	fr, gb, de, es, it, pt, pt_br, nl, id, zh, tr, ko, ru, ja, ar, pl, th, sv;

	private static SupportedLanguage defaultLangauge;
	
	private static final String UNITED_KINGDOM = "united-kingdom";
	private static final String SOUTH_KORIA = "republic-of-korea";
	private static final String MIDDEL_EAST = "other-regions-of-middle-east";

	public static SupportedLanguage getRandomLanguage() {
		final SupportedLanguage[] languages = SupportedLanguage.values();
		final Random generator = new Random();
		return languages[generator.nextInt(languages.length)];
	}

	public static SupportedLanguage getCalculatedLangauge() {
		return SupportedLanguage.defaultLangauge;
	}

	public static void setDefaultLangauge(final SupportedLanguage defaultLangauge) {
		SupportedLanguage.defaultLangauge = defaultLangauge;
	}

	public static SupportedCountries getDefaultCountry() {
		switch (SupportedLanguage.defaultLangauge) {
		case fr:
			return SupportedCountries.france;
		case gb:
			return SupportedCountries.united_kingdom;
		case de:
			return SupportedCountries.deutschland;
		case es:
			return SupportedCountries.espana;
		case it:
			return SupportedCountries.italia;
		case pt:
			return SupportedCountries.portugal;
		case pt_br:
			return SupportedCountries.brasil;
		case nl:
			return SupportedCountries.begium;
		case id:
			return SupportedCountries.indonesia;
		case zh:
			return SupportedCountries.china;
		case tr:
			return SupportedCountries.turkey;
		case ko:
			return SupportedCountries.southKorea;
		case ru:
			return SupportedCountries.russia;
		case ja:
			return SupportedCountries.japan;
		case ar:
			return SupportedCountries.arabiArabia;
		case pl:
			return SupportedCountries.polska;
		case th:
			return SupportedCountries.thailand;
		case sv:
			return SupportedCountries.sweden;
		default:
			return SupportedCountries.france;
		}
	}
	
	public static String getDefaultLocalization() {
		switch (SupportedLanguage.defaultLangauge) {
		case fr:
			return SupportedCountries.france.name();
		case gb:
			return SupportedLanguage.UNITED_KINGDOM;
		case de:
			return SupportedCountries.deutschland.name();
		case es:
			return SupportedCountries.espana.name();
		case it:
			return SupportedCountries.italia.name();
		case pt:
			return SupportedCountries.portugal.name();
		case pt_br:
			return SupportedCountries.brasil.name();
		case nl:
			return SupportedCountries.begium.name();
		case id:
			return SupportedCountries.indonesia.name();
		case zh:
			return SupportedCountries.china.name();
		case tr:
			return SupportedCountries.turkey.name();
		case ko:
			return SupportedLanguage.SOUTH_KORIA;
		case ru:
			return SupportedCountries.russia.name();
		case ja:
			return SupportedCountries.japan.name();
		case ar:
			return SupportedLanguage.MIDDEL_EAST;
		case pl:
			return SupportedCountries.polska.name();
		case th:
			return SupportedCountries.thailand.name();
		case sv:
			return SupportedCountries.sweden.name();
		default:
			return SupportedCountries.france.name();
		}
	}
	
	
}
