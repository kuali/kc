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
package org.kuali.kra.award;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

public class MinimalAwardTest extends KcIntegrationTestBase {

    private Award award;
    private DocumentService docService;
    private AwardDocument awardDocument;

    @Before
    public void setUp() throws Exception {
        award = AwardFixtureFactory.createAwardFixture();
        docService = KcServiceLocator.getService(DocumentService.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSavingAward() throws Exception {
        awardDocument = (AwardDocument) docService.getNewDocument(AwardDocument.class);
        awardDocument.getDocumentHeader().setDocumentDescription("A description");
        awardDocument.setAward(award);
        docService.saveDocument(awardDocument);
        
        Assert.assertNotNull("Award ID was null", award.getAwardId());
    }
}
