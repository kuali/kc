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
package org.kuali.kra.test.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.kuali.kra.test.infrastructure.lifecycle.KcUnitTestSeleniumLifecycle;
import org.kuali.rice.test.web.HtmlUnitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@RunWith(KcSeleniumTestRunner.class)
public class KcSeleniumTestBase extends KcUnitTestBase {
    
    protected static final String QUICKSTART_USER = "quickstart";
    
    protected static WebDriver driver;
    
    private static KcUnitTestSeleniumLifecycle LIFECYCLE = new KcUnitTestSeleniumLifecycle();
    private static RunListener RUN_LISTENER = new KcUnitTestRunListener(LIFECYCLE);
    
    private static final String BROWSER_PROTOCOL = "http";
    private static final String BROWSER_ADDRESS = "127.0.0.1";
    private static final String PORTAL_ADDRESS = "kc-dev/portal.jsp";
    private static final String TIMEOUT = "60000";
    
    private static final String RESEARCHER_TAB_TITLE = "Researcher";
    private static final String UNIT_TAB_TITLE = "Unit";
    private static final String CENTRAL_ADMIN_TAB_TITLE = "Central Admin";
    private static final String MAINTENANCE_TAB_TITLE = "Maintenance";
    private static final String SYSTEM_ADMIN_TAB_TITLE = "System Admin";
    
    private static final String HELP_PAGE_TITLE = "Kuali Research Administration Online Help";
    
    private static final String DOT = ".";
    private static final String METHOD_TO_CALL_PREFIX = "methodToCall";
    private static final String SAVE_BUTTON = METHOD_TO_CALL_PREFIX + DOT + "save";
    private static final String RELOAD_BUTTON = METHOD_TO_CALL_PREFIX + DOT + "reload";
    private static final String ROUTE_BUTTON = METHOD_TO_CALL_PREFIX + DOT + "route";
    private static final String CLOSE_BUTTON = METHOD_TO_CALL_PREFIX + DOT + "close";
    
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    private static final String SAVE_SUCCESS_MESSAGE = "Document was successfully saved";
    
    @BeforeClass
    public static final void seleniumBeforeClass() {
        if (!LIFECYCLE.isPerSuiteStarted()) {
            LIFECYCLE.startPerSuite();
            driver = LIFECYCLE.getWebDriver();
        }
        LIFECYCLE.startPerClass();
    }
    
    @AfterClass
    public static final void seleniumAfterClass() {
        LIFECYCLE.stopPerClass();
    }
    
    @Before
    public void seleniumBeforeTest() throws Exception {
        LIFECYCLE.startPerTest(transactional);
    }
    
    @After
    public void seleniumAfterTest() throws Exception {
        LIFECYCLE.stopPerTest();
    }
    
    /**
     * This method returns the <code>RunListener</code> needed to ensure the KC persistent lifecycles shut down properly
     * @return the RunListener responsible for shutting down all KC persistent lifecycles
     */
    public static RunListener getRunListener() {
        return RUN_LISTENER;
    }
    
    /**
     * Returns the timeout value to wait for a page to load.  May be overridden by subclasses.
     * @return the timeout value for page loads
     */
    protected String getTimeoutValue() {
        return TIMEOUT;
    }
    
    /**
     * Checks for the Login web page and if it exists, logs the user in.
     */
    protected void login() {
        login(QUICKSTART_USER);
    }
    
    /**
     * Checks for the Login web page and if it exists, logs the user in.
     * 
     * @param username the user's id
     */
    protected void login(String username) {
        if (StringUtils.equals(driver.getTitle(), "Login")) {
            set("__login_user", username);
            click("//input[@value='Login']");
        }
    }
    
    /**
     * Checks for the Logout button and if it exists, logs the current user out.
     */
    protected void logout() {
        click("imageField");
    }
    
    /**
     * Clicks the Researcher tab.
     */
    protected final void clickResearcherTab() {
        openPortalPage();
        
        click(RESEARCHER_TAB_TITLE);
    }
    
    /**
     * Clicks the Unit tab.
     */
    protected final void clickUnitTab() {
        openPortalPage();
        
        click(UNIT_TAB_TITLE);
    }
    
