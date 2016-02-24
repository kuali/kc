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
package org.kuali.kra.iacuc.actions.notifycommittee;

import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.actions.ActionHelperBase;
import org.kuali.kra.protocol.actions.notifycommittee.ProtocolNotifyCommitteeBean;

import java.sql.Date;

/**
 * This class is really just a "form" for notifying the CommitteeBase.
 */
public class IacucProtocolNotifyCommitteeBean extends IacucProtocolActionBean implements ProtocolNotifyCommitteeBean {
    

    private static final long serialVersionUID = 3812176663326229406L;
    
    private String comment = "";
    private String committeeId;
    private Date actionDate = new Date(System.currentTimeMillis());

    
    
    /**
     * Constructs a ProtocolNotifyCommitteeBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolNotifyCommitteeBean(ActionHelperBase actionHelper) {
        super(actionHelper);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }    

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
    
    public String getCommitteeId() {
        return committeeId;
    }
    
    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;        
    }

    @Override
    public void prepareView() {
        // we refresh only if the user is not currently working on this task since we do not want to lose user changes
        if( !(TaskName.NOTIFY_COMMITTEE.equalsIgnoreCase(getActionHelper().getCurrentTask())) ) {
            // do nothing, a placeholder for code to be added as fuctionality for this action is fleshed out better 
        }
        
    }
}
