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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

/**
 * This class...
 */
public class ProposalAttachmentWebTest extends ProposalDevelopmentWebTestBase {

    private static final String KEY_PERSONNEL_IMAGE_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.keyPersonnel.x";
    private static final String ERRORS_FOUND_ON_PAGE = "error(s) found on page";
    public static final String YES_BTN_ID =  "methodToCall.processAnswer.button0";

    @Test
    public void testProposalAttachment() throws Exception {
        
        final HtmlPage propDevPage = getProposalDevelopmentPage();
        setDefaultRequiredFields(propDevPage);
        
        final HtmlPage keyPersonnelPage = clickOn(getProposalDevelopmentPage(), KEY_PERSONNEL_IMAGE_NAME);
        
        
        final HtmlPage propAttPage = clickOn(addKeyPerson(keyPersonnelPage), "methodToCall.headerTab.headerDispatch.save.navigateTo.abstractsAttachments.x");
      
        assertTrue(propAttPage.asText().contains("Document was successfully saved"));
        // really is in proposal attachment page
        assertTrue(propAttPage.asText().contains("Attachment Type"));
        String[] values0 = { "1","I","Test Contact Name","t0@t0.com","123456","Test Comments","Test Module Title"};
        
        setProposalAttachmentLine(propAttPage, getKeyMap("newNarrative",values0));
//        testTextAreaPopup(propAttPage,"newNarrative.comments"," More text","proposalDevelopmentAbstractsAttachments","Comments","");
//        values0[5]+=" More text";
        String moreText = "More text";
        checkExpandedTextArea(propAttPage,"newNarrative.comments",values0[5],moreText);
        Map<String,String> keyVal0 = getKeyMap("document.narrative[0]",values0);
        keyVal0.remove("document.narrative[0].narrativeTypeCode");

        HtmlPage addedPage = testAddProposalAttachment(propAttPage,keyVal0);
        
        String[] values1 = { "2","I","Test Another Contact Name","t1@t1.com","1234567","Test Comments again","Test Module Title again"};
        setProposalAttachmentLine(addedPage, getKeyMap("newNarrative",values1));
        Map<String,String> keyVal1 = getKeyMap("document.narrative[1]",values1);
        keyVal1.remove("document.narrative[1].narrativeTypeCode");
        addedPage = testAddProposalAttachment(addedPage,keyVal1);
        String[] values2 = { "3","I","Contact Name 2","t2@t2.com","12345678","Test Comments 2","Test Module Title 2"};
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

    private HtmlPage addKeyPerson(HtmlPage keyPersonPage) throws Exception {
        HtmlPage keyPersonnelPage = lookup(keyPersonPage, "org.kuali.kra.bo.Person","userName","quickstart");
        assertEquals("Geoff McGregor", getFieldValue(keyPersonnelPage, "newProposalPerson.fullName"));
        
        setFieldValue(keyPersonnelPage,"newProposalPerson.proposalPersonRoleId", "KP");

        clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));

        assertFalse(keyPersonnelPage.asText().contains(ERRORS_FOUND_ON_PAGE));
        keyPersonnelPage = clickOn(getElementByName(keyPersonnelPage, "methodToCall.insertProposalPerson", true));
        return keyPersonnelPage;
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
            String accessType = getFieldValue(rightPage, "document.narrative["+lineNumber+"].narrativeUserRight["+(--roCnt)+"].accessType");
          //Check accessTypes are one of (M R or N)
          assertTrue(accessTypes.contains(accessType));
        }while(roCnt>0);
        setFieldValue(rightPage, "document.narrative["+lineNumber+"].narrativeUserRight[0].accessType", "M");
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
        String[] values4 = { "4","I","Test Contact Name 4","t4@t4.com","12345678","Test Comments 4","Test Module Title 4"};
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
        keyVal.put(propertyBaseString+".moduleStatusCode", values[1]);
        keyVal.put(propertyBaseString+".contactName", values[2]);
        keyVal.put(propertyBaseString+".emailAddress", values[3]);
        keyVal.put(propertyBaseString+".phoneNumber", values[4]);
        keyVal.put(propertyBaseString+".comments", values[5]);
        keyVal.put(propertyBaseString+".moduleTitle", values[6]);
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
    }

}
