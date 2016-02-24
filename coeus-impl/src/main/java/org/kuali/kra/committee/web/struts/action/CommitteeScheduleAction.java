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
package org.kuali.kra.committee.web.struts.action;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.rule.event.DeleteCommitteeScheduleEventBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.action.CommitteeScheduleActionBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.rule.event.DeleteCommitteeScheduleEvent;
import org.kuali.kra.committee.service.CommitteeScheduleService;
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
            Document document, ScheduleData scheduleData, List<CommitteeScheduleBase> committeeSchedules, org.kuali.coeus.common.committee.impl.rule.event.CommitteeScheduleEventBase.ErrorType type) {
        
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
