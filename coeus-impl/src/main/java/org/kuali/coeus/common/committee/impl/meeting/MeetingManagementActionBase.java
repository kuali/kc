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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.Resources;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.committee.impl.meeting.MeetingEventBase.ErrorType;
import org.kuali.coeus.common.committee.impl.service.CommitteeScheduleServiceBase;
import org.kuali.coeus.sys.framework.controller.DocHandlerService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MeetingManagementActionBase extends MeetingActionBase {
    private static final String NEW_OTHER_ACTION_ERROR_PATH = "meetingHelper.newOtherAction";
    
    private static final String DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION="deleteCommitteeScheduleMinute";
    private static final String DELETE_COMMITTEE_OTHER_ACTION_QUESTION="deleteCommitteeScheduleMinute";
    private static final String LINE_NUMBER = "line";

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
        getMeetingService().markAbsent(((MeetingFormBase) form).getMeetingHelper().getMemberPresentBeans(),
                ((MeetingFormBase) form).getMeetingHelper().getMemberAbsentBeans(), getLineToDelete(request));
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
        MeetingFormBase meetingForm = (MeetingFormBase) form;
        MemberAbsentBean memberAbsentBean = meetingForm.getMeetingHelper().getMemberAbsentBeans().get(getLineToDelete(request));
        MeetingHelperBase meetingHelper = ((MeetingFormBase) form).getMeetingHelper();
        if (applyRules(new MeetingPresentOtherOrVotingEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper
                .getCommitteeSchedule().getParentCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()),
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
        MeetingFormBase meetingForm = (MeetingFormBase) form;
        MemberAbsentBean memberAbsentBean = meetingForm.getMeetingHelper().getMemberAbsentBeans().get(getLineToDelete(request));
        MeetingHelperBase meetingHelper = ((MeetingFormBase) form).getMeetingHelper();
        if (applyRules(new MeetingPresentOtherOrVotingEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper
                .getCommitteeSchedule().getParentCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()),
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
            HttpServletResponse response) throws Exception{
        MeetingHelperBase meetingHelper = ((MeetingFormBase) form).getMeetingHelper();
        if (applyRules(new MeetingAddOtherEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper.getCommitteeSchedule()
                .getParentCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()), meetingHelper, ErrorType.HARDERROR))) {
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
        getMeetingService().deleteOtherPresent(((MeetingFormBase) form).getMeetingHelper(), getLineToDelete(request));
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
        
        MeetingFormBase meetingForm = (MeetingFormBase) form;
        MeetingHelperBase meetingHelper = meetingForm.getMeetingHelper();
        CommitteeDocumentBase document 
            = getCommitteeDocument(meetingHelper.getCommitteeSchedule().getParentCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber());
        if (applyRules(getNewMeetingAddMinuteEventInstanceHook(Constants.EMPTY_STRING, document, meetingHelper, ErrorType.HARDERROR))) {
            getMeetingService().addCommitteeScheduleMinute(meetingHelper);
        }
    
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected abstract MeetingAddMinuteEventBase getNewMeetingAddMinuteEventInstanceHook(String errorPathPrefix, CommitteeDocumentBase document, MeetingHelperBase meetingHelper, ErrorType type);

    /**
     * 
     * This method is to remove CommitteeBase Schedule Minute from CommitteeBase Schedule Minute list. Also keep in a deleted list, which
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
        String callerString = String.format("deleteCommitteeScheduleMinute.line%s.anchor%s",getLineToDelete(request),0);
        if (question == null){
            forward =  this.performQuestionWithoutInput(mapping, form, request, response, DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION, Resources.getMessage(request, KeyConstants.QUESTION_COMMITTEE_MANAGEMENT_DELETE_MINUTE_CONFIRMATION), KRADConstants.CONFIRMATION_QUESTION, callerString, "");
         } 
        else if ((DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
            //nothing to do.
        }
        else if ((DELETE_COMMITTEE_SCHEDULE_MINUTE_QUESTION.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
            getMeetingService().deleteCommitteeScheduleMinute(((MeetingFormBase) form).getMeetingHelper().getCommitteeSchedule(),
                    ((MeetingFormBase) form).getMeetingHelper().getDeletedCommitteeScheduleMinutes(), getLineToDelete(request));
        } 
        return forward; 
    }

    /**
     *
     * This method is to move a CommitteeBase Schedule Minute up in the CommitteeBase Schedule Minute list.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward moveupCommitteeScheduleMinute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                                       HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        getMeetingService().moveupCommitteeScheduleMinute(((MeetingFormBase) form).getMeetingHelper().getCommitteeSchedule(), getLineToDelete(request));
        return forward;
    }

    /**
     *
     * This method is to move a CommitteeBase Schedule Minute down in the CommitteeBase Schedule Minute list.
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward movedownCommitteeScheduleMinute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                                       HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        getMeetingService().movedownCommitteeScheduleMinute(((MeetingFormBase) form).getMeetingHelper().getCommitteeSchedule(), getLineToDelete(request));
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
        MeetingFormBase meetingForm = (MeetingFormBase) form;
        CommScheduleActItemBase newCommScheduleActItem = meetingForm.getMeetingHelper().getNewOtherAction();
        validateBusinessObject(newCommScheduleActItem, NEW_OTHER_ACTION_ERROR_PATH);
        if (GlobalVariables.getMessageMap().hasNoErrors()) {
            getMeetingService().addOtherAction(meetingForm.getMeetingHelper().getNewOtherAction(),
                    meetingForm.getMeetingHelper().getCommitteeSchedule());

            meetingForm.getMeetingHelper().setNewOtherAction(getNewCommScheduleActItemInstanceHook());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }


    protected abstract CommScheduleActItemBase getNewCommScheduleActItemInstanceHook();

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
        MeetingFormBase meetingForm = (MeetingFormBase) form;
        MeetingHelperBase meetingHelper = meetingForm.getMeetingHelper();
        int lineToDelete = getLineToDelete(request);
       
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC); 
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        String reason = request.getParameter(KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String callerString = String.format("deleteOtherAction.line%s.anchor%s",lineToDelete,0);
        
        if (applyRules(new MeetingDeleteOtherEvent(Constants.EMPTY_STRING, getCommitteeDocument(meetingHelper.getCommitteeSchedule()
                .getParentCommittee().getCommitteeDocument().getDocumentHeader().getDocumentNumber()), meetingHelper, ErrorType.HARDERROR, lineToDelete))) {
            if (question == null){
                forward =  this.performQuestionWithoutInput(mapping, form, request, response, DELETE_COMMITTEE_OTHER_ACTION_QUESTION, Resources.getMessage(request, KeyConstants.QUESTION_COMMITTEE_MANAGEMENT_DELETE_OTHER_ACTION_CONFIRMATION), KRADConstants.CONFIRMATION_QUESTION, callerString, "");
            } 
            else if ((DELETE_COMMITTEE_OTHER_ACTION_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
                //nothing to do.
            }
            else if ((DELETE_COMMITTEE_OTHER_ACTION_QUESTION.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                getMeetingService().deleteOtherAction(((MeetingFormBase) form).getMeetingHelper().getCommitteeSchedule(),
                        lineToDelete, ((MeetingFormBase) form).getMeetingHelper().getDeletedOtherActions());
            }
        }
       
        return forward; 
    }

    public ActionForward returnToCommittee(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
                
        this.save(mapping, form, request, response);        
        return this.getReturnToCommitteeForward((MeetingFormBase)form);
        
    }

    private ActionForward getReturnToCommitteeForward(MeetingFormBase form) throws WorkflowException {
        assert form != null : "the form is null";
        final DocumentService docService = KcServiceLocator.getService(DocumentService.class);
        final String docNumber = form.getMeetingHelper().getCommitteeSchedule().getParentCommittee().getCommitteeDocument().getDocumentNumber();
        
        final CommitteeDocumentBase pdDoc = (CommitteeDocumentBase) docService.getByDocumentHeaderId(docNumber);
        String forwardUrl = buildForwardUrl(pdDoc.getDocumentHeader().getWorkflowDocument().getDocumentId());

        forwardUrl = forwardUrl.replaceFirst( getCommitteeCommitteeActionIdHook() + ".do", getCommitteeScheduleActionIdHook() + ".do");
        forwardUrl += "&methodToCallAttribute=methodToCall.reload";
        return new ActionForward(forwardUrl, true);
    }

    protected abstract String getCommitteeScheduleActionIdHook();

    protected abstract String getCommitteeCommitteeActionIdHook();
    

    protected String buildForwardUrl(String routeHeaderId) {
        DocHandlerService researchDocumentService = KcServiceLocator.getService(DocHandlerService.class);
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

    /**
     * 
     * This method is to add committee schedule attachments.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward addCommitteeScheduleAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        
        MeetingFormBase meetingForm = (MeetingFormBase) form;
        MeetingHelperBase meetingHelper = meetingForm.getMeetingHelper();
        CommitteeDocumentBase document = getCommitteeDocument(meetingHelper.getCommitteeSchedule().getParentCommittee().
                                        getCommitteeDocument().getDocumentHeader().getDocumentNumber());
        if (applyRules(new MeetingAddAttachmentsEvent(Constants.EMPTY_STRING, document, meetingHelper, ErrorType.HARDERROR))) {
        CommitteeScheduleBase committeSchedule= meetingHelper.getCommitteeSchedule();
        CommitteeScheduleAttachmentsBase  committeScheduleAttachment= meetingHelper.getNewCommitteeScheduleAttachments();
        DateTimeService dateTimeService = ( KcServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME));
        dateTimeService.getCurrentTimestamp();
        committeScheduleAttachment.setUpdateTimestamp(dateTimeService.getCurrentTimestamp());
        committeScheduleAttachment.setNewUpdateTimestamp(dateTimeService.getCurrentTimestamp());
        committeScheduleAttachment.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        committeScheduleAttachment.setNewUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        addScheduleAttachmentsToCommitteeSchedule(meetingForm.getMeetingHelper().getCommitteeSchedule(), committeScheduleAttachment);
        meetingForm.getMeetingHelper().setCommitteeSchedule(committeSchedule);
        meetingHelper.getNewCommitteeScheduleAttachments().getAttachmentsTypeCode();
        meetingForm.getMeetingHelper().setNewCommitteeScheduleAttachments(getNewCommitteeScheduleAttachmentsInstanceHook());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
        
    }
    
    protected abstract CommitteeScheduleAttachmentsBase getNewCommitteeScheduleAttachmentsInstanceHook();
    
    
    public void addScheduleAttachmentsToCommitteeSchedule(CommitteeScheduleBase committeSchedule, CommitteeScheduleAttachmentsBase committeScheduleAttachment)
    {
        committeScheduleAttachment.setCommitteeschedule(committeSchedule) ;
        committeScheduleAttachment.populateAttachment();
        committeSchedule.getCommitteeScheduleAttachments().add(committeScheduleAttachment);
    }
    /**
     * 
     * This method is to download committee schedule attachments.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward downloadCommitteScheduleAttachmentsAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        MeetingFormBase meetingForm = (MeetingFormBase) form;
        MeetingHelperBase meetingHelper = meetingForm.getMeetingHelper();
        CommitteeScheduleBase committeSchedule= meetingHelper.getCommitteeSchedule();
        String line = request.getParameter(LINE_NUMBER);
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        CommitteeScheduleAttachmentsBase  committeScheduleAttachment= meetingHelper.getCommitteeSchedule().getCommitteeScheduleAttachments().get(lineNumber);
        if(committeScheduleAttachment.getDocument()!=null){
            KcServiceLocator.getService(getCommitteeScheduleServiceClassHook()).downloadAttachment(committeScheduleAttachment,response);
        }
        return null;
    }
    
    protected abstract Class<? extends CommitteeScheduleServiceBase> getCommitteeScheduleServiceClassHook();

    /**
     * 
     * This method is to delete committee schedule attachments.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward deleteCommitteeScheduleAttachmentsAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MeetingFormBase meetingForm = (MeetingFormBase) form;
        MeetingHelperBase meetingHelper = meetingForm.getMeetingHelper();
        CommitteeScheduleBase committeSchedule= meetingHelper.getCommitteeSchedule();
        int selectedLineNumber = getSelectedLine(request);
        CommitteeScheduleAttachmentsBase  committeScheduleAttachment = meetingHelper.getCommitteeSchedule().getCommitteeScheduleAttachments().get(selectedLineNumber);
        if(committeScheduleAttachment.getFileName()!=null){
            meetingHelper.getCommitteeSchedule().getCommitteeScheduleAttachments().remove(selectedLineNumber);
            this.getBusinessObjectService().delete(committeScheduleAttachment);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is to replace committee schedule attachments.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward replaceCommitteeScheduleAttachmentsAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        MeetingFormBase meetingForm = (MeetingFormBase) form;
        MeetingHelperBase meetingHelper = meetingForm.getMeetingHelper();
        CommitteeScheduleBase committeSchedule= meetingHelper.getCommitteeSchedule();
        CommitteeScheduleAttachmentsBase  committeScheduleAttachment = meetingHelper.getCommitteeSchedule().getCommitteeScheduleAttachments().get(getSelectedLine(request));
        committeScheduleAttachment.populateAttachment();
        if(committeScheduleAttachment.getAttachmentsTypeCode()!=null){
            getBusinessObjectService().save(committeScheduleAttachment);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    
}
