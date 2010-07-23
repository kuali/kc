package org.kuali.kra.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.AwardFixtureFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.DocumentService;

public class NihSponsorHandlingIntegrationTest extends KcUnitTestBase {
    private NihSponsorHandlingTestHelper helper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        Sponsorable sponsorable = createAndSaveAward();
        helper = new NihSponsorHandlingTestHelper(sponsorable, getBusinessObjectService(), getSponsorService(), getKeyPersonnelService());
    }

    @After
    public void tearDown() throws Exception {
        helper = null;
        super.tearDown();
    }

    @Test
    public void testSponsorableSave() {

    }

    @Test
    public void testNIHDescriptionsAssigned() {
        helper.testNihDescriptionsAssigned();
    }

    private SponsorService getSponsorService() {
        return KraServiceLocator.getService(SponsorService.class);
    }

    private KeyPersonnelService getKeyPersonnelService() {
        return KraServiceLocator.getService(KeyPersonnelService.class);
    }

    private Award createAndSaveAward() throws Exception {
        Award award = AwardFixtureFactory.createAwardFixture();
        createAndSaveDocument(award);
        return award;
    }

    private void createAndSaveDocument(Award award) throws Exception {
        DocumentService docService = getDocumentService();
        AwardDocument document = (AwardDocument) docService.getNewDocument(AwardDocument.class);
        document.getDocumentHeader().setDocumentDescription("NihSponsorHandlingIntegrationTest");
        document.setAward(award);
        docService.saveDocument(document);
    }
}