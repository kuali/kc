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
package org.kuali.coeus.common.budget.impl.rate;


import org.junit.*;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.rate.RateClass;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.common.budget.framework.rate.AbstractBudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetLaRate;
import org.kuali.coeus.common.budget.framework.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.rate.AbstractInstituteRate;
import org.kuali.coeus.common.budget.framework.rate.InstituteLaRate;
import org.kuali.coeus.common.budget.framework.rate.InstituteRate;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Date;
import java.util.*;

/**
 * 
 * Since the Budget object has been refactored out of the BudgetDocument, and the DevelopmentProposal has been refactored from the 
 * ProposalDevelopmentDocument, the BudgetRatesService should be refactored to use those objects instead of the xxxDocument objects. 
 * However, so many other classes depend on that service, we should tread carefully, adding to the BudgetRatesService instead of 
 * changing the API. JF 
 */
public class BudgetRatesServiceTest extends KcIntegrationTestBase {
    public static final String BUDGET_ID_KEY = "budgetId";
    private static final String TRACK_AFFECTED_PERIOD_1 = "|1|";
    private static final String TRACK_AFFECTED_PERIOD_2 = "|2|";
    private static final double DOUBLE_VALUE_ERROR_LIMIT = 0.01;
    private static final double TEST_INSTITUTE_LA_RATE = 2.5;
    private static final double TEST_INSTITUTE_RATE = 1.5;
    private static final double OLD_APPLICABLE_RATE = 5.75;
    private static final double INSTITUTE_RATE = 7.75;
    
    private static Map<String, ActivityType> activityTypes;
    private BudgetRatesService budgetRatesService;
    private ProposalDevelopmentBudgetExt budget;
    private List<InstituteRate> instituteRates;
    private List<InstituteLaRate> instituteLaRates;
    
    private Date referenceStartDate = DateUtils.newDate(2006, Calendar.JANUARY, 1);
    private Date budgetPeriod1Start = DateUtils.newDate(2008, Calendar.JULY, 1);
    private Date budgetPeriod1End = DateUtils.newDate(2009, Calendar.JUNE, 30);
    private Date budgetPeriod2Start = DateUtils.newDate(2009, Calendar.JULY, 1);
    private Date budgetPeriod2End = DateUtils.newDate(2010, Calendar.JUNE, 30);
    
    private static final String RATE_CLASS_CODE_1 = "1";
    private static final String RATE_CLASS_CODE_2 = "2";
    private static final String RATE_CLASS_CODE_3 = "3";
    
    private static final String RATE_TYPE_CODE_1 = "1";
    private static final String RATE_TYPE_CODE_2 = "2";
    private static final String RATE_TYPE_CODE_3 = "3";
    
    private static final String UNIT_NO = "1";
    
    private static final String RESEARCH_ACTIVITY_CODE = "1";
    private static final String INSTRUCTION_ACTIVITY_CODE = "2";
    private static final String PUBLIC_SERVICE_ACTIVITY_CODE = "3";
    private static final String OTHER_ACTIVITY_CODE = "5";

    private static final String RESEARCH_ACTIVITY_DESCRIPTION = "Research";
    private static final String INSTRUCTION_ACTIVITY_DESCRIPTION = "Instruction";
    private static final String PUBLIC_SERVICE_ACTIVITY_DESCRIPTION = "Public Service";
    private List<RateClass> rateClasses;
    
    @Test
    public void testResetAllBudgetRates() throws Exception {
        setRates(budget.getBudgetRates());
        setRates(budget.getBudgetLaRates());
        budgetRatesService.resetAllBudgetRates(budget);
        checkApplicableRateEqualsInstituteRate(budget.getBudgetRates());
        checkApplicableRateEqualsInstituteRate(budget.getBudgetLaRates());        
    }
    

    @Test
    public void testResetBudgetRatesForRateClassType() throws Exception {
        setRates(budget.getBudgetRates());
        setRates(budget.getBudgetLaRates());
        budgetRatesService.resetBudgetRatesForRateClassType(RATE_CLASS_CODE_3, budget);
        checkApplicableRateEqualsInstituteRateForRateClass(RATE_CLASS_CODE_3, budget.getBudgetRates());
        checkApplicableRateEqualsInstituteRateForRateClass(RATE_CLASS_CODE_3, budget.getBudgetLaRates());
    }


