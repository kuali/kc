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
package org.kuali.kra.irb.actions.withdraw;

import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionBean;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionsCorrespondenceBase;

import java.sql.Date;

/**
 * This class is really just a "form" containing the reason
 * for withdrawing a protocol.
 */
public class ProtocolWithdrawBean extends ProtocolActionBean implements org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean {
    
    private static final long serialVersionUID = -3244694733749584969L;
    
    private String reason = "";
    
    private Date actionDate = new Date(System.currentTimeMillis());
    
    /**
     * Constructs a ProtocolWithdrawBean.java.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolWithdrawBean(ActionHelper actionHelper) {
        super(actionHelper);
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
    
    public Date getActionDate() {
        return actionDate;
    }
    
    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
    
    /**
     * 
     * This method returns the correct correspondence for this object
     * @return a WithdrawCorrespondence object
     */
    public ProtocolActionsCorrespondenceBase getCorrespondence() {
        WithdrawCorrespondence correspondence = new WithdrawCorrespondence();
        return correspondence;
    }
}
