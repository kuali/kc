/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.htmlunitwebtest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.kuali.kra.KraWebTestBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.kew.exception.WorkflowException;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Base class for all htmlunit tests involving the Award Document Pages.
 * 
 */
public abstract class AwardWebTestBase extends KraWebTestBase {
    protected static final String CONTACTS_LINK_NAME = "contacts.x";
    protected static final String SPECIAL_REVIEW_LINK_NAME = "specialReview.x";
    protected static final String CUSTOM_DATA_LINK_NAME = "customData.x";
    protected static final String PERMISSIONS_LINK_NAME = "permissions.x";
    protected static final String AWARD_ACTIONS_LINK_NAME = "awardActions.x";
    protected static final String DEFAULT_DOCUMENT_DESCRIPTION = "Award Development Web Test";    
    protected static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    protected static final String SOFT_ERRORS_FOUND_ON_PAGE = "Warnings found in this Section";
    protected static final String SAVE_SUCCESS_MESSAGE = "Document was successfully saved";
    protected static final String ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST = "table or view does not exist";
    protected static final String METHOD_TO_CALL_PREFIX = "methodToCall.";
    protected static final String EXCEPTION_OR_SYSTEM_ERROR = "Error occurred while processing this request";
    protected static final String EMPTY_STRING = "";
    
    protected static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    protected static final String AWARD_ID_PREFIX = "document.awardList[0].";
    protected static final String AWARD_TYPE_ID = AWARD_ID_PREFIX + "awardTypeCode";
    protected static final String AWARD_TITLE_ID = AWARD_ID_PREFIX + "title";
    protected static final String PREF_SPONSOR_CODE_ID = AWARD_ID_PREFIX + "primeSponsorCode";
    protected static final String SPONSOR_CODE_ID = AWARD_ID_PREFIX + "sponsorCode";
    protected static final String STATUS_CODE_ID = AWARD_ID_PREFIX + "statusCode";
    protected static final String MOD_NUMBER_ID = AWARD_ID_PREFIX + "modificationNumber";
    protected static final String SPONSOR_AWARD_NUMBER_ID = AWARD_ID_PREFIX + "sponsorAwardNumber";
    protected static final String AWARD_EXEC_DATE_ID = AWARD_ID_PREFIX + "awardExecutionDate";
    protected static final String AWARD_EFF_DATE_ID = AWARD_ID_PREFIX + "awardEffectiveDate";
    protected static final String ACTIVITY_TYPE_CODE_ID = AWARD_ID_PREFIX + "activityTypeCode";
    protected static final String BEGIN_DATE_ID = AWARD_ID_PREFIX + "beginDate";
    protected static final String PROJECT_END_DATE_ID = AWARD_ID_PREFIX + "awardAmountInfos[0].finalExpirationDate";
    
    protected static final String SAVE_PAGE = "methodToCall.save";
    protected static final String RELOAD_PAGE = "methodToCall.reload";
    protected static final String CHECKED = "on";
    protected static final String UNCHECKED = "off";
    
    private static final String ONE = "1";
    private static final String AWARD_TITLE = "Award Title";
    private static final String GOOGLE_SPONSOR_CODE = "005979";
    private static final String SPONSOR_AWARD_NUMBER = "1R01CA123456";
    private static final String DATE_VALUE = "03/01/2009";
    private static final String END_DATE_VALUE = "09/01/2010";
    
    private static final String POUND_SIGN = "#";
    private static final String COLON = ":";
    private static final String ELEMENT_GROUPING = "((<>))";
    private static final String XML_GROUPING = "((&lt;&gt;))";
    private static final String AMPERSAND = "&";
    private static final String XML_AMPERSAND = "&amp;";
    private static final String SAVE_BUTTON_METHOD = METHOD_TO_CALL_PREFIX + "save";
    private static final String RELOAD_BUTTON_METHOD = METHOD_TO_CALL_PREFIX + "reload";
    
    private HtmlPage awardHomePage;    
    
