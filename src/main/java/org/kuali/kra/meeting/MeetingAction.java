/*
 * Copyright 2006-2008 The Kuali Foundation
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

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.replace;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.kra.logging.BufferedLogger.debug;
import static org.kuali.rice.kns.util.KNSConstants.CONFIRMATION_QUESTION;
import static org.kuali.rice.kns.util.KNSConstants.EMPTY_STRING;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_CLICKED_BUTTON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;

import edu.emory.mathcs.backport.java.util.Collections;

public class MeetingAction extends KualiAction {
    private static final String CLOSE_QUESTION = "Would you like to save meeting data before close it ?";

    private static final String CLOSE_QUESTION_ID = "meeting.close.question";


    /**
     * 
     * This method is for the initial load of meeting page. It is called when 'maintain' button of committee schedule is clicked.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward start(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        getMeetingService().populateFormHelper(form, request);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is called when 'meeting detail' tab is clicked.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward meeting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is to save committee schedule when the 'save' button on meeting detail page is clicked.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        CommitteeSchedule committeeSchedule = ((MeetingForm) form).getMeetingHelper().getCommitteeSchedule();
        if (isValidToSave(committeeSchedule)) {
            getMeetingService().SaveMeetingDetails(committeeSchedule,
                    ((MeetingForm) form).getMeetingHelper().getDeletedOtherActions());
            ((MeetingForm) form).getMeetingHelper().setDeletedOtherActions(new ArrayList<CommScheduleActItem>());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private boolean isValidToSave(CommitteeSchedule committeeSchedule) {

        boolean valie = true;
        GlobalVariables.getMessageMap().addToErrorPath("meetingHelper.committeeSchedule");
        KraServiceLocator.getService(DictionaryValidationService.class).validateBusinessObject(committeeSchedule);
        GlobalVariables.getMessageMap().removeFromErrorPath("meetingHelper.committeeSchedule");
        boolean valid = GlobalVariables.getMessageMap().hasNoErrors();
        MeetingDetailsRule meetingDetailsRule = new MeetingDetailsRule();
        valid &= meetingDetailsRule.validateMeetingDetails(committeeSchedule);
        return valid;

    }

    /**
     * 
     * This method is called when 'Meeting Action' Tab is clicked. It is forwarded to 'meetingAction'
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward meetingAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward("meetingAction");
    }

    /**
     * 
     * This method is for the 'view' button of protocol submitted. It will be forwarded to protocol action page.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewProtocolSubmission(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolSubmission protocolSubmission = ((MeetingForm) form).getMeetingHelper().getCommitteeSchedule()
                .getProtocolSubmissions().get(getLineToDelete(request));
        response.sendRedirect("protocolProtocolActions.do?methodToCall=start&protocolId="
                + protocolSubmission.getProtocol().getProtocolId());
        return null;
    }


    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    private MeetingService getMeetingService() {
        return KraServiceLocator.getService(MeetingService.class);
    }

    private KualiConfigurationService getKualiConfigurationService() {
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }

    /**
     * 
     * This method is for updatetextarea tag. Since meeting management is not a trx doc, so it has to implement this method here.
     * this is copied from KraTransactionalDocumentActionBase
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward kraUpdateTextArea(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {

        // parse out the important strings from our methodToCall parameter
        String fullParameter = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);

        // parse textfieldname:htmlformaction
        String parameterFields = StringUtils.substringBetween(fullParameter, KNSConstants.METHOD_TO_CALL_PARM2_LEFT_DEL,
                KNSConstants.METHOD_TO_CALL_PARM2_RIGHT_DEL);
        debug("fullParameter: ", fullParameter);
        debug("parameterFields: ", parameterFields);
        String[] keyValue = null;
        if (StringUtils.isNotBlank(parameterFields)) {
            String[] textAreaParams = parameterFields.split(KNSConstants.FIELD_CONVERSIONS_SEPARATOR);
            debug("lookupParams: ", textAreaParams);
            for (int i = 0; i < textAreaParams.length; i++) {
                keyValue = textAreaParams[i].split(KNSConstants.FIELD_CONVERSION_PAIR_SEPARATOR);

                debug("keyValue[0]: ", keyValue[0]);
                debug("keyValue[1]: ", keyValue[1]);
            }
        }
        request.setAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_NAME, keyValue[0]);
        request.setAttribute(org.kuali.kra.infrastructure.Constants.HTML_FORM_ACTION, keyValue[1]);
        request.setAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_LABEL, keyValue[2]);
        request.setAttribute(org.kuali.kra.infrastructure.Constants.VIEW_ONLY, keyValue[3]);
        if (form instanceof KualiForm && StringUtils.isNotEmpty(((KualiForm) form).getAnchor())) {
            request.setAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_ANCHOR, ((KualiForm) form).getAnchor());
        }

        return mapping.findForward("kraUpdateTextArea");

    }

    /**
     * 
     * This method to post text area if js is disabled. this is also for updatetextarea tag. this is copied from
     * KraTransactionalDocumentActionBase
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward kraPostTextAreaToParent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        return mapping.findForward("basic");
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
        GlobalVariables.getMessageMap().addToErrorPath("meetingHelper.newOtherAction");
        KraServiceLocator.getService(DictionaryValidationService.class).validateBusinessObject(newCommScheduleActItem);
        GlobalVariables.getMessageMap().removeFromErrorPath("meetingHelper.newOtherAction");
        if (GlobalVariables.getMessageMap().hasNoErrors()) {
            newCommScheduleActItem.refreshReferenceObject("scheduleActItemType");
            newCommScheduleActItem.setScheduleIdFk(((MeetingForm) form).getMeetingHelper().getCommitteeSchedule().getId());
            newCommScheduleActItem.setActionItemNumber(getNextActionItemNumber(form));
            meetingForm.getMeetingHelper().getCommitteeSchedule().getCommScheduleActItems().add(newCommScheduleActItem);
            meetingForm.getMeetingHelper().setNewOtherAction(new CommScheduleActItem());
        }
        return mapping.findForward("basic");
    }

    /**
     * 
     * This method is to remove other action from otehr action list. Also keep in a deleted list, which will be used to call
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
        CommScheduleActItem commScheduleActItem = meetingForm.getMeetingHelper().getCommitteeSchedule().getCommScheduleActItems()
                .get(getLineToDelete(request));
        if (commScheduleActItem.getCommScheduleActItemsId() != null) {
            meetingForm.getMeetingHelper().getDeletedOtherActions().add(commScheduleActItem);
        }
        meetingForm.getMeetingHelper().getCommitteeSchedule().getCommScheduleActItems().remove(getLineToDelete(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * find the max action number and increase by one.
     */
    private Integer getNextActionItemNumber(ActionForm form) {
        Integer nextActionItemNumber = ((MeetingForm) form).getMeetingHelper().getCommitteeSchedule().getCommScheduleActItems()
                .size();
        for (CommScheduleActItem commScheduleActItem : ((MeetingForm) form).getMeetingHelper().getCommitteeSchedule()
                .getCommScheduleActItems()) {
            if (commScheduleActItem.getActionItemNumber() > nextActionItemNumber) {
                nextActionItemNumber = commScheduleActItem.getActionItemNumber();
            }
        }
        return nextActionItemNumber + 1;

    }

    public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        if (question == null) {
            return performQuestionWithoutInput(mapping, form, request, response, CLOSE_QUESTION_ID, CLOSE_QUESTION,
                    KNSConstants.CONFIRMATION_QUESTION, ((MeetingForm) form).getMethodToCall(), "");
        }
        else {
            Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
            if ((CLOSE_QUESTION_ID.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                CommitteeSchedule committeeSchedule = ((MeetingForm) form).getMeetingHelper().getCommitteeSchedule();
                if (isValidToSave(committeeSchedule)) {
                    getMeetingService().SaveMeetingDetails(committeeSchedule,
                            ((MeetingForm) form).getMeetingHelper().getDeletedOtherActions());
                }
                else {
                    return mapping.findForward(Constants.MAPPING_BASIC);

                }
            }
        }

        return mapping.findForward(KNSConstants.MAPPING_PORTAL);
    }

    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward(KNSConstants.MAPPING_PORTAL);
    }

}