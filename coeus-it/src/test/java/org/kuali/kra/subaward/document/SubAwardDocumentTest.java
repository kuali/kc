/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