    /**
     * Clicks the Central Admin tab.
     */
    protected final void clickCentralAdminTab() {
        openPortalPage();
        
        click(CENTRAL_ADMIN_TAB_TITLE);
    }
    
    /**
     * Clicks the Maintenance tab.
     */
    protected final void clickMaintenanceTab() {
        openPortalPage();
        
        click(MAINTENANCE_TAB_TITLE);
    }
    
    /**
     * Click the System Admin tab.
     */
    protected final void clickSystemAdminTab() {
        openPortalPage();
        
        click(SYSTEM_ADMIN_TAB_TITLE);
    }
    
    /**
     * Click the Expand All button.
     */
    protected final void clickExpandAll() {
        click("methodToCall.showAllTabs");
    }
    
    /**
     * Click the Collapse All button.
     */
    protected final void clickCollapseAll() {
        click("methodToCall.hideAllTabs");
    }

    /**
     * Gets the value of a control field.
     * <p>
     * The test will fail for any of the following reasons:
     * <ul>
     * <li>The HTML control element was not found</li>
     * </ul>
     *
     * @param locator the XPath location of the HTML element to click on
     * @return the value of the HTML element
     */
    protected final String get(String locator) {
        assertElementExists(locator);
        return getElement(locator).getValue();
    }
    
    /**
     * Sets the value of a control field.
     * <p>
     * The test will fail for any of the following reasons:
     * <ul>
     * <li>The HTML control element was not found</li>
     * </ul>
     *
     * @param locator the XPath location of the HTML element to click on
     * @param value the value to set the HTML element to
     */
    protected final void set(String locator, String value) {
        assertElementExists(locator);
        WebElement element = getElement(locator);
        String elementType = element.getAttribute("type");
        
        if (StringUtils.equals(elementType, "radio")) {
            List<WebElement> radios = getElementsByName(locator);
            for (WebElement radio : radios) {
                String radioValue = radio.getValue();
                if (StringUtils.equals(radioValue, value)) {
                    radio.click();
                    break;
                }
            }
        } else {
            element.clear();
            element.sendKeys(value);
        }
        
    }

    /**
     * Clicks on an HTML element in the web page indicated by the <code>locator</code>.  A locator is either:
     * <ol>
     * <li>An exact value matching an <code>id</code> attribute</li>
     * <li>A value that is contained in a <code>name</code> attribute</li>
     * <li>A value that is contained in a <code>title</code> attribute</li>
     * </ol>
     * <p>
     * Using any of the <code>click()</code> methods is the preferred way to click on an HTML element due to the login process.  If the login web page is 
     * encountered, the user will be automatically logged in and the given button will be clicked.
     *
     * @param locator the exact id, partial name, or partial title of the HTML element to click on
     */
    protected final void click(String locator) {
        click(locator, null);
    }
    
    /**
     * Clicks on an HTML element in the web page indicated by the <code>locator</code>.  A locator is either:
     * <ol>
     * <li>An exact value matching an <code>id</code> attribute</li>
     * <li>A value that is contained in a <code>name</code> attribute</li>
     * <li>A value that is contained in a <code>title</code> attribute</li>
     * </ol>
     * <p>
     * Using any of the <code>click()</code> methods is the preferred way to click on an HTML element due to the login process.  If the login web page is 
     * encountered, the user will be automatically logged in and the given button will be clicked.
     * <p>
     * If the <code>nextPageTitle</code> is not null, the test case will fail if the next web page doesn't have the expected title.
     *
     * @param locator the exact id, partial name, or partial title of the HTML element to click on
     * @param nextPageTitle the expected title of the new web page (may be null)
     */
    protected final void click(String locator, String nextPageTitle) {
        assertElementExists(locator);
        getElement(locator).click();
        
        login();
        
        if (nextPageTitle != null) {
            assertPageContains(nextPageTitle);
        }
    }
    
