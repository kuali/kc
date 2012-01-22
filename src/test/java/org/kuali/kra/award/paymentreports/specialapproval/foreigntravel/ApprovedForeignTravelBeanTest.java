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
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.ContactRoleFixtureFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Contactable;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KcPersonFixtureFactory;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 * This test class will not be executable until we fix the Rice code where 
 * the Document and Form class constructors invoke Spring services. Geez! Have these
 * guys never read anything about unit testing?
 * 
 */ 
public class ApprovedForeignTravelBeanTest extends KcUnitTestBase {
    private static final String DESTINATION_NAME = "Tokyo, Japan";
    private static final Date START_DATE = new Date(new GregorianCalendar(2009, Calendar.JUNE, 1).getTimeInMillis());
    private static final Date END_DATE = new Date(new GregorianCalendar(2009, Calendar.JUNE, 10).getTimeInMillis());
    private static final KualiDecimal TRIP_AMOUNT = new KualiDecimal(6000.00);
    private static final String TRAVELER_NAME_WAS_NULL = "Traveler name was null";
    private static final String TRAVELER_NAME_WAS_INCORRECT_PATTERN = "Traveler name was incorrect: %s";

    private Award award;
    private ApprovedForeignTravelBean bean;
    private NonOrganizationalRolodex nonEmployeeTraveler;
    private KcPerson employeeTraveler;

    private AwardApprovedForeignTravel foreignTravelTrip;

    private AwardPerson employee;
    private AwardPerson nonEmployee;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        award = new Award();
        AwardForm form = new AwardForm();
        AwardDocument awardDocument = new AwardDocument();
        awardDocument.setAward(award);
        form.setDocument(awardDocument);
        bean = new ApprovedForeignTravelBean(form);

        employeeTraveler = KcPersonFixtureFactory.createKcPerson("1001");
        initializeNonEmployeeTraveler();

        foreignTravelTrip = initializeForeignTravelTrip_Employee();

        employee = new AwardPerson(employeeTraveler, ContactRoleFixtureFactory.MOCK_PI);
        award.add(employee);
        nonEmployee = new AwardPerson(nonEmployeeTraveler, ContactRoleFixtureFactory.MOCK_COI);
        award.add(nonEmployee);
    }

    @After
    public void tearDown() throws Exception {
        bean = null;
        award = null;
        super.tearDown();
    }

    @Test
    public void testSunnyDayScenario_Employee() throws Exception {
        checkForeignTravelTrip(bean, foreignTravelTrip, true);
        Assert.assertEquals(1, award.getApprovedForeignTravelTrips().size());
    }

    @Test
    public void testSunnyDayScenario_Nonemployee() throws Exception {
        AwardApprovedForeignTravel trip = initializeForeignTravelTrip_Nonemployee();
        checkForeignTravelTrip(bean, trip, true);
        Assert.assertEquals(1, award.getApprovedForeignTravelTrips().size());
    }

    @Test
    public void testAddingNewForeignTravelTrip_NothingSet() throws Exception {
        checkForeignTravelTrip(bean, new AwardApprovedForeignTravel(), false);
    }

    @Test
    public void testAddingNewForeignTravelTrip_TravelerNameSet() throws Exception {
        foreignTravelTrip.setPersonTraveler(null);
        foreignTravelTrip.setTravelerName(employeeTraveler.getFullName());
        checkForeignTravelTrip(bean, foreignTravelTrip, true);
    }

    @Test
    public void testAddingNewForeignTravelTrip_TravelerNameNotSet() throws Exception {
        foreignTravelTrip.setPersonTraveler(null);
        checkForeignTravelTrip(bean, foreignTravelTrip, false);
    }

    @Test
    public void testAddingNewForeignTravelTrip_StartDateNotSet() throws Exception {
        foreignTravelTrip.setStartDate(null);
        checkForeignTravelTrip(bean, foreignTravelTrip, false);
    }

    @Test
    public void testAddingNewForeignTravelTrip_EndDateNotSet() throws Exception {
        foreignTravelTrip.setEndDate(null);
        checkForeignTravelTrip(bean, foreignTravelTrip, true);
    }

    @Test
    public void testAddingNewForeignTravelTrip_AmountNotSet() throws Exception {
        foreignTravelTrip.setAmount(null);
        checkForeignTravelTrip(bean, foreignTravelTrip, false);
    }

    @Test
    public void testSettingSelectedTravelerId_Employee() throws Exception {
        foreignTravelTrip.setPersonTraveler(null);
        checkForIdentificationOfTravelerNameFromTravelerId(employee);
    }

    @Test
    public void testSettingSelectedTravelerId_nonEmployee() throws Exception {
        foreignTravelTrip.setPersonTraveler(null);
        checkForIdentificationOfTravelerNameFromTravelerId(nonEmployee);
    }

    private void checkForIdentificationOfTravelerNameFromTravelerId(AwardPerson awardPerson) {
        Contactable contact = awardPerson.getContact();
        bean.setSelectedTravelerId(contact.getIdentifier().toString());
        Assert.assertNotNull(TRAVELER_NAME_WAS_NULL, bean.getNewApprovedForeignTravel().getTravelerName());
        Assert.assertNotNull(createIncorrectNameMessage(contact), bean.getNewApprovedForeignTravel().getTravelerName());
    }

    private String createIncorrectNameMessage(Contactable contact) {
        return String.format(TRAVELER_NAME_WAS_INCORRECT_PATTERN,  contact.getFullName());
    }

    private void checkForeignTravelTrip(ApprovedForeignTravelBean bean, AwardApprovedForeignTravel item, boolean expectedOutcome) {
        bean.setNewAwardApprovedForeignTravel(item);
        Assert.assertEquals(expectedOutcome, bean.addApprovedForeignTravel());
        Assert.assertEquals(expectedOutcome ? 1 : 0, award.getApprovedForeignTravelTrips().size());
    }

    private AwardApprovedForeignTravel initializeForeignTravelTrip_Employee() {
        AwardApprovedForeignTravel foreignTravelTrip = initializeForeignTravelTrip();
        foreignTravelTrip.setPersonTraveler(employeeTraveler);
        return foreignTravelTrip;
    }

    private AwardApprovedForeignTravel initializeForeignTravelTrip_Nonemployee() {
        AwardApprovedForeignTravel foreignTravelTrip = initializeForeignTravelTrip();
        foreignTravelTrip.setRolodexTraveler(nonEmployeeTraveler);
        return foreignTravelTrip;
    }

    private AwardApprovedForeignTravel initializeForeignTravelTrip() {
        AwardApprovedForeignTravel foreignTravelTrip = new AwardApprovedForeignTravel();
        foreignTravelTrip.setDestination(DESTINATION_NAME);
        foreignTravelTrip.setStartDate(START_DATE);
        foreignTravelTrip.setEndDate(END_DATE);
        foreignTravelTrip.setAmount(TRIP_AMOUNT);
        return foreignTravelTrip;
    }

    private void initializeNonEmployeeTraveler() {
        nonEmployeeTraveler = new NonOrganizationalRolodex();
        nonEmployeeTraveler.setRolodexId(1234);
        nonEmployeeTraveler.setFirstName("Joe");
        nonEmployeeTraveler.setLastName("Smith");
    }
}
