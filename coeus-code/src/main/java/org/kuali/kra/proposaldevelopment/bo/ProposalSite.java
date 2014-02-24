/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.bo.Organization;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Proposal Site. It can either refer to an Organization, or to
 * a Rolodex entry.
 */
@Entity
@Table(name = "EPS_PROP_SITES")
@IdClass(ProposalSite.ProposalSiteId.class)
public class ProposalSite extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -1657749549230077805L;

    // prroposal site types, see LOCATION_TYPE table  
    public static final int PROPOSAL_SITE_APPLICANT_ORGANIZATION = 1;

    public static final int PROPOSAL_SITE_PERFORMING_ORGANIZATION = 2;

    public static final int PROPOSAL_SITE_OTHER_ORGANIZATION = 3;

    public static final int PROPOSAL_SITE_PERFORMANCE_SITE = 4;

    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Id
    @Column(name = "SITE_NUMBER")
    private Integer siteNumber;

    @Column(name = "LOCATION_NAME", nullable = false)
    private String locationName;

    @Column(name = "LOCATION_TYPE_CODE", nullable = false)
    private Integer locationTypeCode;

    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @ManyToOne(targetEntity = Organization.class, fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private Organization organization;

    @Column(name = "ROLODEX_ID")
    private Integer rolodexId;

    @ManyToOne(targetEntity = Rolodex.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ROLODEX_ID", referencedColumnName = "ROLODEX_ID", insertable = false, updatable = false)
    private Rolodex rolodex;

    @OneToMany(targetEntity = CongressionalDistrict.class, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })

    @JoinColumns({ @JoinColumn(name = "PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false), @JoinColumn(name = "SITE_NUMBER", referencedColumnName = "SITE_NUMBER", insertable = false, updatable = false) })
    @OrderBy("siteNumber")
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

    public void setSiteNumber(Integer siteNumber) {
        this.siteNumber = siteNumber;
    }

    public Integer getSiteNumber() {
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

    public void setLocationTypeCode(Integer locationTypeCode) {
        this.locationTypeCode = locationTypeCode;
    }

    public Integer getLocationTypeCode() {
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

    public void setRolodexId(Integer rolodexId) {
        // When the Rolodex entry changes, remove the congressional districts of the old Rolodex  
        if (this.rolodexId != null && !this.rolodexId.equals(rolodexId)) {
            congressionalDistricts.clear();
        }
        this.rolodexId = rolodexId;
        this.refreshReferenceObject("rolodex");
    }

    public Integer getRolodexId() {
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

    public void setDefaultCongressionalDistrict(CongressionalDistrict congressionalDistrict) {
        if (!contains(congressionalDistrict.getCongressionalDistrict())) {
            congressionalDistricts.add(0, congressionalDistrict);
        }
    }

    /**
     * This method tests whether the ProposalSite contains a ongressional district with a given congressionalDistrict value.
     * @param congressionalDistrictIdentifier
     * @return
     */
    private boolean contains(String congressionalDistrictIdentifier) {
        for (CongressionalDistrict district : congressionalDistricts) {
            if (StringUtils.equals(district.getCongressionalDistrict(), congressionalDistrictIdentifier)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns the first congressional district defined for the ProposalSite,
     * or null if there is none.
     * @return
     */
    public CongressionalDistrict getDefaultCongressionalDistrict() {
        if (congressionalDistricts == null || congressionalDistricts.isEmpty()) {
            return null;
        } else {
            return congressionalDistricts.get(0);
        }
    }

    /**
     * This method returns the name first congressional district defined for the ProposalSite,
     * or an empty string if there is none.
     * @return
     */
    public String getFirstCongressionalDistrictName() {
        CongressionalDistrict firstDistrict = getDefaultCongressionalDistrict();
        if (firstDistrict == null) {
            return "";
        } else {
            return firstDistrict.getCongressionalDistrict();
        }
    }

    /**
     * This method deletes all existing congressional districts and adds one new congressional
     * district.
     * @param districtIdentifier The congressional district string, e.g. "AZ-5"
     */
    public void setDefaultCongressionalDistrictIdentifier(String districtIdentifier) {
        if (!StringUtils.isEmpty(districtIdentifier) && !contains(districtIdentifier)) {
            congressionalDistricts.clear();
            CongressionalDistrict defaultDistrict = new CongressionalDistrict();
            defaultDistrict.setCongressionalDistrict(districtIdentifier);
            defaultDistrict.setProposalNumber(proposalNumber);
            defaultDistrict.setSiteNumber(siteNumber);
            setDefaultCongressionalDistrict(defaultDistrict);
        }
    }

    /**
     * This method creates a CongressionalDistrict from the district defined in the Organization,
     * and adds it to the list of congressional districts if it doesn't exist yet.
     * The proposalNumber and siteNumber are set on the CongressionalDistrict, so they should
     * be initialized before calling this method.
     */
    public void initializeDefaultCongressionalDistrict() {
        Organization organization = getOrganization();
        if (organization != null) {
            String defaultDistrict = organization.getCongressionalDistrict();
            if (!StringUtils.isEmpty(defaultDistrict)) {
                setDefaultCongressionalDistrictIdentifier(defaultDistrict);
            }
        }
    }

    public static final class ProposalSiteId implements Serializable, Comparable<ProposalSiteId> {

        private String proposalNumber;

        private Integer siteNumber;

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        public Integer getSiteNumber() {
            return this.siteNumber;
        }

        public void setSiteNumber(Integer siteNumber) {
            this.siteNumber = siteNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("proposalNumber", this.proposalNumber).append("siteNumber", this.siteNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ProposalSiteId rhs = (ProposalSiteId) other;
            return new EqualsBuilder().append(this.proposalNumber, rhs.proposalNumber).append(this.siteNumber, rhs.siteNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.proposalNumber).append(this.siteNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalSiteId other) {
            return new CompareToBuilder().append(this.proposalNumber, other.proposalNumber).append(this.siteNumber, other.siteNumber).toComparison();
        }
    }
}
