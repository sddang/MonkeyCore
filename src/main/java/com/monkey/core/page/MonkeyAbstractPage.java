
package com.monkey.core.page;

import java.util.Set;

import com.monkey.api.page.MonkeyWebComponent;
import com.monkey.api.page.MonkeyWebElement;
import com.monkey.api.page.MonkeyWebPage;


public abstract class MonkeyAbstractPage {

	private Set<MonkeyWebElement> pageElements;
	private Set<MonkeyWebComponent> pageComponents;
	private Set<MonkeyWebPage> embeddedPages;

	/**
	 * This method should be overrided in each class that inherit from the Page
	 * class It is not used in this version of the Framework 1.0.1 It will be
	 * used to validate the presence of the fields before launching tests. This
	 * action can gain time because we will find errors before launching tests
	 * 
	 * @return
	 */
	// public abstract String getUrl();

	/**
	 * Set the list of the elements that are described in the test pages
	 * 
	 * @param pageElements
	 */
	protected void setPageElements(final Set<MonkeyWebElement> pageElements) {
		this.pageElements = pageElements;
	}

	/**
	 * Set of the injected component
	 * 
	 * @param pageComponents
	 */
	protected void setPageComponents(final Set<MonkeyWebComponent> pageComponents) {
		this.pageComponents = pageComponents;
	}

	/**
	 * Set of the injected embedded pages
	 * 
	 * @param pageEmbeddedPages
	 */
	protected void setEmbeddedPages(final Set<MonkeyWebPage> embeddedPages) {
		this.embeddedPages = embeddedPages;
	}

	@Override
	public String toString() {

		final StringBuffer sb = new StringBuffer(1000);
		sb.append("monkeyPage [ name:  ").append(getClass().getName());
		sb.append(" , elements : \n { ");

		for (final MonkeyAbstractElement element : this.pageElements) {
			sb.append(element).append(", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" } \n ");

		sb.append(" , components : \n { ");
		for (final MonkeyAbstractComponent element : this.pageComponents) {
			sb.append(element).append(", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" } \n ");

		sb.append(" , embeddedPages : \n { ");
		for (final MonkeyAbstractPage element : this.embeddedPages) {
			sb.append(element).append(", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" } \n ");
		
		sb.append(" ]");

		return sb.toString();
	}

}
