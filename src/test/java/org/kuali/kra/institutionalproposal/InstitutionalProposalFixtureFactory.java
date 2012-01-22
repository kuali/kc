package org.kuali.kra.institutionalproposal;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 *
 */
public class InstitutionalProposalFixtureFactory {
    public static InstitutionalProposal createInstitutionalProposal() {
        InstitutionalProposal proposal = new InstitutionalProposal();
        proposal.setProposalNumber("00000001");
        proposal.setProposalTypeCode(1);                                    // New
        proposal.setTitle("An Institutional Proposal");
        proposal.setSponsorCode("000162");                                  // Homeland Security
        proposal.setActivityTypeCode("1");                                  // Research
        proposal.setStatusCode(1);                                          // Pending
        proposal.setSubcontractFlag(false);

        proposal.setTotalDirectCostTotal(new KualiDecimal(100000.00));
        proposal.setTotalIndirectCostTotal(new KualiDecimal(2000.00));

        Calendar calendar = new GregorianCalendar(2010, Calendar.JANUARY, 2);
        proposal.setRequestedStartDateInitial(new Date(calendar.getTimeInMillis()));
        calendar.set(2010, Calendar.DECEMBER, 1);
        proposal.setRequestedEndDateTotal(new Date(calendar.getTimeInMillis()));
        
        return proposal;
    }

    public static InstitutionalProposalPerson createInstitutionalProposalPerson(String contactRoleCode) {
        InstitutionalProposalPerson person = new InstitutionalProposalPerson();
        person.setPersonId("10000000002");
        person.setContactRoleCode(contactRoleCode);
        person.setAcademicYearEffort(new KualiDecimal(50.00));
        person.setCalendarYearEffort(new KualiDecimal(40.00));
        person.setSummerEffort(new KualiDecimal(10.00));
        return person;
    }
}