    /**
     * Gets the document number from a document's web page.  It is expected to be in an HTML table in a table labeled "headerarea".
     *
     * @return the document's number
     */
    protected final String getDocumentNumber() {
        String locator = "//div[@id='headerarea']/div/table/tbody/tr[1]/td[1]";
        
        assertNotNull(locator + " not found", getElementByXPath(locator));
        return getElementByXPath(locator).getText();
    }
    
    /**
     * Do a document search looking for the a specific document based upon its document number.  The following occurs on a Document Search:
     * <ol>
     * <li>The Portal Page is opened</li>
     * <li>The Doc Search button is clicked on</li>
     * <li>In the Doc Search web page, the document number is filled in with the given value</li>
     * <li>The first item in the results is returned</li>
     * <li>The document number link is clicked on</li>
     * </ol>
     * <p>
     * The test will fail for any of the following reasons:
     * <ul>
     * <li>The Doc Search button was not found</li>
     * <li>There was no data returned in the search</li>
     * </ul>
     *
     * @param documentNumber the document number to search for
     */
    protected final void docSearch(String documentNumber) {
        openPortalPage();
        
        click("Document Search");
        
        set("routeHeaderId", documentNumber);
        
        click("methodToCall.search");
        
        click(documentNumber);
    }
    
    /**
     * Performs a single value Lookup.  The following occurs on a Lookup:
     * <ol>
     * <li>The Lookup button is clicked on</li>
     * <li>In the Lookup web page, the search button is clicked on</li>
     * <li>The first item in the results is returned</li>
     * <li>The web page resulting from clicking on "Return Value" is returned</li>
     * </ol>
     * To find the Lookup button, the name attribute of all Lookup buttons are examined to see if it contains the given <code>tag</code>.  Make sure to pick 
     * a <code>tag</code> that is unique for that Lookup.
     * <p>
     * The test will fail for any of the following reasons:
     * <ul>
     * <li>The Lookup button was not found</li>
     * <li>There was no data returned in the search</li>
     * </ul>
     *
     * @param tag identifies the Lookup button to click on.
     */
    protected final void lookup(String tag) {
       lookup(tag, null, null);
    }

    /**
     * Performs a single value Lookup.  The following occurs on a Lookup:
     * <ol>
     * <li>The Lookup button is clicked on</li>
     * <li>In the Lookup web page, the given field is filled in with the given value</li>
     * <li>In the Lookup web page, the search button is clicked on</li>
     * <li>The first item in the results is returned</li>
     * </ol>
     * To find the Lookup button, the name attribute of all Lookup buttons are examined to see if it contains the given <code>tag</code>.  Make sure to pick 
     * a <code>tag</code> that is unique for that Lookup.
     * <p>
     * The test will fail for any of the following reasons:
     * <ul>
     * <li>The Lookup button was not found</li>
     * <li>There was no data returned in the search</li>
     * </ul>
     *
     * @param tag identifies the Lookup button to click on.
     * @param searchFieldId the id of the search field (may be null).
     * @param searchFieldValue the value to insert into the search field (may be null if id is null).
     */
    protected final void lookup(String tag, String searchFieldId, String searchFieldValue) {    
        clickLookup(tag);

        if (searchFieldId != null) {
            assertTrue("searchValue is null", searchFieldValue != null);
            set(searchFieldId, searchFieldValue);
        }

        click("methodToCall.search");

        assertTableCellValue("row", 0, 0, "return value");
        
        click("return value");
    }

    /**
     * Performs a multiple value Lookup.  The following occurs on a Lookup:
     * <ol>
     * <li>The Lookup icon is clicked on</li>
     * <li>In the Lookup web page, the search button is clicked on</li>
     * <li>The "Select All from All Pages" button is clicked on</li>
     * <li>The web page resulting from clicking on "Return Selected" is returned</li>
     * </ol>
     * To find the Lookup button, the name attribute of all Lookup buttons are examined to see if it contains the given <code>tag</code>.  Make sure to pick 
     * a <code>tag</code> that is unique for that Lookup.
     * <p>
     * The test will fail for any of the following reasons:
     * <ul>
     * <li>The Lookup button was not found</li>
     * <li>There was no data returned in the search</li>
     * </ul>
     *
     * @param tag identifies the Lookup button to click on.
     */
    protected final void multiLookup(String tag) {
        multiLookup(tag, null, null);
    }

