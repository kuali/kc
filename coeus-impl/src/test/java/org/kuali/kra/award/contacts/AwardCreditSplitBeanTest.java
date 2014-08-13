/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.contacts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.KcPersonFixtureFactory;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.*;

/**
 * This class tests AwardCreditSplitBean
 */
public class AwardCreditSplitBeanTest {
    private static final double DELTA = 0.1;
    
    private Award award;
    private AwardCreditSplitBean bean;
    private static final ScaleTwoDecimal MAX_VALUE = new ScaleTwoDecimal("100.00");
    private static final String ERROR_MSG_PATTERN = "Error on Person : %s Role: %s: %s";

    @SuppressWarnings("serial")
    @Before
    public void setUp() {
        prepareAward();
        bean = new AwardCreditSplitBean((AwardDocument)null) {
            Award getAward() {
                return award;
            }
            Collection<InvestigatorCreditType> loadInvestigatorCreditTypes() {
                return getMockCreditTypes();
            }            
        };        
    }
    
    @After
    public void tearDown() {
        bean = null;
        award = null;
    }
    
    @Test
    public void testCalculatingCreditSplitTotals_PersonTotals_NonZero() {
        Map<String, ScaleTwoDecimal> expectedTotals = initializeCreditSplitTotalsForAllPersons();
        Map<String, Map<String, ScaleTwoDecimal>> results = bean.calculateCreditSplitTotals();
        Map<String, ScaleTwoDecimal> personCreditSplitTotalsByCreditSplitType = results.get(AwardCreditSplitBean.PERSON_TOTALS_KEY);
        for(InvestigatorCreditType creditSplitType: bean.loadInvestigatorCreditTypes()) {
            String creditTypeCode = creditSplitType.getCode();
            ScaleTwoDecimal total = personCreditSplitTotalsByCreditSplitType.get(creditTypeCode);
            ScaleTwoDecimal expectedTotal = expectedTotals.get(creditTypeCode);
            Assert.assertEquals(createInvalidTotalMessage(creditSplitType.getDescription()), expectedTotal.doubleValue(), total.doubleValue(), DELTA);
        }        
    }
    
