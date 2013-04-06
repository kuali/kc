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
package org.kuali.kra.meeting;

import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.service.CommitteeScheduleService;
import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.common.committee.document.CommitteeDocumentBase;
import org.kuali.kra.common.committee.meeting.CommScheduleActItemBase;
import org.kuali.kra.common.committee.meeting.CommitteeScheduleAttachmentsBase;
import org.kuali.kra.common.committee.meeting.CommonMeetingService;
import org.kuali.kra.common.committee.meeting.MeetingAddMinuteEventBase;
import org.kuali.kra.common.committee.meeting.MeetingEventBase.ErrorType;
import org.kuali.kra.common.committee.meeting.MeetingHelperBase;
import org.kuali.kra.common.committee.meeting.MeetingManagementActionBase;
import org.kuali.kra.common.committee.service.CommitteeScheduleServiceBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;

public class MeetingManagementAction extends MeetingManagementActionBase {

    @Override
    protected MeetingAddMinuteEventBase getNewMeetingAddMinuteEventInstanceHook(String errorPathPrefix, CommitteeDocumentBase document, MeetingHelperBase meetingHelper, ErrorType type) {
        return new MeetingAddMinuteEvent(errorPathPrefix, (CommitteeDocument) document, (MeetingHelper) meetingHelper, type);
    }

    @Override
    protected CommScheduleActItemBase getNewCommScheduleActItemInstanceHook() {
        return new CommScheduleActItem();
    }

    @Override
    protected String getCommitteeScheduleActionIdHook() {
        return "committeeSchedule";
    }

    @Override
    protected String getCommitteeCommitteeActionIdHook() {
        return "committeeCommittee";
    }

