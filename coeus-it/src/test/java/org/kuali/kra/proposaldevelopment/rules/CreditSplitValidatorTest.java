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
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplitValidator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.UserSession;

import java.util.HashMap;

import static org.kuali.kra.test.fixtures.ProposalDevelopmentDocumentFixture.NORMAL_DOCUMENT;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.*;
import static org.junit.Assert.*;
/**
 * Class to test use cases of <code>{@link org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplitValidator}</code>
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.10 $
 */
public class CreditSplitValidatorTest extends KcIntegrationTestBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CreditSplitValidatorTest.class);
    private CreditSplitValidator validator;
    private ProposalDevelopmentDocument document;
    
    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setAuditErrorMap(new HashMap());
        validator = new CreditSplitValidator();
        document = NORMAL_DOCUMENT.getDocument();
    }
    
    @After
    public void tearDown() throws Exception {
    }

    /**
     * A <code>{@link ProposalDevelopmentDocument}</code> instance can only have one Proposal Investigator. Test that the rule applies.
     *
     * This tests whether exactly one Principle Investigator will work. This test should yield a passed rule.
     * 
     * @see org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument
     * @see org.kuali.coeus.propdev.impl.person.KeyPersonnelAuditRule
     */
    @Test
    public void validProposalInvestigatorNormal() throws Exception {
        LOG.info("Starting validProposalInvetigatorNormal");
        ProposalPerson person1 = INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED.getPerson();
        INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED.populatePerson(document, person1);
        document.getDevelopmentProposal().addProposalPerson(person1);
        assertTrue("Audit Rule shouldn't produce audit errors", validator.validate(document));
        assertEquals(0, GlobalVariables.getAuditErrorMap().size());
        LOG.info("Ending validProposalInvetigatorNormal");
    }

    @Test
    public void invalidProposalInvestigatorFail() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNIT_NOT_TO_ONE_HUNDRED.getPerson();
        document.getDevelopmentProposal().addProposalPerson(person);
        INVESTIGATOR_UNIT_NOT_TO_ONE_HUNDRED.populatePerson(document, person);
        assertFalse("Audit Rule should produce audit errors", validator.validate(document));
        assertFalse(GlobalVariables.getAuditErrorMap().size() < 1);
    }
    
    @Test
    public void invalidProposalInvestigatorOverHundred() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_OVER_ONE_HUNDRED.getPerson();
        document.getDevelopmentProposal().addProposalPerson(person);
        INVESTIGATOR_OVER_ONE_HUNDRED.populatePerson(document, person);
        assertFalse("Audit Rule should produce audit errors", validator.validate(document));
        assertFalse(GlobalVariables.getAuditErrorMap().size() < 1);
    }

    @Test
    public void invalidProposalInvestigatorUnderZero() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNDER_ZERO.getPerson();
        document.getDevelopmentProposal().addProposalPerson(person);
        INVESTIGATOR_UNDER_ZERO.populatePerson(document, person);
        assertFalse("Audit Rule should produce audit errors", validator.validate(document));
        assertFalse(GlobalVariables.getAuditErrorMap().size() < 1);
    }

    @Test
    public void invalidProposalInvestigatorUnitsUnderZero() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNIT_UNDER_ZERO.getPerson();
        document.getDevelopmentProposal().addProposalPerson(person);
        INVESTIGATOR_UNIT_UNDER_ZERO.populatePerson(document, person);
        assertFalse("Audit Rule should produce audit errors", validator.validate(document));
        assertFalse(GlobalVariables.getAuditErrorMap().size() < 1);
    }

    @Test
    public void invalidProposalInvestigatorUnitsOverHundred() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNIT_OVER_ONE_HUNDRED.getPerson();
        document.getDevelopmentProposal().addProposalPerson(person);
        INVESTIGATOR_UNIT_OVER_ONE_HUNDRED.populatePerson(document, person);
        assertFalse("Audit Rule should produce audit errors", validator.validate(document));
        assertFalse(GlobalVariables.getAuditErrorMap().size() < 1);
    }
}
