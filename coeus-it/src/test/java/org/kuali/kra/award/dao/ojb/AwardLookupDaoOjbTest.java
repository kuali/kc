package org.kuali.kra.award.dao.ojb;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.dao.AwardLookupDao;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

public class AwardLookupDaoOjbTest extends KcIntegrationTestBase {

	private AwardLookupDao awardLookupDao;
	
	@Before
	public void setup() {
		awardLookupDao = KcServiceLocator.getService(AwardLookupDao.class);
	}
	
	@Test
	public void testAwardLookup() {
		List<Award> awards = (List<Award>) awardLookupDao.getAwardSearchResults(Collections.emptyMap(), false);
		assertTrue(!awards.isEmpty());
	}
	
	@Test
	public void testAwardLookupWithPiCrit() {
		List<Award> awards = (List<Award>) awardLookupDao.getAwardSearchResults(Collections.singletonMap("projectPersons.fullName", "Joe  Tester"), false);
		assertTrue(!awards.isEmpty());
		assertFalse(awards.stream().anyMatch((award) -> !award.getPrincipalInvestigator().getFullName().equals("Joe  Tester")));
	}
}
