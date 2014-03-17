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