    @Test
    public void testSyncAllBudgetRates_ChangingRatesNotProposalActivityType() throws Exception {
        changeInstituteRates();        
        budgetRatesService.syncAllBudgetRates(budget);
        String activityTypeCode = budget.getBudgetParent().getDocument().getBudgetParent().getActivityTypeCode();
        Assert.assertEquals(countInstituteRatesForActivityTypeCode(activityTypeCode), budget.getBudgetRates().size());
        checkSyncedRates();        
    }
    
    @Test
    public void testSyncAllBudgetRates_ChangeProposalActivityType() throws Exception {
        ((ProposalDevelopmentDocument)budget.getBudgetParent().getDocument()).getDevelopmentProposal().setActivityTypeCode(INSTRUCTION_ACTIVITY_CODE);
        changeInstituteRates();
        budgetRatesService.syncAllBudgetRates(budget);
        String activityTypeCode = budget.getBudgetParent().getDocument().getBudgetParent().getActivityTypeCode();
        Assert.assertEquals(countInstituteRatesForActivityTypeCode(activityTypeCode), budget.getBudgetRates().size());
        checkSyncedRates();        
    }  
    
    @Test
    public void testSyncAllBudgetRates_ChangeProposalActivityType2Other() throws Exception {
        ((ProposalDevelopmentDocument)budget.getBudgetParent().getDocument()).getDevelopmentProposal().setActivityTypeCode(OTHER_ACTIVITY_CODE);
        changeInstituteRates();
        budgetRatesService.syncAllBudgetRates(budget);
        String activityTypeCode = budget.getBudgetParent().getDocument().getBudgetParent().getActivityTypeCode();
        Assert.assertEquals(countInstituteRatesForActivityTypeCode(activityTypeCode), budget.getBudgetRates().size());
        checkSyncedRates();        
    } 

    @Test
    public void testViewLocation() throws Exception {
        testViewLocation(budget.getBudgetRates());
        testViewLocation(budget.getBudgetLaRates());
    }
    
    @BeforeClass
    public static void init() {
        initializeActivityTypes();
    }    
    
    @Before
    public void setUp() throws Exception {
        budgetRatesService = new BudgetRatesServiceImpl(){};
        ((BudgetRatesServiceImpl)budgetRatesService).setBusinessObjectService(new MockBusinessObjectService());
        initializeBudgetDocument();
        initializeBudgetProposalRates();
        initializeInstituteRates();
        initializeRateClasses();
    }

    private void initializeBudgetDocument() {
        budget = new ProposalDevelopmentBudgetExt();
        budget.setBudgetVersionNumber(1);
        budget.setDevelopmentProposal(initializeProposalDevelopmentDocument().getDevelopmentProposal());
        budget.setStartDate(budget.getBudgetParent().getDocument().getBudgetParent().getRequestedStartDateInitial());
        budget.setEndDate(budget.getBudgetParent().getDocument().getBudgetParent().getRequestedEndDateInitial());
        budget.add(generateBudgetPeriod(1, budgetPeriod1Start, budgetPeriod1End));
        budget.add(generateBudgetPeriod(2, budgetPeriod2Start, budgetPeriod2End));
    }

    private BudgetPeriod generateBudgetPeriod(int periodNo, Date start, Date end) {
        BudgetPeriod bp = new BudgetPeriod();
        bp.setStartDate(start);
        bp.setEndDate(end);
        bp.setBudgetPeriod(periodNo);
        return bp;
    }

    private ProposalDevelopmentDocument initializeProposalDevelopmentDocument() {
        ProposalDevelopmentDocument proposal = new ProposalDevelopmentDocument();
        proposal.getDevelopmentProposal().setActivityTypeCode(RESEARCH_ACTIVITY_CODE);
        proposal.getDevelopmentProposal().setActivityType(activityTypes.get(RESEARCH_ACTIVITY_CODE));
        proposal.getDevelopmentProposal().setOwnedByUnitNumber(UNIT_NO);
        Unit unit = new Unit();
        unit.setUnitNumber(UNIT_NO);
        proposal.getDevelopmentProposal().setOwnedByUnit(unit);
        proposal.getDevelopmentProposal().setRequestedStartDateInitial(budgetPeriod1Start);
        proposal.getDevelopmentProposal().setRequestedEndDateInitial(budgetPeriod2End);
        proposal.getDevelopmentProposal().setProposalDocument(proposal);
        return proposal;
    }

