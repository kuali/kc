/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Keywords tab in the Proposal page of a Development Proposal.
 */
public class ProposalDevelopmentKeywordPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TAB_ID = "Keywords";
    private static final String TABLE_ID = "keywords-table";
    
    private static final String KEYWORDS_TAG = "propScienceKeywords";

    private static final String LIST_PREFIX = "document.developmentProposalList[0].propScienceKeyword[%d].";
    
    private static final String DESCRIPTION_ID = "description";
    private static final String SELECT_KEYWORD_ID = "selectKeyword";
    private static final String LIST_SELECT_KEYWORD_ID = LIST_PREFIX + SELECT_KEYWORD_ID;
    
    private static final String KEYWORDS_DESCRIPTION_WILDCARD = "T*";
    private static final String KEYWORDS_DESCRIPTION_TRANSMITTANCE = "Transmittance";
    private static final String KEYWORDS_DESCRIPTION_TEMPERATURE = "Temperature";
    private static final String SELECT_KEYWORD_CHECKED = "on";
    private static final String SELECT_KEYWORD_UNCHECKED = "off";

    private static final String SELECT_ALL_BUTTON = "methodToCall.selectAllScienceKeyword";
    private static final String DELETE_SELECTED_BUTTON = "methodToCall.deleteSelectedScienceKeyword";

    private ProposalDevelopmentSeleniumHelper helper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = ProposalDevelopmentSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Test the addition of keywords by adding two keywords.
     *
     * @throws Exception
     */
    @Test
    public void testAddKeyword() throws Exception {
        helper.createProposalDevelopment();
        
        helper.openTab(TAB_ID);

        helper.multiLookup(KEYWORDS_TAG, DESCRIPTION_ID, KEYWORDS_DESCRIPTION_WILDCARD);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(TABLE_ID, 5);
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, KEYWORDS_DESCRIPTION_TRANSMITTANCE);
        helper.assertElementContains(String.format(LIST_SELECT_KEYWORD_ID, 0), SELECT_KEYWORD_UNCHECKED);
        helper.assertTableCellValueContains(TABLE_ID, 3, 1, KEYWORDS_DESCRIPTION_TEMPERATURE);
        helper.assertElementContains(String.format(LIST_SELECT_KEYWORD_ID, 1), SELECT_KEYWORD_UNCHECKED);
    }
    
    /**
     * Test the select all button.
     *
     * @throws Exception
     */
    @Test
    public void testSelectAllKeyword() throws Exception {
        helper.createProposalDevelopment();
        
        helper.openTab(TAB_ID);

        helper.multiLookup(KEYWORDS_TAG, DESCRIPTION_ID, KEYWORDS_DESCRIPTION_WILDCARD);
        
        helper.click(SELECT_ALL_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertElementContains(String.format(LIST_SELECT_KEYWORD_ID, 0), SELECT_KEYWORD_CHECKED);
        helper.assertElementContains(String.format(LIST_SELECT_KEYWORD_ID, 1), SELECT_KEYWORD_CHECKED);
    }
    
    /**
     * Test the delete selected button.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteSelectedKeyword() throws Exception {
        helper.createProposalDevelopment();
        
        helper.openTab(TAB_ID);

        helper.multiLookup(KEYWORDS_TAG, DESCRIPTION_ID, KEYWORDS_DESCRIPTION_WILDCARD);
        
        helper.saveDocument();
        helper.assertNoPageErrors();

        helper.set(String.format(LIST_SELECT_KEYWORD_ID, 1), SELECT_KEYWORD_CHECKED);
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, KEYWORDS_DESCRIPTION_TRANSMITTANCE);
        helper.assertElementContains(String.format(LIST_SELECT_KEYWORD_ID, 0), SELECT_KEYWORD_UNCHECKED);
        helper.assertTableCellValueContains(TABLE_ID, 3, 1, KEYWORDS_DESCRIPTION_TEMPERATURE);
        helper.assertElementContains(String.format(LIST_SELECT_KEYWORD_ID, 1), SELECT_KEYWORD_CHECKED);
        
        helper.click(DELETE_SELECTED_BUTTON);
        
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.assertTableRowCount(TABLE_ID, 4);
        helper.assertTableCellValueContains(TABLE_ID, 2, 1, KEYWORDS_DESCRIPTION_TRANSMITTANCE);
        helper.assertElementContains(String.format(LIST_SELECT_KEYWORD_ID, 0), SELECT_KEYWORD_UNCHECKED);
    }
    
}