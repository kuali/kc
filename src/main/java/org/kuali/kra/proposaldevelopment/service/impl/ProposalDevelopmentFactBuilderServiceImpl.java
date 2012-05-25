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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.ArgValueLookup;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentFactBuilderService;
import org.kuali.kra.s2s.bo.S2sOppForms;
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
        String documentNumber = getElementValue(docContent,"//documentNumber");
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
        addS2SFacts(factsBuilder,developmentProposal);

        //Activity Type - needs activity type code input
        //allProposals
        //agencyDivisionCodeIsNull
        //nullAgencyProgramCode
        //completeProposalNarratives
        //costElement - cost element code input
        //costElementLimit - cost element code, limit amount *
        //costElementPeriodLimit - cost element code, limit amount *
        //deadlineDate - date
        //grants.gov submission
        //humanExemption e4
        //initialProposalRouting
        //leadUnit - unit number
        //mtdcDeviation
        //multiPi
        //nonFacultyPi
        //ospAdministrator - person ID
        //otherAgencyDeviation
        //piCitizenshipType - type code
        //piHasPiStatus
        //piIsSpecifiedPerson - person ID
        //proposalAwardType - type code
        //proposalCampus - campus code
        //proposalPersonsCertified
        //proposalRoutedToOSP
        //proposalSubmittedByPI
        //proposalType - proposalTypeCode
        //proposalUnit - unitNumber (any proposal unit equals unit number)
        //proposalUnitBelow - unitNumber
        //rolodex person is in proposal - rolodex ID
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
        addMethodFact(factsBuilder, developmentProposal, "hasGGFormIncluded",KcKrmsConstants.ProposalDevelopment.SPECIFIED_GG_FORM);
        
    }
    
    private void addS2SFacts(Builder factsBuilder, DevelopmentProposal developmentProposal) {
        factsBuilder.addFact( KcKrmsConstants.ProposalDevelopment.CFDA_NUMBER, developmentProposal.getCfdaNumber());
        factsBuilder.addFact( KcKrmsConstants.ProposalDevelopment.OPPORTUNITY_ID, developmentProposal.getProgramAnnouncementNumber());
        
    }

    private void addBudgetFacts(Builder factsBuilder, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        Budget budget =  proposalDevelopmentDocument.getFinalBudgetForThisProposal();
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_COST, budget.getTotalCost().bigDecimalValue());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_DIRECT_COST, budget.getTotalDirectCost().bigDecimalValue());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_INDIRECT_COST, budget.getTotalIndirectCost().bigDecimalValue());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.COST_SHARE_AMOUNT, budget.getCostSharingAmount().bigDecimalValue());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.UNDERRECOVERY_AMOUNT, budget.getUnderrecoveryAmount().bigDecimalValue());
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_DIRECT_COST_LIMIT,budget.getTotalDirectCostLimit().bigDecimalValue());

    }

    private void addMethodFact(Builder factsBuilder, DevelopmentProposal developmentProposal, String methodName, String termKey) {
        try {
            Method method = getClass().getMethod(methodName, String.class);
            Object result = method.invoke(this,developmentProposal);
            factsBuilder.addFact(termKey, result);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 
     * This method returns if the s2s forms from arg_value-look_up are included in the given proposal
     * @param proposalNumber
     * @return
     */
    protected String hasGGFormIncluded(DevelopmentProposal developmentProposal){
        List<S2sOppForms> s2sOppForms = developmentProposal.getS2sOppForms();
        List<ArgValueLookup> argLookupValues = getArgValueLookupService().getArgumentValues("GG Form");
        for (S2sOppForms s2sOppForm : s2sOppForms) {
            if(s2sOppForm.getInclude()){
                for (ArgValueLookup argValueLookup : argLookupValues) {
                    if(s2sOppForm.equals(argValueLookup.getValue())){
                        return "true";
                    }
                }
            }
        }
        return "false";
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
