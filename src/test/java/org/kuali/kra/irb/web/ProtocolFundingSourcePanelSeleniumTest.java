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
package org.kuali.kra.irb.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Funding Source tab in the Protocol page of a Protocol.
 */
public class ProtocolFundingSourcePanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TAB_ID = "Funding Sources";
    private static final String TABLE_ID = "funding-source-table";
    
    private static final String HELPER_PREFIX = "protocolHelper.newFundingSource.";
    
    private static final String FUNDING_SOURCE_TYPE_CODE_ID = "fundingSourceTypeCode";
    private static final String FUNDING_SOURCE_NUMBER_ID = "fundingSourceNumber";
    private static final String FUNDING_SOURCE_NAME_ID = "fundingSourceName";
    private static final String HELPER_FUNDING_SOURCE_TYPE_CODE_ID = HELPER_PREFIX + FUNDING_SOURCE_TYPE_CODE_ID;
    private static final String HELPER_FUNDING_SOURCE_NUMBER_ID = HELPER_PREFIX + FUNDING_SOURCE_NUMBER_ID;
    private static final String HELPER_FUNDING_SOURCE_NAME_ID = HELPER_PREFIX + FUNDING_SOURCE_NAME_ID;
    
    private static final String FUNDING_SOURCE_TYPE_CODE_SPONSOR = "Sponsor";
    private static final String FUNDING_SOURCE_NUMBER_SPONSOR = "005174";
    private static final String FUNDING_SOURCE_NAME_SPONSOR = "Arkansas Enterprises for the Blind"; 
    private static final String FUNDING_SOURCE_TYPE_CODE_UNIT = "Unit";
    private static final String FUNDING_SOURCE_NUMBER_UNIT = "IU-UNIV";
    private static final String FUNDING_SOURCE_NAME_UNIT = "UNIVERSITY LEVEL";
    private static final String FUNDING_SOURCE_TYPE_CODE_OTHER = "Other";
    private static final String FUNDING_SOURCE_NUMBER_OTHER = "123";
    private static final String FUNDING_SOURCE_NAME_OTHER = "Other Name";
    private static final String FUNDING_SOURCE_TYPE_CODE_DEVELOPMENT_PROPOSAL = "Development Proposal";
    private static final String FUNDING_SOURCE_NUMBER_DEVELOPMENT_PROPOSAL = "10000";
    private static final String FUNDING_SOURCE_NAME_DEVELOPMENT_PROPOSAL = "Novartis Institutes for BioMedical Research Incorporated";
    private static final String FUNDING_SOURCE_TYPE_CODE_INSTITUTIONAL_PROPOSAL = "Institutional Proposal";
    private static final String FUNDING_SOURCE_NUMBER_INSTITUTIONAL_PROPOSAL = "10000";
    private static final String FUNDING_SOURCE_NAME_INSTITUTIONAL_PROPOSAL = "Institutional Proposal Title";
    private static final String FUNDING_SOURCE_TYPE_CODE_AWARD = "Award";
    private static final String FUNDING_SOURCE_NUMBER_AWARD = "010001-00001";
    private static final String FUNDING_SOURCE_NAME_AWARD = "Award Title";
    
    private static final String ADD_PROTOCOL_FUNDING_SOURCE_BUTTON = "methodToCall.addProtocolFundingSource";
    private static final String VIEW_PROTOCOL_FUNDING_SOURCE_BUTTON = "methodToCall.viewProtocolFundingSource.line%d";
    private static final String DELETE_PROTOCOL_FUNDING_SOURCE_BUTTON = "methodToCall.deleteProtocolFundingSource.line%d";

    private ProtocolSeleniumHelper helper;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProtocolSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Test addition of a sponsor funding source.
     * 
     * @throws Exception
     */
    @Test
    public void testAddViewSponsorFundingSourcePage() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.set(HELPER_FUNDING_SOURCE_TYPE_CODE_ID, FUNDING_SOURCE_TYPE_CODE_SPONSOR);
        helper.set(HELPER_FUNDING_SOURCE_NUMBER_ID, FUNDING_SOURCE_NUMBER_SPONSOR);
        
        helper.click(ADD_PROTOCOL_FUNDING_SOURCE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableCellValueContains(TABLE_ID, 2, 0, FUNDING_SOURCE_TYPE_CODE_SPONSOR);
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, FUNDING_SOURCE_NUMBER_SPONSOR);
        helper.assertTableCellValueContains(TABLE_ID, 2, 2, FUNDING_SOURCE_NAME_SPONSOR);
        
        helper.assertPopupWindowContains(String.format(VIEW_PROTOCOL_FUNDING_SOURCE_BUTTON, 0), FUNDING_SOURCE_NUMBER_SPONSOR);
    }
    
    /**
     * Test addition of a unit funding source.
     * 
     * @throws Exception
     */
    @Test
    public void testAddViewUnitFundingSourcePage() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);

        helper.set(HELPER_FUNDING_SOURCE_TYPE_CODE_ID, FUNDING_SOURCE_TYPE_CODE_UNIT);
        helper.set(HELPER_FUNDING_SOURCE_NUMBER_ID, FUNDING_SOURCE_NUMBER_UNIT);
        
        helper.click(ADD_PROTOCOL_FUNDING_SOURCE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableCellValueContains(TABLE_ID, 2, 0, FUNDING_SOURCE_TYPE_CODE_UNIT);
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, FUNDING_SOURCE_NUMBER_UNIT);
        helper.assertTableCellValueContains(TABLE_ID, 2, 2, FUNDING_SOURCE_NAME_UNIT);
        
        helper.assertPopupWindowContains(String.format(VIEW_PROTOCOL_FUNDING_SOURCE_BUTTON, 0), FUNDING_SOURCE_NUMBER_UNIT);
    }
    
    /**
     * Test addition of an other funding source.
     * 
     * @throws Exception
     */
    @Test
    public void testAddOtherFundingSourcePage() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);

        helper.set(HELPER_FUNDING_SOURCE_TYPE_CODE_ID, FUNDING_SOURCE_TYPE_CODE_OTHER);
        helper.set(HELPER_FUNDING_SOURCE_NUMBER_ID, FUNDING_SOURCE_NUMBER_OTHER);
        helper.set(HELPER_FUNDING_SOURCE_NAME_ID, FUNDING_SOURCE_NAME_OTHER);
        
        helper.click(ADD_PROTOCOL_FUNDING_SOURCE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableCellValueContains(TABLE_ID, 2, 0, FUNDING_SOURCE_TYPE_CODE_OTHER);
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, FUNDING_SOURCE_NUMBER_OTHER);
        helper.assertTableCellValueContains(TABLE_ID, 2, 2, FUNDING_SOURCE_NAME_OTHER);
    }
    
    /**
     * Test addition of a development proposal funding source.
     * 
     * @throws Exception
     */
    /*
     * This is ignored since there is no available DevProp test data yet...
     */
    @Ignore
    @Test
    public void testAddViewDevelopmentProposalFundingSourcePage() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);

        helper.set(HELPER_FUNDING_SOURCE_TYPE_CODE_ID, FUNDING_SOURCE_TYPE_CODE_DEVELOPMENT_PROPOSAL);
        helper.set(HELPER_FUNDING_SOURCE_NUMBER_ID, FUNDING_SOURCE_NUMBER_DEVELOPMENT_PROPOSAL);
        helper.set(HELPER_FUNDING_SOURCE_NAME_ID, FUNDING_SOURCE_NAME_DEVELOPMENT_PROPOSAL);
        
        helper.click(ADD_PROTOCOL_FUNDING_SOURCE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableCellValueContains(TABLE_ID, 2, 0, FUNDING_SOURCE_TYPE_CODE_DEVELOPMENT_PROPOSAL);
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, FUNDING_SOURCE_NUMBER_DEVELOPMENT_PROPOSAL);
        helper.assertTableCellValueContains(TABLE_ID, 2, 2, FUNDING_SOURCE_NAME_DEVELOPMENT_PROPOSAL);
        
        helper.click(String.format(VIEW_PROTOCOL_FUNDING_SOURCE_BUTTON, 0));
        helper.assertPageContains("Proposal Number: " + FUNDING_SOURCE_NUMBER_DEVELOPMENT_PROPOSAL);
    }
    
    /**
     * Test addition of a institutional proposal funding source.
     * 
     * @throws Exception
     */
    /*
     * This is ignored since there is no available InstProp test data yet...
     */
    @Ignore
    @Test
    public void testAddViewInstitutionalProposalFundingSourcePage() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);

        helper.set(HELPER_FUNDING_SOURCE_TYPE_CODE_ID, FUNDING_SOURCE_TYPE_CODE_INSTITUTIONAL_PROPOSAL);
        helper.set(HELPER_FUNDING_SOURCE_NUMBER_ID, FUNDING_SOURCE_NUMBER_INSTITUTIONAL_PROPOSAL);
        helper.set(HELPER_FUNDING_SOURCE_NAME_ID, FUNDING_SOURCE_NAME_INSTITUTIONAL_PROPOSAL);
        
        helper.click(ADD_PROTOCOL_FUNDING_SOURCE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableCellValueContains(TABLE_ID, 2, 0, FUNDING_SOURCE_TYPE_CODE_INSTITUTIONAL_PROPOSAL);
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, FUNDING_SOURCE_NUMBER_INSTITUTIONAL_PROPOSAL);
        helper.assertTableCellValueContains(TABLE_ID, 2, 2, FUNDING_SOURCE_NAME_INSTITUTIONAL_PROPOSAL);
        
        helper.click(String.format(VIEW_PROTOCOL_FUNDING_SOURCE_BUTTON, 0));
        helper.assertPageContains("Institutional Proposal ID: " + FUNDING_SOURCE_NUMBER_INSTITUTIONAL_PROPOSAL);
    }
    
    /**
     * Test addition of an award funding source.
     * 
     * @throws Exception
     */
    /*
     * This is ignored since there is no available Award test data yet...
     */
    @Ignore
    @Test
    public void testAddViewAwardFundingSourcePage() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);

        helper.set(HELPER_FUNDING_SOURCE_TYPE_CODE_ID, FUNDING_SOURCE_TYPE_CODE_AWARD);
        helper.set(HELPER_FUNDING_SOURCE_NUMBER_ID, FUNDING_SOURCE_NUMBER_AWARD);
        helper.set(HELPER_FUNDING_SOURCE_NAME_ID, FUNDING_SOURCE_NAME_AWARD);
        
        helper.click(ADD_PROTOCOL_FUNDING_SOURCE_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableCellValueContains(TABLE_ID, 2, 0, FUNDING_SOURCE_TYPE_CODE_AWARD);
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, FUNDING_SOURCE_NUMBER_AWARD);
        helper.assertTableCellValueContains(TABLE_ID, 2, 2, FUNDING_SOURCE_NAME_AWARD);
        
        helper.click(String.format(VIEW_PROTOCOL_FUNDING_SOURCE_BUTTON, 0));
        helper.assertPageContains("Award ID: " + FUNDING_SOURCE_NUMBER_AWARD);
    }
    
    /**
     * Test addition and deletion of a funding source.
     * 
     * @throws Exception
     */
    @Test
    public void testAddDeleteFundingSourcePage() throws Exception {
        helper.createProtocol();
        
        helper.openTab(TAB_ID);
        
        helper.set(HELPER_FUNDING_SOURCE_TYPE_CODE_ID, FUNDING_SOURCE_TYPE_CODE_SPONSOR);
        helper.set(HELPER_FUNDING_SOURCE_NUMBER_ID, FUNDING_SOURCE_NUMBER_SPONSOR);
        
        helper.click(ADD_PROTOCOL_FUNDING_SOURCE_BUTTON);
        
        helper.set(HELPER_FUNDING_SOURCE_TYPE_CODE_ID, FUNDING_SOURCE_TYPE_CODE_UNIT);
        helper.set(HELPER_FUNDING_SOURCE_NUMBER_ID, FUNDING_SOURCE_NUMBER_UNIT);
        
        helper.click(ADD_PROTOCOL_FUNDING_SOURCE_BUTTON);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(TABLE_ID, 4);

        helper.click(String.format(DELETE_PROTOCOL_FUNDING_SOURCE_BUTTON, 1));
        helper.clickYesAnswer();
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(TABLE_ID, 3);
        
        helper.assertTableCellValueContains(TABLE_ID, 2, 0, FUNDING_SOURCE_TYPE_CODE_SPONSOR);
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, FUNDING_SOURCE_NUMBER_SPONSOR);
        helper.assertTableCellValueContains(TABLE_ID, 2, 2, FUNDING_SOURCE_NAME_SPONSOR);
     }

}