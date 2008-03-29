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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import static org.kuali.kra.logging.FormattedLogger.*;

/**
 *  Web Test class for testing the Key Personnel Tab of the <code>{@link ProposalDevelopmentDocument}</code>
 *  @author $Author: lprzybyl $
 *  @version $Revision: 1.10 $
 */
public class KeyPersonnelWebTest extends ProposalDevelopmentWebTestBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeyPersonnelWebTest.class);
    private static final String KEY_PERSONNEL_IMAGE_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.keyPersonnel.x";
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";

    private HtmlPage keyPersonnelPage;


    /**
     * @see org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentWebTestBase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setDefaultRequiredFields(getProposalDevelopmentPage());
        setKeyPersonnelPage(clickOn(getProposalDevelopmentPage(), KEY_PERSONNEL_IMAGE_NAME));
        warn("");
        error("");
        fatal("");
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Assign key personnel page
     *
     * @param keyPersonnelPage <code>{@link HtmlPage}</code> instance of key personnel page
     */
    public void setKeyPersonnelPage(HtmlPage keyPersonnelPage) {
        this.keyPersonnelPage = keyPersonnelPage;
    }

    /**
     * Generate a Key Personnel Page by going to the clicking on the link from the proposal page.
     *
     * @return <code>{@link HtmlPage}</code> instance of the key Personnel Page
     * @see #getProposalDevelopmentPage()
     */
    public HtmlPage getKeyPersonnelPage() {
        return keyPersonnelPage;
    }

    /**
     * @see org.kuali.kra.KraWebTestBase#getDefaultDocumentPage()
     */
    protected HtmlPage getDefaultDocumentPage() {
        return getKeyPersonnelPage();
    }

    // //////////////////////////////////////////////////////////////////////
    // Test Methods                                                                  //
    // //////////////////////////////////////////////////////////////////////

    /**
     * Test adding a principal investigator
     */
    @Test
    public void addPrincipalInvestigator() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.Person");
        assertEquals("Terry Durkin", getFieldValue(keyPersonnelPage, "newProposalPerson.fullName"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "PI");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        saveAndSearchDoc(keyPersonnelPage);
    }

    @Test
    public void clearInvestigator() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.Person");
        assertEquals("Terry Durkin", getFieldValue(keyPersonnelPage, "newProposalPerson.fullName"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "PI");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.clearProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        assertEquals(getFieldValue(keyPersonnelPage, "newRolodexId"), "");
        assertEquals(getFieldValue(keyPersonnelPage, "newPersonId"), "");
        saveAndSearchDoc(keyPersonnelPage);
    }
    @Test
    public void changeRole() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.Person");
        assertEquals("Terry Durkin", getFieldValue(keyPersonnelPage, "newProposalPerson.fullName"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "PI");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        keyPersonnelPage = saveDoc(keyPersonnelPage);
        setFieldValue(keyPersonnelPage,"document.proposalPerson[0].proposalPersonRoleId", "COI");
        saveAndSearchDoc(keyPersonnelPage);        
    }
    
    @Test
    public void addDegree() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.Person");
        assertEquals("Terry Durkin", getFieldValue(keyPersonnelPage, "newProposalPerson.fullName"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "PI");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        keyPersonnelPage = saveDoc(keyPersonnelPage);
        setFieldValue(keyPersonnelPage,"newProposalPersonDegree[0].degreeCode", "AS");
        setFieldValue(keyPersonnelPage,"newProposalPersonDegree[0].degree", "Sega");
        setFieldValue(keyPersonnelPage,"newProposalPersonDegree[0].graduationYear", "2000");
        setFieldValue(keyPersonnelPage,"newProposalPersonDegree[0].school", "Monroe");
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertDegree", true));
        saveAndSearchDoc(keyPersonnelPage);        
    }

    @Test
    public void removeDegree() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.Person");
        assertEquals("Terry Durkin", getFieldValue(keyPersonnelPage, "newProposalPerson.fullName"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "PI");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        keyPersonnelPage = saveDoc(keyPersonnelPage);
        setFieldValue(keyPersonnelPage,"newProposalPersonDegree[0].degreeCode", "AS");
        setFieldValue(keyPersonnelPage,"newProposalPersonDegree[0].degree", "Sega");
        setFieldValue(keyPersonnelPage,"newProposalPersonDegree[0].graduationYear", "2000");
        setFieldValue(keyPersonnelPage,"newProposalPersonDegree[0].school", "Monroe");
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertDegree", true));
        keyPersonnelPage = saveDoc(keyPersonnelPage);
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.deleteDegree.document.proposalPerson[0].line0", true));
        saveAndSearchDoc(keyPersonnelPage);        
    }
    /**
     * Test adding a principal investigator
     */
    @Test
    public void addPrincipalInvestigator_Rolodex() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.NonOrganizationalRolodex");
        assertEquals("First Name Middle Name Last Name", getFieldValue(keyPersonnelPage, "newProposalPerson.fullName"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "PI");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        saveAndSearchDoc(keyPersonnelPage);
    }

    /**
     * Test adding a Key Person
     */
    @Test
    public void addKeyPerson() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.Person");
        assertEquals("Terry Durkin", getFieldValue(keyPersonnelPage, "newProposalPerson.fullName"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "KP");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));

        saveAndSearchDoc(keyPersonnelPage);
    }

    /**
     * @see org.kuali.kra.KraWebTestBase#testHelpLinks()
     */
    @Test
    public void testHelpLinks() throws Exception {
        super.testHelpLinks();
    }
}
