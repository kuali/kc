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
package org.kuali.kra.proposaldevelopment.bo;


import static java.util.Collections.sort;
import static org.junit.Assert.*;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.BRYAN_CO_INVESTIGATOR;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.PHILIP_CO_INVESTIGATOR;
import static org.kuali.kra.test.fixtures.ProposalPersonFixture.ANDY_KEY_PERSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.kuali.kra.KraTestBase;

import org.kuali.kra.proposaldevelopment.bo.ProposalPersonComparator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;


/**
 * Test scenarios for sorting <code>{@link ProposalPerson}</code> instances by values in the
 * <code>{@link ProposalPersonRole}</code>
 * 
 * @see org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole
 */
public class ProposalPersonComparatorTest extends KraTestBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalPersonComparatorTest.class);
    
    
    private Comparator<ProposalPerson> comparator;
    
    /**
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        comparator = new ProposalPersonComparator();
    }
    
    /**
     * 
     * @see org.kuali.kra.KraTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Compares a Principal Investigator with a Co-Investigator. The Principal Investigator comes first.
     * 
     */
    @Test
    public void principleInvestigator() {
        final List<ProposalPerson> sortedList = new ArrayList<ProposalPerson>();
        final ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
        
        final ProposalPerson person1 = INVESTIGATOR_SPLIT_ADDS_TO_ONE_HUNDRED.getPerson();
        getKeyPersonnelService().populateProposalPerson(person1, document);
        
        final ProposalPerson person2 = PHILIP_CO_INVESTIGATOR.getPerson();
        getKeyPersonnelService().populateProposalPerson(person2, document);

        final ProposalPerson person3 = BRYAN_CO_INVESTIGATOR.getPerson();
        getKeyPersonnelService().populateProposalPerson(person2, document);

        final ProposalPerson person4 = ANDY_KEY_PERSON.getPerson();
        getKeyPersonnelService().populateProposalPerson(person2, document);

        sortedList.add(person1);
        sortedList.add(person2);
        sortedList.add(person3);
        sortedList.add(person4);

        sort(sortedList, comparator);
        
        for (ProposalPerson person: sortedList) {
            LOG.info("person full Name = " + person.getFullName());
        }
        
        // This is the order we expect to find them in by personId
        LOG.info(" person1 = " + sortedList.get(0).getPersonId());
        LOG.info(" person2 = " + sortedList.get(1).getPersonId());
        LOG.info(" person3 = " + sortedList.get(2).getPersonId());
        LOG.info(" person4 = " + sortedList.get(3).getPersonId());
        assertEquals("000000003", sortedList.get(0).getPersonId());
        assertEquals("000000002", sortedList.get(1).getPersonId());
        assertEquals("000000006", sortedList.get(2).getPersonId());
        assertEquals("000000005", sortedList.get(3).getPersonId());
    }
    
    /**
     * Locate injected <code>{@link KeyPersonnelService}</code> service
     * 
     * @return KeyPersonnelService
     */
    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }
}
