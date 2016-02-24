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
package org.kuali.kra.irb.actions;

import org.kuali.kra.protocol.actions.notify.ProtocolActionAttachment;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class for the base property of request/notify irb action
 */
public abstract class ProtocolSubmissionBeanBase extends ProtocolActionBean implements org.kuali.kra.protocol.actions.ProtocolSubmissionBeanBase {
    
    private String committeeId;
    private ProtocolActionAttachment newActionAttachment;
    private List<ProtocolActionAttachment> actionAttachments = new ArrayList<ProtocolActionAttachment>();

    public ProtocolSubmissionBeanBase(ActionHelper actionHelper) {
        super(actionHelper);
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public ProtocolActionAttachment getNewActionAttachment() {
        return newActionAttachment;
    }

    public void setNewActionAttachment(ProtocolActionAttachment newActionAttachment) {
        this.newActionAttachment = newActionAttachment;
    }

    public List<ProtocolActionAttachment> getActionAttachments() {
        return actionAttachments;
    }

    public void setActionAttachments(List<org.kuali.kra.protocol.actions.notify.ProtocolActionAttachment> actionAttachments) {
        this.actionAttachments = actionAttachments;
    }

}
