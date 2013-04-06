/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.notifycommittee;

import java.sql.Date;

import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.iacuc.actions.IacucProtocolActionBean;
import org.kuali.kra.protocol.actions.ActionHelperBase;
import org.kuali.kra.protocol.actions.notifycommittee.ProtocolNotifyCommitteeBean;

/**
 * This class is really just a "form" for notifying the CommitteeBase.
 */
public class IacucProtocolNotifyCommitteeBean extends IacucProtocolActionBean implements ProtocolNotifyCommitteeBean {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3812176663326229406L;
    
    private String comment = "";
    private CommitteeBase committee;
    private Date actionDate = new Date(System.currentTimeMillis());
    
    /**
     * Constructs a ProtocolNotifyCommitteeBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolNotifyCommitteeBean(ActionHelperBase actionHelper) {
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

    public void setCommittee(CommitteeBase committee) {
        this.committee = committee;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
    
}
