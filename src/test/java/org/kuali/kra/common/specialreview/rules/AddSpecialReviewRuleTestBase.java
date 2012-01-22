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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
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
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.AutoPopulatingList;

public abstract class AddSpecialReviewRuleTestBase<T extends SpecialReview<? extends SpecialReviewExemption>> extends KcUnitTestBase {
    
    private static final String SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE = "2";
    
    private static final String APPROVAL_TYPE_CODE_APPROVED = "2";
    private static final String APPROVAL_TYPE_CODE_EXEMPT = "4";
    
    private static final String EXEMPTION_TYPE_CODE_E1 = "1";
    private static final String EXEMPTION_TYPE_CODE_E2 = "2";
    
    private static final String SPECIAL_REVIEW_TYPE_CODE_FIELD = "specialReviewTypeCode";
    private static final String APPROVAL_TYPE_CODE_FIELD = "approvalTypeCode";
    private static final String PROTOCOL_NUMBER_FIELD = "protocolNumber";
    private static final String APPLICATION_DATE_FIELD = "applicationDate";
    private static final String APPROVAL_DATE_FIELD = "approvalDate";
    private static final String EXPIRATION_DATE_FIELD = "expirationDate";
    private static final String EXEMPTION_TYPE_CODE_FIELD = "exemptionTypeCodes";
    
    private static final String SPECIAL_REVIEW_TYPE_DESCRIPTION_ANIMAL_USAGE = "Animal Usage";
    
    private static final String APPROVAL_TYPE_DESCRIPTION_APPROVED = "Approved";
    private static final String APPROVAL_TYPE_DESCRIPTION_EXEMPT = "Exempt";
    
    private Mockery context;
    private AddSpecialReviewRule<T> rule;
    private DateFormat dateFormat;
    
