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
package org.kuali.kra.proposaldevelopment.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

/**
 * Class representation of the Person <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @see org.kuali.rice.krad.bo.BusinessObject
 * @see org.kuali.rice.krad.bo.PersistableBusinessObject
 * $Id: InvestigatorCreditType.java,v 1.6 2008-07-23 19:16:37 gmcgrego Exp $
 */
public class InvestigatorCreditType extends KraPersistableBusinessObjectBase implements MutableInactivatable {

    private static final long serialVersionUID = 2881039955568764530L;

    private String invCreditTypeCode;

    private Boolean addsToHundred;

    private boolean active;

    private String description;

    /**
     * Default constructor
     */
    public InvestigatorCreditType() {
    }

    /**
     * Convenience constructor
     * @param invCreditTypeCode
     * @param description
     */
    public InvestigatorCreditType(String invCreditTypeCode, String description) {
        this.invCreditTypeCode = invCreditTypeCode;
        this.description = description;
        this.active = true;
        this.addsToHundred = true;
    }

    /**
     * Retrieves the description attribute
     * 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assigns the description attribute
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the description attribute
     * 
     * @return Boolean
     */
    public Boolean addsToHundred() {
        return getAddsToHundred();
    }

    /**
     * Retrieves the description attribute
     * 
     * @return Boolean
     */
    public Boolean getAddsToHundred() {
        return addsToHundred;
    }

    /**
     * Assigns the description attribute
     *
     * @param argAddsToHundred
     */
    public void setAddsToHundred(Boolean argAddsToHundred) {
        this.addsToHundred = argAddsToHundred;
    }

    /**
     * Gets the value of invCreditTypeCode
     *
     * @return the value of invCreditTypeCode
     */
    public String getInvCreditTypeCode() {
        return invCreditTypeCode;
    }

    /**
     * Sets the value of invCreditTypeCode
     *
     * @param argInvCreditTypeCode Value to assign to this.invCreditTypeCode
     */
    public void setInvCreditTypeCode(String argInvCreditTypeCode) {
        invCreditTypeCode = argInvCreditTypeCode;
    }

    /**
     * Read access to the active flag
     * @return Boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Write access to the active flag
     * 
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((invCreditTypeCode == null) ? 0 : invCreditTypeCode.hashCode());
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
        if (!(obj instanceof InvestigatorCreditType)) {
            return false;
        }
        InvestigatorCreditType other = (InvestigatorCreditType) obj;
        if (invCreditTypeCode == null) {
            if (other.invCreditTypeCode != null) {
                return false;
            }
        } else if (!invCreditTypeCode.equals(other.invCreditTypeCode)) {
            return false;
        }
        return true;
    }
}
