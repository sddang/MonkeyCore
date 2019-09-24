
/**
 * Package to manage the Tasks
 */
package com.monkey.core.task;

import com.monkey.api.page.MonkeyWebElement;
import com.monkey.api.web.browser.Window;
import com.monkey.core.exception.MonkeyException;
import com.monkey.core.session.ExecutionManager;
import com.monkey.services.HilightElementEvent;
import com.monkey.services.ScreenShotTaker;
import com.monkey.services.documentation.DocumentationEvent;
import com.monkey.services.documentation.DocumentationType;
import com.monkey.services.log.LogTrackerEvent;
import com.monkey.services.report.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;


public abstract class AbstractTask {

	private TaskStatus status;
	private MonkeyWebElement element;
	protected boolean initElement = true;

	/**
	 * Run the task
	 */
	public void runTask() {
        this.beforeRunningTask();
        this.run();
        this.afterRunningTask();
	}

	/**
	 * before firing the run of the task do some treatments
	 */
	private void beforeRunningTask() {
		if (this.initElement) {
			if (this.element != null) {
				final boolean allowEnabledElement = this.allowEnabledElement();
                this.element.initializeWebElement(allowEnabledElement);
			}
			HilightElementEvent.hilightElement(this.element);
		}
	}

	/**
	 * This methods is added to allow the click action on disabled element
	 * 
	 * @return
	 */
	protected boolean allowEnabledElement() {
		return false;
	}

	/**
	 * Is the body of the execution that will be specific of each task
	 */
	public abstract void execute();

	/**
	 * run the task and return it's status
	 * 
	 * @return
	 */
	private void run() {
		try {
            this.execute();
            this.status = TaskStatus.SUCCESSED;
		} catch (final MonkeyException e) {
            this.status = TaskStatus.FAILED;
		}
	}

	/**
	 * Do treatments after the execution of the task
	 */
	private void afterRunningTask() {
		final String className = getClass().getName();
		final String description = this.getDescription();
		LogTrackerEvent.traceTask(className, description);
		if (!ExecutionManager.getConfiguration().isVerbose() && this.isVerbose())
			return;
		if (ExecutionManager.getConfiguration().isGenerateTechnicalDoc() && !this.getName().equals(Window.CLOSE)) {
			final String screenshotFilePath = ScreenShotTaker.takeScreenshots();
			ExtentReport.logWithScreenshot(LogStatus.UNKNOWN, description, screenshotFilePath);
			DocumentationEvent.addEvent(className, description, screenshotFilePath, this.getStatus(),
					DocumentationType.TECHNICAL);
		} else {
			ExtentReport.log(LogStatus.UNKNOWN, description);
		}
	}

	/**
	 * Return the description of each action. This information will be used in
	 * the documentation
	 * 
	 * @return
	 */
	public abstract String getDescription();
	public abstract String getName();

	/**
	 * Return the current element that fire the action
	 * 
	 * @return
	 */
	public MonkeyWebElement getElement() {
		return this.element;
	}

	/**
	 * Set the element that will do the task
	 * 
	 * @param element
	 * @return
	 */
	public AbstractTask setElement(final MonkeyWebElement element) {
		this.element = element;
		return this;
	}

	/**
	 * Return the status
	 * 
	 * @return Status
	 */
	private TaskStatus getStatus() {
		return this.status;
	}

	protected boolean isVerbose() {
		return false;
	}
	

	public void setInitElement(final boolean initElement) {
		this.initElement = initElement;
	}

}
