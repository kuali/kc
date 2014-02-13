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
package org.kuali.kra.common.committee.meeting;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.kra.common.committee.document.CommitteeDocumentBase;
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
