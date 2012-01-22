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
package org.kuali.kra.bo;


/**
 * Defines the Special Review Approval Type business object for all modules.
 */
public class SpecialReviewApprovalType extends KraPersistableBusinessObjectBase {

    /**
     * The type code for Approval Type 'Approved'.
     */
    public static final String APPROVED = "2";

    /**
     * The type code for Approval Type 'Link to IRB'.
     */
    public static final String LINK_TO_IRB = "5";

    private static final long serialVersionUID = -3695729124365459765L;

    private String approvalTypeCode;

    private String description;

    public String getApprovalTypeCode() {
        return approvalTypeCode;
    }

    public void setApprovalTypeCode(String approvalTypeCode) {
        this.approvalTypeCode = approvalTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
