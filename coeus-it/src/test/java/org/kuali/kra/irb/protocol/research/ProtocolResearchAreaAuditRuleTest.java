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
package org.kuali.kra.irb.protocol.research;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;

import static org.kuali.rice.krad.util.GlobalVariables.getAuditErrorMap;
import static org.junit.Assert.*;
/*
 * WARNING: Please do not move this test.  It does not like to pass with the entire suite unless it is further up the testing chain.
 *          If moved it will complain that its Transaction status is rolled back.  I'm not sure why.
 */
public class ProtocolResearchAreaAuditRuleTest extends ProtocolRuleTestBase {
    
    private ProtocolResearchAreaAuditRule auditRule;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        auditRule = new ProtocolResearchAreaAuditRule();
    }
    
    @Override
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
