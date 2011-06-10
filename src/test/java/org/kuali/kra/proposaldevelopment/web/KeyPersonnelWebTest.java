/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.web;

import static org.kuali.kra.logging.BufferedLogger.debug;
import static org.kuali.kra.logging.BufferedLogger.error;
import static org.kuali.kra.logging.BufferedLogger.fatal;
import static org.kuali.kra.logging.BufferedLogger.warn;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

/**
 *  Web Test class for testing the Key Personnel Tab of the <code>{@link ProposalDevelopmentDocument}</code>
 *  @author $Author: gmcgrego $
 *  @version $Revision: 1.18 $
 */
@PerSuiteUnitTestData(
        @UnitTestData(
            sqlFiles = {
                 @UnitTestFile(filename = "classpath:sql/dml/load_NIH_TEST_HIERARCHY.sql", delimiter = ";")
            }
        )
)

public class KeyPersonnelWebTest extends ProposalDevelopmentWebTestBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeyPersonnelWebTest.class);
    private static final String KEY_PERSONNEL_IMAGE_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.keyPersonnel";
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";

    protected HtmlPage keyPersonnelPage;
    private boolean javaScriptEnabled;
    
    private static final String NIH_HIERARCHY_SPONSOR = "004777";
    private static final String NON_NIH_HIERARCHY_SPONSOR = "004210";
    private static final String PI_NIH_LABEL = "PI/Contact";
    private static final String PI_NON_NIH_LABEL = "Principal Investigator";
    private static final String PERSON_ID= "10000000004";

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
        javaScriptEnabled = webClient.isJavaScriptEnabled();
        webClient.setJavaScriptEnabled(false);
    }
    
    @After
    public void tearDown() throws Exception {
        webClient.setJavaScriptEnabled(javaScriptEnabled);
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
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.KcPerson", "personId", PERSON_ID);
        //commented the following line out because you cannot check for the text on this page the following way
        // the name does not appear in the source. The table that contains the name is populated using
        // Javascript
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("Nicholas Majors"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "PI");
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        saveAndSearchDoc(keyPersonnelPage);
    }

    @Test
    public void clearInvestigator() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.KcPerson", "personId", PERSON_ID);
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("Nicholas Majors"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "PI");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.clearProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        // assertEquals(getFieldValue(keyPersonnelPage, "newRolodexId"), "");
        // assertEquals(getFieldValue(keyPersonnelPage, "newPersonId"), "");
        saveAndSearchDoc(keyPersonnelPage);
    }
    /*
    @Test
    public void changeRole() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.KcPerson", "personId", PERSON_ID);
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("Nicholas Majors"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "PI");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        keyPersonnelPage = saveDoc(keyPersonnelPage);
        setFieldValue(keyPersonnelPage,"document.developmentProposalList[0].proposalPersons[0].proposalPersonRoleId", "COI");
        saveAndSearchDoc(keyPersonnelPage);        
    }
    
    @Test
    public void addDegree() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.KcPerson", "personId", PERSON_ID);
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("Nicholas Majors"));
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
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.KcPerson", "personId", PERSON_ID);
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("Nicholas Majors"));
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
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.deleteDegree.document.developmentProposalList[0].proposalPersons[0].line0", true));
        saveAndSearchDoc(keyPersonnelPage);        
    }
    */
    
    /**
     * Test the basic case of removing a unit from a proposal person
     */
    @Test
    public void removeUnit() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.KcPerson", "personId", PERSON_ID);
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("Nicholas Majors"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "PI");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        setFieldValue(keyPersonnelPage, "newProposalPersonUnit[0].unitNumber", "IU-UNIV");
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertUnit.document.developmentProposalList[0].proposalPersons[0].line", true));

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.deleteUnit.document.developmentProposalList[0].proposalPersons[0].line1", true));
        keyPersonnelPage = saveDoc(keyPersonnelPage);        
        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
    } 

   /**
     * Test adding a principal investigator
     */
    /*
    @Test
    public void addPrincipalInvestigator_Rolodex() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.NonOrganizationalRolodex");
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("First Name Middle Name Last Name"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "PI");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        saveAndSearchDoc(keyPersonnelPage);
    }
    */

    /**
     * Test adding a Key Person
     */
    @Test
    public void testAddKeyPerson() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.KcPerson", "personId", PERSON_ID);
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("Nicholas Majors"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "KP");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertTrue(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        
        setFieldValue(keyPersonnelPage,"newProposalPerson.projectRole", "Tester");
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));
        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        HtmlElement unitNumber = getElementById(keyPersonnelPage, "document.developmentProposalList[0].proposalPersons[0].unit[0].unitNumber");
        assertNull(unitNumber);
        assertTrue(keyPersonnelPage.asXml().contains("You have the option to add Certification Questions for a key person"));
        assertTrue(keyPersonnelPage.asXml().contains("You have the option to add unit details for a key person"));
         saveAndSearchDoc(keyPersonnelPage);
    }
    
    
    /**
     * Test adding a Unit Details for key person
     */
    @Test
    public void optInUnitDetailsKeyPerson() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.KcPerson", "personId", PERSON_ID);
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("Nicholas Majors"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "KP");
        setFieldValue(keyPersonnelPage,"newProposalPerson.projectRole", "Tester");
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.addUnitDetails.document.developmentProposalList[0].proposalPersons[0].line", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        //G3 is the id for the tbody element that contains the units, so if 000001 is found inside that element then the unit
        //was added
        HtmlElement tbodyElement = keyPersonnelPage.getHtmlElementById("G3");
        assertTrue(tbodyElement.asXml().contains("000001"));
        
        assertTrue(keyPersonnelPage.asText().contains("Combined Credit Split"));
        saveAndSearchDoc(keyPersonnelPage);
    }

    /**
     * Test Removing a Unit Details for key person
     */
    @Test
    public void removeUnitDetailsKeyPerson() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.KcPerson", "personId", PERSON_ID);
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("Nicholas Majors"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "KP");
        setFieldValue(keyPersonnelPage,"newProposalPerson.projectRole", "Tester");
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.addUnitDetails.document.developmentProposalList[0].proposalPersons[0].line", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        
        HtmlElement tbodyElement = keyPersonnelPage.getHtmlElementById("G3");
        assertTrue(tbodyElement.asXml().contains("000001"));

        assertTrue(keyPersonnelPage.asText().contains("Combined Credit Split"));
        saveAndSearchDoc(keyPersonnelPage);
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.removeUnitDetails.document.developmentProposalList[0].proposalPersons[0].line", true));
        assertFalse(keyPersonnelPage.asText().contains("Combined Credit Split"));
    }

    /**
     * Test Removing a Unit Details for key person
     */
    @Test
    public void addCertificationQuestionKeyPerson() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.KcPerson", "personId", PERSON_ID);
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("Nicholas Majors"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "KP");
        setFieldValue(keyPersonnelPage,"newProposalPerson.projectRole", "Tester");
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.addCertificationQuestion.document.developmentProposalList[0].proposalPersons[0].line", true));
        assertTrue(keyPersonnelPage.asXml().contains("Have lobbying activities been conducted on behalf of this proposal?"));

       }
    
    /**
     * For a proposal viewer, they should not be able to opt in the certify questions
     * for a Key Person.
     * @throws Exception
     */
    /*
    @Test
    public void testNoOptInCertifyForViewer() throws Exception {
        HtmlPage keyPersonnelPage = addKeyPerson();
        HtmlPage permissionsPage = clickOnTab(keyPersonnelPage, PERMISSIONS_LINK_NAME);
        
        permissionsPage = addUserToRole(permissionsPage, "majors", "Viewer");
        String docNbr = this.getDocNbr(permissionsPage);
        saveAndCloseDoc(permissionsPage);
        
        backdoorLogin("tdurkin");
        
        HtmlPage proposalPage = docSearch(docNbr);
        keyPersonnelPage = clickOnTab(proposalPage, KEY_PERSONNEL_LINK_NAME);
        
        assertTrue(!keyPersonnelPage.asText().contains("You have the option to add Certification Questions for a key person"));
        assertTrue(!keyPersonnelPage.asText().contains("You have the option to add unit details for a key person"));
    }
    */
    
    @Test @Ignore("Data needs to be loaded before this can run.")
    public void testNihSponsorPersonnelLabels() throws Exception {
        setSponsorForProposal(NIH_HIERARCHY_SPONSOR);
        assertTrue(this.getElement(keyPersonnelPage, "newProposalPerson.proposalPersonRoleId").asXml().contains(PI_NIH_LABEL));
        assertTrue(keyPersonnelPage.asText().contains("PI/Contact is a required Proposal Role"));
        assertFalse(this.getElement(keyPersonnelPage, "newProposalPerson.proposalPersonRoleId").asXml().contains(PI_NON_NIH_LABEL));
    }
    
    @Test
    public void testNonNihSponsorPersonnelLabels() throws Exception {
        setSponsorForProposal(NON_NIH_HIERARCHY_SPONSOR);
        assertTrue(this.getElement(keyPersonnelPage, "newProposalPerson.proposalPersonRoleId").asXml().contains(PI_NON_NIH_LABEL));
        assertFalse(this.getElement(keyPersonnelPage, "newProposalPerson.proposalPersonRoleId").asXml().contains(PI_NIH_LABEL));
    }
    
    /**
     * Add a Key Person.
     * @return
     * @throws Exception
     */
    private HtmlPage addKeyPerson() throws Exception {
        HtmlPage keyPersonnelPage = lookup(getKeyPersonnelPage(), "org.kuali.kra.bo.KcPerson", "personId", PERSON_ID);
        //assertTrue("New Person not found on page", keyPersonnelPage.asXml().contains("Nicholas Majors"));
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "KP");
        setFieldValue(keyPersonnelPage,"newProposalPerson.projectRole", "Tester");

        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));
        return keyPersonnelPage;
    }
    
    /**
     * Add a single user to the proposal.
     * @param page
     * @param username
     * @param roleName
     * @return
     * @throws Exception
     */
    private HtmlPage addUserToRole(HtmlPage page, String username, String roleName) throws Exception {
        setFieldValue(page, "newProposalUser.username", username);
        setFieldValue(page, "newProposalUser.roleName", roleName);
        HtmlElement addBtn = getElementByName(page, "methodToCall.addProposalUser", true);
        return clickOn(addBtn);
    }
    
    /**
     * @see org.kuali.kra.KraWebTestBase#testHelpLinks()
     */
//    @Test
//    public void testHelpLinks() throws Exception {
//        super.testHelpLinks();
//    }
    
    private void setSponsorForProposal(String sponsor) throws IOException {
        setFieldValue(getProposalDevelopmentPage(), PROPOSAL_SPONSOR_CODE_ID, sponsor);
        setKeyPersonnelPage(clickOn(getProposalDevelopmentPage(), KEY_PERSONNEL_IMAGE_NAME));
    }
    
}
