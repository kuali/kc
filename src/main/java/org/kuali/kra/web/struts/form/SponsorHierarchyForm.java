/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.web.struts.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.springframework.util.AutoPopulatingList;

public class SponsorHierarchyForm extends KualiForm {

    private String topSponsorHierarchies;
    private String selectedSponsorHierarchy;
    private String hierarchyName;
    private String newHierarchyName;
    private String groupName;
    private String sponsorCode;
    private String sponsorCodeList;
    private Collection hierarchyNameList;
    private SponsorHierarchy newSponsorHierarchy;
    private List<SponsorHierarchy> sponsorHierarchyList;
    private List<List> newSponsors;
    private String lookupResultsBOClassName;
    private String lookedUpCollectionName;
    private String selectedSponsors;
    private String actionSelected;
    private String mapKey;
    private String message;
    private String sqlScripts;
    private String timestamp;
    private int numberPerGroup;
    private static final Log LOG = LogFactory.getLog(SponsorHierarchyForm.class);
    private transient ParameterService parameterService;

    /**
     * Used to indicate which result set we're using when refreshing/returning from a multi-value lookup
     */
    private String lookupResultsSequenceNumber;

    /**
     * Constructs a SponsorHierarchyForm.
     */
    public SponsorHierarchyForm() {
        super();
        hierarchyNameList=new ArrayList();
        newSponsors = new AutoPopulatingList<List>(ArrayList.class);
        //topSponsorHierarchies = KraServiceLocator.getService(SponsorService.class).getTopSponsorHierarchy();        

    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }

    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln.  it always get the methodtocall='refresh' after it started properly the first time.  
        // need to investigate this.
        this.setMethodToCall(Constants.EMPTY_STRING);
        this.setMessage(Constants.EMPTY_STRING);
    }

    public String getTopSponsorHierarchies() {
        return topSponsorHierarchies;
    }

    public void setTopSponsorHierarchies(String topSponsorHierarchies) {
        this.topSponsorHierarchies = topSponsorHierarchies;
    }

    public String getSelectedSponsorHierarchy() {
        return selectedSponsorHierarchy;
    }

    public void setSelectedSponsorHierarchy(String selectedSponsorHierarchy) {
        this.selectedSponsorHierarchy = selectedSponsorHierarchy;
    }

    public String getHierarchyName() {
        return hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNewHierarchyName() {
        return newHierarchyName;
    }

    public void setNewHierarchyName(String newHierarchyName) {
        this.newHierarchyName = newHierarchyName;
    }


    public String getSponsorCode() {
        return sponsorCode;
    }


    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }


    public Collection getHierarchyNameList() {
        return hierarchyNameList;
    }


    public void setHierarchyNameList(Collection hierarchyNameList) {
        this.hierarchyNameList = hierarchyNameList;
    }


    public SponsorHierarchy getNewSponsorHierarchy() {
        return newSponsorHierarchy;
    }


    public void setNewSponsorHierarchy(SponsorHierarchy newSponsorHierarchy) {
        this.newSponsorHierarchy = newSponsorHierarchy;
    }


    public List<SponsorHierarchy> getSponsorHierarchyList() {
        return sponsorHierarchyList;
    }


    public void setSponsorHierarchyList(List<SponsorHierarchy> sponsorHierarchyList) {
        this.sponsorHierarchyList = sponsorHierarchyList;
    }

    public void addSponsorHierarchyGroup() {
        if (getSponsorHierarchyList() == null) {
            setSponsorHierarchyList(new ArrayList<SponsorHierarchy>());
        }
         getSponsorHierarchyList().add(newSponsorHierarchy);
         newSponsorHierarchy = new SponsorHierarchy();

    }


    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }


    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }


    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }


    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }


    public String getLookedUpCollectionName() {
        return lookedUpCollectionName;
    }


    public void setLookedUpCollectionName(String lookedUpCollectionName) {
        this.lookedUpCollectionName = lookedUpCollectionName;
    }


    public List<List> getNewSponsors() {
        return newSponsors;
    }


    public void setNewSponsors(List<List> newSponsors) {
        this.newSponsors = newSponsors;
    }


    public String getSelectedSponsors() {
        return selectedSponsors;
    }


    public void setSelectedSponsors(String selectedSponsors) {
        this.selectedSponsors = selectedSponsors;
    }


    public String getActionSelected() {
        return actionSelected;
    }


    public void setActionSelected(String actionSelected) {
        this.actionSelected = actionSelected;
    }


    public String getMapKey() {
        return mapKey;
    }


    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }


    public String getSponsorCodeList() {
        return sponsorCodeList;
    }


    public void setSponsorCodeList(String sponsorCodeList) {
        this.sponsorCodeList = sponsorCodeList;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getSqlScripts() {
        return sqlScripts;
    }


    public void setSqlScripts(String sqlScripts) {
        this.sqlScripts = sqlScripts;
    }


    public int getNumberPerGroup() {
        int groupingNumber = 300;
        try {
           String sysParam = this.getParameterService().getParameterValueAsString(
                Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.ALL_COMPONENT, Constants.NUMBER_PER_SPONSOR_HIERARCHY_GROUP);
           groupingNumber=Integer.parseInt(sysParam);
        } catch (Exception e) {
            LOG.debug("System param for numberPerSponsorHierarchyGroup is not defined");
        }
 
        return groupingNumber;

    }


    public void setNumberPerGroup(int numberPerGroup) {
        this.numberPerGroup = numberPerGroup;
    }


    public String getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    public boolean isPrintingHierarchy() {
        String printingHierarchyName = getParameterService().getParameterValueAsString(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
                Constants.SPONSOR_HIERARCHY_PRINTING_NAME_PARAM);
        return StringUtils.equals(getHierarchyName(), printingHierarchyName);
    }
}
