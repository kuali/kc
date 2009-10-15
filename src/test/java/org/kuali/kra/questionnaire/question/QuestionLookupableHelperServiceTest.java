/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.questionnaire.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.kns.util.GlobalVariables;

public class QuestionLookupableHelperServiceTest extends KraTestBase {
    
    private QuestionLookupableHelperServiceImpl questionLookupableHelperServiceImpl;
    private DocumentService documentService;
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        questionLookupableHelperServiceImpl = (QuestionLookupableHelperServiceImpl) KraServiceLocator.getService("questionLookupableHelperService");
        questionLookupableHelperServiceImpl.setBusinessObjectClass(Question.class);
        documentService = KraServiceLocator.getService(DocumentService.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }
    
    @After
    public void tearDown() throws Exception {
        questionLookupableHelperServiceImpl = null;
        documentService = null;
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    /**
     * 
     * This method tests getSearchResults
     * @throws Exception
     */
    @Test
    public void testGetSearchResults() throws Exception {
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestion(1));
        documentService.routeDocument(maintDocument, null, null);
        
        // 2nd document
        maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 2"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestion(2));
        documentService.routeDocument(maintDocument, null, null);
        List<? extends BusinessObject> searchResults = questionLookupableHelperServiceImpl.getSearchResults(new HashMap());
        Assert.assertEquals(searchResults.size(), 2);
        // newer one will be at the top
        Question question = (Question)searchResults.get(0);
        Assert.assertEquals(question.getQuestion(), "test2");
        
        question = (Question)searchResults.get(1);
        Assert.assertEquals(question.getQuestion(), "test1");
    }

    /**
     * 
     * This method tests getCustomActionUrls with VIEW_QUESTION permission
     * @throws Exception
     */
    @Test
    public void testGetCustomActionUrlsWithViewQuestionPermission() throws Exception {
        final QuestionAuthorizationService questionAuthorizationService = context.mock(QuestionAuthorizationService.class);
        questionLookupableHelperServiceImpl.setQuestionAuthorizationService(questionAuthorizationService);
        context.checking(new Expectations() {{
            one(questionAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTION);
            will(returnValue(false));
            one(questionAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTION);
            will(returnValue(true));
        }});
  
        Question question = createQuestion(1);
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(question);
        documentService.routeDocument(maintDocument,null,null);
        
        List pkNames = new ArrayList();
        pkNames.add("questionRefId");
        
        List<HtmlData> htmldata = questionLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);
        Assert.assertEquals(htmldata.size(), 1);
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(0)).getHref(), "../maintenance.do?questionRefId="+question.getQuestionRefId()+"&businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=edit&readOnly=true");
    }
    
    /**
     * 
     * This method tests getCustomActionUrls with MODIFY_QUESTION permission
     * @throws Exception
     */
    @Test
    public void testGetCustomActionUrlsWithModifyQuestionPermission() throws Exception {
        final QuestionAuthorizationService questionAuthorizationService = context.mock(QuestionAuthorizationService.class);
        questionLookupableHelperServiceImpl.setQuestionAuthorizationService(questionAuthorizationService);
        context.checking(new Expectations() {{
            one(questionAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTION);
            will(returnValue(true));
            one(questionAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTION);
            will(returnValue(false));
        }});
  
        Question question = createQuestion(1);
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(question);
        documentService.routeDocument(maintDocument,null,null);
        
        List pkNames = new ArrayList();
        pkNames.add("questionRefId");
        
        List<HtmlData> htmldata = questionLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);
        Assert.assertEquals(htmldata.size(), 3);
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(0)).getHref(), "../maintenance.do?questionRefId="+question.getQuestionRefId()+"&businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=edit");
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(1)).getHref(), "../maintenance.do?questionRefId="+question.getQuestionRefId()+"&businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=copy");
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(2)).getHref(), "../maintenance.do?questionRefId="+question.getQuestionRefId()+"&businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=edit&readOnly=true");
    }

    /**
     * 
     * This method tests getCustomActionUrls with no permissions
     * @throws Exception
     */
    @Test
    public void testGetCustomActionUrlsWithNoPermissions() throws Exception {
        final QuestionAuthorizationService questionAuthorizationService = context.mock(QuestionAuthorizationService.class);
        questionLookupableHelperServiceImpl.setQuestionAuthorizationService(questionAuthorizationService);
        context.checking(new Expectations() {{
            one(questionAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTION);
            will(returnValue(false));
            one(questionAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTION);
            will(returnValue(false));
        }});
  
        Question question = createQuestion(1);
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(question);
        documentService.routeDocument(maintDocument,null,null);

        List pkNames = new ArrayList();
        pkNames.add("questionRefId");
        
        List<HtmlData> htmldata = questionLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);
        Assert.assertEquals(htmldata.size(), 0);
    }
    
    /**
     * 
     * This method creates a simple question 
     * @param questionId 
     * @return question
     */
    private Question createQuestion(int questionId) {
        Question question = new Question();
        question.setQuestionRefId(Long.valueOf(questionId));
        question.setQuestionId(questionId);
        question.setQuestion("test" + questionId);
        question.setStatus("A");
        question.setCategoryTypeCode(1);
        question.setQuestionTypeId(1);  
        return question;
    }
}
