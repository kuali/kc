/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.questionnaire.question;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.common.questionnaire.impl.question.QuestionAuthorizationService;
import org.kuali.coeus.common.questionnaire.impl.question.QuestionLookupableHelperServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class QuestionLookupableHelperServiceTest extends KcIntegrationTestBase {
    
    private static final String SEQUENCE_STATUS_CURRENT = "C";
    private static final String SEQUENCE_STATUS_ARCHIVED = "A";
    
    private QuestionLookupableHelperServiceImpl questionLookupableHelperServiceImpl;
    private DocumentService documentService;
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    @Before
    public void setUp() throws Exception {
        questionLookupableHelperServiceImpl = new QuestionLookupableHelperServiceImpl();
        questionLookupableHelperServiceImpl.setBusinessObjectClass(Question.class);
        Map<String, String[]> parameters = new HashMap<String, String[]>();
        parameters.put("multipleValues", new String[] {"Yes"});
        questionLookupableHelperServiceImpl.setParameters(parameters );
        documentService = KcServiceLocator.getService(DocumentService.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }
    
    @After
    public void tearDown() throws Exception {
        questionLookupableHelperServiceImpl = null;
        documentService = null;
        GlobalVariables.setUserSession(null);
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
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1111"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestion(1111, SEQUENCE_STATUS_ARCHIVED));
        maintDocument.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        documentService.routeDocument(maintDocument, null, null);
        
        // Create & submit new Question document for version 2 of the question
        maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1112"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestion(1112, SEQUENCE_STATUS_CURRENT));
        maintDocument.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        documentService.routeDocument(maintDocument, null, null);
        
        List<? extends Question> searchResults = (List<? extends Question>) questionLookupableHelperServiceImpl.getSearchResults(new HashMap());
        
        Assert.assertThat(searchResults.isEmpty(), is(not(true)));
        
        for (Question q : searchResults) {
            Assert.assertThat("question retrieved " + q.getQuestionSeqId() + " did not have a status of " + SEQUENCE_STATUS_CURRENT, q.getSequenceStatus(), is(SEQUENCE_STATUS_CURRENT));
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
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1111"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(question);
        maintDocument.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        documentService.routeDocument(maintDocument,null,null);
        
        List pkNames = new ArrayList();
        pkNames.add("id");
        
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
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1111"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(question);
        maintDocument.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        documentService.routeDocument(maintDocument,null,null);
        
        List pkNames = new ArrayList();
        pkNames.add("id");
        
        String docNumber = maintDocument.getDocumentNumber();
        List<HtmlData> htmldata = questionLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);
        Assert.assertEquals(4, htmldata.size());
        Assert.assertEquals("../maintenanceQ.do?businessObjectClassName=" + Question.class.getName() + "&methodToCall=edit&id="+question.getId(), ((AnchorHtmlData)htmldata.get(0)).getHref());
        Assert.assertEquals("../maintenanceQ.do?businessObjectClassName=" + Question.class.getName() + "&methodToCall=copy&id="+question.getId(), ((AnchorHtmlData)htmldata.get(1)).getHref());
        Assert.assertEquals("../maintenanceQ.do?businessObjectClassName=" + Question.class.getName() + "&methodToCall=delete&id="+question.getId(), ((AnchorHtmlData)htmldata.get(2)).getHref());
        Assert.assertEquals(getTailOfUrl(((AnchorHtmlData)htmldata.get(3)).getHref()), "DocHandler.do?command=displayDocSearchView&readOnly=true&docId="+docNumber);
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
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1111"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(question);
        maintDocument.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        documentService.routeDocument(maintDocument,null,null);

        List pkNames = new ArrayList();
        pkNames.add("id");
        
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
        question.setId(Long.valueOf(questionRefId));
        question.setQuestionSeqId(questionRefId);
        question.setSequenceStatus(sequenceStatus);
        question.setQuestion("test" + questionRefId);
        question.setStatus("A");
        question.setCategoryTypeCode(1L);
        question.setQuestionTypeId(1L);
        return question;
    }
}