    @After
    public void tearDown() throws Exception {
        budgetRatesService = null;
        budget = null;
        instituteRates = null;
        instituteLaRates = null;
    }

    private static void initializeActivityTypes() {
        activityTypes = new TreeMap<String, ActivityType>();
        activityTypes.put(RESEARCH_ACTIVITY_CODE, generateMinimalActivityType(RESEARCH_ACTIVITY_CODE, RESEARCH_ACTIVITY_DESCRIPTION));
        activityTypes.put(INSTRUCTION_ACTIVITY_CODE, generateMinimalActivityType(INSTRUCTION_ACTIVITY_CODE, INSTRUCTION_ACTIVITY_DESCRIPTION));
        activityTypes.put(PUBLIC_SERVICE_ACTIVITY_CODE, generateMinimalActivityType(PUBLIC_SERVICE_ACTIVITY_CODE, PUBLIC_SERVICE_ACTIVITY_DESCRIPTION));
    }

    private static ActivityType generateMinimalActivityType(String activityTypeCode, String description) {
        ActivityType activityType = new ActivityType();
        activityType.setCode(activityTypeCode);
        activityType.setDescription(description);
        return activityType;
    }
    
    private void changeInstituteRates() {
        for(AbstractInstituteRate instituteRate: instituteRates) {
            instituteRate.setInstituteRate(new ScaleTwoDecimal(TEST_INSTITUTE_RATE));
        }
        for(AbstractInstituteRate instituteRate: instituteLaRates) {
            instituteRate.setInstituteRate(new ScaleTwoDecimal(TEST_INSTITUTE_LA_RATE));
        }
    }
    
    private void checkSyncedRates() {
        for(BudgetRate budgetRate: budget.getBudgetRates()) {
            Assert.assertEquals(TEST_INSTITUTE_RATE, budgetRate.getInstituteRate().doubleValue(), DOUBLE_VALUE_ERROR_LIMIT);
            Assert.assertEquals(TEST_INSTITUTE_RATE, budgetRate.getApplicableRate().doubleValue(), DOUBLE_VALUE_ERROR_LIMIT);
        }
        
        for(BudgetLaRate budgetLaRate: budget.getBudgetLaRates()) {
            Assert.assertEquals(TEST_INSTITUTE_LA_RATE, budgetLaRate.getInstituteRate().doubleValue(), DOUBLE_VALUE_ERROR_LIMIT);
            Assert.assertEquals(TEST_INSTITUTE_LA_RATE, budgetLaRate.getApplicableRate().doubleValue(), DOUBLE_VALUE_ERROR_LIMIT);
        }
    }
    
    private int countInstituteRatesForActivityTypeCode(String activityTypeCode) {
        int count = 0;
        for(InstituteRate rate: instituteRates) {
            if(rate.getActivityTypeCode().equals(activityTypeCode)) {
                count++;
            }
        }
        return count;
    }
    
    private void initializeBudgetProposalRates() {
        budget.add(generateBudgetProposalRate(RESEARCH_ACTIVITY_CODE, "1", "1", referenceStartDate, Constants.ON_CAMUS_FLAG));
        budget.add(generateBudgetProposalRate(RESEARCH_ACTIVITY_CODE, "2", "1", referenceStartDate, Constants.ON_CAMUS_FLAG));
        budget.add(generateBudgetProposalRate(RESEARCH_ACTIVITY_CODE, "2", "2", referenceStartDate, Constants.ON_CAMUS_FLAG));
        budget.add(generateBudgetProposalRate(RESEARCH_ACTIVITY_CODE, "3", "1", referenceStartDate, Constants.ON_CAMUS_FLAG));
        budget.add(generateBudgetProposalRate(RESEARCH_ACTIVITY_CODE, "3", "2", referenceStartDate, Constants.ON_CAMUS_FLAG));
        budget.add(generateBudgetProposalRate(RESEARCH_ACTIVITY_CODE, "3", "3", referenceStartDate, Constants.ON_CAMUS_FLAG));
    }
    