    /**
     * Performs a multiple value Lookup.  The following occurs on a Lookup:
     * <ol>
     * <li>The Lookup icon is clicked on</li>
     * <li>The search field is filled in with the given search value.</li>
     * <li>In the Lookup web page, the search button is clicked on</li>
     * <li>The "Select All from All Pages" button is clicked on</li>
     * <li>The web page resulting from clicking on "Return Selected" is returned</li>
     * </ol>
     * To find the Lookup button, the name attribute of all Lookup buttons are examined to see if it contains the given <code>tag</code>.  Make sure to pick 
     * a <code>tag</code> that is unique for that Lookup.
     * <p>
     * The test will fail for any of the following reasons:
     * <ul>
     * <li>The Lookup button was not found</li>
     * <li>There was no data returned in the search</li>
     * </ul>
     *
     * @param tag identifies the Lookup button to click on.
     * @param searchFieldId the id of the search field (may be null).
     * @param searchFieldValue the value to insert into the search field (may be null if id is null).
     */
    protected void multiLookup(String tag, String searchFieldId, String searchFieldValue) {
        clickLookup(tag);

        if (searchFieldId != null) {
            assertTrue("searchValue is null", searchFieldValue != null);
            set(searchFieldId, searchFieldValue);
        }

        click("methodToCall.search");
        
        assertTableCellValue("row", 0, 1, searchFieldValue);
        
        click("methodToCall.selectAll");

        click("methodToCall.prepareToReturnSelectedResults");
    }
    
    /**
     * Gets a Lookup HTML element.  The searching for Lookup HTML elements
     * is a special case.  This is because it lacks an id attribute and because
     * the value of its name attribute is extremely long and cryptic. To find
     * a Lookup element, the value of the name attribute is examined to see if
     * it contains the given id.  Lookup HTML elements always contain some data
     * in the name attribute that is specific to the Lookup.
     *
     * @param element the parent HTML element to search.
     * @param tag the tag to compare against Lookup HTML name attributes.
     * @return the Lookup's HTML element or null if not found.
     */
    private final void clickLookup(String tag) {
        final String locator = "//input[starts-with(@name,'methodToCall.performLookup')and contains(@name,'" + tag + "')]";

        assertNotNull(locator + " not found", getElementByXPath(locator));
        getElementByXPath(locator).click();
        
        login();
    }
    
    /**
     * Reload a document by clicking on the Reload button.
     */
    protected final void reloadDocument() {
        click(RELOAD_BUTTON);
    }

    /**
     * Save a document by clicking on the Save button.
     */
    protected final void saveDocument() {
        click(SAVE_BUTTON);
    }
    
    /**
     * Closes a document without saving.
     */
    protected final void closeDocument() {
        closeDocument(false);
    }

    /**
     * Closes a document, optionally saving if <code>save</code> is set to true.
     *
     * @param save whether or not the document should be saved before closing
     */
    protected final void closeDocument(boolean save) {
        if (save) {
            saveDocument();
        }
        
        click(CLOSE_BUTTON);
        
        if (getElement("methodToCall.processAnswer.button1") != null) {
            click("methodToCall.processAnswer.button1");
        }
    }

    /**
     * Saves and closes document and then performs a document search to retrieve the document.
     */
    protected final void closeAndSearchDocument() {
        String documentNumber = getDocumentNumber();
        
        closeDocument(true);

        docSearch(documentNumber);
    }
    
    /**
     * Routes the document.
     */
    protected final void routeDocument() {
        click(ROUTE_BUTTON);
    }
    
    /**
     * Asserts that the document has been saved with no errors.
     */
    protected final void assertSave() {
        assertPageDoesNotContain(ERRORS_FOUND_ON_PAGE);
        assertPageContains(SAVE_SUCCESS_MESSAGE);
    }
    
    /**
     * Asserts that <code>locator</code> exists on the page.
     * 
     * @param locator the XPath location of the HTML element to verify
     */
    protected final void assertElementExists(String locator) {
        assertNotNull(locator + " not found", getElement(locator));
    }
    
