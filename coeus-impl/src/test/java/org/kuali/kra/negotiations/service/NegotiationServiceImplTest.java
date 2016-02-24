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
package org.kuali.kra.negotiations.service;

import static org.junit.Assert.*;
import static org.kuali.kra.negotiations.service.NegotiationServiceImpl.ZERO_DAYS_LONG_STRING;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityHistoryLineBean;
import org.kuali.kra.negotiations.bo.NegotiationActivityType;
import org.kuali.kra.negotiations.bo.NegotiationLocation;

public class NegotiationServiceImplTest {

	private NegotiationServiceImpl negotiationService;
	private NegotiationActivityType activityType1;
	private NegotiationLocation location1;
	private NegotiationLocation location2;
	
	@Before
	public void setup() {
		negotiationService = new NegotiationServiceImpl();
		
		activityType1 = new NegotiationActivityType();
		activityType1.setCode("1");
		activityType1.setDescription("Activity Type 1");
		
		location1 = new NegotiationLocation();
		location1.setCode("1");
		location1.setDescription("Location 1");
		
		location2 = new NegotiationLocation();
		location2.setCode("2");
		location2.setDescription("Location 2");
		
	}
	
	@Test
	public void testHistoryBeanGeneration_emptyList() {
		negotiationService.getNegotiationActivityHistoryLineBeans(new ArrayList<NegotiationActivity>());
	}
	
	@Test
	public void testHistoryBeanGeneration_oneActivity_noStartDate() {
		NegotiationActivity activity = new NegotiationActivity();
		activity.setLocation(location1);
		activity.setActivityType(activityType1);
		List<NegotiationActivity> activities = new ArrayList<>();
		activities.add(activity);
		
		List<NegotiationActivityHistoryLineBean> historyBeans = negotiationService.getNegotiationActivityHistoryLineBeans(activities);
		
		assertEquals(1, historyBeans.size());
		assertEquals(location1.getDescription(), historyBeans.get(0).getLocation());
		assertEquals("", historyBeans.get(0).getLocationDays());
	}
	
	@Test
	public void testHistoryBeanGeneration_oneActivity_noEndDate() {
		int numberOfDaysAgo = 10;
		NegotiationActivity activity = new NegotiationActivity();
		activity.setLocation(location1);
		activity.setActivityType(activityType1);
		activity.setStartDate(new Date(DateTime.now().minusDays(numberOfDaysAgo).getMillis()));
		List<NegotiationActivity> activities = new ArrayList<>();
		activities.add(activity);
		
		List<NegotiationActivityHistoryLineBean> historyBeans = negotiationService.getNegotiationActivityHistoryLineBeans(activities);
		
		assertEquals(1, historyBeans.size());
		assertEquals(location1.getDescription(), historyBeans.get(0).getLocation());
		assertEquals(String.valueOf(numberOfDaysAgo), historyBeans.get(0).getLocationDays());
	}
	
	@Test
	public void testHistoryBeanGeneration_oneActivity() {
		NegotiationActivity activity = new NegotiationActivity();
		activity.setLocation(location1);
		activity.setActivityType(activityType1);
		int numberOfDaysAgo = 10;
		int activityLength1 = 2;
		DateTime startDate1 = DateTime.now().minusDays(numberOfDaysAgo);
		activity.setStartDate(new Date(startDate1.getMillis()));
		DateTime endDate1 = startDate1.plusDays(activityLength1);
		activity.setEndDate(new Date(endDate1.getMillis()));
		List<NegotiationActivity> activities = new ArrayList<>();
		activities.add(activity);
		
		List<NegotiationActivityHistoryLineBean> historyBeans = negotiationService.getNegotiationActivityHistoryLineBeans(activities);
		
		assertEquals(1, historyBeans.size());
		assertEquals(location1.getDescription(), historyBeans.get(0).getLocation());
		assertEquals(String.valueOf(activityLength1), historyBeans.get(0).getLocationDays());
	}
	
	@Test
	public void testHistoryBeanGeneration_twoActivities_sameLocation_contiguousDates() {
		int numberOfDaysAgo = 10;
		int activityLength1 = 2;
		int activityLength2 = 3;
		
		NegotiationActivity activity1 = new NegotiationActivity();
		activity1.setLocation(location1);
		activity1.setActivityType(activityType1);

		DateTime startDate1 = DateTime.now().minusDays(numberOfDaysAgo);
		activity1.setStartDate(new Date(startDate1.getMillis()));
		DateTime endDate1 = startDate1.plusDays(activityLength1);
		activity1.setEndDate(new Date(endDate1.getMillis()));
		
		NegotiationActivity activity2 = new NegotiationActivity();
		activity2.setLocation(location1);
		activity2.setActivityType(activityType1);
		DateTime startDate2 = startDate1.plusDays(activityLength1+1);
		activity2.setStartDate(new Date(startDate2.getMillis()));
		DateTime endDate2 = startDate2.plusDays(activityLength2);
		activity2.setEndDate(new Date(endDate2.getMillis()));
		
		List<NegotiationActivity> activities = new ArrayList<>();
		activities.add(activity1);
		activities.add(activity2);
		
		List<NegotiationActivityHistoryLineBean> historyBeans = negotiationService.getNegotiationActivityHistoryLineBeans(activities);
		
		assertEquals(2, historyBeans.size());
		assertEquals(location1.getDescription(), historyBeans.get(0).getLocation());
		assertEquals(String.valueOf(activityLength1), historyBeans.get(0).getLocationDays());
		assertEquals(location1.getDescription(), historyBeans.get(1).getLocation());
		assertEquals(String.valueOf(activityLength2), historyBeans.get(1).getLocationDays());
		DateTime effectiveStartDate = endDate1.plusDays(1);
		assertEquals(effectiveStartDate.toDate(), historyBeans.get(1).getEfectiveLocationStartDate());
	}
	
