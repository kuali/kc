/*
 * Copyright 2005-2010 The Kuali Foundation
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

import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcWebTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public abstract class MaintenanceDocumentTestBase extends KcWebTestBase {

    public abstract String getDocTypeName();
    
    /**
     * 
     * This method to test the document is set up properly
     * @param docType
     * @throws Exception
     */
    @Test
    public void testDocumentCreation() throws Exception {
        MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getNewDocument(getDocTypeName());
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
        HtmlPage MaintenanceDocumentLookupPage = clickOn(adminPage, documentTitle, "Kuali Portal Index");
        
        MaintenanceDocumentLookupPage = getInnerPages(MaintenanceDocumentLookupPage).get(0);
        assertTrue("Kuali :: Lookup".equals(MaintenanceDocumentLookupPage.getTitleText()));
        return MaintenanceDocumentLookupPage;
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
        if (actionName.equalsIgnoreCase("copy") && page.asXml().indexOf("methodToCall=edit", idx1) > 0) {
            // next maintenance.do.  Link is in 'edit' then 'copy' order
            // some pages may not have 'edit'
            idx1 = page.asXml().indexOf("maintenance.do?", idx1+1);
        }
        int idx2 = page.asXml().indexOf("\"", idx1);
        return page.asXml().substring(idx1, idx2).replace("&amp;", "&");
    }

}
