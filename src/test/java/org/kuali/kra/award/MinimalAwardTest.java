/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;

public class MinimalAwardTest extends KraTestBase {

    private Award award;
    private DocumentService docService;
    private AwardDocument awardDocument;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        award = new Award();
        docService = KraServiceLocator.getService(DocumentService.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testSavingAward() throws Exception {
        awardDocument = (AwardDocument) docService.getNewDocument(AwardDocument.class);
        awardDocument.setAward(award);
        docService.saveDocument(awardDocument);
        
        Assert.assertNotNull("Award ID was null", award.getAwardId());
    }
}
