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
package org.kuali.kra.negotiations.auth;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.budget.AwardBudgetService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationAgreementType;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.bo.NegotiationStatus;
import org.kuali.kra.negotiations.bo.NegotiationUnassociatedDetail;
import org.kuali.kra.negotiations.document.NegotiationDocument;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.timeandmoney.transactions.AwardTransactionType;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.workflow.service.WorkflowDocumentService;

public class TestNegotiationAuthorizers extends KcUnitTestBase {
    
    TaskAuthorizationService taskAuthorizationService;
    BusinessObjectService businessObjectService;  
    DocumentService documentService;
    Person quickstart;
    Person jtester;
    Person woods;
    Person ospAdmin;
    Person negotiator;
    

    @Before
    public void setUp() throws Exception {
        taskAuthorizationService = KraServiceLocator.getService(TaskAuthorizationService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        documentService =  KraServiceLocator.getService(DocumentService.class);
        quickstart = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("quickstart");
        jtester = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("jtester");
        woods = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("woods");
        ospAdmin = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("borst");
        negotiator = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("oblood");
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
    public void testCreateNegotiationAuthorizer() throws WorkflowException {
        NegotiationDocument negotiationDoc = getNewNegotiationWithUnassociatedDetail(); 
        NegotiationTask task = new NegotiationTask(TaskName.NEGOTIATION_CREATE_NEGOTIATION, negotiationDoc);
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
        
        retVal = taskAuthorizationService.isAuthorized(negotiator.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(jtester.getPrincipalId(), task);
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
        
        retVal = taskAuthorizationService.isAuthorized(negotiator.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(jtester.getPrincipalId(), task);
        assertFalse(retVal);
        
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
        NegotiationDocument document = (NegotiationDocument) getDocumentService().getNewDocument(NegotiationDocument.class);
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
    
    /**
     * 
     * This method returns a newly built negotiation associated with an award.  Note this doesn't work, but am keeping it
     * to maybe try later.
     * @return
     * @throws WorkflowException
     */
    private NegotiationDocument getNewNegotiationWithAward() throws WorkflowException {
        NegotiationDocument document = (NegotiationDocument) getDocumentService().getNewDocument(NegotiationDocument.class);
        Negotiation negotiation = document.getNegotiation();
        
        NegotiationStatus status = (NegotiationStatus)businessObjectService.findAll(NegotiationStatus.class).iterator().next();
        NegotiationAgreementType agreementType = (NegotiationAgreementType)businessObjectService.findAll(NegotiationAgreementType.class).iterator().next();
        
        Map primaryKey = new HashMap();
        primaryKey.put("code", "AWD");
        NegotiationAssociationType associationType = (NegotiationAssociationType)businessObjectService.findMatching(NegotiationAssociationType.class, primaryKey).iterator().next();
        
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
        
        this.businessObjectService.save(negotiation);
        
        Award award = new Award();
        award.setTitle("super awesome cool title");
        
        primaryKey = new HashMap();
        primaryKey.put("unit_number", "000001");
        Unit unit = (Unit) this.businessObjectService.findByPrimaryKey(Unit.class, primaryKey);
        
        award.setUnitNumber(unit.getUnitNumber());
        award.setLeadUnit(unit);
        
        Sponsor sponsor = (Sponsor) this.businessObjectService.findAll(Sponsor.class).iterator().next();
        award.setSponsor(sponsor);
        
        ActivityType activityType = (ActivityType) this.businessObjectService.findAll(ActivityType.class).iterator().next();
        award.setActivityType(activityType);
        award.setActivityTypeCode(activityType.getActivityTypeCode());
        
        AwardTransactionType awardTransactionType = (AwardTransactionType) this.businessObjectService.findAll(AwardTransactionType.class).iterator().next();
        award.setAwardTransactionType(awardTransactionType);
        award.setAwardTransactionTypeCode(awardTransactionType.getAwardTransactionTypeCode());
        
        AwardType awardType = (AwardType) this.businessObjectService.findAll(AwardType.class).iterator().next();
        award.setAwardType(awardType);
        award.setAwardTypeCode(awardType.getAwardTypeCode());
        
        award.setStatusCode(1);
        
        java.sql.Date finalExpirationDate = new java.sql.Date(2011, 9, 27);
        award.getAwardAmountInfos().get(0).setFinalExpirationDate(finalExpirationDate);
        
        award.setSequenceNumber(1);
        award.setSequenceOwner(award);
        
        AwardDocument awardDocument = new AwardDocument();
        awardDocument.setAward(award);
        awardDocument.setDocumentNumber("123");
        awardDocument.getDocumentHeader().setDocumentNumber("123");
        awardDocument.getDocumentHeader().setDocumentDescription("doc header descr.");
        awardDocument.setVersionNumber(new Long(1));
        awardDocument.setUpdateTimestamp(new Timestamp(2011, 11, 11, 0, 0, 0, 0));
        awardDocument.setUpdateUser(quickstart.getPrincipalId());
        
        WorkflowDocumentService wds = GlobalResourceLoader.getService("workflowDocumentService");
        WorkflowDocument wd = wds.createWorkflowDocument("AwardDocument", quickstart);
        wd.setTitle("doc ttitle");
        //TODO: Rice Upgrade 2.0 : Are these calls needed?
//        wd.getRouteHeader().setAckRequested(false);
//        wd.getRouteHeader().setFyiRequested(false);
        awardDocument.getDocumentHeader().setWorkflowDocument(wd);
        award.setAwardDocument(awardDocument);
        
        GlobalVariables.getMessageMap().clearErrorMessages();
        //documentService.saveDocument(awardDocument);
        this.businessObjectService.save(awardDocument);
        this.businessObjectService.save(award);
        
        
        Award dbAward1 = KraServiceLocator.getService(AwardBudgetService.class).getActiveOrNewestAward(award.getAwardNumber());
        System.err.println("dbAward1 == null: " + (dbAward1 == null));
        
        List<Award> dbAwards = KraServiceLocator.getService(AwardService.class).findAwardsForAwardNumber(award.getAwardNumber());
        System.err.println("dbAwards.size(): " + dbAwards.size());
        
        negotiation.setAssociatedDocumentId(award.getAwardNumber());
        this.businessObjectService.save(negotiation);
        
        return document;
    }
}