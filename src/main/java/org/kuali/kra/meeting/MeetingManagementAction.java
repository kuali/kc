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
import org.apache.struts.validator.Resources;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.meeting.MeetingEventBase.ErrorType;
import org.kuali.kra.service.ResearchDocumentService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

public class MeetingManagementAction extends MeetingAction {
    private static final String NEW_OTHER_ACTION_ERROR_PATH = "meetingHelper.newOtherAction";
    
    private static final String DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION="deleteCommitteeScheduleMinute";
    private static final String DELETE_COMMITTEE_OTHER_ACTION_QUESTION="deleteCommitteeScheduleMinute";


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
        MeetingHelper meetingHelper = meetingForm.getMeetingHelper();
        CommitteeDocument document 
            = getCommitteeDocument(meetingHelper.getCommitteeSchedule().getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber());
        if (applyRules(new MeetingAddMinuteEvent(Constants.EMPTY_STRING, document, meetingHelper, ErrorType.HARDERROR))) {
            getMeetingService().addCommitteeScheduleMinute(meetingHelper);
        }
    
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC); 
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        String reason = request.getParameter(KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String callerString = String.format("deleteCommitteeScheduleMinute.line%s.anchor%s",getLineToDelete(request),0);
        if (question == null){
            forward =  this.performQuestionWithoutInput(mapping, form, request, response, DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION, Resources.getMessage(request, KeyConstants.QUESTION_COMMITTEE_MANAGEMENT_DELETE_MINUTE_CONFIRMATION), KRADConstants.CONFIRMATION_QUESTION, callerString, "");
         } 
        else if ((DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
            //nothing to do.
        }
        else if ((DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
            getMeetingService().deleteCommitteeScheduleMinute(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule(),
                    ((MeetingForm) form).getMeetingHelper().getDeletedCommitteeScheduleMinutes(), getLineToDelete(request));
        } 
        return forward; 
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
       
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC); 
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        String reason = request.getParameter(KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String callerString = String.format("deleteOtherAction.line%s.anchor%s",lineToDelete,0);
        
        if (applyRules(new MeetingDeleteOtherEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper.getCommitteeSchedule()
                .getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()), meetingHelper, ErrorType.HARDERROR, lineToDelete))) {
            if (question == null){
                forward =  this.performQuestionWithoutInput(mapping, form, request, response, DELETE_COMMITTEE_OTHER_ACTION_QUESTION, Resources.getMessage(request, KeyConstants.QUESTION_COMMITTEE_MANAGEMENT_DELETE_OTHER_ACTION_CONFIRMATION), KRADConstants.CONFIRMATION_QUESTION, callerString, "");
            } 
            else if ((DELETE_COMMITTEE_OTHER_ACTION_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
                //nothing to do.
            }
            else if ((DELETE_COMMITTEE_OTHER_ACTION_QUESTION.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                getMeetingService().deleteOtherAction(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule(),
                        lineToDelete, ((MeetingForm) form).getMeetingHelper().getDeletedOtherActions());
            }
        }
       
        return forward; 
    }

    public ActionForward returnToCommittee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
                
        this.save(mapping, form, request, response);        
        return this.getReturnToCommitteeForward((MeetingForm)form);
        
    }

    private ActionForward getReturnToCommitteeForward(MeetingForm form) throws WorkflowException {
        assert form != null : "the form is null";
        final DocumentService docService = KraServiceLocator.getService(DocumentService.class);
        final String docNumber = form.getMeetingHelper().getCommitteeSchedule().getCommittee().getCommitteeDocument().getDocumentNumber();
        
        final CommitteeDocument pdDoc = (CommitteeDocument) docService.getByDocumentHeaderId(docNumber);
        String forwardUrl = buildForwardUrl(pdDoc.getDocumentHeader().getWorkflowDocument().getDocumentId());
        forwardUrl = forwardUrl.replaceFirst("committeeCommittee.do", "committeeSchedule.do");
        forwardUrl += "&methodToCallAttribute=methodToCall.reload";
        return new ActionForward(forwardUrl, true);
    }

    protected String buildForwardUrl(String routeHeaderId) {
        ResearchDocumentService researchDocumentService = KraServiceLocator.getService(ResearchDocumentService.class);
        String forward = researchDocumentService.getDocHandlerUrl(routeHeaderId);
//        forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
        if (forward.indexOf("?") == -1) {
            forward += "?";
        }
        else {
            forward += "&";
        }
        forward += KewApiConstants.DOCUMENT_ID_PARAMETER + "=" + routeHeaderId;
        forward += "&" + KewApiConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            forward += "&" + KewApiConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
        }
        return forward;
    }

}
