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
package org.kuali.coeus.common.framework.type;

import org.kuali.coeus.common.api.type.InvestigatorCreditTypeContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;

/**
 * Class representation of the Person <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @see org.kuali.rice.krad.bo.BusinessObject
 * @see org.kuali.rice.krad.bo.PersistableBusinessObject
 * $Id: InvestigatorCreditType.java,v 1.6 2008-07-23 19:16:37 gmcgrego Exp $
 */
@Entity
@Table(name = "INV_CREDIT_TYPE")
public class InvestigatorCreditType extends KcPersistableBusinessObjectBase implements MutableInactivatable, InvestigatorCreditTypeContract {

    private static final long serialVersionUID = 2881039955568764530L;

    @Id
    @Column(name = "INV_CREDIT_TYPE_CODE")
    private String code;

    @Column(name = "ADDS_TO_HUNDRED")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean addsToHundred;

    @Column(name = "ACTIVE_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    @Column(name = "DESCRIPTION")
    private String description;


    public InvestigatorCreditType() {
    }

    public InvestigatorCreditType(String code, String description) {
        this.code = code;
        this.description = description;
        this.active = true;
        this.addsToHundred = true;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean addsToHundred() {
        return getAddsToHundred();
    }

    @Override
    public Boolean getAddsToHundred() {
        return addsToHundred;
    }


    public void setAddsToHundred(Boolean argAddsToHundred) {
        this.addsToHundred = argAddsToHundred;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String argInvCreditTypeCode) {
        code = argInvCreditTypeCode;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((code == null) ? 0 : code.hashCode());
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
        if (!(obj instanceof InvestigatorCreditType)) {
            return false;
        }
        InvestigatorCreditType other = (InvestigatorCreditType) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        return true;
    }
}
