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
package org.kuali.kra.institutionalproposal.contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.contacts.ContactRoleFixtureFactory;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KcPersonFixtureFactory;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 * This class tests AwardCreditSplitBean
 */
public class InstitutionalProposalCreditSplitBeanTest {
    private static final double DELTA = 0.1;
    
    private InstitutionalProposal institutionalProposal;
    private InstitutionalProposalCreditSplitBean bean;
    private static final KualiDecimal MAX_VALUE = new KualiDecimal("100.00");
    private static final KualiDecimal ZERO_VALUE = new KualiDecimal("0.00");
    private static final String ERROR_MSG_PATTERN = "Error on Person : %s Role: %s: %s";

    @SuppressWarnings("serial")
    @Before
    public void setUp() {
        prepareInstitutionalProposal();
        bean = new InstitutionalProposalCreditSplitBean((InstitutionalProposalDocument)null) {
            InstitutionalProposal getInstitutionalProposal() {
                return institutionalProposal;
            }
            Collection<InvestigatorCreditType> loadInvestigatorCreditTypes() {
                return getMockCreditTypes();
            }            
        };        
    }
    
    @After
    public void tearDown() {
        bean = null;
        institutionalProposal = null;
    }
    
    @Test
    public void testCalculatingCreditSplitTotals_PersonTotals_NonZero() {
        Map<String, KualiDecimal> expectedTotals = initializeCreditSplitTotalsForAllPersons();
        Map<String, Map<String, KualiDecimal>> results = bean.calculateCreditSplitTotals();
        Map<String, KualiDecimal> personCreditSplitTotalsByCreditSplitType = results.get(InstitutionalProposalCreditSplitBean.PERSON_TOTALS_KEY);
        for(InvestigatorCreditType creditSplitType: bean.loadInvestigatorCreditTypes()) {
            String creditTypeCode = creditSplitType.getInvCreditTypeCode();
            KualiDecimal total = personCreditSplitTotalsByCreditSplitType.get(creditTypeCode);
            KualiDecimal expectedTotal = expectedTotals.get(creditTypeCode);
            Assert.assertEquals(createInvalidTotalMessage(creditSplitType.getDescription()), expectedTotal.doubleValue(), total.doubleValue(), DELTA);
        }        
    }
    
    @Test
    public void testCalculatingCreditSplitTotals_PersonTotals_Zero() {
        Map<String, Map<String, KualiDecimal>> results = bean.calculateCreditSplitTotals();
        Map<String, KualiDecimal> personCreditSplitTotalsByCreditSplitType = results.get(InstitutionalProposalCreditSplitBean.PERSON_TOTALS_KEY);
        for(String creditSplitType: personCreditSplitTotalsByCreditSplitType.keySet()) {
            KualiDecimal total = personCreditSplitTotalsByCreditSplitType.get(creditSplitType);
            Assert.assertEquals(0.0, total.doubleValue(), DELTA);
        }        
    }

    @Test
    public void testCalculatingCreditSplitTotals_PersonUnitTotals__NonZeroTotals_AllPersonsHaveUnits() {
        verifyPersonUnitTotals();
    }

    @Test
    public void testCalculatingCreditSplitTotals_PersonUnitTotals_NonZeroTotals_KeyPersonHasNoUnits() {
        clearKeyPersonUnits();
        verifyPersonUnitTotals();
    }

    @Test
    public void testCalculatingCreditSplitTotals_PersonUnitTotals_Zero() {
        Map<String, Map<String, KualiDecimal>> results = bean.calculateCreditSplitTotals();
        for(InstitutionalProposalPerson ap: institutionalProposal.getProjectPersons()) {
            Map<String, KualiDecimal> personUnitCreditSplitTotalsByCreditSplitType = results.get(ap.getFullName());
            for(String creditSplitType: personUnitCreditSplitTotalsByCreditSplitType.keySet()) {
                KualiDecimal total = personUnitCreditSplitTotalsByCreditSplitType.get(creditSplitType);
                Assert.assertEquals(0.0, total.doubleValue(), DELTA);
            }
        }
    }

    /**
     * @return
     */
    private void addInstitutionalProposalPerson(Long id, String personId, String fullName, ContactRole contactRole) {
        KcPerson person = KcPersonFixtureFactory.createKcPerson(personId);
        person.setPersonId(id.toString());
        InstitutionalProposalPerson ap = new InstitutionalProposalPerson(person, contactRole);
        ap.setFullName(fullName);
        ap.setInstitutionalProposalContactId(id);
        addUnit(ap, "Unit A", "100");
        addUnit(ap, "Unit B", "200");        
        addUnit(ap, "Unit C", "300");
        institutionalProposal.add(ap);
    }
    
    private void addUnit(InstitutionalProposalPerson ap, String unitName, String unitNumber) {
        Unit unit = new Unit();
        unit.setUnitName(unitName);
        unit.setUnitNumber(unitNumber);
        InstitutionalProposalPersonUnit apu = new InstitutionalProposalPersonUnit(ap, unit, false);
        ap.add(apu);
    }