    private Protocol protocol;
    
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
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        rule = new AddSpecialReviewRule<T>();
        dateFormat = DateFormat.getDateInstance();
        protocol = ProtocolFactory.createProtocolDocument().getProtocol();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        context = null;
        rule = null;
        dateFormat = null;
        protocol = null;
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
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(protocol.getProtocolNumber());
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE, APPROVAL_TYPE_CODE_APPROVED, true, false, false, false));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertTrue(rule.processRules(addSpecialReviewEvent));
    }
    
    /**
     * Test adding a Special Review with an unspecified special review code.
     * 
     * @throws Exception
     */
    @Test
    public void testBlankSpecialReviewCode() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(null);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(protocol.getProtocolNumber());
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(null, APPROVAL_TYPE_CODE_APPROVED, false, false, false, false));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(SPECIAL_REVIEW_TYPE_CODE_FIELD, KeyConstants.ERROR_REQUIRED);
    }

    /**
     * Test adding a Special Review with an unspecified approval status.
     * 
     * @throws Exception
     */
    @Test
    public void testBlankApprovalTypeCode() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE);
        newSpecialReview.setApprovalTypeCode(null);
        newSpecialReview.setProtocolNumber(protocol.getProtocolNumber());
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE, null, false, false, false, false));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(APPROVAL_TYPE_CODE_FIELD, KeyConstants.ERROR_REQUIRED);
    }
    
    /**
     * Test adding a Special Review with an unspecified protocol number.
     * 
     * @throws Exception
     */
    @Test
    public void testProtocolWithBlankProtocolNumber() throws Exception {
        Document document = getDocument();
        
        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(null);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE, APPROVAL_TYPE_CODE_APPROVED, true, false, false, false));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(PROTOCOL_NUMBER_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID);
    }
    
    /**
     * Test adding a Special Review with an approval type but with a blank application date.
     * 
     * @throws Exception
     */
    @Test
    public void testApprovalWithBlankApplicationDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(protocol.getProtocolNumber());
        newSpecialReview.setApplicationDate(null);
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE, APPROVAL_TYPE_CODE_APPROVED, true, true, false, false));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(APPLICATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID);
    }

    /**
     * Test adding a Special Review with an approval type but with a blank approval date.
     * 
     * @throws Exception
     */
    @Test
    public void testApprovalWithBlankApprovalDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(protocol.getProtocolNumber());
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(null);
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE, APPROVAL_TYPE_CODE_APPROVED, true, false, true, false));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID);
    }
    
    /**
     * Test adding a Special Review with a non-approval (i.e. exempt) approval type but also entering an approval date.
     * 
     * @throws Exception
     */
    @Test
    public void testNonApprovalWithApprovalDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_EXEMPT);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE, APPROVAL_TYPE_CODE_EXEMPT, false, false, false, true));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_EMPTY_FOR_NOT_APPROVED);
    }
    
    /**
     * Test adding a Special Review with an exempt approval type but also entering no exemption codes.
     * 
     * @throws Exception
     */
    @Test
    public void testExemptWithBlankExemptionCodes() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_EXEMPT);
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        newSpecialReview.setExemptionTypeCodes(new ArrayList<String>());
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE, APPROVAL_TYPE_CODE_EXEMPT, false, false, false, true));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_REQUIRED_FOR_VALID);
    }
    
    /**
     * Test adding a Special Review with a non-exempt (i.e. approved) approval type but also entering exemption codes.
     * 
     * @throws Exception
     */
    @Test
    public void testNotExemptWithExemptionCodes() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(protocol.getProtocolNumber());
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2007").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        newSpecialReview.setExemptionTypeCodes(Arrays.asList(EXEMPTION_TYPE_CODE_E1, EXEMPTION_TYPE_CODE_E2));
        AddSpecialReviewEvent<T> addSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE, APPROVAL_TYPE_CODE_APPROVED, true, false, true, false));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertFalse(rule.processRules(addSpecialReviewEvent));
        assertError(EXEMPTION_TYPE_CODE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_CANNOT_SELECT_EXEMPTION_FOR_VALID);
    }

    /**
     * Test adding a Special Review with approval date before application date. 
     * 
     * @throws Exception
     */
    @Test
    public void testApprovalDateBeforeApplicationDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(protocol.getProtocolNumber());
        // 08/01/2008 > 08/01/2007
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2008").getTime()));
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addProposalSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE, APPROVAL_TYPE_CODE_APPROVED, true, false, false, false));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertFalse(rule.processRules(addProposalSpecialReviewEvent));
        assertError(APPROVAL_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_SAME_OR_LATER);
    }
    
    /**
     * Test adding a Special Review with expiration date before approval date. 
     * 
     * @throws Exception
     */
    @Test
    public void testExpirationDateBeforeApprovalDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(protocol.getProtocolNumber());
        // 08/01/2008 > 08/01/2007
        newSpecialReview.setApprovalDate(new Date(dateFormat.parse("Aug 1, 2008").getTime()));
        newSpecialReview.setExpirationDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addProposalSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE, APPROVAL_TYPE_CODE_APPROVED, true, false, false, false));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertFalse(rule.processRules(addProposalSpecialReviewEvent));
        assertError(EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_SAME_OR_LATER);
    }
    
    /**
     * Test adding a Special Review with expiration date before application date. 
     * 
     * @throws Exception
     */
    @Test
    public void testApplicationDateBeforeExpirationDate() throws Exception {
        Document document = getDocument();

        T newSpecialReview = getSpecialReview();
        newSpecialReview.setSpecialReviewTypeCode(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE);
        newSpecialReview.setApprovalTypeCode(APPROVAL_TYPE_CODE_APPROVED);
        newSpecialReview.setProtocolNumber(protocol.getProtocolNumber());
        // 08/01/2008 > 08/01/2007
        newSpecialReview.setApplicationDate(new Date(dateFormat.parse("Aug 1, 2008").getTime()));
        newSpecialReview.setExpirationDate(new Date(dateFormat.parse("Aug 21, 2007").getTime()));
        AddSpecialReviewEvent<T> addProposalSpecialReviewEvent = new AddSpecialReviewEvent<T>(document, newSpecialReview, new ArrayList<T>(), false);
        
        rule.setBusinessObjectService(getBusinessObjectService(SPECIAL_REVIEW_TYPE_CODE_ANIMAL_USAGE, APPROVAL_TYPE_CODE_APPROVED, true, false, false, false));
        rule.setProtocolFinderDao(getProtocolFinderDao());
        assertFalse(rule.processRules(addProposalSpecialReviewEvent));
        assertError(EXPIRATION_DATE_FIELD, KeyConstants.ERROR_SPECIAL_REVIEW_DATE_SAME_OR_LATER);
    }
    
    protected BusinessObjectService getBusinessObjectService(final String specialReviewTypeCode, final String approvalTypeCode, 
            final boolean protocolNumberFlag, final boolean applicationDateFlag, final boolean approvalDateFlag, final boolean exemptNumberFlag) {
        final BusinessObjectService service = context.mock(BusinessObjectService.class);
        final SpecialReviewType specialReviewType = context.mock(SpecialReviewType.class);
        final SpecialReviewApprovalType specialReviewApprovalType = context.mock(SpecialReviewApprovalType.class);
        final ValidSpecialReviewApproval approval = context.mock(ValidSpecialReviewApproval.class);
        
        final Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(SPECIAL_REVIEW_TYPE_CODE_FIELD, specialReviewTypeCode);
        fieldValues.put(APPROVAL_TYPE_CODE_FIELD, approvalTypeCode);
        
        context.checking(new Expectations() {{
            allowing(service).findMatching(ValidSpecialReviewApproval.class, fieldValues);
            will(returnValue(Collections.singletonList(approval)));
            
            allowing(approval).getSpecialReviewType();
            will(returnValue(specialReviewType));
            
            allowing(approval).getSpecialReviewApprovalType();
            will(returnValue(specialReviewApprovalType));
            
            allowing(approval).isProtocolNumberFlag();
            will(returnValue(protocolNumberFlag));
            
            allowing(approval).isApplicationDateFlag();
            will(returnValue(applicationDateFlag));
            
            allowing(approval).isApprovalDateFlag();
            will(returnValue(approvalDateFlag));
            
            allowing(approval).isExemptNumberFlag();
            will(returnValue(exemptNumberFlag));
            
            allowing(specialReviewType).getSpecialReviewTypeCode();
            will(returnValue(specialReviewTypeCode));
            
            allowing(specialReviewType).getDescription();
            will(returnValue(SPECIAL_REVIEW_TYPE_DESCRIPTION_ANIMAL_USAGE));
            
            allowing(specialReviewApprovalType).getApprovalTypeCode();
            will(returnValue(approvalTypeCode));
            
            allowing(specialReviewApprovalType).getDescription();
            will(returnValue(getApprovalDescription(approvalTypeCode)));
        }

        private String getApprovalDescription(String approvalTypeCode) {
            if (APPROVAL_TYPE_CODE_APPROVED.equals(approvalTypeCode)) {
                return APPROVAL_TYPE_DESCRIPTION_APPROVED;
            } else if (APPROVAL_TYPE_CODE_EXEMPT.equals(approvalTypeCode)) {
                return APPROVAL_TYPE_DESCRIPTION_EXEMPT;
            } else {
                return Constants.EMPTY_STRING;
            }
        }
        
        });
    
        return service;
    }
    
    protected ProtocolFinderDao getProtocolFinderDao() throws WorkflowException {
        final ProtocolFinderDao service = context.mock(ProtocolFinderDao.class);
        
        context.checking(new Expectations() {{
            allowing(service).findCurrentProtocolByNumber(protocol.getProtocolNumber());
            will(returnValue(protocol));
        }});
        
        return service;
    }
    
    private void assertError(String propertyKey, String errorKey) {
        AutoPopulatingList errors = GlobalVariables.getMessageMap().getMessages(propertyKey);
        assertNotNull(errors);
        assertTrue(errors.size() == 1);
        
        ErrorMessage message = (ErrorMessage) errors.get(0);
        assertNotNull(message);
        assertEquals(errorKey, message.getErrorKey());
    }
    
}