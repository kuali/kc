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
package org.kuali.coeus.propdev.impl.s2s.question;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;

import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.QUESTIONNAIRE_PAGE_ID;
import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.QUESTIONNAIRE_PAGE_NAME;

public class ProposalDevelopmentS2sQuestionnaireAuditRule extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {

   
    private transient ProposalDevelopmentS2sQuestionnaireService proposalDevelopmentS2sQuestionnaireService;
    
    public boolean processRunAuditBusinessRules(Document document) {

        boolean valid = true;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        S2sOpportunity opp = developmentProposal.getS2sOpportunity();

        if (opp!=null && opp.getS2sOppForms()!=null) {
            List<AnswerHeader> headers = getProposalDevelopmentS2sQuestionnaireService().getProposalS2sAnswerHeaders(developmentProposal);
            for (AnswerHeader header : headers) {
                if (!header.isCompleted()) {
                    valid = false;
                    getAuditErrors(header.getLabel()).add(
                            new AuditError(QUESTIONNAIRE_PAGE_ID + "-" +StringUtils.removePattern(header.getLabel(), "([^0-9a-zA-Z\\-_])"), KeyConstants.ERROR_S2S_QUESTIONNAIRE_NOT_COMPLETE,
                                    QUESTIONNAIRE_PAGE_ID + "." +QUESTIONNAIRE_PAGE_ID+ "-" + StringUtils.removePattern(header.getLabel(),"([^0-9a-zA-Z\\-_])"), new String[] {header.getLabel()}));
                }
            }
        }
        return valid;
    }
    
    private synchronized ProposalDevelopmentS2sQuestionnaireService getProposalDevelopmentS2sQuestionnaireService() {
        if (proposalDevelopmentS2sQuestionnaireService == null) {
            proposalDevelopmentS2sQuestionnaireService = KcServiceLocator.getService(ProposalDevelopmentS2sQuestionnaireService.class);
        }
        return proposalDevelopmentS2sQuestionnaireService;
    }


    private List<AuditError> getAuditErrors(String sectionName) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String clusterKey = QUESTIONNAIRE_PAGE_NAME + "." + sectionName;
        if (!GlobalVariables.getAuditErrorMap().containsKey(clusterKey)) {
            GlobalVariables.getAuditErrorMap().put(clusterKey, new AuditCluster(clusterKey, auditErrors, ProposalDevelopmentDataValidationConstants.AUDIT_ERRORS));
        }
        else {
            auditErrors = GlobalVariables.getAuditErrorMap().get(clusterKey).getAuditErrorList();
        }
        return auditErrors;
    }

    
    

}
