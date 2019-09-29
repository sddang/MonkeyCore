package com.monkey.api.page.decorator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.monkey.api.annotation.AndroidLocator;
import com.monkey.api.annotation.IosLocator;
import com.monkey.api.page.MonkeyMobileElement;
import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.page.MonkeyAbstractElement;
import com.monkey.core.session.ExecutionManager;
import com.monkey.impl.drivers.DriverType;

public class MonkeyMobileDecorator {

    private static final List<Class<? extends MonkeyAbstractElement>> availableElementClasses = new ArrayList<Class<? extends MonkeyAbstractElement>>() {
        private static final long serialVersionUID = 1L;

        {
            this.add(MonkeyMobileElement.class);

        }
    };

    public Object decorate(final ClassLoader loader, final Field field) {
        if (!(MonkeyMobileDecorator.availableElementClasses
                .contains(field.getType())/* || isDecoratableList(field) */)) {
            return null;
        }

        if (MonkeyWebElement.class.isAssignableFrom(field.getType())) {
            return this.proxyForLocator(field);
        } else {
            return null;
        }
    }

    private Object proxyForLocator(final Field field) {
        final DriverType driverType = ExecutionManager.getConfiguration().getDriverType();
        final MonkeyMobileElement testElement = new MonkeyMobileElement();
        switch (driverType) {
            case iosDriver:
                final IosLocator iosLocator = field.getAnnotation(IosLocator.class);
                if (iosLocator != null) {
                    testElement.setInputValue(iosLocator.value());
                    testElement.setIdentifier(iosLocator.identifier());
                    testElement.setSelector(iosLocator.selector());
                }
                testElement.setVariableName(field.getName());
                return testElement;
            case androidDriver:
                final AndroidLocator androidLocator = field.getAnnotation(AndroidLocator.class);
                if (androidLocator != null) {
                    testElement.setInputValue(androidLocator.value());
                    testElement.setIdentifier(androidLocator.identifier());
                    testElement.setSelector(androidLocator.selector());
                }
                testElement.setVariableName(field.getName());
                return testElement;
            default:
                return null;
        }
    }

}
