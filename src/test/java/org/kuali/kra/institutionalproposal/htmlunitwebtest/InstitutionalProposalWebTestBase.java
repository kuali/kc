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

import org.junit.After;
import org.junit.Before;
import org.kuali.kra.KraWebTestBase;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class...
 */
public abstract class InstitutionalProposalWebTestBase extends KraWebTestBase {
    
    protected static final String CUSTOM_DATA_LINK_NAME = "customData.x";
    
    protected static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    protected static final String DOCUMENT_STATUS_CODE = "document.institutionalProposal.statusCode";
    protected static final String DEFAULT_ACTIVITY_TYPE_CODE = "document.institutionalProposal.activityTypeCode";
    protected static final String DOCUMENT_PROPOSAL_TYPE_CODE = "document.institutionalProposal.proposalTypeCode";
    protected static final String DOCUMENT_PROJECT_TITLE = "document.institutionalProposal.title";
    protected static final String DOCUMENT_SPONSOR_CODE = "document.institutionalProposal.sponsorCode";
    

    
    protected static final String DEFAULT_DOCUMENT_DESCRIPTION = "Institutional Proposal Web Test";
    protected static final String DEFAULT_PROJECT_TITLE = "Test Project";
    protected static final String DEFAULT_SPONSOR_CODE = "005891";

    protected static final String METHOD_TO_CALL_PREFIX = "methodToCall.";
    private static final String SAVE_BUTTON_METHOD = METHOD_TO_CALL_PREFIX + "save";
    private static final String RELOAD_BUTTON_METHOD = METHOD_TO_CALL_PREFIX + "reload";
    
    private static final String ELEMENT_GROUPING = "((<>))";
    private static final String XML_GROUPING = "((&lt;&gt;))";
    private static final String AMPERSAND = "&";
    private static final String XML_AMPERSAND = "&amp;";
    private static final String ONE = "1";

    private HtmlPage proposalHomePage; 
    
    /**
     * Web test setup overloading. Sets up Portal Page and Proposal page access.
     * 
     * @see org.kuali.kra.KraWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setProposalHomePage(buildProposalDocumentPage());
    }
        
    /**
     * Create a new instance of the Proposal document page by clicking on the link to the portal page. 
     * The resulting page of the click through is a frame, so it is important to get the inner page.
     * 
     * @return <code>{@link HtmlPage}</code> instance of the award document page
     * @throws IOException
     */
    protected final HtmlPage buildProposalDocumentPage() throws Exception {
        HtmlPage createProposalPage = clickOn(getPortalPage(), "Create Institutional Proposal", "Kuali Portal Index");
        createProposalPage = getInnerPages(createProposalPage).get(0);
        assertTrue("Kuali :: KC Institutional Proposal".equals(createProposalPage.getTitleText()));
        setDefaultRequiredFields(createProposalPage);
        return createProposalPage;
    }

    /**
     * Web test tear down overloading.
     * 
     * @see org.kuali.kra.KraWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Gets the Proposal Home web page for creating a new Proposal document.
     * We don't want to test within the Portal.  This means that we will extract the
     * proposal web page from within the Portal's Inline Frame (iframe).
     * 
     * @return the Award Home web page.
     */
    protected final HtmlPage getProposalHomePage() {
        return this.proposalHomePage;
    }
    
    /**
     * Sets the Proposal page for tests. Typically, run out of <code>{@link #setUp()}</code>
     * 
     * @param proposalHomePage <code>{@link HtmlPage}</code> instance for the test
     */
    protected final void setProposalHomePage(HtmlPage proposalHomePage) {
        this.proposalHomePage = proposalHomePage;
    }
    
    /**
     * As we build the proposal Home page further; the required fields will increase;
     * So we have to add more fields to this method as and when the fields are added 
     * on proposal Home page.
     * 
     * Sets the proposal Document's required fields to legal default values.
     * @param page the Proposal Development web page.
     */
    protected void setDefaultRequiredFields(HtmlPage page) {
        setFieldValue(page, DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        setFieldValue(page, DOCUMENT_STATUS_CODE, ONE);
        setFieldValue(page, DEFAULT_ACTIVITY_TYPE_CODE, ONE);
        setFieldValue(page, DOCUMENT_PROPOSAL_TYPE_CODE, ONE);
        setFieldValue(page, DOCUMENT_PROJECT_TITLE, DEFAULT_PROJECT_TITLE);
        setFieldValue(page, DOCUMENT_SPONSOR_CODE, DEFAULT_SPONSOR_CODE);
    }
    
    /**
     * 
     * This method finds an element in the specified page where the name starts 
     * with a unique name prefix
     * @param page
     * @param uniqueNamePrefix
     * @return The decoded String representing the found String 
     */
    protected String getImageTagName(HtmlPage page, String uniqueNamePrefix) {
        String pageAsXml = page.asXml();
        int idx1 = pageAsXml.indexOf(uniqueNamePrefix);        
        int idx2 = pageAsXml.indexOf("\"", idx1);
        String element = pageAsXml.substring(idx1, idx2); 
        return element.replace(XML_AMPERSAND, AMPERSAND).replace(XML_GROUPING, ELEMENT_GROUPING);
    }
    
    protected HtmlPage clickOnTab(HtmlPage page, String tabName) throws Exception {
        HtmlElement element = getElementByNameEndsWith(page, tabName);
        return clickOn(element);
    }
    
    /**
     * Find a button on the page
     * The button action is assumed to be named in the image tag name prepended with 'methodtoCall.' 
     * That is used to find the image which is used to find the actual button input control
     * @param page
     * @param methodToCall
     * @return
     */
    protected HtmlImageInput findAButtonByMethodToCallName(HtmlPage page, String methodToCall) {
        HtmlForm form = (HtmlForm) page.getForms().get(0);        
        String buttonName = getImageTagName(page, methodToCall);        
        return (HtmlImageInput) form.getInputByName(buttonName);
    }
    
    /**
     * This method causes a button to be pressed
     * @param page
     * @param methodToCall
     * @return
     * @throws IOException
     */
    protected HtmlPage pressAButton(HtmlPage page, String methodToCall) throws IOException {
        HtmlImageInput button = findAButtonByMethodToCallName(page, methodToCall);
        return (HtmlPage) button.click();
    }
    
    /**
     * This method causes the Save button to be pressed
     * @param page The page after the press is returned
     * @return
     * @throws IOException
     */
    protected HtmlPage save(HtmlPage page) throws IOException {
        return pressAButton(page, SAVE_BUTTON_METHOD);
    }

    /**
     * This method causes the Reload button to be pressed
     * @param page The page after the press is returned
     * @return
     * @throws IOException
     */
    protected HtmlPage reload(HtmlPage page) throws IOException {
        return pressAButton(page, RELOAD_BUTTON_METHOD);
    }
}
