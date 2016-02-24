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
package org.kuali.kra.protocol.questionnaire;

import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewModuleBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This is Helper class for protocol questionnaire.
 */
public abstract class QuestionnaireHelperBase extends org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase {


    private static final long serialVersionUID = 3436562040789756688L;

    /**
     * Each Helper must contain a reference to its document form so that it can access the actual document.
     */
    private ProtocolFormBase form;

    private String protocolNumber;
    
    // two data items used by Protocol Actions -- > Print --> Questionnaire sub-tab     
    private List<AnswerHeader> printAnswerHeaders; 
    private List<String> printHeaderLabels;
    
    /**
     * 
     * Constructs a QuestionnaireHelperBase.java. To hook up with protocol form.
     * @param form
     */
    public QuestionnaireHelperBase(ProtocolFormBase form) {
        this.form = form;
    }

    /**
     * 
     * This method is to set up things for questionnaire page to be displayed.
     */
    public void prepareView() {
        initializePermissions(getProtocol());
    }

    
    /*
     * authorization check.
     */
    private void initializePermissions(ProtocolBase protocol) {
        ProtocolTaskBase task = getModifyQnnrTaskHook(protocol);
        setAnswerQuestionnaire(getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task));
    }

    protected abstract ProtocolTaskBase getModifyQnnrTaskHook(ProtocolBase protocol);
    
    
    public abstract String getModuleCode();


    public ModuleQuestionnaireBean getModuleQnBean() {
        ProtocolModuleQuestionnaireBeanBase moduleQuestionnaireBean = getProtocolModuleQuestionnaireBeanHook(getProtocol());
        return moduleQuestionnaireBean;

    }
    
    protected abstract ProtocolModuleQuestionnaireBeanBase getProtocolModuleQuestionnaireBeanHook(ProtocolBase protocol);

    
    public void populateAnswers() {
        if (isAmendQuestionnaire()) {
            super.populateAnswers();
            List <AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
            ModuleQuestionnaireBean moduleBean = getAmendModuleBean();
            answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleBean);
            if (!answerHeaders.isEmpty() && answerHeaders.get(0).getId() == null){
                // this is based on usage association, and it is not saved qn answer.
                answerHeaders = new ArrayList<AnswerHeader>();
            }
            if (answerHeaders.isEmpty()) {
                answerHeaders = getAnswerHeadersForCurrentProtocol(moduleBean);
            } 
            if (!answerHeaders.isEmpty()) {
                for (AnswerHeader answerHeader : answerHeaders) {
                    getAnswerHeaders().add(answerHeader);
                }
                resetHeaderLabels();
           }     

        } else {
            super.populateAnswers();
        }
        setQuestionnaireActiveStatuses();
    }

    private ModuleQuestionnaireBean getAmendModuleBean() {
        ModuleQuestionnaireBean moduleBean = getModuleQnBean();
        moduleBean.setModuleSubItemCode(CoeusSubModule.ZERO_SUBMODULE);
        return moduleBean;

    }
    
    /**
     * need to override to take care of "0' modulesubitemcode for amend questionnaire
     * @see org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase#updateQuestionnaireAnswer(int)
     */
    public void updateQuestionnaireAnswer(int answerHeaderIndex) {
        AnswerHeader answerHeader = getAnswerHeaders().get(answerHeaderIndex);
        if (isAmendQuestionnaire() && CoeusSubModule.ZERO_SUBMODULE.equals(answerHeader.getModuleSubItemCode())) {
            ModuleQuestionnaireBean moduleBean = getAmendModuleBean();
            if (UPDATE_WITH_NO_ANSWER_COPY.equals(answerHeader.getUpdateOption())) {
                // no copy
                getAnswerHeaders().remove(answerHeaderIndex);
                getAnswerHeaders().add(answerHeaderIndex,
                        getQuestionnaireAnswerService().getNewVersionAnswerHeader(moduleBean, answerHeader.getQuestionnaire()));
            }
            else {
                AnswerHeader newAnswerHeader = getQuestionnaireAnswerService().getNewVersionAnswerHeader(moduleBean,
                        answerHeader.getQuestionnaire());
                getQuestionnaireAnswerService().copyAnswerToNewVersion(answerHeader, newAnswerHeader);
                getAnswerHeaders().remove(answerHeaderIndex);
                getAnswerHeaders().add(answerHeaderIndex, newAnswerHeader);
            }
            resetHeaderLabels();
        } else {
            super.updateQuestionnaireAnswer(answerHeaderIndex);
        }

    }


    /*
     * Get the current protocol that the amend/renewal is from.  Then get its
     * answerheader for amend questionnaire to use.
     */
    private List <AnswerHeader> getAnswerHeadersForCurrentProtocol(ModuleQuestionnaireBean moduleBean) {
        List <AnswerHeader> answerHeaders;    
        // try to retrieve from original protocol
        ProtocolBase currentProtocol = getProtocolFinder().findCurrentProtocolByNumber(getProtocol().getProtocolNumber().substring(0, 10));
        moduleBean.setModuleItemKey(currentProtocol.getProtocolNumber());
        moduleBean.setModuleSubItemKey(currentProtocol.getSequenceNumber().toString());
        answerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleBean);
        if (!answerHeaders.isEmpty()) {
            ProtocolBase protocol = getProtocol();
            for (AnswerHeader answerHeader : answerHeaders) {
                for (Answer answer : answerHeader.getAnswers()) {
                    answer.setAnswerHeaderId(null);
                    answer.setId(null);
                }
                answerHeader.setId(null);
                answerHeader.setModuleItemKey(protocol.getProtocolNumber());
                answerHeader.setModuleSubItemKey(protocol.getSequenceNumber().toString());
            }
        }
        return answerHeaders;
    }
    
    private ProtocolFinderDao getProtocolFinder() {
        return KcServiceLocator.getService(getProtocolFinderDaoClassHook());
    }
    
    protected abstract Class<? extends ProtocolFinderDao> getProtocolFinderDaoClassHook();
    
    
    private boolean isAmendQuestionnaire() {
        ProtocolBase protocol = getProtocol();
        boolean isAmendQuestionnaire = false;
        if (protocol.isAmendment() || protocol.isRenewal()) {
            for (ProtocolAmendRenewModuleBase module : protocol.getProtocolAmendRenewal().getModules()) {
                if (getProtocolModuleCodeForQuestionnaireHook().equals(module.getProtocolModuleTypeCode())) {
                    isAmendQuestionnaire = true;
                    break;
                }
            }
        }
        return isAmendQuestionnaire;
    }

    protected abstract String getProtocolModuleCodeForQuestionnaireHook();

    private ProtocolBase getProtocol() {
        ProtocolDocumentBase document = form.getProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocumentBase in ProtocolFormBase");
        }
        return document.getProtocol();
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }   
    
    //Determine if protocol is an amendment based solely on the existence of "A" or "R" in the protocol number
    private boolean isAmendmentOrRenewal () {
        boolean isAmendmentOrRenewal = false;
        ProtocolBase protocol = getProtocol();
        if (protocol.isAmendment() || protocol.isRenewal()) {
            isAmendmentOrRenewal = true;
        }
        return isAmendmentOrRenewal;
    }
    
    public List<AnswerHeader> getPrintAnswerHeaders() {
        return printAnswerHeaders;
    }

    public void setPrintAnswerHeaders(List<AnswerHeader> printAnswerHeaders) {
        this.printAnswerHeaders = printAnswerHeaders;
    }

    public List<String> getPrintHeaderLabels() {
        return printHeaderLabels;
    }

    public void setPrintHeaderLabels(List<String> printHeaderLabels) {
        this.printHeaderLabels = printHeaderLabels;
    }
    
    /**
     * set up the tab labels for each questionnaire 
     */
    public void resetPrintHeaderLabels() {
        List<String> labels = new ArrayList<String>();
        for (AnswerHeader printAnswerHeader : printAnswerHeaders) {
            labels.add(getQuestionnaireLabel(printAnswerHeader.getQuestionnaire().getQuestionnaireUsages(), printAnswerHeader.getModuleSubItemCode()));
        }
        setPrintHeaderLabels(labels);     
    }
    
    /**
     * 
     * With this method, get/setup questionnaire answers for Print--> Questionnaire when 'protocol actions' page is clicked.
     */
    public void populatePrintAnswers() {
        if (isAmendmentOrRenewal()) {
            
            setPrintAnswerHeaders(getQuestionnaireAnswerService().getQuestionnaireAnswer(getModuleQnBean()));
            resetPrintHeaderLabels();
            
            List <AnswerHeader> printAnswerHeaders = new ArrayList<AnswerHeader>();
            ModuleQuestionnaireBean moduleBean = getAmendModuleBean();
            printAnswerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(moduleBean);
            if (!printAnswerHeaders.isEmpty() && printAnswerHeaders.get(0).getId() == null){
                // this is based on usage association, and it is not saved qn answer.
                printAnswerHeaders = new ArrayList<AnswerHeader>();
            }
            
            if (printAnswerHeaders.isEmpty()) {
                printAnswerHeaders = getAnswerHeadersForCurrentProtocol(moduleBean);
                
            } 
            
            if (!printAnswerHeaders.isEmpty()) {
                for (AnswerHeader printAnswerHeader : printAnswerHeaders) {
                    getPrintAnswerHeaders().add(printAnswerHeader);
                }
                resetPrintHeaderLabels();
           }     

        } else {
            setPrintAnswerHeaders(getQuestionnaireAnswerService().getQuestionnaireAnswer(getModuleQnBean()));
            resetPrintHeaderLabels();
        }
        setPrintQuestionnaireActiveStatuses();
    }
    
    
    /**
     * This method loops through the current list of answer headers, checking if the questionnaire for each is still active and 
     * sets the status for each answer header accordingly. 
     */
    public void setPrintQuestionnaireActiveStatuses() {
        for (AnswerHeader printAnswerHeader : printAnswerHeaders) {
            if(isQuestionnaireActive(printAnswerHeader)){
                printAnswerHeader.setActiveQuestionnaire(true);
            }
            else{
                printAnswerHeader.setActiveQuestionnaire(false);
            }
        }
    }
    
}
