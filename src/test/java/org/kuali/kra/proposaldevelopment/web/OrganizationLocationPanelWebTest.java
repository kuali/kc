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

import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * This class tests tests the Sponsor & Program Information panel of the Proposal Development
 * Proposal web page.
 */
public class OrganizationLocationPanelWebTest extends ProposalDevelopmentWebTestBase {


    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";

    /**
    *
    * Test organization/location panel on proposal page
    * @throws Exception
    */
   @Test
   public void testOrganizationLocationPanel() throws Exception {
       HtmlPage proposalPage = getProposalDevelopmentPage();

       setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);

       // start to set up organization/location panel
       // organization
       HtmlPage locationPage = lookup(proposalPage, "org.kuali.kra.bo.Organization");

       assertEquals("000001", getFieldValue(locationPage, "document.organizationId"));
       assertContains(locationPage, "Congressional District: Eighth");
       //assertContains(locationPage, "Performing Organization: University");
       assertContains(locationPage, "Applicant Organization: University");
       assertContains(locationPage, "Authorized Representative Name & Address: First Name");

       // default prop location created
       assertEquals("University", getFieldValue(locationPage, "document.proposalLocation[0].location"));

       // delete default line
       HtmlPage page5 = clickOn(locationPage, "methodToCall.deleteLocation.line0.anchor3");

       // save without location line
       // the default location line will be recreated
       HtmlPage page6 = clickOn(page5, "methodToCall.save", "Kuali :: Proposal Development Document");

       // need at least one location
       assertContains(page6, ERRORS_FOUND_ON_PAGE);
       assertDoesNotContain(page6, "Document was successfully saved");

       // performing org lookup
       HtmlPage page7 = lookup(page6, "document.performingOrganizationId", "organizationId", "000002");

       assertEquals("000002", getFieldValue(page7, "document.performingOrganizationId"));
       assertContains(page7, "Performing Organization: California Institute of Technology");
       // California Institute of Technology

       // proplocations
       // set up and add first line
       setFieldValue(page7, "newPropLocation.location", "location 1");

       // test rolodex lookup
       HtmlPage page8 = lookup(page7, "newPropLocation.rolodexId", "rolodexId", "1728");

       assertEquals("1728", getFieldValue(page8, "newPropLocation.rolodexId"));
       assertContains(page8, "National Center for Environmental Research and Quality Assurance");

       final HtmlPage page9 = clickOn(page8, "methodToCall.addLocation.anchor3");

       assertEquals("0", getFieldValue(page9, "newPropLocation.rolodexId"));
       // how to check newlocation address is empty
       assertEquals("1728", getFieldValue(page9, "document.proposalLocation[0].rolodexId"));
       assertContains(page9, "National Center for Environmental Research and Quality Assurance");

       // 2nd line
       // set up and add 2nd line
       setFieldValue(page9, "newPropLocation.location", "location 2");

       // test rolodex lookup
       HtmlPage page10 = lookup(page9, "newPropLocation.rolodexId", "rolodexId", "1727");
       assertEquals("1727", getFieldValue(page10, "newPropLocation.rolodexId"));
       assertContains(page10, "Organization 1126");

       final HtmlPage page11 = clickOn(page10, "methodToCall.addLocation.anchor3");

       assertEquals("0", getFieldValue(page11, "newPropLocation.rolodexId"));
       // how to check newlocation address is empty
       assertEquals("1727", getFieldValue(page11, "document.proposalLocation[1].rolodexId"));
       assertContains(page11, "Organization 1126");

       // clearaddress
       final HtmlPage page12 = clickOn(page11, "methodToCall.clearAddress.line0.anchor3");
       assertEquals("0", getFieldValue(page12, "document.proposalLocation[0].rolodexId"));
       assertDoesNotContain(page12, "National Center for Environmental Research and Quality Assurance");
       // verify other fields too? location, proplocation[1] ?

       // delete lines
       final HtmlPage page13 = clickOn(page12, "methodToCall.deleteLocation.line0.anchor3");
       assertEquals("1727", getFieldValue(page13, "document.proposalLocation[0].rolodexId"));
       assertContains(page13, "Organization 1126");
       // how to check only one left
       final HtmlPage page14 = clickOn(page13, "methodToCall.save", "Kuali :: Proposal Development Document");

       // one of the following to check save is OK
       assertDoesNotContain(page14, ERRORS_FOUND_ON_PAGE);
       assertContains(page14, "Document was successfully saved");
       // verify form is still ok
       assertEquals("000001", getFieldValue(page14, "document.organizationId"));
       assertContains(page14, "Congressional District: Eighth");
       assertContains(page14, "Applicant Organization: University");
       assertContains(page14, "Authorized Representative Name & Address: First Name");
       assertEquals("000002", getFieldValue(page14, "document.performingOrganizationId"));
       assertContains(page14, "Performing Organization: California Institute of Technology");

