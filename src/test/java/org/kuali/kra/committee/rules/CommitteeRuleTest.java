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
package org.kuali.kra.committee.rules;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.bo.CommitteeType;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Test the Committee Rules.
 */
public class CommitteeRuleTest extends CommitteeRuleTestBase {

    private static final String INACTIVE_RESEARCH_AREAS_PREFIX = "document.committeeList[0].committeeResearchAreas.inactive";
    private static final String SEPERATOR = ".";
    
    private CommitteeDocumentRule rule;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rule = new CommitteeDocumentRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
        super.tearDown();
    }
    
    /**
     * Test the required fields in the committee.  
     * @throws Exception
     */
    @Test
    public void testRequiredFields() throws Exception {
        
        /*
         * Create a committee without setting any properties.
         */
        CommitteeDocument document = getNewCommitteeDocument();
        

        /*
         * Process the Save Document rule.  Since we didn't set
         * any properties, each of the required fields should 
         * result in an error.
         */
        boolean rulesPassed = rule.processSaveDocument(document);
        assertFalse(rulesPassed);
        
        /*
         * There should be nine required fields.
         */
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(8, errorMap.getErrorCount());
        
        /*
         * Verify that the error keys for each of the required fields 
         * is in the ErrorMap.
         */
        assertTrue(errorMap.containsKey("document.documentHeader.documentDescription"));
        assertTrue(errorMap.containsKey("document.committeeList[0].committeeTypeCode"));
        assertTrue(errorMap.containsKey("document.committeeList[0].maxProtocols"));
        assertTrue(errorMap.containsKey("document.committeeList[0].homeUnitNumber"));
        assertTrue(errorMap.containsKey("document.committeeList[0].minimumMembersRequired"));
        assertTrue(errorMap.containsKey("document.committeeList[0].committeeName"));
        assertTrue(errorMap.containsKey("document.committeeList[0].advancedSubmissionDaysRequired"));
        // assertTrue(errorMap.containsKey("document.committeeList[0].reviewTypeCode"));
        assertTrue(errorMap.containsKey("document.committeeList[0].committeeId"));
    }
    
    /**
     * The committee IDs are required to be unique. 
     * @throws Exception
     */
    @Test
    public void testDuplicateIds() throws Exception {
        
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);
        documentService.saveDocument(document);
        String committeeId = document.getCommittee().getCommitteeId();
       
        document = getNewCommitteeDocument();
        setCommitteeProperties(document);
        document.getCommittee().setCommitteeId(committeeId);
        
        /*
         * Verify that we can't save a committee with a duplicate Committee ID.
         */
        boolean rulesPassed = rule.processSaveDocument(document);
        assertFalse(rulesPassed);
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertTrue(errorMap.containsMessageKey(KeyConstants.ERROR_COMMITTEE_DUPLICATE_ID));
    }
    
    /**
     * The home unit number must be valid.
     * @throws Exception
     */
    @Test
    public void testInvalidHomeUnit() throws Exception {
        
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);
        document.getCommittee().setHomeUnitNumber("xxx");
       
        /*
         * Verify that we can't save a committee with a duplicate Committee ID.
         */
        boolean rulesPassed = rule.processSaveDocument(document);
        assertFalse(rulesPassed);
        
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertTrue(errorMap.containsMessageKey(KeyConstants.ERROR_INVALID_UNIT));
    }
    
    @Test
    public void testValidateCommitteeReviewType() throws Exception {
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);
        // unset both the review types
        document.getCommittee().setCoiReviewTypeCode(null);
        document.getCommittee().setReviewTypeCode(null);
        assertFalse(invoke_ValidateCommitteeReviewType(document));
        
        // set a non-null review type code for IRB, null for COI and set the committee type to COI
        document.getCommittee().setCommitteeTypeCode(CommitteeType.COI_TYPE_CODE);
        document.getCommittee().setCoiReviewTypeCode(null);
        document.getCommittee().setReviewTypeCode("4");
        assertFalse(invoke_ValidateCommitteeReviewType(document));
        assertError("document.committeeList[0].reviewTypeCode", KeyConstants.ERROR_COMMITTEE_REVIEW_TYPE_REQUIRED);
        
        // set a non-null review type code for COI, should now pass
        document.getCommittee().setCoiReviewTypeCode("4");
        assertTrue(invoke_ValidateCommitteeReviewType(document));
        
        // set a non-null review type code for COI, null for IRB and set the committee type to IRB
        document.getCommittee().setCoiReviewTypeCode("4");
        document.getCommittee().setReviewTypeCode(null);
        document.getCommittee().setCommitteeTypeCode(CommitteeType.IRB_TYPE_CODE);
        assertFalse(invoke_ValidateCommitteeReviewType(document));
        assertError("document.committeeList[0].reviewTypeCode", KeyConstants.ERROR_COMMITTEE_REVIEW_TYPE_REQUIRED);
        
        // set a non-null review type code for IRB, should now pass
        document.getCommittee().setReviewTypeCode("4");
        assertTrue(invoke_ValidateCommitteeReviewType(document));
    }
    
    // for testing a private validation method
    private boolean invoke_ValidateCommitteeReviewType(CommitteeDocument document) throws Exception {      
        Method m = rule.getClass().getDeclaredMethod("validateCommitteeReviewType", CommitteeDocument.class);
        m.setAccessible(true);       
        return (Boolean) m.invoke(rule, document);    
    }
    
    
    /**
     * This method tests the logic for validating that all research areas associated with a committee are active.
     * Specifically it tests 3 different cases: 
     *      1. IRB Committee has no research areas -- should give no error as rule is satisfied trivially.
     *      2. IRB Committee has research areas and all are active -- should give no error
     *      3. IRB Committee has research areas and some are inactive -- should give a single error with the error-property correctly encoding the
     *              indices of the inactive areas.
     *      4. COI Committee does not care about research areas being active or inactive.        
     */
    @Test
    public void testProcessCommitteeResearchAreaBusinessRules() throws Exception {
        CommitteeDocument document = getNewCommitteeDocument();
        setCommitteeProperties(document);
        // check case 1
        assertTrue(rule.processCommitteeResearchAreaBusinessRules(document));
        
        // check case 2
        CommitteeResearchArea dummyCRA0 = new CommitteeResearchArea();
        ResearchArea dummyRA0 = new ResearchArea();
        dummyRA0.setActive(true);
        dummyCRA0.setResearchAreas(dummyRA0);
        
        CommitteeResearchArea dummyCRA1 = new CommitteeResearchArea();
        ResearchArea dummyRA1 = new ResearchArea();
        dummyRA1.setActive(true);
        dummyCRA1.setResearchAreas(dummyRA1);
        
        CommitteeResearchArea dummyCRA2 = new CommitteeResearchArea();
        ResearchArea dummyRA2 = new ResearchArea();
        dummyRA2.setActive(true);
        dummyCRA2.setResearchAreas(dummyRA2);
        
        CommitteeResearchArea dummyCRA3 = new CommitteeResearchArea();
        ResearchArea dummyRA3 = new ResearchArea();
        dummyRA3.setActive(true);
        dummyCRA3.setResearchAreas(dummyRA3);
        
        ArrayList<CommitteeResearchArea> cras = new ArrayList<CommitteeResearchArea>();
        cras.add(dummyCRA0);
        cras.add(dummyCRA1);
        cras.add(dummyCRA2);
        cras.add(dummyCRA3);
        
        document.getCommittee().setCommitteeResearchAreas(cras);
        
        assertTrue(document.getCommittee().getCommitteeResearchAreas().get(0).getResearchAreas().isActive());
        assertTrue(document.getCommittee().getCommitteeResearchAreas().get(1).getResearchAreas().isActive());
        assertTrue(document.getCommittee().getCommitteeResearchAreas().get(2).getResearchAreas().isActive());
        assertTrue(document.getCommittee().getCommitteeResearchAreas().get(3).getResearchAreas().isActive());
        
        assertTrue(rule.processCommitteeResearchAreaBusinessRules(document));
        
        // check case 3
        assertTrue(document.getCommittee().getCommitteeResearchAreas().get(0).getResearchAreas().isActive());
        
        dummyRA1.setActive(false);
        assertFalse(document.getCommittee().getCommitteeResearchAreas().get(1).getResearchAreas().isActive());
        
        assertTrue(document.getCommittee().getCommitteeResearchAreas().get(2).getResearchAreas().isActive());
        
        dummyRA3.setActive(false);
        assertFalse(document.getCommittee().getCommitteeResearchAreas().get(3).getResearchAreas().isActive());
        
        assertFalse(rule.processCommitteeResearchAreaBusinessRules(document));
        String errorPropertyKey = INACTIVE_RESEARCH_AREAS_PREFIX + SEPERATOR + "1.3.";
        assertError(errorPropertyKey, KeyConstants.ERROR_COMMITTEE_RESEARCH_AREA_INACTIVE);
        
        // check case 4
        document.getCommittee().setCommitteeTypeCode("2");
        assertTrue(rule.processCommitteeResearchAreaBusinessRules(document));
        
    }

}
