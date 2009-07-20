/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

/**
 * This class...
 */
public class ProposalAttachmentWebTest extends ProposalDevelopmentWebTestBase {

    private static final String KEY_PERSONNEL_IMAGE_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.keyPersonnel.x";
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    public static final String YES_BTN_ID =  "methodToCall.processAnswer.button0";
    
    private static final String USERNAME_FIELD_ID = "newProposalUser.username";
    private static final String ROLENAME_FIELD_ID = "newProposalUser.roleName";
    private static final String ADD_BTN_ID = "methodToCall.addProposalUser";
    
    private static final String NARRATIVE_FILE_ID = "newNarrative.narrativeFile";
    private static final String ATTACHMENT_FILENAME = "/org/kuali/kra/proposaldevelopment/web/ProposalAttachmentWebTest.class";


    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @Test
    public void testProposalAttachment() throws Exception {
        
        final HtmlPage propDevPage = getProposalDevelopmentPage();
        setDefaultRequiredFields(propDevPage);
        
        final HtmlPage propAttPage = clickOnTab(propDevPage, ABSTRACTS_ATTACHMENTS_LINK_NAME);
        
        assertTrue(propAttPage.asText().contains("Document was successfully saved"));
        // really is in proposal attachment page
        assertTrue(propAttPage.asText().contains("Attachment Type"));
        String[] values0 = { "1","Test Contact Name","t0@t0.com","666-666-6666","Test Comments","Test Module Title"};
        
        setProposalAttachmentLine(propAttPage, getKeyMap("newNarrative",values0));
//        testTextAreaPopup(propAttPage,"newNarrative.comments"," More text","proposalDevelopmentAbstractsAttachments","Comments","");
//        values0[5]+=" More text";
        String moreText = "More text";
        checkExpandedTextArea(propAttPage,"newNarrative.comments",values0[4],moreText);
        Map<String,String> keyVal0 = getKeyMap("document.narrative[0]",values0);
        keyVal0.remove("document.narrative[0].narrativeTypeCode");

        HtmlPage addedPage = testAddProposalAttachment(propAttPage,keyVal0);
        
        String[] values1 = { "2","Test Another Contact Name","t1@t1.com","666-666-6666","Test Comments again","Test Module Title again"};
        setProposalAttachmentLine(addedPage, getKeyMap("newNarrative",values1));
        Map<String,String> keyVal1 = getKeyMap("document.narrative[1]",values1);
        keyVal1.remove("document.narrative[1].narrativeTypeCode");
        addedPage = testAddProposalAttachment(addedPage,keyVal1);
        String[] values2 = { "3","Contact Name 2","t2@t2.com","666-666-6666","Test Comments 2","Test Module Title 2"};
        setProposalAttachmentLine(addedPage, getKeyMap("newNarrative",values2));
        
        Map<String,String> keyVal2 = getKeyMap("document.narrative[2]",values2);
        keyVal2.remove("document.narrative[2].narrativeTypeCode");
        addedPage = testAddProposalAttachment(addedPage,keyVal2);
        
        HtmlPage pageAfterDeletion = testDeleteProposalAttachment(addedPage,1,2);
        
        HtmlPage savedPage = testSaveProposalAttachment(pageAfterDeletion);
        
        validatePage(savedPage,keyVal0);
        Map<String,String> key1Val2 = getKeyMap("document.narrative[1]",values2);
        key1Val2.remove("document.narrative[1].narrativeTypeCode");
        validatePage(savedPage,key1Val2);
        
        HtmlPage uploadedPage = testUploadAttachment(savedPage);
        
        /*
         * Uncomment this only after implementing narrative user rights test cases.
         * replace link will be disabled if user doesn't have proper right to modify
         * 
         */
        testNarrUserRights(uploadedPage,2);
//        
        savedPage = testSaveProposalAttachment(uploadedPage);
        
        testReplaceAttachment(savedPage,2);
    }
    
    /**
     * Test the situation where the only user with rights to 
     * modify a narrative is having his/her narrative rights
     * changed to read.  This is bad.  There must always be at
     * least one user with modify rights.
     * @throws Exception
     */
    @Test
    public void testUserRightsLastModifier() throws Exception {
        HtmlPage page = initUserRightsTest();
        HtmlPage userRightsPage = clickOnViewRights(page, 1);
        
        setNarrativeAccess(userRightsPage, 0, "R");
        userRightsPage = clickOn(userRightsPage, "save");
        List<String> errors = this.getErrors(userRightsPage, "tab-Rights-div");
        assertTrue("At least one error should be flagged", errors.size() > 0);
        assertTrue(containsError(errors, "At least one user"));
    }
    
