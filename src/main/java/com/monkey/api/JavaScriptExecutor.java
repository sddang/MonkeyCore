

package com.monkey.api;

import com.monkey.api.page.MonkeyWebElement;
import com.monkey.core.task.common.JsExecutorTask;

public class JavaScriptExecutor {
	/**
	 * Execute the given script 
	 * @param script
	 */
	public static Object excuteJS(final String script) {
		final JsExecutorTask jsExecutorTask = new JsExecutorTask();
		jsExecutorTask.setScript(script);
		jsExecutorTask.execute();
		return jsExecutorTask.getResult();
	}
	/**
	 * execute the given script on the given element
	 * @param element
	 * @param script
	 */
	public static Object excuteJS(final MonkeyWebElement element, final String script) {
		final JsExecutorTask jsExecutorTask = new JsExecutorTask();
		jsExecutorTask.setScript(script);
		jsExecutorTask.setElement(element);
		jsExecutorTask.execute();
		return jsExecutorTask.getResult();
	}	
	/**
	 * Execute the script on the given element with some args
	 * @param script
	 * @param element
	 * @param arguments
	 */
	public static Object excuteJS(final String script, final MonkeyWebElement element, final String arguments) {
		final JsExecutorTask jsExecutorTask = new JsExecutorTask();
		jsExecutorTask.setScript(script);
		jsExecutorTask.setArguments(arguments);
		jsExecutorTask.setElement(element);
		jsExecutorTask.execute();
		return jsExecutorTask.getResult();
	}
	
	/**
	 * Execute the script on the given object
	 * @param script
	 * @param object
	 */	
	public static Object excuteJS(final Object obj, final String script) {
		final JsExecutorTask jsExecutorTask = new JsExecutorTask();
		jsExecutorTask.setScript(script);
		jsExecutorTask.setObject(obj);
		jsExecutorTask.execute();
		return jsExecutorTask.getResult();
	}
	
}
