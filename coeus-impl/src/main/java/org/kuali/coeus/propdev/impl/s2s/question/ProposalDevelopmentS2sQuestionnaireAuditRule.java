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
