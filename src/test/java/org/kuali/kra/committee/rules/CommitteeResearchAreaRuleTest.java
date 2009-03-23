/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import edu.iu.uis.eden.exception.WorkflowException;

/**
 * Test the Committee Research Area Rules.
 */
@PerSuiteUnitTestData(
    @UnitTestData(
        sqlFiles = {
            @UnitTestFile(filename = "classpath:sql/dml/load_committee_type.sql", delimiter = ";")
           ,@UnitTestFile(filename = "classpath:sql/dml/load_protocol_review_type.sql", delimiter = ";")
           ,@UnitTestFile(filename = "classpath:sql/dml/load_research_areas.sql", delimiter = ";")
        }
    )
)
public class CommitteeResearchAreaRuleTest extends KraTestBase {
    
    private static final String VALID_RESEARCH_AREA_CODE = "01.0101";
    
    protected DocumentService documentService = null;
    private CommitteeResearchAreaRule rule;
   
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("aslusar"));
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KNSServiceLocator.getDocumentService();
        rule = new CommitteeResearchAreaRule();
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        GlobalVariables.setErrorMap(null);
        GlobalVariables.setAuditErrorMap(null);
        documentService = null;
        super.tearDown();
    }
    
    /**
     * Test adding a valid research area to a committee
     * where everything is valid.  
     * @throws Exception
     */
    @Test
    public void testAddResearchAreaOK() throws Exception {
        CommitteeDocument document = getNewCommitteeDocument();
        boolean rulesPassed = rule.processAddCommitteeResearchAreaRules(document.getCommittee(), VALID_RESEARCH_AREA_CODE);
        assertTrue(rulesPassed);
    }
    
    /**
     * Test an invalid Research Area code.
     * @throws ExceptionErrorMap
     */
    @Test
    public void testAddResearchAreaInvalid() throws Exception {
        CommitteeDocument document = getNewCommitteeDocument();

        boolean rulesPassed = rule.processAddCommitteeResearchAreaRules(document.getCommittee(), "XXX");
        assertFalse(rulesPassed);
        
        /*
         * There should be one error and it must be an invalid research area.
         */
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(1, errorMap.getErrorCount());
        assertTrue(errorMap.containsKey(Constants.COMMITTEE_RESEARCH_AREAS_PROPERTY_KEY));
        assertTrue(errorMap.containsMessageKey(KeyConstants.ERROR_RESEARCH_AREA_INVALID));
    }
    
    /**
     * Test adding a duplicate Research Area. 
     * @throws ExceptionErrorMap
     */
    @Test
    public void testAddResearchAreaDuplicate() throws Exception {
        CommitteeDocument document = getNewCommitteeDocument();
        addResearchArea(document.getCommittee());

        boolean rulesPassed = rule.processAddCommitteeResearchAreaRules(document.getCommittee(), VALID_RESEARCH_AREA_CODE);
        assertFalse(rulesPassed);
        
        /*
         * There should be one error and it must be a duplicate research area.
         */
        ErrorMap errorMap = GlobalVariables.getErrorMap();
        assertEquals(1, errorMap.getErrorCount());
        assertTrue(errorMap.containsKey(Constants.COMMITTEE_RESEARCH_AREAS_PROPERTY_KEY));
        assertTrue(errorMap.containsMessageKey(KeyConstants.ERROR_RESEARCH_AREA_DUPLICATE));
    }
    
    /**
     * Add a valid research area to the committee.
     * @param committee
     */
    private void addResearchArea(Committee committee) {
        CommitteeResearchArea ra = new CommitteeResearchArea();
        ra.setResearchAreaCode(VALID_RESEARCH_AREA_CODE);
        committee.getCommitteeResearchAreas().add(ra);
    }
    
    /**
     * Get a new Committee Document.
     * 
     * @return a new Committee Document.
     * @throws WorkflowException
     */
    protected CommitteeDocument getNewCommitteeDocument() throws WorkflowException {
        return (CommitteeDocument) documentService.getNewDocument("CommitteeDocument");
    }
}