    private void initializeRateClasses() {
        rateClasses = new ArrayList<RateClass>();
        rateClasses.add(generateRateClass(RATE_TYPE_CODE_1, RATE_CLASS_CODE_1));
        rateClasses.add(generateRateClass(RATE_TYPE_CODE_2, RATE_CLASS_CODE_2));
        rateClasses.add(generateRateClass(RATE_TYPE_CODE_3, RATE_CLASS_CODE_3));
        budget.setRateClasses(rateClasses);
    }

    private RateClass generateRateClass(String rateClassType, String rateClassCode) {
        RateClass rateClass = new RateClass();
        rateClass.setRateClassTypeCode(rateClassType);
        rateClass.setCode(rateClassCode);
        return rateClass;
    }
    
    private void initializeInstituteRates() {
        instituteRates = new ArrayList<InstituteRate>();
        instituteLaRates = new ArrayList<InstituteLaRate>();
        
        generateInstituteRate(RESEARCH_ACTIVITY_CODE, RATE_CLASS_CODE_1, RATE_TYPE_CODE_1);
        generateInstituteRate(RESEARCH_ACTIVITY_CODE, RATE_CLASS_CODE_2, RATE_TYPE_CODE_1);
        generateInstituteRate(RESEARCH_ACTIVITY_CODE, RATE_CLASS_CODE_2, RATE_TYPE_CODE_2);
        generateInstituteRate(RESEARCH_ACTIVITY_CODE, RATE_CLASS_CODE_3, RATE_TYPE_CODE_1);
        generateInstituteRate(RESEARCH_ACTIVITY_CODE, RATE_CLASS_CODE_3, RATE_TYPE_CODE_2);
        generateInstituteRate(RESEARCH_ACTIVITY_CODE, RATE_CLASS_CODE_3, RATE_TYPE_CODE_3);
        
        generateInstituteRate(INSTRUCTION_ACTIVITY_CODE, RATE_CLASS_CODE_1, RATE_TYPE_CODE_1);
        generateInstituteRate(INSTRUCTION_ACTIVITY_CODE, RATE_CLASS_CODE_2, RATE_TYPE_CODE_2);
        generateInstituteRate(INSTRUCTION_ACTIVITY_CODE, RATE_CLASS_CODE_3, RATE_TYPE_CODE_3);
        
        generateInstituteRate(PUBLIC_SERVICE_ACTIVITY_CODE, RATE_CLASS_CODE_1, RATE_TYPE_CODE_1);
        generateInstituteRate(PUBLIC_SERVICE_ACTIVITY_CODE, RATE_CLASS_CODE_1, RATE_TYPE_CODE_2);
        generateInstituteRate(PUBLIC_SERVICE_ACTIVITY_CODE, RATE_CLASS_CODE_3, RATE_TYPE_CODE_3);
        generateInstituteRate(PUBLIC_SERVICE_ACTIVITY_CODE, RATE_CLASS_CODE_2, RATE_TYPE_CODE_1);
        generateInstituteRate(PUBLIC_SERVICE_ACTIVITY_CODE, RATE_CLASS_CODE_2, RATE_TYPE_CODE_2);
        generateInstituteRate(PUBLIC_SERVICE_ACTIVITY_CODE, RATE_CLASS_CODE_2, RATE_TYPE_CODE_3);
        generateInstituteRate(PUBLIC_SERVICE_ACTIVITY_CODE, RATE_CLASS_CODE_3, RATE_TYPE_CODE_1);
        generateInstituteRate(PUBLIC_SERVICE_ACTIVITY_CODE, RATE_CLASS_CODE_3, RATE_TYPE_CODE_2);
        generateInstituteRate(PUBLIC_SERVICE_ACTIVITY_CODE, RATE_CLASS_CODE_3, RATE_TYPE_CODE_3);
    }
    