    /**
     * Web test setup overloading. Sets up Portal Page and ProposalDevelopment page access.
     * 
     * @see org.kuali.kra.KraWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setAwardHomePage(buildAwardDocumentPage());
    }
        
    /**
     * Create a new instance of the award document page by clicking on the link to the portal page. 
     * The resulting page of the click through is a frame, so it is important to get the inner page.
     * 
     * @return <code>{@link HtmlPage}</code> instance of the award document page
     * @throws IOException
     */
    protected final HtmlPage buildAwardDocumentPage() throws Exception {
        HtmlPage createAwardPage = clickOn(getPortalPage(), "Create Award", "Kuali Portal Index");
        createAwardPage = getInnerPages(createAwardPage).get(0);
        assertTrue("Kuali :: Award Document".equals(createAwardPage.getTitleText()));
        setDefaultRequiredFields(createAwardPage);
        return createAwardPage;
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
     * Gets the Award Home web page for creating a new Award document.
     * We don't want to test within the Portal.  This means that we will extract the
     * proposal development web page from within the Portal's Inline Frame (iframe).
     * 
     * @return the Award Home web page.
     */
    protected final HtmlPage getAwardHomePage() {
        return this.awardHomePage;
    }
    
    /**
     * Sets the award page for tests. Typically, run out of <code>{@link #setUp()}</code>
     * 
     * @param awardHomePage <code>{@link HtmlPage}</code> instance for the test
     */
    protected final void setAwardHomePage(HtmlPage awardHomePage) {
        this.awardHomePage = awardHomePage;
    }
    
    /**
     * As we build the Award Home page further; the required fields will increase;
     * So we have to add more fields to this method as and when the fields are added 
     * on Award Home page.
     * 
     * Sets the Award Document's required fields to legal default values.
     * @param page the Proposal Development web page.
     */
    protected void setDefaultRequiredFields(HtmlPage page) {
        setFieldValue(page, DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        setFieldValue(page, AWARD_TYPE_ID, ONE);
        setFieldValue(page, AWARD_TITLE_ID, AWARD_TITLE);
        setFieldValue(page, PREF_SPONSOR_CODE_ID, GOOGLE_SPONSOR_CODE);
        setFieldValue(page, STATUS_CODE_ID, ONE);
        setFieldValue(page, SPONSOR_CODE_ID, GOOGLE_SPONSOR_CODE);
        setFieldValue(page, MOD_NUMBER_ID, ONE);
        setFieldValue(page, SPONSOR_AWARD_NUMBER_ID, SPONSOR_AWARD_NUMBER);
        setFieldValue(page, SPONSOR_AWARD_NUMBER_ID, SPONSOR_AWARD_NUMBER);
        setFieldValue(page, AWARD_EXEC_DATE_ID, DATE_VALUE);
        setFieldValue(page, AWARD_EFF_DATE_ID, DATE_VALUE);
        setFieldValue(page, ACTIVITY_TYPE_CODE_ID, ONE);
        setFieldValue(page, BEGIN_DATE_ID, DATE_VALUE);
        setFieldValue(page, PROJECT_END_DATE_ID, END_DATE_VALUE);
    }
    
    /**
     * 
     * This method is to test the <code>ExtendedTextArea</code> tag
     * @param page
     * @param textAreaFieldName
     * @param moreTextToBeAdded
     * @param action
     * @param textAreaLabel
     * @param tabIndex
     * @throws Exception
     */
    protected void testTextAreaPopup(HtmlPage page, String textAreaFieldName, String moreTextToBeAdded, 
                                        String action, String textAreaLabel, String tabIndex) throws Exception{
        String controlId = buildTextAreaControlId(textAreaFieldName, action, textAreaLabel, tabIndex);
        HtmlPage textAreaPopupPage = clickOn(page, controlId);
        String currentValue = getFieldValue(textAreaPopupPage, textAreaFieldName);
        String completeText = currentValue+moreTextToBeAdded;
        setFieldValue(textAreaPopupPage, textAreaFieldName, completeText);
        super.assertContains(textAreaPopupPage, textAreaLabel);
        HtmlPage textAreasAddedPage = clickOn(textAreaPopupPage,"methodToCall.postTextAreaToParent.anchor"+tabIndex);
        assertEquals(getFieldValue(textAreasAddedPage, textAreaFieldName), completeText);
    }

    /**
     * 
     * This method checks the values mentioned in the map against the values in from the page.
     * It uses getFieldValue(HtmlPage,string) method to get the value from page by using key.
     * @param page
     * @param keyValues
     */
    protected void validatePage(HtmlPage page, Map<String, String> keyValues) {
        Iterator<String> it = keyValues.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            assertEquals(getFieldValue(page, key), keyValues.get(key));
        }
    }
    
    /**
     * Get the Award Actions Web Page. To do this, we first
     * get the Award Home page and fill in the required
     * fields with some default values.  We can then navigate to the
     * Award Actions Web Page.
     * 
     * @return the Award Actions Web Page.
     * @throws Exception
     */
    protected HtmlPage getAwardActionsPage() throws Exception {
        HtmlPage awardHomePage = this.getAwardHomePage();
        HtmlPage awardActionsPage = clickOnTab(awardHomePage, AWARD_ACTIONS_LINK_NAME);
        return awardActionsPage;
    }
    
    /**
     * Get the Permissions Web Page. To do this, we first get the Award  
     * Web Page and fill in the required fields with some default values.  We can 
     * then navigate to the Permissions Web Page.
     * 
     * @return the Permissions Web Page.
     * @throws Exception
     */
    protected HtmlPage getPermissionsPage() throws Exception {
        HtmlPage awardPage = getAwardHomePage();
        this.setDefaultRequiredFields(awardPage);
        awardPage = savePage(awardPage);
        validateSavedPage(awardPage);
        return clickOnTab(awardPage, PERMISSIONS_LINK_NAME);
    }
    
    /**
     * This method is to save a given page
     * @param page
     * @return saved page
     * @throws Exception
     */
    protected HtmlPage savePage(HtmlPage page) throws Exception {
        HtmlPage savedPage = clickOn(page, SAVE_PAGE);
        return savedPage;
    }
    
    /**
     * This method is to validate a saved page. Check to see if there are no errors in the page
     * and save success message is displayed
     * @param page
     * @return
     * @throws Exception
     */
    protected void validateSavedPage(HtmlPage page) throws Exception {
        assertDoesNotContain(page, ERRORS_FOUND_ON_PAGE);
        assertContains(page,SAVE_SUCCESS_MESSAGE);        
    }
    
    protected HtmlPage getTabPage(String tabPageLinkName) throws Exception {
        HtmlPage awardHomePage = getAwardHomePage();
        this.setDefaultRequiredFields(awardHomePage);
        return clickOnTab(awardHomePage, tabPageLinkName);
    }
            
    protected HtmlPage clickOnTab(HtmlPage page, String tabName) throws Exception {
        HtmlElement element = getElementByNameEndsWith(page, tabName);
        return clickOn(element);
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
    
    /**
     * As we build the Award Home page further; the required fields will increase;
     * So we have to add more fields to this method as and when the fields are added 
     * on Award Home page.
     * This method checks document fields against the passed in values
     * @param doc the document to check values against
     * @param description to check
     * @throws WorkflowException
     */
    protected void verifySavedRequiredFields(AwardDocument doc, String description) throws WorkflowException {
        assertEquals(description, doc.getDocumentHeader().getDocumentDescription());
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

    private String buildTextAreaControlId(String textAreaFieldName, String action, String textAreaLabel, String tabIndex) {
        return new StringBuilder("methodToCall.updateTextArea.((")
                                    .append(POUND_SIGN)
                                    .append(textAreaFieldName)
                                    .append(COLON)
                                    .append(action)
                                    .append(COLON)
                                    .append(textAreaLabel)
                                    .append(POUND_SIGN)
                                    .append("))")
                                    .append(tabIndex).toString();
    }
}
