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
package org.kuali.kra.meeting;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * 
 * This class implements the business to check when moving member absent to present voting or present other.
 */
public class MeetingPresentOtherOrVotingRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<MeetingPresentOtherOrVotingEvent> {


    private ErrorReporter errorReporter;

    /**
     * 
     * This method is to validate that the member absent is not an alternate for.
     * This will be called by action 'presentVoting & 'presentOther'
     * @param event
     * @return
     */
public boolean processRules(MeetingPresentOtherOrVotingEvent event) {
        boolean rulePassed = true;
        errorReporter = new ErrorReporter();
        for (MemberPresentBean memberPresentBean : event.getMeetingHelper().getMemberPresentBeans()) {
            if (isAlternateFor(memberPresentBean, event.getMemberAbsentBean())) {
                errorReporter.reportError("meetingHelper.memberAbsentBean.attendance.personId",
                        KeyConstants.ERROR_PRESENT_MEMBER_ABSENT, event.getMemberAbsentBean().getAttendance().getPersonName());
                rulePassed = false;
            }

        }

        return rulePassed;
    }
    
    /*
     * check if the member in absent panel is selected as alternate for already
     */
    private boolean isAlternateFor(MemberPresentBean memberPresentBean, MemberAbsentBean memberAbsentBean) {
        boolean isPresent = false;

        if (StringUtils.isNotBlank(memberPresentBean.getAttendance().getAlternateFor())
                && StringUtils.isNotBlank(memberAbsentBean.getAttendance().getPersonId())
                && memberPresentBean.getAttendance().getAlternateFor().equals(memberAbsentBean.getAttendance().getPersonId())) {
            isPresent = true;
        }
        return isPresent;
    }

}
