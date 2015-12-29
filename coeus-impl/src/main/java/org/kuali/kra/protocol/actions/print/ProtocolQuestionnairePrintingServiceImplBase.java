/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.protocol.actions.print;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

public abstract class ProtocolQuestionnairePrintingServiceImplBase implements ProtocolQuestionnairePrintingService {

    private ProtocolBase protocol;
    private BusinessObjectService businessObjectService;
    private QuestionnaireAnswerService questionnaireAnswerService;
    
    public void setupQnPrintOption(List<AnswerHeader> answerHeaders, ProtocolBase protocol, List<QuestionnairePrintOption> questionnairesToPrints) {
        setProtocol(protocol);
        int maxSubmissionNumber = getMaxSubmissionNumber();
        for (AnswerHeader answerHeader : answerHeaders) {
                       
            //moduleItemKey is protocol number. When moduleSubItemCode indicates initial protocol as ZERO_MODULE and we have its current qnnr answers
            //ensure those answers can be retrieved from the table by using the original protocol number and not the amend/renew number.
            String moduleItemKey = answerHeader.getModuleItemKey();
            if (CoeusSubModule.ZERO_SUBMODULE.equalsIgnoreCase(answerHeader.getModuleSubItemCode()) && isCurrentRegularQn(answerHeader)) {
                moduleItemKey = answerHeader.getModuleItemKey().substring(0, 10);
            }
            
            // only submission questionnaire and current protocol questionnaire will be printed    
            if ( (CoeusSubModule.PROTOCOL_SUBMISSION.equals(answerHeader.getModuleSubItemCode()) && Integer.parseInt(answerHeader.getModuleSubItemKey()) <= maxSubmissionNumber)
                    || (isCurrentAmendRenewalQn(answerHeader)) ) {
                QuestionnairePrintOption printOption = new QuestionnairePrintOption();
                printOption.setQuestionnaireId(answerHeader.getQuestionnaire().getQuestionnaireRefIdAsLong());
                printOption.setQuestionnaireSeqId(answerHeader.getQuestionnaire().getQuestionnaireSeqIdAsInteger());
                printOption.setSelected(true);
                printOption.setQuestionnaireName(answerHeader.getQuestionnaire().getName());
                printOption.setLabel(getQuestionnaireLabel(answerHeader));
                printOption.setItemKey(moduleItemKey);
                printOption.setSubItemKey(answerHeader.getModuleSubItemKey());
                printOption.setSubItemCode(answerHeader.getModuleSubItemCode());
                // finally check if the answerheader's questionnaire is active, and set it accordingly in the Qnnr print option bean
                printOption.setQuestionnaireActive(isQuestionnaireActive(answerHeader));
                questionnairesToPrints.add(printOption);
            }
        }
        Collections.sort(questionnairesToPrints, new QuestionnairePrintOptionComparator());
    }

    private boolean isQuestionnaireActive(AnswerHeader answerHeader) {        
        Integer questionniareSeqId = answerHeader.getQuestionnaire().getQuestionnaireSeqIdAsInteger();
        String coeusModuleCode = answerHeader.getModuleItemCode();
        String coeusSubModuleCode = answerHeader.getModuleSubItemCode(); 
        return getQuestionnaireAnswerService().checkIfQuestionnaireIsActiveForModule(questionniareSeqId, coeusModuleCode, coeusSubModuleCode);
    }
    
    private int getMaxSubmissionNumber() {
        int maxSubmissionNumber = 0;

        for (Integer submissionNumber : getAvailableSubmissionNumbers()) {
            if (submissionNumber > maxSubmissionNumber) {
                maxSubmissionNumber = submissionNumber;
            }
        }
        return maxSubmissionNumber;
    }

    /*
     * this returns a list of submission numbers for a protocol.
     */
    private List<Integer> getAvailableSubmissionNumbers() {
        List<Integer> submissionNumbers = new ArrayList<Integer>();
        for (ProtocolSubmissionBase submission : getProtocol().getProtocolSubmissions()) {
            submissionNumbers.add(submission.getSubmissionNumber());
        }
        return submissionNumbers;
    }

