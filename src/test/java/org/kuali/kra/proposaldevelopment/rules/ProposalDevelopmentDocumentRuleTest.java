/*
 * Copyright 2006-2008 The Kuali Foundation
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

import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonCreditSplit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class tests the ProposalDevelopmentDocumentRule class
 */
public class ProposalDevelopmentDocumentRuleTest extends KraTestBase {

    private static final String DEFAULT_PROPOSAL_SPONSOR_CODE = "005889";
    private static final String DEFAULT_PROPOSAL_TITLE = "Project title";
    private static final String DEFAULT_PROPOSAL_ACTIVITY_TYPE = "1";
    private static final String DEFAULT_PROPOSAL_OWNED_BY_UNIT = "000002";

    private static final String PROPOSAL_TYPE_NEW = "1";
    private static final String PROPOSAL_TYPE_CONTINUATION = "2";
    private static final String PROPOSAL_TYPE_RENEWAL = "3";
    private static final String PROPOSAL_TYPE_RESUBMISSION = "4";
    private static final String PROPOSAL_TYPE_REVISION = "5";
    private static final String PROPOSAL_TYPE_TASK_ORDER = "6";
    private static final String DOCUMENT_HEADER_DESCRIPTION = "ProposalDevelopmentDocumentWebTest test";
    
    // Key Personnel Fixtures
    private static final String UNIT_NUMBER_TO_ADD = "IN-PERS";
    
