/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.workflow.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.ProposalDevelopmentWebTestBase;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class UserRoleBoundWebTest extends ProposalDevelopmentWebTestBase {
    
    private static final String USERNAME_FIELD_ID = "newProposalUser.username";
    private static final String ROLENAME_FIELD_ID = "newProposalUser.roleName";
    private static final String ADD_BTN_ID = "methodToCall.addProposalUser";

    @Before
   public void setUp() throws Exception {
       super.setUp();
       GlobalVariables.setUserSession(new UserSession("quickstart"));
       documentService = KNSServiceLocator.getDocumentService();
   }  

   @After
   public void tearDown() throws Exception {
       super.tearDown();
       GlobalVariables.setErrorMap(new ErrorMap());
       stopLifecycles(this.perTestLifeCycles);
       }

   @Test
   public void testuserrolebound() throws Exception {
       
       HtmlPage proposaldevelopmentPage = getProposalDevelopmentPage();
       setDefaultRequiredFields(proposaldevelopmentPage);
       HtmlForm proposalForm = (HtmlForm) proposaldevelopmentPage.getForms().get(0);
       final HtmlHiddenInput documentNumber = (HtmlHiddenInput) proposalForm.getInputByName("document.documentHeader.documentNumber");
       HtmlPage permissionsPage = clickOnTab(proposaldevelopmentPage, PERMISSIONS_LINK_NAME);
       permissionsPage = addUser(permissionsPage, "jtester", "Viewer");
       permissionsPage=addUser(permissionsPage, "tdurkin", "Aggregator");
       permissionsPage=addUser(permissionsPage, "aslusar", "Budget Creator");
       permissionsPage=addUser(permissionsPage, "bhutchin", "Narrative Writer");
       HtmlPage keyPersonnelPage = clickOnTab(permissionsPage, KEY_PERSONNEL_LINK_NAME);
       ProposalDevelopmentDocument savedDocument = (ProposalDevelopmentDocument) documentService
       .getByDocumentHeaderId(documentNumber.getDefaultValue());
       assertNotNull(savedDocument);
       DocumentRouteHeaderValue routeHeader = KEWServiceLocator.getRouteHeaderService().getRouteHeader(savedDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
       RouteContext routecontext=RouteContext.createNewRouteContext();  
       routecontext.setDocument(routeHeader);
       String doccontent=routecontext.getDocumentContent().getDocContent();
       assertTrue(doccontent.contains("jtester"));
       assertTrue(doccontent.contains("tdurkin"));
       assertTrue(doccontent.contains("aslusar"));
       assertTrue(doccontent.contains("bhutchin"));
       
       
   }
   
   private HtmlPage addUser(HtmlPage page, String username, String roleName) throws Exception {
       setFieldValue(page, USERNAME_FIELD_ID, username);
       setFieldValue(page, ROLENAME_FIELD_ID, roleName);
       HtmlElement addBtn = getElementByName(page, ADD_BTN_ID, true);
       return clickOn(addBtn);
   }
}
