/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.document;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;
import static org.junit.Assert.*;
public class AwardDocumentIntegrationTest extends KcIntegrationTestBase {
    
    @Test
    public void testSavingDocument() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));

        AwardDocument doc = (AwardDocument) KRADServiceLocatorWeb.getDocumentService().getNewDocument(AwardDocument.class);
        assertNotNull(doc);
    }
}
