/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.questionnaire.answer.SaveQuestionnaireAnswerEvent;
import org.kuali.kra.questionnaire.print.QuestionnairePrintingService;
import org.kuali.rice.krad.document.Document;

public class ProposalDevelopmentQuestionsAction extends ProposalDevelopmentAction{
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentAction#preSave(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.preSave(mapping, form, request, response);
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        Document document = proposalDevelopmentForm.getDocument();
        
        List<AnswerHeader> answerHeaders = proposalDevelopmentForm.getQuestionnaireHelper().getAnswerHeaders();
        List<AnswerHeader> s2sAnswerHeaders = proposalDevelopmentForm.getS2sQuestionnaireHelper().getAnswerHeaders();
        
        if (applyRules(new SaveQuestionnaireAnswerEvent(document, answerHeaders)) && applyRules(new SaveQuestionnaireAnswerEvent(document,s2sAnswerHeaders,"s2sQuestionnaireHelper"))) {
            proposalDevelopmentForm.getQuestionnaireHelper().preSave();
            proposalDevelopmentForm.getS2sQuestionnaireHelper().preSave();
            getBusinessObjectService().save(answerHeaders);
            getBusinessObjectService().save(s2sAnswerHeaders);
        }
        
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentAction#saveOnClose(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        Document document = proposalDevelopmentForm.getDocument();
        List<AnswerHeader> answerHeaders = proposalDevelopmentForm.getQuestionnaireHelper().getAnswerHeaders();
        List<AnswerHeader> s2sAnswerHeaders = proposalDevelopmentForm.getS2sQuestionnaireHelper().getAnswerHeaders();
        
        if (applyRules(new SaveQuestionnaireAnswerEvent(document, answerHeaders))) {
            proposalDevelopmentForm.getQuestionnaireHelper().preSave();
            proposalDevelopmentForm.getS2sQuestionnaireHelper().preSave();
            getBusinessObjectService().save(answerHeaders);
            getBusinessObjectService().save(s2sAnswerHeaders);
            forward = super.saveOnClose(mapping, form, request, response);
        }
        
        return forward;
    }
    
    //TODO: Fix for multiple modules
    public ActionForward printQuestionnaireAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO : this is only available after questionnaire is saved ?
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        final int answerHeaderIndex = this.getSelectedLine(request);
        final String formProperty = getFormProperty(request,"printQuestionnaireAnswer");
        // TODO : a flag to check whether to print answer or not
        // for release 3 : if questionnaire questions has answer, then print answer. 
       
        if (StringUtils.equals(formProperty, ".questionnaireHelper")) {
            reportParameters.put("questionnaireId", proposalDevelopmentForm.getQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex).getQuestionnaire().getQuestionnaireIdAsInteger());
            reportParameters.put("template", proposalDevelopmentForm.getQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex).getQuestionnaire().getTemplate());
            reportParameters.put("coeusModuleSubItemCode", CoeusSubModule.ZERO_SUBMODULE);
        } else if (StringUtils.equals(formProperty, ".s2sQuestionnaireHelper")) { 
            reportParameters.put("questionnaireId", proposalDevelopmentForm.getS2sQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex).getQuestionnaire().getQuestionnaireIdAsInteger());
            reportParameters.put("template", proposalDevelopmentForm.getS2sQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex).getQuestionnaire().getTemplate());
            reportParameters.put("coeusModuleSubItemCode", CoeusSubModule.PROPOSAL_S2S_SUBMODULE);
        } else {
            throw new RuntimeException(String.format("Do not know how to process printQuestionnaireAnswer for formProperty %s",formProperty));
        }
        
        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(proposalDevelopmentForm.getDocument().getDevelopmentProposal(), reportParameters);
        
        if (dataStream.getContent() != null) {
            streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }

    private QuestionnairePrintingService getQuestionnairePrintingService() {
        return KraServiceLocator.getService(QuestionnairePrintingService.class);
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
    
    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return KraServiceLocator.getService(QuestionnaireAnswerService.class);
    }
    
    /*
     * This is to retrieve answer header based on answerheaderid
     */
    private AnswerHeader getAnswerHeader(HttpServletRequest request) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("answerHeaderId", Integer.toString(this.getSelectedLine(request)));
        return  (AnswerHeader)getBusinessObjectService().findByPrimaryKey(AnswerHeader.class, fieldValues);
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
    
    //TODO: Fix for multiple modules
    public ActionForward updateAnswerToNewVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        final String formProperty = getFormProperty(request,"updateAnswerToNewVersion");
        
        if (StringUtils.equals(formProperty, ".questionnaireHelper")) {
            ((ProposalDevelopmentForm) form).getQuestionnaireHelper().updateQuestionnaireAnswer(getLineToDelete(request));
            getBusinessObjectService().save(((ProposalDevelopmentForm) form).getQuestionnaireHelper().getAnswerHeaders().get(getLineToDelete(request)));
        } else if (StringUtils.equals(formProperty, ".s2sQuestionnaireHelper")) {
           ((ProposalDevelopmentForm) form).getS2sQuestionnaireHelper().updateQuestionnaireAnswer(getLineToDelete(request));
            getBusinessObjectService().save(((ProposalDevelopmentForm) form).getS2sQuestionnaireHelper().getAnswerHeaders().get(getLineToDelete(request))); 
        } else {
            throw new RuntimeException(String.format("Do not know how to process updateAnswerToNewVersion for formProperty %s",formProperty));
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);

    }

    /**
     * This is specifically for 'lookup' return a value.
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#refresh(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    //TODO: Fix for multiple modules
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward =  super.refresh(mapping, form, request, response);
        if (request.getParameter("refreshCaller") !=null && request.getParameter("refreshCaller").toString().equals("kualiLookupable")) {
            // Lookup field 'onchange' is not working if it is return a value from 'lookup', so do it on server side
            for (Object obj : request.getParameterMap().keySet()) {
                if (StringUtils.indexOf((String) obj, "questionnaireHelper.answerHeaders[") == 0) {
                    ((ProposalDevelopmentForm) form).getQuestionnaireHelper().updateChildIndicator(Integer.parseInt(StringUtils.substringBetween((String) obj, "questionnaireHelper.answerHeaders[",
                            "].answers[")));
                } else if (StringUtils.indexOf((String) obj, "s2sQuestionnaireHelper.answerHeaders[") == 0) {
                    ((ProposalDevelopmentForm) form).getS2sQuestionnaireHelper().updateChildIndicator(Integer.parseInt(StringUtils.substringBetween((String) obj, "s2sQuestionnaireHelper.answerHeaders[",
                    "].answers[")));
                }
            }
        }
        return forward;
    }

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO : reload should reload the action page too, so this submissionquestionnaire may not needed ?
        //String submissionActionTypeCode = ((ProposalDevelopmentForm)form).getQuestionnaireHelper().getSubmissionActionTypeCode();
        ActionForward actionForward = super.reload(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm)form;
        proposalDevelopmentForm.getQuestionnaireHelper().prepareView();
        proposalDevelopmentForm.getS2sQuestionnaireHelper().prepareView();
        
        proposalDevelopmentForm.getQuestionnaireHelper().populateAnswers();
        proposalDevelopmentForm.getS2sQuestionnaireHelper().populateAnswers();
        return actionForward;
    }
}
