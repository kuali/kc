/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.workflow.attribute;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.workflow.ProposalPersonRoleAttribute;
import org.kuali.rice.KNSServiceLocator;
import edu.iu.uis.eden.Id;
import edu.iu.uis.eden.KEWServiceLocator;
import edu.iu.uis.eden.engine.RouteContext;
import edu.iu.uis.eden.routeheader.DocumentRouteHeaderValue;
import edu.iu.uis.eden.routetemplate.ResolvedQualifiedRole;
import edu.iu.uis.eden.routetemplate.Role;
import edu.iu.uis.eden.user.AuthenticationUserId;
public class ProposalPersonRoleAttributeTest extends KraTestBase{
    
    private DocumentService documentService = null;
    private static final Role PROPOSAL_INVESTIGATOR_ROLE = new Role(ProposalPersonRoleAttribute.class, "PROPOSALINVESTIGATOR", "Proposal Investigator");
    private static final Role CO_INVESTIGATOR_ROLE = new Role(ProposalPersonRoleAttribute.class, "COINVESTIGATOR", "Co-Investigator");
    private static final Role INVESTIGATORS = new Role(ProposalPersonRoleAttribute.class, "INVESTIGATORS", "Investigators");
    private static final Role KEY_PERSON_ROLE = new Role(ProposalPersonRoleAttribute.class, "KEYPERSON", "Key Person");
    private static final Role PROPOSAL_PERSON_ROLE = new Role(ProposalPersonRoleAttribute.class, "PROPOSALPERSONS", "Proposal Person");
    private BusinessObjectService bos;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        documentService = KNSServiceLocator.getDocumentService();
        bos = KraServiceLocator.getService(BusinessObjectService.class);
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        documentService = null;
        super.tearDown();
    }
    @Test
    public void proposalpersontest() throws Exception {
        ProposalDevelopmentDocument proposaldevelopmentdocument = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        setDocumentFields(proposaldevelopmentdocument);
       documentService.saveDocument(proposaldevelopmentdocument);
        DocumentRouteHeaderValue routeHeader = KEWServiceLocator.getRouteHeaderService().getRouteHeader(proposaldevelopmentdocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId());
        RouteContext routecontext=RouteContext.createNewRouteContext();  
        routecontext.setDocument(routeHeader);
        ProposalPersonRoleAttribute roleattribute=new ProposalPersonRoleAttribute();
        ResolvedQualifiedRole resolvedrole=roleattribute.resolveQualifiedRole(routecontext, PROPOSAL_INVESTIGATOR_ROLE.getName(), PROPOSAL_INVESTIGATOR_ROLE.getBaseName());
       
        for (Iterator<Id> ids = resolvedrole.getRecipients().iterator(); ids.hasNext();) {
            AuthenticationUserId authid = (AuthenticationUserId) ids.next();
            assertEquals(authid.getId(),"tdurkin");
        }

        ResolvedQualifiedRole resolvedrole1=roleattribute.resolveQualifiedRole(routecontext, CO_INVESTIGATOR_ROLE.getName(), CO_INVESTIGATOR_ROLE.getBaseName());
         for (Iterator<Id> ids = resolvedrole1.getRecipients().iterator(); ids.hasNext();) {
            AuthenticationUserId authid = (AuthenticationUserId) ids.next();
            assertEquals(authid.getId(),"jtester");
        }

    
    }

    private void setDocumentFields(ProposalDevelopmentDocument document) {
        Date requestedStartDateInitial = new Date(System.currentTimeMillis());
        Date requestedEndDateInitial = new Date(System.currentTimeMillis());
        List<ProposalPerson> ProposalPersons= new ArrayList<ProposalPerson>();

        ProposalPerson person = new ProposalPerson();
        person.setProposalNumber("7");
        person.setProposalPersonNumber(1);
        person.setProposalPersonRoleId("PI");
        person.setPersonId("000000001");
        person.setUserName("tdurkin");



        ProposalPerson person2 = new ProposalPerson();
        person2.setProposalNumber("7");
        person2.setProposalPersonNumber(3);
        person2.setProposalPersonRoleId("COI");
        person2.setPersonId("000000008");
        person2.setUserName("jtester");

        
        ProposalPersons.add(person);
       
        ProposalPersons.add(person2);
        
        
        document.setProposalPersons(ProposalPersons);
        document.getDocumentHeader().setFinancialDocumentDescription("ProposalDevelopmentDocumentTest test doc");
        document.setSponsorCode("005770");
        document.setTitle( "project title");
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode("1");
        document.setProposalTypeCode("1");
        document.setOwnedByUnitNumber("000001");
    }
}
