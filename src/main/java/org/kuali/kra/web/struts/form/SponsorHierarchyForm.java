/*
 * Copyright 2006-2008 The Kuali Foundation
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

import org.apache.struts.action.ActionMapping;
import org.kuali.core.util.TypedArrayList;
import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.SponsorHierarchy;

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
    private String timestamp;
    private String message;

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
        newSponsors = new TypedArrayList(ArrayList.class);
        //topSponsorHierarchies = KraServiceLocator.getService(SponsorService.class).getTopSponsorHierarchy();        

    }

    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // FIXME : just a temporary soln.  it always get the methodtocall='refresh' after it started properly the first time.  
        // need to investigate this.
        this.setMethodToCall("");
        this.setMessage("");
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


    public String getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


}
