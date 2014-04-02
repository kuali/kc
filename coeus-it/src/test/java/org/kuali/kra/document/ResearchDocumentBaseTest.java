/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.document;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;
/**
 * This class tests ResearchDocumentBase.
 */
public class ResearchDocumentBaseTest extends KcIntegrationTestBase {

    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
    }

    @Test public void testPrepareForSaveQuickstart() throws Exception {
        KcTransactionalDocumentBase researchDocumentBase = (KcTransactionalDocumentBase) KcServiceLocator.getService(DocumentService.class).getNewDocument(ProposalDevelopmentDocument.class);
        assertNull(researchDocumentBase.getUpdateTimestamp());
        assertNull(researchDocumentBase.getUpdateUser());
        researchDocumentBase.prepareForSave();

        assertEquals("quickstart", researchDocumentBase.getUpdateUser());
        Timestamp updateTimestamp = researchDocumentBase.getUpdateTimestamp();
        assertNotNull(researchDocumentBase.getUpdateTimestamp());

        Date currentDate = new Date(System.currentTimeMillis());
        long diff = updateTimestamp.getTime() - currentDate.getTime();

        assertTrue("Should be less than one second difference between dates", diff < 1000);
    }

    @Test public void testPrepareForSaveJtester() throws Exception {
        GlobalVariables.setUserSession(new UserSession("jtester"));

        KcTransactionalDocumentBase researchDocumentBase = (KcTransactionalDocumentBase) KcServiceLocator.getService(DocumentService.class).getNewDocument(ProposalDevelopmentDocument.class);
        assertNull(researchDocumentBase.getUpdateTimestamp());
        assertNull(researchDocumentBase.getUpdateUser());
        researchDocumentBase.prepareForSave();

        assertEquals("jtester", researchDocumentBase.getUpdateUser());
        Timestamp updateTimestamp = researchDocumentBase.getUpdateTimestamp();
        assertNotNull(researchDocumentBase.getUpdateTimestamp());

        Date currentDate = new Date(System.currentTimeMillis());
        long diff = updateTimestamp.getTime() - currentDate.getTime();

        assertTrue("Should be less than one second difference between dates", diff < 1000);
    }

}
