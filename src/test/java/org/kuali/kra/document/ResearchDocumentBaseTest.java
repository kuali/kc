/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.sql.Timestamp;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class tests ResearchDocumentBase.
 */
public class ResearchDocumentBaseTest extends KraTestBase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        GlobalVariables.setUserSession(null);
    }

    @Test public void testPrepareForSaveQuickstart() throws Exception {
        ResearchDocumentBase researchDocumentBase = new ProposalDevelopmentDocument();
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

    @Test public void testPrepareForSaveUser4() throws Exception {
        GlobalVariables.setUserSession(new UserSession("user4"));

        ResearchDocumentBase researchDocumentBase = new ProposalDevelopmentDocument();
        assertNull(researchDocumentBase.getUpdateTimestamp());
        assertNull(researchDocumentBase.getUpdateUser());
        researchDocumentBase.prepareForSave();

        assertEquals("user4", researchDocumentBase.getUpdateUser());
        Timestamp updateTimestamp = researchDocumentBase.getUpdateTimestamp();
        assertNotNull(researchDocumentBase.getUpdateTimestamp());

        Date currentDate = new Date(System.currentTimeMillis());
        long diff = updateTimestamp.getTime() - currentDate.getTime();

        assertTrue("Should be less than one second difference between dates", diff < 1000);
    }

}
