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
package org.kuali.kra.irb.web;

import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Base class for all integration tests for Protocols.
 */
public class ProtocolSeleniumTestBase extends KcSeleniumTestBase {
    
    private static final String PAGE_TITLE = "Kuali :: KC Protocol";
    
    private static final String CREATE_PROTOCOL_LINK_NAME = "Create Protocol";
    private static final String PROTOCOL_LINK_NAME = "protocol";
    private static final String PERSONNEL_LINK_NAME = "personnel";
    private static final String QUESTIONNAIRE_LINK_NAME = "questionnaire";
    private static final String CUSTOM_DATA_LINK_NAME = "customData";
    private static final String SPECIAL_REVIEW_LINK_NAME = "specialReview";
    private static final String PERMISSIONS_LINK_NAME = "permissions";
    private static final String NOTES_AND_ATTACHMENTS_LINK_NAME = "noteAndAttachment";
    private static final String PROTOCOL_ACTIONS_LINK_NAME = "protocolActions";
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    private static final String PROTOCOL_LIST_PREFIX = "document.protocolList[0].";
    private static final String PROTOCOL_TYPE_CODE_ID = PROTOCOL_LIST_PREFIX + "protocolTypeCode";
    private static final String PROTOCOL_TITLE_ID = PROTOCOL_LIST_PREFIX + "title";
    private static final String PROTOCOL_PRINCIPAL_INVESTIGATOR_ID_ID = "protocolHelper.personId";
    
    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Protocol Document";
    private static final String DEFAULT_PROTOCOL_TYPE_CODE = "1";//test of option "Standard"
    private static final String DEFAULT_PROTOCOL_TITLE = "New protocol test";
    private static final String DEFAULT_PRINCIPAL_INVESTIGATOR_ID = "10000000004";
    
    private static final String PROTOCOL_SUBMISSION_TYPE_CODE_ID = "actionHelper.protocolSubmitAction.submissionTypeCode";
    private static final String PROTOCOL_REVIEW_TYPE_CODE_ID = "actionHelper.protocolSubmitAction.protocolReviewTypeCode";
    
    private static final String DEFAULT_PROTOCOL_SUBMISSION_TYPE = "Initial Protocol Application for Approval";
    private static final String DEFAULT_PROTOCOL_SUBMISSION_REVIEW_TYPE = "Full";
    
    /**
     * Creates a new instance of the Protocol page, filling in all required values, and saving.
     */
    protected final void createProtocol() {
        clickResearcherTab();
        
        click(CREATE_PROTOCOL_LINK_NAME);
        assertTitleContains(PAGE_TITLE);
        
        setDefaultRequiredFields();
        
        saveDocument();
        assertSave();
    }
    
    
    /**
     * Submits an already created Protocol and returns to the Protocol Action page.
     */
    protected final void submitProtocol() throws Exception {
        clickProtocolActionsPage();
        
        setSubmissionRequiredFields();
        
        click("methodToCall.submitForReview");
        assertSubmit();
    }

    /**
     * Sets the Protocol's required fields to legal default values.
     */
    protected void setDefaultRequiredFields(){
        set(DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        set(PROTOCOL_TYPE_CODE_ID, DEFAULT_PROTOCOL_TYPE_CODE);
        set(PROTOCOL_TITLE_ID, DEFAULT_PROTOCOL_TITLE);
        lookup(PROTOCOL_PRINCIPAL_INVESTIGATOR_ID_ID, "personId", DEFAULT_PRINCIPAL_INVESTIGATOR_ID);
    }
    
    /**
     * Sets the Protocol's submission fields to legal default values.
     */
    protected void setSubmissionRequiredFields() {
        clickExpandAll();
        
        set(PROTOCOL_SUBMISSION_TYPE_CODE_ID, DEFAULT_PROTOCOL_SUBMISSION_TYPE);
        set(PROTOCOL_REVIEW_TYPE_CODE_ID, DEFAULT_PROTOCOL_SUBMISSION_REVIEW_TYPE);
    }
    
    /**
     * Navigate to the Protocol Protocol page.
     */
    protected void clickProtocolProtocolPage() {
        click(PROTOCOL_LINK_NAME);
    }
    
    /**
     * Navigate to the Protocol Personnel page.
     */
    protected void clickProtocolPersonnelPage() {
        click(PERSONNEL_LINK_NAME);
    }
    
    /**
     * Navigate to the Protocol Questionnaire page.
     */
    protected void clickProtocolQuestionnairePage() {
        click(QUESTIONNAIRE_LINK_NAME);
    }
    
    /**
     * Navigate to the Protocol Custom Data page.
     */
    protected void clickProtocolCustomDataPage() {
        click(CUSTOM_DATA_LINK_NAME);
    }
    
    /**
     * Navigate to the Protocol Special Review page.
     */
    protected void clickProtocolSpecialReviewPage() {
        click(SPECIAL_REVIEW_LINK_NAME);
    }
    
    /**
     * Navigate to the Protocol Permissions page.
     */
    protected void clickProtocolPermissionsPage() {
        click(PERMISSIONS_LINK_NAME);
    }
    
    /**
     * Navigate to the Protocol Notes & Attachments page.
     */
    protected void clickProtocolNotesAndAttachmentsPage() {
        click(NOTES_AND_ATTACHMENTS_LINK_NAME);
    }
    
    /**
     * Navigate to the Protocol Actions page.
     */
    protected void clickProtocolActionsPage() {
        click(PROTOCOL_ACTIONS_LINK_NAME);
    }
    
    protected void lookupResearchArea(String researchAreaCode) {
        multiLookup("protocolResearchAreas", "researchAreaCode", researchAreaCode);
    }
    
    protected void lookupOrganization(String organizationId) {
        lookup("protocolHelper.newProtocolLocation.organizationId", "organizationId", organizationId);
    }
    
    protected void lookupFundingSource(String fundingSourceTypeCode, String fundingSourceIdField, String fundingSourceIdValue) {
        set("protocolHelper.newFundingSource.fundingSourceTypeCode", "Unit");
        
        lookup("methodToCall.performFundingSourceLookup", fundingSourceIdField, fundingSourceIdValue);
    }
    
    protected void assertSubmit() {
        assertPageContains("Document was successfully submitted");
    }
    
}