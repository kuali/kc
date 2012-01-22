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
package org.kuali.kra.proposaldevelopment.rules;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.service.S2SBudgetCalculatorService;
import org.kuali.kra.service.SponsorService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

public class ProposalDevelopmentProposalAttachmentsAuditRule  implements DocumentAuditRule{
    public static final String AUDIT_CLUSTER_KEY = "proposalAttachmentsAuditWarnings";
    
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentProposalAttachmentsAuditRule.class);
    
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) document;
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        
        valid &= checkForIncompleteAttachments(developmentProposal);
        valid &= checkNihRelatedAttachments(developmentProposal);
        valid &= checkNsfRelatedAttachments(proposalDevelopmentDocument);
        
        return valid;
    }

    public boolean checkForIncompleteAttachments(DevelopmentProposal developmentProposal) {
        boolean valid = true;
            int i = 0;
            for(Narrative narrative: developmentProposal.getNarratives()) {
            if (StringUtils.equals(narrative.getModuleStatusCode(), "I")) {
                 valid = false;            
                getAuditErrors().add(new AuditError("document.developmentProposalList[0].narrative[" + i + "].moduleStatusCode", 
                        KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE, Constants.ATTACHMENTS_PAGE));
             }
             i++;
            }
        return valid;
    }
    
    public boolean checkNihRelatedAttachments(DevelopmentProposal developmentProposal) {
        boolean valid = true;
            if(getSponsorService().isSponsorNihMultiplePi(developmentProposal)
                    && developmentProposal.getS2sOpportunity() != null){
                boolean attachment = false;   
                boolean hasPI= false;
                
                for( ProposalPerson proposalPerson: developmentProposal.getInvestigators()){
                if (proposalPerson.getProposalPersonRoleId().equals(Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
                        hasPI=true;
                }
                    if(proposalPerson.isMultiplePi()){  
                        attachment = true;
                       
                        for (Narrative narrative : developmentProposal.getNarratives()) {                                       
                             if(narrative.getNarrativeTypeCode() != null 
                                     &&  Integer.parseInt(narrative.getNarrativeTypeCode()) == Constants.PHS_RESTRAININGPLAN_PILEADERSHIPPLAN_ATTACHMENT
                                     ||  Integer.parseInt(narrative.getNarrativeTypeCode()) == Constants.PHS_RESEARCHPLAN_MULTIPLEPILEADERSHIPPLAN){
                                attachment = false;
                                break;
                             }
                         }
                    }
                }   
                if(attachment && hasPI) {
                    valid=false;
                    getAuditErrors().add(new AuditError("document.developmentProposalList[0].narrative", 
                        KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_FOUND, Constants.ATTACHMENTS_PAGE));
                }
           }
           return valid;
    } 
           
    public boolean checkNsfRelatedAttachments(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        boolean valid = true;
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
            if (  developmentProposal.getSponsorCode().equals(
                        getParameterService().getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE, 
                                        ParameterConstants.ALL_COMPONENT, KeyConstants.NSF_SPONSOR_CODE))
                                        && developmentProposal.getS2sOpportunity() != null) {
                boolean attachmentNotExists = true;            
                try {
                String budgetCostElement = getParameterService().getParameterValueAsString("KC-GEN","A","POST_DOCTORAL_COSTELEMENT");                        
                    BudgetDocument bdDoc = KraServiceLocator.getService(
                            S2SBudgetCalculatorService.class).getFinalBudgetVersion(
                            proposalDevelopmentDocument); 
                    if(bdDoc != null && bdDoc.getBudget() != null 
                            && bdDoc.getBudget().getBudgetPeriods() != null){
                        for (BudgetPeriod budgetPeriod : bdDoc.getBudget().getBudgetPeriods()){                   
                            for (BudgetLineItem budgetLineItem : budgetPeriod.getBudgetLineItems()){
                                if(budgetLineItem.getCostElement().equals(budgetCostElement)){                       
                                    for (Narrative narrative : developmentProposal.getNarratives()) { 
                                        if(narrative.getNarrativeTypeCode() != null 
                                                &&  Integer.parseInt(narrative.getNarrativeTypeCode()) == Constants.MENTORING_PLAN_ATTACHMENT_TYPE_CODE
                                                &&  narrative.getName().equalsIgnoreCase(Constants.MENTORING_PLAN_ATTACHMENT)){                                   
                                            attachmentNotExists=false;
                                            break;
                                        }
                                    } 
                                    if(attachmentNotExists) {
                                        valid=false;
                                    getAuditErrors().add(new AuditError("document.developmentProposalList[0].narrative", 
                                            KeyConstants.ERROR_PROPOSAL_MENTORINGPLAN_ATTACHMENT_NOT_FOUND, Constants.ATTACHMENTS_PAGE));
                                        break;
                                    }
                                }
                            }        
                        }
                    }    
                }catch (Exception e) {
                    LOG.error("Unknown error while validating budget data", e); 
                }
        }   
        return valid;
    }
                
    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(AUDIT_CLUSTER_KEY)) {
            KNSGlobalVariables.getAuditErrorMap().put(AUDIT_CLUSTER_KEY, 
                    new AuditCluster(Constants.ABSTRACTS_AND_ATTACHMENTS_PANEL, auditErrors, Constants.AUDIT_ERRORS));
        } else {
            auditErrors = ((AuditCluster) KNSGlobalVariables.getAuditErrorMap().get(AUDIT_CLUSTER_KEY)).getAuditErrorList();
                }
        
        return auditErrors;
            }
    
    private ParameterService getParameterService() {
        return KraServiceLocator.getService(ParameterService.class);
    }
    private SponsorService getSponsorService() {
        return KraServiceLocator.getService(SponsorService.class);
    }
}
