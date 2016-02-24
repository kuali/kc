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
