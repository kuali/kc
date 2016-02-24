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
package org.kuali.org.kuali.kra.s2s.depend;


import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.api.rate.TrainingStipendRateContract;
import org.kuali.coeus.common.budget.framework.rate.TrainingStipendRate;
import org.kuali.coeus.common.budget.impl.rate.TrainingStipendRateServiceImpl;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

import static org.junit.Assert.*;

public class TrainingStipendRateServiceImplTest {

    private Mockery context;

    @Before()
    public void setUpMockery() {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_findClosestMatchTrainingStipendRate_null_effectiveDate() {
        final TrainingStipendRateServiceImpl rateService = new TrainingStipendRateServiceImpl();
        rateService.findClosestMatchTrainingStipendRate(null, "a level", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_findClosestMatchTrainingStipendRate_null_level() {
        final TrainingStipendRateServiceImpl rateService = new TrainingStipendRateServiceImpl();
        rateService.findClosestMatchTrainingStipendRate(new Date(), null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_findClosestMatchTrainingStipendRate_blank_level() {
        final TrainingStipendRateServiceImpl rateService = new TrainingStipendRateServiceImpl();
        rateService.findClosestMatchTrainingStipendRate(new Date(), " ", 1);
    }

    @Test
    public void test_findClosestMatchTrainingStipendRate_no_rate() {
        final TrainingStipendRateServiceImpl rateService = new TrainingStipendRateServiceImpl();
        final BusinessObjectService boService = context.mock(BusinessObjectService.class);

        final Date effectiveDate = new Date();
        final String careerLevel = "a career level";
        final int experienceLevel = 1;

        final Map<String, Object> criteria = new HashMap<>();
        criteria.put("careerLevel", careerLevel);
        criteria.put("experienceLevel", experienceLevel);

        context.checking(new Expectations() {{
            oneOf(boService).findMatchingOrderBy(TrainingStipendRate.class, criteria, "effectiveDate", false);
            will(returnValue(null));
        }});
        rateService.setBusinessObjectService(boService);

        final TrainingStipendRateContract rate = rateService.findClosestMatchTrainingStipendRate(effectiveDate, careerLevel, experienceLevel);

        assertNull(rate);
    }

    @Test
    public void test_findClosestMatchTrainingStipendRate_one_rate_equal_date() {
        final TrainingStipendRateServiceImpl rateService = new TrainingStipendRateServiceImpl();
        final BusinessObjectService boService = context.mock(BusinessObjectService.class);

        final Date effectiveDate = new Date();
        final String careerLevel = "a career level";
        final int experienceLevel = 1;

        final Map<String, Object> criteria = new HashMap<>();
        criteria.put("careerLevel", careerLevel);
        criteria.put("experienceLevel", experienceLevel);

        context.checking(new Expectations() {{

            final TrainingStipendRate rate = new TrainingStipendRate();
            rate.setCareerLevel(careerLevel);
            rate.setExperienceLevel(experienceLevel);
            rate.setEffectiveDate(new java.sql.Date(effectiveDate.getTime()));
            oneOf(boService).findMatchingOrderBy(TrainingStipendRate.class, criteria, "effectiveDate", false);
            will(returnValue(new ArrayList<TrainingStipendRate>(){{
                add(rate);
            }}));
        }});
        rateService.setBusinessObjectService(boService);

        final TrainingStipendRateContract rate = rateService.findClosestMatchTrainingStipendRate(effectiveDate, careerLevel, experienceLevel);

        assertNotNull(rate);
    }

    @Test
    public void test_findClosestMatchTrainingStipendRate_one_rate_less_date() {
        final TrainingStipendRateServiceImpl rateService = new TrainingStipendRateServiceImpl();
        final BusinessObjectService boService = context.mock(BusinessObjectService.class);

        final Date effectiveDate = new Date();
        final String careerLevel = "a career level";
        final int experienceLevel = 1;

        final Map<String, Object> criteria = new HashMap<>();
        criteria.put("careerLevel", careerLevel);
        criteria.put("experienceLevel", experienceLevel);

        context.checking(new Expectations() {{

            final TrainingStipendRate rate = new TrainingStipendRate();
            rate.setCareerLevel(careerLevel);
            rate.setExperienceLevel(experienceLevel);
            java.sql.Date date = new java.sql.Date(effectiveDate.getTime());
            date.setYear(date.getYear() - 1);
            rate.setEffectiveDate(date);
            oneOf(boService).findMatchingOrderBy(TrainingStipendRate.class, criteria, "effectiveDate", false);
            will(returnValue(new ArrayList<TrainingStipendRate>(){{
                add(rate);
            }}));
        }});
        rateService.setBusinessObjectService(boService);

        final TrainingStipendRateContract rate = rateService.findClosestMatchTrainingStipendRate(effectiveDate, careerLevel, experienceLevel);

        assertNotNull(rate);
    }

    @Test
    public void test_findClosestMatchTrainingStipendRate_one_rate_greater_date() {
        final TrainingStipendRateServiceImpl rateService = new TrainingStipendRateServiceImpl();
        final BusinessObjectService boService = context.mock(BusinessObjectService.class);

        final Date effectiveDate = new Date();
        final String careerLevel = "a career level";
        final int experienceLevel = 1;

        final Map<String, Object> criteria = new HashMap<>();
        criteria.put("careerLevel", careerLevel);
        criteria.put("experienceLevel", experienceLevel);

        context.checking(new Expectations() {{

            final TrainingStipendRate rate = new TrainingStipendRate();
            rate.setCareerLevel(careerLevel);
            rate.setExperienceLevel(experienceLevel);
            java.sql.Date date = new java.sql.Date(effectiveDate.getTime());
            date.setYear(date.getYear() + 1);
            rate.setEffectiveDate(date);
            oneOf(boService).findMatchingOrderBy(TrainingStipendRate.class, criteria, "effectiveDate", false);
            will(returnValue(new ArrayList<TrainingStipendRate>(){{
                add(rate);
            }}));
        }});
        rateService.setBusinessObjectService(boService);

        final TrainingStipendRateContract rate = rateService.findClosestMatchTrainingStipendRate(effectiveDate, careerLevel, experienceLevel);

        assertNull(rate);
    }

    /**
     * When multiple matching stipend rates are found, the one with the "newer" effective date wins.  That should be returned.
     */
    @Test
    public void test_findClosestMatchTrainingStipendRate_multiple_rate_less_date() {
        final TrainingStipendRateServiceImpl rateService = new TrainingStipendRateServiceImpl();
        final BusinessObjectService boService = context.mock(BusinessObjectService.class);

        final Date effectiveDate = new Date();
        final String careerLevel = "a career level";
        final int experienceLevel = 1;

        final Map<String, Object> criteria = new HashMap<>();
        criteria.put("careerLevel", careerLevel);
        criteria.put("experienceLevel", experienceLevel);

        context.checking(new Expectations() {{

            final TrainingStipendRate rate1 = new TrainingStipendRate();
            rate1.setCareerLevel(careerLevel);
            rate1.setExperienceLevel(experienceLevel);
            rate1.setDescription("CLOSER EFFECTIVE DATE");
            java.sql.Date date1 = new java.sql.Date(effectiveDate.getTime());
            //this is the "closer" effective date since we are only subtracting 1... rate2 subtracts 2
            date1.setYear(date1.getYear() - 1);
            rate1.setEffectiveDate(date1);

            final TrainingStipendRate rate2 = new TrainingStipendRate();
            rate1.setCareerLevel(careerLevel);
            rate1.setExperienceLevel(experienceLevel);
            java.sql.Date date2 = new java.sql.Date(effectiveDate.getTime());
            date2.setYear(date2.getYear() - 2);
            rate1.setEffectiveDate(date2);

            oneOf(boService).findMatchingOrderBy(TrainingStipendRate.class, criteria, "effectiveDate", false);
            will(returnValue(new ArrayList<TrainingStipendRate>(){{
                add(rate1);
                add(rate2);
            }}));
        }});
        rateService.setBusinessObjectService(boService);

        final TrainingStipendRateContract rate = rateService.findClosestMatchTrainingStipendRate(effectiveDate, careerLevel, experienceLevel);

        assertEquals("CLOSER EFFECTIVE DATE", rate.getDescription());
    }

    @Test
    public void test_findClosestMatchTrainingStipendRate_one_rate_null_date() {
        final TrainingStipendRateServiceImpl rateService = new TrainingStipendRateServiceImpl();
        final BusinessObjectService boService = context.mock(BusinessObjectService.class);

        final Date effectiveDate = new Date();
        final String careerLevel = "a career level";
        final int experienceLevel = 1;

        final Map<String, Object> criteria = new HashMap<>();
        criteria.put("careerLevel", careerLevel);
        criteria.put("experienceLevel", experienceLevel);

        context.checking(new Expectations() {{

            final TrainingStipendRate rate = new TrainingStipendRate();
            rate.setCareerLevel(careerLevel);
            rate.setExperienceLevel(experienceLevel);
            rate.setEffectiveDate(null);
            oneOf(boService).findMatchingOrderBy(TrainingStipendRate.class, criteria, "effectiveDate", false);
            will(returnValue(new ArrayList<TrainingStipendRate>(){{
                add(rate);
            }}));
        }});
        rateService.setBusinessObjectService(boService);

        final TrainingStipendRateContract rate = rateService.findClosestMatchTrainingStipendRate(effectiveDate, careerLevel, experienceLevel);

        assertNull(rate);
    }
}
