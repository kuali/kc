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
package org.kuali.kra.bo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.kuali.kra.test.infrastructure.KcSeleniumHelper;
import org.openqa.selenium.WebDriver;

/**
 * Base class for all integration tests for maintenance documents.
 */
public class MaintenanceDocumentSeleniumHelper extends KcSeleniumHelper {
    
    private static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    private static final String MAINTAINABLE_PREFIX = "document.newMaintainableObject.";
    private static final String MAINTAINABLE_COLLECTION_PREFIX = MAINTAINABLE_PREFIX + "add.";
    
    private static MaintenanceDocumentSeleniumHelper helper;
    
    public static MaintenanceDocumentSeleniumHelper instance(WebDriver driver) {
        if (helper == null) {
            helper = new MaintenanceDocumentSeleniumHelper(driver);
        }
        return helper;
    }
    
    private MaintenanceDocumentSeleniumHelper(WebDriver driver) {
        super(driver);
    }
    
    
    /**
     * Looks up the maintenance document with a title of {@code documentTitle}.
     * 
     * @param documentTitle the title of the document to open
     */
    public void lookupMaintenanceDocument(String documentTitle) {
        clickMaintenanceTab();
        
        click(documentTitle);
    }
    
    /**
     * Creates a new instance of the maintenance document with a title of {@code documentTitle}.
     * 
     * @param documentTitle the title of the document to open
     * @param className the BO class name of this maintenance document
     * @param nextPageTitle the title of the maintenance document on the next page
     * @return the document number of the new maintenance document
     */
    public String createMaintenanceDocument(String documentTitle, String className, String nextPageTitle) {
        lookupMaintenanceDocument(documentTitle);
        
        createNewMaintenanceDocument(className, nextPageTitle);
        
        return getDocumentNumber();
    }
    
    public String editMaintenanceDocument(String documentTitle, String className, Map<String, String> searchValues, String nextPageTitle) {
        lookupMaintenanceDocument(documentTitle);
        
        editExistingMaintenanceDocument(className, searchValues, nextPageTitle);
        
        return getDocumentNumber();
    }
    
    public String copyMaintenanceDocument(String documentTitle, String className, Map<String, String> searchValues, String nextPageTitle) {
        lookupMaintenanceDocument(documentTitle);

        copyExistingMaintenanceDocument(className, searchValues, nextPageTitle);
        
        return getDocumentNumber();
    }
    
    public void populateMaintenanceDocument(String documentDescription, Map<String, String> fieldValues) {
        populateMaintenanceDocument(documentDescription, fieldValues, new ArrayList<Map<String, String>>());
    }
    
    public void populateMaintenanceDocument(String documentDescription, Map<String, String> fieldValues, List<Map<String, String>> collectionFieldValuesList) {
        set(DOCUMENT_DESCRIPTION_ID, documentDescription);
        
        for (Entry<String, String> fieldValue : fieldValues.entrySet()) {
            helper.set(MAINTAINABLE_PREFIX + fieldValue.getKey(), fieldValue.getValue());
        }
        
        for (Map<String, String> collectionFieldValues : collectionFieldValuesList) {
            for (Entry<String, String> fieldValue : collectionFieldValues.entrySet()) {
                helper.set(MAINTAINABLE_COLLECTION_PREFIX + fieldValue.getKey(), fieldValue.getValue());
            }
        }
    }

}