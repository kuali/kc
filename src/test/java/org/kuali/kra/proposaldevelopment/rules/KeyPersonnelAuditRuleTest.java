/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.core.util.GlobalVariables.getAuditErrorMap;
import static org.kuali.core.util.GlobalVariables.setAuditErrorMap;
import static org.kuali.core.util.GlobalVariables.setUserSession;
import static org.kuali.kra.test.fixtures.ProposalDevelopmentDocumentFixture.NORMAL_DOCUMENT;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.INCOMPLETE_CERTIFICATIONS;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.INVESTIGATOR_UNDER_ZERO;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.INVESTIGATOR_OVER_ONE_HUNDRED;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.INVESTIGATOR_UNIT_OVER_ONE_HUNDRED;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.INVESTIGATOR_UNIT_UNDER_ZERO;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.INVESTIGATOR_UNIT_NOT_TO_ONE_HUNDRED;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.PRINCIPAL_INVESTIGATOR;


import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;

/**
 * Class to test Key Personnel Audit Mode Rules. These rules are executed when audit mode becomes activated.
 * 
 * @see org.kuali.kra.proposaldevelopment.rules.KeyPersonnelAuditModeRule
 */
public class KeyPersonnelAuditRuleTest extends KraTestBase {
    public static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeyPersonnelAuditRuleTest.class);

    private KeyPersonnelAuditRule auditRule;
    private ProposalDevelopmentDocument document;

    /**
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setUserSession(new UserSession("quickstart"));
        setAuditErrorMap(new HashMap());
        auditRule = new KeyPersonnelAuditRule();
        document = NORMAL_DOCUMENT.getDocument();
    }
    
    /**
     * @see org.kuali.kra.KraTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        setUserSession(null);
        setAuditErrorMap(null);
        auditRule = null;
        super.tearDown();
    }
    
    /**
     * A document with a PI and Unanswered Certification Questions should produce audit errros
     * 
     * @throws Exception for whatever reason
     */
    @Test
    public void incompleteYesNoQuestions() throws Exception {
        ProposalPerson person1 = INCOMPLETE_CERTIFICATIONS.getPerson();
        INCOMPLETE_CERTIFICATIONS.populatePerson(document, person1);
        document.addProposalPerson(person1);
        
        assertFalse("Audit Rule should produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertEquals(1, getAuditErrorMap().size());

    }
 
    /**
     * A document with a PI and completely answered Certification Questions shouldn't produce audit errros
     * 
     * @throws Exception for whatever reason
     */
    @Test
    public void completeYesNoQuestions() throws Exception {
        ProposalPerson person1 = PRINCIPAL_INVESTIGATOR.getPerson();
        PRINCIPAL_INVESTIGATOR.populatePerson(document, person1);
        document.addProposalPerson(person1);
        
        assertTrue("Audit Rule shouldn't produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertEquals(0, getAuditErrorMap().size());

    }
    
    /**
     * A <code>{@link ProposalDevelopmentDocument}</code> instance can only have one Proposal Investigator. Test that the rule applies.<br/> 
     * <br/>
     * This tests whether 0 Principle Investigators will work. This test should yield a failed rule.
     * 
     * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
     * @see org.kuali.kra.proposaldevelopment.rules.KeyPersonnelAuditRule
     */
    @Test
    public void validProposalInvestigatorCount() throws Exception {
        assertFalse("Audit Rule should produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertEquals(1, getAuditErrorMap().size());
    }

    /**
     * A <code>{@link ProposalDevelopmentDocument}</code> instance can only have one Proposal Investigator. Test that the rule applies.<br/> 
     * <br/>
     * This tests whether 2 or more Principle Investigators will work. This test should yield a failed rule.
     * 
     * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
     * @see org.kuali.kra.proposaldevelopment.rules.KeyPersonnelAuditRule
     */
    @Test
    public void validProposalInvestigatorUpperBound()throws Exception {
        ProposalPerson person1 = PRINCIPAL_INVESTIGATOR.getPerson();
        PRINCIPAL_INVESTIGATOR.populatePerson(document, person1);
        document.addProposalPerson(person1);
        ProposalPerson person2 = PRINCIPAL_INVESTIGATOR.getPerson();
        document.addProposalPerson(person2);
        
        assertFalse("Audit Rule should produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertEquals(1, getAuditErrorMap().size());
    }
    
    /**
     * A <code>{@link ProposalDevelopmentDocument}</code> instance can only have one Proposal Investigator. Test that the rule applies.<br/> 
     * <br/>
     * This tests whether exactly one Principle Investigator will work. This test should yield a passed rule.
     * 
     * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
     * @see org.kuali.kra.proposaldevelopment.rules.KeyPersonnelAuditRule
     */
    @Test
    public void validProposalInvestigatorNormal() throws Exception {
        LOG.info("Starting validProposalInvetigatorNormal");
        ProposalPerson person1 = INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED.getPerson();
        INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED.populatePerson(document, person1);
        document.addProposalPerson(person1);
        assertTrue("Audit Rule shouldn't produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertEquals(0, getAuditErrorMap().size());
        LOG.info("Ending validProposalInvetigatorNormal");
    }

    @Test
    public void invalidProposalInvestigatorFail() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNIT_NOT_TO_ONE_HUNDRED.getPerson();
        document.addProposalPerson(person);
        assertFalse("Audit Rule should produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertFalse(getAuditErrorMap().size() < 1);
    }
    
    @Test
    public void invalidProposalInvestigatorOverHundred() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_OVER_ONE_HUNDRED.getPerson();
        document.addProposalPerson(person);
        assertFalse("Audit Rule should produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertFalse(getAuditErrorMap().size() < 1);
    }

    @Test
    public void invalidProposalInvestigatorUnderZero() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNDER_ZERO.getPerson();
        document.addProposalPerson(person);
        assertFalse("Audit Rule should produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertFalse(getAuditErrorMap().size() < 1);
    }

    @Test
    public void invalidProposalInvestigatorUnitsUnderZero() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNIT_UNDER_ZERO.getPerson();
        document.addProposalPerson(person);
        assertFalse("Audit Rule should produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertFalse(getAuditErrorMap().size() < 1);
    }

    @Test
    public void invalidProposalInvestigatorUnitsOverHundred() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNIT_OVER_ONE_HUNDRED.getPerson();
        document.addProposalPerson(person);
        assertFalse("Audit Rule should produce audit errors", auditRule.processRunAuditBusinessRules(document));
        assertFalse(getAuditErrorMap().size() < 1);
    }

    /**
     * Locate the <code>{@link KeyPersonnelService}</code>
     * 
     * @return KeyPersonnelService
     * @see KraTestBase#getService(Class)
     */
    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }
}

