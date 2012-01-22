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

import java.util.Collections;
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
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.service.FundingSourceTypeService;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.UnitService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.KRADConstants;

/**
* The JUnit test class for <code>{@link ProtocolFundingSourceServiceImpl}</code>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolFundingSourceServiceTest extends KcUnitTestBase {

    private Mockery context = new JUnit4Mockery();

    private ProtocolFundingSourceServiceImpl protocolFundingSourceService;

    private final String emptyNumber = "";
    
    private final String sponsorNumberAirForce = "000100";
    private final String sponsorNameAirForce = "Air Force";
    private final String sponsorNumberBad = "-1";
    private Sponsor sponsorGood;
    
    private final String unitNumberGood = "000001";
    private final String unitNameGood = "University";
    private final String unitNumberBad = "zzzzz";
    
    private final String otherNumberGood = "otherId";
    private final String otherNameGood = "otherName";

    private final String devProposalNumberGood = "1";
    private final String devProposalTitleGood = "DevPropTitle";
    private final String devProposalNumberBad = "zzzzz";
    private DevelopmentProposal  devProposalGood;
    
    private final String instProposalNumberGood = "00000001";
    private final String instProposalTitleGood = "Institutional Proposal Title";
    private final String instProposalNumberBad = "zzzzzzzz";
    private InstitutionalProposal instProposalGood;
    
    private final String awardNumberGood = "000001-00001";
    private final String awardTitleGood = "AwardTitle";
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

    /**
     * Create the mock services and insert them into the protocol auth service.
     * 
     * @see org.kuali.kra.KraTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        fundingSponsorSourceType = new FundingSourceType();
        fundingSponsorSourceType.setFundingSourceTypeCode(FundingSourceType.SPONSOR);
        fundingSponsorSourceType.setFundingSourceTypeFlag(true);
        fundingSponsorSourceType.setDescription("Sponsor");
        fundingUnitSourceType = new FundingSourceType();
        fundingUnitSourceType.setFundingSourceTypeCode(FundingSourceType.UNIT);
        fundingUnitSourceType.setFundingSourceTypeFlag(true);
        fundingUnitSourceType.setDescription("Unit");
        fundingOtherSourceType= new FundingSourceType();
        fundingOtherSourceType.setFundingSourceTypeCode(FundingSourceType.OTHER);
        fundingOtherSourceType.setFundingSourceTypeFlag(true);
        fundingOtherSourceType.setDescription("Other");
        fundingDevProposalSourceType= new FundingSourceType();
        fundingDevProposalSourceType.setFundingSourceTypeCode(FundingSourceType.PROPOSAL_DEVELOPMENT);
        fundingDevProposalSourceType.setFundingSourceTypeFlag(true);
        fundingDevProposalSourceType.setDescription("Proposal Development");
        fundingInstProposalSourceType= new FundingSourceType();
        fundingInstProposalSourceType.setFundingSourceTypeCode(FundingSourceType.INSTITUTIONAL_PROPOSAL);
        fundingInstProposalSourceType.setFundingSourceTypeFlag(true);
        fundingInstProposalSourceType.setDescription("Institutional Proposal");
        fundingAwardSourceType= new FundingSourceType();
        fundingAwardSourceType.setFundingSourceTypeCode(FundingSourceType.AWARD);
        fundingAwardSourceType.setFundingSourceTypeFlag(true);
        fundingAwardSourceType.setDescription("Award");
        
        sponsorGood = new Sponsor();
        sponsorGood.setSponsorName(sponsorNameAirForce);
        sponsorGood.setSponsorCode(sponsorNumberAirForce);
        
        devProposalGood = new DevelopmentProposal();
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
            = protocolFundingSourceService.updateProtocolFundingSource(sponsorSourceTypeId, sponsorNumberAirForce, null);
        assertNotNull(fundingSource);
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
    }
    
    @Test
    public void testCalculateSponsorFundingSourceBadId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setSponsorService(getSponsorService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(sponsorSourceTypeId, sponsorNumberBad, null);
        assertNotNull(fundingSource);
        assertNull(fundingSource.getFundingSourceName());
    }
    
    @Test
    public void testCalculateSponsorFundingSourceEmptyId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setSponsorService(getSponsorService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(sponsorSourceTypeId, emptyNumber, null);
        assertNull(fundingSource);
    }   
    
    @Test
    public void testCalculateUnitFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setUnitService(getUnitService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(unitSourceTypeId, unitNumberGood, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(unitNameGood));
    }   
    
    @Test
    public void testCalculateUnitFundingSourceBadId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setUnitService(getUnitService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(unitSourceTypeId, unitNumberBad, null);
        assertNotNull(fundingSource);
        assertNull(fundingSource.getFundingSourceName());
    }    

    @Test
    public void testCalculateUnitFundingSourceEmptyId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        final UnitService unitService = context.mock(UnitService.class);

        protocolFundingSourceService.setUnitService(unitService);
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(unitSourceTypeId, emptyNumber, null);
        assertNull(fundingSource);
    }
    
    @Test
    public void testCalculateOtherFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource 
            = protocolFundingSourceService.updateProtocolFundingSource(otherSourceTypeId, otherNumberGood, otherNameGood);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(otherNameGood));
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceTitle()));
    } 
    
    @Test
    public void testCalculateOtherFundingEmptyId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(otherSourceTypeId, emptyNumber, null);
        assertNull(fundingSource);
    }
    
    @Test
    public void testCalculateDevProposalFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setBusinessObjectService(getBusinessObjectService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource 
            = protocolFundingSourceService.updateProtocolFundingSource(developmentPropSourceTypeId, devProposalNumberGood, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(devProposalTitleGood));
    } 
    
    @Test
    public void testCalculateDevProposalFundingBadID() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setBusinessObjectService(getBusinessObjectService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource 
            = protocolFundingSourceService.updateProtocolFundingSource(developmentPropSourceTypeId, devProposalNumberBad, null);
        assertNotNull(fundingSource);
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceName()));
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceTitle()));
    } 
    
    @Test
    public void testCalculateDevProposalFundingNegativeEmptyID() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setBusinessObjectService(getBusinessObjectService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(developmentPropSourceTypeId, emptyNumber, null);
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
            protocolFundingSourceService.updateProtocolFundingSource(institutePropSourceTypeId, instProposalNumberGood, null);
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
            = protocolFundingSourceService.updateProtocolFundingSource(institutePropSourceTypeId, instProposalNumberGood, null);
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
            = protocolFundingSourceService.updateProtocolFundingSource(institutePropSourceTypeId, instProposalNumberBad, null);
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
            = protocolFundingSourceService.updateProtocolFundingSource(institutePropSourceTypeId, emptyNumber, null);
        assertNull(fundingSource);
    }
    
    @Test
    public void testCalculateAwardFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();

        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setParameterService(getParameterService());
        
        ProtocolFundingSource fundingSource = protocolFundingSourceService.updateProtocolFundingSource(awardSourceTypeId, awardNumberGood, null);
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

        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(awardSourceTypeId, awardNumberGood, null);
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

        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(awardSourceTypeId, awardNumberBad, null);
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

        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(awardSourceTypeId, emptyNumber, null);
        assertNull(fundingSource);
    } 
    
    @Test
    public void testIsValidIdForTypeSponsor() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());    
        protocolFundingSourceService.setSponsorService(getSponsorService());       

        ProtocolFundingSource fundingSource = new ProtocolFundingSource(sponsorNumberAirForce, FundingSourceType.SPONSOR, null, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(sponsorNumberBad, FundingSourceType.SPONSOR, null, null);
        assertFalse(protocolFundingSourceService.isValidIdForType(fundingSource));
    } 
    
    @Test
    public void testIsValidIdForTypeUnit() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());    
        protocolFundingSourceService.setUnitService(getUnitService());       

        ProtocolFundingSource fundingSource = new ProtocolFundingSource(unitNumberGood, FundingSourceType.UNIT, null, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(unitNumberBad, FundingSourceType.UNIT, null, null);
        assertFalse(protocolFundingSourceService.isValidIdForType(fundingSource));
    }
    
    @Test
    public void testIsValidIdForTypeOther() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());       
        ProtocolFundingSource fundingSource = new ProtocolFundingSource(otherSourceTypeId, FundingSourceType.OTHER, "otherName", null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(otherSourceTypeId, FundingSourceType.OTHER, emptyNumber, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
    } 
    
    @Test
    public void testIsValidIdForTypeAward() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());    
        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setParameterService(getParameterService());
        ProtocolFundingSource fundingSource = new ProtocolFundingSource(awardNumberGood, FundingSourceType.AWARD, null, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(awardNumberBad, FundingSourceType.AWARD, null, null);
        assertFalse(protocolFundingSourceService.isValidIdForType(fundingSource));
    } 

    @Test
    public void testGetLookupParameters() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        StringBuilder builder = new StringBuilder();
        
        Entry<String, String> entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.SPONSOR);
        Assert.assertNotNull(entry);
        builder.append("sponsorCode:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("sponsorName:" + protocolFundingSourceName);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());

        entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.UNIT);
        Assert.assertNotNull(entry);
        builder.append("unitNumber:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("unitName:" + protocolFundingSourceName);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());
        
        entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.PROPOSAL_DEVELOPMENT);
        Assert.assertNotNull(entry);
        builder.append("proposalNumber:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("sponsor.sponsorName:" + protocolFundingSourceName + Constants.COMMA);
        builder.append("title:" + protocolFundingSourceTitle);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());

        entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.INSTITUTIONAL_PROPOSAL);
        Assert.assertNotNull(entry);
        builder.append("proposalId:" + protocolFundingSource + Constants.COMMA);
        builder.append("proposalNumber:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("sponsor.sponsorName:" + protocolFundingSourceName + Constants.COMMA);
        builder.append("title:" + protocolFundingSourceTitle);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());
        
        entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.AWARD);
        Assert.assertNotNull(entry);
        builder.append("awardId:" + protocolFundingSource + Constants.COMMA);
        builder.append("awardNumber:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("sponsor.sponsorName:" + protocolFundingSourceName + Constants.COMMA);
        builder.append("title:" + protocolFundingSourceTitle);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
            
        try {
            entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.OTHER);
            fail("IllegalArgumentException was not thrown for invalid test case using OTHER");
        } catch (IllegalArgumentException e) {
            //yup
        }

    }
    
    @Test
    public void testUpdateLookupParameter() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        Entry<String, String> entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.SPONSOR);
        Assert.assertNotNull(entry);
        String fieldConversions = entry.getValue();
        StringBuilder builder = new StringBuilder();
        builder.append("sponsorCode:" + protocolFundingSourceNumber + Constants.COMMA);
        builder.append("sponsorName:" + protocolFundingSourceName);
        
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        String parameter = KRADConstants.METHOD_TO_CALL_BOPARM_LEFT_DEL 
                            + KRADConstants.METHOD_TO_CALL_BOPARM_RIGHT_DEL
                            + KRADConstants.METHOD_TO_CALL_PARM1_LEFT_DEL
                            + KRADConstants.METHOD_TO_CALL_PARM1_RIGHT_DEL;
        String updatedParam 
            = protocolFundingSourceService.updateLookupParameter(parameter, "org.kuali.kra.bo.Sponsor", fieldConversions);
        Assert.assertThat(updatedParam, JUnitMatchers.containsString("(!!org.kuali.kra.bo.Sponsor!!)(((" + builder.toString() + ")))"));

    }
    
  @Test
  public void testIsLookupableFundingSource() throws Exception {
      String badFundingTypeCode = "-99";
      protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
      protocolFundingSourceService.setParameterService(getParameterService());

      
      assertFalse(protocolFundingSourceService.isLookupable(FundingSourceType.OTHER));
      assertFalse(protocolFundingSourceService.isLookupable(badFundingTypeCode));

      assertTrue(protocolFundingSourceService.isLookupable(FundingSourceType.INSTITUTIONAL_PROPOSAL));
      assertTrue(protocolFundingSourceService.isLookupable(FundingSourceType.UNIT));
      assertTrue(protocolFundingSourceService.isLookupable(FundingSourceType.SPONSOR));
      assertTrue(protocolFundingSourceService.isLookupable(FundingSourceType.AWARD));
      assertTrue(protocolFundingSourceService.isLookupable(FundingSourceType.PROPOSAL_DEVELOPMENT));
  }
  
  @Test
  public void testUpdateSourceNameEditable() throws Exception {
      protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
      protocolFundingSourceService.setParameterService(getParameterService());
      
      assertFalse(protocolFundingSourceService.isEditable(FundingSourceType.INSTITUTIONAL_PROPOSAL));
      assertTrue(protocolFundingSourceService.isEditable(FundingSourceType.OTHER));
      
      assertFalse(protocolFundingSourceService.isEditable(FundingSourceType.AWARD));
      assertFalse(protocolFundingSourceService.isEditable(FundingSourceType.PROPOSAL_DEVELOPMENT));
      assertFalse(protocolFundingSourceService.isEditable(FundingSourceType.SPONSOR));
      assertFalse(protocolFundingSourceService.isEditable(FundingSourceType.UNIT));
      
  }

    protected SponsorService getSponsorService() {
        final SponsorService sponsorService = context.mock(SponsorService.class);
        context.checking(new Expectations() {{
            allowing(sponsorService).getSponsorName(sponsorNumberAirForce); 
            will(returnValue(sponsorNameAirForce));
            allowing(sponsorService).getSponsorName(sponsorNumberBad); 
            will(returnValue(null));
        }});
        return sponsorService;
    }
    
    protected UnitService getUnitService() {
        final UnitService unitService = context.mock(UnitService.class);
        context.checking(new Expectations() {{
            allowing(unitService).getUnitName(unitNumberGood); 
            will(returnValue(unitNameGood));
            allowing(unitService).getUnitName(unitNumberBad); 
            will(returnValue(null));
        }});
        return unitService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            allowing(businessObjectService).findBySinglePrimaryKey(DevelopmentProposal.class, devProposalNumberGood); 
            will(returnValue(devProposalGood));
            allowing(businessObjectService).findBySinglePrimaryKey(DevelopmentProposal.class, devProposalNumberBad); 
            will(returnValue(null));
        }});
        return businessObjectService;
    }
    
    protected InstitutionalProposalService getInstProposalService() {
        final InstitutionalProposalService institutionalProposalService = context.mock(InstitutionalProposalService.class);
        context.checking(new Expectations() {{
            allowing(institutionalProposalService).getActiveInstitutionalProposalVersion(instProposalNumberGood); 
            will(returnValue(instProposalGood));
            allowing(institutionalProposalService).getActiveInstitutionalProposalVersion(instProposalNumberBad); 
            will(returnValue(null));
            allowing(institutionalProposalService).getPendingInstitutionalProposalVersion(instProposalNumberGood); 
            will(returnValue(instProposalGood));
            allowing(institutionalProposalService).getPendingInstitutionalProposalVersion(instProposalNumberBad); 
            will(returnValue(null));
        }});
        return institutionalProposalService;
    }
    
    protected AwardService getAwardService() {
        final AwardService awardService = context.mock(AwardService.class);
        context.checking(new Expectations() {{
            allowing(awardService).findAwardsForAwardNumber(awardNumberGood); 
            will(returnValue(Collections.singletonList(awardGood)));
            allowing(awardService).findAwardsForAwardNumber(awardNumberBad); 
            will(returnValue(Collections.emptyList()));
        }});
        return awardService;
    }

    @SuppressWarnings("unchecked")
    protected ParameterService getParameterService() {
        final ParameterService parameterService = context.mock(ParameterService.class);
        context.checking(new Expectations() {{
            allowing(parameterService).parameterExists(with(any(Class.class)), with(any(String.class))); 
            will(returnValue(true));
            allowing(parameterService).getParameterValueAsBoolean( ProtocolDocument.class, Constants.ENABLE_PROTOCOL_TO_AWARD_LINK ); 
            will(returnValue(true));
            allowing(parameterService).getParameterValueAsBoolean(ProtocolDocument.class, Constants.ENABLE_PROTOCOL_TO_DEV_PROPOSAL_LINK ); 
            will(returnValue(true));
            allowing(parameterService).getParameterValueAsBoolean(ProtocolDocument.class, Constants.ENABLE_PROTOCOL_TO_PROPOSAL_LINK ); 
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