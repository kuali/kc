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

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 *  Web Test class for testing the Key Personnel Tab of the <code>{@link ProposalDevelopmentDocument}</code>
 *  @author $Author: lprzybyl $
 *  @version $Revision: 1.5 $
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
    }

    /**
     * Test adding a Key Person
     */
    @Test
    public void addKeyPerson() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.Person");
        assertEquals("Terry Durkin", getFieldValue(keyPersonnelPage, "newProposalPerson.fullName"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "KP");

        clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

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
