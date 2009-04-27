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
package org.kuali.kra.irb.htmlunitwebtest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.kuali.kra.KraWebTestBase;
import org.kuali.kra.irb.document.ProtocolDocument;
import org.kuali.rice.kew.exception.WorkflowException;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Base class for all htmlunit tests involving the Protocol Document Pages.
 * 
 * FIXME: this class was mostly taken from the AwardWebTestBase.
 * 
 * We should consolidate these classes to avoid the duplication before we actually start using it.
 */

public abstract class ProtocolWebTestBase extends KraWebTestBase {
    
    protected static final String PROTOCOL_LINK_NAME = "protocol.x";
    protected static final String PERSONNEL_LINK_NAME = "personnel.x";
    protected static final String CUSTOM_DATA_LINK_NAME = "customData.x";
    protected static final String PERMISSIONS_LINK_NAME = "permissions.x";
    protected static final String PROTOCOL_ACTIONS_LINK_NAME = "protocolActions.x";
    
    protected static final String DOCUMENT_DESCRIPTION_ID = "document.documentHeader.documentDescription";
    protected static final String DEFAULT_DOCUMENT_DESCRIPTION = "Protocol Development Web Test";
    
    protected static final String PROTOCOL_TYPE_ID = "document.protocolList[0].protocolTypeCode";
    protected static final int DEFAULT_PROTOCOL_TYPE = 2;
    
    protected static final String PROTOCOL_TITLE = "document.protocolList[0].title";
    protected static final String DEFAULT_PROTOCOL_TITLE = "Protocol Development Web Test Title";
    
    protected static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    protected static final String SOFT_ERRORS_FOUND_ON_PAGE = "Warnings found in this Section";
    protected static final String SAVE_SUCCESS_MESSAGE = "Document was successfully saved";
    protected static final String ERROR_TABLE_OR_VIEW_DOES_NOT_EXIST = "table or view does not exist";
    protected static final String METHOD_TO_CALL_PREFIX = "methodToCall.";
    protected static final String EXCEPTION_OR_SYSTEM_ERROR = "Error occurred while processing this request";
    protected static final String EMPTY_STRING = "";
    
    private static final String POUND_SIGN = "#";
    private static final String COLON = ":";
    private static final String ELEMENT_GROUPING = "((<>))";
    private static final String XML_GROUPING = "((&lt;&gt;))";
    private static final String AMPERSAND = "&";
    private static final String XML_AMPERSAND = "&amp;";
    private static final String SAVE_BUTTON_METHOD = METHOD_TO_CALL_PREFIX + "save";
    private static final String RELOAD_BUTTON_METHOD = METHOD_TO_CALL_PREFIX + "reload";
    
    private HtmlPage protocolHomePage;    
    
    /**
     * Web test setup. Sets up Portal Page and ProposalDevelopment page access.
     * 
     * @see org.kuali.kra.KraWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setProtocolHomePage(buildProtocolDocumentPage());
    }
        
    /**
     * Create a new instance of the protocol document page by clicking on the link to the portal page. 
     * The resulting page of the click through is a frame, so it is important to get the inner page.
     * 
     * @return <code>{@link HtmlPage}</code> instance of the protocol document page
     * @throws IOException
     */
    protected final HtmlPage buildProtocolDocumentPage() throws Exception {
        HtmlPage retval = clickOn(getPortalPage(), "Create Protocol", "Kuali Portal Index");
        retval = getInnerPages(retval).get(0);
        System.out.println(retval.getTitleText());
        assertTrue("Kuali :: Protocol Document".equals(retval.getTitleText()));
        setDefaultRequiredFields(retval);
        return retval;
    }

    /**
     * Web test tear down.
     * 
     * @see org.kuali.kra.KraWebTestBase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        this.protocolHomePage = null;
    }

    /**
     * Gets the Protocol Home web page for creating a new Protocol document.
     * We don't want to test within the Portal.  This means that we will extract the
     * proposal development web page from within the Portal's Inline Frame (iframe).
     * 
     * @return the Protocol Home web page.
     */
    protected final HtmlPage getProtocolHomePage() {
        return this.protocolHomePage;
    }
    
    /**
     * Sets the Protocol page for tests. Typically, run out of <code>{@link #setUp()}</code>
     * 
     * @param protocolHomePage <code>{@link HtmlPage}</code> instance for the test
     */
    protected final void setProtocolHomePage(HtmlPage protocolHomePage) {
        this.protocolHomePage = protocolHomePage;
    }
    
    /**
     * As we build the Protocol Home page further; the required fields will increase;
     * So we have to add more fields to this method as and when the fields are added 
     * on Protocol Home page.
     * 
     * Sets the Protocol Document's required fields to legal default values.
     * @param page the Protocol web page.
     */
    protected void setDefaultRequiredFields(HtmlPage page) {       
        System.err.println(page.asXml());
        setFieldValue(page, DOCUMENT_DESCRIPTION_ID, DEFAULT_DOCUMENT_DESCRIPTION);
        setFieldValue(page, PROTOCOL_TYPE_ID, "1");
        setFieldValue(page, PROTOCOL_TITLE, DEFAULT_PROTOCOL_TITLE);
        setFieldValue(page, "document.protocolList[0].principalInvestigatorId", "000000010");
        setFieldValue(page, "protocolHelper.leadUnitNumber", "000001");
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
            String key = it.next();
            assertEquals(getFieldValue(page, key), keyValues.get(key));
        }
    }
    
    /**
     * Get the Protocol Actions Web Page. To do this, we first
     * get the Protocol Home page and fill in the required
     * fields with some default values.  We can then navigate to the
     * Protocol Actions Web Page.
     * 
     * @return the Protocol Actions Web Page.
     * @throws Exception
     */
    protected HtmlPage getProtocolActionsPage() throws Exception {
        HtmlPage ProtocolHomePage = this.getProtocolHomePage();
        System.err.println("home page: " + ProtocolHomePage.asText());
        HtmlPage ProtocolActionsPage = clickOnTab(ProtocolHomePage, PROTOCOL_ACTIONS_LINK_NAME);
        System.err.println("actions page: " + ProtocolActionsPage.asText());
        return ProtocolActionsPage;
    }
    
    protected HtmlPage getTabPage(String tabPageLinkName) throws Exception {
        HtmlPage ProtocolHomePage = getProtocolHomePage();
        this.setDefaultRequiredFields(ProtocolHomePage);
        return clickOnTab(ProtocolHomePage, tabPageLinkName);
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
     * As we build the Protocol Home page further; the required fields will increase;
     * So we have to add more fields to this method as and when the fields are added 
     * on Protocol Home page.
     * This method checks document fields against the passed in values
     * @param doc the document to check values against
     * @param description to check
     * @throws WorkflowException
     */
    protected void verifySavedRequiredFields(ProtocolDocument doc, String description) throws WorkflowException {
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
