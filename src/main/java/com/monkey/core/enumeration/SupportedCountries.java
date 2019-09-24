package com.monkey.core.enumeration;

public enum SupportedCountries {
	france, begium, united_kingdom, UnitedStatesOfAmerica, deutschland, espana, italia, portugal, belgium, china, polska, japan, brasil, russia, indonesia, southKorea, turkey, arabiArabia, thailand, sweden;

	public static SupportedContinent getContient(final SupportedCountries country) {
		switch (country) {
		case france:
			return SupportedContinent.EUROPE;
		case begium:
			return SupportedContinent.EUROPE;
		case united_kingdom:
			return SupportedContinent.EUROPE;
		case UnitedStatesOfAmerica:
			return SupportedContinent.AMERICA;
		case deutschland:
			return SupportedContinent.EUROPE;
		case espana:
			return SupportedContinent.EUROPE;
		case italia:
			return SupportedContinent.EUROPE;
		case portugal:
			return SupportedContinent.EUROPE;
		case belgium:
			return SupportedContinent.EUROPE;
		case china:
			return SupportedContinent.ASIA;
		case polska:
			return SupportedContinent.EUROPE;
		case japan:
			return SupportedContinent.ASIA;
		case brasil:
			return SupportedContinent.AMERICA;
		case russia:
			return SupportedContinent.EUROPE;
		case indonesia:
			return SupportedContinent.ASIA;
		case southKorea:
			return SupportedContinent.ASIA;
		case turkey:
			return SupportedContinent.ASIA;
		case arabiArabia:
			return SupportedContinent.ASIA;
		case thailand:
			return SupportedContinent.ASIA;
		case sweden:
			return SupportedContinent.EUROPE;

		default:
			return SupportedContinent.EUROPE;
		}
	}
}
