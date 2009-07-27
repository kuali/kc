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
package org.kuali.kra.institutionalproposal.htmlunitwebtest;

import java.io.IOException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

/**
 * This class...
 */
public class InstitutionalProposalKeywordWebTest extends InstitutionalProposalHomeWebTest {

    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String DOCUMENT_SAVED = "Document was successfully saved";
    private static final String SCIENCE_KEYWORD_CHECKBOX_FIELD = "document.institutionalProposal.institutionalProposalScienceKeywords[0].selectKeyword";
    private static final String FIRST_ROW_DATA = "Temperature";
    private static final String SECOND_ROW_DATA = "Transmittance";
    private static final String FIRST_ROW_DATA_CHECKED = "Temperature checked";
    private static final String FIRST_ROW_DATA_UNCHECKED = "Temperature unchecked";
    private static final String CHECKBOX_CHECKED = "on";
    private static final String CHECKBOX_UNCHECKED = "off";
    private static final String JS_SELECT_ALL = "selectAllAwardKeywords(document)";
    private static final String BUTTON_DELETE_SELECTED = "methodToCall.deleteSelectedScienceKeyword.anchorKeywords";
    private static final String BUTTON_SELECT_ALL = "methodToCall.selectAllScienceKeyword.anchorKeywords";
    private static final String BUTTON_SAVE = "save";
    private String keywordStatus = CHECKBOX_UNCHECKED;

    private static final String DEFAULT_DOCUMENT_DESCRIPTION = "Award Keywords Web Test";

    @Test
    public void testKeywordPanel() throws Exception {

        HtmlPage institutionalProposalHomePage = getProposalHomePage();
        setFieldValue(institutionalProposalHomePage, DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        
        final HtmlPage pageAfterInitSave = saveAndVerifyData(institutionalProposalHomePage);
        HtmlPage pageKeywordLookup = scienceKeywordLookupTest(pageAfterInitSave);
        final HtmlPage pageAfterKeywordLookup = saveAndVerifyData(pageKeywordLookup);
        final ScriptResult scriptResult = pageAfterKeywordLookup.executeJavaScriptIfPossible(JS_SELECT_ALL, "onSubmit", pageAfterKeywordLookup.getDocumentHtmlElement());
        final HtmlPage pageAfterSelectAll = (HtmlPage)scriptResult.getNewPage();
        assertContains(pageAfterSelectAll, FIRST_ROW_DATA_CHECKED);
        setFieldValue(pageAfterSelectAll, SCIENCE_KEYWORD_CHECKBOX_FIELD, CHECKBOX_UNCHECKED);
        assertContains(pageAfterSelectAll, FIRST_ROW_DATA_UNCHECKED);
        final HtmlPage pageAfterSelect = clickOn(pageAfterSelectAll,BUTTON_SELECT_ALL);
        keywordStatus = getFieldValue(pageAfterSelect, SCIENCE_KEYWORD_CHECKBOX_FIELD);
        assertEquals(keywordStatus, CHECKBOX_CHECKED);
        setFieldValue(pageAfterSelect, SCIENCE_KEYWORD_CHECKBOX_FIELD, CHECKBOX_UNCHECKED);
        assertContains(pageAfterSelect, FIRST_ROW_DATA_UNCHECKED);
        final HtmlPage pageAfterDeleteSelected = clickOn(pageAfterSelectAll,BUTTON_DELETE_SELECTED);
        assertDoesNotContain(pageAfterDeleteSelected, SECOND_ROW_DATA);
        assertContains(pageAfterDeleteSelected, FIRST_ROW_DATA_UNCHECKED);
        final HtmlPage pageComplete = saveAndVerifyData(pageAfterDeleteSelected);
        assertNotNull(pageComplete);
    }

    private HtmlPage scienceKeywordLookupTest(final HtmlPage pageAfterInitSave) throws IOException {
        HtmlPage pageKeywordLookup = multiLookup(pageAfterInitSave, "ScienceKeyword", "description", "T*");
        HtmlTable table = getTable(pageKeywordLookup, "tab-Keywords-div");
        assertEquals(table.getRowCount(), 5);
        keywordStatus = getFieldValue(pageKeywordLookup, SCIENCE_KEYWORD_CHECKBOX_FIELD);
        assertContains(pageKeywordLookup, FIRST_ROW_DATA);
        assertEquals(keywordStatus, CHECKBOX_UNCHECKED);
        return pageKeywordLookup;
    }
    
    private HtmlPage saveAndVerifyData(HtmlPage dataPage) throws IOException {
        HtmlPage pageSaved = clickOn(dataPage, BUTTON_SAVE);
        assertContains(pageSaved, DOCUMENT_SAVED);
        assertDoesNotContain(pageSaved, ERRORS_FOUND_ON_PAGE);
        return pageSaved;
    }

}
