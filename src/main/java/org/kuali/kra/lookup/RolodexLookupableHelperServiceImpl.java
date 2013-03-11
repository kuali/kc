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
package org.kuali.kra.lookup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.Rolodex;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;

public class RolodexLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl{
    private static final String ORGANIZATION_NAME = "organizationName.sponsorName";
    private static final String NAME_FOR_ORGANIZATION = "orgName.organizationName";
    private static final String SPONSOR_NAME = "sponsor.sponsorName";
    private static final String ORGANIZATION_ID = "organization";

    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {

        setBackLocation(fieldValues.get(KRADConstants.BACK_LOCATION));
        setDocFormKey(fieldValues.get(KRADConstants.DOC_FORM_KEY));
        setReferencesToRefresh(fieldValues.get(KRADConstants.REFERENCES_TO_REFRESH));
        String organizationName = fieldValues.get(ORGANIZATION_NAME);
        String orgName = fieldValues.get(NAME_FOR_ORGANIZATION);
        fieldValues.remove(ORGANIZATION_NAME);
        fieldValues.remove(SPONSOR_NAME);
        fieldValues.remove(ORGANIZATION_ID);
        fieldValues.remove(NAME_FOR_ORGANIZATION);
        List<Rolodex> unboundedResults =
            (List<Rolodex>) super.getSearchResultsUnbounded(fieldValues);
        List<Rolodex> returnResults = new ArrayList<Rolodex>();
        try {
            returnResults = filterForRolodex(unboundedResults, organizationName, orgName);
        } catch (WorkflowException e) {
            e.printStackTrace();
        }
        return returnResults;
    }

    protected List<Rolodex> filterForRolodex(List<Rolodex> collectionByQuery, String organizationName, String orgName) throws WorkflowException{
        List<Rolodex> filterRolodexList = new ArrayList<Rolodex>();

        if (organizationName != null && !organizationName.isEmpty()) {
        for (Rolodex rolodex : collectionByQuery) {           
                if (rolodex.getOrganization().equalsIgnoreCase(organizationName)) {
                    filterRolodexList.add(rolodex);
                } else {
                    String subRecName = organizationName.replace("*", ".*").replace("?", ".");
                    subRecName = subRecName.toLowerCase();
                    if (rolodex.getOrganization().toLowerCase().matches(subRecName)) {
                        filterRolodexList.add(rolodex);
                    }

                }
            }
        } else {
            for (Rolodex rolodex : collectionByQuery) {
                filterRolodexList.add(rolodex);
            }
        }
        List<Rolodex> filteredRolodexList = new ArrayList<Rolodex>(); 
        if (orgName != null && !orgName.isEmpty()) {
            for (Rolodex rolodex : filterRolodexList) {
                if (rolodex.getOrganization().equalsIgnoreCase(orgName)) {
                    filteredRolodexList.add(rolodex);
                } else {
                    String subOrgName = organizationName.replace("*", ".*").replace("?", ".");
                    subOrgName = subOrgName.toLowerCase();
                    if(rolodex.getOrganization().toLowerCase().matches(subOrgName)) {
                        filteredRolodexList.add(rolodex);
                    }
                }
            }
        }
        else {
            for (Rolodex rolodex : filterRolodexList) {
                filteredRolodexList.add(rolodex);
            }
        }
       
        return filteredRolodexList;
    }

}