	@Test
	public void testHistoryBeanGeneration_twoActivities_sameLocation_noEndDates() {
		int numberOfDaysAgo = 10;
		
		NegotiationActivity activity1 = new NegotiationActivity();
		activity1.setLocation(location1);
		activity1.setActivityType(activityType1);

		DateTime startDate1 = DateTime.now().minusDays(numberOfDaysAgo);
		activity1.setStartDate(new Date(startDate1.getMillis()));
		
		NegotiationActivity activity2 = new NegotiationActivity();
		activity2.setLocation(location1);
		activity2.setActivityType(activityType1);
		DateTime startDate2 = startDate1.plusDays(3);
		activity2.setStartDate(new Date(startDate2.getMillis()));
		
		List<NegotiationActivity> activities = new ArrayList<>();
		activities.add(activity1);
		activities.add(activity2);
		
		List<NegotiationActivityHistoryLineBean> historyBeans = negotiationService.getNegotiationActivityHistoryLineBeans(activities);
		
		assertEquals(2, historyBeans.size());
		assertEquals(location1.getDescription(), historyBeans.get(0).getLocation());
		assertEquals(String.valueOf(numberOfDaysAgo), historyBeans.get(0).getLocationDays());
		assertEquals(location1.getDescription(), historyBeans.get(1).getLocation());
		assertEquals(ZERO_DAYS_LONG_STRING, historyBeans.get(1).getLocationDays());
	}
	
	@Test
	public void testHistoryBeanGeneration_twoActivities_sameLocation_noFinalEndDate() {
		int numberOfDaysAgo = 10;
		int activityLength1 = 2;
		
		NegotiationActivity activity1 = new NegotiationActivity();
		activity1.setLocation(location1);
		activity1.setActivityType(activityType1);

		DateTime startDate1 = DateTime.now().minusDays(numberOfDaysAgo);
		activity1.setStartDate(new Date(startDate1.getMillis()));
		DateTime endDate1 = startDate1.plusDays(activityLength1);
		activity1.setEndDate(new Date(endDate1.getMillis()));
		
		NegotiationActivity activity2 = new NegotiationActivity();
		activity2.setLocation(location1);
		activity2.setActivityType(activityType1);
		DateTime startDate2 = endDate1.plusDays(1);
		activity2.setStartDate(new Date(startDate2.getMillis()));
		
		List<NegotiationActivity> activities = new ArrayList<>();
		activities.add(activity1);
		activities.add(activity2);
		
		List<NegotiationActivityHistoryLineBean> historyBeans = negotiationService.getNegotiationActivityHistoryLineBeans(activities);
		
		assertEquals(2, historyBeans.size());
		assertEquals(location1.getDescription(), historyBeans.get(0).getLocation());
		assertEquals(String.valueOf(activityLength1), historyBeans.get(0).getLocationDays());
		assertEquals(location1.getDescription(), historyBeans.get(1).getLocation());
		assertEquals(NegotiationActivity.getNumberOfDays(new Date(startDate2.getMillis()), new Date(DateTime.now().getMillis())), historyBeans.get(1).getLocationDays());
		DateTime effectiveStartDate = endDate1.plusDays(1);
		assertEquals(effectiveStartDate.toDate(), historyBeans.get(1).getEfectiveLocationStartDate());
	}
	
