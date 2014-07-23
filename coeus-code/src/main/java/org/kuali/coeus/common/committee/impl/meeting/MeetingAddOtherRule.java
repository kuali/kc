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
