package com.monkey.api;

import org.openqa.selenium.By;

import java.io.Serializable;

public abstract class MonkeyBy {

    public static MonkeyBy id(String id) {
        return new monkeyById(id);
    }

    public static MonkeyBy linkText(String linkText) {
        return new monkeyByLinkText(linkText);
    }

    public static MonkeyBy partialLinkText(String linkText) {
        return new monkeyByPartialLinkText(linkText);
    }

    /* deprecated */
    // public static monkeyBy name(final String name) {
    // return new monkeyByName(name);
    // }

    public static MonkeyBy tagName(String name) {
        return new monkeyByTagName(name);
    }

    public static MonkeyBy xpath(String xpathExpression) {
        return new monkeyByXPath(xpathExpression);
    }

    public static MonkeyBy className(String className) {
        return new monkeyByClassName(className);
    }

    public static MonkeyBy cssSelector(String selector) {
        return new monkeyByCssSelector(selector);

    }

    public abstract By ToSeleniumBy();

    public static class monkeyById extends MonkeyBy implements Serializable {

        private static final long serialVersionUID = 7941019248720317292L;

        private final String id;

        public monkeyById(final String id) {
            this.id = id;
        }

        @Override
        public By ToSeleniumBy() {
            return By.id(this.id);
        }
    }

    public static class monkeyByLinkText extends MonkeyBy implements Serializable {

        private static final long serialVersionUID = 1079745611982829527L;

        private final String linkText;

        public monkeyByLinkText(final String linkText) {
            this.linkText = linkText;
        }

        @Override
        public By ToSeleniumBy() {
            return By.linkText(this.linkText);
        }

    }

    public static class monkeyByPartialLinkText extends MonkeyBy implements Serializable {

        private static final long serialVersionUID = 4364707863441913159L;

        private final String linkText;

        public monkeyByPartialLinkText(final String linkText) {
            this.linkText = linkText;
        }

        @Override
        public By ToSeleniumBy() {
            return By.partialLinkText(this.linkText);
        }
    }

    // DEPRECATED
    // public static class monkeyByName extends monkeyBy implements Serializable
    // {
    //
    // private static final long serialVersionUID = 3953453762064828447L;
    //
    // private final String name;
    //
    // public monkeyByName(String name) {
    // this.name = name;
    // }
    //
    // @Override
    // public By ToSeleniumBy() {
    // return By.name(name);
    // }
    // }

    public static class monkeyByTagName extends MonkeyBy implements Serializable {

        private static final long serialVersionUID = -448060482852711495L;

        private final String name;

        public monkeyByTagName(final String name) {
            this.name = name;
        }

        @Override
        public By ToSeleniumBy() {
            return By.tagName(this.name);
        }
    }

    public static class monkeyByXPath extends MonkeyBy implements Serializable {

        private static final long serialVersionUID = 1252090267582477436L;

        private final String xpathExpression;

        public monkeyByXPath(final String xpathExpression) {
            this.xpathExpression = xpathExpression;
        }

        @Override
        public By ToSeleniumBy() {
            return By.xpath(this.xpathExpression);
        }
    }

    public static class monkeyByClassName extends MonkeyBy implements Serializable {

        private static final long serialVersionUID = -4115863352242582790L;

        private final String className;

        public monkeyByClassName(final String className) {
            this.className = className;
        }

        @Override
        public By ToSeleniumBy() {
            return By.className(this.className);
        }
    }

    public static class monkeyByCssSelector extends MonkeyBy implements Serializable {

        private static final long serialVersionUID = -3132453906569199490L;

        private final String selector;

        public monkeyByCssSelector(final String selector) {
            this.selector = selector;
        }

        @Override
        public By ToSeleniumBy() {
            return By.cssSelector(this.selector);
        }
    }
}