    /**
     * Asserts that <code>locator</code> does <b>not</b> exist on the page.
     * 
     * @param locator the XPath location of the HTML element to verify
     */
    protected final void assertElementDoesNotExist(String locator) {
        assertNull(locator + " found", getElement(locator));
    }
    
    
    /**
     * Asserts that the value of the element indicated by <code>locator</code> contains <code>value</code>.
     * 
     * @param locator the XPath location of the HTML element to verify
     * @param value the value to look for in the element.
     */
    protected final void assertElementContains(String locator, String value) {
        clickExpandAll();

        assertElementExists(locator);
        WebElement element = getElement(locator);
        
        assertTrue("Element " + locator + " does not contain " + value, StringUtils.contains(element.getValue(), value)); 
    }
    
    /**
     * Asserts that the web page does <b>not</b> contain <code>text</code>.
     * 
     * @param locator the XPath location of the HTML element to verify
     * @param value the value to look for in the element.
     */
    protected final void assertElementDoesNotContain(String locator, String value) {
        clickExpandAll();
        
        assertElementExists(locator);
        WebElement element = getElement(locator);

        assertFalse("Element " + locator + " contains " + value, StringUtils.contains(element.getValue(), value)); 
    }

    /**
     * Asserts that the web page contains <code>text</code>.
     * 
     * @param text the string to look for in the web page.
     */
    protected final void assertPageContains(String text) {
        clickExpandAll();

        assertNotNull("Page does not contain " + text, getElementByText(text)); 
    }
    
    /**
     * Asserts that the web page does <b>not</b> contain <code>text</code>.
     * 
     * @param text the string to look for in the web page.
     */
    protected final void assertPageDoesNotContain(String text) {
        clickExpandAll();

        assertNull("Page contains " + text, getElementByText(text)); 
    }
    
    protected final void assertTitleContains(String title) {
        String pageSource = driver.getPageSource();
        
        if (!StringUtils.contains(pageSource, title)) {
            if (switchToIFramePortlet()) {
                pageSource = driver.getPageSource();
            }
        }

        assertTrue("Page does not contain " + title, StringUtils.contains(pageSource, title));
    }
    
    protected final void assertTitleDoesNotContain(String title) {
        String pageSource = driver.getPageSource();
        
        if (StringUtils.contains(pageSource, title)) {
            if (switchToIFramePortlet()) {
                pageSource = driver.getPageSource();
            }
        }

        assertTrue("Page contains" + title, !StringUtils.contains(pageSource, title));
    }
    
    /**
     * Assert that the list of options contains <code>text</code>.
     *
     * @param locator the XPath location of the select options
     * @param text the string to look for in the select options
     */
    protected final void assertSelectOptionsContains(String locator, String text) {
        WebElement element = getElement(locator);
        List<WebElement> options = element.findElements(By.tagName("option"));
        
        List<String> selectedValues = new ArrayList<String>();
        for (WebElement option : options) {
            if (option.isSelected()) {
                selectedValues.add(option.getText());
            }
        }
        
        assertTrue("Select options do not contain " + text, selectedValues.contains(text));
    }
    
    /**
     * Asserts that the Expanded Text Area is providing a popup window in which to change its value.  Verifies that the that this is working properly by 
     * performing the following:
     * <ol>
     * <li>The text area is set to the <code>originalText</code> value</li>
     * <li>The pencil button is clicked on, opening in a popup window</li>
     * <li>The text in the popup window is examined to verify that it is equal to <code>originalText</code></li>
     * <li>The popup window text area is changed to <code>expandedAreaText</code></li>
     * <li>The "Continue" button is clicked on, closing the popup window</li>
     * <li>The resulting web page is examined to verify that the text area has changed to the value of <code>expandedAreaText</code></li>
     * </ol>
     * <p>
     * The test will fail for any of the following reasons:
     * <ul>
     * <li>The text area element was not found</li>
     * <li>The pencil button was not found</li>
     * <li>The popup did not appear</li>
     * <li>The setting of <code>originalText</code> did not transfer to the popup window text area</li>
     * <li>The saving of <code>expandedAreaText</code> did not transfer to the text area</li>
     * </ul>
     *
     * @param textAreaId identifies the text area
     * @param originalText the string to set the original text area to
     * @param expandedAreaText the string to set in the popup window text area
     */
    protected final void assertExpandedTextArea(String textAreaId, String originalText, String expandedAreaText) {
        set(textAreaId, originalText);

        click("//input[starts-with(@name,'methodToCall.updateTextArea') and contains(@name, '" + textAreaId + "')]");
        
        driver.switchTo().window("null");
        
        assertEquals(originalText, get(textAreaId));

        set(textAreaId, expandedAreaText);
        
        click("methodToCall.postTextAreaToParent.anchor");

        assertEquals(expandedAreaText, get(textAreaId));
    }
    
