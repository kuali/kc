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
package org.kuali.kra.negotiations.bo;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This class...
 */
public class NegotiationBasicTests extends KcUnitTestBase {
    
    BusinessObjectService businessObjectService;
    NegotiationStatus status;
    NegotiationAgreementType agreementType;
    NegotiationAssociationType associationType;
    NegotiationLocation location;
    NegotiationActivityType activityType;
    
    @Before
    public void setUp() throws Exception {
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        
        status = (NegotiationStatus)businessObjectService.findAll(NegotiationStatus.class).iterator().next();
        agreementType = (NegotiationAgreementType)businessObjectService.findAll(NegotiationAgreementType.class).iterator().next();
        associationType = (NegotiationAssociationType)businessObjectService.findAll(NegotiationAssociationType.class).iterator().next();
        activityType = (NegotiationActivityType)businessObjectService.findAll(NegotiationActivityType.class).iterator().next();
        location = (NegotiationLocation)businessObjectService.findAll(NegotiationLocation.class).iterator().next();
    }
    
    @After
    public void tearDown() throws Exception {
        businessObjectService = null;
    }
    
    @Test
    public void testNegotiationStatuses() throws Exception {    
        Collection<NegotiationStatus> statuses = businessObjectService.findAll(NegotiationStatus.class);
        assertTrue(statuses.size() > 0);
    }
    
    @Test
    public void testNegotiationAgreementTypes() throws Exception {    
        Collection<NegotiationAgreementType> types = businessObjectService.findAll(NegotiationAgreementType.class);
        assertTrue(types.size() > 0);
    }
    @Test
    public void testNegotiationAssociationTypes() throws Exception {    
        Collection<NegotiationAssociationType> types = businessObjectService.findAll(NegotiationAssociationType.class);
        assertTrue(types.size() > 0);
    }
    
    protected Negotiation getNewNegotiation() {
        
        Negotiation negotiation = new Negotiation();
        negotiation.setNegotiationStatus(status);
        negotiation.setNegotiationAgreementType(agreementType);
        negotiation.setNegotiationAssociationType(associationType);
        
        java.sql.Date testEndDate = new java.sql.Date(2011, 12, 31);
        java.sql.Date testStartDate = new java.sql.Date(2009, 12, 31);
        
        negotiation.setAnticipatedAwardDate(testEndDate);
        negotiation.setNegotiationEndDate(testEndDate);
        negotiation.setNegotiationStartDate(testStartDate);
        negotiation.setDocumentFolder("document folder");
        negotiation.setDocumentNumber("123321");
        negotiation.setNegotiatorPersonId("jdh34");
        negotiation.setNegotiatorName("quickstart");
        
        assertNull(negotiation.getUpdateUser());
        
        businessObjectService.save(negotiation);
        
        assertNotNull(negotiation.getUpdateUser());
        
        return negotiation;
    }
    
    @Test
    public void testNegotiation() throws Exception {
        Negotiation negotiation = getNewNegotiation();

        Map primaryKey = new HashMap();
        primaryKey.put("negotiationId", negotiation.getNegotiationId());
        Negotiation dbNegotation = (Negotiation) businessObjectService.findByPrimaryKey(Negotiation.class, primaryKey);
        
        assertNotNull(dbNegotation.getNegotiationStatus());
        assertEquals(status.getId(), dbNegotation.getNegotiationStatus().getId());
        assertEquals(status.getId(), dbNegotation.getNegotiationStatusId());
        assertEquals(agreementType.getId(), dbNegotation.getNegotiationAgreementType().getId());
        assertEquals(agreementType.getId(), dbNegotation.getNegotiationAgreementTypeId());
        assertEquals(associationType.getId(), dbNegotation.getNegotiationAssociationType().getId());
        assertEquals(associationType.getId(), dbNegotation.getNegotiationAssociationTypeId());
    }
    
