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
package org.kuali.coeus.common.committee.impl.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.rule.event.DeleteCommitteeScheduleEventBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;

import java.util.List;

/**
 * 
 * This class implements rule for deleting committee schedule.
 */
public abstract class DeleteCommitteeScheduleRuleBase  extends KcTransactionalDocumentRuleBase implements KcBusinessRule<DeleteCommitteeScheduleEventBase> {
    
    private static final String ID = "document.committeeList[0].committeeSchedules[";
   
    /**
     * If committee schedule has protocol submitted or has meeting data, then it can not be deleted.
     * @see org.kuali.coeus.sys.framework.rule.KcBusinessRule#processRules(org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension)
     */
    public boolean processRules(DeleteCommitteeScheduleEventBase deleteCommitteeScheduleEvent) {

        boolean rulePassed = true;
        List<CommitteeScheduleBase> schedules = deleteCommitteeScheduleEvent.getCommitteeSchedules();
        CommitteeBase activeCommittee = getCommitteeService().getCommitteeById(
                ((CommitteeDocumentBase) deleteCommitteeScheduleEvent.getDocument()).getCommittee().getCommitteeId());
        if (activeCommittee != null) {
            int i = 0;
            for (CommitteeScheduleBase schedule : schedules) {
                if (schedule.isSelected() && canNotDelete(activeCommittee.getCommitteeSchedules(), schedule.getScheduleId())) {
                    reportError(ID + i + "].selected", KeyConstants.ERROR_COMMITTEESCHEDULE_DELETE);
                    rulePassed = false;
                }
                i++;
            }
        }
        return rulePassed;
    }

    /*
     * check if the matching schedule contain meeting data which also include whether protocol submitted to this schedule.
     */
    private boolean canNotDelete(List<CommitteeScheduleBase> schedules, String scheduleId) {
        boolean retVal = false;
        for (CommitteeScheduleBase committeeSchedule : schedules) {
            if (StringUtils.equals(committeeSchedule.getScheduleId(), scheduleId)) {
                retVal = committeeSchedule.hasMeetingData();
            }
        }
        return retVal;
    }

    private CommitteeServiceBase getCommitteeService() {
        return KcServiceLocator.getService(getCommitteeServiceClassHook());
    }
    
    protected abstract Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook();

    
}
