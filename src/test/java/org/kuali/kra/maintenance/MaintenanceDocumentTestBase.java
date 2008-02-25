/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.maintenance;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.kuali.core.UserSession;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraWebTestBase;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public abstract class MaintenanceDocumentTestBase extends KraWebTestBase {


    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    protected final HtmlPage getMaintenanceDocumentPage(String documentTitle, String maintenanceDocumentBoClassName, String maintenanceDocumentPageTitle) throws IOException {
        HtmlPage adminPage = clickOn(getPortalPage(), "Administration", "Kuali Portal Index");
        // customAttributeHyperlink is not actually the lookup page - not sure why
        //final HtmlAnchor customAttributeHyperlink = adminPage.getAnchorByHref("portal.do?channelTitle=Custom Attribute&channelUrl=kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.CustomAttribute&returnLocation=http://localhost:9925/kra-dev/portal.do&hideReturnLink=true&docFormKey=88888888");
        HtmlPage MaintenanceDocumentLookupPage = clickOn(adminPage, documentTitle, "Kuali Portal Index");
        //HtmlPage customAttributeLookupPage = clickOn(customAttributeHyperlink, "Kuali :: Lookup");
        assertTrue("Kuali Portal Index".equals(MaintenanceDocumentLookupPage.getTitleText()));
        // this dummy step will load lookup page properly
        HtmlPage lookupPage = clickOn(getPortalPage(), "Create a new record", "Kuali :: Lookup");
        HtmlAnchor createNewHyperlink = lookupPage.getAnchorByHref("maintenance.do?businessObjectClassName="+maintenanceDocumentBoClassName+"&methodToCall=start");
        HtmlPage customAttributeMaintenancePage = clickOn(createNewHyperlink, maintenanceDocumentPageTitle);

        
        return customAttributeMaintenancePage;
    }

}
