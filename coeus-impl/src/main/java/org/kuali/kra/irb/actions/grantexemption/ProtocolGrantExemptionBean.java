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
