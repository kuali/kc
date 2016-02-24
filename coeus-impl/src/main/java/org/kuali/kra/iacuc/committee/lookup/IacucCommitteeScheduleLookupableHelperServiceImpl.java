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
package org.kuali.kra.iacuc.committee.lookup;

import org.kuali.coeus.common.committee.impl.bo.CommitteeType;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.lookup.CommitteeScheduleLookupableHelperServiceImplBase;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.document.authorization.IacucCommitteeScheduleTask;
import org.kuali.kra.infrastructure.TaskGroupName;

public class IacucCommitteeScheduleLookupableHelperServiceImpl extends CommitteeScheduleLookupableHelperServiceImplBase<IacucCommitteeSchedule, IacucCommittee, CommitteeTaskBase<IacucCommittee>, IacucCommitteeScheduleTask> {


    private static final long serialVersionUID = 2963379561699240982L;

    @Override
    protected Class<IacucCommittee> getCommonCommitteeBOClassHook() {
        return IacucCommittee.class;
    }

    @Override
    protected Class<IacucCommitteeSchedule> getCommitteeScheduleBOClassHook() {
        return IacucCommitteeSchedule.class;
    }

    @Override
    protected String getCommitteeTypeCodeHook() {
        return CommitteeType.IACUC_TYPE_CODE;
    }

    @Override
    protected CommitteeTaskBase<IacucCommittee> getNewCommitteeTaskInstanceHook(String taskName, IacucCommittee committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<IacucCommittee>(TaskGroupName.IACUC_COMMITTEE, taskName, committee) {};
    }

    @Override
    protected IacucCommitteeScheduleTask getNewCommitteeScheduleTaskInstanceHook(String taskName, IacucCommittee committee, IacucCommitteeSchedule committeeSchedule) {
        return new IacucCommitteeScheduleTask(taskName, committee, committeeSchedule);
    }

    @Override
    protected String getMeetingManagementActionIdHook() {
        return "iacucMeetingManagement";
    }

}
