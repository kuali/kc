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
package org.kuali.kra.common.committee.rules;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.common.committee.rule.event.CommitteeScheduleDeadlineEvent;
import org.kuali.kra.infrastructure.KeyConstants;

import java.sql.Date;
import java.util.List;

public class CommitteeScheduleDeadlineDateRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<CommitteeScheduleDeadlineEvent> {
    
    public static final String ID = "document.committeeList[0].committeeSchedules[%1$s].protocolSubDeadline";
    
    /**
     * @see org.kuali.kra.common.committee.rule.DeadlineCommitteeScheduleRule#processCommitteeScheduleDeadlineBusinessRules(org.kuali.kra.common.committee.rule.event.CommitteeScheduleDeadlineEvent)
     */
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
