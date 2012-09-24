/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc.committee.web.struts.action;

import java.util.List;

import org.kuali.kra.common.committee.bo.CommitteeMembership;
import org.kuali.kra.common.committee.bo.Committee;
import org.kuali.kra.common.committee.document.authorization.CommitteeTask;
import org.kuali.kra.common.committee.rule.event.CommitteeMemberEventBase.ErrorType;
import org.kuali.kra.common.committee.rule.event.DeleteCommitteeMemberEvent;
import org.kuali.kra.common.committee.rules.CommitteeDocumentRule;
import org.kuali.kra.common.committee.service.CommitteeMembershipServiceBase;
import org.kuali.kra.common.committee.web.struts.action.CommitteeMembershipAction;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeMembership;
import org.kuali.kra.iacuc.committee.rule.event.IacucDeleteCommitteeMemberEvent;
import org.kuali.kra.iacuc.committee.rules.IacucCommitteeDocumentRule;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeMembershipService;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.rice.krad.document.Document;

public class IacucCommitteeMembershipAction extends CommitteeMembershipAction {

    @Override
    protected CommitteeMembership getNewCommitteeMembershipInstanceHook() {
        return new IacucCommitteeMembership();
    }

    @Override
    protected CommitteeDocumentRule getNewCommitteeDocumentRuleInstanceHook() {
        return new IacucCommitteeDocumentRule();
    }

    @Override
    protected CommitteeTask getNewCommitteeTaskInstanceHook(String taskName, Committee committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTask<IacucCommittee>(TaskGroupName.IACUC_COMMITTEE, taskName, (IacucCommittee) committee) {};
    }

    @Override
    protected Class<? extends CommitteeMembershipServiceBase> getCommitteeMembershipServiceClassHook() {
        return IacucCommitteeMembershipService.class;
    }
    
    @Override
    protected String getCommitteeDocumentTypeSimpleNameHook() {
        return "CommonCommitteeDocument";
    }

    @Override
    protected DeleteCommitteeMemberEvent getNewDeleteCommitteeMemberEventInstanceHook(String errorPathPrefix, Document document,
            List<CommitteeMembership> committeeMemberships, ErrorType type) {
        
        return new IacucDeleteCommitteeMemberEvent(errorPathPrefix, document, committeeMemberships,type);
    }

}
