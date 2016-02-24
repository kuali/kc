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
package org.kuali.kra.negotiations.auth;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.negotiations.bo.*;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class TestNegotiationAuthorizersTest extends KcIntegrationTestBase {
    
    TaskAuthorizationService taskAuthorizationService;
    BusinessObjectService businessObjectService;  
    Person quickstart;
    Person jtester;
    Person woods;
    Person ospAdmin;
    Person negotiator;
    

    @Before
    public void setUp() throws Exception {
        taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        quickstart = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("quickstart");
        jtester = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("jtester");
        woods = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("woods");
        ospAdmin = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("borst");
        negotiator = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("oblood");
    }

    @After
    public void tearDown() throws Exception {
        taskAuthorizationService = null;
        businessObjectService = null;
        quickstart = null;
        jtester = null;
        woods = null;
        ospAdmin = null;
    }
    
    @Test
    public void testCreateNegotiationAuthorizer() throws WorkflowException {
        NegotiationDocument negotiationDoc = getNewNegotiationWithUnassociatedDetail(); 
        ApplicationTask task = new ApplicationTask(TaskName.NEGOTIATION_CREATE_NEGOTIATION);
        boolean retVal = taskAuthorizationService.isAuthorized(quickstart.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(jtester.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(woods.getPrincipalId(), task);
        assertFalse(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(ospAdmin.getPrincipalId(), task);
        assertFalse(retVal);
    }
    
    @Test
    public void testModifyNegotiationAuthorizer()  throws WorkflowException {
        NegotiationDocument negotiationDoc = getNewNegotiationWithUnassociatedDetail();
        NegotiationTask task = new NegotiationTask(TaskName.NEGOTIATION_MODIFIY_NEGOTIATION, negotiationDoc);

        boolean retVal = taskAuthorizationService.isAuthorized(quickstart.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(jtester.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(woods.getPrincipalId(), task);
        assertFalse(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(ospAdmin.getPrincipalId(), task);
        assertFalse(retVal);
    }
    
    @Test
    public void testCreateActivitiesAuthorizer() throws WorkflowException {
        NegotiationDocument negotiationDoc = getNewNegotiationWithUnassociatedDetail();
        NegotiationTask task = new NegotiationTask(TaskName.NEGOTIATION_CREATE_ACTIVITIES, negotiationDoc);
        boolean retVal = taskAuthorizationService.isAuthorized(quickstart.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(jtester.getPrincipalId(), task);
        assertTrue(retVal);

        //Following code is commented because of KCINFR-447. Once this is resolved, 
        //the following can be uncommented back

        retVal = taskAuthorizationService.isAuthorized(negotiator.getPrincipalId(), task);
        assertTrue(retVal);
                
        retVal = taskAuthorizationService.isAuthorized(woods.getPrincipalId(), task);
        assertFalse(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(ospAdmin.getPrincipalId(), task);
        assertFalse(retVal);
    }
        
    
    @Test
    public void testModifyActivitiesAuthorizer() throws WorkflowException {
        NegotiationDocument negotiationDoc = getNewNegotiationWithUnassociatedDetail();
        NegotiationTask task = new NegotiationTask(TaskName.NEGOTIATION_MODIFY_ACTIVITIES, negotiationDoc);
        boolean retVal = taskAuthorizationService.isAuthorized(quickstart.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(jtester.getPrincipalId(), task);
        assertFalse(retVal);
        
        //Following code is commented because of KCINFR-447. Once this is resolved, 
        //the following can be uncommented back

        retVal = taskAuthorizationService.isAuthorized(negotiator.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(woods.getPrincipalId(), task);
        assertFalse(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(ospAdmin.getPrincipalId(), task);
        assertFalse(retVal);
    }
    
    @Test
    public void testViewNegotiationUnRestrictedAuthorizer()  throws WorkflowException{
        NegotiationDocument negotiationDoc = getNewNegotiationWithUnassociatedDetail();
        NegotiationTask task = new NegotiationTask(TaskName.NEGOTIATION_VIEW_NEGOTIATION_UNRESTRICTED, negotiationDoc);

        boolean retVal = taskAuthorizationService.isAuthorized(quickstart.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(jtester.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(woods.getPrincipalId(), task);
        assertFalse(retVal);

        retVal = taskAuthorizationService.isAuthorized(ospAdmin.getPrincipalId(), task);
        assertTrue(retVal);
    }
    
    @Test
    public void testViewNegotiationAuthorizer()  throws WorkflowException{
        NegotiationDocument negotiationDoc = getNewNegotiationWithUnassociatedDetail();
        NegotiationTask task = new NegotiationTask(TaskName.NEGOTIATION_VIEW_NEGOTIATION,  negotiationDoc);
        boolean retVal = taskAuthorizationService.isAuthorized(quickstart.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(jtester.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(woods.getPrincipalId(), task);
        assertFalse(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(ospAdmin.getPrincipalId(), task);
        assertTrue(retVal);
    }
    
    private NegotiationDocument getNewNegotiationWithUnassociatedDetail() throws WorkflowException {
        NegotiationDocument document = (NegotiationDocument) KRADServiceLocatorWeb.getDocumentService().getNewDocument(NegotiationDocument.class);
        Negotiation negotiation = document.getNegotiation();
        
        NegotiationStatus status = (NegotiationStatus)businessObjectService.findAll(NegotiationStatus.class).iterator().next();
        NegotiationAgreementType agreementType = (NegotiationAgreementType)businessObjectService.findAll(NegotiationAgreementType.class).iterator().next();
        
        Map primaryKey = new HashMap();
        primaryKey.put("code", "NO");
        NegotiationAssociationType associationType = (NegotiationAssociationType)businessObjectService.findMatching(NegotiationAssociationType.class, primaryKey).iterator().next();
        
        //NegotiationActivityType activityType = (NegotiationActivityType)businessObjectService.findAll(NegotiationActivityType.class).iterator().next();
        //NegotiationLocation location = (NegotiationLocation)businessObjectService.findAll(NegotiationLocation.class).iterator().next();
        
        negotiation.setNegotiationAgreementType(agreementType);
        negotiation.setNegotiationAssociationType(associationType);
        negotiation.setNegotiationAssociationTypeId(associationType.getId());
        negotiation.setNegotiationStatus(status);
        
        java.sql.Date testEndDate = new java.sql.Date(2011, 12, 31);
        java.sql.Date testStartDate = new java.sql.Date(2009, 12, 31);
        
        negotiation.setAnticipatedAwardDate(testEndDate);
        negotiation.setNegotiationEndDate(testEndDate);
        negotiation.setNegotiationStartDate(testStartDate);
        negotiation.setDocumentFolder("document folder");
        negotiation.setDocumentNumber("123321");
        negotiation.setNegotiatorPersonId(negotiator.getPrincipalId());
        
        this.businessObjectService.save(negotiation);
        
        NegotiationUnassociatedDetail detail = new NegotiationUnassociatedDetail();
        detail.setNegotiation(negotiation);
        detail.setNegotiationId(negotiation.getNegotiationId());
        detail.setTitle("super cool title");
        
        primaryKey = new HashMap();
        primaryKey.put("unit_number", "000001");
        Unit unit = (Unit) this.businessObjectService.findByPrimaryKey(Unit.class, primaryKey);
        
        detail.setLeadUnit(unit);
        detail.setLeadUnitNumber(unit.getUnitNumber());
        
        this.businessObjectService.save(detail);
        
        negotiation.setAssociatedDocumentId(detail.getNegotiationId().toString());
        negotiation.setUnAssociatedDetail(detail);
        
        this.businessObjectService.save(negotiation);
        return document;
    }

}
