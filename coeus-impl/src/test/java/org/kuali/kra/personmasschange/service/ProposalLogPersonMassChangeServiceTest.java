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
package org.kuali.kra.personmasschange.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.masschange.ProposalDevelopmentPersonMassChange;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.impl.ProposalLogPersonMassChangeServiceImpl;
import org.kuali.rice.krad.service.KualiRuleService;

public class ProposalLogPersonMassChangeServiceTest {
	ProposalLogPersonMassChangeServiceImpl proposalLogPersonMassChangeServiceImpl;
	Mockery context;
	ProposalCopyCriteria criteria;
	KualiRuleService kualiRuleService;
	ProposalDevelopmentDocument proposalDocument;

	@Before
	public void setUp() {
		proposalLogPersonMassChangeServiceImpl = new ProposalLogPersonMassChangeServiceImpl();
	}

	@After
	public void tearDown() {
		proposalLogPersonMassChangeServiceImpl = null;
	}

	@Test
	public void test_getProposalLogChangeCandidates() {
		PersonMassChange personMassChange = new PersonMassChange();
		List<ProposalLog> proposalLogs = proposalLogPersonMassChangeServiceImpl
				.getProposalLogChangeCandidates(personMassChange);
		assertNotNull(proposalLogs);
	}

	@Test(expected = NullPointerException.class)
	public void test_getProposalLogChangeCandidates_noArg() {
		List<ProposalLog> proposalLogs = proposalLogPersonMassChangeServiceImpl
				.getProposalLogChangeCandidates(null);
		assertNotNull(proposalLogs);
	}

	@Test
	public void test_getProposalLogChangeCandidates_emptyArg() {
		PersonMassChange personMassChange = new PersonMassChange();
		List<ProposalLog> proposalLogs = proposalLogPersonMassChangeServiceImpl
				.getProposalLogChangeCandidates(personMassChange);
		assertEquals(proposalLogs.size(), 0);
	}

	@Test
	public void test_getProposalLogChangeCandidates_completeArg() {
		PersonMassChange personMassChange = new PersonMassChange();
		ProposalDevelopmentPersonMassChange proposalDevelopmentPersonMassChange = new ProposalDevelopmentPersonMassChange();
		personMassChange
				.setProposalDevelopmentPersonMassChange(proposalDevelopmentPersonMassChange);
		List<ProposalLog> proposalLogs = proposalLogPersonMassChangeServiceImpl
				.getProposalLogChangeCandidates(personMassChange);
		assertFalse("", (proposalLogs.size() > 0));
	}

	@Test(expected = NullPointerException.class)
	public void test_performPersonMassChange() {
		proposalLogPersonMassChangeServiceImpl.performPersonMassChange(null,
				null);
	}

	@Test
	public void test_performPersonMassChange_withArgs() {
		PersonMassChange personMassChange = new PersonMassChange();
		List<ProposalLog> proposalLogs = new ArrayList<ProposalLog>();
		proposalLogPersonMassChangeServiceImpl.performPersonMassChange(
				personMassChange, proposalLogs);
	}
}
