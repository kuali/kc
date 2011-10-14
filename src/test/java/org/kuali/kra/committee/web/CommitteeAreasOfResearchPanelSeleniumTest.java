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
package org.kuali.kra.committee.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.test.infrastructure.KcSeleniumTestBase;

/**
 * Tests the Areas of Research tab in the Committee page of a Committee.
 */
public class CommitteeAreasOfResearchPanelSeleniumTest extends KcSeleniumTestBase {
    
    private static final String TABLE_ID = "researchAreaTableId";
    
    private static final String RESEARCH_AREA = "000001 All Research Areas";
    
    private static final String DELETE_RESEARCH_AREA_BUTTON = "methodToCall.deleteResearchArea";
 
    private CommitteeSeleniumHelper helper;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        helper = CommitteeSeleniumHelper.instance(driver);
    }
    
    @After
    public void tearDown() throws Exception {
        helper = null;
        
        super.tearDown();
    }
    
    /**
     * Tests adding a research area.
     * 
     * @throws Exception
     */
    @Test
    public void testAddResearchArea() throws Exception {
        helper.createCommittee();
        
        helper.assertTableRowCount(TABLE_ID, 3);
        helper.assertTableCellValue(TABLE_ID, RESEARCH_AREA);

        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.closeAndSearchDocument();
        
        helper.assertTableRowCount(TABLE_ID, 3);
        helper.assertTableCellValue(TABLE_ID, RESEARCH_AREA);
    }
    
    /**
     * Tests deleting a research area.
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteResearchArea() throws Exception {
        helper.createCommittee();
        
        helper.saveDocument();
        helper.assertNoPageErrors();
       
        helper.click(DELETE_RESEARCH_AREA_BUTTON);
        
        helper.assertTableRowCount(TABLE_ID, 2);
       
        helper.saveDocument();
        helper.assertNoPageErrors();
        
        helper.closeAndSearchDocument();
        
        helper.assertTableRowCount(TABLE_ID, 2);
    }
    
}