    @Test
    public void testNegotiationUnassociatedDetail() throws Exception {
        Map primaryKey = new HashMap();
        primaryKey.put("code", "NO");
        associationType = (NegotiationAssociationType)businessObjectService.findMatching(NegotiationAssociationType.class, primaryKey).iterator().next();
        
        Negotiation negotiation = new Negotiation();
        negotiation.setNegotiationStatus(status);
        negotiation.setNegotiationAgreementType(agreementType);
        negotiation.setNegotiationAssociationType(associationType);
        java.sql.Date testEndDate = new java.sql.Date(2011, 12, 31);
        java.sql.Date testStartDate = new java.sql.Date(2009, 12, 31);
        negotiation.setAnticipatedAwardDate(testEndDate);
        negotiation.setNegotiationEndDate(testEndDate);
        negotiation.setNegotiationStartDate(testStartDate);
        negotiation.setDocumentFolder("document folder");
        negotiation.setDocumentNumber("123321");
        negotiation.setNegotiatorPersonId("jdh34");
        negotiation.setNegotiatorName("quickstart");
        businessObjectService.save(negotiation);
        
        primaryKey = new HashMap();
        primaryKey.put("negotiationId", negotiation.getNegotiationId());
        Negotiation dbNegotation = (Negotiation) businessObjectService.findByPrimaryKey(Negotiation.class, primaryKey);
        
        Organization org = (Organization) businessObjectService.findAll(Organization.class).iterator().next();
        Sponsor sponsor = (Sponsor) businessObjectService.findAll(Sponsor.class).iterator().next();
        Unit unit = (Unit) businessObjectService.findAll(Unit.class).iterator().next();
        
        NegotiationUnassociatedDetail detail = new NegotiationUnassociatedDetail();
        detail.setNegotiation(dbNegotation);
        detail.setLeadUnit(unit);
        detail.setSponsor(sponsor);
        detail.setSponsorCode(sponsor.getSponsorCode());
        detail.setPrimeSponsor(sponsor);
        detail.setSubAwardOrganization(org);
        detail.setTitle("super awesome title");
        detail.setPiPersonId("10000000001");
        detail.setPiRolodexId("212");
        detail.setSponsorAwardNumber("1590431");
        detail.setContactAdminPersonId("jdh34");
        
        businessObjectService.save(detail);
        
        dbNegotation.setAssociatedDocumentId(detail.getNegotiationUnassociatedDetailId().toString());
        
        businessObjectService.save(detail);
        
        primaryKey = new HashMap();
        primaryKey.put("negotiationUnassociatedDetailId", detail.getNegotiationUnassociatedDetailId());
        NegotiationUnassociatedDetail dbDetail = (NegotiationUnassociatedDetail) businessObjectService.findByPrimaryKey(NegotiationUnassociatedDetail.class, primaryKey);        
        
        assertEquals(dbDetail.getNegotiationUnassociatedDetailId().toString(), dbDetail.getNegotiation().getAssociatedDocumentId());
        assertEquals(associationType.getId(), dbDetail.getNegotiation().getNegotiationAssociationType().getId());
        assertEquals(sponsor.getSponsorCode(), dbDetail.getSponsorCode());
        assertEquals(detail.getContactAdminPersonId(), dbDetail.getContactAdminPersonId());

    }
    
    @Test
    public void testActivities() {
        Negotiation negotiation = getNewNegotiation();
        
        NegotiationActivity activity = new NegotiationActivity();
        activity.setLocation(location);
        activity.setActivityType(activityType);
        activity.setStartDate(new Date(2011, 1, 1));
        activity.setDescription("Test Description");
        activity.setCreateDate(new Date(new java.util.Date().getTime()));
        activity.setLastModifiedDate(new Date(new java.util.Date().getTime()));
        activity.setLastModifiedUsername("quickstart");
        negotiation.getActivities().add(activity);
        
        businessObjectService.save(negotiation);
        
        Negotiation negotiation2 = businessObjectService.findBySinglePrimaryKey(Negotiation.class, negotiation.getNegotiationId());
        
        assertTrue(negotiation2.getActivities().size() == 1);
    }

}
