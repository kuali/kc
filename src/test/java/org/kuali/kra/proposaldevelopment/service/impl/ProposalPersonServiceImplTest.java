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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

@PerSuiteUnitTestData(
        @UnitTestData(
            sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_proposalpersontest_data.sql", delimiter = ";")
            }
            )
            )

public class ProposalPersonServiceImplTest extends KcUnitTestBase{
    
    private ProposalPersonService service;
    private ProposalPersonServiceImpl service2;
    private static final String PROPOSAL_NUMBER = "12345";
    private static final String TESTER_B_NAME = "testerB";
    private static final Integer TESTER_B_ID = 2;
    private static final String TEST_B_ROLE = "tst2";
    private static final String TESTER_A_NAME = "testerA";
    private static final Integer TESTER_A_ID = 1;
    private static final String TEST_A_ROLE = "tst";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        service = KraServiceLocator.getService(ProposalPersonService.class);
        service2 = (ProposalPersonServiceImpl)KraServiceLocator.getService(ProposalPersonService.class);
        service2.setKcPersonService(KraServiceLocator.getService(KcPersonService.class));
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        service = null;
        service2 = null;
        super.tearDown();
    }
    
    @Test
    public void testCorrectClass(){
        assertTrue("Should be the same", service.getClass().equals(ProposalPersonServiceImpl.class));
    }

    @Test
    public void testGetPersonName() {
        DevelopmentProposal dp = new DevelopmentProposal();
        dp.setProposalNumber(PROPOSAL_NUMBER);
        ProposalDevelopmentDocument doc = new ProposalDevelopmentDocument();
        doc.setDevelopmentProposal(dp);
        String personName = service.getPersonName(doc, String.valueOf(TESTER_B_ID));
        assertTrue("Expected name to be '" + TESTER_B_NAME + "' but was '" + personName + "'", TESTER_B_NAME.equals(personName));
        //fail("Not yet implemented");
    }

    @Test
    public void testGetPerson() {
        KcPerson person = service.getPerson("quickstart");
        assertTrue("Expected name to be 'quickstart' but was '" + person.getUserName() + "'", "quickstart".equals(person.getUserName()));
    }

    @Test
    public void testGetProposalPersonById() {
        //public ProposalPerson getProposalPersonById(String proposalNumber, Integer proposalPersonNumber) {
        ProposalPerson person = service.getProposalPersonById(PROPOSAL_NUMBER, TESTER_A_ID);
        if(person != null){
            assertTrue("Expected name to be '" + TESTER_A_NAME + "' but was '" + person.getFullName() + "'", 
                    TESTER_A_NAME.equals(person.getFullName()));
        }else{
            assertTrue("Null person object returned", false);
        }
    }

    @Test
    public void testGetProposalKeyPersonnel() {
        //public List<ProposalPerson> getProposalKeyPersonnel(String proposalNumber, String roleCode) {
        List<ProposalPerson> personList = service.getProposalKeyPersonnel(PROPOSAL_NUMBER, TEST_A_ROLE);
        Iterator<ProposalPerson> i = personList.iterator();
        boolean foundRightPerson = false;
        while(i.hasNext()){
            ProposalPerson person = i.next();
            if(person.getFullName().equals(TESTER_A_NAME)){
                foundRightPerson = true;
            }
        }
        assertTrue("Expected to find '" + TESTER_A_NAME + "', Number of people found: " + personList.size(), foundRightPerson );
    }

}
