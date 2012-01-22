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
package org.kuali.kra.negotiations.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
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
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationAgreementType;
import org.kuali.kra.negotiations.bo.NegotiationAssociatedDetailBean;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.bo.NegotiationStatus;
import org.kuali.kra.negotiations.bo.NegotiationUnassociatedDetail;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

public class NegotiationServiceImplTest extends KcUnitTestBase {
    
    private NegotiationService negotiationService;
    private BusinessObjectService businessObjectService;

    @Before
    public void setUp() throws Exception {
        negotiationService = KraServiceLocator.getService(NegotiationService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        negotiationService = null;
        businessObjectService = null;
    }

    @Test
    public void testGetInProgressStatusCodes() {
        List<String> inProgressCodes = negotiationService.getInProgressStatusCodes();
        assertEquals(1, inProgressCodes.size());
    }

    @Test
    public void testGetCompletedStatusCodes() {
        List<String> completed = negotiationService.getCompletedStatusCodes();
        assertEquals(2, completed.size());
    }

    @Test
    public void testBuildNegotiationAssociatedDetailBean1() {
        Negotiation negotiation = getBasicNegotiation();
        negotiation.setNegotiationAssociationType(getNegotiationAssociationType("NO"));
        this.businessObjectService.save(negotiation);
        
        Organization org = (Organization) businessObjectService.findAll(Organization.class).iterator().next();
        Sponsor sponsor = (Sponsor) businessObjectService.findAll(Sponsor.class).iterator().next();
        Unit unit = (Unit) businessObjectService.findAll(Unit.class).iterator().next();
        
        NegotiationUnassociatedDetail detail = new NegotiationUnassociatedDetail();
        detail.setNegotiation(negotiation);
        detail.setLeadUnit(unit);
        detail.setLeadUnitNumber(unit.getUnitNumber());
        detail.setSponsor(sponsor);
        detail.setSponsorCode(sponsor.getSponsorCode());
        detail.setPrimeSponsor(sponsor);
        detail.setPrimeSponsorCode(sponsor.getSponsorCode());
        detail.setSubAwardOrganization(org);
        detail.setSubAwardOrganizationId(org.getOrganizationId());
        detail.setTitle("super awesome title");
        detail.setPiPersonId("10000000001");
        detail.setPiRolodexId("306");
        detail.setSponsorAwardNumber("1590431");
        detail.setContactAdminPersonId("10000000001");
        
        this.businessObjectService.save(detail);
        detail.refresh();
        
        negotiation.setAssociatedDocumentId(detail.getNegotiationId().toString());
        negotiation.setUnAssociatedDetail(detail);
        this.businessObjectService.save(negotiation);
        
        NegotiationAssociatedDetailBean bean = this.negotiationService.buildNegotiationAssociatedDetailBean(negotiation);
    }
    
    private Negotiation getBasicNegotiation() {
        NegotiationStatus status = (NegotiationStatus)businessObjectService.findAll(NegotiationStatus.class).iterator().next();
        NegotiationAgreementType agreementType = (NegotiationAgreementType)businessObjectService.findAll(NegotiationAgreementType.class).iterator().next();
        //NegotiationAssociationType associationType = (NegotiationAssociationType)businessObjectService.findAll(NegotiationAssociationType.class).iterator().next();
        
        Negotiation negotiation = new Negotiation();
        negotiation.setNegotiationStatus(status);
        negotiation.setNegotiationAgreementType(agreementType);
        
        //negotiation.setNegotiationAssociationType(associationType);
        
        java.sql.Date testEndDate = new java.sql.Date(2011, 12, 31);
        java.sql.Date testStartDate = new java.sql.Date(2009, 12, 31);
        negotiation.setAnticipatedAwardDate(testEndDate);
        negotiation.setNegotiationEndDate(testEndDate);
        negotiation.setNegotiationStartDate(testStartDate);
        negotiation.setDocumentFolder("document folder");
        negotiation.setDocumentNumber("123321");
        negotiation.setNegotiatorPersonId("10000000001");
        return negotiation;
    }
    
    private NegotiationAssociationType getNegotiationAssociationType(String code) {
        Map<String,String> codes = new HashMap<String, String>();
        codes.put("NEGOTIATION_ASSC_TYPE_CODE", code);
        return (NegotiationAssociationType) this.businessObjectService.findMatching(NegotiationAssociationType.class, codes).iterator().next();
    }
    
    @Test
    public void testIsAwardLinkingEnabled() {
        boolean checkVal = this.negotiationService.isAwardLinkingEnabled();
        assertTrue(checkVal);
        
        NegotiationAssociationType awardType = this.negotiationService.getNegotiationAssociationType(NegotiationAssociationType.AWARD_ASSOCIATION);
        awardType.setActive(false);
        this.businessObjectService.save(awardType);
        
        checkVal = this.negotiationService.isAwardLinkingEnabled();
        assertFalse(checkVal);
    }
    
    @Test
    public void testIsInstitutionalProposalLinkingEnabled() {
        boolean checkVal = this.negotiationService.isInstitutionalProposalLinkingEnabled();
        assertTrue(checkVal);
    }
    
    @Test
    public void testIsNoModuleLinkingEnabled() {
        boolean checkVal = this.negotiationService.isNoModuleLinkingEnabled();
        assertTrue(checkVal);
    }
    
    @Test
    public void testIsProposalLogLinkingEnabled() {
        boolean checkVal = this.negotiationService.isProposalLogLinkingEnabled();
        assertTrue(checkVal);
    }
    
    @Test
    public void testIsSubawardLinkingEnabled() {
        boolean checkVal = this.negotiationService.isSubawardLinkingEnabled();
        assertTrue(checkVal);
    }
}