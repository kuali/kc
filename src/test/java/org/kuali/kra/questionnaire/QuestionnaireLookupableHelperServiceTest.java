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
package org.kuali.kra.questionnaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
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

public class QuestionnaireLookupableHelperServiceTest extends KcUnitTestBase {

    private QuestionnaireLookupableHelperServiceImpl questionnaireLookupableHelperServiceImpl;
    private DocumentService documentService;
    private Mockery context = new JUnit4Mockery();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        questionnaireLookupableHelperServiceImpl = new QuestionnaireLookupableHelperServiceImpl();
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
        List<Questionnaire> searchResults = (List<Questionnaire>) questionnaireLookupableHelperServiceImpl.getSearchResults(new HashMap());
        assertEquals(9, searchResults.size());
        
        Questionnaire test1 = null;
        Questionnaire test2 = null;
        for (Questionnaire questionnaire : searchResults) {
            if (StringUtils.equals(questionnaire.getName(), "test1")) {
                test1 = questionnaire;
            } else if (StringUtils.equals(questionnaire.getName(), "test2")) {
                test2 = questionnaire;
            }
        }
        
        assertNotNull(test1);
        assertEquals(test1.getName(), "test1");
        assertEquals(test1.getDescription(), "desc 1");
        assertEquals(0, test1.getQuestionnaireQuestions().size());

        assertNotNull(test2);
        assertEquals(test2.getName(), "test2");
        assertEquals(test2.getDescription(), "desc 2");
        assertEquals(0, test2.getQuestionnaireQuestions().size());
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
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getDataObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        getBusinessObjectService().save((Questionnaire)maintDocument.getNewMaintainableObject().getDataObject());
        List pkNames = new ArrayList();
        pkNames.add("questionnaireRefId");
  
        List<HtmlData> htmldata = questionnaireLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);        
        Assert.assertEquals(htmldata.size(), 1);
        Assert.assertTrue(((AnchorHtmlData)htmldata.get(0)).getHref().contains("/kew/DocHandler.do?command=displayDocSearchView&readOnly=true&docId="+questionnaire.getDocumentNumber()));
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
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getDataObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        getBusinessObjectService().save((Questionnaire)maintDocument.getNewMaintainableObject().getDataObject());
        List pkNames = new ArrayList();
        pkNames.add("questionnaireRefId");
  
        List<HtmlData> htmldata = questionnaireLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);        
        Assert.assertEquals(htmldata.size(), 3);
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(0)).getHref(), "../maintenanceQn.do?businessObjectClassName=org.kuali.kra.questionnaire.Questionnaire&questionnaireRefId="+questionnaire.getQuestionnaireRefId()+"&methodToCall=edit");
        Assert.assertTrue(((AnchorHtmlData)htmldata.get(1)).getHref().contains("/kew/DocHandler.do?command=displayDocSearchView&readOnly=true&docId="+questionnaire.getDocumentNumber()));
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
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getDataObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        getBusinessObjectService().save((Questionnaire)maintDocument.getNewMaintainableObject().getDataObject());
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
        
        /**
         * @ToDo get QuestionnaireQuestion working
         */
        
        /*
        QuestionnaireQuestion q1 = new QuestionnaireQuestion();
        q1.setParentQuestionNumber(0);
        q1.setQuestionNumber(1);
        q1.setQuestionRefIdFk(1L);
        q1.setQuestionSeqNumber(1);
        List<QuestionnaireQuestion> questions = new ArrayList<QuestionnaireQuestion>();
        questions.add(q1);
        questionnaire.setQuestionnaireQuestions(questions);
        */
        return questionnaire;
    }
}