       assertEquals("1727", getFieldValue(page14, "document.proposalLocation[0].rolodexId"));
       assertContains(page14, "Organization 1126");

       // verify DB
       String documentNumber = getFieldValue(page14, "document.documentHeader.documentNumber");

       ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
       assertNotNull(doc);

       verifySavedRequiredFields(doc, DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_OWNED_BY_UNIT, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "2007-08-14", "2007-08-21", DEFAULT_PROPOSAL_TYPE_CODE);
       assertEquals("000001", doc.getOrganizationId());
       assertEquals("000002", doc.getPerformingOrganizationId());
       assertEquals("location 2", doc.getProposalLocations().get(0).getLocation());
       assertEquals(new Integer(1727), doc.getProposalLocations().get(0).getRolodexId());

   }
   
   /**
   *
   * Test organization/location panel on proposal page Applicant and Performing Organization Lookup
   * @throws Exception
   */
  @Test
  public void testOrganizationLocationPanelOrganizationLookupNew() throws Exception {   
      HtmlPage proposalPage = getProposalDevelopmentPage();

      
      
      setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);

      HtmlPage page1 = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");
      
      // applicant org lookup
      HtmlPage page2 = lookup(page1, "document.organizationId", "organizationId", "000002");
      
      HtmlPage page3 = clickOn(page2, "methodToCall.save", "Kuali :: Proposal Development Document");
      
      String documentNumber = getFieldValue(page3, "document.documentHeader.documentNumber");
      ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
      
      
      assertEquals("000002", doc.getPerformingOrganizationId());
      assertEquals("000002", doc.getOrganizationId());
      
      // performing org lookup
      HtmlPage page4 = lookup(page3, "document.performingOrganizationId", "organizationId", "000005");
      HtmlPage page5 = clickOn(page4, "methodToCall.save", "Kuali :: Proposal Development Document");
      
      doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
      
      assertEquals("000005", doc.getPerformingOrganizationId());
      assertEquals("000002", doc.getOrganizationId());
      
      // applicant org lookup
      HtmlPage page6 = lookup(page5, "document.organizationId", "organizationId", "000007");
      
      HtmlPage page7 = clickOn(page6, "methodToCall.save", "Kuali :: Proposal Development Document");
      
      doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
      
      assertEquals("000005", doc.getPerformingOrganizationId());
      assertEquals("000007", doc.getOrganizationId());
  }
  /**
  *
  * Test organization/location panel on proposal page Applicant and Performing Organization Lookup
  * @throws Exception
  */
  
 @Test
 public void testOrganizationLocationPanelOrganizationLookupExisting() throws Exception {   
     HtmlPage proposalPage = getProposalDevelopmentPage();

     setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);
     
     // verify DB
     String documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");
     
     HtmlPage page1 = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");
     
     HtmlPage page55 = clickOn(page1, "methodToCall.close", "Kuali :: Question Dialog Page");
     HtmlPage page56 = clickOn(page55, "methodToCall.processAnswer.button0", "Kuali Portal Index");
//    HtmlPage page2 = clickOn(page1, "Main Menu", "Kuali Portal Index");
     HtmlPage page57 = clickOn(page56, "Action List", "Kuali Portal Index");     
//     HtmlPage page4 = clickOn(page57, documentNumber, "Kuali :: Proposal Development Document");    
//     http://localhost:8080/kra-dev/en/DocHandler.do?docId=2241&command=displayActionListView
     String url = "http://localhost:" + getPort() + "/kra-dev/en/DocHandler.do?docId=" + documentNumber + "&command=displayActionListView";
     HtmlPage page4 = buildPageFromUrl(url,"Kuali :: Proposal Development Document");
         // applicant org lookup
     HtmlPage page5 = lookup(page4, "document.organizationId", "organizationId", "000002");
     
     HtmlPage page6 = clickOn(page5, "methodToCall.save", "Kuali :: Proposal Development Document");
     
     ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
     
     assertEquals("000002", doc.getOrganizationId());
     assertEquals("000002", doc.getPerformingOrganizationId());
     
     
     // performing org lookup
     HtmlPage page7 = lookup(page6, "document.performingOrganizationId", "organizationId", "000005");
     HtmlPage page8 = clickOn(page7, "methodToCall.save", "Kuali :: Proposal Development Document");
     
     doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
     
     assertEquals("000005", doc.getPerformingOrganizationId());
     assertEquals("000002", doc.getOrganizationId());      
     
 }
}
