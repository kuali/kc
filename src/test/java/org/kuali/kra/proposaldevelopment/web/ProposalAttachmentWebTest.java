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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeStatus;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.ClickableElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlLink;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * This class...
 */
public class ProposalAttachmentWebTest extends ProposalDevelopmentWebTestBase {

    @Test
    public void testProposalAttachment() throws Exception {
        
//        final WebClient webClient = new WebClient();
        final HtmlPage propDevPage = getProposalDevelopmentPage();
        setDefaultRequiredFields(propDevPage);
        
      final HtmlPage propAttPage = clickOn(propDevPage, "methodToCall.headerTab.headerDispatch.save.navigateTo.abstractsAttachments.x");
      
        assertTrue(propAttPage.asText().contains("Document was successfully saved"));
        // really is in proposal attachment page
        assertTrue(propAttPage.asText().contains("Attachment Type"));
//        webClient.setJavaScriptEnabled(false);
        String[] values0 = { "1","C","Test Contact Name","t@t.com","123456","Test Comments","Test Module Title"};
        
        setProposalAtatchmentLine(propAttPage, getKeyMap("newNarrative",values0));
        testTextAreaPopup(propAttPage,"newNarrative.comments"," More text","proposalDevelopmentAbstractsAttachments","Comments","");
        values0[5]+=" More text";
        Map<String,String> keyVal0 = getKeyMap("document.narratives[0]",values0);
        HtmlPage addedPage = testAddProposalAttachment(propAttPage,keyVal0);
        
        String[] values1 = { "2","I","Test Another Contact Name","t1@t1.com","1234567","Test Comments again","Test Module Title again"};
        setProposalAtatchmentLine(addedPage, getKeyMap("newNarrative",values1));
        Map<String,String> keyVal1 = getKeyMap("document.narratives[1]",values1);
        addedPage = testAddProposalAttachment(addedPage,keyVal1);
        String[] values2 = { "3","I","Contact Name 2","t2@t2.com","12345678","Test Comments 2","Test Module Title 2"};
        setProposalAtatchmentLine(addedPage, getKeyMap("newNarrative",values2));
        
        Map<String,String> keyVal2 = getKeyMap("document.narratives[2]",values2);
        addedPage = testAddProposalAttachment(addedPage,keyVal2);
        
        HtmlPage pageAfterDeletion = testDeleteProposalAttachment(addedPage,1,2);
        
        HtmlPage savedPage = testSaveProposalAttachment(pageAfterDeletion);
        
        validatePage(savedPage,keyVal0);
        Map<String,String> key1Val2 = getKeyMap("document.narratives[1]",values2);
        validatePage(savedPage,key1Val2);
        
        HtmlPage uploadedPage = testUploadAttachment(savedPage);

        testReplaceAttachment(uploadedPage,2);
    }


    private HtmlPage testReplaceAttachment(HtmlPage uploadedPage,int lineIndex) throws Exception{
        HtmlPage rplPage = clickOn(uploadedPage, "replaceProposalAttachment.line"+lineIndex);
        URL fileUrl = getClass().getResource("/org/kuali/kra/proposaldevelopment/web/ProposalDevelopmentWebTestBase.class");
        String filePath = fileUrl.getPath();
        setFieldValue(rplPage, "document.narratives["+lineIndex+"].narrativeFile", filePath);
        HtmlPage replacedPage = clickOn(rplPage,"methodToCall.replaceProposalAttachment.line"+lineIndex+".anchor"+(lineIndex+1));
        HtmlPage savedPage = testSaveProposalAttachment(replacedPage);
        assertEquals(getFieldValue(savedPage, "document.narratives["+lineIndex+"].fileName"), "ProposalDevelopmentWebTestBase.class");
        return savedPage;
    }


    private HtmlPage testUploadAttachment(HtmlPage page) throws Exception{
        URL fileUrl = getClass().getResource("/org/kuali/kra/proposaldevelopment/web/ProposalAttachmentWebTest.class");
        assertNotNull(fileUrl);
        String filePath = fileUrl.getPath();
        String[] values4 = { "4","I","Test Contact Name 4","t4@t4.com","12345678","Test Comments 4","Test Module Title 4"};
        setProposalAtatchmentLine(page, getKeyMap("newNarrative",values4));
        setFieldValue(page, "newNarrative.narrativeFile", filePath);
        Map<String,String> keyVal4 = getKeyMap("document.narratives[2]",values4);
        HtmlPage addedPage = testAddProposalAttachment(page,keyVal4);
        HtmlPage savedPage = testSaveProposalAttachment(addedPage);
        assertEquals(getFieldValue(savedPage, "document.narratives[2].fileName"), "ProposalAttachmentWebTest.class");
        return savedPage;
    }


    private HtmlPage testSaveProposalAttachment(HtmlPage pageAfterDeletion) throws Exception{
        HtmlPage pageAfterSave = clickOn(pageAfterDeletion,"methodToCall.save");
        return pageAfterSave;
    }


    private HtmlPage testDeleteProposalAttachment(HtmlPage page, int i,int tabIndex) throws Exception{
        String commentToBeDeleted = getFieldValue(page, "document.narratives["+i+"].comments");
        HtmlPage deletedPage = clickOn(page, "methodToCall.deleteProposalAttachment.line"+i+".anchor"+tabIndex);
        if(i>0)
            assertNotSame(commentToBeDeleted,getFieldValue(deletedPage, "document.narratives["+(--i)+"].comments"));
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


    private void setProposalAtatchmentLine(HtmlPage propAttPage, Map<String,String> keyValues) {
        Iterator<String> it = keyValues.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            setFieldValue(propAttPage, key, keyValues.get(key));
        }
    }

}
