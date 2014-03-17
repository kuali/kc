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
package org.kuali.kra.irb.questionnaire;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.questionnaire.answer.SaveQuestionnaireAnswerEvent;
import org.kuali.rice.krad.document.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;

/**
 * This class represents the Struts Action for Protocol Questionnaires.
 */
public class ProtocolQuestionnaireAction extends ProtocolAction {
    private static final String MODULE_SUB_ITEM_CODE = "moduleSubItemCode";
    private static final String PROTOCOL_NUMBER      = "protocolNumber";
    private static final String QUESTIONNAIRE_ID     = "questionnaireId";
    private static final String SEQUENCE_NUMBER      = "sequenceNumber";
    private static final String TEMPLATE             = "template";

    
    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.preSave(mapping, form, request, response);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        Document document = protocolForm.getDocument();
        List<AnswerHeader> answerHeaders = protocolForm.getQuestionnaireHelper().getAnswerHeaders();
        
        if ( applyRules(new SaveQuestionnaireAnswerEvent(document, answerHeaders)) && applyRules(new SaveProtocolQuestionnaireEvent(document, answerHeaders)) ) {
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
        return forward;
    }

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.reload(mapping, form, request, response);
        ((ProtocolForm)form).getQuestionnaireHelper().prepareView();
        ((ProtocolForm)form).getQuestionnaireHelper().populateAnswers();
        ((ProtocolForm)form).getQuestionnaireHelper().setQuestionnaireActiveStatuses();
        return actionForward;
    }
    
    
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

    public ActionForward printQuestionnaireAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = mapping.findForward(MAPPING_BASIC);
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        ProtocolForm protocolForm = (ProtocolForm) form;
        final int answerHeaderIndex = this.getSelectedLine(request);
        
        reportParameters.put(QUESTIONNAIRE_ID, ((ProtocolForm) form).getQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex).getQuestionnaire().getQuestionnaireIdAsInteger());
        reportParameters.put(TEMPLATE, ((ProtocolForm) form).getQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex).getQuestionnaire().getTemplate());
        reportParameters.put(MODULE_SUB_ITEM_CODE, ((ProtocolForm) form).getQuestionnaireHelper().getAnswerHeaders().get(answerHeaderIndex).getModuleSubItemCode());

        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(protocolForm.getProtocolDocument().getProtocol(), reportParameters);
        if (dataStream.getContent() != null) {
            streamToResponse(dataStream, response);
            forward = null;
        }
        return forward;
    }

    public ActionForward summaryQuestionnairePop(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward;
        ProtocolForm protocolForm = (ProtocolForm) form;
        String sequenceNumber = request.getParameter("sequenceNumber");
        String protocolNumber = request.getParameter(PROTOCOL_NUMBER);
        
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProtocolModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocolNumber,
            (protocolNumber.contains("A") || protocolNumber.contains("R")) ? CoeusSubModule.AMENDMENT_RENEWAL : CoeusSubModule.ZERO_SUBMODULE, sequenceNumber, true);
        protocolForm.getQuestionnaireHelper().setAnswerHeaders(
                getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean));

        protocolForm.getQuestionnaireHelper().setAnswerHeaders(
                getAnsweredQuestionnaire(protocolForm.getQuestionnaireHelper().getAnswerHeaders()));
        forward = mapping.findForward("viewQuestionnaire");

        protocolForm.getQuestionnaireHelper().resetHeaderLabels();
        protocolForm.getQuestionnaireHelper().setQuestionnaireActiveStatuses();
        return forward;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ActionForward summaryQuestionnaireAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward;
        ProtocolForm protocolForm = (ProtocolForm) form;
        String sequenceNumber = request.getParameter(SEQUENCE_NUMBER);
        String protocolNumber = request.getParameter(PROTOCOL_NUMBER);
        Map keyValues= new HashMap();
        keyValues.put(PROTOCOL_NUMBER, protocolNumber);
        keyValues.put(SEQUENCE_NUMBER, sequenceNumber);
        Protocol protocol = ((List<Protocol>)getBusinessObjectService().findMatching(Protocol.class, keyValues)).get(0);
        String subModuleCode = CoeusSubModule.ZERO_SUBMODULE;
        if (protocol.isRenewal()) {
            subModuleCode = CoeusSubModule.AMENDMENT_RENEWAL;
            if (protocol.isRenewalWithoutAmendment()) {
                subModuleCode = CoeusSubModule.RENEWAL;
            }
        } 
        if (protocol.isAmendment()) {
            subModuleCode = CoeusSubModule.AMENDMENT;
        }
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProtocolModuleQuestionnaireBean(CoeusModule.IRB_MODULE_CODE, protocolNumber,
                                                                                      subModuleCode, sequenceNumber, true);
        // TODO : should handle this more smoothly.  maybe in service, change the fieldvalues map of subitemcode to a list
        // so bos.findmatching will find all the matching codes in the list 
        protocolForm.getQuestionnaireHelper().setAnswerHeaders(
                getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean));
        if (protocol.isAmendment() || protocol.isRenewal()) {  
            // get the questionnaire amended if 'questionnaire' is selected amendment section
            moduleQuestionnaireBean.setModuleSubItemCode(CoeusSubModule.ZERO_SUBMODULE);
            List<AnswerHeader> answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleQuestionnaireBean);
            if (!answerHeaders.isEmpty()) {
                protocolForm.getQuestionnaireHelper().getAnswerHeaders().addAll(answerHeaders);
            }
        }
      // protocolForm.getQuestionnaireHelper().populateAnswers();

        protocolForm.getQuestionnaireHelper().setAnswerHeaders(
                getAnsweredQuestionnaire(protocolForm.getQuestionnaireHelper().getAnswerHeaders()));
        forward = mapping.findForward("ajaxQuestionnaire");

        protocolForm.getQuestionnaireHelper().resetHeaderLabels();        
        protocolForm.getQuestionnaireHelper().setQuestionnaireActiveStatuses();
        
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

    private QuestionnaireAnswerService getQuestionnaireAnswerService() {
            return KcServiceLocator.getService(QuestionnaireAnswerService.class);
    }



}
