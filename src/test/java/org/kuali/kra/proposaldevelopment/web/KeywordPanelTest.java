/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class KeywordPanelTest extends ProposalDevelopmentWebTestBase{
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String DOCUMENT_SAVED = "Document was successfully saved";
    private static final String SCIENCE_KEYWORD_CHECKBOX_FIELD = "document.propScienceKeywords[0].selectKeyword";
    private static final String FIRST_ROW_DATA = "1 Diabetes";
    private static final String SECOND_ROW_DATA = "10 Chemosynthesis";
    private static final String FIRST_ROW_DATA_CHECKED = "1 Diabetes checked";
    private static final String FIRST_ROW_DATA_UNCHECKED = "1 Diabetes unchecked";
    private static final String CHECKBOX_CHECKED = "on";
    private static final String CHECKBOX_UNCHECKED = "off";
    private static final String JS_SELECT_ALL = "selectAllKeywords(document)";
    private static final String BUTTON_DELETE_SELECTED = "methodToCall.deleteSelectedScienceKeyword";
    private static final String BUTTON_SELECT_ALL = "methodToCall.selectAllScienceKeyword";
    private static final String BUTTON_SAVE = "save";
    private String keywordStatus = CHECKBOX_UNCHECKED;

    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Proposal Development Web Test";
    private static final String DEFAULT_PROPOSAL_SPONSOR_CODE = "005894";
    private static final String DEFAULT_PROPOSAL_TITLE = "Project title";
    private static final String DEFAULT_PROPOSAL_REQUESTED_START_DATE = "08/14/2007";
    private static final String DEFAULT_PROPOSAL_REQUESTED_END_DATE = "08/21/2007";
    private static final String DEFAULT_PROPOSAL_ACTIVITY_TYPE = "1";
    private static final String DEFAULT_PROPOSAL_TYPE_CODE = "1";
    private static final String DEFAULT_PROPOSAL_OWNED_BY_UNIT = "IN-PERS";

    @Test
    public void testKeywordPanel() throws Exception {

        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION,
                DEFAULT_PROPOSAL_SPONSOR_CODE,
                DEFAULT_PROPOSAL_TITLE,
                DEFAULT_PROPOSAL_REQUESTED_START_DATE,
                DEFAULT_PROPOSAL_REQUESTED_END_DATE,
                DEFAULT_PROPOSAL_ACTIVITY_TYPE,
                DEFAULT_PROPOSAL_TYPE_CODE,
                DEFAULT_PROPOSAL_OWNED_BY_UNIT);

        /* Save with basic/mandatory data and verify data saved */
        final HtmlPage pageAfterInitSave = saveAndVerifyData(proposalPage);

        /* performing science keyword lookup */
        HtmlPage pageKeywordLookup = multiLookup(pageAfterInitSave, "ScienceKeyword", "scienceKeywordCode", "1*");
        HtmlTable table = getTable(pageKeywordLookup, "tab-Keywords-div");
        assertEquals(table.getRowCount(), 5);
        
        /* verify data returned by keyword lookup */
        keywordStatus = getFieldValue(pageKeywordLookup, SCIENCE_KEYWORD_CHECKBOX_FIELD);
        assertContains(pageKeywordLookup, FIRST_ROW_DATA);
        assertEquals(keywordStatus, CHECKBOX_UNCHECKED);

        /* save document with science keyword data */
        final HtmlPage pageAfterKeywordLookup = saveAndVerifyData(pageKeywordLookup);

        /* Test javascript for select all */
        final ScriptResult scriptResult = pageAfterKeywordLookup.executeJavaScriptIfPossible(JS_SELECT_ALL, "onSubmit", true, pageAfterKeywordLookup.getDocumentElement());
        final HtmlPage pageAfterSelectAll = (HtmlPage)scriptResult.getNewPage();

        /* verify data after select all */
        assertContains(pageAfterSelectAll, FIRST_ROW_DATA_CHECKED);

        /* uncheck first row. science keyword */
        setFieldValue(pageAfterSelectAll, SCIENCE_KEYWORD_CHECKBOX_FIELD, CHECKBOX_UNCHECKED);
        assertContains(pageAfterSelectAll, FIRST_ROW_DATA_UNCHECKED);
        
        /* check server side select all */
        final HtmlPage pageAfterSelect = clickOn(pageAfterSelectAll,BUTTON_SELECT_ALL);

        /* verify data after server side select all */
        keywordStatus = getFieldValue(pageAfterSelect, SCIENCE_KEYWORD_CHECKBOX_FIELD);
        assertEquals(keywordStatus, CHECKBOX_CHECKED);
        
        /* uncheck first row */
        setFieldValue(pageAfterSelect, SCIENCE_KEYWORD_CHECKBOX_FIELD, CHECKBOX_UNCHECKED);
        assertContains(pageAfterSelect, FIRST_ROW_DATA_UNCHECKED);
        
        /* check delete selected function - delete all rows other than one unchecked above*/
        final HtmlPage pageAfterDeleteSelected = clickOn(pageAfterSelectAll,BUTTON_DELETE_SELECTED);
        assertDoesNotContain(pageAfterDeleteSelected, SECOND_ROW_DATA);
        assertContains(pageAfterDeleteSelected, FIRST_ROW_DATA_UNCHECKED);

        /* save after removing all rows except one - unchecked above */
        final HtmlPage pageComplete = saveAndVerifyData(pageAfterDeleteSelected);

    }
    
    private HtmlPage saveAndVerifyData(HtmlPage dataPage) throws IOException {
        HtmlPage pageSaved = clickOn(dataPage, BUTTON_SAVE);
        assertContains(pageSaved, DOCUMENT_SAVED);
        assertDoesNotContain(pageSaved, ERRORS_FOUND_ON_PAGE);
        return pageSaved;
    }

}
