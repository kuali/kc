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
package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.contact.Contactable;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.ContactRoleFixtureFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KcPersonFixtureFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This test class will not be executable until we fix the Rice code where 
 * the Document and Form class constructors invoke Spring services. Geez! Have these
 * guys never read anything about unit testing?
 * 
 */ 
public class ApprovedForeignTravelBeanTest extends KcIntegrationTestBase {
    private static final String DESTINATION_NAME = "Tokyo, Japan";
    private static final Date START_DATE = new Date(new GregorianCalendar(2009, Calendar.JUNE, 1).getTimeInMillis());
    private static final Date END_DATE = new Date(new GregorianCalendar(2009, Calendar.JUNE, 10).getTimeInMillis());
    private static final ScaleTwoDecimal TRIP_AMOUNT = new ScaleTwoDecimal(6000.00);
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
