/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.maintenance;

import java.io.IOException;

import org.kuali.core.document.MaintenanceDocumentBase;
import org.kuali.core.service.DocumentService;
import org.kuali.kra.KraWebTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public abstract class MaintenanceDocumentTestBase extends KraWebTestBase {

    /**
     * 
     * This method to test the document is set up properly
     * @param docType
     * @throws Exception
     */
    protected void testDocumentCreation(String docType) throws Exception {
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getNewDocument(docType);
        assertNotNull(document.getDocumentNumber());
        assertNotNull(document.getDocumentHeader());
        assertNotNull(document.getDocumentHeader().getDocumentNumber());
    }

    /**
     * 
     * This method is to go to admin tab and return maintenance document creation page
     * @param documentTitle
     * @param maintenanceDocumentBoClassName
     * @param maintenanceDocumentPageTitle
     * @return
     * @throws IOException
     */
    protected final HtmlPage getMaintenanceDocumentPage(String documentTitle, String maintenanceDocumentBoClassName, String maintenanceDocumentPageTitle) throws IOException {
        HtmlPage lookupPage = getMaintenanceDocumentLookupPage(documentTitle);
        HtmlAnchor createNewHyperlink = lookupPage.getAnchorByHref("maintenance.do?businessObjectClassName="+maintenanceDocumentBoClassName+"&methodToCall=start");
        HtmlPage maintenanceDocumentPage = clickOn(createNewHyperlink, maintenanceDocumentPageTitle);

        
        return maintenanceDocumentPage;
    }

    protected final HtmlPage getMaintenanceDocumentLookupPage(String documentTitle) throws IOException {
        HtmlPage adminPage = clickOn(getPortalPage(), "Maintenance", "Kuali Portal Index");
        // customAttributeHyperlink is not actually the lookup page - not sure why
        //final HtmlAnchor customAttributeHyperlink = adminPage.getAnchorByHref("portal.do?channelTitle=Custom Attribute&channelUrl=kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.CustomAttribute&returnLocation=http://localhost:9925/kra-dev/portal.do&hideReturnLink=true&docFormKey=88888888");
        HtmlPage MaintenanceDocumentLookupPage = clickOn(adminPage, documentTitle, "Kuali Portal Index");
        //HtmlPage customAttributeLookupPage = clic kOn(customAttributeHyperlink, "Kuali :: Lookup");
        assertTrue("Kuali Portal Index".equals(MaintenanceDocumentLookupPage.getTitleText()));
        // this dummy step will load lookup page properly
        HtmlPage lookupPage = clickOn(getPortalPage(), "Create a new record", "Kuali :: Lookup");

        
        return lookupPage;
    }

    /**
     * 
     * This method a helper method to get the anchor for 'copy' or 'edit' link
     * This is only good for exactly one search results returned.  Other it will get the first result.
     * @param page
     * @param actionName
     * @return
     */
    protected String getAnchorName(HtmlPage page, String actionName) {
        int idx1 = page.asXml().indexOf("<form");
        idx1 = page.asXml().indexOf("maintenance.do?", idx1);
        if (actionName.equalsIgnoreCase("copy")) {
            // next maintenance.do.  Link is in 'edit' then 'copy' order
            idx1 = page.asXml().indexOf("maintenance.do?", idx1+1);
        }
        int idx2 = page.asXml().indexOf("\"", idx1);
        return page.asXml().substring(idx1, idx2).replace("&amp;", "&");
    }


}
