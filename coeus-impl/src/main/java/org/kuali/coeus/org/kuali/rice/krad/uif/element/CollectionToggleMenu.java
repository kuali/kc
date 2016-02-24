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
package org.kuali.coeus.org.kuali.rice.krad.uif.element;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.rice.krad.uif.component.BindingInfo;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.ToggleMenu;
import org.kuali.rice.krad.uif.util.ComponentUtils;
import org.kuali.rice.krad.uif.util.LifecycleElement;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CollectionToggleMenu extends ToggleMenu {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CollectionToggleMenu.class);

    private Class<?> collectionObjectClass;
    private BindingInfo bindingInfo;

    private Action navigationActionPrototype;
    private String actionLabelPropertyName;

    @Override
    public void performInitialization(Object model) {
        super.performInitialization(model);
        this.setMenuItems(new ArrayList<Component>());
    }

    @Override
    public void performApplyModel(Object model, LifecycleElement parent) {
        List<Component> menuItemList = new ArrayList<Component>();
        List<String> actionLabelNames = new ArrayList<String>();
        List<Object> modelCollection = ObjectPropertyUtils.getPropertyValue(model,
                getBindingInfo().getBindingPath());
        int index = 0;
        for (Object object : modelCollection) {
            String actionLabel = getActionLabel(object);
            if (!actionLabelNames.contains(actionLabel)) {
                Action menuItem =  (Action) ComponentUtils.copy(navigationActionPrototype);
                menuItem.getActionParameters().put("actionLabel",actionLabel);
                menuItem.setActionLabel(actionLabel);
                menuItem.setSuccessCallback("Kc.PropDev.markActiveMenuLink(" + index+ ");");
                menuItem.addDataAttribute("menuname",this.getNavigationActionPrototype().getNavigateToPageId() + index);
                menuItemList.add(menuItem);
                actionLabelNames.add(actionLabel);
                index++;
            }
        }

        Collections.sort(menuItemList, new Comparator<Component>(){
            public int compare(Component c1, Component c2) {
                if (c1 instanceof Action && c2 instanceof Action) {
                    return ((Action)c1).getActionLabel().compareTo(((Action)c2).getActionLabel());
                } else {
                    return c1.getTitle().compareTo(c2.getTitle());
                }
            }
        });

        setMenuItems(menuItemList);
        getMenuGroup().setItems(menuItemList);
        super.performApplyModel(model, parent);
    }

    protected String getActionLabel(Object obj) {
        try {
            return PropertyUtils.getNestedProperty(obj,this.getActionLabelPropertyName()).toString();
        } catch(Exception e) {
            LOG.error("Problem creating actionLabel from actionLabelPropertyName",e);
        }
        return null;
    }



    public Class<?> getCollectionObjectClass() {
        return collectionObjectClass;
    }

    public void setCollectionObjectClass(Class<?> collectionObjectClass) {
        this.collectionObjectClass = collectionObjectClass;
    }

    public BindingInfo getBindingInfo() {
        return bindingInfo;
    }

    public void setBindingInfo(BindingInfo bindingInfo) {
        this.bindingInfo = bindingInfo;
    }

    public Action getNavigationActionPrototype() {
        return navigationActionPrototype;
    }

    public void setNavigationActionPrototype(Action navigationActionPrototype) {
        this.navigationActionPrototype = navigationActionPrototype;
    }

    public String getActionLabelPropertyName() {
        return actionLabelPropertyName;
    }

    public void setActionLabelPropertyName(String actionLabelPropertyName) {
        this.actionLabelPropertyName = actionLabelPropertyName;
    }

}
