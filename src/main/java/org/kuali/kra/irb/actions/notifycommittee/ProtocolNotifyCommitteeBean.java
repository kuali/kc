/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.io.Serializable;
import java.sql.Date;

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.actions.ProtocolActionBean;

/**
 * This class is really just a "form" for notifying the Committee.
 */
public class ProtocolNotifyCommitteeBean extends ProtocolActionBean implements Serializable {
    
    private String comment = "";
    private Committee committee;
    private Date actionDate = new Date(System.currentTimeMillis());
    
    /**
     * Constructs a ProtocolNotifyCommitteeBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public ProtocolNotifyCommitteeBean(ActionHelper actionHelper) {
        super(actionHelper);
        committee = actionHelper.getProtocol().getProtocolSubmission().getCommittee();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommitteeId() {
        return committee.getCommitteeId();
    }

    public String getCommitteeName() {
        return committee.getCommitteeName();
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
    
}
