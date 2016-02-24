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
package org.kuali.kra.iacuc.committee.web.struts.action;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.rule.event.DeleteCommitteeScheduleEventBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeScheduleEventBase.ErrorType;
import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.action.CommitteeScheduleActionBase;
import org.kuali.coeus.common.committee.impl.web.struts.form.schedule.ScheduleData;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.rule.event.IacucDeleteCommitteeScheduleEvent;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeScheduleService;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.rice.krad.document.Document;

import java.util.List;

public class IacucCommitteeScheduleAction extends CommitteeScheduleActionBase {

    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<IacucCommittee>(TaskGroupName.IACUC_COMMITTEE, taskName, (IacucCommittee) committee) {};
    }

    @Override
    protected Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook() {
        return IacucCommitteeScheduleService.class;
    }
    
    @Override
    protected String getCommitteeDocumentTypeSimpleNameHook() {
        return "CommonCommitteeDocument";
    }

    @Override
    protected DeleteCommitteeScheduleEventBase getNewDeleteCommitteeScheduleEventInstanceHook(String errorPathPrefix,
            Document document, ScheduleData scheduleData, List<CommitteeScheduleBase> committeeSchedules, ErrorType type) {
        return new IacucDeleteCommitteeScheduleEvent(errorPathPrefix, document, scheduleData, committeeSchedules, type);
                
    }

    @Override
    protected String getMeetingManagementActionIdHook() {
        return "iacucMeetingManagement";
    }

}
