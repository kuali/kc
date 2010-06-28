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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.meeting.MeetingEventBase.ErrorType;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.util.GlobalVariables;

public class MeetingManagementAction extends MeetingAction {
    private static final String NEW_SCHEDULE_MINUTE_ERROR_PATH = "meetingHelper.newCommitteeScheduleMinute";
    private static final String NEW_OTHER_ACTION_ERROR_PATH = "meetingHelper.newOtherAction";

    /**
     * 
     * This method is called when markabsent is clicked.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward markAbsent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        getMeetingService().markAbsent(((MeetingForm) form).getMeetingHelper().getMemberPresentBeans(),
                ((MeetingForm) form).getMeetingHelper().getMemberAbsentBeans(), getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is to move member absent to member present.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward presentVoting(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        MeetingForm meetingForm = (MeetingForm) form;
        MemberAbsentBean memberAbsentBean = meetingForm.getMeetingHelper().getMemberAbsentBeans().get(getLineToDelete(request));
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
        if (applyRules(new MeetingPresentOtherOrVotingEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper
                .getCommitteeSchedule().getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()),
            meetingHelper, memberAbsentBean, ErrorType.HARDERROR))) {
            getMeetingService().presentVoting(meetingHelper, getLineToDelete(request));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is to move member absent to other present.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward presentOther(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        MeetingForm meetingForm = (MeetingForm) form;
        MemberAbsentBean memberAbsentBean = meetingForm.getMeetingHelper().getMemberAbsentBeans().get(getLineToDelete(request));
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
        if (applyRules(new MeetingPresentOtherOrVotingEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper
                .getCommitteeSchedule().getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()),
            meetingHelper, memberAbsentBean, ErrorType.HARDERROR))) {
            getMeetingService().presentOther(meetingHelper, getLineToDelete(request));
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is to add the selected person/rolodex to other present.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward addOtherPresent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
        if (applyRules(new MeetingAddOtherEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper.getCommitteeSchedule()
                .getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()), meetingHelper, ErrorType.HARDERROR))) {
            getMeetingService().addOtherPresent(meetingHelper);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is to remove the selected other present.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteOtherPresent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        getMeetingService().deleteOtherPresent(((MeetingForm) form).getMeetingHelper(), getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    /**
     * 
     * This method is to add committee schedule minutes.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward addCommitteeScheduleMinute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        MeetingForm meetingForm = (MeetingForm) form;
        CommitteeScheduleMinute newCommitteeScheduleMinute = meetingForm.getMeetingHelper().getNewCommitteeScheduleMinute();
        validateBusinessObject(newCommitteeScheduleMinute, NEW_SCHEDULE_MINUTE_ERROR_PATH);
        boolean valid = GlobalVariables.getMessageMap().hasNoErrors();
        MeetingHelper meetingHelper = meetingForm.getMeetingHelper();
        valid &= applyRules(new MeetingAddMinuteEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper
                .getCommitteeSchedule().getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()),
            meetingHelper, ErrorType.HARDERROR));
        if (valid) {
            getMeetingService().addCommitteeScheduleMinute(meetingHelper);
        }
        return mapping.findForward("basic");
    }

    /**
     * 
     * This method is to remove Committee Schedule Minute from Committee Schedule Minute list. Also keep in a deleted list, which
     * will be used to call 'bos.delete' to remove them from DB when save.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteCommitteeScheduleMinute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        getMeetingService().deleteCommitteeScheduleMinute(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule(),
                ((MeetingForm) form).getMeetingHelper().getDeletedCommitteeScheduleMinutes(), getLineToDelete(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * Utility methods to validate a bo.
     */
    private void validateBusinessObject(BusinessObject bo, String errorPath) {
        GlobalVariables.getMessageMap().addToErrorPath(errorPath);
        getDictionaryValidationService().validateBusinessObject(bo);
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPath);
    }

    /**
     * 
     * This method is to add the new other action to other action list. Other action is committee schedule act item.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward addOtherAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        MeetingForm meetingForm = (MeetingForm) form;
        CommScheduleActItem newCommScheduleActItem = meetingForm.getMeetingHelper().getNewOtherAction();
        validateBusinessObject(newCommScheduleActItem, NEW_OTHER_ACTION_ERROR_PATH);
        if (GlobalVariables.getMessageMap().hasNoErrors()) {
            getMeetingService().addOtherAction(meetingForm.getMeetingHelper().getNewOtherAction(),
                    meetingForm.getMeetingHelper().getCommitteeSchedule());
            meetingForm.getMeetingHelper().setNewOtherAction(new CommScheduleActItem());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    /**
     * 
     * This method is to remove other action from other action list. Also keep in a deleted list, which will be used to call
     * 'bos.delete' to remove them from DB before save.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteOtherAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        MeetingForm meetingForm = (MeetingForm) form;
        MeetingHelper meetingHelper = meetingForm.getMeetingHelper();
        int lineToDelete = getLineToDelete(request);
        if (applyRules(new MeetingDeleteOtherEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper.getCommitteeSchedule()
                .getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()), meetingHelper, ErrorType.HARDERROR, lineToDelete))) {
            getMeetingService().deleteOtherAction(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule(),
                    lineToDelete, ((MeetingForm) form).getMeetingHelper().getDeletedOtherActions());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }



}
