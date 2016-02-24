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
package org.kuali.coeus.common.framework.compliance.exemption;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Defines the base class for the Special Review Exemption business object for all modules.
 */
@MappedSuperclass
public abstract class SpecialReviewExemption extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -3039637933149436453L;

    @Column(name = "EXEMPTION_TYPE_CODE")
    private String exemptionTypeCode;

    @ManyToOne
    @JoinColumn(name = "EXEMPTION_TYPE_CODE", referencedColumnName = "EXEMPTION_TYPE_CODE", insertable = false, updatable = false)
    private ExemptionType exemptionType;

    public String getExemptionTypeCode() {
        return exemptionTypeCode;
    }

    public void setExemptionTypeCode(String exemptionTypeCode) {
        this.exemptionTypeCode = exemptionTypeCode;
    }

    public ExemptionType getExemptionType() {
        return exemptionType;
    }

    public void setExemptionType(ExemptionType exemptionType) {
        this.exemptionType = exemptionType;
    }
}
