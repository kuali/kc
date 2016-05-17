package org.kuali.kra.institutionalproposal.home;

import static org.junit.Assert.*;
import static org.kuali.kra.institutionalproposal.home.InstitutionalProposal.ProjectPersonComparator;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;

public class InstitutionalProposalTest {

	private InstitutionalProposalPerson pi;
	private InstitutionalProposalPerson coi;
	private InstitutionalProposalPerson coi2;
	private InstitutionalProposalPerson kp;
	private InstitutionalProposalPerson kp2;

	@Before
	public void setup() {
		pi = generatePerson("PI", "1");
		coi = generatePerson("COI", "2");
		coi2 = generatePerson("COI", "3");
		kp = generatePerson("KP", "4");
		kp2 = generatePerson("KP", "5");
	}

	private InstitutionalProposalPerson generatePerson(String roleCode, String name) {
		InstitutionalProposalPerson person = new InstitutionalProposalPerson();
		person.setContactRoleCode(roleCode);
		KcPerson personMock = mock(KcPerson.class);
		when(personMock.getLastName()).thenReturn(name);
		when(personMock.getPersonId()).thenReturn("1");
		person.setPerson(personMock);
		return person;
	}

	@Test
	public void testPersonComparator() {
		ProjectPersonComparator comparator = new ProjectPersonComparator();
		assertEquals(-1, comparator.compare(pi, coi));
		assertEquals(1, comparator.compare(kp, pi));
		assertEquals(-1, comparator.compare(coi, kp));
		assertEquals(1, comparator.compare(kp, coi));
		assertTrue(comparator.compare(coi, coi2) < 0);
		assertTrue(comparator.compare(kp, kp2) < 0);
		assertTrue(comparator.compare(coi2, coi) > 0);
		assertTrue(comparator.compare(kp2, kp) > 0);

	}
}