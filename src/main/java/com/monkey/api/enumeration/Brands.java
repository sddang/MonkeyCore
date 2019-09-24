package com.monkey.api.enumeration;

import java.util.Random;

public enum Brands {

	accorhotels, ibis, novotel, hotelf1, sofitel, mercure, pullmanhotels, mgallery, adagio_city;

	private static Brands defaultBrand = Brands.accorhotels;

	public static boolean hasNewLookBrand(final Brands brand) {
		switch (brand) {
		case accorhotels:
			return true;
		case ibis:
			return true;
		case novotel:
			return true;
		case hotelf1:
			return true;
		default:
			return false;
		}
	}

	public static String getsupportedLanguage(final String language) {
		final String supportedLanguage;
		supportedLanguage = supportLanguage(getCalculatedBrand(), SupportedLanguage.valueOf(language))
				.name();
		return supportedLanguage;

	}

	private static SupportedLanguage supportLanguage(final Brands brand, final SupportedLanguage language) {

		// switch (brand) {
		// case accorhotels:
		// return language;
		// case ibis:
		// switch (language) {
		// case ar:
		// case ko:
		// case tr:
		// case th:
		// case sv:
		// return SupportedLanguage.fr;
		// default:
		// return language;
		// }
		// case novotel:
		// switch (language) {
		// case ar:
		// case ko:
		// case tr:
		// case th:
		// case sv:
		// case ja:
		// case id:
		// return SupportedLanguage.fr;
		// default:
		// return language;
		// }
		// case hotelf1:
		// switch (language) {
		// case fr:
		// return language;
		// case gb:
		// return language;
		// default:
		// return SupportedLanguage.fr;
		// }
		// case pullmanhotels:
		// switch (language) {
		// case fr:
		// case gb:
		// case de:
		// case es:
		// case id:
		// return language;
		// default:
		// return SupportedLanguage.fr;
		// }
		// case mercure:
		// switch (language) {
		// case ar:
		// case ko:
		// case tr:
		// case th:
		// case sv:
		// case ja:
		// case id:
		// return SupportedLanguage.fr;
		// default:
		// return language;
		// }
		// case sofitel:
		// switch (language) {
		// case fr:
		// case gb:
		// case de:
		// case es:
		// case it:
		// return language;
		// default:
		// return SupportedLanguage.fr;
		// }
		// default:
		// return SupportedLanguage.fr;
		// }

		switch (brand) {
		case accorhotels:
			switch (language) {
			case fr:
			case gb:
			case ja:
				return language;
			default:
				return SupportedLanguage.fr;
			}
		case ibis:
			switch (language) {
			case fr:
			case gb:
			case ja:
				return language;
			default:
				return SupportedLanguage.fr;
			}
		case novotel:
			switch (language) {
			case fr:
			case gb:
				return language;
			default:
				return SupportedLanguage.fr;
			}
		case hotelf1:
			switch (language) {
			case fr:
			case gb:
				return language;
			default:
				return SupportedLanguage.fr;
			}
		case pullmanhotels:
			switch (language) {
			case fr:
			case gb:
				return language;
			default:
				return SupportedLanguage.fr;
			}
		case mercure:
			switch (language) {
			case fr:
			case gb:
				return language;
			default:
				return SupportedLanguage.fr;
			}
		case sofitel:
			switch (language) {
			case fr:
			case gb:
				return language;
			default:
				return SupportedLanguage.fr;
			}
		default:
			return SupportedLanguage.fr;
		}

	}

	public static Brands getRandomBrand() {
		final Brands[] brands = Brands.values();
		final Random generator = new Random();
		return brands[generator.nextInt(brands.length-2)];
	}

	public static Brands getCalculatedBrand() {
		return Brands.defaultBrand;
	}

	public static void setDefaultBrand(final Brands defaultBrand) {
		Brands.defaultBrand = defaultBrand;
	}

}
