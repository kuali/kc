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
package org.kuali.kra.document;

import org.junit.Test;
import org.kuali.kra.budget.bo.RateClass;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.MaintenanceDocumentTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.test.SQLDataLoader;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class RateClassMaintenanceDocumentTest  extends MaintenanceDocumentTestBase {

        private static final String DOCTYPE = "RateClassMaintenanceDocument";
        @Override
        public void tearDown() throws Exception {
            SQLDataLoader sqlDataLoader = new SQLDataLoader("delete from RATE_CLASS where RATE_CLASS_CODE = '99'");
            sqlDataLoader.runSql();
            sqlDataLoader = new SQLDataLoader("update RATE_CLASS set description = 'Lab Allocation - Salaries' where RATE_CLASS_TYPE = 'Y' and RATE_CLASS_CODE='10'");
            sqlDataLoader.runSql();
            sqlDataLoader = new SQLDataLoader("commit");
            sqlDataLoader.runSql();

            super.tearDown();
        }

        @Test
        public void testDocumentCreation() throws Exception {
            testDocumentCreation(DOCTYPE);
        }

        @Test
        public void testCopyRateClassMaintenanceDocument() throws Exception {
            HtmlPage rateClassMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Rate Class");
            setFieldValue(rateClassMaintenanceLookupPage,"rateClassCode","10");
            HtmlPage searchPage = clickOn(rateClassMaintenanceLookupPage, "search");
            
            HtmlAnchor copyLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "copy"));
            HtmlPage rateClassMaintenanceDocumentMaintenanceCopyPage = clickOn(copyLink, "Kuali :: Rate Class Maintenance Document");
            String documentNumber = getFieldValue(rateClassMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentNumber");

            setFieldValue(rateClassMaintenanceDocumentMaintenanceCopyPage, "document.documentHeader.documentDescription", "Rate Class - copy test");

            setFieldValue(rateClassMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateClassType", "Y");
            setFieldValue(rateClassMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.rateClassCode", "99");
            setFieldValue(rateClassMaintenanceDocumentMaintenanceCopyPage, "document.newMaintainableObject.description", "test copy rate class");
                    
            HtmlPage routedPage = clickOn(rateClassMaintenanceDocumentMaintenanceCopyPage, "methodToCall.route", "Kuali :: Rate Class Maintenance Document");
            
            assertContains(routedPage, "Document was successfully submitted.");
            MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
            assertNotNull(document.getDocumentNumber());
            assertNotNull(document.getDocumentHeader());
            assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
            RateClass rateClass = (RateClass)document.getNewMaintainableObject().getBusinessObject();
            assertEquals(rateClass.getRateClassCode(),"99");
            assertEquals(rateClass.getRateClassType(),"Y");
            assertEquals(rateClass.getDescription(),"test copy rate class");


        }

        @Test
        public void testEditRateClassMaintenanceDocument() throws Exception {
            HtmlPage rateClassMaintenanceLookupPage = getMaintenanceDocumentLookupPage("Rate Class");
            setFieldValue(rateClassMaintenanceLookupPage,"rateClassCode","10");
            HtmlPage searchPage = clickOn(rateClassMaintenanceLookupPage, "search");
            
            HtmlAnchor editLink = searchPage.getAnchorByHref(getAnchorName(searchPage, "edit"));
            HtmlPage rateClassMaintenanceDocumentMaintenanceEditPage = clickOn(editLink, "Kuali :: Rate Class Maintenance Document");
            String documentNumber = getFieldValue(rateClassMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentNumber");

            setFieldValue(rateClassMaintenanceDocumentMaintenanceEditPage, "document.documentHeader.documentDescription", "Rate Class - edit test");

            setFieldValue(rateClassMaintenanceDocumentMaintenanceEditPage, "document.newMaintainableObject.description", "test edit rate class");
                    
            HtmlPage routedPage = clickOn(rateClassMaintenanceDocumentMaintenanceEditPage, "methodToCall.route", "Kuali :: Rate Class Maintenance Document");
            
            assertContains(routedPage, "Document was successfully submitted.");
            MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
            assertNotNull(document.getDocumentNumber());
            assertNotNull(document.getDocumentHeader());
            assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
            RateClass rateClass = (RateClass)document.getNewMaintainableObject().getBusinessObject();
            assertEquals(rateClass.getRateClassCode(),"10");
            assertEquals(rateClass.getRateClassType(),"Y");
            assertEquals(rateClass.getDescription(),"test edit rate class");

        }


        @Test
        public void testCreateNewRateClass() throws Exception {
            HtmlPage rateClassMaintenancePage = getMaintenanceDocumentPage("Rate Class","org.kuali.kra.budget.bo.RateClass","Kuali :: Rate Class Maintenance Document");
            String documentNumber = getFieldValue(rateClassMaintenancePage, "document.documentHeader.documentNumber");
            assertContains(rateClassMaintenancePage,"Edit Rate Class New * Rate Class Code: * Rate Class Type: * Description: ");
            setFieldValue(rateClassMaintenancePage, "document.documentHeader.documentDescription", "Rate Class - test");
            setFieldValue(rateClassMaintenancePage, "document.newMaintainableObject.rateClassType", "Y");
            setFieldValue(rateClassMaintenancePage, "document.newMaintainableObject.rateClassCode", "99");
            setFieldValue(rateClassMaintenancePage, "document.newMaintainableObject.description", "test new rate class");
            HtmlPage routedRateClassMaintenanceDocumentPage = clickOn(rateClassMaintenancePage, "methodToCall.route", "Kuali :: Rate Class Maintenance Document");
            
            assertContains(routedRateClassMaintenanceDocumentPage, "Document was successfully submitted.");
            assertContains(routedRateClassMaintenanceDocumentPage,"New Rate Class Code: 99 Rate Class Type: Y Description: test new rate class");
            MaintenanceDocumentBase document = (MaintenanceDocumentBase) KraServiceLocator.getService(DocumentService.class).getByDocumentHeaderId(documentNumber);
            assertNotNull(document.getDocumentNumber());
            assertNotNull(document.getDocumentHeader());
            assertEquals(document.getDocumentHeader().getDocumentNumber(),documentNumber);
            RateClass rateClass = (RateClass)document.getNewMaintainableObject().getBusinessObject();
            assertEquals(rateClass.getRateClassType(),"Y");
            assertEquals(rateClass.getRateClassCode(),"99");
            assertEquals(rateClass.getDescription(),"test new rate class");
            
        }


}
