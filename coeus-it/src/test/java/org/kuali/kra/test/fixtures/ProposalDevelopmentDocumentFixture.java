/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.test.fixtures;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.krad.service.DocumentService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

/**
 * Fixture class for creating valid <code>{@link ProposalDevelopmentDocument}</code> instances.
 * 
 * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
 */
public enum ProposalDevelopmentDocumentFixture {
    NORMAL_DOCUMENT();
    
    private String description;
    private String sponsorCode;
    private String title;
    private String startDateInitial;
    private String endDateInitial;
    private String activityTypeCode;
    private String proposalTypeCode;
    private String ownedByUnit;
    
    /**
     * Default construction of a <code>{@link ProposalDevelopmentDocument}</code>
     * @throws ParseException 
     * 
     * @throws Exception
     */
    private ProposalDevelopmentDocumentFixture() {
        this("KeyPersonnelAuditRuleTest test", "005889", "Project title", "08/14/2007", "08/21/2007", "1", "1", "000001");
    }
    
    private ProposalDevelopmentDocumentFixture(String description, String sponsorCode, String title, String startDateInitial, String endDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        this.description      = description;
        this.sponsorCode      = sponsorCode;
        this.title            = title;
        this.startDateInitial = startDateInitial;
        this.endDateInitial   = endDateInitial;
        this.activityTypeCode = activityTypeCode;
        this.proposalTypeCode = proposalTypeCode;
        this.ownedByUnit      = ownedByUnit;
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
    public ProposalDevelopmentDocument getDocument() {
        ProposalDevelopmentDocument retval = null;
        try {
            Date requestedStartDateInitial = new Date(new SimpleDateFormat("MM/dd/yyyy").parse(startDateInitial).getTime());
            Date requestedEndDateInitial = new Date(new SimpleDateFormat("MM/dd/yyyy").parse(endDateInitial).getTime());
            retval = (ProposalDevelopmentDocument) getDocumentService().getNewDocument(ProposalDevelopmentDocument.class);
            setRequiredDocumentFields(retval, description, sponsorCode, title, requestedStartDateInitial, requestedEndDateInitial, activityTypeCode, proposalTypeCode, ownedByUnit);
        }
        catch (Exception e) {
            throw new RuntimeException(e);   
        }
        
        return retval;
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
    private void setRequiredDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.getDocumentHeader().setDocumentDescription(description);
        document.getDevelopmentProposal().setSponsorCode(sponsorCode);
        document.getDevelopmentProposal().setTitle(title);
        document.getDevelopmentProposal().setRequestedStartDateInitial(requestedStartDateInitial);
        document.getDevelopmentProposal().setRequestedEndDateInitial(requestedEndDateInitial);
        document.getDevelopmentProposal().setActivityTypeCode(activityTypeCode);
        document.getDevelopmentProposal().setProposalTypeCode(proposalTypeCode);
        document.getDevelopmentProposal().setOwnedByUnitNumber(ownedByUnit);
        
        for (ProposalSite site : document.getDevelopmentProposal().getProposalSites()) {
            site.setSiteNumber(document.getDocumentNextValue(Constants.PROPOSAL_LOCATION_SEQUENCE_NUMBER));
        }
    }
    
    private DocumentService getDocumentService() {  
        return getService(DocumentService.class);
    }
}