	@Test
	public void testHistoryBeanGeneration_twoActivities_sameLocation_overLappingDates() {
		int numberOfDaysAgo = 30;
		int activityLength1 = 15;
		int activityLength2 = 10;
		
		NegotiationActivity activity1 = new NegotiationActivity();
		activity1.setLocation(location1);
		activity1.setActivityType(activityType1);

		DateTime startDate1 = DateTime.now().minusDays(numberOfDaysAgo);
		activity1.setStartDate(new Date(startDate1.getMillis()));
		DateTime endDate1 = startDate1.plusDays(activityLength1);
		activity1.setEndDate(new Date(endDate1.getMillis()));
		
		NegotiationActivity activity2 = new NegotiationActivity();
		activity2.setLocation(location1);
		activity2.setActivityType(activityType1);
		DateTime startDate2 = startDate1.plusDays(8);
		activity2.setStartDate(new Date(startDate2.getMillis()));
		DateTime endDate2 = startDate2.plusDays(activityLength2);
		activity2.setEndDate(new Date(endDate2.getMillis()));
		
		List<NegotiationActivity> activities = new ArrayList<>();
		activities.add(activity1);
		activities.add(activity2);
		
		List<NegotiationActivityHistoryLineBean> historyBeans = negotiationService.getNegotiationActivityHistoryLineBeans(activities);
		
		assertEquals(2, historyBeans.size());
		assertEquals(location1.getDescription(), historyBeans.get(0).getLocation());
		assertEquals(String.valueOf(activityLength1), historyBeans.get(0).getLocationDays());
		assertEquals(location1.getDescription(), historyBeans.get(1).getLocation());
		assertEquals(NegotiationActivity.getNumberOfDays(new Date(endDate1.plusDays(1).getMillis()), new Date(endDate2.getMillis())), historyBeans.get(1).getLocationDays());
		DateTime effectiveStartDate = endDate1.plusDays(1);
		assertEquals(effectiveStartDate.toDate(), historyBeans.get(1).getEfectiveLocationStartDate());
	}
	
	@Test
	public void testHistoryBeanGeneration_twoActivities_sameLocation_inclusiveDates() {
		int numberOfDaysAgo = 30;
		int activityLength1 = 15;
		int activityLength2 = 10;
		
		NegotiationActivity activity1 = new NegotiationActivity();
		activity1.setLocation(location1);
		activity1.setActivityType(activityType1);

		DateTime startDate1 = DateTime.now().minusDays(numberOfDaysAgo);
		activity1.setStartDate(new Date(startDate1.getMillis()));
		DateTime endDate1 = startDate1.plusDays(activityLength1);
		activity1.setEndDate(new Date(endDate1.getMillis()));
		
		NegotiationActivity activity2 = new NegotiationActivity();
		activity2.setLocation(location1);
		activity2.setActivityType(activityType1);
		DateTime startDate2 = startDate1.plusDays(1);
		activity2.setStartDate(new Date(startDate2.getMillis()));
		DateTime endDate2 = startDate2.plusDays(activityLength2);
		activity2.setEndDate(new Date(endDate2.getMillis()));
		
		List<NegotiationActivity> activities = new ArrayList<>();
		activities.add(activity1);
		activities.add(activity2);
		
		List<NegotiationActivityHistoryLineBean> historyBeans = negotiationService.getNegotiationActivityHistoryLineBeans(activities);
		
		assertEquals(2, historyBeans.size());
		assertEquals(location1.getDescription(), historyBeans.get(0).getLocation());
		assertEquals(String.valueOf(activityLength1), historyBeans.get(0).getLocationDays());
		assertEquals(location1.getDescription(), historyBeans.get(1).getLocation());
		assertEquals(ZERO_DAYS_LONG_STRING, historyBeans.get(1).getLocationDays());
		assertNull(historyBeans.get(1).getEfectiveLocationStartDate());
	}
	
	@Test
	public void testHistoryBeanGeneration_twoActivities_twoLocations() {
		int numberOfDaysAgo = 10;
		int activityLength1 = 2;
		int activityLength2 = 5;
		
		NegotiationActivity activity1 = new NegotiationActivity();
		activity1.setLocation(location1);
		activity1.setActivityType(activityType1);

		DateTime startDate1 = DateTime.now().minusDays(numberOfDaysAgo);
		activity1.setStartDate(new Date(startDate1.getMillis()));
		DateTime endDate1 = startDate1.plusDays(activityLength1);
		activity1.setEndDate(new Date(endDate1.getMillis()));
		
		NegotiationActivity activity2 = new NegotiationActivity();
		activity2.setLocation(location2);
		activity2.setActivityType(activityType1);
		DateTime startDate2 = endDate1.minusDays(1);
		activity2.setStartDate(new Date(startDate2.getMillis()));
		activity2.setEndDate(new Date(startDate2.plusDays(activityLength2).getMillis()));
		
		List<NegotiationActivity> activities = new ArrayList<>();
		activities.add(activity1);
		activities.add(activity2);
		
		List<NegotiationActivityHistoryLineBean> historyBeans = negotiationService.getNegotiationActivityHistoryLineBeans(activities);
		
		assertEquals(3, historyBeans.size());
		assertEquals(location1.getDescription(), historyBeans.get(0).getLocation());
		assertEquals(String.valueOf(activityLength1), historyBeans.get(0).getLocationDays());
		assertEquals(location2.getDescription(), historyBeans.get(2).getLocation());
		assertEquals(String.valueOf(activityLength2), historyBeans.get(2).getLocationDays());
		assertEquals(activity2.getStartDate(), historyBeans.get(2).getEfectiveLocationStartDate());
	}
}
