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
package org.kuali.kra.lookup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.core.bo.BusinessObject;
import org.kuali.core.lookup.CollectionIncomplete;
import org.kuali.core.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.core.lookup.LookupUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.core.web.struts.form.MultipleValueLookupForm;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.SponsorService;

public class SponsorLookupableHelperServiceImpl  extends KualiLookupableHelperServiceImpl {
    private static final String HIERARCHY_NAME = "hierarchyName";
    private static final String SELECTED_HIERARCHY_NAME = "selectedHierarchyName";

    /**
     * 
     * @see org.kuali.core.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     * This is primarily for multiple value lookup.  also need to take care of single value lookup
     */
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List searchResults;
        List searchResultsReturn = new ArrayList();
        //searchResults = super.getSearchResults(fieldValues);
        KualiForm kualiForm = GlobalVariables.getKualiForm();
        if (kualiForm == null || !(kualiForm instanceof MultipleValueLookupForm)) {
            // not multiple value lookup
            return super.getSearchResults(fieldValues);
        } else {
            searchResults = super.getSearchResultsHelper(fieldValues, true);
            
        }
        //searchResults = (List)KraServiceLocator.getService(BusinessObjectService.class).findAll(Sponsor.class);
        Object hierarchyName = GlobalVariables.getUserSession().retrieveObject(HIERARCHY_NAME);
        Object selectedHierarchyName = GlobalVariables.getUserSession().retrieveObject(SELECTED_HIERARCHY_NAME);
        String sponsors= null;
        boolean isNewHierarchy = false;
        String existSponsors = (String)GlobalVariables.getUserSession().retrieveObject("sponsorCodes");
        String[] existSponsorArray ;
        List existSponsorList;
        if (existSponsors != null) {
            existSponsorArray = existSponsors.split(";");
            existSponsorList = Arrays.asList(existSponsorArray);            
        } else {
            existSponsorList = new ArrayList();
        }
        if (selectedHierarchyName != null) {
            sponsors = KraServiceLocator.getService(SponsorService.class).loadToSponsorHierachyMt(selectedHierarchyName.toString());
            isNewHierarchy = true;
        } else {
            if (existSponsors == null) {
                sponsors = KraServiceLocator.getService(SponsorService.class).loadToSponsorHierachyMt(hierarchyName.toString());
            } else {
                sponsors = existSponsors;
            }
        }
        String[] sponsorArray = sponsors.split(";");
        List sponsorList = Arrays.asList(sponsorArray);
        int i = 0;
        Integer searchResultsLimit = LookupUtils.getSearchResultsLimit(Sponsor.class);
        for (Iterator iterator = searchResults.iterator(); iterator.hasNext();) {
            Sponsor sponsor = (Sponsor)iterator.next();
            if (isNewHierarchy) {
                if (sponsorList.contains(sponsor.getSponsorCode()) && !existSponsorList.contains(sponsor.getSponsorCode())) {
                    i++;
                    searchResultsReturn.add(sponsor);
                }
            } else {
                if (!sponsorList.contains(sponsor.getSponsorCode())) {
                    i++;
                    searchResultsReturn.add(sponsor);
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

