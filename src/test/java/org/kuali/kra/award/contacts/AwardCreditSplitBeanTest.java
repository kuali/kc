/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class tests AwardCreditSplitBean
 */
public class AwardCreditSplitBeanTest {
    private static final long THREE = 3L;
    private static final double DELTA = 0.001;
    private static final double DEFAULT_VALUE = 16.67;
    private static final KualiDecimal BASE_VALUE = new KualiDecimal(DEFAULT_VALUE);
    
    private Award award;
    private AwardCreditSplitBean bean;     
    
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
        Map<String, KualiDecimal> expecetdTotals = initializeCreditSplitTotalsForAllPersons();
        Map<String, Map<String, KualiDecimal>> results = bean.calculateCreditSplitTotals();
        Map<String, KualiDecimal> personCreditSplitTotalsByCreditSplitType = results.get(AwardCreditSplitBean.PERSON_TOTALS_KEY);
        for(InvestigatorCreditType creditSplitType: bean.loadInvestigatorCreditTypes()) {
            String creditTypeCode = creditSplitType.getInvCreditTypeCode();
            KualiDecimal total = personCreditSplitTotalsByCreditSplitType.get(creditTypeCode);
            KualiDecimal expectedTotal = expecetdTotals.get(creditTypeCode);
            Assert.assertEquals(createInvalidTotalMessage(creditSplitType.getDescription()), expectedTotal.doubleValue(), total.doubleValue(), DELTA);
        }        
    }
    
    @Test
    public void testCalculatingCreditSplitTotals_PersonTotals_Zero() {
        Map<String, Map<String, KualiDecimal>> results = bean.calculateCreditSplitTotals();
        Map<String, KualiDecimal> personCreditSplitTotalsByCreditSplitType = results.get(AwardCreditSplitBean.PERSON_TOTALS_KEY);
        for(String creditSplitType: personCreditSplitTotalsByCreditSplitType.keySet()) {
            KualiDecimal total = personCreditSplitTotalsByCreditSplitType.get(creditSplitType);
            Assert.assertEquals(0.0, total.doubleValue(), DELTA);
        }        
    }

    @Test
    public void testCalculatingCreditSplitTotals_PersonUnitTotals_NonZero() {
        Map<String, Map<String, KualiDecimal>> expectedPersonUnitCreditSplitTotals = initializeCreditSplitTotalsForEachPersonUnit();   
        Map<String, Map<String, KualiDecimal>> results = bean.calculateCreditSplitTotals();
        for(AwardPerson p: award.getProjectPersons()) {
            String fullName = p.getFullName();
            Map<String, KualiDecimal> personUnitCreditSplitTotalsByCreditSplitType = results.get(fullName);
            for(InvestigatorCreditType creditSplitType: bean.loadInvestigatorCreditTypes()) {
                String invCreditTypeCode = creditSplitType.getInvCreditTypeCode();
                KualiDecimal total = personUnitCreditSplitTotalsByCreditSplitType.get(invCreditTypeCode);
                KualiDecimal expectedValue = expectedPersonUnitCreditSplitTotals.get(fullName).get(invCreditTypeCode);
                Assert.assertEquals(createInvalidTotalMessage(creditSplitType.getDescription()), expectedValue.doubleValue(), total.doubleValue(), DELTA);
            }
        }
    }

    @Test
    public void testCalculatingCreditSplitTotals_PersonUnitTotals_Zero() {
        Map<String, Map<String, KualiDecimal>> results = bean.calculateCreditSplitTotals();
        for(AwardPerson ap: award.getProjectPersons()) {
            Map<String, KualiDecimal> personUnitCreditSplitTotalsByCreditSplitType = results.get(ap.getFullName().toString());
            for(String creditSplitType: personUnitCreditSplitTotalsByCreditSplitType.keySet()) {
                KualiDecimal total = personUnitCreditSplitTotalsByCreditSplitType.get(creditSplitType);
                Assert.assertEquals(0.0, total.doubleValue(), DELTA);
            }
        }
    }

    /**
     * This method...
     * @return
     */
    private void addAwardPerson(String fullName, Long id) {
        Person p = new Person();
        p.setFullName(fullName);
        p.setPersonId(id.toString());
        AwardPerson ap = new AwardPerson(p, null);
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
    
    /**
     * This method...
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
        for(InvestigatorCreditType creditType: bean.loadInvestigatorCreditTypes()) {
            KualiDecimal value = new KualiDecimal(0);
            KualiDecimal expectedTotal = new KualiDecimal(0);
            for(AwardPerson p: award.getProjectPersons()) {
                value = value.add(BASE_VALUE);
                expectedTotal = expectedTotal.add(value);
                AwardPersonCreditSplit personCreditSplit = new AwardPersonCreditSplit(creditType, value);
                p.add(personCreditSplit);
            }
            expectedTotals.put(creditType.getInvCreditTypeCode(), expectedTotal);
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
        for(AwardPerson p: award.getProjectPersons()) {
            for(InvestigatorCreditType creditType: bean.loadInvestigatorCreditTypes()) {
                KualiDecimal value = new KualiDecimal(0);
                KualiDecimal expectedUnitTotalByCreditType = new KualiDecimal(0);
                for(AwardPersonUnit apu: p.getUnits()) {
                    value = value.add(BASE_VALUE);
                    expectedUnitTotalByCreditType = expectedUnitTotalByCreditType.add(value);
                    apu.add(new AwardPersonUnitCreditSplit(creditType, value));
//                    System.out.println(String.format("Set %f credit split on %s for %s in %s", value.doubleValue(), p.getFullName(), 
//                                                                                   apu.getUnitName(), creditType.getDescription()));                    
                }
                expectedPersonUnitCreditSplitTotals.put(creditType.getInvCreditTypeCode(), expectedUnitTotalByCreditType);
            }
            personMapOfUnitCreditSplitTotals.put(p.getFullName(), expectedPersonUnitCreditSplitTotals);
        }
        return personMapOfUnitCreditSplitTotals;
    }
    
    private void prepareAward() {
        award = new Award();
        addAwardPerson("Person A", 1L);
        addAwardPerson("Person B", 2L);
        addAwardPerson("Person C", THREE);
    }
}
