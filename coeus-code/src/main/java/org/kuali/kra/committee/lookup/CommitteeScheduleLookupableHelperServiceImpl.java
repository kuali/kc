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
