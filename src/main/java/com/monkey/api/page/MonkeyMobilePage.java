package com.monkey.api.page;

import java.lang.reflect.Field;

import com.monkey.api.page.decorator.MonkeyMobileDecorator;
import com.monkey.core.page.MonkeyAbstractPage;

public class MonkeyMobilePage extends MonkeyAbstractPage {

	public MonkeyMobilePage() {
        MonkeyMobilePage.initElements(new MonkeyMobileDecorator(), this);
	}

	private static void initElements(final MonkeyMobileDecorator decorator, final Object page) {
		Class<?> proxyIn = page.getClass();
		while (proxyIn != Object.class) {
            MonkeyMobilePage.proxyFields(decorator, page, proxyIn);
			proxyIn = proxyIn.getSuperclass();
		}
	}

	private static void proxyFields(final MonkeyMobileDecorator decorator, final Object page, final Class<?> proxyIn) {
		final Field[] fields = proxyIn.getDeclaredFields();
		for (final Field field : fields) {
			final Object value = decorator.decorate(page.getClass().getClassLoader(), field);
			if (value != null) {
				try {
					field.setAccessible(true);
					field.set(page, value);
				} catch (final IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

}
