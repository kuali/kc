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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentFactBuilderService;
import org.kuali.kra.service.ArgValueLookupService;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.rule.xmlrouting.XPathHelper;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.Facts.Builder;
import org.w3c.dom.Document;

public class ProposalDevelopmentFactBuilderServiceImpl implements ProposalDevelopmentFactBuilderService {
    
    private BusinessObjectService businessObjectService;
    private ArgValueLookupService argValueLookupService;
    private DocumentService documentService;;
    
    public ArgValueLookupService getArgValueLookupService() {
        return argValueLookupService;
    }

    public void setArgValueLookupService(ArgValueLookupService argValueLookupService) {
        this.argValueLookupService = argValueLookupService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void addFacts(Facts.Builder factsBuilder, String docContent) {
        String documentNumber = getElementValue(docContent, "//document/documentNumber");
        try {
            ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)getDocumentService().getByDocumentHeaderId(documentNumber);
            addFacts(factsBuilder, proposalDevelopmentDocument);
        }catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void addFacts(Facts.Builder factsBuilder, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        DevelopmentProposal developmentProposal = proposalDevelopmentDocument.getDevelopmentProposal();
        addBudgetFacts(factsBuilder,proposalDevelopmentDocument);
        addProposalFacts(factsBuilder,developmentProposal);
        addS2SFacts(factsBuilder,developmentProposal);
        // parameterized functions with user-input params
        //costElement - cost element code input
        //costElementLimit - cost element code, limit amount *
        //costElementPeriodLimit - cost element code, limit amount *
        
        //s2sAttachmentNarrative
        //s2sBudgetExists
        //s2sCompetitionId - id (free string input)
        //s2sCoverPage
        //s2sEraCommonsName (check if all pis have one)
        //s2sExemptionNumbers
        //s2sFederalId
        //s2sModularBudget
        //s2sPhsLeadershipRole
        //s2sResearchPlanAppendix
        //s2sSubaward
        //specialReviewApprovalDate
        //specialReviewType - type code
        //specified GG form - form type
        //specified narrative type - type code
        //sponsor code - sponsor code
        //sponsorGroup - group code
        //sponsorType - type code
        //valid attachment file name
        
        // Questionnaire Prerequisites
        factsBuilder.addFact("moduleCode", CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE);
        factsBuilder.addFact("moduleItemKey", developmentProposal.getProposalNumber());
        
    }
    
    private void addS2SFacts(Builder factsBuilder, DevelopmentProposal developmentProposal) {
        factsBuilder.addFact( KcKrmsConstants.ProposalDevelopment.CFDA_NUMBER, developmentProposal.getCfdaNumber());
        factsBuilder.addFact( KcKrmsConstants.ProposalDevelopment.OPPORTUNITY_ID, developmentProposal.getProgramAnnouncementNumber());
        
    }

    private void addBudgetFacts(Builder factsBuilder, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        Budget budget =  proposalDevelopmentDocument.getFinalBudgetForThisProposal();
        if (budget != null) {
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_COST, budget.getTotalCost().bigDecimalValue());
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_DIRECT_COST, budget.getTotalDirectCost().bigDecimalValue());
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_INDIRECT_COST, budget.getTotalIndirectCost().bigDecimalValue());
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.COST_SHARE_AMOUNT, budget.getCostSharingAmount().bigDecimalValue());
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.UNDERRECOVERY_AMOUNT, budget.getUnderrecoveryAmount().bigDecimalValue());
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_DIRECT_COST_LIMIT,budget.getTotalDirectCostLimit().bigDecimalValue());
        } else {
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_COST, null);
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_DIRECT_COST, null);
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_INDIRECT_COST, null);
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.COST_SHARE_AMOUNT, null);
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.UNDERRECOVERY_AMOUNT, null);
            factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_DIRECT_COST_LIMIT, null);
        }
    }
    
    private void addProposalFacts(Builder factsBuilder, DevelopmentProposal developmentProposal) {
        
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.DEVELOPMENT_PROPOSAL, developmentProposal);
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.ACTIVITY_TYPE, developmentProposal.getActivityTypeCode());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.DEADLINE_DATE, developmentProposal.getDeadlineDate());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.LEAD_UNIT, developmentProposal.getOwnedByUnitNumber());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.PROPOSAL_TYPE, developmentProposal.getProposalTypeCode());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.ANTICIPATED_AWARD_TYPE, developmentProposal.getAnticipatedAwardTypeCode());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.ALL_PROPOSALS, getAllProposals());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.AGENCY_DIVISION_CODE, developmentProposal.getAgencyDivisionCode());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.AGENCY_PROGRAM_CODE, developmentProposal.getAgencyProgramCode());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.PROPOSAL_NARRATIVES_COMPLETE, isProposalNarrativesComplete(developmentProposal));
        // remaining functions - evaluate and pass in as boolean facts
        //grants.gov submission
        //humanExemption e4
        //initialProposalRouting
        //mtdcDeviation
        //multiPi
        //nonFacultyPi
        //ospAdministrator - person ID
        //otherAgencyDeviation
        //piCitizenshipType - type code
        //piHasPiStatus
        //piIsSpecifiedPerson - person ID
        //proposalCampus - campus code
        //proposalPersonsCertified
        //proposalRoutedToOSP
        //proposalSubmittedByPI
        //proposalUnit - unitNumber (any proposal unit equals unit number)
        //proposalUnitBelow - unitNumber
        //rolodex person is in proposal - rolodex ID
    }
    
    /**
     * Always returns 1 - to enable routing/notification in any case
     * @return
     */
    protected int getAllProposals() {
        return 1;
    }
    
    protected boolean isProposalNarrativesComplete(DevelopmentProposal developmentProposal) {
        for (Narrative narrative : developmentProposal.getNarratives()) {
            if (!"C".equals(narrative.getNarrativeStatus())) {
                return false;
            }
        }
        return true;
    }

    
    protected void addBigDecimalFact(Facts.Builder factsBuilder, String docContent, String xpathExpression, String term) {
        String fact = getElementValue(docContent, xpathExpression);
        if (!StringUtils.isBlank(fact)) {
            BigDecimal factBd = new BigDecimal(fact);
            factsBuilder.addFact(term, factBd);
        } else {
            factsBuilder.addFact(term, null);
        }
    }
    
    protected void addStringFact(Facts.Builder factsBuilder, String docContent, String xpathExpression, String term) {
        String fact = getElementValue(docContent, xpathExpression);
        if (!StringUtils.isBlank(fact)) {
            factsBuilder.addFact(term, fact);
        } else {
            factsBuilder.addFact(term, null);
        }
    }
    
    protected void addDateFact(Facts.Builder factsBuilder, String docContent, String xpathExpression, String term) {
        String fact = getElementValue(docContent, xpathExpression);
        Date date;
        if (!StringUtils.isBlank(fact)) {
            try {
                date = DateUtils.parseDate(fact);
            }
            catch (DateParseException e) {
                // Log error
                factsBuilder.addFact(term, null);
                return;
            }
            factsBuilder.addFact(term, date);
        } else {
            factsBuilder.addFact(term, null);
        }
    }
    
    protected String getElementValue(String docContent, String xpathExpression) {
        try {
            Document document = XmlHelper.trimXml(new ByteArrayInputStream(docContent.getBytes()));

            XPath xpath = XPathHelper.newXPath();
            String value = (String) xpath.evaluate(xpathExpression, document, XPathConstants.STRING);

            return value;

        } catch (Exception e) {
            throw new RiceRuntimeException();
        }
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
