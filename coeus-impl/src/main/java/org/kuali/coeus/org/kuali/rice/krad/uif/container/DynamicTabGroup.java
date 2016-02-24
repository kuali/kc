/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
	
	/**
	 * When set to true will set the prototype group's fieldBindingObjectPath to the path into the tab groups collection.
	 * @param setFieldBindingObjectPath
	 */
	void setSetFieldBindingObjectPath(Boolean setFieldBindingObjectPath);
}
