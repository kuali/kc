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
package org.kuali.kra.questionnaire;

import org.apache.commons.lang3.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.*;
import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireAuthorizationService;
import org.kuali.coeus.common.questionnaire.impl.core.QuestionnaireLookupableHelperServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class QuestionnaireLookupableHelperServiceTest extends KcIntegrationTestBase {

    private LookupableHelperService questionnaireLookupableHelperServiceImpl;
    private DocumentService documentService;
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};

    @Before
    public void setUp() throws Exception {
        questionnaireLookupableHelperServiceImpl = KcServiceLocator.getService("questionnaireLookupableHelperService");
        questionnaireLookupableHelperServiceImpl.setBusinessObjectClass(Questionnaire.class);
        documentService = KcServiceLocator.getService(DocumentService.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
   }

    @After
    public void tearDown() throws Exception {
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
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test1", "desc 1"));
        maintDocument.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        documentService.routeDocument(maintDocument, null, null);
        
        // 2nd document
        maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 2"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test2", "desc 2"));
        maintDocument.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        documentService.routeDocument(maintDocument, null, null);
        List<Questionnaire> searchResults = (List<Questionnaire>) questionnaireLookupableHelperServiceImpl.getSearchResults(new HashMap());
        assertEquals(21 , searchResults.size());
        
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
    public void testCustomActionUrlWithVIEW_QUESTIONNAIRE() throws Throwable {
        final QuestionnaireAuthorizationService questionnaireAuthorizationService = context.mock(QuestionnaireAuthorizationService.class);
        Method m = QuestionnaireLookupableHelperServiceImpl.class.getMethod("setQuestionnaireAuthorizationService", QuestionnaireAuthorizationService.class);
        Proxy.getInvocationHandler(questionnaireLookupableHelperServiceImpl).invoke(questionnaireLookupableHelperServiceImpl, m, new Object[] {questionnaireAuthorizationService});

        context.checking(new Expectations() {{
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE);
            will(returnValue(false));
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE);
            will(returnValue(true));
        }});
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test1", "desc 1"));
        maintDocument.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        documentService.routeDocument(maintDocument,null,null);
        // not sure why it is not persisted in DB.  also need to do this save, so getcustomactionurls can retrieve it with bos
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getDataObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        KNSServiceLocator.getBusinessObjectService().save((Questionnaire)maintDocument.getNewMaintainableObject().getDataObject());
        List pkNames = new ArrayList();
        pkNames.add("id");
  
        List<HtmlData> htmldata = questionnaireLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);        
        Assert.assertEquals(htmldata.size(), 1);
        Assert.assertEquals("../maintenanceQn.do?businessObjectClassName=" + Questionnaire.class.getName() +"&methodToCall=edit" + "&id="+questionnaire.getId() + "&readOnly=true", ((AnchorHtmlData)htmldata.get(0)).getHref(), ((AnchorHtmlData)htmldata.get(0)).getHref());
    }
    
    /**
     * 
     * This method to test getCustomActionUrls for someone with "VIEW_QUESTIONNAIRE" permission
     * should only have view action links
     * @throws Exception
     */
    @Test
    public void testCustomActionUrlWithMODIFY_QUESTIONNAIRE() throws Throwable {
        final QuestionnaireAuthorizationService questionnaireAuthorizationService = context.mock(QuestionnaireAuthorizationService.class);
        Method m = QuestionnaireLookupableHelperServiceImpl.class.getMethod("setQuestionnaireAuthorizationService", QuestionnaireAuthorizationService.class);
        Proxy.getInvocationHandler(questionnaireLookupableHelperServiceImpl).invoke(questionnaireLookupableHelperServiceImpl, m, new Object[] {questionnaireAuthorizationService});

        context.checking(new Expectations() {{
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE);
            will(returnValue(true));
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE);
            will(returnValue(true));
        }});
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test1", "desc 1"));
        maintDocument.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        documentService.routeDocument(maintDocument,null,null);
        // not sure why it is not persisted in DB.  also need to do this save, so getcustomactionurls can retrieve it with bos
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getDataObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        KNSServiceLocator.getBusinessObjectService().save((Questionnaire)maintDocument.getNewMaintainableObject().getDataObject());
        List pkNames = new ArrayList();
        pkNames.add("id");
  
        List<HtmlData> htmldata = questionnaireLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);        
        Assert.assertEquals(4, htmldata.size());
        Assert.assertEquals("../maintenanceQn.do?businessObjectClassName=" + Questionnaire.class.getName() +"&methodToCall=edit" + "&id="+questionnaire.getId(), ((AnchorHtmlData)htmldata.get(0)).getHref(), ((AnchorHtmlData)htmldata.get(0)).getHref());
        Assert.assertEquals("../maintenanceQn.do?businessObjectClassName=" + Questionnaire.class.getName() +"&methodToCall=edit" + "&id="+questionnaire.getId() + "&readOnly=true", ((AnchorHtmlData)htmldata.get(1)).getHref(), ((AnchorHtmlData)htmldata.get(1)).getHref());
        Assert.assertEquals("../maintenanceQn.do?businessObjectClassName=" + Questionnaire.class.getName() + "&methodToCall=copy" + "&id="+questionnaire.getId(), ((AnchorHtmlData)htmldata.get(2)).getHref(), ((AnchorHtmlData)htmldata.get(2)).getHref());
    }

    /**
     * 
     * This method to test getCustomActionUrls for someone with no permission to modify/view questionnaire.
     * @throws Exception
     */
    @Test
    public void testCustomActionUrlWithNoPermission() throws Throwable {
        final QuestionnaireAuthorizationService questionnaireAuthorizationService = context.mock(QuestionnaireAuthorizationService.class);
        Method m = QuestionnaireLookupableHelperServiceImpl.class.getMethod("setQuestionnaireAuthorizationService", QuestionnaireAuthorizationService.class);
        Proxy.getInvocationHandler(questionnaireLookupableHelperServiceImpl).invoke(questionnaireLookupableHelperServiceImpl, m, new Object[] {questionnaireAuthorizationService});

        context.checking(new Expectations() {{
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTIONNAIRE);
            will(returnValue(false));
            one(questionnaireAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTIONNAIRE);
            will(returnValue(false));
        }});
  
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Questionnaire.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestionnaire("test1", "desc 1"));
        maintDocument.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        documentService.routeDocument(maintDocument,null,null);
        // not sure why it is not persisted in DB.  also need to do this save, so getcustomactionurls can retrieve it with bos
        Questionnaire questionnaire = (Questionnaire)maintDocument.getNewMaintainableObject().getDataObject();
        questionnaire.setDocumentNumber(maintDocument.getDocumentNumber());
        KNSServiceLocator.getBusinessObjectService().save((Questionnaire)maintDocument.getNewMaintainableObject().getDataObject());
        List pkNames = new ArrayList();
        pkNames.add("id");
  
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

        return questionnaire;
    }
}
