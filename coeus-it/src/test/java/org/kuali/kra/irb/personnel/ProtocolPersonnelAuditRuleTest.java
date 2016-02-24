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
package org.kuali.kra.irb.personnel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolRuleTestBase;

import static org.kuali.rice.krad.util.GlobalVariables.getAuditErrorMap;
import static org.junit.Assert.*;
/**
 * This class is to test protocol personnel audit rule error message
 */
public class ProtocolPersonnelAuditRuleTest extends ProtocolRuleTestBase {
    private ProtocolPersonnelAuditRule auditRule;
    private ProtocolDocument document;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        auditRule = new ProtocolPersonnelAuditRule();
        document = getNewProtocolDocument();
        setProtocolRequiredFields(document);
    }
    
    @Override
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

