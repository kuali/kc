/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;

/**
 * This class represents a Proposal Site. It can either refer to an Organization, or to
 * a Rolodex entry.
 */
public class ProposalSite extends KraPersistableBusinessObjectBase {
    private static final long serialVersionUID = -1657749549230077805L;
    // prroposal site types, see LOCATION_TYPE table
    public static final int PROPOSAL_SITE_APPLICANT_ORGANIZATION = 1;
    public static final int PROPOSAL_SITE_PERFORMING_ORGANIZATION = 2;
    public static final int PROPOSAL_SITE_OTHER_ORGANIZATION = 3;
    public static final int PROPOSAL_SITE_PERFORMANCE_SITE = 4;
    
    private String proposalNumber;
    private int siteNumber;
    private String locationName;
    private int locationTypeCode;
    private String organizationId;
    private Organization organization;
    private int rolodexId;
    private Rolodex rolodex;
    private List<CongressionalDistrict> congressionalDistricts;

    public ProposalSite() {
        congressionalDistricts = new ArrayList<CongressionalDistrict>();
    }
    
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setSiteNumber(int siteNumber) {
        this.siteNumber = siteNumber;
    }

    public int getSiteNumber() {
        return siteNumber;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * This method returns the name of the Proposal Site
     * @return 
     */
    public String getLocationName() {
        return locationName;
    }

    public void setLocationTypeCode(int locationTypeCode) {
        this.locationTypeCode = locationTypeCode;
    }

    public int getLocationTypeCode() {
        return locationTypeCode;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setRolodexId(int rolodexId) {
        this.rolodexId = rolodexId;
    }

    public int getRolodexId() {
        return rolodexId;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setCongressionalDistricts(List<CongressionalDistrict> congressionalDistricts) {
        this.congressionalDistricts = congressionalDistricts;
    }

    public List<CongressionalDistrict> getCongressionalDistricts() {
        return congressionalDistricts;
    }

    public void addCongressionalDistrict(CongressionalDistrict congressionalDistrict) {
        congressionalDistricts.add(congressionalDistrict);
    }
    
    public void deleteCongressionalDistrict(int districtIndex) {
        congressionalDistricts.remove(districtIndex);
    }
    
    /**
     * This method returns the first congressional district defined for the ProposalSite,
     * or null if there is none.
     * @return
     */
    public CongressionalDistrict getFirstCongressionalDistrict() {
        if (congressionalDistricts==null || congressionalDistricts.isEmpty()) {
            return null;
        }
        else {
            return congressionalDistricts.get(0);
        }
    }

    /**
     * This method returns the name first congressional district defined for the ProposalSite,
     * or an empty string if there is none.
     * @return
     */
    public String getFirstCongressionalDistrictName() {
        CongressionalDistrict firstDistrict = getFirstCongressionalDistrict();
        if (firstDistrict == null) {
            return "";
        }
        else {
            return firstDistrict.getCongressionalDistrict();
        }
    }

    @Override
    protected LinkedHashMap<String, ?> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("proposalNumber", this.getProposalNumber());
        hashMap.put("locationName", this.getLocationName());
        hashMap.put("locationTypeCode", this.getLocationTypeCode());
        hashMap.put("organizationId", this.getOrganizationId());
        hashMap.put("rolodexId", this.getRolodexId());
        return hashMap;
    }

}
