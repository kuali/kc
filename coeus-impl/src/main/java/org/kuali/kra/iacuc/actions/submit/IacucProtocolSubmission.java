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
package org.kuali.kra.iacuc.actions.submit;

import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionQualifierTypeBase;

/**
 * 
 * This class tracks the data associated with the submission of a protocol for review.
 */
public class IacucProtocolSubmission extends ProtocolSubmissionBase {
    

    private static final long serialVersionUID = 4270551170133689515L;

    @Override
    protected ProtocolSubmissionQualifierTypeBase getNewInstanceProtocolSubmissionQualifierTypeHook() {
        return new IacucProtocolSubmissionQualifierType();
    }
    
    
    public IacucCommittee getIacucCommittee() {
        return (IacucCommittee) super.getCommittee();
    }

    
    public IacucCommitteeSchedule getIacucCommitteeSchedule() {
        return (IacucCommitteeSchedule) super.getCommitteeSchedule();
    }
}
