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
package org.kuali.kra.irb.actions.approve;

import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolRiskLevelCommentable;
import org.kuali.kra.irb.actions.genericactions.ProtocolGenericActionBean;
import org.kuali.kra.irb.actions.risklevel.ProtocolRiskLevelBean;

import java.sql.Date;

/**
 * This class is really just a "form" for approving a protocol.
 */
public class ProtocolApproveBean extends ProtocolGenericActionBean implements org.kuali.kra.protocol.actions.approve.ProtocolApproveBean, ProtocolRiskLevelCommentable {

    private static final long serialVersionUID = 8022339401747868812L;
    
    private Date approvalDate;
    private Date expirationDate;
    
    private String errorPropertyKey;
    private ProtocolRiskLevelBean protocolRiskLevelBean;

    private int defaultExpirationDateDifference;
    
    /**
     * Constructs a ProtocolApproveBean.
     * @param actionHelper a reference back to the parent helper
     */
    public ProtocolApproveBean(ActionHelper actionHelper, String errorPropertyKey) {
        super(actionHelper, errorPropertyKey);
        
        this.errorPropertyKey = errorPropertyKey;
        protocolRiskLevelBean = new ProtocolRiskLevelBean(errorPropertyKey);
    }
    
    public Date getApprovalDate() {
        return approvalDate;
    }
    
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }
    
    public Date getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public String getErrorPropertyKey() {
        return errorPropertyKey;
    }
       
    public ProtocolRiskLevelBean getProtocolRiskLevelBean() {
        return protocolRiskLevelBean;
    }
    
    public int getDefaultExpirationDateDifference() {
        return defaultExpirationDateDifference;
    }

    public void setDefaultExpirationDateDifference(int defaultExpirationDateDifference) {
        this.defaultExpirationDateDifference = defaultExpirationDateDifference;
    }
    
}