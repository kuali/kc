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
package org.kuali.kra.irb.protocol.funding;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceServiceImpl.FundingSourceLookup;
import org.kuali.kra.irb.test.ProtocolTestUtil;
import org.kuali.kra.proposaldevelopment.bo.LookupableDevelopmentProposal;
import org.kuali.kra.proposaldevelopment.service.LookupableDevelopmentProposalService;
import org.kuali.kra.service.FundingSourceTypeService;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.KNSConstants;

import edu.emory.mathcs.backport.java.util.Collections;

/**
* The JUnit test class for <code>{@link ProtocolFundingSourceServiceImpl}</code>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolFundingSourceServiceTest extends Assert{


    private Mockery context = new JUnit4Mockery();

    private ProtocolFundingSourceServiceImpl protocolFundingSourceService;

    private final String emptyId = "";
    
    private final String sponsorIdAirForce = "000100";
    private final String sponsorNameAirForce = "Air Force";
    private final String sponsorIdBad = "-1";
    private Sponsor sponsorGood;
    
    private final String unitIdGood = "000001";
    private final String unitNameGood = "University";
    private final String unitIdBad = "zzzzz";
    
    private final String otherIdGood = "otherId";
    private final String otherNameGood = "otherName";

    private final String devProposalIdGood = "1";
    private final String devProposalTitleGood = "DevPropTitle";
    private final String devProposalIdBad = "zzzzz";
    private LookupableDevelopmentProposal  devProposalGood;
    
    private final String instProposalIdGood = "99";
    private final String instProposalNumberGood = "00000001";
    private final String instProposalTitleGood = "Institutional Proposal Title";
    private final String instProposalIdBad = "zzzzz";
    private final String instProposalNumberBad = "zzzzzzzz";
    private InstitutionalProposal instProposalGood;
    
    private final String awardIdGood = "1";
    private final String awardNumberGood = "000001-00001";
    private final String awardTitleGood = "AwardTitle";
    private final String awardIdBad = "zzzzz";
    private final String awardNumberBad = "zzzzzz-zzzzz";
    private Award awardGood;
    
    private final String sponsorSourceTypeId = "1";
    private final String unitSourceTypeId = "2";
    private final String otherSourceTypeId = "3";
    private final String developmentPropSourceTypeId = "4";
    private final String institutePropSourceTypeId = "5";
    private final String awardSourceTypeId = "6";

    private final String protocolFundingSource = "protocolHelper.newFundingSource.fundingSource";
    private final String protocolFundingSourceNumber = "protocolHelper.newFundingSource.fundingSourceNumber";
    private final String protocolFundingSourceName = "protocolHelper.newFundingSource.fundingSourceName";
    private final String protocolFundingSourceTitle = "protocolHelper.newFundingSource.fundingSourceTitle";
    
    private FundingSourceType fundingSponsorSourceType;
    private FundingSourceType fundingUnitSourceType;
    private FundingSourceType fundingOtherSourceType;
    private FundingSourceType fundingDevProposalSourceType;
    private FundingSourceType fundingInstProposalSourceType;
    private FundingSourceType fundingAwardSourceType;
    
    private BusinessObjectService businessObjectService;
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Create the mock services and insert them into the protocol auth service.
     * 
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        
        fundingSponsorSourceType = new FundingSourceType();
        fundingSponsorSourceType.setFundingSourceTypeCode(1);
        fundingSponsorSourceType.setFundingSourceTypeFlag(true);
        fundingSponsorSourceType.setDescription("Sponsor");
        fundingUnitSourceType = new FundingSourceType();
        fundingUnitSourceType.setFundingSourceTypeCode(2);
        fundingUnitSourceType.setFundingSourceTypeFlag(true);
        fundingUnitSourceType.setDescription("Unit");
        fundingOtherSourceType= new FundingSourceType();
        fundingOtherSourceType.setFundingSourceTypeCode(3);
        fundingOtherSourceType.setFundingSourceTypeFlag(true);
        fundingOtherSourceType.setDescription("Other");
        fundingDevProposalSourceType= new FundingSourceType();
        fundingDevProposalSourceType.setFundingSourceTypeCode(4);
        fundingDevProposalSourceType.setFundingSourceTypeFlag(true);
        fundingDevProposalSourceType.setDescription("Proposal Development");
        fundingInstProposalSourceType= new FundingSourceType();
        fundingInstProposalSourceType.setFundingSourceTypeCode(5);
        fundingInstProposalSourceType.setFundingSourceTypeFlag(true);
        fundingInstProposalSourceType.setDescription("Institutional Proposal");
        fundingAwardSourceType= new FundingSourceType();
        fundingAwardSourceType.setFundingSourceTypeCode(6);
        fundingAwardSourceType.setFundingSourceTypeFlag(true);
        fundingAwardSourceType.setDescription("Award");
        
        sponsorGood = new Sponsor();
        sponsorGood.setSponsorName(sponsorNameAirForce);
        sponsorGood.setSponsorCode(sponsorIdAirForce);
        
        devProposalGood = new LookupableDevelopmentProposal();
        devProposalGood.setTitle(devProposalTitleGood);
        devProposalGood.setSponsor(sponsorGood);
        
        instProposalGood = new InstitutionalProposal();
        instProposalGood.setTitle(instProposalTitleGood);
        instProposalGood.setSponsor(sponsorGood);

        awardGood = new Award();
        awardGood.setTitle(awardTitleGood);
        awardGood.setSponsor(sponsorGood);
    }    

    @Test
    public void testCalculateSponsorFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setSponsorService(getSponsorService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource 
            = protocolFundingSourceService.updateProtocolFundingSource(sponsorSourceTypeId, sponsorIdAirForce, sponsorIdAirForce, null);
        assertNotNull(fundingSource);
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
    }
    
    @Test
    public void testCalculateSponsorFundingSourceBadId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setSponsorService(getSponsorService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(sponsorSourceTypeId, sponsorIdBad, sponsorIdBad, null);
        assertNotNull(fundingSource);
        assertNull(fundingSource.getFundingSourceName());
    }
    
    @Test
    public void testCalculateSponsorFundingSourceEmptyId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setSponsorService(getSponsorService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(sponsorSourceTypeId, emptyId, emptyId, null);
        assertNull(fundingSource);
    }   
    
    @Test
    public void testCalculateUnitFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setUnitService(getUnitService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(unitSourceTypeId, unitIdGood, unitIdGood, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(unitNameGood));
    }   
    
    @Test
    public void testCalculateUnitFundingSourceBadId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setUnitService(getUnitService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(unitSourceTypeId, unitIdBad, unitIdBad, null);
        assertNotNull(fundingSource);
        assertNull(fundingSource.getFundingSourceName());
    }    

    @Test
    public void testCalculateUnitFundingSourceEmptyId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        final UnitService unitService = context.mock(UnitService.class);

        protocolFundingSourceService.setUnitService(unitService);
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(unitSourceTypeId, emptyId, emptyId, null);
        assertNull(fundingSource);
    }
    
    @Test
    public void testCalculateOtherFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource 
            = protocolFundingSourceService.updateProtocolFundingSource(otherSourceTypeId, otherIdGood, otherIdGood, otherNameGood);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(otherNameGood));
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceTitle()));
    } 
    
    @Test
    public void testCalculateOtherFundingEmptyId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(otherSourceTypeId, emptyId, emptyId, null);
        assertNull(fundingSource);
    }
    
    @Test
    public void testCalculateDevProposalFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setLookupableDevelopmentProposalService(getDevProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource 
            = protocolFundingSourceService.updateProtocolFundingSource(developmentPropSourceTypeId, devProposalIdGood, devProposalIdGood, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(devProposalTitleGood));
    } 
    
    @Test
    public void testCalculateDevProposalFundingBadID() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setLookupableDevelopmentProposalService(getDevProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource 
            = protocolFundingSourceService.updateProtocolFundingSource(developmentPropSourceTypeId, devProposalIdBad, devProposalIdBad, null);
        assertNotNull(fundingSource);
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceName()));
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceTitle()));
    } 
    
    @Test
    public void testCalculateDevProposalFundingNegativeEmptyID() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setLookupableDevelopmentProposalService(getDevProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(developmentPropSourceTypeId, emptyId, emptyId, null);
        assertNull(fundingSource);
    }

    @Test
    public void testCalculateInstProposalFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setInstitutionalProposalService(getInstProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setBusinessObjectService(getBusinessObjectService());
        
        ProtocolFundingSource fundingSource  = 
            protocolFundingSourceService.updateProtocolFundingSource(institutePropSourceTypeId, instProposalIdGood, instProposalNumberGood, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(instProposalTitleGood));
    }     
    
    @Test
    public void testCalculateInstProposalFundingBadIdGoodNumber() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setInstitutionalProposalService(getInstProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setBusinessObjectService(getBusinessObjectService());
        
        ProtocolFundingSource fundingSource 
            = protocolFundingSourceService.updateProtocolFundingSource(institutePropSourceTypeId, instProposalIdBad, instProposalNumberGood, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(instProposalTitleGood));
    }
    
    @Test
    public void testCalculateInstProposalFundingBadIdBadNumber() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setInstitutionalProposalService(getInstProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setBusinessObjectService(getBusinessObjectService());
        
        ProtocolFundingSource fundingSource 
            = protocolFundingSourceService.updateProtocolFundingSource(institutePropSourceTypeId, instProposalIdBad, instProposalNumberBad, null);
        assertNotNull(fundingSource);
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceName()));
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceTitle()));
    } 
    
    @Test
    public void testCalculateInstProposalFundingNegativeEmpty() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setInstitutionalProposalService(getInstProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setBusinessObjectService(getBusinessObjectService());
        
        ProtocolFundingSource fundingSource  
            = protocolFundingSourceService.updateProtocolFundingSource(institutePropSourceTypeId, emptyId, emptyId, null);
        assertNull(fundingSource);
    }
    
    @Test
    public void testCalculateAwardFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();

        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setParameterService(getParameterService());
        
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(awardSourceTypeId, awardIdGood, awardNumberGood, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(awardTitleGood));
    }
    
    @Test
    public void testCalculateAwardFundingBadIdGoodNumber() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setParameterService(getParameterService());

        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(awardSourceTypeId, awardIdBad, awardNumberGood, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(awardTitleGood));
    } 

    @Test
    public void testCalculateAwardFundingBadIdBadNumber() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setParameterService(getParameterService());

        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(awardSourceTypeId, awardIdBad, awardNumberBad, null);
        assertNotNull(fundingSource);
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceName()));
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceTitle()));
    } 
    
    @Test
    public void testCalculateAwardFundingEmptyId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setParameterService(getParameterService());

        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(awardSourceTypeId, emptyId, emptyId, null);
        assertNull(fundingSource);
    } 
    
    @Test
    public void testIsValidIdForTypeSponsor() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());    
        protocolFundingSourceService.setSponsorService(getSponsorService());       

        ProtocolFundingSource fundingSource = new ProtocolFundingSource(sponsorIdAirForce, fundingSponsorSourceType, null, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(sponsorIdBad, fundingSponsorSourceType, null, null);
        assertFalse(protocolFundingSourceService.isValidIdForType(fundingSource));
    } 
    
    @Test
    public void testIsValidIdForTypeUnit() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());    
        protocolFundingSourceService.setUnitService(getUnitService());       

        ProtocolFundingSource fundingSource = new ProtocolFundingSource(unitIdGood, fundingUnitSourceType, null, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(unitIdBad, fundingUnitSourceType, null, null);
        assertFalse(protocolFundingSourceService.isValidIdForType(fundingSource));
    }
    
    @Test
    public void testIsValidIdForTypeOther() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());       
        ProtocolFundingSource fundingSource = new ProtocolFundingSource(otherSourceTypeId, fundingOtherSourceType, "otherName", null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(otherSourceTypeId, fundingOtherSourceType, emptyId, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
    } 
    
    @Test
    public void testIsValidIdForTypeAward() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());    
        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setParameterService(getParameterService());
        ProtocolFundingSource fundingSource = new ProtocolFundingSource(awardIdGood, awardNumberGood, fundingAwardSourceType, null, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(awardIdBad, awardNumberBad, fundingAwardSourceType, null, null);
        assertFalse(protocolFundingSourceService.isValidIdForType(fundingSource));
    } 

    @Test
    public void testGetLookupParameters() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        StringBuilder builder = new StringBuilder();
        
        Entry<String, String> entry = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.SPONSOR.getTypeCode());
        Assert.assertNotNull(entry);
        builder.append("sponsorCode:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("sponsorName:" + protocolFundingSourceName);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());

        entry = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.UNIT.getTypeCode());
        Assert.assertNotNull(entry);
        builder.append("unitNumber:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("unitName:" + protocolFundingSourceName);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());
        
        entry = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getTypeCode());
        Assert.assertNotNull(entry);
        builder.append("proposalNumber:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("sponsor.sponsorName:" + protocolFundingSourceName + Constants.COMMA);
        builder.append("title:" + protocolFundingSourceTitle);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());

        entry = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.INSTITUTIONAL_PROPOSAL.getTypeCode());
        Assert.assertNotNull(entry);
        builder.append("proposalId:" + protocolFundingSource + Constants.COMMA);
        builder.append("proposalNumber:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("sponsor.sponsorName:" + protocolFundingSourceName + Constants.COMMA);
        builder.append("title:" + protocolFundingSourceTitle);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());
        
        entry = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.AWARD.getTypeCode());
        Assert.assertNotNull(entry);
        builder.append("awardId:" + protocolFundingSource + Constants.COMMA);
        builder.append("awardNumber:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("sponsor.sponsorName:" + protocolFundingSourceName + Constants.COMMA);
        builder.append("title:" + protocolFundingSourceTitle);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
            
        try {
            entry = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.OTHER.getTypeCode());
            fail("IllegalArgumentException was not thrown for invalid test case using OTHER");
        } catch (IllegalArgumentException e) {
            //yup
        }

    }
    
    @Test
    public void testUpdateLookupParameter() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        Entry<String, String> entry = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.SPONSOR.getTypeCode());
        Assert.assertNotNull(entry);
        String fieldConversions = entry.getValue();
        StringBuilder builder = new StringBuilder();
        builder.append("sponsorCode:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("sponsorName:" + protocolFundingSourceName);
        
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        String parameter = KNSConstants.METHOD_TO_CALL_BOPARM_LEFT_DEL 
                            + KNSConstants.METHOD_TO_CALL_BOPARM_RIGHT_DEL
                            + KNSConstants.METHOD_TO_CALL_PARM1_LEFT_DEL
                            + KNSConstants.METHOD_TO_CALL_PARM1_RIGHT_DEL;
        String updatedParam 
            = protocolFundingSourceService.updateLookupParameter(parameter, FundingSourceLookup.SPONSOR.getBOClass().getName(), fieldConversions);
        Assert.assertThat(updatedParam, JUnitMatchers.containsString("(!!org.kuali.kra.bo.Sponsor!!)(((" + builder.toString() + ")))"));

    }
    
  @Test
  public void testIsViewableFundingSource() throws Exception {
      int badFundingTypeCode = -99;
      protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
      protocolFundingSourceService.setParameterService(getParameterService());

      
      assertFalse(protocolFundingSourceService.isViewable(FundingSourceLookup.OTHER.getTypeCode()));
      assertFalse(protocolFundingSourceService.isViewable(badFundingTypeCode));

      assertTrue(protocolFundingSourceService.isViewable(FundingSourceLookup.INSTITUTIONAL_PROPOSAL.getTypeCode()));
      assertTrue(protocolFundingSourceService.isViewable(FundingSourceLookup.UNIT.getTypeCode()));
      assertTrue(protocolFundingSourceService.isViewable(FundingSourceLookup.SPONSOR.getTypeCode()));
      assertTrue(protocolFundingSourceService.isViewable(FundingSourceLookup.AWARD.getTypeCode()));
      assertTrue(protocolFundingSourceService.isViewable(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getTypeCode()));
  }
  
  @Test
  public void testUpdateSourceNameEditable() throws Exception {
      protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
      protocolFundingSourceService.setParameterService(getParameterService());
      
      assertFalse(protocolFundingSourceService.updateSourceNameEditable(Integer.toString(FundingSourceLookup.INSTITUTIONAL_PROPOSAL.getTypeCode())));
      assertTrue(protocolFundingSourceService.updateSourceNameEditable(Integer.toString(FundingSourceLookup.OTHER.getTypeCode())));
      
      assertFalse(protocolFundingSourceService.updateSourceNameEditable(Integer.toString(FundingSourceLookup.AWARD.getTypeCode())));
      assertFalse(protocolFundingSourceService.updateSourceNameEditable(Integer.toString(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getTypeCode())));
      assertFalse(protocolFundingSourceService.updateSourceNameEditable(Integer.toString(FundingSourceLookup.SPONSOR.getTypeCode())));
      assertFalse(protocolFundingSourceService.updateSourceNameEditable(Integer.toString(FundingSourceLookup.UNIT.getTypeCode())));
      
  }
    
    @Test
    public void testAddDeleteSponsorFundingSource() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setSponsorService(getSponsorService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setParameterService(getParameterService());

        ProtocolFundingSource fundingSource 
            = protocolFundingSourceService.updateProtocolFundingSource(sponsorSourceTypeId, sponsorIdAirForce, sponsorIdAirForce, null);
        assertNotNull(fundingSource);
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));

        Protocol protocol = ProtocolTestUtil.getProtocol(this.context);

        ArrayList<ProtocolFundingSource> protocolFundingSources = new ArrayList<ProtocolFundingSource>();
        protocolFundingSources.add(fundingSource);
        protocol.setProtocolFundingSources(protocolFundingSources);

        assertFalse(protocol.getProtocolFundingSources().isEmpty());
        protocolFundingSourceService.deleteProtocolFundingSource(protocol, 0);
        assertTrue(protocol.getProtocolFundingSources().isEmpty());
    }

    protected SponsorService getSponsorService() {
        final SponsorService sponsorService = context.mock(SponsorService.class);
        context.checking(new Expectations() {{
            allowing(sponsorService).getSponsorName(sponsorIdAirForce); 
            will(returnValue(sponsorNameAirForce));
            allowing(sponsorService).getSponsorName(sponsorIdBad); 
            will(returnValue(null));
        }});
        return sponsorService;
    }
    
    protected UnitService getUnitService() {
        final UnitService unitService = context.mock(UnitService.class);
        context.checking(new Expectations() {{
            allowing(unitService).getUnitName(unitIdGood); 
            will(returnValue(unitNameGood));
            allowing(unitService).getUnitName(unitIdBad); 
            will(returnValue(null));
        }});
        return unitService;
    }

    protected LookupableDevelopmentProposalService getDevProposalService() {
        final LookupableDevelopmentProposalService lookupableDevelopmentProposalService = context.mock(LookupableDevelopmentProposalService.class);
        context.checking(new Expectations() {{
            allowing(lookupableDevelopmentProposalService).getLookupableDevelopmentProposal(devProposalIdGood); 
            will(returnValue(devProposalGood));
            allowing(lookupableDevelopmentProposalService).getLookupableDevelopmentProposal(devProposalIdBad); 
            will(returnValue(null));
        }});
        return lookupableDevelopmentProposalService;
    }
    
    protected InstitutionalProposalService getInstProposalService() {
        final InstitutionalProposalService institutionalProposalService = context.mock(InstitutionalProposalService.class);
        context.checking(new Expectations() {{
            allowing(institutionalProposalService).getInstitutionalProposal(instProposalIdGood); 
            will(returnValue(instProposalGood));
            allowing(institutionalProposalService).getInstitutionalProposal(instProposalIdBad); 
            will(returnValue(null));
            allowing(institutionalProposalService).getActiveInstitutionalProposalVersion(instProposalNumberGood); 
            will(returnValue(instProposalGood));
            allowing(institutionalProposalService).getActiveInstitutionalProposalVersion(instProposalNumberBad); 
            will(returnValue(null));
        }});
        return institutionalProposalService;
    }
    
    @SuppressWarnings("deprecation")
    protected AwardService getAwardService() {
        final AwardService awardService = context.mock(AwardService.class);
        context.checking(new Expectations() {{
            allowing(awardService).getAward(Long.parseLong(awardIdGood)); 
            will(returnValue(awardGood));
            allowing(awardService).getAward(awardIdBad); 
            will(returnValue(null));
            allowing(awardService).findAwardsForAwardNumber(awardNumberGood); 
            will(returnValue(Collections.singletonList(awardGood)));
            allowing(awardService).findAwardsForAwardNumber(awardNumberBad); 
            will(returnValue(Collections.emptyList()));
        }});
        return awardService;
    }

    protected ParameterService getParameterService() {
        final ParameterService parameterService = context.mock(ParameterService.class);
        context.checking(new Expectations() {{
            allowing(parameterService).parameterExists(with(any(Class.class)), with(any(String.class))); 
            will(returnValue(true));
            allowing(parameterService).getIndicatorParameter( ProtocolDocument.class, Constants.ENABLE_PROTOCOL_TO_AWARD_LINK ); 
            will(returnValue(true));
            allowing(parameterService).getIndicatorParameter(ProtocolDocument.class, Constants.ENABLE_PROTOCOL_TO_DEV_PROPOSAL_LINK ); 
            will(returnValue(true));
            allowing(parameterService).getIndicatorParameter(ProtocolDocument.class, Constants.ENABLE_PROTOCOL_TO_PROPOSAL_LINK ); 
            will(returnValue(true));
        }});
        return parameterService;
    }

    protected FundingSourceTypeService getFundingSourceTypeService() {
        final FundingSourceTypeService service = context.mock(FundingSourceTypeService.class);
        context.checking(new Expectations() {{
            allowing(service).getFundingSourceType(sponsorSourceTypeId); 
            will(returnValue(fundingSponsorSourceType));
            
            allowing(service).getFundingSourceType(unitSourceTypeId); 
            will(returnValue(fundingUnitSourceType));
            
            allowing(service).getFundingSourceType(otherSourceTypeId); 
            will(returnValue(fundingOtherSourceType));
            
            allowing(service).getFundingSourceType(developmentPropSourceTypeId); 
            will(returnValue(fundingDevProposalSourceType));
            
            allowing(service).getFundingSourceType(institutePropSourceTypeId); 
            will(returnValue(fundingInstProposalSourceType));
            
            allowing(service).getFundingSourceType(awardSourceTypeId); 
            will(returnValue(fundingAwardSourceType));
        }});
        return service;
    }

}