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
package org.kuali.kra.iacuc.actions.noreview;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;
import org.kuali.kra.protocol.actions.ActionHelperBase;
import org.kuali.kra.protocol.actions.noreview.ProtocolReviewNotRequiredBean;

import java.sql.Date;

/**
 * This class manages the HTML Elements needed for the review not required panel.
 */
public class IacucProtocolReviewNotRequiredBean extends IacucProtocolActionBean implements ProtocolReviewNotRequiredBean {

    private static final long serialVersionUID = -8686091412369007790L;
    
    private String comments = "";
    private Date actionDate = new Date(System.currentTimeMillis());
    private Date decisionDate = new Date(System.currentTimeMillis());

    /**
     * Constructs a ProtocolReviewNotRequiredBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolReviewNotRequiredBean(ActionHelperBase actionHelper) {
        super(actionHelper);
    }

    public IacucProtocol getIacucProtocol() {
        return (IacucProtocol)getProtocol();
    }
    

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }    
}
