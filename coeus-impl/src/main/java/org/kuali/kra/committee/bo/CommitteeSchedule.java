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
package org.kuali.kra.committee.bo;

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionLite;
import org.kuali.kra.meeting.CommitteeScheduleMinute;

/**
 * This is BO class to support CommitteeScheulde. It has three transient field to support UI.
 */
public class CommitteeSchedule extends CommitteeScheduleBase<CommitteeSchedule, Committee, ProtocolSubmissionLite, CommitteeScheduleMinute> {
    
    private static final long serialVersionUID = -360139608123017188L;
    
    private Committee committee;

    
    public Committee getParentCommittee() {
        return this.getCommittee();
    }
    
    public Committee getCommittee() {
        if (committee == null && getCommitteeIdFk() == null) {
            committee = new Committee();
        }
        return committee;
    }
    
	public void setCommittee(Committee committee) {
		this.committee = committee;
	}

}
