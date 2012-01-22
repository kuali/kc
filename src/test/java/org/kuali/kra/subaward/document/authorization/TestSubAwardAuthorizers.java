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
package org.kuali.kra.subaward.document.authorization;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public class TestSubAwardAuthorizers extends KcUnitTestBase {
    
    TaskAuthorizationService taskAuthorizationService;
    BusinessObjectService businessObjectService;  
    DocumentService documentService;
    Person quickstart;
    Person jtester;
    Person woods;
    Person ospAdmin;
    

    @Before
    public void setUp() throws Exception {
        taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        documentService =  KraServiceLocator.getService(DocumentService.class);
        quickstart = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("quickstart");
        jtester = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("jtester");
        woods = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("woods");
        ospAdmin = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("borst");
    }

    @After
    public void tearDown() throws Exception {
        taskAuthorizationService = null;
        businessObjectService = null;
        documentService=null;
        quickstart = null;
        jtester = null;
        woods = null;
        ospAdmin = null;
    }
    
  
  
    
    @Test
    public void testModifySubAuthorizer()  throws WorkflowException {
        
        SubAwardDocument subAwardDoc = getSubAwardDocument(); 
        
        SubAwardTask task = new SubAwardTask(TaskName.MODIFY_SUBAWARD, subAwardDoc);

        boolean retVal = taskAuthorizationService.isAuthorized(quickstart.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(jtester.getPrincipalId(), task);
        assertFalse(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(woods.getPrincipalId(), task);
        assertFalse(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(ospAdmin.getPrincipalId(), task);
        assertFalse(retVal);
    }
    
   
   
    
  @Test
    public void testViewSubAuthorizer()  throws WorkflowException{
        SubAwardDocument subAwardDoc = getSubAwardDocument(); 
        SubAwardTask task = new SubAwardTask(TaskName.VIEW_SUBAWARD,  subAwardDoc);
        boolean retVal = taskAuthorizationService.isAuthorized(quickstart.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(jtester.getPrincipalId(), task);
        assertFalse(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(woods.getPrincipalId(), task);
        assertFalse(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(ospAdmin.getPrincipalId(), task);
        assertFalse(retVal);
    }
    
    private SubAwardDocument getSubAwardDocument() throws WorkflowException {
        SubAwardDocument document = (SubAwardDocument) getDocumentService().getNewDocument(SubAwardDocument.class);
        document.getDocumentHeader().setDocumentDescription("SubcontractDocumentTest test doc");
        SubAward subAward = new SubAward();
        subAward.setOrganizationId("000001");
        subAward.setSubAwardTypeCode(229);
        subAward.setSubAwardCode("7687");
        subAward.setStatusCode(123);
        document.setSubAward(subAward);
        getDocumentService().saveDocument(document);
        return document;
    }
    
   
}