    private ScaleTwoDecimal generateDefaultRate(AbstractInstituteRate instituteRate) {
        return new ScaleTwoDecimal(new StringBuilder(instituteRate.getRateClassCode()).append(".").append(instituteRate.getRateTypeCode()).toString());
    }
    
    /**
     * This method creates InstituteRate and InstituteLaRates
     * @param activityTypeCode
     * @param rateClassCode
     * @param rateTypeCode
     */
    private void generateInstituteRate(String activityTypeCode, String rateClassCode, String rateTypeCode) {
        InstituteRate instituteRate = new InstituteRate();
        initializeInstitutionRate(instituteRate, activityTypeCode, rateClassCode, rateTypeCode);
        instituteRates.add(instituteRate);
        
        InstituteLaRate instituteLaRate = new InstituteLaRate();        
        initializeInstitutionRate(instituteLaRate, activityTypeCode, rateClassCode, rateTypeCode);
        instituteLaRates.add(instituteLaRate);
    }
    
    private void initializeInstitutionRate(AbstractInstituteRate instituteRate, String activityTypeCode, String rateClassCode, String rateTypeCode) {
        instituteRate.setUnitNumber(UNIT_NO);
        if(instituteRate instanceof InstituteRate) {
            InstituteRate instRate = (InstituteRate) instituteRate;
            instRate.setActivityTypeCode(activityTypeCode);
            instRate.setActivityType(activityTypes.get(activityTypeCode));
        }
        
        instituteRate.setRateClassCode(rateClassCode);
        RateClass rateClass = new RateClass();
        RateClassType rateClassType = new RateClassType();
        rateClassType.setCode("O");
        rateClassType.setPrefixActivityType(false);
        rateClassType.setSortId(1);
        rateClass.setRateClassTypeCode("O");
        rateClass.setRateClassType(rateClassType);
        instituteRate.setRateClass(rateClass);
        instituteRate.setRateTypeCode(rateTypeCode);
        RateType rateType = new RateType();
        rateType.setDescription(rateTypeCode);
        instituteRate.setRateType(rateType);
        instituteRate.setStartDate(referenceStartDate);
        instituteRate.setOnOffCampusFlag(true);
        instituteRate.setInstituteRate(generateDefaultRate(instituteRate));
        instituteRate.setFiscalYear("2006");
    }
    
    private BudgetRate generateBudgetProposalRate(String activityTypeCode, String rateClassCode, String rateTypeCode, Date startDate, String onOffCampusFlag) {
        BudgetRate budgetRate = new BudgetRate();
        budgetRate.setActivityTypeCode(activityTypeCode);
        budgetRate.setRateClassCode(rateClassCode);
        budgetRate.setRateTypeCode(rateTypeCode);
        RateType rateType = new RateType();
        rateType.setDescription(rateTypeCode);
        budgetRate.setRateType(rateType);
        budgetRate.setStartDate(referenceStartDate);
        budgetRate.setOnOffCampusFlag(Constants.ON_CAMUS_FLAG.equals(onOffCampusFlag));
        budgetRate.setFiscalYear("2008");        
        return budgetRate;
    }
    
