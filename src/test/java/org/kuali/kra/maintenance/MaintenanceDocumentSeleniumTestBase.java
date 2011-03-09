/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.maintenance;

import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Base class for all integration tests for maintenance documents.
 */
public class MaintenanceDocumentSeleniumTestBase extends KcSeleniumTestBase {
    
    private static final String PAGE_TITLE = "Kuali :: Lookup";
    
    /**
     * Creates a new instance of the maintenance document with a title of {@code documentTitle}.
     * 
     * @param documentTitle the title of the document to open
     * @param className the BO class name of this maintenance document
     * @param nextPageTitle the title of the maintenance document on the next page
     */
    protected void createMaintenanceDocument(String documentTitle, String className, String nextPageTitle) {
        lookupMaintenanceDocument(documentTitle);
        
        createNewMaintenanceDocument(className, nextPageTitle);
    }
    
    /**
     * Looks up the maintenance document with a title of {@code documentTitle}.
     * 
     * @param documentTitle the title of the document to open
     */
    protected void lookupMaintenanceDocument(String documentTitle) {
        clickMaintenanceTab();
        
        click(documentTitle);
        assertTitleContains(PAGE_TITLE);
    }

}