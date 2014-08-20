package org.kuali.coeus.org.kuali.rice.krad.uif.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.container.Group;
import org.kuali.rice.krad.uif.container.TabGroup;
import org.kuali.rice.krad.uif.util.ComponentUtils;
import org.kuali.rice.krad.uif.util.ContextUtils;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.util.ObjectUtils;

public class DynamicTabGroup extends TabGroup {

	private String tabCollectionPropertyName;
	private Group groupPrototype;
	private Map<String, String> expressionProperties;
	private String idSuffixPropertyName;
	
    /**
     * {@inheritDoc}
     */
    @Override
    public void performInitialization(Object model) {
    	List<Component> items = new ArrayList<Component>();
        List<Object> modelCollection = ObjectPropertyUtils.getPropertyValue(model,
                tabCollectionPropertyName);
        for (Object tabObj : modelCollection) {
        	Group newGroup = ComponentUtils.copy(groupPrototype, "_" + ObjectPropertyUtils.getPropertyValueAsText(tabObj, idSuffixPropertyName));
        	Map<String, Object> tabContext = new HashMap<String, Object>();
        	tabContext.put("tabContext", tabObj);
        	for (Map.Entry<String, String> entry : expressionProperties.entrySet()) {
        		//ContextUtils.pushObjectToContextDeep(newGroup, entry.getKey(), ObjectPropertyUtils.getPropertyValueAsText(tabObj, entry.getValue()));
        		tabContext.put(entry.getKey(), ObjectPropertyUtils.getPropertyValueAsText(tabObj, entry.getValue()));
        	}
        	ContextUtils.pushAllToContextDeep(newGroup, tabContext);
        	items.add(newGroup);
        }
        setItems(items);
        super.performInitialization(model);
    }

	public String getTabCollectionPropertyName() {
		return tabCollectionPropertyName;
	}

	public void setTabCollectionPropertyName(String tabCollectionPropertyName) {
		this.tabCollectionPropertyName = tabCollectionPropertyName;
	}

	public Group getGroupPrototype() {
		return groupPrototype;
	}

	public void setGroupPrototype(Group groupPrototype) {
		this.groupPrototype = groupPrototype;
	}

	public Map<String, String> getExpressionProperties() {
		return expressionProperties;
	}

	public void setExpressionProperties(Map<String, String> expressionProperties) {
		this.expressionProperties = expressionProperties;
	}

	public String getIdSuffixPropertyName() {
		return idSuffixPropertyName;
	}

	public void setIdSuffixPropertyName(String idSuffixPropertyName) {
		this.idSuffixPropertyName = idSuffixPropertyName;
	}
}
