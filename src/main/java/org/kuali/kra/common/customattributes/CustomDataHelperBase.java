/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.common.customattributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The CustomDataHelperBase is the base class for all Custom Data Helper classes.
 */
public abstract class CustomDataHelperBase {
    
    private SortedMap<String, List> customAttributeGroups = new TreeMap<String, List>();
    private Map<String, String[]> customAttributeValues = new HashMap<String, String[]>();
   
    /*
     * Is the end-user allowed to modify the custom data values?
     */
    private boolean modifyCustomData = false;
    
    /**
     * Prepare the tab for viewing.
     */
    public void prepareView() {
       initializePermissions();
    }
    
    /**
     * Initialize the permissions for viewing/editing the Custom Data web page.
     */
    private void initializePermissions() {
        modifyCustomData = canModifyCustomData();
    }
    
    /**
     * Can the current user modify the custom data values?
     * @return true if can modify the custom data; otherwise false
     */
    public abstract boolean canModifyCustomData();
    
    /**
     * Get the ModifyCustomData value.
     * @return the ModifyCustomData value
     */
    public boolean getModifyCustomData() {
        return modifyCustomData;
    }
    
    /**
     * Sets the customAttributeGroups attribute value.
     * @param customAttributeGroups The customAttributeGroups to set.
     */
    public void setCustomAttributeGroups(SortedMap<String, List> customAttributeGroups) {
        this.customAttributeGroups = customAttributeGroups;
    }

    /**
     * Gets the customAttributeGroups attribute.
     * @return Returns the customAttributeGroups.
     */
    public Map<String, List> getCustomAttributeGroups() {
        return customAttributeGroups;
    }


    /**
     * Sets the customAttributeValues attribute value.
     * @param customAttributeValues The customAttributeValues to set.
     */
    public void setCustomAttributeValues(Map<String, String[]> customAttributeValues) {
        this.customAttributeValues = customAttributeValues;
    }

    /**
     * Gets the customAttributeValues attribute.
     * @return Returns the customAttributeValues.
     */
    public Map<String, String[]> getCustomAttributeValues() {
        return customAttributeValues;
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    protected String getUserName() {
         UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
         return user.getPersonUserIdentifier();
    }
}
