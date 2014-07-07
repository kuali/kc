/*
 * Copyright 2005-2014 The Kuali Foundation
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
