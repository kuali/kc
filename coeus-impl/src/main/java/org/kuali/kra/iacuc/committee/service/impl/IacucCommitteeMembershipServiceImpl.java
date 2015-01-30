/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.iacuc.committee.service.impl;

import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipExpertiseBase;
import org.kuali.coeus.common.committee.impl.service.impl.CommitteeMembershipServiceImplBase;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeMembershipExpertise;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeMembershipService;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

public class IacucCommitteeMembershipServiceImpl extends CommitteeMembershipServiceImplBase<IacucCommittee, IacucCommitteeService> implements IacucCommitteeMembershipService{

    @Override
    protected Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook() {
        return IacucProtocolSubmission.class;
    }

    @Override
    protected CommitteeMembershipExpertiseBase getNewCommitteeMembershipExpertiseInstanceHook() {
        return new IacucCommitteeMembershipExpertise();
    }

}
