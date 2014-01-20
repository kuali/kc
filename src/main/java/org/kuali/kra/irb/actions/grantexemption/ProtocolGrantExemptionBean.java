/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.irb.actions.grantexemption;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;

import java.io.Serializable;
import java.sql.Date;

/**
 * This class is really just a "form" for granting an exemption.
 */
public class ProtocolGrantExemptionBean extends ProtocolGenericActionBean implements Serializable {

    private static final long serialVersionUID = 1066298574931838541L;
    
    private Date approvalDate = new Date(System.currentTimeMillis());
    
    /**
     * Constructs a ProtocolGrantExemptionBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolGrantExemptionBean(ActionHelper actionHelper) {
        super(actionHelper, Constants.PROTOCOL_GRANT_EXEMPTION_ACTION_PROPERTY_KEY);
    }
    
    public Date getApprovalDate() {
        return approvalDate;
    }
    
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public GrantExemptionCorrespondence getCorrespondence() {
        return new GrantExemptionCorrespondence();
    }
    
}