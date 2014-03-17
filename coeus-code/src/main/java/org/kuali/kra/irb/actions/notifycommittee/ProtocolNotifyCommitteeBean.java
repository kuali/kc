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
package org.kuali.kra.irb.actions.notifycommittee;

import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionBean;

import java.sql.Date;

/**
 * This class is really just a "form" for notifying the Committee.
 */
public class ProtocolNotifyCommitteeBean extends ProtocolActionBean implements org.kuali.kra.protocol.actions.notifycommittee.ProtocolNotifyCommitteeBean {
    

    private static final long serialVersionUID = 6386919161260179234L;
    
    private String comment = "";
    private String committeeId;
    private Date actionDate = new Date(System.currentTimeMillis());
    
    /**
     * Constructs a ProtocolNotifyCommitteeBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolNotifyCommitteeBean(ActionHelper actionHelper) {
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