    /*
     * check if this is the current version of the amend/renewal Qn
     */
    private boolean isCurrentAmendRenewalQn(AnswerHeader answerHeader) {
        boolean isCurrentQn = CoeusSubModule.AMENDMENT_RENEWAL.equals(answerHeader.getModuleSubItemCode())
                              || CoeusSubModule.AMENDMENT.equals(answerHeader.getModuleSubItemCode())
                              || CoeusSubModule.RENEWAL.equals(answerHeader.getModuleSubItemCode())
                              || CoeusSubModule.ZERO_SUBMODULE.equals(answerHeader.getModuleSubItemCode());
        if (isCurrentQn) {
            if (CoeusSubModule.ZERO_SUBMODULE.equals(answerHeader.getModuleSubItemCode())) {
                isCurrentQn = isCurrentRegularQn(answerHeader);
            } else if (CoeusSubModule.AMENDMENT_RENEWAL.equals(answerHeader.getModuleSubItemCode()) 
                       || CoeusSubModule.AMENDMENT.equals(answerHeader.getModuleSubItemCode())
                       || CoeusSubModule.RENEWAL.equals(answerHeader.getModuleSubItemCode())) {
                isCurrentQn = isCurrentAorRQn(answerHeader);
            }
        }
        return isCurrentQn;
    }

    private String getQuestionnaireLabel(AnswerHeader answerHeader) {
        String label = null;
        List<QuestionnaireUsage> usages = answerHeader.getQuestionnaire().getQuestionnaireUsages();
        if (CollectionUtils.isNotEmpty(usages) && usages.size() > 1) {
            Collections.sort((List<QuestionnaireUsage>) usages);
        }
        for (QuestionnaireUsage usage : usages) {
            if (getCoeusModuleCode().equals(usage.getModuleItemCode()) && answerHeader.getModuleSubItemCode().equals(usage.getModuleSubItemCode())) {
                if ("0".equals(answerHeader.getModuleSubItemCode())) {
                    label = usage.getQuestionnaireLabel();
                } else if (CoeusSubModule.PROTOCOL_SUBMISSION.equals(answerHeader.getModuleSubItemCode())) {
                    Map keyValues = new HashMap();
                    keyValues.put("protocolNumber", answerHeader.getModuleItemKey());
                    keyValues.put("submissionNumber", answerHeader.getModuleSubItemKey());
                    
                    List<ProtocolSubmissionBase> submissions = ((List<ProtocolSubmissionBase>) getBusinessObjectService().findMatchingOrderBy(getProtocolSubmissionBOClassHook(), keyValues,
                            "submissionId", false));
                    if (submissions.isEmpty()) {
                        // this may happen if it is undo last action
                        label = usage.getQuestionnaireLabel();
                    } else {
                        keyValues.clear();
                        keyValues.put("protocolId", submissions.get(0).getProtocolId());
                        keyValues.put("submissionNumber", answerHeader.getModuleSubItemKey());
                        
                        ProtocolActionBase protocolAction = ((List<ProtocolActionBase>) getBusinessObjectService().findMatching(getProtocolActionBOClassHook(), keyValues)).get(0);
                        label = usage.getQuestionnaireLabel() + " - " + protocolAction.getProtocolActionType().getDescription()
                                + " - " + protocolAction.getActionDateString();
                    }
                } else if (CoeusSubModule.AMENDMENT_RENEWAL.equals(answerHeader.getModuleSubItemCode())) {
                    if (answerHeader.getModuleItemKey().contains("A")) {
                        label = usage.getQuestionnaireLabel() + " - Amendment " + answerHeader.getModuleItemKey().substring(10);
                    } else {
                        label = usage.getQuestionnaireLabel() + " - Renewal " + answerHeader.getModuleItemKey().substring(10);
                    }
                } else if (CoeusSubModule.AMENDMENT.equals(answerHeader.getModuleSubItemCode())) {
                    if (answerHeader.getModuleItemKey().contains("A")) {
                        label = usage.getQuestionnaireLabel() + " - Amendment " + answerHeader.getModuleItemKey().substring(10);
                    } 
                } else if (CoeusSubModule.RENEWAL.equals(answerHeader.getModuleSubItemCode())) {
                    if (answerHeader.getModuleItemKey().contains("R")) {                        
                        label = usage.getQuestionnaireLabel() + " - Renewal " + answerHeader.getModuleItemKey().substring(10);
                    }                    
                }
            }
        }
        return label;
    }

