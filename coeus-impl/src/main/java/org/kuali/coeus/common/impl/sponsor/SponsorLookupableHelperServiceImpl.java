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
package org.kuali.coeus.common.impl.sponsor;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.web.struts.form.MultipleValueLookupForm;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component("sponsorLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SponsorLookupableHelperServiceImpl  extends KcKualiLookupableHelperServiceImpl {
    private static final String HIERARCHY_NAME = "hierarchyName";
    private static final String SELECTED_HIERARCHY_NAME = "selectedHierarchyName";
    private static final String CONVERSION_FIELD_PARAM_NAME = "conversionFields";
    
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
    
    @Override
    public List<Row> getRows() {
    	List<Row> rows = super.getRows();
    	//if this is coming from a form of some sort, conversion fields are supplied.
    	//if this is the case, we don't want to allow the user to search for inactive sponsors.
    	if (getParameters().containsKey(CONVERSION_FIELD_PARAM_NAME) && !getParameters().get(CONVERSION_FIELD_PARAM_NAME)[0].isEmpty()) {
    		Iterator<Row> i = rows.iterator();
    		while (i.hasNext()) {
    			Row row = i.next();
    			boolean removeRow = false;
    			for (Field field : row.getFields()) {
        			if (StringUtils.equalsIgnoreCase("Active", field.getFieldLabel())) {
        				removeRow = true;
        				break;
        			}
        		}
    			if (removeRow) {
    				i.remove();
    			}
    		}
    	}
    	return rows;
    }
    
}
