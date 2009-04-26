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
package org.kuali.kra.irb.rules;

import static org.kuali.rice.kns.util.GlobalVariables.getAuditErrorMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.kra.irb.personnel.ProtocolPersonnelAuditRule;
/**
 * This class is to test protocol personnel audit rule error message
 */
public class ProtocolPersonnelAuditRuleTest extends ProtocolRuleTestBase {
    private ProtocolPersonnelAuditRule auditRule;
    private ProtocolDocument document;

    /**
     * @see org.kuali.kra.irb.rules.ProtocolRuleTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        auditRule = new ProtocolPersonnelAuditRule();
        document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
    }
    
    /**
     * @see org.kuali.kra.irb.rules.ProtocolRuleTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        auditRule = null;
        super.tearDown();
    }
    
    
    /**
     * This method is to test protocol personnel audit rule.
     * For each student investigator, there should be a faculty supervisor assigned.
     * Affiliation type is used to tag a person as Student Investigator / Faculty supervisor.
     * Change the PI affiliation to Student Investigator and it should generate audit rule
     * error message.
     * @throws Exception
     */
    @Test
    public void invalidInvestigatorMatch() throws Exception {
        document.getProtocol().getProtocolPerson(0).setAffiliationTypeCode(Constants.AFFILIATION_STUDENT_INVESTIGATOR_TYPE);
        
        assertFalse("Audit Rule should produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertEquals(1, getAuditErrorMap().size());

    }

}

