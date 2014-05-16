package org.kuali.rice.contrib.uif.element;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.velocity.util.StringUtils;
import org.kuali.rice.krad.uif.component.BindingInfo;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.ToggleMenu;
import org.kuali.rice.krad.uif.util.ComponentFactory;
import org.kuali.rice.krad.uif.util.LifecycleElement;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CollectionToggleMenu extends ToggleMenu {
    private Class<?> collectionObjectClass;
    private BindingInfo bindingInfo;

    private String navigateToPageId;
    private String actionLabelPropertyName;
    private String methodToCall;

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
        for (Object object : modelCollection) {
            String actionLabel = getActionLabel(object);
            if (!actionLabelNames.contains(actionLabel)) {
                Action menuItem =  (Action) ComponentFactory.getNewComponentInstance("Uif-NavigationActionLink");
                menuItem.setNavigateToPageId(this.getNavigateToPageId());
                menuItem.setActionLabel(actionLabel);
                menuItem.getActionParameters().put("groupName", actionLabel);
                menuItem.setMethodToCall(getMethodToCall());
                menuItemList.add(menuItem);
                actionLabelNames.add(actionLabel);
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
        String labelPathArray[] = StringUtils.split(this.getActionLabelPropertyName(),".");
        try {
            Object nextObj = PropertyUtils.getProperty(obj,labelPathArray[0]);
            for(int i=1;i < labelPathArray.length;i++) {
                Object tempObj = PropertyUtils.getProperty(nextObj,labelPathArray[i]);
                nextObj = tempObj;
            }
            return nextObj.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public Class<?> getCollectionObjectClass() {
        return collectionObjectClass;
    }

    public void setCollectionObjectClass(Class<?> collectionObjectClass) {
        this.collectionObjectClass = collectionObjectClass;
    }

    public String getNavigateToPageId() {
        return navigateToPageId;
    }

    public void setNavigateToPageId(String navigateToPageId) {
        this.navigateToPageId = navigateToPageId;
    }

    public BindingInfo getBindingInfo() {
        return bindingInfo;
    }

    public void setBindingInfo(BindingInfo bindingInfo) {
        this.bindingInfo = bindingInfo;
    }

    public String getActionLabelPropertyName() {
        return actionLabelPropertyName;
    }

    public void setActionLabelPropertyName(String actionLabelPropertyName) {
        this.actionLabelPropertyName = actionLabelPropertyName;
    }

    public String getMethodToCall() {
        return methodToCall;
    }

    public void setMethodToCall(String methodToCall) {
        this.methodToCall = methodToCall;
    }
}
