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
package org.kuali.kra.subaward.document;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;
import static org.junit.Assert.*;
public class SubAwardDocumentTest extends KcIntegrationTestBase {
    
    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }
    
    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
    }
    @Test 
    public void testSave() throws Exception {
        SubAwardDocument document = (SubAwardDocument) KRADServiceLocatorWeb.getDocumentService().getNewDocument("SubAwardDocument");
        setBaseDocumentFields(document, "SubcontractDocumentTest test doc");
        KRADServiceLocatorWeb.getDocumentService().saveDocument(document);
        SubAwardDocument savedDocument =  (SubAwardDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(document.getDocumentNumber());
        assertNotNull(savedDocument);
        checkDocumentFields(savedDocument,document,  "SubcontractDocumentTest test doc");
    }
    
    
   private void setBaseDocumentFields(SubAwardDocument document,String description) {
        document.getDocumentHeader().setDocumentDescription(description);
        SubAward subAward = new SubAward();
        subAward.setOrganizationId("000001");
        subAward.setSubAwardTypeCode(229);
        subAward.setSubAwardCode("7687");
        subAward.setStatusCode(123);
        subAward.setPurchaseOrderNum("111");
        subAward.setRequisitionerId("1");
        document.setSubAward(subAward);
    }
   
   private void checkDocumentFields(SubAwardDocument saveddoc,SubAwardDocument document,String description) {
       assertEquals(saveddoc.getDocumentNumber(), document.getDocumentNumber());
       assertEquals(description, document.getDocumentHeader().getDocumentDescription());
       assertEquals(saveddoc.getSubAward().getOrganizationId(), document.getSubAward().getOrganizationId());
       assertEquals(saveddoc.getSubAward().getSubAwardTypeCode(), document.getSubAward().getSubAwardTypeCode());
       assertEquals(saveddoc.getSubAward().getSubAwardCode(), document.getSubAward().getSubAwardCode());
       assertEquals(saveddoc.getSubAward().getStatusCode(), document.getSubAward().getStatusCode());
   }
}