    /**
     * Test the situation where a user's narrative rights are being
     * set to a value above what their permission allows.  Specifically,
     * a user only has the VIEW_NARRATIVE permission, but we will try
     * to give that user the "modify" right for a narrative.  This 
     * should result in an error.
     * @throws Exception
     */
    @Test
    public void testUserRightsNoPermission() throws Exception {
        HtmlPage page = initUserRightsTest();
        HtmlPage userRightsPage = clickOnViewRights(page, 1);
        
        setNarrativeAccess(userRightsPage, 1, "M");
        userRightsPage = clickOn(userRightsPage, "save");
        List<String> errors = this.getErrors(userRightsPage, "tab-Rights-div");
        assertEquals(1, errors.size());
        assertTrue(containsError(errors, "cannot exceed that of assigned role/permission"));
    }
    
    /**
     * Someone who doesn't have modify rights for a narrative, shouldn't
     * be able to perform a save.  Therefore, make sure the save button
     * isn't displayed.
     * @throws Exception
     */
    @Test
    public void testUserRightsForViewer() throws Exception {
        boolean javaScriptEnabled = webClient.isJavaScriptEnabled();
        webClient.setJavaScriptEnabled(false);
        
        HtmlPage page = initUserRightsTest();
        String docNbr = getDocNbr(page);
        clickOn(page, "save");
        loginAsTester();
        page = docSearch(docNbr);
        page = clickOnTab(page, ABSTRACTS_ATTACHMENTS_LINK_NAME);
        HtmlPage userRightsPage = clickOnViewRights(page, 1);
        assertNull(getElement(userRightsPage, "save"));
        
        webClient.setJavaScriptEnabled(javaScriptEnabled);
    }
    
    /**
     * Create the proposal necessary for testing narrative user rights.
     * We will need two proposal attachments and a user with the Viewer role.
     * @return
     * @throws Exception
     */
    private HtmlPage initUserRightsTest() throws Exception {
        HtmlPage page = getAbstractsAndAttachmentsPage();
        page = addNarrative(page, "4");
        page = addNarrative(page, "5");
        page = clickOnTab(page, PERMISSIONS_LINK_NAME);
        page = addUser(page, "jtester", "Viewer");
        return clickOnTab(page, ABSTRACTS_ATTACHMENTS_LINK_NAME);
    }
    
    /**
     * Click on the "edit/view rights" button for a narrative.
     * @param page
     * @param index
     * @return
     * @throws IOException
     */
    private HtmlPage clickOnViewRights(HtmlPage page, int index) throws IOException {
        return clickOn(page, "getProposalAttachmentRights.line" + index);
    }
    
    /**
     * Set the narrative right for a user.  
     * @param page
     * @param index the index of the user
     * @param value the new right ("M", "R", or "N")
     */
    private void setNarrativeAccess(HtmlPage page, int index, String value) {
        setFieldValue(page, "newNarrativeUserRight[" + index + "].accessType", value);
    }
    
    /**
     * Add a single user to the proposal.
     * @param page
     * @param username
     * @param roleName
     * @return
     * @throws Exception
     */
    private HtmlPage addUser(HtmlPage page, String username, String roleName) throws Exception {
        setFieldValue(page, USERNAME_FIELD_ID, username);
        setFieldValue(page, ROLENAME_FIELD_ID, roleName);
        HtmlElement addBtn = getElementByName(page, ADD_BTN_ID, true);
        return clickOn(addBtn);
    }
    
    /**
     * Add a narrative to the proposal.
     * @param page
     * @param type
     * @return
     * @throws Exception
     */
    private HtmlPage addNarrative(HtmlPage page, String type) throws Exception {
        page = clickOnTab(page, ABSTRACTS_ATTACHMENTS_LINK_NAME);
        assertTrue(!hasError(page));
        setFieldValue(page, "newNarrative.narrativeTypeCode", type);
        //setFieldValue(page, "newNarrative.moduleStatusCode", "I");
        setFieldValue(page, NARRATIVE_FILE_ID, getFilePath());
        page = clickOn(page, "methodToCall.addProposalAttachment");
        return page;
    }
    

    private void testNarrUserRights(HtmlPage propPage,int lineNumber) throws Exception{
        assertNotNull(webClient);
        boolean javaScriptEnabled = webClient.isJavaScriptEnabled(); 
        webClient.setJavaScriptEnabled(false);
        HtmlPage rightPage = clickOn(propPage, "getProposalAttachmentRights.line"+lineNumber);
        String str = rightPage.asText();
        assertNotNull("Narrative user rights page is null",rightPage);
        assertContains(rightPage, "Proposal Attachment Rights for "+(lineNumber+1)+".");
        HtmlTable table = getTable(rightPage, "narrative-rights-table");
        int roCnt = table.getRowCount()-1;
        assertTrue("Narrative user rights table is empty. Make sure proposal user roles have been added to the proposal",roCnt>0);
        ArrayList<String> accessTypes = new ArrayList<String>(3);
        accessTypes.add("M");
        accessTypes.add("R");
        accessTypes.add("N");
        do{
            String accessType = getFieldValue(rightPage, "newNarrativeUserRight["+(--roCnt)+"].accessType");
          //Check accessTypes are one of (M R or N)
          assertTrue(accessTypes.contains(accessType));
        }while(roCnt>0);
        setFieldValue(rightPage, "newNarrativeUserRight[0].accessType", "M");
        HtmlPage closePage = clickOn(rightPage, "methodToCall.addProposalAttachmentRights");
        assertContains(closePage, "Empty Page");
//        HtmlPage savedPage = testSaveProposalAttachment(propPage);
//        HtmlPage rightPage1 = clickOn(savedPage, "getProposalAttachmentRights.line"+lineNumber);
//        assertEquals("M",getFieldValue(rightPage1,"document.narrative["+lineNumber+"].narrativeUserRight[0].accessType"));
        webClient.setJavaScriptEnabled(javaScriptEnabled);

    }


