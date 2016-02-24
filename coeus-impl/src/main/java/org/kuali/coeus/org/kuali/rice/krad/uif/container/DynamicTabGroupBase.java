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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.rice.krad.datadictionary.parse.BeanTag;
import org.kuali.rice.krad.datadictionary.parse.BeanTags;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.container.CollectionGroup;
import org.kuali.rice.krad.uif.container.Group;
import org.kuali.rice.krad.uif.container.TabGroup;
import org.kuali.rice.krad.uif.util.ComponentUtils;
import org.kuali.rice.krad.uif.util.ContextUtils;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;

/**
 * A dynamic tab group is an extension of the standard tab group where the tabs
 * are dynamically data driven. Instead of specifying the tabs as items in the group
 * collection, items for this group are derived based on a collection specified by <code>tabCollectionPropertyName</code>.
 *
 * Each tab will then be created using the <code>groupPrototype</code> specified.
 *
 * Additionally the bean id suffix can be extended from the group prototype by specifying <code>idSuffixPropertyname</code>
 * The SpEL context for each group is also extended, first by adding <code>#tabContext</code> as the item from the collection
 * but also by specifying <code>expressionProperties</code> which is a map containing SpEL variable names and the related property
 * names from the object in the collection whose values will be used.
 *
 */
@BeanTags({@BeanTag(name = "dynamicTabGroup", parent = "Kc-DynamicTabGroup"),
	@BeanTag(name="dynamicTagSection", parent="Kc-DynamicTabSection")
})
public class DynamicTabGroupBase extends TabGroup implements DynamicTabGroup {

	private String tabCollectionPropertyName;
	private Group groupPrototype;
	private Map<String, String> expressionProperties;
	private String idSuffixPropertyName;
	private Boolean setFieldBindingObjectPath = Boolean.FALSE;

    /**
     * {@inheritDoc}
     */
    @Override
    public void performInitialization(Object model) {
    	List<Component> items = new ArrayList<Component>();
        List<Object> modelCollection = ObjectPropertyUtils.getPropertyValue(model,
                tabCollectionPropertyName);
        int index = 0;
        for (Object tabObj : modelCollection) {
        	String idSuffix;
        	if (idSuffixPropertyName != null) {
        		idSuffix = ObjectPropertyUtils.getPropertyValueAsText(tabObj, idSuffixPropertyName);
        	} else {
        		idSuffix = Integer.toString(index);
        	}
        	Group newGroup = ComponentUtils.copy(groupPrototype, "_" + idSuffix);
        	if (expressionProperties != null && !expressionProperties.isEmpty()) {
        		Map<String, Object> tabContext = new HashMap<String, Object>();
	        	for (Map.Entry<String, String> entry : expressionProperties.entrySet()) {
	        		tabContext.put(entry.getKey(), ObjectPropertyUtils.getPropertyValueAsText(tabObj, entry.getValue()));
	        	}
	        	ContextUtils.pushAllToContextDeep(newGroup, tabContext);
        	}
        	if (setFieldBindingObjectPath && newGroup instanceof CollectionGroup) {
        		((CollectionGroup)newGroup).getBindingInfo().setBindingObjectPath(tabCollectionPropertyName + "[" + index + "]");
        	} else if (setFieldBindingObjectPath) {
        		 newGroup.setFieldBindingObjectPath(tabCollectionPropertyName + "[" + index + "]");
        	}
			items.add(newGroup);
            index ++;
        }
        setItems(items);
        super.performInitialization(model);
    }

	public String getTabCollectionPropertyName() {
		return tabCollectionPropertyName;
	}

    /**
     * {@inheritDoc}
     */
	public void setTabCollectionPropertyName(String tabCollectionPropertyName) {
		this.tabCollectionPropertyName = tabCollectionPropertyName;
	}

	public Group getGroupPrototype() {
		return groupPrototype;
	}

    /**
     * {@inheritDoc}
     */
	public void setGroupPrototype(Group groupPrototype) {
		this.groupPrototype = groupPrototype;
	}

	public Map<String, String> getExpressionProperties() {
		return expressionProperties;
	}

    /**
     * {@inheritDoc}
     */
	public void setExpressionProperties(Map<String, String> expressionProperties) {
		this.expressionProperties = expressionProperties;
	}

	public String getIdSuffixPropertyName() {
		return idSuffixPropertyName;
	}

    /**
     * {@inheritDoc}
     */
	public void setIdSuffixPropertyName(String idSuffixPropertyName) {
		this.idSuffixPropertyName = idSuffixPropertyName;
	}

	public Boolean getSetFieldBindingObjectPath() {
		return setFieldBindingObjectPath;
	}

	public void setSetFieldBindingObjectPath(Boolean setFieldBindingObjectPath) {
		this.setFieldBindingObjectPath = setFieldBindingObjectPath;
	}
}
