package org.kuali.kra.award.paymentreports.specialapproval.foreigntravel;

import java.sql.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.NonOrganizationalRolodex;

/**
 * Testcase for ApprovedForeignTravelerValuesFinder
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class ApprovedForeignTravelerValuesFinderTest {
    private ApprovedForeignTravelerValuesFinder finder;
    private Award award;

    @Before
    public void setUp() {
        finder = new ApprovedForeignTravelerValuesFinder() {
            protected Award getAward() {
                return award;
            }
        };
        award = new Award();
        award.add(new AwardPerson(generatePerson("Jane", "Doe"), new ContactType(ContactRole.PI_CODE, "PI")));
        award.add(new AwardPerson(generateRolodex("John", "Smith"), new ContactType(ContactRole.COI_CODE, "CO-I")));
        award.add(new AwardPerson(generatePerson("William", "Johnson"), new ContactType(ContactRole.KEY_PERSON_CODE, "Key Person")));

        Date d = new Date(System.currentTimeMillis());
        award.add(new AwardApprovedForeignTravel(generatePerson("Isaac", "Asimov"), "Tokyo, Japan", d, d, 2000.00));
        award.add(new AwardApprovedForeignTravel(generatePerson("Robert", "Heinlein"), "Tokyo, Japan", d, d, 2000.00));
    }

    @After
    public void tearDown() {
        finder = null;
        award = null;
    }

    @Test
    public void testFindingProjectPersons_NoTravelers() {
        award.getApprovedForeignTravelTrips().clear();
        Assert.assertEquals(award.getProjectPersons().size(), finder.getKeyValues().size());
    }

    @Test
    public void testFindingProjectPersons_NoProjectPersons() {
        award.getProjectPersons().clear();
        Assert.assertEquals(award.getApprovedForeignTravelTripCount(), finder.getKeyValues().size());
    }

    @Test
    public void testFindingProjectPersons_NoDuplicates() {
        Assert.assertEquals(award.getApprovedForeignTravelTripCount() + award.getProjectPersons().size(), finder.getKeyValues().size());
    }

    @Test
    public void testFindingProjectPersons_OneDuplicateFromPriorTravelers() {
        Date d = new Date(System.currentTimeMillis());
        award.add(new AwardApprovedForeignTravel(generatePerson("Jane", "Doe"), "Tokyo, Japan", d, d, 2000.00));
        Assert.assertEquals(award.getApprovedForeignTravelTripCount() + award.getProjectPersons().size() - 1, finder.getKeyValues().size());
    }

    @Test
    public void testFindingProjectPersons_OneDuplicateFromProjectPersons() {
        award.add(new AwardPerson(generatePerson("Isaac", "Asimov"), new ContactType(ContactRole.KEY_PERSON_CODE, "Key Person")));
        Assert.assertEquals(award.getApprovedForeignTravelTripCount() + award.getProjectPersons().size() - 1, finder.getKeyValues().size());
    }

    private KcPerson generatePerson(String firstName, String lastName) {
        KcPerson contact = KcPerson.fromPersonId(generateUserName(firstName, lastName));
        return contact;
    }

    private String generateUserName(String firstName, String lastName) {
        return firstName.toLowerCase().charAt(0) + lastName;
    }

    private NonOrganizationalRolodex generateRolodex(String firstName, String lastName) {
        NonOrganizationalRolodex contact = new NonOrganizationalRolodex();
        contact.setRolodexId((generateUserName(firstName, lastName)).hashCode());
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        return contact;
    }
}