    /**
     * Asserts that all of the Help hyperlinks on a web page (identified by the <code>helpWindow</code> target) are bringing up a page with the appropriate
     * Help Page title.
     */
    protected final void assertHelpLinks() {
        List<WebElement> helpLinks = driver.findElements(By.xpath("//node()[@target='helpWindow']"));
        for (WebElement helpLink : helpLinks) {
            helpLink.click();
            assertTitleContains(HELP_PAGE_TITLE);
        }
    }
    
    /**
     * Asserts that the text in the table identified by <code>id</code> at row <code>row</code> and column <code>column</code> matches the given 
     * <code>text</code>.
     *
     * @param id identifies the table to search
     * @param row the 0-valued row number to search
     * @param column the 0-valued column number to search
     * @param text the text to verify
     */
    protected final void assertTableCellValue(String id, int row, int column, String text) {
        String rowString = String.valueOf(row + 1);
        String columnString = String.valueOf(column + 1);
        
        WebElement cell = null;
        
        try {
            cell = driver.findElement(By.xpath("//table[@id='" + id + "']//tr[" + rowString + "]/td[" + columnString + "]"));
        
            assertEquals(text + " not found for table " + id + " at row " + rowString + " and column " + columnString, text, cell.getText());
        } catch (NoSuchElementException nsee) {
            assertNotNull("Cannot find cell for table " + id + " at row " + rowString + " and column " + columnString, cell);
        }
    }
    
    /**
     * Assert that the page contains one or more errors.
     */
    protected final void assertPageErrors() {
        clickExpandAll();
        
        assertTrue("Page does not contain errors", getElementByText("error(s) found on page") != null || getElementByText("Errors Found in Document") != null 
                || getElementByText("Kuali :: Incident Report") != null);
    }
    
    /**
     * Assert that the page contains no errors.
     */
    protected final void assertNoPageErrors() {
        clickExpandAll();
        
        assertFalse("Page contains errors", getElementByText("error(s) found on page") != null || getElementByText("Errors Found in Document") != null
                || getElementByText("Kuali :: Incident Report") != null);
    }
    
    /**
     * Determines if any of the errors contains the given text string.
     *
     * @param errors the list of errors.
     * @param text the string to compare against.
     * @return true if any of errors contains the text string; otherwise false.
     */
    protected final boolean assertError(String panelId, String text) {
        boolean errorMatches = false;
        
        List<WebElement> errors = getErrors(panelId);
        for (WebElement error : errors) {
            if (StringUtils.contains(error.getValue(), text)) {
                errorMatches = true;
                break;
            }
        }
        
        return errorMatches;
    }

    /**
     * Gets the error messages for a specific panel.  If an operation
     * results in an error, those errors are displayed in specific panel,
     * i.e. tab.  Each panel is contained within an HTML div tag that has
     * a unique id, i.e. the HTML id attribute.
     *
     * @param page the HTML web page.
     * @param panelId the unique id of the panel.
     * @return the list of error strings (may be empty).
     */
    protected final void assertErrors(String panelId, String expectedErrorCount) {
        clickExpandAll();
        
        List<WebElement> errors = getErrors(panelId);
        
        assertEquals("Expected error count did not match actual error count", expectedErrorCount, errors.size());
    }
    
