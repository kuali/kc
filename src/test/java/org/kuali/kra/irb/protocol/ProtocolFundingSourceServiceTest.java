/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.protocol;

import java.util.HashMap;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.service.AwardService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.irb.protocol.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.ProtocolFundingSourceServiceImpl;
import org.kuali.kra.irb.protocol.ProtocolFundingSourceServiceImpl.FundingSourceLookup;
import org.kuali.kra.proposaldevelopment.bo.LookupableDevelopmentProposal;
import org.kuali.kra.proposaldevelopment.service.LookupableDevelopmentProposalService;
import org.kuali.kra.service.FundingSourceTypeService;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;

/**
* The JUnit test class for <code>{@link ProtocolFundingSourceServiceImpl}</code>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolFundingSourceServiceTest extends TestCase {


    private Mockery context = new JUnit4Mockery();

    private ProtocolFundingSourceServiceImpl protocolFundingSourceService;

    private final String emptyId = "";
    private final String sponsorIdAirForce = "000100";
    private final String sponsorNameAirForce = "Air Force";
    private final String sponsorIdBad = "-1";
    
    private final String unitIdGood = "000001";
    private final String unitNameGood = "University";
    private final String unitIdBad = "zzzzz";
    
    private final String awardIdGood = "1";
    private final String awardTitleGood = "AwardTitle";
    private final String awardIdBad = "zzzzz";
    private Award awardGood;

    private final String devProposalIdGood = "1";
    private final String devProposalTitleGood = "DevPropTitle";
    private final String devProposalIdBad = "zzzzz";
    private LookupableDevelopmentProposal  devProposalGood;
    
    private final String sponsorSourceTypeId = "1";
    private final String unitSourceTypeId = "2";
    private final String otherSourceTypeId = "3";
    private final String developmentPropSourceTypeId = "4";
    private final String institutePropSourceTypeId = "5";
    private final String awardSourceTypeId = "6";
    
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
        super.setUp();
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
        fundingInstProposalSourceType.setDescription("Institute Proposal");
        fundingAwardSourceType= new FundingSourceType();
        fundingAwardSourceType.setFundingSourceTypeCode(6);
        fundingAwardSourceType.setFundingSourceTypeFlag(true);
        fundingAwardSourceType.setDescription("Award");
        
        Sponsor goodSponsor = new Sponsor();
        goodSponsor.setSponsorName(sponsorNameAirForce);
        goodSponsor.setSponsorCode(sponsorIdAirForce);

        awardGood = new Award();
        awardGood.setTitle(awardTitleGood);
        awardGood.setSponsor(goodSponsor); 
        
        devProposalGood = new LookupableDevelopmentProposal();
        devProposalGood.setTitle(devProposalTitleGood);
        devProposalGood.setSponsor(goodSponsor);   
    }    
    
    @Test
    public void testCalculateSponsorFundingSource() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setSponsorService(getSponsorService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(sponsorSourceTypeId, sponsorIdAirForce, null);
        assertNotNull(fundingSource);
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
    }
    
    @Test
    public void testCalculateSponsorFundingSourceNegative() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setSponsorService(getSponsorService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(sponsorSourceTypeId, sponsorIdBad, null);
        assertNotNull(fundingSource);
        assertNull(fundingSource.getFundingSourceName());
    }
    
    @Test
    public void testCalculateUnitFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        final UnitService unitService = context.mock(UnitService.class);
        context.checking(new Expectations() {{
            one(unitService).getUnitName(unitIdGood); 
            will(returnValue(unitNameGood));
        }});
        protocolFundingSourceService.setUnitService(unitService);
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(unitSourceTypeId, unitIdGood, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(unitNameGood));
    }   
    
    @Test
    public void testCalculateUnitFundingSourceNegative() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        final UnitService unitService = context.mock(UnitService.class);
        context.checking(new Expectations() {{
            one(unitService).getUnitName(unitIdBad); 
            will(returnValue(null));
        }});
        protocolFundingSourceService.setUnitService(unitService);
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(unitSourceTypeId, unitIdBad, null);
        assertNotNull(fundingSource);
        assertNull(fundingSource.getFundingSourceName());
    }    

    @Test
    public void testCalculateUnitFundingSourceNegative2() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        final UnitService unitService = context.mock(UnitService.class);

        protocolFundingSourceService.setUnitService(unitService);
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(unitSourceTypeId, emptyId, null);
        assertNull(fundingSource);
    }    
    
    @Test
    public void testCalculateAwardFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();

        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(awardSourceTypeId, awardIdGood, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(awardTitleGood));
    } 

    @Test
    public void testCalculateAwardFundingNegative() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(awardSourceTypeId, awardIdBad, null);
        assertNotNull(fundingSource);
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceName()));
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceTitle()));
    } 
    
    @Test
    public void testCalculateAwardFundingNegative2() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(awardSourceTypeId, emptyId, null);
        assertNull(fundingSource);
    } 
    
    @Test
    public void testCalculateDevProposalFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();

        protocolFundingSourceService.setLookupableDevelopmentProposalService(getDevProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(developmentPropSourceTypeId, devProposalIdGood, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(devProposalTitleGood));
    } 
    
    @Test
    public void testCalculateDevProposalFundingNegative() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();

        protocolFundingSourceService.setLookupableDevelopmentProposalService(getDevProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(developmentPropSourceTypeId, devProposalIdBad, null);
        assertNotNull(fundingSource);
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceName()));
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceTitle()));
    } 
    
    @Test
    public void testCalculateDevProposalFundingNegative2() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();

        protocolFundingSourceService.setLookupableDevelopmentProposalService(getDevProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(developmentPropSourceTypeId, emptyId, null);
        assertNull(fundingSource);

    } 
    
    @Test
    public void testCalculateOtherFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(otherSourceTypeId, "otherId", "otherName");
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase("otherName"));
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceTitle()));
    } 
    
    @Test
    public void testCalculateOtherFundingNegative() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource  = protocolFundingSourceService.updateProtocolFundingSource(otherSourceTypeId, "", "");
        assertNull(fundingSource);
    } 

    @Test
    public void testIsValidFundingSourceLookup() throws Exception {
        ErrorMessage msg = null;
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        GlobalVariables.clear();
//        assertFalse(protocolFundingSourceService.isValidLookup(FundingSourceLookup.INSTITUTE_PROPOSAL.getLookupName()));
//        msg = (ErrorMessage)(GlobalVariables.getErrorMap().getMessages(Constants.PROTO_FUNDING_SRC_TYPE_CODE_FIELD)).get(0);
//        assertTrue(
//                msg.getErrorKey().equalsIgnoreCase(KeyConstants.ERROR_FUNDING_LOOKUPTEMP_UNAVAIL)
//                );
//        GlobalVariables.clear();
//        assertFalse(protocolFundingSourceService.isValidLookup(FundingSourceLookup.OTHER.getLookupName()));
//        msg = (ErrorMessage)(GlobalVariables.getErrorMap().getMessages(Constants.PROTO_FUNDING_SRC_TYPE_CODE_FIELD)).get(0);
//        assertTrue(
//                msg.getErrorKey().equalsIgnoreCase(KeyConstants.ERROR_FUNDING_LOOKUP_UNAVAIL)
//                );
//        GlobalVariables.clear();
//        assertFalse(protocolFundingSourceService.isValidLookup(emptyId));
//        msg = (ErrorMessage)(GlobalVariables.getErrorMap().getMessages(Constants.PROTO_FUNDING_SRC_TYPE_CODE_FIELD)).get(0);
//        assertTrue(
//                msg.getErrorKey().equalsIgnoreCase(KeyConstants.ERROR_FUNDING_LOOKUP_NOT_FOUND)
//                );
//        GlobalVariables.clear();
//        assertFalse(protocolFundingSourceService.isValidLookup(null));
//        msg = (ErrorMessage)(GlobalVariables.getErrorMap().getMessages(Constants.PROTO_FUNDING_SRC_TYPE_CODE_FIELD)).get(0);
//        assertTrue(
//                msg.getErrorKey().equalsIgnoreCase(KeyConstants.ERROR_FUNDING_LOOKUP_NOT_FOUND)
//                );
//        GlobalVariables.clear();
//        assertFalse(protocolFundingSourceService.isValidLookup("BOGUS"));
//        msg = (ErrorMessage)(GlobalVariables.getErrorMap().getMessages(Constants.PROTO_FUNDING_SRC_TYPE_CODE_FIELD)).get(0);
//        assertTrue(
//                msg.getErrorKey().equalsIgnoreCase(KeyConstants.ERROR_FUNDING_LOOKUP_NOT_FOUND)
//                );        
//        assertTrue(protocolFundingSourceService.isValidLookup(FundingSourceLookup.UNIT.getLookupName()));
//        assertTrue(protocolFundingSourceService.isValidLookup(FundingSourceLookup.SPONSOR.getLookupName()));
//        assertTrue(protocolFundingSourceService.isValidLookup(FundingSourceLookup.AWARD.getLookupName()));
//        assertTrue(protocolFundingSourceService.isValidLookup(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getLookupName()));
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
    public void testIsValidIdForTypeAward() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());    
        protocolFundingSourceService.setAwardService(getAwardService());

        ProtocolFundingSource fundingSource = new ProtocolFundingSource(awardIdGood, fundingAwardSourceType, null, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(awardIdBad, fundingAwardSourceType, null, null);
        assertFalse(protocolFundingSourceService.isValidIdForType(fundingSource));
    } 

    
    @Test
    public void testGetLookupParameters() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        String fieldConversions;
        
        HashMap<String, String> map = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.SPONSOR.getLookupName());
        Assert.assertNotNull(map);
        fieldConversions = 
            (String) map.get(FundingSourceLookup.SPONSOR.getBOClass().getName());
        Assert.assertThat(fieldConversions, 
                JUnitMatchers.containsString("sponsorCode:protocolHelper.newFundingSource.fundingSource,sponsorName:"));

        map = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.UNIT.getLookupName());
        Assert.assertNotNull(map);
        fieldConversions = 
            (String) map.get(FundingSourceLookup.UNIT.getBOClass().getName());
        Assert.assertThat(fieldConversions, 
                JUnitMatchers.containsString("unitNumber:protocolHelper.newFundingSource.fundingSource,unitName:"));
        
        map = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getLookupName());
        Assert.assertNotNull(map);
        fieldConversions = 
            (String) map.get(FundingSourceLookup.PROPOSAL_DEVELOPMENT.getBOClass().getName());
        Assert.assertThat(fieldConversions, 
                JUnitMatchers.containsString("proposalNumber:protocolHelper.newFundingSource.fundingSource,sponsor.sponsorName:protocolHelper"));

        map = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.AWARD.getLookupName());
        Assert.assertNotNull(map);
        fieldConversions = 
            (String) map.get(FundingSourceLookup.AWARD.getBOClass().getName());
        Assert.assertThat(fieldConversions, 
                JUnitMatchers.containsString("awardId:protocolHelper.newFundingSource.fundingSource,sponsor.sponsorName:protocolHelper"));

        
        try {
            map = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.INSTITUTE_PROPOSAL.getLookupName());
            fail("IllegalArgumentException was not thrown for invalid test case using INSTITUTE_PROPOSAL");
        } catch (IllegalArgumentException e) {
            //yup
        }
        
        try {
            map = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.OTHER.getLookupName());
            fail("IllegalArgumentException was not thrown for invalid test case using OTHER");
        } catch (IllegalArgumentException e) {
            //yup
        }

    }
    
    @Test
    public void testUpdateLookupParameter() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        String fieldConversions;
        HashMap<String, String> map = protocolFundingSourceService.getLookupParameters(FundingSourceLookup.SPONSOR.getLookupName());
        Assert.assertNotNull(map);
        fieldConversions = 
            (String) map.get(FundingSourceLookup.SPONSOR.getBOClass().getName());
        Assert.assertThat(fieldConversions, 
                JUnitMatchers.containsString("sponsorCode:protocolHelper.newFundingSource.fundingSource,sponsorName:"));
        String parameter = KNSConstants.METHOD_TO_CALL_BOPARM_LEFT_DEL+
                            KNSConstants.METHOD_TO_CALL_BOPARM_RIGHT_DEL+
                            KNSConstants.METHOD_TO_CALL_PARM1_LEFT_DEL+
                            KNSConstants.METHOD_TO_CALL_PARM1_RIGHT_DEL;
        
        String updatedParam = 
            protocolFundingSourceService.updateLookupParameter(parameter, 
                    FundingSourceLookup.SPONSOR.getBOClass().getName(), 
                    fieldConversions);
        Assert.assertThat(updatedParam, 
                JUnitMatchers.containsString("(!!org.kuali.kra.bo.Sponsor!!)(((sponsorCode:protocolHelper.newFundingSource.fundingSource,sponsorName:protocolHelper.newFundingSource.fundingSourceName,)))"));

    }
    
    
    protected BusinessObjectService buildBusinessObjectService() {
        BusinessObjectService service = null;
        return service;        
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

    protected AwardService getAwardService() {
        final AwardService awardService = context.mock(AwardService.class);
        context.checking(new Expectations() {{
            allowing(awardService).getAward(awardIdBad); 
            will(returnValue(null));
            allowing(awardService).getAward(awardIdGood); 
            will(returnValue(awardGood));
        }});
        return awardService;
    }

    protected LookupableDevelopmentProposalService getDevProposalService() {
        final LookupableDevelopmentProposalService lookupableDevelopmentProposalService = context.mock(LookupableDevelopmentProposalService.class);
        context.checking(new Expectations() {{
            allowing(lookupableDevelopmentProposalService).getLookupableDevelopmentProposal(devProposalIdBad); 
            will(returnValue(null));
            allowing(lookupableDevelopmentProposalService).getLookupableDevelopmentProposal(devProposalIdGood); 
            will(returnValue(devProposalGood));
        }});
        return lookupableDevelopmentProposalService;
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
