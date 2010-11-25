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
package org.kuali.kra.irb.questionnaire;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.questionnaire.answer.SaveQuestionnaireAnswerEvent;
import org.kuali.kra.questionnaire.answer.SaveQuestionnaireAnswerRule;
import org.kuali.kra.questionnaire.print.QuestionnairePrintingService;
import org.kuali.rice.kns.document.Document;

/**
 * This class represents the Struts Action for Protocol Questionnaires.
 */
public class ProtocolQuestionnaireAction extends ProtocolAction {
    private static final String PROTOCOL_NUMBER = "protocolNumber";
    private static final String SUBMISSION_NUMBER = "submissionNumber";
    private static final String SUBMISSION_QUESTIONNAIRE = "submissionQuestionnaire";
    private static final String SUFFIX_T = "T";
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.ProtocolAction#preSave(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     *      javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.preSave(mapping, form, request, response);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        Document document = protocolForm.getDocument();
        List<AnswerHeader> answerHeaders = protocolForm.getQuestionnaireHelper().getAnswerHeaders();
        
        if (applyRules(new SaveQuestionnaireAnswerEvent(document, answerHeaders))) {
            protocolForm.getQuestionnaireHelper().preSave();
            getBusinessObjectService().save(answerHeaders);
        }
    }

    /**
     * 
     * This method is for the 'update' button to update questionnaire answer to new version
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateAnswerToNewVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ((ProtocolForm) form).getQuestionnaireHelper().updateQuestionnaireAnswer(getLineToDelete(request));
        getBusinessObjectService().save(((ProtocolForm) form).getQuestionnaireHelper().getAnswerHeaders().get(getLineToDelete(request)));
        return mapping.findForward(Constants.MAPPING_BASIC);

    }

    /**
     * This is specifically for 'lookup' return a value.
     * @see org.kuali.kra.irb.ProtocolAction#refresh(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward =  super.refresh(mapping, form, request, response);
        if (request.getParameter("refreshCaller") !=null && request.getParameter("refreshCaller").toString().equals("kualiLookupable")) {
            // Lookup field 'onchange' is not working if it is return a value from 'lookup', so do it on server side
            for (Object obj : request.getParameterMap().keySet()) {
                if (StringUtils.indexOf((String) obj, "questionnaireHelper.answerHeaders[") == 0) {
                    ((ProtocolForm) form).getQuestionnaireHelper().updateChildIndicator(Integer.parseInt(StringUtils.substringBetween((String) obj, "questionnaireHelper.answerHeaders[",
                            "].answers[")));
                }
            }
        }
        if (StringUtils.isBlank(((ProtocolForm) form).getDocId())) {
            // lookup return to submission questionnaire popup
            forward = mapping.findForward(SUBMISSION_QUESTIONNAIRE);
            ((ProtocolForm) form).getQuestionnaireHelper().resetHeaderLabels();
        }
        return forward;
    }

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO : reload should reload the action page too, so this submissionquestionnaire may not needed ?
        String submissionActionTypeCode = ((ProtocolForm)form).getQuestionnaireHelper().getSubmissionActionTypeCode();
        ActionForward actionForward = super.reload(mapping, form, request, response);
        ((ProtocolForm)form).getQuestionnaireHelper().prepareView();
        ((ProtocolForm)form).getQuestionnaireHelper().setSubmissionActionTypeCode(submissionActionTypeCode);
        ((ProtocolForm)form).getQuestionnaireHelper().populateAnswers();
        
        return actionForward;
    }
    
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.irb.ProtocolAction#saveOnClose(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     *      javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        Document document = protocolForm.getDocument();
        List<AnswerHeader> answerHeaders = protocolForm.getQuestionnaireHelper().getAnswerHeaders();
        
        if (applyRules(new SaveQuestionnaireAnswerEvent(document, answerHeaders))) {
            protocolForm.getQuestionnaireHelper().preSave();
            getBusinessObjectService().save(answerHeaders);
            
            forward = super.saveOnClose(mapping, form, request, response);
        }
        
        return forward;
    }

    public ActionForward printQuestionnaireAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO : this is only available after questionnaire is saved ?
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        ProtocolForm protocolForm = (ProtocolForm) form;
        final int answerHeaderIndex = this.getSelectedLine(request);
        // TODO : a flag to check whether to print answer or not
        // for release 3 : if questionnaire questions has answer, then print answer. 
        reportParameters.put("questionnaireId", ((ProtocolForm) form).getQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex).getQuestionnaire().getQuestionnaireId());
        reportParameters.put("template", ((ProtocolForm) form).getQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex).getQuestionnaire().getTemplate());

        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(protocolForm.getProtocolDocument().getProtocol(), reportParameters);
        if (dataStream.getContent() != null) {
            streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }

    private QuestionnairePrintingService getQuestionnairePrintingService() {
        return KraServiceLocator.getService(QuestionnairePrintingService.class);
    }

    /**
     * 
     * This method is to edit or view submission questionnaire
     * suffix "T" at the end of protocol# to indicate that it is saved before submission
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward submissionQuestionnairePop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward;
        ProtocolForm protocolForm = (ProtocolForm) form;
        String submissionNumber = request.getParameter(SUBMISSION_NUMBER);
        String protocolNumber = request.getParameter(PROTOCOL_NUMBER);
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocolNumber, CoeusSubModule.PROTOCOL_SUBMISSION, submissionNumber, !protocolNumber.endsWith(SUFFIX_T));
        protocolForm.getQuestionnaireHelper().setAnswerHeaders(
                getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean));
        if (protocolNumber.endsWith(SUFFIX_T)) {
            if (!CollectionUtils.isEmpty(protocolForm.getQuestionnaireHelper().getAnswerHeaders())) {
                protocolForm.getQuestionnaireHelper().setProtocolNumber(protocolNumber);
                protocolForm.getQuestionnaireHelper().setSubmissionNumber(submissionNumber);
            }
            forward = mapping.findForward(SUBMISSION_QUESTIONNAIRE);

        } else {
            protocolForm.getQuestionnaireHelper().setAnswerHeaders(getAnsweredQuestionnaire(protocolForm.getQuestionnaireHelper().getAnswerHeaders()));
            forward =  mapping.findForward("viewQuestionnaire");
        }
        protocolForm.getQuestionnaireHelper().resetHeaderLabels();
        return forward;
    }

    /*
     * to filter out the questionnaire answer not saved
     */
    private List<AnswerHeader> getAnsweredQuestionnaire(List<AnswerHeader> answerHeaders) {
        List<AnswerHeader> savedHeaders = new ArrayList<AnswerHeader>();
        for (AnswerHeader answerHeader : answerHeaders) {
            if (answerHeader.getAnswerHeaderId() != null) {
                savedHeaders.add(answerHeader);
            }
        }
        return savedHeaders;
    }
    
    /**
     * 
     * This method is to save the submission questionnaire answer from the pop up window
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveSubmissionQuestionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        List<AnswerHeader> answerHeaders = protocolForm.getQuestionnaireHelper().getAnswerHeaders();
        SaveQuestionnaireAnswerRule rule = new SaveQuestionnaireAnswerRule();
        if (rule.processRules(new SaveQuestionnaireAnswerEvent(null, answerHeaders))) {
            protocolForm.getQuestionnaireHelper().preSave();
            getBusinessObjectService().save(answerHeaders);
            
        }
        protocolForm.getQuestionnaireHelper().resetHeaderLabels();
        
        return mapping.findForward(SUBMISSION_QUESTIONNAIRE);
    }

    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
            return KraServiceLocator.getService(QuestionnaireAnswerService.class);
    }

    /**
     * 
     * This method is to print submission questionnaire answer
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */                  
    public ActionForward printSubmissionQuestionnaireAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        AnswerHeader answerHeader = getAnswerHeader(request);
        // for release 3 : if questionnaire questions has answer, then print answer.
        reportParameters.put("questionnaireId", answerHeader.getQuestionnaire().getQuestionnaireId());
        reportParameters.put("template", answerHeader.getQuestionnaire().getTemplate());
        reportParameters.put(PROTOCOL_NUMBER, answerHeader.getModuleItemKey());
        reportParameters.put(SUBMISSION_NUMBER, answerHeader.getModuleSubItemKey());

        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(
                getProtocolFinder().findCurrentProtocolByNumber(getProtocolNumber(answerHeader)), reportParameters);
        if (dataStream.getContent() != null) {
            streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }
    
    /*
     * get protocolnumber for answerheader moduleitemkey
     * a saved but not submitted answer has "T" at the end of protocolnumber
     */
    private String getProtocolNumber(AnswerHeader answerHeader) {
        String protocolNumber = answerHeader.getModuleItemKey();
        if (protocolNumber.endsWith(SUFFIX_T)) {
            protocolNumber = protocolNumber.substring(0, protocolNumber.length() - 1);
        }
        return protocolNumber;
    }
    
    /*
     * This is to retrieve answer header based on answerheaderid
     */
    private AnswerHeader getAnswerHeader(HttpServletRequest request) {

        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("answerHeaderId", Integer.toString(this.getSelectedLine(request)));
        return  (AnswerHeader)getBusinessObjectService().findByPrimaryKey(AnswerHeader.class, fieldValues);
    }

    private ProtocolFinderDao getProtocolFinder() {
        return KraServiceLocator.getService(ProtocolFinderDao.class);
    }


}