    private DocumentService documentService = null;
    private ProposalDevelopmentDocumentRule proposalDevelopmentDocumentRule = null;
    private Date defaultProposalRequestedStartDate = null;
    private Date defaultProposalRequestedEndDate = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KNSServiceLocator.getDocumentService();
        proposalDevelopmentDocumentRule = new ProposalDevelopmentDocumentRule();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        defaultProposalRequestedStartDate = new Date(dateFormat.parse("08/14/2007").getTime());
        defaultProposalRequestedEndDate = new Date(dateFormat.parse("08/21/2007").getTime());
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        documentService = null;
        proposalDevelopmentDocumentRule = null;
        defaultProposalRequestedStartDate = null;
        defaultProposalRequestedEndDate = null;
        super.tearDown();
    }

    @Test 
    public void testValidNewProposalType() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");

        setRequiredDocumentFields(document,
                DOCUMENT_HEADER_DESCRIPTION,
                DEFAULT_PROPOSAL_SPONSOR_CODE,
                DEFAULT_PROPOSAL_TITLE,
                defaultProposalRequestedStartDate,
                defaultProposalRequestedEndDate,
                DEFAULT_PROPOSAL_ACTIVITY_TYPE,
                PROPOSAL_TYPE_NEW,
                DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        assertTrue("Rule shouldn't produce any errors", proposalDevelopmentDocumentRule.processCustomSaveDocumentBusinessRules(document));
        assertEquals(0, GlobalVariables.getErrorMap().size());
    }

    @Test 
    public void testNonNewProposalTypeWithoutOriginalProposalId() throws Exception {
        processType(PROPOSAL_TYPE_NEW, false, false);
        processType(PROPOSAL_TYPE_CONTINUATION, false, true);
        processType(PROPOSAL_TYPE_RENEWAL, false, true);
        processType(PROPOSAL_TYPE_RESUBMISSION, false, false);
        processType(PROPOSAL_TYPE_REVISION, false, true);
        processType(PROPOSAL_TYPE_TASK_ORDER, false, false);
    }

    @Test 
    public void testNonNewProposalTypeWithOriginalProposalId() throws Exception {
        processType(PROPOSAL_TYPE_NEW, true, false);
        processType(PROPOSAL_TYPE_CONTINUATION, true, false);
        processType(PROPOSAL_TYPE_RENEWAL, true, false);
        processType(PROPOSAL_TYPE_RESUBMISSION, true, false);
        processType(PROPOSAL_TYPE_REVISION, true, false);
        processType(PROPOSAL_TYPE_TASK_ORDER, true, false);
    }

    /**
     * This method does all the processing for a particular proposalTypeCode
     * @param proposalTypeCode proposalType to check
     * @param setContinuedFrom boolean whether to set original Proposal ID or not - if it's set
     * we shouldn't get any errors, but if it's missing we should get errors
     * @throws WorkflowException
     */
    private void processType(String proposalTypeCode, boolean setSponsorProposalId, boolean expectError) throws WorkflowException {
        GlobalVariables.getErrorMap().clear();
        
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        setRequiredDocumentFields(document,
                DOCUMENT_HEADER_DESCRIPTION,
                DEFAULT_PROPOSAL_SPONSOR_CODE,
                DEFAULT_PROPOSAL_TITLE,
                defaultProposalRequestedStartDate,
                defaultProposalRequestedEndDate,
                DEFAULT_PROPOSAL_ACTIVITY_TYPE,
                proposalTypeCode,
                DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        
        if (setSponsorProposalId) {
            document.setSponsorProposalNumber("234567");
        }
        if (!expectError) {
            assertTrue("Rule should NOT produce any errors", proposalDevelopmentDocumentRule.processCustomSaveDocumentBusinessRules(document));
            assertEquals(0, GlobalVariables.getErrorMap().size());
        } else {
            assertFalse("Rule should produce an errors", proposalDevelopmentDocumentRule.processCustomSaveDocumentBusinessRules(document));
            assertEquals(1, GlobalVariables.getErrorMap().size());
            ErrorMap errorMap = GlobalVariables.getErrorMap();
            List<ErrorMessage> messages = errorMap.getMessages("document.sponsorProposalNumber");
            ErrorMessage errorMessage = messages.get(0);
            assertEquals(KeyConstants.ERROR_REQUIRED_PROPOSAL_SPONSOR_ID, errorMessage.getErrorKey());
            
            assertEquals("Sponsor Proposal ID (Sponsor Proposal ID)", errorMessage.getMessageParameters()[0]);
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
    private void setRequiredDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.getDocumentHeader().setDocumentDescription(description);
        document.setSponsorCode(sponsorCode);
        document.setTitle(title);
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode(activityTypeCode);
        document.setProposalTypeCode(proposalTypeCode);
        document.setOwnedByUnitNumber(ownedByUnit);
    }
    
    /**
     * Tests the {@link ProposalDevelopmentKeyPersonsRule#processChangeKeyPersonBusinessRules(org.kuali.kra.proposaldevelopment.bo.ProposalPerson, org.kuali.rice.kns.bo.BusinessObject)}
     * by adding a {@link Unit} and then removing the same {@link Unit}
     * 
     */
    @Test
    public void testRemoveUnit() {
        ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
        ProposalPerson person = new ProposalPerson();
        document.setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE);
        
        getKeyPersonnelService().populateProposalPerson(person, document);
        document.addProposalPerson(person);
        
        ProposalPersonUnit unit = getKeyPersonnelService().createProposalPersonUnit(UNIT_NUMBER_TO_ADD, person);
        
        assertTrue(new ProposalDevelopmentKeyPersonsRule().processChangeKeyPersonBusinessRules(person, unit,0));
        
        getKeyPersonnelService().addUnitToPerson(person, unit);
        assertFalse(new ProposalDevelopmentKeyPersonsRule().processChangeKeyPersonBusinessRules(person, unit,0));        
    }

    /**
     * Tests the {@link ProposalDevelopmentKeyPersonsRule#processChangeKeyPersonBusinessRules(org.kuali.kra.proposaldevelopment.bo.ProposalPerson, org.kuali.rice.kns.bo.BusinessObject)}
     * by adding a {@link Unit} and adding it again
     * 
     */
    @Test
    public void testDoubleUnit() {
        ProposalPerson person = new ProposalPerson();
        ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
        document.setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE);
        getKeyPersonnelService().populateProposalPerson(person, document);
        document.addProposalPerson(person);

        
        ProposalPersonUnit unit = getKeyPersonnelService().createProposalPersonUnit(UNIT_NUMBER_TO_ADD, person);
        
        assertTrue(new ProposalDevelopmentKeyPersonsRule().processChangeKeyPersonBusinessRules(person, unit,0));

        getKeyPersonnelService().addUnitToPerson(person, unit);

        assertFalse(new ProposalDevelopmentKeyPersonsRule().processChangeKeyPersonBusinessRules(person, unit,0));
    }
    
    /**
     * Tests the {@link ProposalDevelopmentKeyPersonsRule#processAddKeyPersonBusinessRules(ProposalDevelopmentDocument, ProposalPerson)}
     * by adding a {@link ProposalPerson} without a role id set.
     * 
     */
    @Test
    public void testAddPersonWithoutRole() {
        ProposalPerson person = new ProposalPerson();
        ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
        document.setOwnedByUnitNumber("000001");
        person.setPersonId("000000003");
        getKeyPersonnelService().populateProposalPerson(person, document);
        person.setProposalPersonRoleId("");
        person.setRole(null);
        person.refreshReferenceObject("role");
        
        assertFalse(new ProposalDevelopmentKeyPersonsRule().processAddKeyPersonBusinessRules(document, person));
    }
    
    /**
     * Tests the {@link ProposalDevelopmentKeyPersonsRule#processAddKeyPersonBusinessRules(ProposalDevelopmentDocument, ProposalPerson)}
     * by running the rule on a document with the {@link ProposalPerson} added already
     * 
     */
    @Test
    public void testPersonExists() {
        ProposalPerson person = INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED.getPerson();
        ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
        INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED.populatePerson(document, person);
        document.setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE);
        document.addProposalPerson(person);
        
        assertFalse(new ProposalDevelopmentKeyPersonsRule().processAddKeyPersonBusinessRules(document, person));
    }
    
    /**
     * Tests the {@link ProposalDevelopmentKeyPersonsRule#processAddKeyPersonBusinessRules(ProposalDevelopmentDocument, ProposalPerson)}
     * by running the rule on a document with the {@link ProposalPerson} that has invalid personId and rolodexId
     * 
     */
    @Test
    public void testPersonInvalid() {
        ProposalPerson person = new ProposalPerson();
        ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
        document.setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE);
        getKeyPersonnelService().populateProposalPerson(person, document);
        document.addProposalPerson(person);
        person.setRolodexId(-1);
        person.setPersonId("-1");
        
        assertFalse(new ProposalDevelopmentKeyPersonsRule().processAddKeyPersonBusinessRules(document, person));
    }
    
    
    
    /**
     * Tests the {@link ProposalDevelopmentKeyPersonsRule#processAddKeyPersonBusinessRules(ProposalDevelopmentDocument, ProposalPerson)}
     * by running the rule on a document with the {@link ProposalPerson} that has null values for degree fields
     * 
     */
    @Test
    public void testRemoveDegreeRule() {
        ProposalPerson person = new ProposalPerson();
        ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
        document.setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE);
        getKeyPersonnelService().populateProposalPerson(person, document);
        document.addProposalPerson(person);
        ProposalPersonDegree degree = new ProposalPersonDegree();
        person.addDegree(degree);
        person.setRolodexId(-1);
        person.setPersonId("-1");
        assertFalse(new ProposalDevelopmentKeyPersonsRule().processAddKeyPersonBusinessRules(document, person));
    }
    
    @Test
    public void testPercentage()
    {
        ProposalPerson person = new ProposalPerson();
        ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
        document.setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE);
        getKeyPersonnelService().populateProposalPerson(person, document);
        KualiDecimal dec=new KualiDecimal(109.00);
        person.setPercentageEffort(dec);
        List<ProposalPersonCreditSplit> personcreditsplit=new ArrayList<ProposalPersonCreditSplit>();
        ProposalPersonCreditSplit creditsplit=new ProposalPersonCreditSplit();
        creditsplit.setInvCreditTypeCode("0");
        creditsplit.setCredit(dec);
        ProposalPersonCreditSplit creditsplit1=new ProposalPersonCreditSplit();
        creditsplit1.setInvCreditTypeCode("1");
        creditsplit1.setCredit(dec);
        
        ProposalPersonCreditSplit creditsplit2=new ProposalPersonCreditSplit();
        creditsplit2.setInvCreditTypeCode("2");
        creditsplit2.setCredit(dec);
        
        ProposalPersonCreditSplit creditsplit3=new ProposalPersonCreditSplit();
        creditsplit3.setInvCreditTypeCode("3");
        creditsplit3.setCredit(dec);
        
        personcreditsplit.add(creditsplit);
        personcreditsplit.add(creditsplit1);
        personcreditsplit.add(creditsplit2);
        personcreditsplit.add(creditsplit3);
        
        
        person.setCreditSplits(personcreditsplit);
            
        document.addProposalPerson(person);
        
        assertFalse(new ProposalDevelopmentKeyPersonsRule().processSaveKeyPersonBusinessRules(document));
        
        
    }
    
    /**
     * Locate the <code>{@link KeyPersonnelService}</code>
     * 
     * @return KeyPersonnelService
     * @see KraTestBase#getService(Class)
     */
    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }
}