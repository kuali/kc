package org.kuali.kra.award.dao.ojb;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardFixtureFactory;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.dao.AwardLookupDao;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public class AwardLookupDaoOjbTest extends KcIntegrationTestBase {

	private AwardLookupDao awardLookupDao;
	private BusinessObjectService businessObjectService;
	private DocumentService documentService;
	private String personName;
	
	@Before
	public void setup() throws WorkflowException {
		awardLookupDao = KcServiceLocator.getService(AwardLookupDao.class);
		businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
		documentService = KcServiceLocator.getService(DocumentService.class);
		AwardDocument document = (AwardDocument) documentService.getNewDocument(AwardDocument.class);
		Award newAward = AwardFixtureFactory.createAwardFixture();
		document.setAward(newAward);
		AwardPerson pi = new AwardPerson();
		pi.setPersonId("10000000002");
		pi.setRoleCode("PI");
		pi.setUnitName("University");
		newAward.add(pi);
		AwardPersonUnit unit = new AwardPersonUnit();
		unit.setUnitNumber("000001");
		unit.setLeadUnit(true);
		pi.add(unit);
		documentService.saveDocument(document);
		personName = pi.getFullName();
	}
	
	@Test
	public void testAwardLookup() {
		List<Award> awards = (List<Award>) awardLookupDao.getAwardSearchResults(Collections.emptyMap(), false);
		assertTrue(!awards.isEmpty());
	}
	
	@Test
	public void testAwardLookupWithPiCrit() {
		List<Award> awards = (List<Award>) awardLookupDao.getAwardSearchResults(Collections.singletonMap("projectPersons.fullName", personName), false);
		assertTrue(!awards.isEmpty());
		assertFalse(awards.stream().anyMatch((award) -> !award.getPrincipalInvestigator().getFullName().equals(personName)));
	}
}