    private HtmlPage testReplaceAttachment(HtmlPage uploadedPage,int lineIndex) throws Exception{
        HtmlPage rplPage = clickOn(uploadedPage, "replaceProposalAttachment.line"+lineIndex);
        URL fileUrl = getClass().getResource("/org/kuali/kra/proposaldevelopment/web/ProposalDevelopmentWebTestBase.class");
        String filePath = fileUrl.getPath();
        setFieldValue(rplPage, "document.narrative["+lineIndex+"].narrativeFile", filePath);
        HtmlPage replacedPage = clickOn(rplPage,"methodToCall.replaceProposalAttachment.line"+lineIndex+".anchor"+(lineIndex+1));
        HtmlPage savedPage = testSaveProposalAttachment(replacedPage);
        assertEquals(getFieldValue(savedPage, "document.narrative["+lineIndex+"].fileName"), "ProposalDevelopmentWebTestBase.class");
        return savedPage;
    }


    private HtmlPage testUploadAttachment(HtmlPage page) throws Exception{
        URL fileUrl = getClass().getResource("/org/kuali/kra/proposaldevelopment/web/ProposalAttachmentWebTest.class");
        assertNotNull(fileUrl);
        String filePath = fileUrl.getPath();
        String[] values4 = { "4","Test Contact Name 4","t4@t4.com","666-666-6666","Test Comments 4","Test Module Title 4"};
        setProposalAttachmentLine(page, getKeyMap("newNarrative",values4));
        setFieldValue(page, "newNarrative.narrativeFile", filePath);
        Map<String,String> keyVal4 = getKeyMap("document.narrative[2]",values4);
        keyVal4.remove("document.narrative[2].narrativeTypeCode");
        HtmlPage addedPage = testAddProposalAttachment(page,keyVal4);
        HtmlPage savedPage = testSaveProposalAttachment(addedPage);
        assertEquals(getFieldValue(savedPage, "document.narrative[2].fileName"), "ProposalAttachmentWebTest.class");
        return savedPage;
    }


    private HtmlPage testSaveProposalAttachment(HtmlPage pageAfterDeletion) throws Exception{
        HtmlPage pageAfterSave = clickOn(pageAfterDeletion,"methodToCall.save");
        return pageAfterSave;
    }
  

    private HtmlPage testDeleteProposalAttachment(HtmlPage page, int i,int tabIndex) throws Exception{
        String commentToBeDeleted = getFieldValue(page, "document.narrative["+i+"].comments");

        HtmlPage confirmationPage = clickOn(page, "methodToCall.deleteProposalAttachment.line"+i+".anchor"+tabIndex);
        HtmlPage deletedPage = clickOn(confirmationPage, YES_BTN_ID);
        if(i>0)
            assertNotSame(commentToBeDeleted,getFieldValue(deletedPage, "document.narrative["+(--i)+"].comments"));
        return deletedPage;
    }

    private Map<String, String> getKeyMap(String propertyBaseString,String[] values) {
        Map<String,String> keyVal = new HashMap<String,String>();
        keyVal.put(propertyBaseString+".narrativeTypeCode", values[0]);
        keyVal.put(propertyBaseString+".contactName", values[1]);
        keyVal.put(propertyBaseString+".emailAddress", values[2]);
        keyVal.put(propertyBaseString+".phoneNumber", values[3]);
        keyVal.put(propertyBaseString+".comments", values[4]);
        keyVal.put(propertyBaseString+".moduleTitle", values[5]);
        return keyVal;
    }


    private HtmlPage testAddProposalAttachment(HtmlPage propAttPage,Map<String,String> keyValues) throws Exception{
        HtmlPage addedPage = clickOn(propAttPage, "methodToCall.addProposalAttachment");
        validatePage(addedPage,keyValues);
        return addedPage;
    }


    private void setProposalAttachmentLine(HtmlPage propAttPage, Map<String,String> keyValues) {
        Iterator<String> it = keyValues.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            setFieldValue(propAttPage, key, keyValues.get(key));
        }
        setFieldValue(propAttPage, NARRATIVE_FILE_ID, getFilePath());
    }
    
    /**
     * Get the path to a file.  That file will be attached to the document.
     * @return
     */
    private String getFilePath() {
        URL fileUrl = getClass().getResource(ATTACHMENT_FILENAME);
        assertNotNull(fileUrl);
        return fileUrl.getPath();
    }

}
