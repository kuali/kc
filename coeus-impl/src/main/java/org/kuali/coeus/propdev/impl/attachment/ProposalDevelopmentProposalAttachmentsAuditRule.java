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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentUtils;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
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
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.*;

public class ProposalDevelopmentProposalAttachmentsAuditRule extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {


    private static final String AUDIT_PARAMETER = ProposalDevelopmentUtils.AUDIT_INCOMPLETE_PROPOSAL_ATTATCHMENTS_PARM;
    private static final String AUDIT_PARAMETER_VALUE_YES = "Y";
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
                    getAuditErrors(ATTACHMENT_PROPOSAL_SECTION_NAME).add(new AuditError(String.format(NARRATIVES_STATUS_KEY,i),
                            KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE, ATTACHMENT_PAGE_ID+"."+ATTACHMENT_PROPOSAL_SECTION_ID));
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
                getAuditErrors(ATTACHMENT_PROPOSAL_SECTION_NAME).add(new AuditError(String.format(NARRATIVES_STATUS_KEY,i),
                        KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE, ATTACHMENT_PAGE_ID+"."+ATTACHMENT_PROPOSAL_SECTION_ID));
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
                    getAuditErrors(NO_SECTION_ID).add(new AuditError(NARRATIVES_KEY,
                            KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_FOUND, ATTACHMENT_PAGE_ID));
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
                    Budget budget = getProposalBudgetService().getFinalBudgetVersion(proposalDevelopmentDocument); 
                    if(budget != null && budget.getBudgetPeriods() != null){
                        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()){                   
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
                                    getAuditErrors(NO_SECTION_ID).add(new AuditError(NARRATIVES_KEY,
                                            KeyConstants.ERROR_PROPOSAL_MENTORINGPLAN_ATTACHMENT_NOT_FOUND, ATTACHMENT_PAGE_ID));
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
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List&lt;AuditError&gt;}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getAuditErrors(String sectionName ) {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        String clusterKey = ATTACHMENT_PAGE_NAME + "." + sectionName;
        if (!GlobalVariables.getAuditErrorMap().containsKey(clusterKey)) {
            GlobalVariables.getAuditErrorMap().put(clusterKey, new AuditCluster(clusterKey, auditErrors, AUDIT_ERRORS));
        }
        else {
            auditErrors = GlobalVariables.getAuditErrorMap().get(clusterKey).getAuditErrorList();
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
