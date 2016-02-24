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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * 
 * This class implements the business rule for adding present other attendant.
 */
public class MeetingAddOtherRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<MeetingAddOtherEvent> {

    private static final String NEWOTHERPRESENT_PERSONNAME = "meetingHelper.newOtherPresentBean.attendance.personName";
    private ErrorReporter errorReporter;

    /**
     * 
     * This method is to validate that new person/rolodex is selected, and the selected is not in member present or alternate for.
     * @param event
     * @return
     */
    public boolean processRules(MeetingAddOtherEvent event) {
        boolean rulePassed = true;
        errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        if (StringUtils.isBlank(event.getMeetingHelper().getNewOtherPresentBean().getAttendance().getPersonName())) {
            errorReporter.reportError(NEWOTHERPRESENT_PERSONNAME, KeyConstants.ERROR_EMPTY_PERSON);
            rulePassed = false;
        } else {
            for (MemberPresentBean memberPresentBean : event.getMeetingHelper().getMemberPresentBeans()) {
                if (isMemberPresent(memberPresentBean, event.getMeetingHelper().getNewOtherPresentBean())) {
                    errorReporter.reportError(NEWOTHERPRESENT_PERSONNAME, KeyConstants.ERROR_ADD_MEMBER_PRESENT, event
                            .getMeetingHelper().getNewOtherPresentBean().getAttendance().getPersonName());
                    rulePassed = false;
                }

            }
        }
        return rulePassed;
    }

    /*
     * check if the selected person for 'other present' is a member.
     */
    private boolean isMemberPresent(MemberPresentBean memberPresentBean, OtherPresentBeanBase otherPresentBean) {
        boolean isPresent = false;
        if (memberPresentBean.getAttendance().getNonEmployeeFlag() && otherPresentBean.getAttendance().getNonEmployeeFlag()
                && memberPresentBean.getAttendance().getPersonId().equals(otherPresentBean.getAttendance().getPersonId())) {
            isPresent = true;
        } else if (!memberPresentBean.getAttendance().getNonEmployeeFlag() && !otherPresentBean.getAttendance().getNonEmployeeFlag()
                && memberPresentBean.getAttendance().getPersonId().equals(otherPresentBean.getAttendance().getPersonId())) {
            isPresent = true;
        }  else if (StringUtils.isNotBlank(memberPresentBean.getAttendance().getAlternateFor())
                && StringUtils.isNotBlank(otherPresentBean.getAttendance().getPersonId())
                && memberPresentBean.getAttendance().getAlternateFor().equals(otherPresentBean.getAttendance().getPersonId())) {
            isPresent = true;
        }
        return isPresent;
    }

}
