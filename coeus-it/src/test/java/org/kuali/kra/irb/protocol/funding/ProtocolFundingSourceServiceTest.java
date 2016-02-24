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
package org.kuali.kra.irb.protocol.funding;

import org.apache.commons.lang3.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.kuali.coeus.common.api.sponsor.SponsorContract;
import org.kuali.coeus.common.api.sponsor.SponsorService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.service.FundingSourceTypeService;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.Collections;
import java.util.Map.Entry;

import static org.junit.Assert.*;
/**
* The JUnit test class for <code>{@link ProtocolFundingSourceServiceImpl}</code>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolFundingSourceServiceTest extends KcIntegrationTestBase {

    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};

    private ProtocolFundingSourceServiceImpl protocolFundingSourceService;

    private static final String EMPTY_NUMBER = "";

    private static final String SPONSOR_NUMBER_BAD = "-1";
    private SponsorContract sponsorGood;
    private static final String SPONSOR_NUMBER_AIR_FORCE = "000108";
    private String sponsorNameAirForce;

    private static final String UNIT_NUMBER_GOOD = "000001";
    private static final String UNIT_NAME_GOOD = "University";
    private static final String UNIT_NUMBER_BAD = "zzzzz";
    
    private static final String OTHER_NUMBER_GOOD = "otherId";
    private static final String OTHER_NAME_GOOD = "otherName";

    private static final String DEV_PROPOSAL_NUMBER_GOOD = "1";
    private static final String DEV_PROPOSAL_TITLE_GOOD = "DevPropTitle";
    private static final String DEV_PROPOSAL_NUMBER_BAD = "zzzzz";
    private DevelopmentProposal  devProposalGood;
    
    private static final String INST_PROPOSAL_NUMBER_GOOD = "00000001";
    private static final String INST_PROPOSAL_TITLE_GOOD = "Institutional Proposal Title";
    private static final String INST_PROPOSAL_NUMBER_BAD = "zzzzzzzz";
    private InstitutionalProposal instProposalGood;
    
    private static final String AWARD_NUMBER_GOOD = "000001-00001";
    private static final String AWARD_TITLE_GOOD = "AwardTitle";
    private static final String AWARD_NUMBER_BAD = "zzzzzz-zzzzz";
    private Award awardGood;
    
    private static final String SPONSOR_SOURCE_TYPE_ID = "1";
    private static final String UNIT_SOURCE_TYPE_ID = "2";
    private static final String OTHER_SOURCE_TYPE_ID = "3";
    private static final String DEVELOPMENT_PROP_SOURCE_TYPE_ID = "4";
    private static final String INSTITUTE_PROP_SOURCE_TYPE_ID = "5";
    private static final String AWARD_SOURCE_TYPE_ID = "6";

    private static final String PROTOCOL_FUNDING_SOURCE = "protocolHelper.newFundingSource.fundingSource";
    private static final String PROTOCOL_FUNDING_SOURCE_NUMBER = "protocolHelper.newFundingSource.fundingSourceNumber";
    private static final String PROTOCOL_FUNDING_SOURCE_NAME = "protocolHelper.newFundingSource.fundingSourceName";
    private static final String PROTOCOL_FUNDING_SOURCE_TITLE = "protocolHelper.newFundingSource.fundingSourceTitle";
    
    private FundingSourceType fundingSponsorSourceType;
    private FundingSourceType fundingUnitSourceType;
    private FundingSourceType fundingOtherSourceType;
    private FundingSourceType fundingDevProposalSourceType;
    private FundingSourceType fundingInstProposalSourceType;
    private FundingSourceType fundingAwardSourceType;

    /**
     * Create the mock services and insert them into the protocol auth service.
     *
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
        
        //sponsorGood = new Sponsor();
        //sponsorGood.setSponsorName(sponsorNameAirForce);
        //sponsorGood.setSponsorCode(SPONSOR_NUMBER_AIR_FORCE);
        sponsorGood = KcServiceLocator.getService(SponsorService.class).getSponsor(SPONSOR_NUMBER_AIR_FORCE);
        sponsorNameAirForce = sponsorGood.getSponsorName();
        
        devProposalGood = new DevelopmentProposal();
        devProposalGood.setTitle(DEV_PROPOSAL_TITLE_GOOD);
        devProposalGood.setSponsorCode(sponsorGood.getSponsorCode());

        instProposalGood = new InstitutionalProposal();
        instProposalGood.setTitle(INST_PROPOSAL_TITLE_GOOD);
        instProposalGood.setSponsorCode(sponsorGood.getSponsorCode());

        awardGood = new Award();
        awardGood.setTitle(AWARD_TITLE_GOOD);
        awardGood.setSponsorCode(sponsorGood.getSponsorCode());
    }    

    @Test
    public void testCalculateSponsorFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setSponsorService(getSponsorService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource 
            = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(SPONSOR_SOURCE_TYPE_ID, SPONSOR_NUMBER_AIR_FORCE, null);
        assertNotNull(fundingSource);
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
    }
    
    @Test
    public void testCalculateSponsorFundingSourceBadId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setSponsorService(getSponsorService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(SPONSOR_SOURCE_TYPE_ID, SPONSOR_NUMBER_BAD, null);
        assertNotNull(fundingSource);
        assertNull(fundingSource.getFundingSourceName());
    }
    
    @Test
    public void testCalculateSponsorFundingSourceEmptyId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setSponsorService(getSponsorService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(SPONSOR_SOURCE_TYPE_ID, EMPTY_NUMBER, null);
        assertNull(fundingSource);
    }   
    
    @Test
    public void testCalculateUnitFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setUnitService(getUnitService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(UNIT_SOURCE_TYPE_ID, UNIT_NUMBER_GOOD, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(UNIT_NAME_GOOD));
    }   
    
    @Test
    public void testCalculateUnitFundingSourceBadId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setUnitService(getUnitService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(UNIT_SOURCE_TYPE_ID, UNIT_NUMBER_BAD, null);
        assertNotNull(fundingSource);
        assertNull(fundingSource.getFundingSourceName());
    }    

    @Test
    public void testCalculateUnitFundingSourceEmptyId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        final UnitService unitService = context.mock(UnitService.class);

        protocolFundingSourceService.setUnitService(unitService);
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        ProtocolFundingSource fundingSource = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(UNIT_SOURCE_TYPE_ID, EMPTY_NUMBER, null);
        assertNull(fundingSource);
    }
    
    @Test
    public void testCalculateOtherFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource 
            = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(OTHER_SOURCE_TYPE_ID, OTHER_NUMBER_GOOD, OTHER_NAME_GOOD);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(OTHER_NAME_GOOD));
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceTitle()));
    } 
    
    @Test
    public void testCalculateOtherFundingEmptyId() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(OTHER_SOURCE_TYPE_ID, EMPTY_NUMBER, null);
        assertNull(fundingSource);
    }
    
    @Test
    public void testCalculateDevProposalFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setDataObjectService(getDataObjectService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource 
            = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(DEVELOPMENT_PROP_SOURCE_TYPE_ID, DEV_PROPOSAL_NUMBER_GOOD, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(DEV_PROPOSAL_TITLE_GOOD));
    } 
    
    @Test
    public void testCalculateDevProposalFundingBadID() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setDataObjectService(getDataObjectService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource 
            = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(DEVELOPMENT_PROP_SOURCE_TYPE_ID, DEV_PROPOSAL_NUMBER_BAD, null);
        assertNotNull(fundingSource);
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceName()));
        assertTrue(StringUtils.isEmpty(fundingSource.getFundingSourceTitle()));
    } 
    
    @Test
    public void testCalculateDevProposalFundingNegativeEmptyID() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setDataObjectService(getDataObjectService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        
        ProtocolFundingSource fundingSource = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(DEVELOPMENT_PROP_SOURCE_TYPE_ID, EMPTY_NUMBER, null);
        assertNull(fundingSource);
    }

    @Test
    public void testCalculateInstProposalFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setInstitutionalProposalService(getInstProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setDataObjectService(getDataObjectService());
        
        ProtocolFundingSource fundingSource  = 
            (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(INSTITUTE_PROP_SOURCE_TYPE_ID, INST_PROPOSAL_NUMBER_GOOD, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(INST_PROPOSAL_TITLE_GOOD));
    }     
    
    @Test
    public void testCalculateInstProposalFundingBadIdGoodNumber() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setInstitutionalProposalService(getInstProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setDataObjectService(getDataObjectService());
        
        ProtocolFundingSource fundingSource 
            = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(INSTITUTE_PROP_SOURCE_TYPE_ID, INST_PROPOSAL_NUMBER_GOOD, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(INST_PROPOSAL_TITLE_GOOD));
    }
    
    @Test
    public void testCalculateInstProposalFundingBadIdBadNumber() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setParameterService(getParameterService());
        protocolFundingSourceService.setInstitutionalProposalService(getInstProposalService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setDataObjectService(getDataObjectService());
        
        ProtocolFundingSource fundingSource 
            = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(INSTITUTE_PROP_SOURCE_TYPE_ID, INST_PROPOSAL_NUMBER_BAD, null);
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
        protocolFundingSourceService.setDataObjectService(getDataObjectService());
        
        ProtocolFundingSource fundingSource  
            = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(INSTITUTE_PROP_SOURCE_TYPE_ID, EMPTY_NUMBER, null);
        assertNull(fundingSource);
    }
    
    @Test
    public void testCalculateAwardFunding() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();

        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setParameterService(getParameterService());
        
        ProtocolFundingSource fundingSource = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(AWARD_SOURCE_TYPE_ID, AWARD_NUMBER_GOOD, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(AWARD_TITLE_GOOD));
    }
    
    @Test
    public void testCalculateAwardFundingBadIdGoodNumber() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setParameterService(getParameterService());

        ProtocolFundingSource fundingSource  = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(AWARD_SOURCE_TYPE_ID, AWARD_NUMBER_GOOD, null);
        assertNotNull(fundingSource);
        assertNotNull(fundingSource.getFundingSourceName());
        assertTrue(fundingSource.getFundingSourceName().equalsIgnoreCase(sponsorNameAirForce));
        assertTrue(fundingSource.getFundingSourceTitle().equalsIgnoreCase(AWARD_TITLE_GOOD));
    } 

    @Test
    public void testCalculateAwardFundingBadIdBadNumber() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());
        protocolFundingSourceService.setParameterService(getParameterService());

        ProtocolFundingSource fundingSource  = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(AWARD_SOURCE_TYPE_ID, AWARD_NUMBER_BAD, null);
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

        ProtocolFundingSource fundingSource  = (ProtocolFundingSource) protocolFundingSourceService.updateProtocolFundingSource(AWARD_SOURCE_TYPE_ID, EMPTY_NUMBER, null);
        assertNull(fundingSource);
    } 
    
    @Test
    public void testIsValidIdForTypeSponsor() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());    
        protocolFundingSourceService.setSponsorService(getSponsorService());

        ProtocolFundingSource fundingSource = new ProtocolFundingSource(SPONSOR_NUMBER_AIR_FORCE, FundingSourceType.SPONSOR, null, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(SPONSOR_NUMBER_BAD, FundingSourceType.SPONSOR, null, null);
        assertFalse(protocolFundingSourceService.isValidIdForType(fundingSource));
    } 
    
    @Test
    public void testIsValidIdForTypeUnit() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());    
        protocolFundingSourceService.setUnitService(getUnitService());       

        ProtocolFundingSource fundingSource = new ProtocolFundingSource(UNIT_NUMBER_GOOD, FundingSourceType.UNIT, null, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(UNIT_NUMBER_BAD, FundingSourceType.UNIT, null, null);
        assertFalse(protocolFundingSourceService.isValidIdForType(fundingSource));
    }
    
    @Test
    public void testIsValidIdForTypeOther() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());       
        ProtocolFundingSource fundingSource = new ProtocolFundingSource(OTHER_SOURCE_TYPE_ID, FundingSourceType.OTHER, "otherName", null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(OTHER_SOURCE_TYPE_ID, FundingSourceType.OTHER, EMPTY_NUMBER, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
    } 
    
    @Test
    public void testIsValidIdForTypeAward() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        protocolFundingSourceService.setFundingSourceTypeService(getFundingSourceTypeService());    
        protocolFundingSourceService.setAwardService(getAwardService());
        protocolFundingSourceService.setParameterService(getParameterService());
        ProtocolFundingSource fundingSource = new ProtocolFundingSource(AWARD_NUMBER_GOOD, FundingSourceType.AWARD, null, null);
        assertTrue(protocolFundingSourceService.isValidIdForType(fundingSource));
        
        fundingSource = new ProtocolFundingSource(AWARD_NUMBER_BAD, FundingSourceType.AWARD, null, null);
        assertFalse(protocolFundingSourceService.isValidIdForType(fundingSource));
    } 

    @Test
    public void testGetLookupParameters() throws Exception {
        protocolFundingSourceService = new ProtocolFundingSourceServiceImpl();
        StringBuilder builder = new StringBuilder();
        
        Entry<String, String> entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.SPONSOR);
        Assert.assertNotNull(entry);
        builder.append("sponsorCode:" + PROTOCOL_FUNDING_SOURCE_NUMBER + Constants.COMMA);
        builder.append("sponsorName:" + PROTOCOL_FUNDING_SOURCE_NAME);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());

        entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.UNIT);
        Assert.assertNotNull(entry);
        builder.append("unitNumber:" + PROTOCOL_FUNDING_SOURCE_NUMBER + Constants.COMMA);
        builder.append("unitName:" + PROTOCOL_FUNDING_SOURCE_NAME);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());
        
        entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.PROPOSAL_DEVELOPMENT);
        Assert.assertNotNull(entry);
        builder.append("proposalNumber:" + PROTOCOL_FUNDING_SOURCE_NUMBER + Constants.COMMA);
        builder.append("sponsor.sponsorName:" + PROTOCOL_FUNDING_SOURCE_NAME + Constants.COMMA);
        builder.append("title:" + PROTOCOL_FUNDING_SOURCE_TITLE);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());

        entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.INSTITUTIONAL_PROPOSAL);
        Assert.assertNotNull(entry);
        builder.append("proposalId:" + PROTOCOL_FUNDING_SOURCE + Constants.COMMA);
        builder.append("proposalNumber:" + PROTOCOL_FUNDING_SOURCE_NUMBER + Constants.COMMA);
        builder.append("sponsor.sponsorName:" + PROTOCOL_FUNDING_SOURCE_NAME + Constants.COMMA);
        builder.append("title:" + PROTOCOL_FUNDING_SOURCE_TITLE);
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        builder.delete(0, builder.length());
        
        entry = protocolFundingSourceService.getLookupParameters(FundingSourceType.AWARD);
        Assert.assertNotNull(entry);
        builder.append("awardId:" + PROTOCOL_FUNDING_SOURCE + Constants.COMMA);
        builder.append("awardNumber:" + PROTOCOL_FUNDING_SOURCE_NUMBER + Constants.COMMA);
        builder.append("sponsor.sponsorName:" + PROTOCOL_FUNDING_SOURCE_NAME + Constants.COMMA);
        builder.append("title:" + PROTOCOL_FUNDING_SOURCE_TITLE);
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
        builder.append("sponsorCode:" + PROTOCOL_FUNDING_SOURCE_NUMBER + Constants.COMMA);
        builder.append("sponsorName:" + PROTOCOL_FUNDING_SOURCE_NAME);
        
        Assert.assertThat(entry.getValue(), JUnitMatchers.containsString(builder.toString()));
        String parameter = KRADConstants.METHOD_TO_CALL_BOPARM_LEFT_DEL 
                            + KRADConstants.METHOD_TO_CALL_BOPARM_RIGHT_DEL
                            + KRADConstants.METHOD_TO_CALL_PARM1_LEFT_DEL
                            + KRADConstants.METHOD_TO_CALL_PARM1_RIGHT_DEL;
        String updatedParam 
            = protocolFundingSourceService.updateLookupParameter(parameter, Sponsor.class.getName(), fieldConversions);
        Assert.assertThat(updatedParam, JUnitMatchers.containsString("(!!" + Sponsor.class.getName() + "!!)(((" + builder.toString() + ")))"));

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
            allowing(sponsorService).getSponsorName(SPONSOR_NUMBER_AIR_FORCE);
            will(returnValue(sponsorNameAirForce));
            allowing(sponsorService).getSponsorName(SPONSOR_NUMBER_BAD);
            will(returnValue(null));
        }});
        return sponsorService;
    }
    
    protected UnitService getUnitService() {
        final UnitService unitService = context.mock(UnitService.class);
        context.checking(new Expectations() {{
            allowing(unitService).getUnitName(UNIT_NUMBER_GOOD);
            will(returnValue(UNIT_NAME_GOOD));
            allowing(unitService).getUnitName(UNIT_NUMBER_BAD);
            will(returnValue(null));
        }});
        return unitService;
    }

    protected DataObjectService getDataObjectService() {
        final DataObjectService dataObjectService = context.mock(DataObjectService.class);
        context.checking(new Expectations() {{
            allowing(dataObjectService).find(DevelopmentProposal.class, DEV_PROPOSAL_NUMBER_GOOD);
            will(returnValue(devProposalGood));
            allowing(dataObjectService).find(DevelopmentProposal.class, DEV_PROPOSAL_NUMBER_BAD);
            will(returnValue(null));
        }});
        return dataObjectService;
    }
    
    protected InstitutionalProposalService getInstProposalService() {
        final InstitutionalProposalService institutionalProposalService = context.mock(InstitutionalProposalService.class);
        context.checking(new Expectations() {{
            allowing(institutionalProposalService).getActiveInstitutionalProposalVersion(INST_PROPOSAL_NUMBER_GOOD);
            will(returnValue(instProposalGood));
            allowing(institutionalProposalService).getActiveInstitutionalProposalVersion(INST_PROPOSAL_NUMBER_BAD);
            will(returnValue(null));
            allowing(institutionalProposalService).getPendingInstitutionalProposalVersion(INST_PROPOSAL_NUMBER_GOOD);
            will(returnValue(instProposalGood));
            allowing(institutionalProposalService).getPendingInstitutionalProposalVersion(INST_PROPOSAL_NUMBER_BAD);
            will(returnValue(null));
        }});
        return institutionalProposalService;
    }
    
    protected AwardService getAwardService() {
        final AwardService awardService = context.mock(AwardService.class);
        context.checking(new Expectations() {{
            allowing(awardService).findAwardsForAwardNumber(AWARD_NUMBER_GOOD);
            will(returnValue(Collections.singletonList(awardGood)));
            allowing(awardService).findAwardsForAwardNumber(AWARD_NUMBER_BAD);
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
            allowing(service).getFundingSourceType(SPONSOR_SOURCE_TYPE_ID);
            will(returnValue(fundingSponsorSourceType));
            
            allowing(service).getFundingSourceType(UNIT_SOURCE_TYPE_ID);
            will(returnValue(fundingUnitSourceType));
            
            allowing(service).getFundingSourceType(OTHER_SOURCE_TYPE_ID);
            will(returnValue(fundingOtherSourceType));
            
            allowing(service).getFundingSourceType(DEVELOPMENT_PROP_SOURCE_TYPE_ID);
            will(returnValue(fundingDevProposalSourceType));
            
            allowing(service).getFundingSourceType(INSTITUTE_PROP_SOURCE_TYPE_ID);
            will(returnValue(fundingInstProposalSourceType));
            
            allowing(service).getFundingSourceType(AWARD_SOURCE_TYPE_ID);
            will(returnValue(fundingAwardSourceType));
        }});
        return service;
    }

}
