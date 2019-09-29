package com.monkey.core.enumeration;

import java.util.Random;

public enum SupportedLanguage {
    fr, en, vn;

    private static SupportedLanguage defaultLanguage;

    public static SupportedLanguage getRandomLanguage() {
        final SupportedLanguage[] languages = SupportedLanguage.values();
        final Random generator = new Random();
        return languages[generator.nextInt(languages.length)];
    }

    public static SupportedLanguage getCalculatedLanguage() {
        return SupportedLanguage.defaultLanguage;
    }

    public static void setDefaultLanguage(final SupportedLanguage defaultLanguage) {
        SupportedLanguage.defaultLanguage = defaultLanguage;
    }

}
