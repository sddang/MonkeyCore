 

package com.monkey.core.page;

import java.util.Set;

import com.monkey.api.page.MonkeyWebComponent;
import com.monkey.api.page.MonkeyWebElement;


public abstract class MonkeyAbstractComponent {

	private Set<MonkeyWebElement> componentElements;
	private Set<MonkeyWebComponent> embeddedComponentElements;
	
	/**
	 * Set the list of the elements that are described in the test components
	 * @param pageElements
	 */
	protected void setComponentElements(final Set<MonkeyWebElement> componentElements) {
		this.componentElements = componentElements;
	}
	
	/**
	 * Set the list of the elements that are embedded in the test components
	 * @param pageElements
	 */
	protected void setEmbeddedComponentElements(final Set<MonkeyWebComponent> embeddedComponentElements) {
		this.embeddedComponentElements = embeddedComponentElements;
	}

	@Override
	public String toString() {

		final StringBuffer sb = new StringBuffer(1000);
		sb.append("monkeyComponent [ name:  ").append(getClass().getName())
				.append(" , elements : \n { ");
		for (final MonkeyAbstractElement element : this.componentElements) {
			sb.append(element).append(", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" }, embeddedElement : \n{ ");
		for (final MonkeyAbstractComponent component : this.embeddedComponentElements) {
			sb.append(component).append(", ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" }");
		sb.append("\n ]");

		return sb.toString();
	}

	
}
