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
package org.kuali.kra.negotiations.bo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class NegotiationBasicTest extends KcIntegrationTestBase {
    
    BusinessObjectService businessObjectService;
    NegotiationStatus status;
    NegotiationAgreementType agreementType;
    NegotiationAssociationType associationType;
    NegotiationLocation location;
    NegotiationActivityType activityType;
    
    @Before
    public void setUp() throws Exception {
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        
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
        negotiation.setNegotiatorPersonId("cate");
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
        negotiation.setNegotiatorPersonId("cate");
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
        detail.setContactAdminPersonId("cate");
        
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