    /**
     * Opens the Portal Web Page. The portal page is the starting point for many web tests in order to simulate a user.
     */
    private final void openPortalPage() {
        driver.get(BROWSER_PROTOCOL + "://" + BROWSER_ADDRESS + ":" + HtmlUnitUtil.getPort().toString() + "/" + PORTAL_ADDRESS);
    }
    
    /**
     * Gets an HTML element in the web page.  To find the HTML element, the following algorithm is used:
     * <ol>
     * <li>Search for a HTML element with an <b>id</b> attribute that matches the given id.</li>
     * <li>If not found, search for the first HTML element with a <b>name</b> attribute that matches.</li>
     * <li>If not found, search for the first HTML element with a <b>title</b> attribute that matches.</li>
     * </ol>
     *
     * @param id the id of the HTML attribute.
     * @return the HTML element or null if not found.
     */
    private final WebElement getElement(String locator) {
        WebElement element = getElementById(locator);
        
        if (element == null) {
            element = getElementByName(locator);
            if (element == null) {
                element = getElementByTitle(locator);
                if (element == null) {
                    element = getElementByLinkText(locator);
                }
            }
        }
        
        return element;
    }
    
    /**
     * Gets an HTML element in the web page.  Searches the web page for
     * an HTML element whose id attribute matches the given id.
     *
     * HTML web pages may contain Inline Frames (iframes) which are not expanded
     * within HtmlUnit.  The inline frames contain inner web pages that must
     * also be searched.
     *
     * @param page the HTML web page to search.
     * @param id the id to search for.
     * @return the HTML element or null if not found.
     */
    private final WebElement getElementById(String id) {
        WebElement element = null;
        
        List<WebElement> elements = getElementsById(id);
        if (!elements.isEmpty()) {
            element = elements.get(0);
        }
        
        return element;
    }

    /**
     * Gets an HTML element in a parent HTML element.  Searches the parent HTML
     * element for the first HTML element whose name attribute matches the given name.
     *
     * @param element the parent HTML element to search.
     * @param name the name to search for.
     * @param startsWith if true, only match against the start of the name.
     * @return the HTML element or null if not found.
     */
    private final WebElement getElementByName(String name) {
        WebElement element = null;
        
        List<WebElement> elements = getElementsByName(name);
        if (!elements.isEmpty()) {
            element = elements.get(0);
        }
        
        return element;
    }
    
    /**
     * Gets an HTML element in a parent HTML element.  Searches the parent HTML
     * element for the first HTML element whose title attribute matches the given title.
     *
     * @param element the parent HTML element to search.
     * @param title the title to search for.
     * @return the HTML element or null if not found.
     */
    private final WebElement getElementByTitle(String title) {
        WebElement element = null;
        
        List<WebElement> elements = getElementsByTitle(title);
        if (!elements.isEmpty()) {
            element = elements.get(0);
        }
        
        return element;
    }
    
    private final WebElement getElementByLinkText(String linkText) {
        WebElement element = null;
        
        List<WebElement> elements = getElementsByLinkText(linkText);
        if (!elements.isEmpty()) {
            element = elements.get(0);
        }
        
        return element;
    }
    
    private final WebElement getElementByXPath(String xPath) {
        WebElement element = null;
        
        List<WebElement> elements = getElementsByXPath(xPath);
        if (!elements.isEmpty()) {
            element = elements.get(0);
        }
        
        return element;
    }
    
    private final WebElement getElementByText(String text) {
        WebElement element = null;
        
        List<WebElement> elements = getElementsByText(text);
        if (!elements.isEmpty()) {
            element = elements.get(0);
        }
        
        return element;
    }
    
    private final List<WebElement> getElementsById(String id) {
        List<WebElement> elements = getActiveElementsById(id);
        
        if (switchToIFramePortlet()) {
            elements.addAll(getActiveElementsById(id));
        }
        
        return elements;
    }
    
    private final List<WebElement> getActiveElementsById(String id) {
        List<WebElement> activeElements = new ArrayList<WebElement>();

        List<WebElement> elements = driver.findElements(By.id(id));
        for (WebElement element : elements) {
            if (((RenderedWebElement) element).isDisplayed()) {
                activeElements.add(element);
            }
        }
        
        return activeElements;
    }
    
