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
package org.kuali.kra.questionnaire.question;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

public class QuestionLookupableHelperServiceTest extends KcUnitTestBase {
    
    private static final String SEQUENCE_STATUS_CURRENT = "C";
    private static final String SEQUENCE_STATUS_ARCHIVED = "A";
    
    private QuestionLookupableHelperServiceImpl questionLookupableHelperServiceImpl;
    private DocumentService documentService;
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        questionLookupableHelperServiceImpl = new QuestionLookupableHelperServiceImpl();
        questionLookupableHelperServiceImpl.setBusinessObjectClass(Question.class);
        Map<String, String[]> parameters = new HashMap<String, String[]>();
        parameters.put("multipleValues", new String[] {"Yes"});
        questionLookupableHelperServiceImpl.setParameters(parameters );
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
     * This method tests getSearchResults.  It ensures that only the most recent version of a question
     * will be returned in the search result.
     * @throws Exception
     */
    @Test
    public void testGetSearchResults() throws Exception {
        
        // Create & submit new Question document for version 1 of the question
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1111"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestion(1111, SEQUENCE_STATUS_ARCHIVED));
        documentService.routeDocument(maintDocument, null, null);
        
        // Create & submit new Question document for version 2 of the question
        maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 2222"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestion(2222, SEQUENCE_STATUS_CURRENT));
        documentService.routeDocument(maintDocument, null, null);
        
        List<? extends Question> searchResults = (List<? extends Question>) questionLookupableHelperServiceImpl.getSearchResults(new HashMap());
        
        Assert.assertThat(searchResults.isEmpty(), is(not(true)));
        
        for (Question q : searchResults) {
            Assert.assertThat("question retrieved " + q.getQuestionId() + " did not have a status of " + SEQUENCE_STATUS_CURRENT, q.getSequenceStatus(), is(SEQUENCE_STATUS_CURRENT));
        }
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
  
        Question question = createQuestion(1111, SEQUENCE_STATUS_CURRENT);
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1111"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(question);
        documentService.routeDocument(maintDocument,null,null);
        
        List pkNames = new ArrayList();
        pkNames.add("questionRefId");
        
        String docNumber = maintDocument.getDocumentNumber();
        List<HtmlData> htmldata = questionLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);
        Assert.assertEquals(htmldata.size(), 1);
        Assert.assertEquals(getTailOfUrl(((AnchorHtmlData)htmldata.get(0)).getHref()), "DocHandler.do?command=displayDocSearchView&readOnly=true&docId="+docNumber);
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
  
        Question question = createQuestion(1111, SEQUENCE_STATUS_CURRENT);
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1111"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(question);
        documentService.routeDocument(maintDocument,null,null);
        
        List pkNames = new ArrayList();
        pkNames.add("questionRefId");
        
        String docNumber = maintDocument.getDocumentNumber();
        List<HtmlData> htmldata = questionLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);
        Assert.assertEquals(4, htmldata.size());
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(0)).getHref(), "../maintenanceQ.do?questionRefId="+question.getQuestionRefId()+"&businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=edit");
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(1)).getHref(), "../maintenanceQ.do?questionRefId="+question.getQuestionRefId()+"&businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=copy");
        Assert.assertEquals(getTailOfUrl(((AnchorHtmlData)htmldata.get(2)).getHref()), "DocHandler.do?command=displayDocSearchView&readOnly=true&docId="+docNumber);
    }

    private String getTailOfUrl(String url) {
        int inx = url.indexOf("kew");
        return url.substring(inx+4);
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
  
        Question question = createQuestion(1111, SEQUENCE_STATUS_CURRENT);
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1111"); 
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
     * @param questionRefId 
     * @parm sequenceStatus
     * @return question
     */
    private Question createQuestion(int questionRefId, String sequenceStatus) {
        Question question = new Question();
        question.setQuestionRefId(Long.valueOf(questionRefId));
        question.setQuestionIdFromInteger(questionRefId);
        question.setSequenceStatus(sequenceStatus);
        question.setQuestion("test" + questionRefId);
        question.setStatus("A");
        question.setCategoryTypeCode(1);
        question.setQuestionTypeId(1);  
        return question;
    }
}