    @SuppressWarnings("unchecked")
    private void checkApplicableRateEqualsInstituteRate(List rates) {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) rates; 
        for(AbstractBudgetRate budgetRate: abstractBudgetRates) {
            Assert.assertEquals(budgetRate.getInstituteRate(), budgetRate.getApplicableRate());
        }
    }

    @SuppressWarnings("unchecked")
    private void checkApplicableRateEqualsInstituteRateForRateClass(String rateClassType, List rates) {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) rates; 
        for(AbstractBudgetRate budgetRate: abstractBudgetRates) {
            if(budgetRate.getRateClass().getRateClassTypeCode().equals(rateClassType)) {
                Assert.assertEquals(budgetRate.getInstituteRate(), budgetRate.getApplicableRate());
            } else {
                Assert.assertEquals(OLD_APPLICABLE_RATE, budgetRate.getApplicableRate().doubleValue(), DOUBLE_VALUE_ERROR_LIMIT);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private void setRates(List rateList) {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) rateList;         
        for(AbstractBudgetRate budgetRate: abstractBudgetRates) {
            budgetRate.setApplicableRate(new ScaleTwoDecimal(OLD_APPLICABLE_RATE));
            budgetRate.setInstituteRate(new ScaleTwoDecimal(INSTITUTE_RATE));
            budgetRate.setRateClass(rateClasses.get(Integer.valueOf(budgetRate.getRateClassCode()) - 1));
            budgetRate.setRateClassCode(rateClasses.get(Integer.valueOf(budgetRate.getRateClassCode()) - 1).getCode());
        }
    }
    
    private void testViewLocation(List budgetRates) throws Exception {
        List<AbstractBudgetRate> abstractBudgetRates = (List<AbstractBudgetRate>) budgetRates;
        testViewLocation_DisplayLocation(abstractBudgetRates, TRACK_AFFECTED_PERIOD_1, 2, false);
        testViewLocation_DisplayLocation(abstractBudgetRates, TRACK_AFFECTED_PERIOD_2, 2, true);        
    }
    
    private void testViewLocation_DisplayLocation(List<AbstractBudgetRate> abstractBudgetRates, String trackAffectedPeriod, Integer budgetPeriod, 
                                                    boolean expectedResults) throws Exception {
        for(AbstractBudgetRate rate: abstractBudgetRates) {
            rate.setTrackAffectedPeriod(trackAffectedPeriod);
        }
        budgetRatesService.viewLocation(Constants.ON_CAMUS_FLAG, budgetPeriod, budget);
        for(AbstractBudgetRate rate: abstractBudgetRates) {
            Assert.assertEquals(expectedResults, rate.isDisplayLocation());
        }
    }
    
    @SuppressWarnings("unchecked")
    public class MockBusinessObjectService extends BusinessObjectServiceAdapter {
        
        @Override
        public Collection findMatching(Class clazz, Map fieldValues) {
            Collection results;
            if(clazz.equals(InstituteRate.class)) {
                results = findMatchingInstituteRates(fieldValues);
            } else if(clazz.equals(InstituteLaRate.class)) {
                results = findMatchingInstituteLaRates(fieldValues);
            } else if (clazz.equals(BudgetPerson.class)) {
                results = findMatchingBudgetPersons(fieldValues);
            } else {
                return KcServiceLocator.getService(BusinessObjectService.class).findMatching(clazz, fieldValues);
            }
            
            return results;
            
        }

        private Collection findMatchingInstituteRates(Map fieldValues) {
            String activityTypeCode = (String) fieldValues.get(BudgetRatesServiceImpl.ACTIVITY_TYPE_CODE_KEY);
            String unitNumber = (String) fieldValues.get(BudgetRatesServiceImpl.UNIT_NUMBER_KEY);
            
            List<InstituteRate> filteredInstituteRates = new ArrayList<InstituteRate>();
            for(InstituteRate testRate: instituteRates) {
                if(testRate.getUnitNumber().equals(unitNumber) && (testRate.getActivityTypeCode().equals(activityTypeCode) || activityTypeCode == null)) {
                    filteredInstituteRates.add(testRate);
                }
            }
            
            return filteredInstituteRates;
        }
        
        private Collection findMatchingInstituteLaRates(Map fieldValues) {
            String unitNumber = (String) fieldValues.get(BudgetRatesServiceImpl.UNIT_NUMBER_KEY);
            
            List<InstituteLaRate> filteredInstituteLaRates = new ArrayList<InstituteLaRate>();
            for(InstituteLaRate testRate: instituteLaRates) {
                if(testRate.getUnitNumber().equals(unitNumber)) {
                    filteredInstituteLaRates.add(testRate);
                }
            }
            
            return filteredInstituteLaRates;
        }  
        
        private Collection findMatchingBudgetPersons(Map fieldValues) {
            Integer bvNumber = (Integer) fieldValues.get(BUDGET_ID_KEY);
            
            List<BudgetPerson> budgetPersons = new ArrayList<BudgetPerson>();
            for(BudgetPerson budgetPerson: budgetPersons) {
                if(budgetPerson.getBudgetId().equals(bvNumber)) {
                    budgetPersons.add(budgetPerson);
                }
            }
            return budgetPersons;
        }  
        
    }
}
