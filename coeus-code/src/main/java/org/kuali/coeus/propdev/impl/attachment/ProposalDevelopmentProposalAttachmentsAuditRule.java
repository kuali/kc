/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentUtils;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.budget.ProposalBudgetService;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

public class ProposalDevelopmentProposalAttachmentsAuditRule extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {

    public static final String AUDIT_CLUSTER_KEY = "proposalAttachmentsAuditWarnings";
    
    private static final String AUDIT_PARAMETER = ProposalDevelopmentUtils.AUDIT_INCOMPLETE_PROPOSAL_ATTATCHMENTS_PARM;
    private static final String AUDIT_PARAMETER_VALUE_YES = "Y";
    private static final String AUDIT_PARAMETER_VALUE_NO = "N";
    private static final String MODULE_STATUS_CODE_INCOMPLETE = "I";

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentProposalAttachmentsAuditRule.class);
    
    private SponsorHierarchyService sponsorHierarchyService;
    private ParameterService parameterService;
    private ProposalBudgetService proposalBudgetService;

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
        //audit for Proposal Attachments to ensure status code is set to complete.
        boolean valid = true;

        Parameter attachmentAuditParam = getParameterService().getParameter(ProposalDevelopmentDocument.class, 
                                                                            AUDIT_PARAMETER);
        if(attachmentAuditParam == null) {
            LOG.warn("System parameter AUDIT_INCOMPLETE_ATTACHMENTS is missing or invalid.");
            return alternateIncompleteAttachmentValidation(developmentProposal);
        }
        String validateIncompleteAttachments = attachmentAuditParam.getValue();

        int i = 0;
        for(Narrative narrative: developmentProposal.getNarratives()) {
            if (StringUtils.equals(narrative.getModuleStatusCode(), MODULE_STATUS_CODE_INCOMPLETE )) {
                if(validateIncompleteAttachments.equals(AUDIT_PARAMETER_VALUE_YES)) {
                    valid &= false;
                    getAuditErrors(Constants.AUDIT_ERRORS).add(new AuditError("document.developmentProposalList[0].narrative[" + i + "].moduleStatusCode", 
                            KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE, Constants.ATTACHMENTS_PAGE));
                }
            }
            i++;
        }
        return valid;
    }
    
    private boolean alternateIncompleteAttachmentValidation(DevelopmentProposal developmentProposal) {
        boolean valid = true;

        int i = 0;
        for(Narrative narrative: developmentProposal.getNarratives()) {
            if (StringUtils.equals(narrative.getModuleStatusCode(), MODULE_STATUS_CODE_INCOMPLETE )) {
                valid &= false;
                getAuditErrors(Constants.AUDIT_ERRORS).add(new AuditError("document.developmentProposalList[0].narrative[" + i + "].moduleStatusCode", 
                        KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE, Constants.ATTACHMENTS_PAGE));
            }
            i++;
        }
        return valid;
    }

    public boolean checkNihRelatedAttachments(DevelopmentProposal developmentProposal) {
        boolean valid = true;
            if(getSponsorHierarchyService().isSponsorNihMultiplePi(developmentProposal.getSponsorCode())
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
                                     &&  StringUtils.equals(narrative.getNarrativeTypeCode(), Constants.PHS_RESTRAININGPLAN_PILEADERSHIPPLAN_ATTACHMENT)
                                     ||  StringUtils.equals(narrative.getNarrativeTypeCode(), Constants.PHS_RESEARCHPLAN_MULTIPLEPILEADERSHIPPLAN)){
                                attachment = false;
                                break;
                             }
                         }
                    }
                }   
                if(attachment && hasPI) {
                    valid=false;
                    getAuditErrors(Constants.AUDIT_ERRORS).add(new AuditError("document.developmentProposalList[0].narrative", 
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
                    BudgetDocument bdDoc = getProposalBudgetService().getFinalBudgetVersion(
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
                                    getAuditErrors(Constants.AUDIT_ERRORS).add(new AuditError("document.developmentProposalList[0].narrative", 
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
    private List<AuditError> getAuditErrors(String auditClusterCategory) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(AUDIT_CLUSTER_KEY)) {
            KNSGlobalVariables.getAuditErrorMap().put(AUDIT_CLUSTER_KEY, 
                    new AuditCluster(Constants.ABSTRACTS_AND_ATTACHMENTS_PANEL, auditErrors, auditClusterCategory));
        } else {
            auditErrors = ((AuditCluster) KNSGlobalVariables.getAuditErrorMap().get(AUDIT_CLUSTER_KEY)).getAuditErrorList();
                }
        
        return auditErrors;
    }
    
    private SponsorHierarchyService getSponsorHierarchyService() {
        if(sponsorHierarchyService == null) {
            sponsorHierarchyService = KcServiceLocator.getService(SponsorHierarchyService.class);
        }
        return sponsorHierarchyService;
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
    protected ProposalBudgetService getProposalBudgetService() {
        if (proposalBudgetService == null )
            proposalBudgetService = KcServiceLocator.getService(ProposalBudgetService.class);
        return proposalBudgetService;
    }

}