    private final List<WebElement> getElementsByName(String name) {
        List<WebElement> elements = getActiveElementsByName(name);
        
        if (switchToIFramePortlet()) {
            elements.addAll(getActiveElementsByName(name));
        }
        
        return elements;
    }
    
    private final List<WebElement> getActiveElementsByName(String name) {
        List<WebElement> activeElements = new ArrayList<WebElement>();

        List<WebElement> elements = driver.findElements(By.xpath(getAttributeContainsXPath("name", name)));
        for (WebElement element : elements) {
            if (((RenderedWebElement) element).isDisplayed()) {
                activeElements.add(element);
            }
        }
        
        return activeElements;
    }
    
    private final List<WebElement> getElementsByTitle(String title) {
        List<WebElement> elements = getActiveElementsByTitle(title);
        
        if (switchToIFramePortlet()) {
            elements.addAll(getActiveElementsByTitle(title));
        }
        
        return elements;
    }
    
    private final List<WebElement> getActiveElementsByTitle(String title) {
        List<WebElement> activeElements = new ArrayList<WebElement>();

        List<WebElement> elements = driver.findElements(By.xpath(getAttributeContainsXPath("title", title)));
        for (WebElement element : elements) {
            if (((RenderedWebElement) element).isDisplayed()) {
                activeElements.add(element);
            }
        }
        
        return activeElements;
    }
    
    private final List<WebElement> getElementsByLinkText(String linkText) {
        List<WebElement> elements = getActiveElementsByLinkText(linkText);
        
        if (switchToIFramePortlet()) {
            elements.addAll(getActiveElementsByLinkText(linkText));
        }
        
        return elements;
    }
    
    private final List<WebElement> getActiveElementsByLinkText(String linkText) {
        List<WebElement> activeElements = new ArrayList<WebElement>();

        List<WebElement> elements = driver.findElements(By.partialLinkText(linkText));
        for (WebElement element : elements) {
            if (((RenderedWebElement) element).isDisplayed()) {
                activeElements.add(element);
            }
        }
        
        return activeElements;
    }
    
    private final List<WebElement> getElementsByXPath(String xPath) {
        List<WebElement> elements = getActiveElementsByXPath(xPath);
        
        if (switchToIFramePortlet()) {
            elements.addAll(getActiveElementsByXPath(xPath));
        }
        
        return elements;
    }
    
    private final List<WebElement> getActiveElementsByXPath(String xPath) {
        List<WebElement> activeElements = new ArrayList<WebElement>();

        List<WebElement> elements = driver.findElements(By.xpath(xPath));
        for (WebElement element : elements) {
            if (((RenderedWebElement) element).isDisplayed()) {
                activeElements.add(element);
            }
        }
        
        return activeElements;
    }
    
    private final List<WebElement> getElementsByText(String text) {
        List<WebElement> elements = getActiveElementsByText(text);
        
        if (switchToIFramePortlet()) {
            elements.addAll(getActiveElementsByText(text));
        }
        
        return elements;
    }
    
    private final List<WebElement> getActiveElementsByText(String text) {
        List<WebElement> activeElements = new ArrayList<WebElement>();

        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(), '" + text + "')]"));
        for (WebElement element : elements) {
            if (((RenderedWebElement) element).isDisplayed()) {
                activeElements.add(element);
            }
        }
        
        return activeElements;
    }
    
    private boolean switchToIFramePortlet() {
        boolean switchToIFramePortletSuccessful = true;
        
        try {
            driver.switchTo().frame("iframeportlet");
        } catch (Exception e) {
            switchToIFramePortletSuccessful = false;
        }
        
        return switchToIFramePortletSuccessful;
    }
    
    private String getAttributeContainsXPath(String attribute, String locator) {
        return "//*[contains(@" + attribute + ", '" + locator + "')]";
    }
    
    private List<WebElement> getErrors(String panelId) {
        return driver.findElements(By.xpath("//div[@id='" + panelId + "']//div[@style='display: list-item; margin-left: 20px;']"));
    }

}