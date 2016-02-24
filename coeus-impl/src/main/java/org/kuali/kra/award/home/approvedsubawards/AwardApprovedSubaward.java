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
package org.kuali.kra.award.home.approvedsubawards;

import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

/**
 * This class is the Business Object representation of an Award Approved Subaward
 */
public class AwardApprovedSubaward extends AwardAssociate implements ValuableItem {


    private static final long serialVersionUID = -5025168632828604306L;

    private Long awardApprovedSubawardId;

    private String organizationId;

    private String organizationName;

    private ScaleTwoDecimal amount;

    private Organization organization;

    public AwardApprovedSubaward() {
        setAmount(new ScaleTwoDecimal(0.00));
    }

    public Long getAwardApprovedSubawardId() {
        return awardApprovedSubawardId;
    }

    public void setAwardApprovedSubawardId(Long awardApprovedSubawardId) {
        this.awardApprovedSubawardId = awardApprovedSubawardId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * Gets the organizationId attribute. 
     * @return Returns the organizationId.
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * Sets the organizationId attribute value.
     * @param organizationId The organizationId to set.
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * Gets the organization attribute. 
     * @return Returns the organization.
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Sets the organization attribute value.
     * @param organization The organization to set.
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public ScaleTwoDecimal getAmount() {
        return amount;
    }

    public void setAmount(ScaleTwoDecimal amount) {
        this.amount = amount;
    }

    //CSOFF  
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * super.hashCode();
        result = PRIME * result + ((amount == null) ? 0 : amount.hashCode());
        result = PRIME * result + ((awardApprovedSubawardId == null) ? 0 : awardApprovedSubawardId.hashCode());
        result = PRIME * result + ((organizationName == null) ? 0 : organizationName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final AwardApprovedSubaward OTHER = (AwardApprovedSubaward) obj;
        if (amount == null) {
            if (OTHER.amount != null) {
                return false;
            }
        } else if (!amount.equals(OTHER.amount)) {
            return false;
        }
        if (awardApprovedSubawardId == null) {
            if (OTHER.awardApprovedSubawardId != null) {
                return false;
            }
        } else if (!awardApprovedSubawardId.equals(OTHER.awardApprovedSubawardId)) {
            return false;
        }
        if (organizationName == null) {
            if (OTHER.organizationName != null) {
                return false;
            }
        } else if (!organizationName.equals(OTHER.organizationName)) {
            return false;
        }
        return true;
    }

    //CSON  
    @Override
    public void resetPersistenceState() {
        this.awardApprovedSubawardId = null;
    }
}
