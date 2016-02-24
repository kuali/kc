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
package org.kuali.coeus.common.impl.sponsor.hierarchy;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.sponsor.hierarchy.SponsorHierarchy;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.springframework.util.AutoPopulatingList;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


    public SponsorHierarchyForm() {
        super();
        hierarchyNameList=new ArrayList();
        newSponsors = new AutoPopulatingList<List>(ArrayList.class);
        //topSponsorHierarchies = KcServiceLocator.getService(SponsorService.class).getTopSponsorHierarchy();        

    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
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
