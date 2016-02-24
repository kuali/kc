/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.committee.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import static org.junit.Assert.*;
/**
 * Test the Committee Rules.
 */
public class CommitteeRuleTest extends CommitteeRuleTestBase {

    
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
         * There should be seven required fields.
         */
        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertEquals(7, errorMap.getErrorCount());
        
        /*
         * Verify that the error keys for each of the required fields 
         * is in the MessageMap.
         */
        assertTrue(errorMap.getErrorMessages().containsKey("document.committeeList[0].maxProtocols"));
        assertTrue(errorMap.getErrorMessages().containsKey("document.committeeList[0].homeUnitNumber"));
        assertTrue(errorMap.getErrorMessages().containsKey("document.committeeList[0].minimumMembersRequired"));
        assertTrue(errorMap.getErrorMessages().containsKey("document.committeeList[0].committeeName"));
        assertTrue(errorMap.getErrorMessages().containsKey("document.committeeList[0].advancedSubmissionDaysRequired"));
        assertTrue(errorMap.getErrorMessages().containsKey("document.committeeList[0].reviewTypeCode"));
        assertTrue(errorMap.getErrorMessages().containsKey("document.committeeList[0].committeeId"));
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
        
        MessageMap errorMap = GlobalVariables.getMessageMap();
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
        
        MessageMap errorMap = GlobalVariables.getMessageMap();
        assertTrue(errorMap.containsMessageKey(KeyConstants.ERROR_INVALID_UNIT));
    }
    /*
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
        committee.setCommitteeTypeCode(CommitteeType.IRB_TYPE_CODE);
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
     */
        
}
