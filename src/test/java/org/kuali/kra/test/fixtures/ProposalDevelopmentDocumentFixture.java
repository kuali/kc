/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.test.fixtures;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.kuali.core.service.DocumentService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * Fixture class for creating valid <code>{@link ProposalDevelopmentDocument}</code> instances.
 * 
 * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
 */
public enum ProposalDevelopmentDocumentFixture {
    NORMAL_DOCUMENT();
    
    private ProposalDevelopmentDocument document;
    
    /**
     * Default construction of a <code>{@link ProposalDevelopmentDocument}</code>
     * @throws ParseException 
     * 
     * @throws Exception
     */
    private ProposalDevelopmentDocumentFixture() {
        this("KeyPersonnelAuditRuleTest test", "005889", "Project title", "08/14/2007", "08/21/2007", "1", "1", "000001");
    }
    
    /**
     * 
     * Constructs a KeyPersonnelAuditRuleTest.java.
     * @param description
     * @param sponsorCode
     * @param title
     * @param requestedStartDateInitial
     * @param requestedEndDateInitial
     * @param activityTypeCode
     * @param proposalTypeCode
     * @param ownedByUnit
     * @throws Exception
     */
    private ProposalDevelopmentDocumentFixture(String description, String sponsorCode, String title, String startDateInitial, String endDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        try {
            Date requestedStartDateInitial = new Date(new SimpleDateFormat("MM/dd/yyyy").parse(startDateInitial).getTime());
            Date requestedEndDateInitial = new Date(new SimpleDateFormat("MM/dd/yyyy").parse(endDateInitial).getTime());
            document = (ProposalDevelopmentDocument) getDocumentService().getNewDocument(ProposalDevelopmentDocument.class);
            setRequiredDocumentFields(description, sponsorCode, title, requestedStartDateInitial, requestedEndDateInitial, activityTypeCode, proposalTypeCode, ownedByUnit);
        }
        catch (Exception e) {
            throw new RuntimeException(e);   
        }
    }
    
    /**
     * This method sets required document fields
     * @param document ProposalDevelopmentDocument to set fields for
     * @param description String financialdescription for the document header
     * @param sponsorCode String Sponsor code for the document
     * @param title String title of document
     * @param requestedStartDateInitial String start date
     * @param requestedEndDateInitila String end date
     * @param activityTypeCode String activity type code
     * @param proposalTypeCode String proposal type code
     * @param ownedByUnit String owned by unit
     */
    private void setRequiredDocumentFields(String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        getDocument().getDocumentHeader().setFinancialDocumentDescription(description);
        getDocument().setSponsorCode(sponsorCode);
        getDocument().setTitle(title);
        getDocument().setRequestedStartDateInitial(requestedStartDateInitial);
        getDocument().setRequestedEndDateInitial(requestedEndDateInitial);
        getDocument().setActivityTypeCode(activityTypeCode);
        getDocument().setProposalTypeCode(proposalTypeCode);
        getDocument().setOwnedByUnitNumber(ownedByUnit);
    }
    
    private DocumentService getDocumentService() {  
        return getService(DocumentService.class);
    }

    /**
     * Read method for document
     * 
     * @return ProposalDevelopmentDocument
     */
    public ProposalDevelopmentDocument getDocument() {
        return document;
    }

    /**
     * Write method for document
     * 
     * @param document
     */
    public void setDocument(ProposalDevelopmentDocument document) {
        this.document = document;
    }
}