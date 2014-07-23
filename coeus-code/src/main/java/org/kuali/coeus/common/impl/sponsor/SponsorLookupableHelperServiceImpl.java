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
package org.kuali.coeus.common.impl.sponsor;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.web.struts.form.MultipleValueLookupForm;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component("sponsorLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SponsorLookupableHelperServiceImpl  extends KcKualiLookupableHelperServiceImpl {
    private static final String HIERARCHY_NAME = "hierarchyName";
    private static final String SELECTED_HIERARCHY_NAME = "selectedHierarchyName";
    
    protected static final String ACTIVE_FIELD_NAME = "active";
    protected static final String ACTIVE_FIELD_DEFAULT_VALUE_YES = "Y";
    protected static final String ACTIVE_FIELD_DEFAULT_VALUE_NO = "N";

    @Autowired
    @Qualifier("sponsorHierarchyMaintenanceService")
    private SponsorHierarchyMaintenanceService sponsorHierarchyMaintenanceService;


    /**
     * 
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
            sponsorsCodes = sponsorHierarchyMaintenanceService.loadToSponsorHierachyMt(selectedHierarchyName.toString());
            isNewHierarchy = true;
        }
        else {
            if (existSponsors == null) {
                String hierarchyNameString = hierarchyName != null ? hierarchyName.toString() : "";
                sponsorsCodes = sponsorHierarchyMaintenanceService.loadToSponsorHierachyMt(hierarchyNameString);
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
    }

    public SponsorHierarchyMaintenanceService getSponsorHierarchyMaintenanceService() {
        return sponsorHierarchyMaintenanceService;
    }

    public void setSponsorHierarchyMaintenanceService(SponsorHierarchyMaintenanceService sponsorHierarchyMaintenanceService) {
        this.sponsorHierarchyMaintenanceService = sponsorHierarchyMaintenanceService;
    }
}

