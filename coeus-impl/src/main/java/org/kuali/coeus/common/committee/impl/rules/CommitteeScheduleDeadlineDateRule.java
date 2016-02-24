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
package org.kuali.coeus.common.committee.impl.rules;

import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeScheduleDeadlineEvent;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;

import java.sql.Date;
import java.util.List;

public class CommitteeScheduleDeadlineDateRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<CommitteeScheduleDeadlineEvent> {
    
    public static final String ID = "document.committeeList[0].committeeSchedules[%1$s].protocolSubDeadline";
    
    @Override
    public boolean processRules(CommitteeScheduleDeadlineEvent deadlineCommitteeScheduleEvent) {
        
        boolean rulePassed = true;
        
        List<CommitteeScheduleBase> committeeSchedules = deadlineCommitteeScheduleEvent.getCommitteeSchedules();
        int count = 0;
        for(CommitteeScheduleBase committeeSchedule : committeeSchedules) {
            
            Date deadline = committeeSchedule.getProtocolSubDeadline();
            Date schedule = committeeSchedule.getScheduledDate();
            
            if ((schedule != null) && (deadline != null) && (schedule.before(deadline))) {
                reportError(String.format(ID, count), KeyConstants.ERROR_COMMITTEESCHEDULE_DEADLINE, deadline.toString(), schedule.toString());
                rulePassed = false;
            }
            count++;
        }
        
        return rulePassed;
    }

}
