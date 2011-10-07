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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.UnitAdministrator;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

/**
 * Lookupable helper service used for proposal log lookup
 */  
public class UnitAdministratorLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2733736916454475501L;
    
    @SuppressWarnings("unchecked")
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames){
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
        List<HtmlData> returnHtmlDataList = new ArrayList<HtmlData>();
        for (HtmlData htmlData : htmlDataList) {
            if(!(htmlData.getDisplayText().equals("copy") ||
                    htmlData.getDisplayText().equals("edit"))) {
                returnHtmlDataList.add(htmlData);
            }
        }
        return returnHtmlDataList;
    }

    /**
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals("person.userName")) {
                    field.setFieldConversions("principalName:person.userName,principalId:personId");
                }
            }
        }
        return rows;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        String userName = (String) lookupForm.getFieldsForLookup().get("person.userName");
            if (StringUtils.isNotEmpty(userName)) {
                KcPerson person = getKcPersonService().getKcPersonByUserName(userName);
            if (person != null) {
                lookupForm.getFieldsForLookup().put("personId", person.getPersonId());
            }
        }
        
        return super.performLookup(lookupForm, resultTable, bounded);
    }
    
    public KcPersonService getKcPersonService() {
        return (KcPersonService) KraServiceLocator.getService(KcPersonService.class);
    }

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<UnitAdministrator> searchResults = (List<UnitAdministrator>)super.getSearchResults(fieldValues);
        if (!searchResults.isEmpty()) {
            if (StringUtils.isNotBlank(fieldValues.get("person.userName"))) {
                return filterSearchResults(searchResults, fieldValues.get("person.userName"));
            }
        }
        return searchResults;
    }

    /*
     * This method is primarily to match person username.
     * kcperson is not in unitadministrator table, so generic getsearchresults is not working properly.
     */
    protected List<UnitAdministrator> filterSearchResults(List<UnitAdministrator> searchResults, String userName) {
        List<UnitAdministrator> filteredList = new ArrayList<UnitAdministrator>();
        
        String regexp = StringUtils.replace(userName, "*", ".*").toUpperCase() + "$";
        for (UnitAdministrator unitAdministrator : searchResults) {
            if (unitAdministrator.getPerson().getUserName().toUpperCase().matches(regexp)) {
                filteredList.add(unitAdministrator);
            }
        }
        return filteredList;
    }
    
}
