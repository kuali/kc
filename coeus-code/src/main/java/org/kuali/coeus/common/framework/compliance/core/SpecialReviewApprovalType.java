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
package org.kuali.coeus.common.framework.compliance.core;

import org.kuali.coeus.common.api.compliance.core.SpecialReviewApprovalTypeContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Defines the Special Review Approval Type business object for all modules.
 */
@Entity
@Table(name = "SP_REV_APPROVAL_TYPE")
public class SpecialReviewApprovalType extends KcPersistableBusinessObjectBase implements SpecialReviewApprovalTypeContract {

    /**
     * The type code for Approval Type 'Approved'.
     */
    public static final String PENDING = "1";

    public static final String APPROVED = "2";

    public static final String NOT_YET_APPLIED = "3";

    public static final String EXEMPT = "4";

    /**
     * The type code for Approval Type 'Link to IRB'.
     */
    public static final String LINK_TO_IRB = "5";

    /**
     * The type code for Approval Type 'Link to IACUC'.
     */
    public static final String LINK_TO_IACUC = "6";

    private static final long serialVersionUID = -3695729124365459765L;

    @Id
    @Column(name = "APPROVAL_TYPE_CODE")
    private String approvalTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getApprovalTypeCode() {
        return approvalTypeCode;
    }

    public void setApprovalTypeCode(String approvalTypeCode) {
        this.approvalTypeCode = approvalTypeCode;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getCode() {
        return getApprovalTypeCode();
    }
}
