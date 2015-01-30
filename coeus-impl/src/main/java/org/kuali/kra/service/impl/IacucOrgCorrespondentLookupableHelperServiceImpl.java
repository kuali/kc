/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.org.crrspndnt.OrganizationCorrespondent;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Lookupable helper service used for person id lookup
 */  
public class IacucOrgCorrespondentLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {


    private static final long serialVersionUID = 749587517623905557L;

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
        return (KcPersonService) KcServiceLocator.getService(KcPersonService.class);
    }

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<OrganizationCorrespondent> searchResults = (List<OrganizationCorrespondent>)super.getSearchResults(fieldValues);
        if (!searchResults.isEmpty()) {
            if (StringUtils.isNotBlank(fieldValues.get("person.userName"))) {
                return filterSearchResults(searchResults, fieldValues.get("person.userName"));
            }
        }
        return searchResults;
    }

    /*
     * This method is primarily to match person username.
     * kcperson is not in organizationCorrespondent table, so generic getsearchresults is not working properly.
     */
    protected List<OrganizationCorrespondent> filterSearchResults(List<OrganizationCorrespondent> searchResults, String userName) {
        List<OrganizationCorrespondent> filteredList = new ArrayList<OrganizationCorrespondent>();
        
        String regexp = StringUtils.replace(userName, "*", ".*").toUpperCase() + "$";
        for (OrganizationCorrespondent organizationCorrespondent : searchResults) {
            if (organizationCorrespondent.getPerson().getUserName().toUpperCase().matches(regexp)) {
                filteredList.add(organizationCorrespondent);
            }
        }
        return filteredList;
    }
    
}
