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
package org.kuali.kra.subaward.document.authorization;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class TestSubAwardAuthorizersTest extends KcIntegrationTestBase {
    
    TaskAuthorizationService taskAuthorizationService;
    Person quickstart;
    Person jtester;
    Person woods;
    Person ospAdmin;
    

    @Before
    public void setUp() throws Exception {
        taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        quickstart = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("quickstart");
        jtester = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("jtester");
        woods = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("woods");
        ospAdmin = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("borst");
    }

    @After
    public void tearDown() throws Exception {
        taskAuthorizationService = null;
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
        SubAwardDocument document = (SubAwardDocument) KRADServiceLocatorWeb.getDocumentService().getNewDocument(SubAwardDocument.class);
        document.getDocumentHeader().setDocumentDescription("SubcontractDocumentTest test doc");
        SubAward subAward = new SubAward();
        subAward.setOrganizationId("000001");
        subAward.setSubAwardTypeCode(229);
        subAward.setSubAwardCode("7687");
        subAward.setStatusCode(123);
        subAward.setPurchaseOrderNum("111");
        subAward.setRequisitionerId("1");
        
        document.setSubAward(subAward);
        KRADServiceLocatorWeb.getDocumentService().saveDocument(document);
        return document;
    }
    
   
}