    protected abstract Class<? extends ProtocolActionBase> getProtocolActionBOClassHook();

    protected abstract Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook();
    
    
    
    /*
     * This is Questionnaire answer is with submodulecode of "0".
     * Then if this protocol is A/R, then it has to check whether this is the first version
     * A/R merged into. 
     */
    private boolean isCurrentRegularQn(AnswerHeader answerHeader) {
        boolean isCurrentQn = false;
        if (!getProtocol().isNew() && !answerHeader.getModuleItemKey().equals(getProtocol().getProtocolNumber())) {
            Map keyValues = new HashMap();
            keyValues.put("protocolNumber", answerHeader.getModuleItemKey());
            ProtocolBase prevProtocol = null;
            // if this is an A/R protocol, then need to find the original protocol that the A/R first merged into.            
            for (ProtocolBase protocol : ((List<ProtocolBase>) getBusinessObjectService().findMatchingOrderBy(getProtocolBOClassHook(), keyValues, "sequenceNumber", true))) {
                isCurrentQn = answerHeader.getModuleSubItemKey().equals(protocol.getSequenceNumber().toString())
                        && !CollectionUtils.isEmpty(getProtocol().getProtocolSubmissions())
                        && isMergedToProtocol(protocol, getProtocol());
                if (isCurrentQn) {
                    if (prevProtocol == null
                            || !isMergedToProtocol(prevProtocol, getProtocol())) {
                        // this is the protocol this A/R merged into. so, use this questionnaire.
                        break;
                    } else {
                        // prevProtocol is the initial ProtocolBase that this A/R merged into.
                        isCurrentQn = false;
                    }

                }
                prevProtocol = protocol;
            }
        } else {
            // if this is a regular protocol, then check if sequencenumber & modulesubitemkey match
            isCurrentQn = answerHeader.getModuleSubItemKey().equals(getProtocol().getSequenceNumber().toString());
        }
        return isCurrentQn;
    }
    
    protected abstract Class<? extends ProtocolBase> getProtocolBOClassHook();
    
    

    private boolean isMergedToProtocol(ProtocolBase protocol, ProtocolBase amendment) {
        boolean merged = false;
        int submissionNumber = amendment.getProtocolSubmissions().get(amendment.getProtocolSubmissions().size() - 1).getSubmissionNumber();
        for (ProtocolSubmissionBase submission : protocol.getProtocolSubmissions()) {
            if (submissionNumber == submission.getSubmissionNumber().intValue()) {
                merged = true;
                break;
            }
        }
        return merged;
    }
    
    /*
     * if questionnaire is associated with Amendment/renewal submodulecode.
     * if this protocol is normal protocol, then it has to check whether the A/R of this
     * questionnaire has merged to this protocol yet.
     */
    private boolean isCurrentAorRQn(AnswerHeader answerHeader) {
        boolean isCurrentQn = false;
        if (!getProtocol().isNew()) {
            // if this is A/R/F, then just match sequencenumber and modulesubitemkey
            isCurrentQn = answerHeader.getModuleSubItemKey().equals(getProtocol().getSequenceNumber().toString());
        } else {
            // if this is a regular protocol, then get this A/R associated this this Qn and see if
            // A/R/F has been merged to this version of protocol
            Map keyValues = new HashMap();
            keyValues.put("protocolNumber", answerHeader.getModuleItemKey());
            
            ProtocolBase protocol = ((List<ProtocolBase>) getBusinessObjectService().findMatchingOrderBy(getProtocolBOClassHook(), keyValues, "sequenceNumber", false)).get(0);

            isCurrentQn = answerHeader.getModuleSubItemKey().equals(protocol.getSequenceNumber().toString())
                    && !CollectionUtils.isEmpty(protocol.getProtocolSubmissions())
                    && isMergedToProtocol(getProtocol(), protocol);
        }
        return isCurrentQn;
    }       
    
    public ProtocolBase getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return questionnaireAnswerService;
    }

    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }
    
    protected abstract String getCoeusModuleCode();

}
