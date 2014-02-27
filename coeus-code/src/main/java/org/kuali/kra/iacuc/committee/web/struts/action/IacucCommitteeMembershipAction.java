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
package org.kuali.kra.iacuc.committee.web.struts.action;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.coeus.common.committee.impl.rule.event.DeleteCommitteeMemberEventBase;
import org.kuali.coeus.common.committee.impl.rule.event.CommitteeMemberEventBase.ErrorType;
import org.kuali.coeus.common.committee.impl.rules.CommitteeDocumentRuleBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeMembershipServiceBase;
import org.kuali.coeus.common.committee.impl.web.struts.action.CommitteeMembershipActionBase;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.iacuc.IacucResearchArea;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeMembership;
import org.kuali.kra.iacuc.committee.rule.event.IacucDeleteCommitteeMemberEvent;
import org.kuali.kra.iacuc.committee.rules.IacucCommitteeDocumentRule;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeMembershipService;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.rice.krad.document.Document;

import java.util.List;

public class IacucCommitteeMembershipAction extends CommitteeMembershipActionBase {

    @Override
    protected CommitteeMembershipBase getNewCommitteeMembershipInstanceHook() {
        return new IacucCommitteeMembership();
    }

    @Override
    protected CommitteeDocumentRuleBase getNewCommitteeDocumentRuleInstanceHook() {
        return new IacucCommitteeDocumentRule();
    }

    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        // creating an anonymous class to avoid task hierarchy issues
        return new CommitteeTaskBase<IacucCommittee>(TaskGroupName.IACUC_COMMITTEE, taskName, (IacucCommittee) committee) {};
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
    protected DeleteCommitteeMemberEventBase getNewDeleteCommitteeMemberEventInstanceHook(String errorPathPrefix, Document document,
            List<CommitteeMembershipBase> committeeMemberships, ErrorType type) {
        
        return new IacucDeleteCommitteeMemberEvent(errorPathPrefix, document, committeeMemberships,type);
    }

    @Override
    protected Class<? extends ResearchAreaBase> getResearchAreaBOClassHook() {
        return IacucResearchArea.class;
    }

}
