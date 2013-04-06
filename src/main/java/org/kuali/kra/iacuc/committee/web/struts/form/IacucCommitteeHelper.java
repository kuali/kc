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
package org.kuali.kra.iacuc.committee.web.struts.form;

import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.bo.CommitteeMembershipBase;
import org.kuali.kra.common.committee.bo.CommitteeMembershipExpertiseBase;
import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeScheduleTaskBase;
import org.kuali.kra.common.committee.document.authorization.CommitteeTaskBase;
import org.kuali.kra.common.committee.service.CommitteeScheduleServiceBase;
import org.kuali.kra.common.committee.service.CommitteeServiceBase;
import org.kuali.kra.common.committee.web.struts.form.CommitteeHelperBase;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeMembership;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeMembershipExpertise;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.document.authorization.IacucCommitteeScheduleTask;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeScheduleService;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.kra.infrastructure.TaskGroupName;

public class IacucCommitteeHelper extends CommitteeHelperBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4430069287888114555L;

    public IacucCommitteeHelper(IacucCommitteeForm committeeForm) {
        super(committeeForm);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected CommitteeScheduleTaskBase getNewCommitteeScheduleTaskInstanceHook(String taskName, CommitteeBase committee, CommitteeScheduleBase committeeSchedule) {
        return new IacucCommitteeScheduleTask(taskName, (IacucCommittee) committee, (IacucCommitteeSchedule) committeeSchedule);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<IacucCommittee>(TaskGroupName.IACUC_COMMITTEE, taskName, (IacucCommittee) committee) {};
    }

    @Override
    protected CommitteeMembershipBase getNewCommitteeMembershipInstanceHook() {
        return new IacucCommitteeMembership();
    }

    @Override
    protected Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook() {
        return IacucCommitteeScheduleService.class;
    }

    @Override
    protected Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook() {
        return IacucCommitteeService.class;
    }

    @Override
    protected CommitteeMembershipExpertiseBase getNewCommitteeMembershipExpertiseInstanceHook() {
        return new IacucCommitteeMembershipExpertise();
    }

}
