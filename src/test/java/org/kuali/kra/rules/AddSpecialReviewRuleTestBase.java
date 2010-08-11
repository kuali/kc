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
package org.kuali.kra.rules;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;

public abstract class AddSpecialReviewRuleTestBase<T extends AbstractSpecialReview<? extends AbstractSpecialReviewExemption>> extends KcUnitTestBase {
    
    private static final String SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS = "1";
    
    private static final String APPROVAL_TYPE_CODE_APPROVED = "2";
    private static final String APPROVAL_TYPE_CODE_EXEMPT = "4";
    
    private static final String PROTOCOL_NUMBER = "0906000001";
    
    private static final String EXEMPTION_TYPE_CODE_E1 = "1";
    private static final String EXEMPTION_TYPE_CODE_E2 = "2";
    
    private static final String DOT = ".";
    private static final String SPECIAL_REVIEW_CODE_FIELD = "specialReviewCode";
    private static final String APPROVAL_TYPE_CODE_FIELD = "approvalTypeCode";
    private static final String PROTOCOL_NUMBER_FIELD = "protocolNumber";
    private static final String APPLICATION_DATE_FIELD = "applicationDate";
    private static final String APPROVAL_DATE_FIELD = "approvalDate";
    private static final String EXPIRATION_DATE_FIELD = "expirationDate";
    private static final String EXEMPTION_TYPE_CODE_FIELD = "exemptionTypeCodes";
    
    private String errorPathPrefix;
    
    private AddSpecialReviewRule<T> rule;
    private DateFormat dateFormat;
    
    public AddSpecialReviewRuleTestBase(String errorPathPrefix) {
        this.errorPathPrefix = errorPathPrefix;
    }
    
    /**
     * Returns the document specific to the SpecialReview type being tested.
     * @return Document
     * @throws WorkflowException
     * @throws Exception
     */
    public abstract Document getDocument() throws WorkflowException, Exception;
    
    /**
     * Returns the SpecialReview being tested.
     * @return a subtype of AbstractSpecialReview
     */
    public abstract T getSpecialReview();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new AddSpecialReviewRule<T>(errorPathPrefix);
        dateFormat = DateFormat.getDateInstance();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        rule = null;
        dateFormat = null;
    }

    /**
     * Test a good case.
     * 
     * @throws Exception
     */
    @Test
    public void testOK() throws Exception {
        Document document = getDocument();
 
        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertTrue(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));
    }

    /**
     * Test adding an proposal special with unspecified approval status . This corresponds to a empty string type code, i.e. the user
     * didn't select approval status.
     * 
     * @throws Exception
     */
    @Test
    public void testBlankApprovalTypeCode() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(null);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertFalse(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));
        assertError(errorPathPrefix + DOT + APPROVAL_TYPE_CODE_FIELD, KeyConstants.ERROR_REQUIRED_SELECT_APPROVAL_STATUS);
    }

    /**
     * Test adding an  special with unspecified special review code . This corresponds to a empty string type code, i.e. the user
     * didn't select special review code.
     * 
     * @throws Exception
     */
    @Test
    public void testBlankSpecialReviewCode() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(null);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertFalse(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));
        assertError(errorPathPrefix + DOT + SPECIAL_REVIEW_CODE_FIELD, KeyConstants.ERROR_REQUIRED_SELECT_SPECIAL_REVIEW_CODE);
    }
    
    /**
     * Test adding a special review with a non-approval (i.e. exempt) approval type but also entering an approval date.
     * 
     * @throws Exception
     */
    @Test
    public void testNonApprovalWithApprovalDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_EXEMPT);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertFalse(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));
        assertError(errorPathPrefix + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_NOT_APPROVED_SPECIALREVIEW);
    }

    /**
     * Test adding a special review with approval date before application date. 
     * 
     * @throws Exception
     */
    @Test
    public void testApprovalDateBeforeApplicationDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        // 08/01/2008 > 08/01/2007
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2008").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addProposalSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertFalse(rule.processAddSpecialReviewEvent(addProposalSpecialReviewEvent));
        assertError(errorPathPrefix + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_APPROVAL_DATE_BEFORE_APPLICATION_DATE_SPECIALREVIEW);
    }
    
    /**
     * Test adding a special review with expiration date before approval date. 
     * 
     * @throws Exception
     */
    @Test
    public void testExpirationDateBeforeApprovalDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        // 08/01/2008 > 08/01/2007
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 1, 2008").getTime()));
        newSpecialReview.setExpirationDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addProposalSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertFalse(rule.processAddSpecialReviewEvent(addProposalSpecialReviewEvent));
        assertError(errorPathPrefix + DOT + EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_ORDERING);
    }
    
    /**
     * Test adding a special review with expiration date before application date. 
     * 
     * @throws Exception
     */
    @Test
    public void testApplicationDateBeforeExpirationDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        // 08/01/2008 > 08/01/2007
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2008").getTime()));
        newSpecialReview.setExpirationDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addProposalSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertFalse(rule.processAddSpecialReviewEvent(addProposalSpecialReviewEvent));
        assertError(errorPathPrefix + DOT + EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_ORDERING);
    }
    
    @Test
    public void testProtocolWithBlankProtocolNumber() throws Exception {
        Document document = getDocument();
        
        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(null);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertFalse(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));
        assertError(errorPathPrefix + DOT + PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW);
    }
    
    /*
     * There doesn't seem to be any case where this validation rule is false, but the ValidSpecialRuleApproval is set up to detect it, so leave this test
     * ignored for now...
     */
    @Ignore
    @Test
    public void testApplicationWithBlankApplicationDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(null);
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertFalse(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));
        assertError(errorPathPrefix + DOT + APPLICATION_DATE_FIELD, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW);
    }
    
    /*
     * There doesn't seem to be any case where this validation rule is false, but the ValidSpecialRuleApproval is set up to detect it, so leave this test
     * ignored for now...
     */
    @Ignore
    @Test
    public void testApprovalWithBlankApprovalDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(null);
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertFalse(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));
        assertError(errorPathPrefix + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW);
    }

    @Test
    public void testExemptWithBlankExemptionCodes() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_EXEMPT);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        newSpecialReview.setExemptionTypeCodes(new ArrayList<String>());
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertFalse(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));
        assertError(errorPathPrefix + DOT + EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_REQUIRED_FOR_VALID_SPECIALREVIEW);
    }
    
    @Test
    public void testNotExemptWithExemptionCodes() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        newSpecialReview.setExemptionTypeCodes(Arrays.asList(EXEMPTION_TYPE_CODE_E1, EXEMPTION_TYPE_CODE_E2));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(errorPathPrefix, document, newSpecialReview);
        
        assertFalse(rule.processAddSpecialReviewEvent(addSpecialReviewEvent));
        assertError(errorPathPrefix + DOT + EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_EXEMPT_NUMBER_SELECTED);
    }
    
    private void assertError(String propertyKey, String errorKey) {
        TypedArrayList errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
        assertEquals(errorKey, message.getErrorKey());
    }
    
}