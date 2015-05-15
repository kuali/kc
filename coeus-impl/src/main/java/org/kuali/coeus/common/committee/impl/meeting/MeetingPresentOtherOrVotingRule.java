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
package org.kuali.coeus.common.committee.impl.meeting;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * 
 * This class implements the business to check when moving member absent to present voting or present other.
 */
public class MeetingPresentOtherOrVotingRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<MeetingPresentOtherOrVotingEvent> {


    private ErrorReporter errorReporter;

    /**
     * 
     * This method is to validate that the member absent is not an alternate for.
     * This will be called by action 'presentVoting &amp; 'presentOther'
     * @param event
     * @return
     */
public boolean processRules(MeetingPresentOtherOrVotingEvent event) {
        boolean rulePassed = true;
        errorReporter = KcServiceLocator.getService(ErrorReporter.class);
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
