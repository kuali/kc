/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PropertyConstants;
import org.kuali.rice.krad.service.BusinessObjectService;

public class CustomDataHelper implements Serializable {

    private static final long serialVersionUID = -6829522940099878931L;

    private SortedMap<String, List<CustomAttributeDocument>> customAttributeGroups;
    
    private transient BusinessObjectService businessObjectService;
    
    public Map<String, List<CustomAttributeDocument>> getCustomAttributeGroups() {
        return customAttributeGroups;
    }

    public void setCustomAttributeGroups(SortedMap<String, List<CustomAttributeDocument>> customAttributeGroups) {
        this.customAttributeGroups = customAttributeGroups;
    }

    public boolean canModifyCustomData() {
        return true;
    }
    
    public void initializeCustomAttributeGroups(KcPersonExtendedAttributes kcPersonExtendedAttributes) {
        Map<String, CustomAttributeDocument> customAttributeDocuments = getCustomAttributeDocuments();
        
        customAttributeGroups = new TreeMap<String, List<CustomAttributeDocument>>();
        for (CustomAttributeDocument customAttributeDocument : customAttributeDocuments.values()) {
            String groupName = StringUtils.defaultIfBlank(customAttributeDocument.getCustomAttribute().getGroupName(), "No Group");
            List<CustomAttributeDocument> groupCustomAttributeDocuments = customAttributeGroups.get(groupName);
            if (groupCustomAttributeDocuments == null) {
                groupCustomAttributeDocuments = new ArrayList<CustomAttributeDocument>();
                customAttributeGroups.put(groupName, groupCustomAttributeDocuments);
            }
            groupCustomAttributeDocuments.add(customAttributeDocuments.get(String.valueOf(customAttributeDocument.getCustomAttributeId())));
            Collections.sort(groupCustomAttributeDocuments, new LabelComparator());
        }
        
        initializeCustomData(customAttributeDocuments.values(), kcPersonExtendedAttributes);
    }
    
    public void populateCustomAttributeGroups(KcPersonExtendedAttributes kcPersonExtendedAttributes) {
        Map<String, CustomAttributeDocument> customAttributeDocuments = getCustomAttributeDocuments();
        
        customAttributeGroups = new TreeMap<String, List<CustomAttributeDocument>>();
        for (PersonCustomData personCustomData : kcPersonExtendedAttributes.getPersonCustomDataList()) {
            CustomAttributeDocument customAttributeDocument = customAttributeDocuments.get(String.valueOf(personCustomData.getCustomAttributeId()));
            String groupName = customAttributeDocument.getCustomAttribute().getGroupName();
            List<CustomAttributeDocument> groupCustomAttributeDocuments = customAttributeGroups.get(groupName);   
            if (groupCustomAttributeDocuments == null) {
                groupCustomAttributeDocuments = new ArrayList<CustomAttributeDocument>();
                customAttributeGroups.put(groupName, groupCustomAttributeDocuments);
            }
            groupCustomAttributeDocuments.add(customAttributeDocument);
            Collections.sort(groupCustomAttributeDocuments, new LabelComparator());
        }
    }
    
    private Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(PropertyConstants.DOCUMENT.TYPE_NAME.toString(), "PERS");
        Collection<CustomAttributeDocument> customAttributeDocumentList = getBusinessObjectService().findMatching(CustomAttributeDocument.class, fieldValues);
        for (CustomAttributeDocument customAttributeDocument : customAttributeDocumentList) {
            if (customAttributeDocument.isActive()) {
                customAttributeDocuments.put(customAttributeDocument.getCustomAttributeId().toString(), customAttributeDocument);
            }
        }
        
        return customAttributeDocuments;
    }
    
    private void initializeCustomData(Collection<CustomAttributeDocument> customAttributeDocuments, KcPersonExtendedAttributes kcPersonExtendedAttributes) {
        for (CustomAttributeDocument customAttributeDocument : customAttributeDocuments) {
            int customAttributeId = customAttributeDocument.getCustomAttributeId(); 
            String customAttributeDefaultValue = customAttributeDocument.getCustomAttribute().getDefaultValue();
            String customAttributeValue = customAttributeDocument.getCustomAttribute().getValue();
            
            PersonCustomData personCustomData = new PersonCustomData();
            personCustomData.setCustomAttributeId((long) customAttributeId);
            personCustomData.setCustomAttribute(customAttributeDocument.getCustomAttribute());
            personCustomData.setPersonId(kcPersonExtendedAttributes.getPersonId());
            personCustomData.setValue(StringUtils.defaultString(StringUtils.defaultString(customAttributeValue, customAttributeDefaultValue)));

            kcPersonExtendedAttributes.getPersonCustomDataList().add(personCustomData);
        }
    }
    
    /**
     * Sorts custom data attributes by label for alphabetical order on custom data panels.
     */
    protected class LabelComparator implements Comparator<CustomAttributeDocument> {
        
        @Override
        public int compare(CustomAttributeDocument o1, CustomAttributeDocument o2) {    
            String label1 = StringUtils.defaultString(o1.getCustomAttribute().getLabel());
            String label2 = StringUtils.defaultString(o2.getCustomAttribute().getLabel());
            return label1.compareTo(label2);
        }
        
    }
    
    public BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}