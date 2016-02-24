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
package org.kuali.coeus.common.committee.impl.meeting;

import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class is for the event to move member absent to present voting or present other.
 */
public class MeetingPresentOtherOrVotingEvent extends MeetingEventBase<MeetingPresentOtherOrVotingRule> {
    
    private static final String MSG = "Present other or voting ";
    
    private MemberAbsentBean memberAbsentBean;
    public MeetingPresentOtherOrVotingEvent(String errorPathPrefix, CommitteeDocumentBase document, MeetingHelperBase meetingHelper, ErrorType type) {
        super(MSG + getDocumentId(document), errorPathPrefix, document, meetingHelper, type);
    }
    
    public MeetingPresentOtherOrVotingEvent(String errorPathPrefix, Document document, MeetingHelperBase meetingHelper, MemberAbsentBean memberAbsentBean, ErrorType type) {
        this(errorPathPrefix, (CommitteeDocumentBase)document, meetingHelper, type);
        this.memberAbsentBean = memberAbsentBean;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public KcBusinessRule getRule() {
        return new MeetingPresentOtherOrVotingRule();
    }

    public MemberAbsentBean getMemberAbsentBean() {
        return memberAbsentBean;
    }

    public void setMemberAbsentBean(MemberAbsentBean memberAbsentBean) {
        this.memberAbsentBean = memberAbsentBean;
    }

}
