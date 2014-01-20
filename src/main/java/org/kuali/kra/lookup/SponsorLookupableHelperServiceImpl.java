/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.SponsorService;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.web.struts.form.MultipleValueLookupForm;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SponsorLookupableHelperServiceImpl  extends KualiLookupableHelperServiceImpl {
    private static final String HIERARCHY_NAME = "hierarchyName";
    private static final String SELECTED_HIERARCHY_NAME = "selectedHierarchyName";
    
    protected static final String ACTIVE_FIELD_NAME = "active";
    protected static final String ACTIVE_FIELD_DEFAULT_VALUE_YES = "Y";
    protected static final String ACTIVE_FIELD_DEFAULT_VALUE_NO = "N";

    /**
     * 
     * @see org.kuali.core.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     * This is primarily for multiple value lookup.  also need to take care of single value lookup
     */
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
    
        if (!fieldValues.containsKey(ACTIVE_FIELD_NAME)) {
            fieldValues.put(ACTIVE_FIELD_NAME, ACTIVE_FIELD_DEFAULT_VALUE_YES);
        }
        boolean forceActiveFlagRestriction = false;
        boolean activeFlagValue = true;
        if (StringUtils.equalsIgnoreCase(fieldValues.get(ACTIVE_FIELD_NAME), ACTIVE_FIELD_DEFAULT_VALUE_YES)) {
            forceActiveFlagRestriction = true;
            activeFlagValue = true;
        } else if (StringUtils.equalsIgnoreCase(fieldValues.get(ACTIVE_FIELD_NAME), ACTIVE_FIELD_DEFAULT_VALUE_NO)) {
            forceActiveFlagRestriction = true;
            activeFlagValue = false;
        } else {
            forceActiveFlagRestriction = false;
            activeFlagValue = true;
        }
        
        List<Sponsor> searchResults;
        List<Sponsor> searchResultsReturn = new ArrayList<Sponsor>();
        //searchResults = super.getSearchResults(fieldValues);
        KualiForm kualiForm = KNSGlobalVariables.getKualiForm();
        if (kualiForm == null || !(kualiForm instanceof MultipleValueLookupForm)) {
            // not multiple value lookup
            searchResults = (List<Sponsor>) super.getSearchResults(fieldValues);
        } else {         
            searchResults = (List<Sponsor>)super.getSearchResultsHelper(fieldValues, true);
        }
        
        //searchResults = (List)KraServiceLocator.getService(BusinessObjectService.class).findAll(Sponsor.class);
        Object hierarchyName = GlobalVariables.getUserSession().retrieveObject(HIERARCHY_NAME);
        Object selectedHierarchyName = GlobalVariables.getUserSession().retrieveObject(SELECTED_HIERARCHY_NAME);
        String sponsorsCodes= "";
        boolean isNewHierarchy = false;
        String existSponsors = (String)GlobalVariables.getUserSession().retrieveObject("sponsorCodes");
        String[] existSponsorCodeArray ;
        List<String> existSponsorCodeList;
        
        if (existSponsors != null) {
            existSponsorCodeArray = existSponsors.split(";");
            existSponsorCodeList = Arrays.asList(existSponsorCodeArray);            
        } 
        else {
            existSponsorCodeList = new ArrayList<String>();
        }
        
        if (selectedHierarchyName != null) {
            sponsorsCodes = KraServiceLocator.getService(SponsorService.class).loadToSponsorHierachyMt(selectedHierarchyName.toString());
            isNewHierarchy = true;
        }
        else {
            if (existSponsors == null) {
                String hierarchyNameString = hierarchyName != null ? hierarchyName.toString() : "";
                sponsorsCodes = KraServiceLocator.getService(SponsorService.class).loadToSponsorHierachyMt(hierarchyNameString);
            } 
            else {
                sponsorsCodes = existSponsors;
            }
        }
        
        String[] sponsorArray = sponsorsCodes.split(";");
        List<String> sponsorList = Arrays.asList(sponsorArray);
        int i = 0;
        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(Sponsor.class);
        
        for (Sponsor sponsor : searchResults) {
            
            boolean allow = true;
            if (forceActiveFlagRestriction && activeFlagValue != sponsor.isActive()) {
                allow = false;
            }
            if (allow) {
                if (isNewHierarchy) {
                    if (!existSponsorCodeList.contains(sponsor.getSponsorCode())) {        
                        i++;
                        searchResultsReturn.add(sponsor);
                    }
                }        
                else {
                    if (!sponsorList.contains(sponsor.getSponsorCode())) {
                        i++;
                        searchResultsReturn.add(sponsor);
                    }
                }
            }
            if (i >= searchResultsLimit) {
                break;
            }
        }       
        return new CollectionIncomplete(searchResultsReturn, new Long(searchResults.size()));
        //return new CollectionIncomplete(searchResultsReturn, ((CollectionIncomplete)searchResults).getActualSizeIfTruncated());
    }
}

