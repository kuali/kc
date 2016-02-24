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
package org.kuali.kra.committee.lookup;

import org.kuali.coeus.common.committee.impl.bo.CommitteeType;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.lookup.CommitteeScheduleLookupableHelperServiceImplBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.authorization.CommitteeScheduleTask;
import org.kuali.kra.infrastructure.TaskGroupName;

/**
 * 
 * This class is to create action links and inquiry url for committeeschedule lookup.
 */
@SuppressWarnings("serial")
public class CommitteeScheduleLookupableHelperServiceImpl extends CommitteeScheduleLookupableHelperServiceImplBase<CommitteeSchedule, Committee, CommitteeTaskBase<Committee>, CommitteeScheduleTask> {

    @Override
    protected Class<Committee> getCommonCommitteeBOClassHook() {
        return Committee.class;
    }

    @Override
    protected Class<CommitteeSchedule> getCommitteeScheduleBOClassHook() {
        return CommitteeSchedule.class;
    }

    @Override
    protected String getCommitteeTypeCodeHook() {
        return CommitteeType.IRB_TYPE_CODE;
    }

    @Override
    protected String getMeetingManagementActionIdHook() {
        return "meetingManagement";
    }

    @Override
    protected CommitteeTaskBase<Committee> getNewCommitteeTaskInstanceHook(String taskName, Committee committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, taskName, committee) {};
    }

    @Override
    protected CommitteeScheduleTask getNewCommitteeScheduleTaskInstanceHook(String taskName, Committee committee, CommitteeSchedule committeeSchedule) {
        return new CommitteeScheduleTask(taskName, committee, committeeSchedule);
    }

    @Override
    protected boolean isCurrentVersion(Committee committee) {
        return super.isCurrentVersion(committee);
    }
}