    private void clearKeyPersonUnits() {
        InstitutionalProposalPerson keyPerson = institutionalProposal.getProjectPersons().get(2);
        if(!keyPerson.getContactRoleCode().equals(ContactRole.KEY_PERSON_CODE)) {
            Assert.fail("Test should have set 3rd person to be KeyPerson");
        }
        keyPerson.getUnits().clear();
    }

    /**
     * This method creates an error message
     * @param creditSplitType
     * @return
     */
    private String createInvalidTotalMessage(String creditSplitType) {
        return String.format("Invalid total for %s", creditSplitType);
    }
    
    private Collection<InvestigatorCreditType> getMockCreditTypes() {
        Collection<InvestigatorCreditType> types = new ArrayList<InvestigatorCreditType>();
        types.add(new InvestigatorCreditType("0", "Recognition"));
        types.add(new InvestigatorCreditType("1", "Responsibility"));
        types.add(new InvestigatorCreditType("2", "Space"));
        types.add(new InvestigatorCreditType("3", "Financial"));
        return types;
    }

    /**
     * This method...
     * @return
     */
    private Map<String, KualiDecimal> initializeCreditSplitTotalsForAllPersons() {
        Map<String, KualiDecimal> expectedTotals = new HashMap<String, KualiDecimal>();

        KualiDecimal creditSplitValue = MAX_VALUE.divide(new KualiDecimal(institutionalProposal.getProjectPersons().size()));
        for(InvestigatorCreditType creditType: bean.loadInvestigatorCreditTypes()) {
            for(InstitutionalProposalPerson p: institutionalProposal.getProjectPersons()) {
                InstitutionalProposalPersonCreditSplit personCreditSplit = new InstitutionalProposalPersonCreditSplit(creditType, creditSplitValue);
                p.add(personCreditSplit);
            }
            expectedTotals.put(creditType.getInvCreditTypeCode(), MAX_VALUE);
        }
        return expectedTotals;
    }

    /**
     * This method...
     * @return
     */
    private Map<String, Map<String, KualiDecimal>> initializeCreditSplitTotalsForEachPersonUnit() {
        Map<String, Map<String, KualiDecimal>> personMapOfUnitCreditSplitTotals = new HashMap<String, Map<String, KualiDecimal>>(); 
        Map<String, KualiDecimal> expectedPersonUnitCreditSplitTotals = new HashMap<String, KualiDecimal>();

        Collection<InvestigatorCreditType> creditSplitTypes = bean.loadInvestigatorCreditTypes();
        for(InstitutionalProposalPerson p: institutionalProposal.getProjectPersons()) {
            for(InvestigatorCreditType creditType: creditSplitTypes) {
                List<InstitutionalProposalPersonUnit> units = p.getUnits();
                if(units.size() > 0) {
                    KualiDecimal creditSplitValue = MAX_VALUE.divide(new KualiDecimal(units.size()));
                    for(InstitutionalProposalPersonUnit apu: units) {
                        apu.add(new InstitutionalProposalPersonUnitCreditSplit(creditType, creditSplitValue));
                    }
                }
                expectedPersonUnitCreditSplitTotals.put(creditType.getInvCreditTypeCode(), MAX_VALUE);
            }
            personMapOfUnitCreditSplitTotals.put(p.getFullName(), expectedPersonUnitCreditSplitTotals);
        }
        return personMapOfUnitCreditSplitTotals;
    }
    
    private void prepareInstitutionalProposal() {
        institutionalProposal = new InstitutionalProposal();
        addInstitutionalProposalPerson(1L, "1001", "Person A", ContactRoleFixtureFactory.MOCK_PI);
        addInstitutionalProposalPerson(2L, "1002", "Person B", ContactRoleFixtureFactory.MOCK_COI);
        addInstitutionalProposalPerson(3L, "1003", "Person C", ContactRoleFixtureFactory.MOCK_KEY_PERSON);
    }

    private void verifyPersonUnitTotals() {
        Map<String, Map<String, KualiDecimal>> expectedPersonUnitCreditSplitTotals = initializeCreditSplitTotalsForEachPersonUnit();
        Map<String, Map<String, KualiDecimal>> results = bean.calculateCreditSplitTotals();
        for(InstitutionalProposalPerson p: institutionalProposal.getProjectPersons()) {
            String fullName = p.getFullName();
            Map<String, KualiDecimal> personUnitCreditSplitTotalsByCreditSplitType = results.get(fullName);
            for(InvestigatorCreditType creditSplitType: bean.loadInvestigatorCreditTypes()) {
                String invCreditTypeCode = creditSplitType.getInvCreditTypeCode();
                KualiDecimal total = personUnitCreditSplitTotalsByCreditSplitType.get(invCreditTypeCode);
                KualiDecimal expectedValue = expectedPersonUnitCreditSplitTotals.get(fullName).get(invCreditTypeCode);
                Assert.assertEquals(createErrorMessage(p, creditSplitType), expectedValue.doubleValue(), total.doubleValue(), DELTA);
            }
        }
    }

    private String createErrorMessage(InstitutionalProposalPerson p, InvestigatorCreditType creditSplitType) {
        return String.format(ERROR_MSG_PATTERN, p.getPersonId(), p.roleCode, createInvalidTotalMessage(creditSplitType.getDescription()));
    }
}
