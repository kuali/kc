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
package org.kuali.kra.committee.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class CommitteeResearchAreasWebTest extends CommitteeWebTestBase {
 
    /***********************************************************************
     * Setup and TearDown
     ***********************************************************************/
    
    private boolean javaScriptEnabled;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        javaScriptEnabled = webClient.isJavaScriptEnabled();
        webClient.setJavaScriptEnabled(false);
    }
    
    @After
    public void tearDown() throws Exception {
        webClient.setJavaScriptEnabled(javaScriptEnabled);
        super.tearDown();
    }
    
    /**
     * Test adding a research area.
     * @throws Exception
     */
    @Test
    public void testAddResearchArea() throws Exception {
       
        HtmlPage committeePage = this.getCommitteePage();
        
        setDefaultRequiredFields(committeePage);
        setFieldValue(committeePage, COMMITTEE_ID_ID, "32676");
        
        /*
         * Do a specific lookup so we know what to look for back
         * on the committee web page.
         */
        committeePage = multiLookup(committeePage, "committeeResearchAreas", "researchAreaCode", "01.0101");
      
        /*
         * Verify that the table has three row (title, form, and new research area row),
         * and verify that the new research area is what we expected from the lookup.
         */
        HtmlTable table = getTable(committeePage, "researchAreaTableId");
        assertEquals(3, table.getRowCount());
        assertContains(committeePage, "Agricultural Business and Management");
    
        /*
         * Verify that we can save the document without getting any errors.
         */
        committeePage = this.saveDoc(committeePage);
        assertFalse(this.hasError(committeePage));
        
        /*
         * Get the committee again and verify that the research area
         * is still there, i.e. it was correctly saved to the database.
         */
        String docNbr = this.getDocNbr(committeePage);
        committeePage = docSearch(docNbr);
        
        table = getTable(committeePage, "researchAreaTableId");
        assertEquals(3, table.getRowCount());
        assertContains(committeePage, "Agricultural Business and Management");
    }
    
    /**
     * Test the deletion of a research area.
     * @throws Exception
     */
    @Test
    public void testDeleteResearchArea() throws Exception {
       
        HtmlPage committeePage = this.getCommitteePage();
        
        setDefaultRequiredFields(committeePage);
        setFieldValue(committeePage, COMMITTEE_ID_ID, "666777");
        
        /*
         * Do a specific lookup so we know what to look for back
         * on the committee web page.
         */
        committeePage = multiLookup(committeePage, "committeeResearchAreas", "researchAreaCode", "01.0101");
    
        /*
         * Save the research area to the database.
         */
        committeePage = this.saveAndSearchDoc(committeePage);
       
        /*
         * Delete the research area and verify that the research area table
         * doesn't have any research areas, i.e. it only has two rows for the
         * title and form.
         */
        HtmlElement deleteBtn = getElementByName(committeePage, "methodToCall.deleteResearchArea", true);
        committeePage = clickOn(deleteBtn);
        
        HtmlTable table = getTable(committeePage, "researchAreaTableId");
        assertEquals(2, table.getRowCount());
       
        /*
         * Verify that we can save the document without getting any errors.
         */
        committeePage = this.saveDoc(committeePage);
        assertFalse(this.hasError(committeePage));
        
        /*
         * Now verify that we can search for document and that the 
         * research area is indeed gone.  This verifies that it was
         * correctly deleted from the database.
         */
        String docNbr = this.getDocNbr(committeePage);
        committeePage = docSearch(docNbr);
        
        table = getTable(committeePage, "researchAreaTableId");
        assertEquals(2, table.getRowCount());
    }
}
