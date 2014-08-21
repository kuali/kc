package org.kuali.coeus.org.kuali.rice.krad.uif.container;

import java.util.Map;

import org.kuali.rice.krad.uif.container.Group;

/**
 * Dynamic Tab Group container
 *
 */
public interface DynamicTabGroup extends Group {

	/**
	 * Property name of a collection on that model that will be iterated over to create each
	 * individual tab in the tab group.
	 * @param tabCollectionPropertyName
	 */
	void setTabCollectionPropertyName(String tabCollectionPropertyName);
	
	/**
	 * A group prototype that will be copied for each item found in the <code>tabCollectionPropertyName</code>.
	 * @param groupPrototype
	 */
	void setGroupPrototype(Group groupPrototype);
	
	/**
	 * Map of SpEL context variable names and associated property names found on items within <code>tabCollectionPropertyName</code>
	 * that will be pushed into each group's SpEL context.
	 * @param expressionProperties
	 */
	void setExpressionProperties(Map<String, String> expressionProperties);
	
	/**
	 * Property name on items found within <code>tabCollectionPropertyName</code> whose value will be used to suffix the id's
	 * in each copy of the <code>groupPrototype</code>
	 * @param idSuffixPropertyName
	 */
	void setIdSuffixPropertyName(String idSuffixPropertyName);
}
