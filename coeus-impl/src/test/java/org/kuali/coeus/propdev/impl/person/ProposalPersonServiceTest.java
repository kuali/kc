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
package org.kuali.coeus.propdev.impl.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.criteria.GenericQueryResults;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;

public class ProposalPersonServiceTest {
	private Mockery context;
	private ProposalPersonServiceImpl proposalPersonService;
	private UnitService unitService;
	private DataObjectService dataObjectService;
	private GenericQueryResults.Builder proposalPersonListBuilder;
	private KcPersonService kcPersonService;

	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery() {
			{
				setThreadingPolicy(new Synchroniser());
			}
		};
		proposalPersonService = new ProposalPersonServiceImpl();
		unitService = context.mock(UnitService.class);
		dataObjectService = context.mock(DataObjectService.class);
		kcPersonService = context.mock(KcPersonService.class);

		final ProposalPerson proposalPerson = new ProposalPerson();

		proposalPersonListBuilder = (GenericQueryResults.Builder<ProposalPerson>) GenericQueryResults.Builder.create();
		proposalPersonListBuilder.setResults(new ArrayList<ProposalPerson>() {
			{
				add(proposalPerson);
			}
		});
	}

	@Test
	public void test_getProposalPersonDivisionName() {
		ProposalPerson proposalPerson = new ProposalPerson();
		proposalPerson.setHomeUnit("000001");
		final List<Unit> units = new ArrayList<Unit>();

		Unit unit1 = new Unit();
		Unit unit2 = new Unit();
		Unit unit3 = new Unit();
		Unit unit4 = new Unit();

		unit1.setUnitName("unitA");
		unit2.setUnitName("unitB");
		unit3.setUnitName("unitC");
		unit4.setUnitName("unitD");

		units.add(unit1);
		units.add(unit2);
		units.add(unit3);
		units.add(unit4);

		context.checking(new Expectations() {
			{
				one(unitService).getUnitHierarchyForUnit("000001");
				will(returnValue(units));
			}
		});
		proposalPersonService.setUnitService(unitService);
		Assert.assertEquals(proposalPersonService
				.getProposalPersonDivisionName(proposalPerson), "unitD");
	}

	@Test
	public void test_getProposalPersonDivisionName_no_unitName() {
		ProposalPerson proposalPerson = new ProposalPerson();
		proposalPerson.setHomeUnit("000001");
		final List<Unit> units = new ArrayList<Unit>();

		Unit unit1 = new Unit();
		Unit unit2 = new Unit();
		Unit unit3 = new Unit();
		Unit unit4 = new Unit();

		units.add(unit1);
		units.add(unit2);
		units.add(unit3);
		units.add(unit4);

		context.checking(new Expectations() {
			{
				one(unitService).getUnitHierarchyForUnit("000001");
				will(returnValue(units));
			}
		});
		proposalPersonService.setUnitService(unitService);
		Assert.assertNull(proposalPersonService
				.getProposalPersonDivisionName(proposalPerson));
	}

	@Test
	public void test_getProposalKeyPersonnel() {
		final QueryByCriteria.Builder criteria = QueryByCriteria.Builder
				.create().setPredicates(
						PredicateFactory.equal(
								"developmentProposal.proposalNumber", "111"));

		context.checking(new Expectations() {
			{
				one(dataObjectService).findMatching(ProposalPerson.class,
						criteria.build()).getResults();
				will(returnValue(proposalPersonListBuilder.build()));
			}
		});
		proposalPersonService.setDataObjectService(dataObjectService);

		assertEquals(1.0, proposalPersonService.getProposalKeyPersonnel("111")
				.size(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_getProposalKeyPersonnel_no_proposalNumber() {
		final QueryByCriteria.Builder criteria = QueryByCriteria.Builder
				.create().setPredicates(
						PredicateFactory.equal(
								"developmentProposal.proposalNumber", null));

		context.checking(new Expectations() {
			{
				one(dataObjectService).findMatching(ProposalPerson.class,
						criteria.build()).getResults();
				will(returnValue(proposalPersonListBuilder.build()));
			}
		});
		proposalPersonService.setDataObjectService(dataObjectService);
		proposalPersonService.getProposalKeyPersonnel(null);
	}

	@Test
	public void test_getPersonName() {
		ProposalDevelopmentDocument doc = new ProposalDevelopmentDocument();
		List<ProposalPerson> proposalPersons = new ArrayList<ProposalPerson>();
		ProposalPerson proposalPerson = new ProposalPerson();
		proposalPerson.setPersonId("123");
		proposalPerson.setFullName("ALAN  MCAFEE");
		proposalPersons.add(proposalPerson);
		doc.getDevelopmentProposal().setProposalPersons(proposalPersons);
		assertEquals(proposalPersonService.getPersonName(doc, "123"),
				"ALAN  MCAFEE");
	}

	@Test
	public void test_getPersonName_no_propPersonName() {
		ProposalDevelopmentDocument doc = new ProposalDevelopmentDocument();
		List<ProposalPerson> proposalPersons = new ArrayList<ProposalPerson>();
		ProposalPerson proposalPerson = new ProposalPerson();
		proposalPerson.setPersonId("123");
		proposalPersons.add(proposalPerson);
		doc.getDevelopmentProposal().setProposalPersons(proposalPersons);

		final KcPerson person = new KcPerson() {
			private String personId;
            @Override
			public void setPersonId(String personId) {
				this.personId = personId;
			}
		};
		context.checking(new Expectations() {
			{
				one(kcPersonService).getKcPersonByPersonId("123");
				will(returnValue(person));
			}
		});
		proposalPersonService.setKcPersonService(kcPersonService);
		assertNotNull(proposalPersonService.getPersonName(doc, "123"));
	}

	@Test
	public void test_getPersonName_empty_proposalPersons() {
		ProposalDevelopmentDocument doc = new ProposalDevelopmentDocument();
		doc.getDevelopmentProposal().setProposalNumber("111");
		final QueryByCriteria.Builder criteria = QueryByCriteria.Builder
				.create().setPredicates(
						PredicateFactory.equal(
								"developmentProposal.proposalNumber", "111"));
		final KcPerson person = new KcPerson() {
            private String personId;
            @Override
			public void setPersonId(String personId) {
				this.personId = personId;
			}
		};
		context.checking(new Expectations() {
			{
				one(dataObjectService).findMatching(ProposalPerson.class,
						criteria.build()).getResults();
				will(returnValue(proposalPersonListBuilder.build()));
				one(kcPersonService).getKcPersonByPersonId("123");
				will(returnValue(person));
			}
		});
		proposalPersonService.setDataObjectService(dataObjectService);
		proposalPersonService.setKcPersonService(kcPersonService);

		assertNotNull(proposalPersonService.getPersonName(doc, "123"));
	}
}