    @Test
    public void testCalculatingCreditSplitTotals_PersonTotals_Zero() {
        Map<String, Map<String, ScaleTwoDecimal>> results = bean.calculateCreditSplitTotals();
        Map<String, ScaleTwoDecimal> personCreditSplitTotalsByCreditSplitType = results.get(AwardCreditSplitBean.PERSON_TOTALS_KEY);
        for(String creditSplitType: personCreditSplitTotalsByCreditSplitType.keySet()) {
            ScaleTwoDecimal total = personCreditSplitTotalsByCreditSplitType.get(creditSplitType);
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
        Map<String, Map<String, ScaleTwoDecimal>> results = bean.calculateCreditSplitTotals();
        for(AwardPerson ap: award.getProjectPersons()) {
            Map<String, ScaleTwoDecimal> personUnitCreditSplitTotalsByCreditSplitType = results.get(ap.getFullName());
            for(String creditSplitType: personUnitCreditSplitTotalsByCreditSplitType.keySet()) {
                ScaleTwoDecimal total = personUnitCreditSplitTotalsByCreditSplitType.get(creditSplitType);
                Assert.assertEquals(0.0, total.doubleValue(), DELTA);
            }
        }
    }


    private void addAwardPerson(Long id, String personId, String fullName, ContactRole contactRole) {
        KcPerson person = KcPersonFixtureFactory.createKcPerson(personId);
        person.setPersonId(id.toString());
        AwardPerson ap = new AwardPerson(person, contactRole);
        ap.setFullName(fullName);
        ap.setAwardContactId(id);
        addUnit(ap, "Unit A", "100");
        addUnit(ap, "Unit B", "200");        
        addUnit(ap, "Unit C", "300");
        award.add(ap);
    }
    
    private void addUnit(AwardPerson ap, String unitName, String unitNumber) {
        Unit unit = new Unit();
        unit.setUnitName(unitName);
        unit.setUnitNumber(unitNumber);
        AwardPersonUnit apu = new AwardPersonUnit(ap, unit, false);
        ap.add(apu);
    }

    private void clearKeyPersonUnits() {
        AwardPerson keyPerson = award.getProjectPersons().get(2);
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

    private Map<String, ScaleTwoDecimal> initializeCreditSplitTotalsForAllPersons() {
        Map<String, ScaleTwoDecimal> expectedTotals = new HashMap<String, ScaleTwoDecimal>();

        ScaleTwoDecimal creditSplitValue = MAX_VALUE.divide(new ScaleTwoDecimal(award.getProjectPersons().size()));
        for(InvestigatorCreditType creditType: bean.loadInvestigatorCreditTypes()) {
            for(AwardPerson p: award.getProjectPersons()) {
                AwardPersonCreditSplit personCreditSplit = new AwardPersonCreditSplit(creditType, creditSplitValue);
                p.add(personCreditSplit);
            }
            expectedTotals.put(creditType.getCode(), MAX_VALUE);
        }
        return expectedTotals;
    }

    private Map<String, Map<String, ScaleTwoDecimal>> initializeCreditSplitTotalsForEachPersonUnit() {
        Map<String, Map<String, ScaleTwoDecimal>> personMapOfUnitCreditSplitTotals = new HashMap<String, Map<String, ScaleTwoDecimal>>();
        Map<String, ScaleTwoDecimal> expectedPersonUnitCreditSplitTotals = new HashMap<String, ScaleTwoDecimal>();

        Collection<InvestigatorCreditType> creditSplitTypes = bean.loadInvestigatorCreditTypes();
        for(AwardPerson p: award.getProjectPersons()) {
            for(InvestigatorCreditType creditType: creditSplitTypes) {
                List<AwardPersonUnit> units = p.getUnits();
                if(units.size() > 0) {
                    ScaleTwoDecimal creditSplitValue = MAX_VALUE.divide(new ScaleTwoDecimal(units.size()));
                    for(AwardPersonUnit apu: units) {
                        apu.add(new AwardPersonUnitCreditSplit(creditType, creditSplitValue));
                    }
                }
                expectedPersonUnitCreditSplitTotals.put(creditType.getCode(), MAX_VALUE);
            }
            personMapOfUnitCreditSplitTotals.put(p.getFullName(), expectedPersonUnitCreditSplitTotals);
        }
        return personMapOfUnitCreditSplitTotals;
    }
    
    private void prepareAward() {
        award = new Award();
        addAwardPerson(1L, "1001", "Person A", ContactRoleFixtureFactory.MOCK_PI);
        addAwardPerson(2L, "1002", "Person B", ContactRoleFixtureFactory.MOCK_COI);
        addAwardPerson(3L, "1003", "Person C", ContactRoleFixtureFactory.MOCK_KEY_PERSON);
    }

    private void verifyPersonUnitTotals() {
        Map<String, Map<String, ScaleTwoDecimal>> expectedPersonUnitCreditSplitTotals = initializeCreditSplitTotalsForEachPersonUnit();
        Map<String, Map<String, ScaleTwoDecimal>> results = bean.calculateCreditSplitTotals();
        for(AwardPerson p: award.getProjectPersons()) {
            String fullName = p.getFullName();
            Map<String, ScaleTwoDecimal> personUnitCreditSplitTotalsByCreditSplitType = results.get(fullName);
            for(InvestigatorCreditType creditSplitType: bean.loadInvestigatorCreditTypes()) {
                String invCreditTypeCode = creditSplitType.getCode();
                ScaleTwoDecimal total = personUnitCreditSplitTotalsByCreditSplitType.get(invCreditTypeCode);
                ScaleTwoDecimal expectedValue = expectedPersonUnitCreditSplitTotals.get(fullName).get(invCreditTypeCode);
                Assert.assertEquals(createErrorMessage(p, creditSplitType), expectedValue.doubleValue(), total.doubleValue(), DELTA);
            }
        }
    }

    private String createErrorMessage(AwardPerson p, InvestigatorCreditType creditSplitType) {
        return String.format(ERROR_MSG_PATTERN, p.getPersonId(), p.roleCode, createInvalidTotalMessage(creditSplitType.getDescription()));
    }
}
