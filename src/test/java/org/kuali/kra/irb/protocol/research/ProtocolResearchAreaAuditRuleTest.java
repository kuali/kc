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
package org.kuali.kra.irb.protocol.research;

import static org.kuali.rice.kns.util.KNSGlobalVariables.getAuditErrorMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;

/*
 * WARNING: Please do not move this test.  It does not like to pass with the entire suite unless it is further up the testing chain.
 *          If moved it will complain that its Transaction status is rolled back.  I'm not sure why.
 */
public class ProtocolResearchAreaAuditRuleTest extends ProtocolRuleTestBase {
    
    private ProtocolResearchAreaAuditRule auditRule;

    /**
     * @see org.kuali.kra.irb.test.ProtocolRuleTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        auditRule = new ProtocolResearchAreaAuditRule();
    }
    
    /**
     * @see org.kuali.kra.irb.test.ProtocolRuleTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        auditRule = null;
        super.tearDown();
    }
    
    
    /**
     * This method is to test the Research Area audit rule.  That is, a protocol cannot be submitted without specifying at least one area of research.
     * Since this only runs at submission, any default document will throw this error at submission.
     * 
     * @throws Exception
     */
    @Test
    public void testMissingResearchArea() throws Exception {
        ProtocolDocument document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
        
        assertFalse("Audit Rule should produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertEquals(1, getAuditErrorMap().size());

    }

}
