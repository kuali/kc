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
package org.kuali.kra.questionnaire;

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
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;

@PerSuiteUnitTestData(
@UnitTestData(
  sqlFiles = {
       @UnitTestFile(filename = "classpath:sql/dml/load_QUESTION.sql", delimiter = ";")
}
)
)

public class QuestionnaireLookupableHelperServiceTest  extends KraTestBase {

    QuestionnaireLookupableHelperServiceImpl questionnaireLookupableHelperServiceImpl;
    private DocumentService documentService;
    private Mockery context = new JUnit4Mockery();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        questionnaireLookupableHelperServiceImpl = (QuestionnaireLookupableHelperServiceImpl)KraServiceLocator.getService("questionnaireLookupableHelperService");
        questionnaireLookupableHelperServiceImpl.setBusinessObjectClass(Questionnaire.class);
        documentService = KraServiceLocator.getService(DocumentService.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
   }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        questionnaireLookupableHelperServiceImpl = null;
        documentService = null;
        GlobalVariables.setUserSession(null);
    }


    /**
     * 
     * This method to test getSearchResults
     * @throws Exception
     */
    @Test 
    public void testGetSearchResults() throws Exception {
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test1", "desc 1"));
        documentService.routeDocument(maintDocument, null, null);
        
        // 2nd document
        maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 2"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test2", "desc 2"));
        documentService.routeDocument(maintDocument, null, null);
        List<? extends BusinessObject> searchResults = questionnaireLookupableHelperServiceImpl.getSearchResults(new HashMap());
        Assert.assertEquals(searchResults.size(), 2);
        // newer one will be at the top
        Questionnaire questionnaire = (Questionnaire)searchResults.get(0);
        Assert.assertEquals(questionnaire.getName(), "test2");
        Assert.assertEquals(questionnaire.getDescription(), "desc 2");
        Assert.assertEquals(questionnaire.getQuestionnaireQuestions().size(), 1);
        
        questionnaire = (Questionnaire)searchResults.get(1);
        Assert.assertEquals(questionnaire.getName(), "test1");
        Assert.assertEquals(questionnaire.getDescription(), "desc 1");
        Assert.assertEquals(questionnaire.getQuestionnaireQuestions().size(), 1);
    }
    
    
    /**
     * 
     * This method to test getCustomActionUrls for someone with "MODIFY_QUESTIONNAIRE" permission
     * should have edit/view/copy action links
     * @throws Exception
     */
    @Test 
    public void testCustomActionUrlWithVIEW_QUESTIONNAIRE() throws Exception {
        final QuestionnaireAuthorizationService questionnaireAuthorizationService = context.mock(QuestionnaireAuthorizationService.class);
        questionnaireLookupableHelperServiceImpl.setQuestionnaireAuthorizationService(questionnaireAuthorizationService);
        context.checking(new Expectations() {{
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE);
            will(returnValue(false));
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE);
            will(returnValue(true));
        }});
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test1", "desc 1"));
        documentService.routeDocument(maintDocument,null,null);
        // not sure why it is not persisted in DB.  also need to do this save, so getcustomactionurls can retrieve it with bos
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getBusinessObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        getBusinessObjectService().save((Questionnaire)maintDocument.getNewMaintainableObject().getBusinessObject());
        List pkNames = new ArrayList();
        pkNames.add("questionnaireRefId");
  
        List<HtmlData> htmldata = questionnaireLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);        
        Assert.assertEquals(htmldata.size(), 1);
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(0)).getHref(), "../kew/DocHandler.do?command=displayDocSearchView&readOnly=true&docId="+questionnaire.getDocumentNumber());
    }
    
    /**
     * 
     * This method to test getCustomActionUrls for someone with "VIEW_QUESTIONNAIRE" permission
     * should only have view action links
     * @throws Exception
     */
    @Test 
    public void testCustomActionUrlWithMODIFY_QUESTIONNAIRE() throws Exception {
        final QuestionnaireAuthorizationService questionnaireAuthorizationService = context.mock(QuestionnaireAuthorizationService.class);
        questionnaireLookupableHelperServiceImpl.setQuestionnaireAuthorizationService(questionnaireAuthorizationService);
        context.checking(new Expectations() {{
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE);
            will(returnValue(true));
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE);
            will(returnValue(true));
        }});
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test1", "desc 1"));
        documentService.routeDocument(maintDocument,null,null);
        // not sure why it is not persisted in DB.  also need to do this save, so getcustomactionurls can retrieve it with bos
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getBusinessObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        getBusinessObjectService().save((Questionnaire)maintDocument.getNewMaintainableObject().getBusinessObject());
        List pkNames = new ArrayList();
        pkNames.add("questionnaireRefId");
  
        List<HtmlData> htmldata = questionnaireLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);        
        Assert.assertEquals(htmldata.size(), 3);
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(0)).getHref(), "../maintenanceQn.do?businessObjectClassName=org.kuali.kra.questionnaire.Questionnaire&questionnaireRefId="+questionnaire.getQuestionnaireRefId()+"&methodToCall=edit");
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(1)).getHref(), "../kew/DocHandler.do?command=displayDocSearchView&readOnly=true&docId="+questionnaire.getDocumentNumber());
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(2)).getHref(), "../maintenanceQn.do?businessObjectClassName=org.kuali.kra.questionnaire.Questionnaire&questionnaireRefId="+questionnaire.getQuestionnaireRefId()+"&methodToCall=copy");
    }
    
    /**
     * 
     * This method to test getCustomActionUrls for someone with no permission to modify/view questionnaire.
     * @throws Exception
     */
    @Test 
    public void testCustomActionUrlWithNoPermission() throws Exception {
        final QuestionnaireAuthorizationService questionnaireAuthorizationService = context.mock(QuestionnaireAuthorizationService.class);
        questionnaireLookupableHelperServiceImpl.setQuestionnaireAuthorizationService(questionnaireAuthorizationService);
        context.checking(new Expectations() {{
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE);
            will(returnValue(false));
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE);
            will(returnValue(false));
        }});
  
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test1", "desc 1"));
        documentService.routeDocument(maintDocument,null,null);
        // not sure why it is not persisted in DB.  also need to do this save, so getcustomactionurls can retrieve it with bos
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getBusinessObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        getBusinessObjectService().save((Questionnaire)maintDocument.getNewMaintainableObject().getBusinessObject());
        List pkNames = new ArrayList();
        pkNames.add("questionnaireRefId");
  
        List<HtmlData> htmldata = questionnaireLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);        
        Assert.assertEquals(htmldata.size(), 0);
    }

    
    /**
     * 
     * This method to create questionnaire for maintenance document manipulation
     * @param name
     * @param desc
     * @return
     */
    private Questionnaire createQuestionnaire(String name, String desc) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName(name);
        questionnaire.setDescription(desc);
        questionnaire.setSequenceNumber(1);

        QuestionnaireQuestion q1 = new QuestionnaireQuestion();
        q1.setParentQuestionNumber(0);
        q1.setQuestionNumber(1);
        q1.setQuestionRefIdFk(1L);
        q1.setQuestionSeqNumber(1);
        List<QuestionnaireQuestion> questions = new ArrayList<QuestionnaireQuestion>();
        questions.add(q1);
        questionnaire.setQuestionnaireQuestions(questions);
        
        return questionnaire;
    }
}
