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


import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivityType;
import org.kuali.kra.negotiations.bo.NegotiationAgreementType;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.bo.NegotiationLocation;
import org.kuali.kra.negotiations.bo.NegotiationStatus;
import org.kuali.kra.negotiations.bo.NegotiationUnassociatedDetail;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kew.dto.UserIdDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.service.impl.WorkflowDocumentServiceImpl;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.PersonService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;
import org.kuali.rice.kns.workflow.service.WorkflowDocumentService;

public class TestNegotiationAuthorizers extends KcUnitTestBase {
    
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
    public void testCreateNegotiationAuthorizer() throws WorkflowException {
        Negotiation negotiation = getNewNegotiation(); 
        NegotiationTask task = new NegotiationTask(TaskName.NEGOTIATION_CREATE_NEGOTIATION, negotiation);
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
        Negotiation negotiation = getNewNegotiation();
        NegotiationTask task = new NegotiationTask(TaskName.NEGOTIATION_MODIFIY_NEGOTIATION, negotiation);

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
    public void testModifyActivitiesAuthorizer() throws WorkflowException {
        Negotiation negotiation = getNewNegotiation();
        NegotiationTask task = new NegotiationTask(TaskName.NEGOTIATION_MODIFY_ACTIVITIES, negotiation);
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
    public void testViewNegotiationUnRestrictedAuthorizer()  throws WorkflowException{
        Negotiation negotiation = getNewNegotiation();
        NegotiationTask task = new NegotiationTask(TaskName.NEGOTIATION_VIEW_NEGOTIATION_UNRESTRICTED, negotiation);

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
        Negotiation negotiation = getNewNegotiation();
        NegotiationTask task = new NegotiationTask(TaskName.NEGOTIATION_VIEW_NEGOTIATION,  negotiation);
        boolean retVal = taskAuthorizationService.isAuthorized(quickstart.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(jtester.getPrincipalId(), task);
        assertTrue(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(woods.getPrincipalId(), task);
        assertFalse(retVal);
        
        retVal = taskAuthorizationService.isAuthorized(ospAdmin.getPrincipalId(), task);
        assertTrue(retVal);
    }
    
    protected Negotiation getNewNegotiation() throws WorkflowException {
        Negotiation negotiation = new Negotiation();
        
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
        
        /*
        InstitutionalProposal ip = new InstitutionalProposal();
        ip.setTitle("super awesome cool title");
        primaryKey = new HashMap();
        primaryKey.put("unit_number", "000001");
        Unit unit = (Unit) this.businessObjectService.findByPrimaryKey(Unit.class, primaryKey);
        ip.setLeadUnit(unit);
        ip.setLeadUnitNumber(unit.getUnitNumber());
        ip.setStatusCode(1);
        ip.setProposalNumber("1234");
        
        ActivityType at = (ActivityType) this.businessObjectService.findAll(ActivityType.class).iterator().next();
        ip.setActivityType(at);
        ip.setActivityTypeCode(at.getActivityTypeCode());
        
        ProposalType pt = (ProposalType) this.businessObjectService.findAll(ProposalType.class).iterator().next();
        ip.setProposalType(pt);
        ip.setProposalTypeCode(Integer.parseInt(pt.getProposalTypeCode()));
        
        Sponsor sponsor = (Sponsor) this.businessObjectService.findAll(Sponsor.class).iterator().next();
        ip.setSponsor(sponsor);
        ip.setSponsorCode(sponsor.getSponsorCode());
        
        ip.setSubcontractFlag(false);
        ip.setScienceCodeIndicator("N");
        ip.setSpecialReviewIndicator("N");
        
        InstitutionalProposalDocument ipd = new InstitutionalProposalDocument();
        ipd.setDocumentNumber("123");
        ipd.getDocumentHeader().setDocumentDescription("descr goes here!");
        ipd.getDocumentHeader().setDocumentNumber("987");
        UserIdDTO memStatFormat;
        WorkflowDocumentService wds = KraServiceLocator.getService(WorkflowDocumentService.class);
        KualiWorkflowDocument wd = wds.createWorkflowDocument("InstitutionalProposalDocument", quickstart);
        ipd.getDocumentHeader().setWorkflowDocument(wd);
        ipd.setInstitutionalProposal(ip);
        //documentService.saveDocument(ipd);
        
        ip.setInstitutionalProposalDocument(ipd);
        
        //this.businessObjectService.save(ip);
        documentService.saveDocument(ipd);
        
        negotiation.setAssociatedDocumentId(ip.getProposalNumber());
        this.businessObjectService.save(negotiation);
        */
        return negotiation;
    }
}