    @Override
    protected CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook() {
        return new CommitteeScheduleAttachments();
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook() {
        return CommitteeScheduleService.class;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Class<? extends CommitteeScheduleBase> getCommitteeScheduleBOClass() {
        return CommitteeSchedule.class;
    }

    @Override
    protected String getActionIdHook() {
        return "protocolProtocolActions";
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected CommonMeetingService getMeetingService() {
        return KraServiceLocator.getService(MeetingService.class);
    }

    @Override
    protected org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService<?> getReviewerCommentsService() {
        return KraServiceLocator.getService(ReviewCommentsService.class);
    }
    
// TODO ********************** commented out during IRB backfit ************************    
//    private static final String NEW_OTHER_ACTION_ERROR_PATH = "meetingHelper.newOtherAction";
//    
//    private static final String DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION="deleteCommitteeScheduleMinute";
//    private static final String DELETE_COMMITTEE_OTHER_ACTION_QUESTION="deleteCommitteeScheduleMinute";
//    private static final String LINE_NUMBER = "line";
//
//    /**
//     * 
//     * This method is called when markabsent is clicked.
//     * 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward markAbsent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//        throws Exception {
//        getMeetingService().markAbsent(((MeetingForm) form).getMeetingHelper().getMemberPresentBeans(),
//                ((MeetingForm) form).getMeetingHelper().getMemberAbsentBeans(), getLineToDelete(request));
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//
//    /**
//     * 
//     * This method is to move member absent to member present.
//     * 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward presentVoting(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        MeetingForm meetingForm = (MeetingForm) form;
//        MemberAbsentBean memberAbsentBean = meetingForm.getMeetingHelper().getMemberAbsentBeans().get(getLineToDelete(request));
//        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
//        if (applyRules(new MeetingPresentOtherOrVotingEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper
//                .getCommitteeSchedule().getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()),
//            meetingHelper, memberAbsentBean, ErrorType.HARDERROR))) {
//            getMeetingService().presentVoting(meetingHelper, getLineToDelete(request));
//        }
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//
//    /**
//     * 
//     * This method is to move member absent to other present.
//     * 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward presentOther(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        MeetingForm meetingForm = (MeetingForm) form;
//        MemberAbsentBean memberAbsentBean = meetingForm.getMeetingHelper().getMemberAbsentBeans().get(getLineToDelete(request));
//        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
//        if (applyRules(new MeetingPresentOtherOrVotingEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper
//                .getCommitteeSchedule().getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()),
//            meetingHelper, memberAbsentBean, ErrorType.HARDERROR))) {
//            getMeetingService().presentOther(meetingHelper, getLineToDelete(request));
//        }
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//
//    /**
//     * 
//     * This method is to add the selected person/rolodex to other present.
//     * 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     */
//    public ActionForward addOtherPresent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) {
//        MeetingHelper meetingHelper = ((MeetingForm) form).getMeetingHelper();
//        if (applyRules(new MeetingAddOtherEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper.getCommitteeSchedule()
//                .getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()), meetingHelper, ErrorType.HARDERROR))) {
//            getMeetingService().addOtherPresent(meetingHelper);
//        }
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//
//    /**
//     * 
//     * This method is to remove the selected other present.
//     * 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward deleteOtherPresent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        getMeetingService().deleteOtherPresent(((MeetingForm) form).getMeetingHelper(), getLineToDelete(request));
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//
//
//    /**
//     * 
//     * This method is to add committee schedule minutes.
//     * 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     */
//    public ActionForward addCommitteeScheduleMinute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) {
//        
//        MeetingForm meetingForm = (MeetingForm) form;
//        MeetingHelper meetingHelper = meetingForm.getMeetingHelper();
//        CommitteeDocument document 
//            = getCommitteeDocument(meetingHelper.getCommitteeSchedule().getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber());
//        if (applyRules(new MeetingAddMinuteEvent(Constants.EMPTY_STRING, document, meetingHelper, ErrorType.HARDERROR))) {
//            getMeetingService().addCommitteeScheduleMinute(meetingHelper);
//        }
//    
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//
//    /**
//     * 
//     * This method is to remove Committee Schedule Minute from Committee Schedule Minute list. Also keep in a deleted list, which
//     * will be used to call 'bos.delete' to remove them from DB when save.
//     * 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward deleteCommitteeScheduleMinute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC); 
//        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
//        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
//        String reason = request.getParameter(KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME);
//        String callerString = String.format("deleteCommitteeScheduleMinute.line%s.anchor%s",getLineToDelete(request),0);
//        if (question == null){
//            forward =  this.performQuestionWithoutInput(mapping, form, request, response, DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION, Resources.getMessage(request, KeyConstants.QUESTION_COMMITTEE_MANAGEMENT_DELETE_MINUTE_CONFIRMATION), KRADConstants.CONFIRMATION_QUESTION, callerString, "");
//         } 
//        else if ((DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
//            //nothing to do.
//        }
//        else if ((DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
//            getMeetingService().deleteCommitteeScheduleMinute(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule(),
//                    ((MeetingForm) form).getMeetingHelper().getDeletedCommitteeScheduleMinutes(), getLineToDelete(request));
//        } 
//        return forward; 
//    }
//
//    /*
//     * Utility methods to validate a bo.
//     */
//    private void validateBusinessObject(BusinessObject bo, String errorPath) {
//        GlobalVariables.getMessageMap().addToErrorPath(errorPath);
//        getDictionaryValidationService().validateBusinessObject(bo);
//        GlobalVariables.getMessageMap().removeFromErrorPath(errorPath);
//    }
//
//    /**
//     * 
//     * This method is to add the new other action to other action list. Other action is committee schedule act item.
//     * 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     */
//    public ActionForward addOtherAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) {
//        MeetingForm meetingForm = (MeetingForm) form;
//        CommScheduleActItem newCommScheduleActItem = meetingForm.getMeetingHelper().getNewOtherAction();
//        validateBusinessObject(newCommScheduleActItem, NEW_OTHER_ACTION_ERROR_PATH);
//        if (GlobalVariables.getMessageMap().hasNoErrors()) {
//            getMeetingService().addOtherAction(meetingForm.getMeetingHelper().getNewOtherAction(),
//                    meetingForm.getMeetingHelper().getCommitteeSchedule());
//            meetingForm.getMeetingHelper().setNewOtherAction(new CommScheduleActItem());
//        }
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//
//
//    /**
//     * 
//     * This method is to remove other action from other action list. Also keep in a deleted list, which will be used to call
//     * 'bos.delete' to remove them from DB before save.
//     * 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward deleteOtherAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        MeetingForm meetingForm = (MeetingForm) form;
//        MeetingHelper meetingHelper = meetingForm.getMeetingHelper();
//        int lineToDelete = getLineToDelete(request);
//       
//        
//        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC); 
//        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
//        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
//        String reason = request.getParameter(KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME);
//        String callerString = String.format("deleteOtherAction.line%s.anchor%s",lineToDelete,0);
//        
//        if (applyRules(new MeetingDeleteOtherEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper.getCommitteeSchedule()
//                .getCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()), meetingHelper, ErrorType.HARDERROR, lineToDelete))) {
//            if (question == null){
//                forward =  this.performQuestionWithoutInput(mapping, form, request, response, DELETE_COMMITTEE_OTHER_ACTION_QUESTION, Resources.getMessage(request, KeyConstants.QUESTION_COMMITTEE_MANAGEMENT_DELETE_OTHER_ACTION_CONFIRMATION), KRADConstants.CONFIRMATION_QUESTION, callerString, "");
//            } 
//            else if ((DELETE_COMMITTEE_OTHER_ACTION_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
//                //nothing to do.
//            }
//            else if ((DELETE_COMMITTEE_OTHER_ACTION_QUESTION.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
//                getMeetingService().deleteOtherAction(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule(),
//                        lineToDelete, ((MeetingForm) form).getMeetingHelper().getDeletedOtherActions());
//            }
//        }
//       
//        return forward; 
//    }
//
//    public ActionForward returnToCommittee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//                
//        this.save(mapping, form, request, response);        
//        return this.getReturnToCommitteeForward((MeetingForm)form);
//        
//    }
//
//    private ActionForward getReturnToCommitteeForward(MeetingForm form) throws WorkflowException {
//        assert form != null : "the form is null";
//        final DocumentService docService = KraServiceLocator.getService(DocumentService.class);
//        final String docNumber = form.getMeetingHelper().getCommitteeSchedule().getCommittee().getCommitteeDocument().getDocumentNumber();
//        
//        final CommitteeDocument pdDoc = (CommitteeDocument) docService.getByDocumentHeaderId(docNumber);
//        String forwardUrl = buildForwardUrl(pdDoc.getDocumentHeader().getWorkflowDocument().getDocumentId());
//        forwardUrl = forwardUrl.replaceFirst("committeeCommittee.do", "committeeSchedule.do");
//        forwardUrl += "&methodToCallAttribute=methodToCall.reload";
//        return new ActionForward(forwardUrl, true);
//    }
//
//    protected String buildForwardUrl(String routeHeaderId) {
//        ResearchDocumentService researchDocumentService = KraServiceLocator.getService(ResearchDocumentService.class);
//        String forward = researchDocumentService.getDocHandlerUrl(routeHeaderId);
////        forward = forward.replaceFirst(DEFAULT_TAB, ALTERNATE_OPEN_TAB);
//        if (forward.indexOf("?") == -1) {
//            forward += "?";
//        }
//        else {
//            forward += "&";
//        }
//        forward += KewApiConstants.DOCUMENT_ID_PARAMETER + "=" + routeHeaderId;
//        forward += "&" + KewApiConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
//        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
//            forward += "&" + KewApiConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
//        }
//        return forward;
//    }
//
//    /**
//     * 
//     * This method is to add committee schedule attachments.
//     * 
//     * @param mapping
//     * @param form
//     * @param requesta
//     * @param response
//     * @return
//     */
//    public ActionForward addCommitteeScheduleAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) {
//        
//        MeetingForm meetingForm = (MeetingForm) form;
//        MeetingHelper meetingHelper = meetingForm.getMeetingHelper();
//        CommitteeDocument document = getCommitteeDocument(meetingHelper.getCommitteeSchedule().getCommittee().
//                                        getCommitteeDocument().getDocumentHeader().getDocumentNumber());
//        if (applyRules(new MeetingAddAttachmentsEvent(Constants.EMPTY_STRING, document, meetingHelper, ErrorType.HARDERROR))) {
//        CommitteeSchedule committeSchedule= meetingHelper.getCommitteeSchedule();
//        CommitteeScheduleAttachments  committeScheduleAttachment= meetingHelper.getNewCommitteeScheduleAttachments();
//        DateTimeService dateTimeService = ( KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME));
//        dateTimeService.getCurrentTimestamp();
//        committeScheduleAttachment.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());
//        committeScheduleAttachment.setNewUpdateTimestamp(dateTimeService.getCurrentTimestamp());
//        committeScheduleAttachment.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
//        committeScheduleAttachment.setNewUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
//        addScheduleAttachmentsToCommitteSchedule( meetingForm.getMeetingHelper().getCommitteeSchedule(),committeScheduleAttachment);
//        meetingForm.getMeetingHelper().setCommitteeSchedule(committeSchedule);
//        meetingHelper.getNewCommitteeScheduleAttachments().getAttachmentsTypeCode();
//        meetingForm.getMeetingHelper().setNewCommitteeScheduleAttachments(new CommitteeScheduleAttachments());
//        }
//        return mapping.findForward(Constants.MAPPING_BASIC);
//        
//    }
//    
//    public void addScheduleAttachmentsToCommitteSchedule(CommitteeSchedule committeSchedule,CommitteeScheduleAttachments  committeScheduleAttachment)
//    {
//        committeScheduleAttachment.setCommitteeschedule(committeSchedule) ;
//        committeScheduleAttachment.populateAttachment();
//        committeSchedule.getCommitteeScheduleAttachments().add(committeScheduleAttachment);
//    }
//    /**
//     * 
//     * This method is to download committee schedule attachments.
//     * 
//     * @param mapping
//     * @param form
//     * @param requesta
//     * @param response
//     * @return
//     */
//    public ActionForward downloadCommitteScheduleAttachmentsAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        MeetingForm meetingForm = (MeetingForm) form;
//        MeetingHelper meetingHelper = meetingForm.getMeetingHelper();
//        CommitteeSchedule committeSchedule= meetingHelper.getCommitteeSchedule();
//        String line = request.getParameter(LINE_NUMBER);
//        int lineNumber = line == null ? 0 : Integer.parseInt(line);
//        CommitteeScheduleAttachments  committeScheduleAttachment= meetingHelper.getCommitteeSchedule().getCommitteeScheduleAttachments().get(lineNumber);
//        if(committeScheduleAttachment.getDocument()!=null){
//            KraServiceLocator.getService(CommitteeScheduleService.class).downloadAttachment(committeScheduleAttachment,response);
//        }
//        return null;
//    }
//    
//    /**
//     * 
//     * This method is to delete committee schedule attachments.
//     * 
//     * @param mapping
//     * @param form
//     * @param requesta
//     * @param response
//     * @return
//     */
//    public ActionForward deleteCommitteScheduleAttachmentsAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        MeetingForm meetingForm = (MeetingForm) form;
//        MeetingHelper meetingHelper = meetingForm.getMeetingHelper();
//        CommitteeSchedule committeSchedule= meetingHelper.getCommitteeSchedule();
//        int selectedLineNumber = getSelectedLine(request);
//        CommitteeScheduleAttachments  committeScheduleAttachment = meetingHelper.getCommitteeSchedule().getCommitteeScheduleAttachments().get(selectedLineNumber);
//        if(committeScheduleAttachment.getFileName()!=null){
//            meetingHelper.getCommitteeSchedule().getCommitteeScheduleAttachments().remove(selectedLineNumber);
//            this.getBusinessObjectService().delete(committeScheduleAttachment);
//        }
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
//    
//    /**
//     * 
//     * This method is to replace committee schedule attachments.
//     * 
//     * @param mapping
//     * @param form
//     * @param requesta
//     * @param response
//     * @return
//     */
//    public ActionForward replaceCommitteeScheduleAttachmentsAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        MeetingForm meetingForm = (MeetingForm) form;
//        MeetingHelper meetingHelper = meetingForm.getMeetingHelper();
//        CommitteeSchedule committeSchedule= meetingHelper.getCommitteeSchedule();
//        CommitteeScheduleAttachments  committeScheduleAttachment = meetingHelper.getCommitteeSchedule().getCommitteeScheduleAttachments().get(getSelectedLine(request));
//        committeScheduleAttachment.populateAttachment();
//        if(committeScheduleAttachment.getAttachmentsTypeCode()!=null){
//            getBusinessObjectService().save(committeScheduleAttachment);
//        }
//        return mapping.findForward(Constants.MAPPING_BASIC);
//    }
    
}
