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
package org.kuali.kra.committee.web.struts.action;

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.rule.event.DeleteCommitteeScheduleEvent;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeTaskBase;
import org.kuali.kra.common.committee.rule.event.DeleteCommitteeScheduleEventBase;
import org.kuali.kra.common.committee.service.CommitteeScheduleServiceBase;
import org.kuali.kra.common.committee.web.struts.action.CommitteeScheduleActionBase;
import org.kuali.kra.common.committee.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.rice.krad.document.Document;

import java.util.List;

public class CommitteeScheduleAction extends CommitteeScheduleActionBase {
    
    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, taskName, (Committee) committee) {};
    }

    @Override
    protected String getCommitteeDocumentTypeSimpleNameHook() {
        return "CommitteeDocument";
    }

    @Override
    protected DeleteCommitteeScheduleEventBase getNewDeleteCommitteeScheduleEventInstanceHook(String errorPathPrefix,
            Document document, ScheduleData scheduleData, List<CommitteeScheduleBase> committeeSchedules, org.kuali.kra.common.committee.rule.event.CommitteeScheduleEventBase.ErrorType type) {
        
        return new DeleteCommitteeScheduleEvent(errorPathPrefix, document, scheduleData, committeeSchedules, type);
    }

    @Override
    protected Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook() {
        return CommitteeScheduleService.class;
    }

    @Override
    protected String getMeetingManagementActionIdHook() {
        return "meetingManagement";
    }
    
}
