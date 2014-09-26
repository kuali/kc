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
