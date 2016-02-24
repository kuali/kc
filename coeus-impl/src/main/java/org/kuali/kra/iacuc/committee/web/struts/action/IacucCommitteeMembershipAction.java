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
