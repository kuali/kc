/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.award.home.approvedsubawards;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.ValuableItem;
import org.kuali.kra.bo.Organization;
import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 * This class is the Business Object representation of an Award Approved Subaward
 */
public class AwardApprovedSubaward extends AwardAssociate implements ValuableItem {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5025168632828604306L;

    private Long awardApprovedSubawardId;

    private String organizationId;

    private String organizationName;

    private KualiDecimal amount;

    private Organization organization;

    public AwardApprovedSubaward() {
        setAmount(new KualiDecimal(0.00));
    }

    /**
     * This method...
     * @return
     */
    public Long getAwardApprovedSubawardId() {
        return awardApprovedSubawardId;
    }

    /**
     * This method...
     * @param awardApprovedSubawardId
     */
    public void setAwardApprovedSubawardId(Long awardApprovedSubawardId) {
        this.awardApprovedSubawardId = awardApprovedSubawardId;
    }

    /**
     * This method...
     * @return
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * This method...
     * @param organizationName
     */
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

    /**
     * This method...
     * @return
     */
    public KualiDecimal getAmount() {
        return amount;
    }

    /**
     * This method...
     * @param amount
     */
    public void setAmount(KualiDecimal amount) {
        this.amount = amount;
    }

    //CSOFF  
    /**
     * @see java.lang.Object#hashCode()
     */
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

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardApprovedSubawardId = null;
    }
}
