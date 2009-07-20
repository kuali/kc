/*
 * Copyright 2006-2009 The Kuali Foundation.
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
package org.kuali.kra;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class KraWebTestUtil extends Assert {
    private static final String KRA_HOMEPAGE_TITLE = "Kuali Portal Index";
    private static final String KRA_DOCSEARCH_LINK_TEXT = "Document Search";
    private static final String KRA_PROPOSAL_PAGE_TITLE = "Kuali :: Proposal Development Document";
    private static final String KRA_BUDGET_PAGE_TITLE = "Kuali :: Budget Document";
    
    private static final String KRA_DOCSEARCH_BUTTON_ID = "methodToCall.doDocSearch";
    private static final String KRA_DOCUMENT_SAVE_BUTTON_ID = "methodToCall.save";
    private static final String KRA_DOCUMENT_RELOAD_BUTTON_ID = "methodToCall.reload";
    private static final String KRA_DOCUMENT_CLOSE_BUTTON_ID = "methodToCall.close";
    
    public static final String KRA_QUESTION_ANSWER_YES_BUTTON = "methodToCall.processAnswer.button0";
    public static final String KRA_QUESTION_ANSWER_NO_BUTTON = "methodToCall.processAnswer.button1";
    
    public static final String KRA_DOCSEARCH_INPUT_DOCUMENT_ELEMENT_ID = "criteria.routeHeaderId";
    private static final String KRA_BUDGET_NEW_BUDGETVER_ELEMENT_ID = "newBudgetVersionName";
    private static final String KRA_BUDGET_VERSION_ADD_BUTTON = "methodToCall.addBudgetVersion";
    
    public static final String KRA_TAB_RELOAD_BUDGETVERSIONS_BUTTON_ID = "methodToCall.headerTab.headerDispatch.reload.navigateTo.budgetVersions.x";
    public static final String KRA_TAB_SAVE_SPECIALREVIEW_BUTTON_ID = "methodToCall.headerTab.headerDispatch.save.navigateTo.specialReview.x";
 
    public static HtmlPage loadPage(WebClient webClient, String url) throws Exception {  
        return (HtmlPage) webClient.getPage(new URL(url));
    }
    
    public static HtmlPage performDocSearch(HtmlPage fromPage, Map<String, String> searchCriteria, String loginUserID) throws Exception {
        HtmlPage docSearchPage = HtmlUnitUtil.clickOnAnchorByTitle(fromPage, KRA_DOCSEARCH_LINK_TEXT, KRA_HOMEPAGE_TITLE, loginUserID);
        docSearchPage = HtmlUnitUtil.getInnerPages(docSearchPage).get(0);
        
        for(Iterator iter = searchCriteria.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, String> searchParameter = (Map.Entry<String, String>) iter.next();
            HtmlUnitUtil.setTextFieldValue(docSearchPage, searchParameter.getKey(), searchParameter.getValue()); 
        }
        
        HtmlImageInput button = HtmlUnitUtil.getImageInput(docSearchPage, KRA_DOCSEARCH_BUTTON_ID);
        HtmlPage docSearchResultsPage = (HtmlPage) button.click();
        return docSearchResultsPage;
    }
    
    public static HtmlPage loadProposalFromSearchResults(HtmlPage searchResultsPage, String documentNumber) throws Exception {
        HtmlAnchor documentIdLink = searchResultsPage.getFirstAnchorByText(documentNumber);
        HtmlPage proposalDocumentPage = (HtmlPage) documentIdLink.click();
        assertEquals(proposalDocumentPage.getTitleText(), KRA_PROPOSAL_PAGE_TITLE);
        return proposalDocumentPage;
    }
    
    public static HtmlPage navigateToTab(HtmlPage fromPage, String tabId) throws Exception {
        HtmlPage targetPage = HtmlUnitUtil.clickSubmitButton(fromPage, tabId);
        assertNotNull(targetPage);  
        return targetPage;
    }
    
    public static HtmlPage closePage(HtmlPage page) throws Exception {
        HtmlImageInput closeButton = getCloseButton(page);
        assertNotNull(closeButton);
        return (HtmlPage) closeButton.click();
    }
    
    public static HtmlPage savePage(HtmlPage page) throws Exception {
        HtmlImageInput saveButton = getSaveButton(page);
        assertNotNull(saveButton);
        return (HtmlPage) saveButton.click();
    }

    public static HtmlPage reloadPage(HtmlPage page) throws Exception {
        HtmlImageInput reloadButton = getReloadButton(page);
        assertNotNull(reloadButton);
        return (HtmlPage) reloadButton.click();
    }
    
    public static HtmlImageInput getReloadButton(HtmlPage page) {
        return HtmlUnitUtil.getImageInput(page, KRA_DOCUMENT_RELOAD_BUTTON_ID);
    }
    
    public static HtmlImageInput getCloseButton(HtmlPage page) {
        return HtmlUnitUtil.getImageInput(page, KRA_DOCUMENT_CLOSE_BUTTON_ID);
    }
    
    public static HtmlImageInput getSaveButton(HtmlPage page) {
        return HtmlUnitUtil.getImageInput(page, KRA_DOCUMENT_SAVE_BUTTON_ID);
    }
    
    public static HtmlImageInput getButton(HtmlPage page, String inputButtonName) {
        return HtmlUnitUtil.getImageInput(page, inputButtonName);
    }
    
    public static HtmlPage addBudgetVersion(HtmlPage budgetVersionsPage, String budgetVersionName) throws Exception {
        HtmlUnitUtil.setTextFieldValue(budgetVersionsPage, KRA_BUDGET_NEW_BUDGETVER_ELEMENT_ID, budgetVersionName); 
        HtmlImageInput addButton = HtmlUnitUtil.getImageInput(budgetVersionsPage, KRA_BUDGET_VERSION_ADD_BUTTON);
        return (HtmlPage) addButton.click();
    }
    
    public static HtmlPage openBudgetVersion(HtmlPage budgetVersionsPage, int versionToOpen) throws Exception {
        HtmlImageInput openBtn = HtmlUnitUtil.getImageInput(budgetVersionsPage, "methodToCall.openBudgetVersion.line" + versionToOpen + ".x");
        return (HtmlPage) openBtn.click();
    }
}
