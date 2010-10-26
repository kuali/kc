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
package org.kuali.kra.common.specialreview.rules;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.common.specialreview.bo.SpecialReviewExemption;
import org.kuali.kra.common.specialreview.rule.AddSpecialReviewRule;
import org.kuali.kra.common.specialreview.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;

public abstract class AddSpecialReviewRuleTestBase<T extends SpecialReview<? extends SpecialReviewExemption>> extends KcUnitTestBase {
    
    private static final String NEW_SPECIAL_REVIEW = "specialReviewHelper.newSpecialReview";
    
    private static final String SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS = "1";
    
    private static final String APPROVAL_TYPE_CODE_APPROVED = "2";
    private static final String APPROVAL_TYPE_CODE_EXEMPT = "4";
    
    private static final String PROTOCOL_NUMBER = "0906000001";
    
    private static final String EXEMPTION_TYPE_CODE_E1 = "1";
    private static final String EXEMPTION_TYPE_CODE_E2 = "2";
    
    private static final String DOT = ".";
    private static final String SPECIAL_REVIEW_TYPE_CODE_FIELD = "specialReviewTypeCode";
    private static final String APPROVAL_TYPE_CODE_FIELD = "approvalTypeCode";
    private static final String PROTOCOL_NUMBER_FIELD = "protocolNumber";
    private static final String APPLICATION_DATE_FIELD = "applicationDate";
    private static final String APPROVAL_DATE_FIELD = "approvalDate";
    private static final String EXPIRATION_DATE_FIELD = "expirationDate";
    private static final String EXEMPTION_TYPE_CODE_FIELD = "exemptionTypeCodes";
    
    private AddSpecialReviewRule<T> rule;
    private DateFormat dateFormat;
    
    private SpecialReviewType humanSubjectsSpecialReviewType;
    private SpecialReviewApprovalType approvedSpecialReviewApprovalType;
    
    /**
     * Returns the document specific to the SpecialReview type being tested.
     * @return Document
     * @throws WorkflowException
     */
    public abstract Document getDocument() throws WorkflowException;
    
    /**
     * Returns the SpecialReview being tested.
     * @return a subtype of AbstractSpecialReview
     */
    public abstract T getSpecialReview();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new AddSpecialReviewRule<T>();
        dateFormat = DateFormat.getDateInstance();
        humanSubjectsSpecialReviewType = getBusinessObjectService().findBySinglePrimaryKey(SpecialReviewType.class, SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        approvedSpecialReviewApprovalType = getBusinessObjectService().findBySinglePrimaryKey(SpecialReviewApprovalType.class, APPROVAL_TYPE_CODE_APPROVED);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        rule = null;
        dateFormat = null;
        humanSubjectsSpecialReviewType = null;
        approvedSpecialReviewApprovalType = null;
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
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertTrue(rule.processRules(addSpecialReviewEvent));
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
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(null);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(APPROVAL_TYPE_CODE_FIELD, KeyConstants.ERROR_REQUIRED);
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
        newSpecialReview.setSpecialReviewTypeCode(null);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(SPECIAL_REVIEW_TYPE_CODE_FIELD, KeyConstants.ERROR_REQUIRED);
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
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_EXEMPT);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(NEW_SPECIAL_REVIEW + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_EMPTY_FOR_NOT_APPROVED);
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
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        // 08/01/2008 > 08/01/2007
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2008").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addProposalSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertFalse(rule.processRules(addProposalSpecialReviewEvent));
        assertError(NEW_SPECIAL_REVIEW + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_SAME_OR_LATER);
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
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        // 08/01/2008 > 08/01/2007
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 1, 2008").getTime()));
        newSpecialReview.setExpirationDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addProposalSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertFalse(rule.processRules(addProposalSpecialReviewEvent));
        assertError(NEW_SPECIAL_REVIEW + DOT + EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_LATER);
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
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        // 08/01/2008 > 08/01/2007
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2008").getTime()));
        newSpecialReview.setExpirationDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addProposalSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertFalse(rule.processRules(addProposalSpecialReviewEvent));
        assertError(NEW_SPECIAL_REVIEW + DOT + EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_LATER);
    }
    
    @Test
    public void testProtocolWithBlankProtocolNumber() throws Exception {
        Document document = getDocument();
        
        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(null);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(NEW_SPECIAL_REVIEW + DOT + PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID);
    }

    @Test
    public void testApplicationWithBlankApplicationDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(null);
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        ValidSpecialReviewApproval approval = new ValidSpecialReviewApproval();
        approval.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        approval.setSpecialReviewType(humanSubjectsSpecialReviewType);
        approval.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        approval.setSpecialReviewApprovalType(approvedSpecialReviewApprovalType);
        approval.setApplicationDateFlag(true);
        newSpecialReview.setValidSpecialReviewApproval(approval);
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(NEW_SPECIAL_REVIEW + DOT + APPLICATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID);
    }

    @Test
    public void testApprovalWithBlankApprovalDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(null);
        ValidSpecialReviewApproval approval = new ValidSpecialReviewApproval();
        approval.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        approval.setSpecialReviewType(humanSubjectsSpecialReviewType);
        approval.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        approval.setSpecialReviewApprovalType(approvedSpecialReviewApprovalType);
        approval.setApprovalDateFlag(true);
        newSpecialReview.setValidSpecialReviewApproval(approval);
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(NEW_SPECIAL_REVIEW + DOT + APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID);
    }

    @Test
    public void testExemptWithBlankExemptionCodes() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_EXEMPT);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        newSpecialReview.setExemptionTypeCodes(new ArrayList<String>());
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(NEW_SPECIAL_REVIEW + DOT + EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID);
    }
    
    @Test
    public void testNotExemptWithExemptionCodes() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_HUMAN_SUBJECTS);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(PROTOCOL_NUMBER);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        newSpecialReview.setExemptionTypeCodes(Arrays.asList(EXEMPTION_TYPE_CODE_E1, EXEMPTION_TYPE_CODE_E2));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview);
        
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(NEW_SPECIAL_REVIEW + DOT + EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_CANNOT_SELECT_EXEMPTION_FOR_VALID);
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