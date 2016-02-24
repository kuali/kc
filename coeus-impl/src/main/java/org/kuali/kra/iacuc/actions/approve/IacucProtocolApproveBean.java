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
package org.kuali.kra.iacuc.actions.approve;

import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.actions.genericactions.IacucProtocolGenericActionBean;
import org.kuali.kra.protocol.actions.approve.ProtocolApproveBean;

import java.sql.Date;

public class IacucProtocolApproveBean extends IacucProtocolGenericActionBean implements ProtocolApproveBean {
    

    private static final long serialVersionUID = -8184965873953673608L;
    
    private Date approvalDate;
    private Date expirationDate;
    
    private int defaultExpirationDateDifference;
 
    /**
     * Constructs a ProtocolApproveBean.
     * @param actionHelper a reference back to the parent helper
     */
    public IacucProtocolApproveBean(IacucActionHelper actionHelper, String errorPropertyKey) {
        super(actionHelper, errorPropertyKey);
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

    public int getDefaultExpirationDateDifference() {
        return defaultExpirationDateDifference;
    }

    public void setDefaultExpirationDateDifference(int defaultExpirationDateDifference) {
        this.defaultExpirationDateDifference = defaultExpirationDateDifference;
    }
 
       
}
