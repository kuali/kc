/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;

import static org.kuali.kra.test.fixtures.ProposalDevelopmentDocumentFixture.NORMAL_DOCUMENT;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.*;
import static org.junit.Assert.*;
/**
 * Class to test use cases of <code>{@link CreditSplitValidator}</code>
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
        KNSGlobalVariables.setAuditErrorMap(new HashMap());
        validator = new CreditSplitValidator();
        document = NORMAL_DOCUMENT.getDocument();
    }
    
    @After
    public void tearDown() throws Exception {
    }

    /**
     * A <code>{@link ProposalDevelopmentDocument}</code> instance can only have one Proposal Investigator. Test that the rule applies.<br/> 
     * <br/>
     * This tests whether exactly one Principle Investigator will work. This test should yield a passed rule.
     * 
     * @see org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument
     * @see org.kuali.kra.proposaldevelopment.rules.KeyPersonnelAuditRule
     */
    @Test
    public void validProposalInvestigatorNormal() throws Exception {
        LOG.info("Starting validProposalInvetigatorNormal");
        ProposalPerson person1 = INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED.getPerson();
        INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED.populatePerson(document, person1);
        document.getDevelopmentProposal().addProposalPerson(person1);
        assertTrue("Audit Rule shouldn't produce audit errors", validator.validate(document));
        assertEquals(0, KNSGlobalVariables.getAuditErrorMap().size());
        LOG.info("Ending validProposalInvetigatorNormal");
    }

    @Test
    public void invalidProposalInvestigatorFail() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNIT_NOT_TO_ONE_HUNDRED.getPerson();
        document.getDevelopmentProposal().addProposalPerson(person);
        INVESTIGATOR_UNIT_NOT_TO_ONE_HUNDRED.populatePerson(document, person);
        assertFalse("Audit Rule should produce audit errors", validator.validate(document));
        assertFalse(KNSGlobalVariables.getAuditErrorMap().size() < 1);
    }
    
    @Test
    public void invalidProposalInvestigatorOverHundred() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_OVER_ONE_HUNDRED.getPerson();
        document.getDevelopmentProposal().addProposalPerson(person);
        INVESTIGATOR_OVER_ONE_HUNDRED.populatePerson(document, person);
        assertFalse("Audit Rule should produce audit errors", validator.validate(document));
        assertFalse(KNSGlobalVariables.getAuditErrorMap().size() < 1);
    }

    @Test
    public void invalidProposalInvestigatorUnderZero() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNDER_ZERO.getPerson();
        document.getDevelopmentProposal().addProposalPerson(person);
        INVESTIGATOR_UNDER_ZERO.populatePerson(document, person);
        assertFalse("Audit Rule should produce audit errors", validator.validate(document));
        assertFalse(KNSGlobalVariables.getAuditErrorMap().size() < 1);
    }

    @Test
    public void invalidProposalInvestigatorUnitsUnderZero() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNIT_UNDER_ZERO.getPerson();
        document.getDevelopmentProposal().addProposalPerson(person);
        INVESTIGATOR_UNIT_UNDER_ZERO.populatePerson(document, person);
        assertFalse("Audit Rule should produce audit errors", validator.validate(document));
        assertFalse(KNSGlobalVariables.getAuditErrorMap().size() < 1);
    }

    @Test
    public void invalidProposalInvestigatorUnitsOverHundred() throws Exception {
        ProposalDevelopmentDocument document = NORMAL_DOCUMENT.getDocument();
        ProposalPerson person = INVESTIGATOR_UNIT_OVER_ONE_HUNDRED.getPerson();
        document.getDevelopmentProposal().addProposalPerson(person);
        INVESTIGATOR_UNIT_OVER_ONE_HUNDRED.populatePerson(document, person);
        assertFalse("Audit Rule should produce audit errors", validator.validate(document));
        assertFalse(KNSGlobalVariables.getAuditErrorMap().size() < 1);
    }
}
