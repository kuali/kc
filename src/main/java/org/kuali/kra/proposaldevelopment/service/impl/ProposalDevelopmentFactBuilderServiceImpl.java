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

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentFactBuilderService;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.rule.xmlrouting.XPathHelper;
import org.kuali.rice.krms.api.engine.Facts;
import org.w3c.dom.Document;

public class ProposalDevelopmentFactBuilderServiceImpl implements ProposalDevelopmentFactBuilderService {
    
    public void addFacts(Facts.Builder factsBuilder, String docContent) {
        addBigDecimalFact(factsBuilder, docContent, "//totalCost", KcKrmsConstants.ProposalDevelopment.TOTAL_COST);
        addBigDecimalFact(factsBuilder, docContent, "//totalDirectCost", KcKrmsConstants.ProposalDevelopment.TOTAL_DIRECT_COST);
        addBigDecimalFact(factsBuilder, docContent, "//totalIndirectCost", KcKrmsConstants.ProposalDevelopment.TOTAL_INDIRECT_COST);
        addBigDecimalFact(factsBuilder, docContent, "//costShareAmount", KcKrmsConstants.ProposalDevelopment.COST_SHARE_AMOUNT);
        addBigDecimalFact(factsBuilder, docContent, "//underrecoveryAmount", KcKrmsConstants.ProposalDevelopment.UNDERRECOVERY_AMOUNT);
        addBigDecimalFact(factsBuilder, docContent, "//totalCostInitial", KcKrmsConstants.ProposalDevelopment.TOTAL_COST_INITIAL);
        addBigDecimalFact(factsBuilder, docContent, "//totalDirectCostLimit", KcKrmsConstants.ProposalDevelopment.TOTAL_DIRECT_COST_LIMIT);
        addStringFact(factsBuilder, docContent, "//cfdaNumber", KcKrmsConstants.ProposalDevelopment.CFDA_NUMBER);
        addStringFact(factsBuilder, docContent, "//opportunityId", KcKrmsConstants.ProposalDevelopment.OPPORTUNITY_ID);
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
        addStringFact(factsBuilder, docContent, "//proposalNumber", "moduleItemKey");
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
}
