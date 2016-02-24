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
package org.kuali.kra.iacuc.actions.withdraw;

import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.correspondence.IacucProtocolActionsCorrespondence;
import org.kuali.kra.protocol.actions.withdraw.ProtocolWithdrawBean;

import java.sql.Date;

/**
 * This class is really just a "form" containing the reason
 * for withdrawing a protocol.
 */
public class IacucProtocolWithdrawBean extends IacucProtocolActionBean implements ProtocolWithdrawBean {
    

    private static final long serialVersionUID = 4142586313917806739L;
    
    private String reason = "";
    
    private Date actionDate = new Date(System.currentTimeMillis());
    
    /**
     * Constructs a ProtocolWithdrawBean.java.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolWithdrawBean(IacucActionHelper actionHelper) {
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
     * 
     */
    public IacucProtocolActionsCorrespondence getCorrespondence() {
        IacucProtocolActionsCorrespondence correspondence = new IacucProtocolActionsCorrespondence(IacucProtocolActionType.IACUC_WITHDRAWN);
        return correspondence;
    }

}
