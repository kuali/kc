/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.service;

import static org.kuali.kra.infrastructure.Constants.CO_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.PROPOSAL_PERSON_INVESTIGATOR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.KraTestBase;

import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;


/**
 * Class intended to exercise testing of units of functionality within the
 * <code>{@link KeyPersonnelService}</code>
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.2 $
 */
public class KeyPersonnelServiceTest extends KraTestBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeyPersonnelServiceTest.class);
    private ProposalDevelopmentDocument document;
    private ProposalDevelopmentDocument blankDocument;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        blankDocument = new ProposalDevelopmentDocument();
        document = new ProposalDevelopmentDocument();
        getKeyPersonnelService().populateDocument(document);
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void populateDocument() {
        getKeyPersonnelService().populateDocument(blankDocument);
        assertTrue(blankDocument.getInvestigatorCreditTypes().size() > 0);
    }

    /**
     * Verify the proposal person is given the lead unit of the document if the person is an investigator, 
     * initial credit splits exist and are setup properly
     */
    @Test
    public void populateProposalPerson_Investigator() {
        ProposalPerson person = new ProposalPerson();
        document.setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE);
        
        getKeyPersonnelService().populateProposalPerson(person, document);
        assertEquals("000001", person.getHomeUnit());
        assertTrue(person.isInvestigator());
    }

    /**
     * Verify the proposal person is given the lead unit of the document if the person is an investigator, 
     * initial credit splits exist and are setup properly
     * 
     */
    @Test
    public void populateProposalPerson_KeyPerson() {
        ProposalPerson person = new ProposalPerson();
        document.setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId("KP");
        assertNotSame("000001", person.getHomeUnit());
        assertFalse(person.isInvestigator());
    }
    
    /**
     * Test credit split totals are created properly initially
     */
    @Test
    public void calculateCreditSplitTotals_Default() {
        ProposalPerson person = new ProposalPerson();
        document.setOwnedByUnitNumber("000001");
        person.setProposalPersonRoleId(PRINCIPAL_INVESTIGATOR_ROLE);
        
        getKeyPersonnelService().populateProposalPerson(person, document);
        document.addProposalPerson(person);
        
        Map<String, Map<String,KualiDecimal>> totals = getKeyPersonnelService().calculateCreditSplitTotals(document);
        for(String key : totals.keySet()) {
            LOG.info("Key = " + key);
        }
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
