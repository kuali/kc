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
package org.kuali.kra.service.impl;




import java.sql.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonRoleService;
import org.kuali.rice.KNSServiceLocator;
import java.util.*;

public class ProposalPersonRoleServiceImplTest extends KraTestBase {
    private DocumentService documentService = null;
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

    @Test public void testProposalPersonRole() throws Exception {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
       
         Date requestedStartDateInitial = new Date(System.currentTimeMillis());
         Date requestedEndDateInitial = new Date(System.currentTimeMillis());
         List<ProposalPerson> ProposalPersons= new ArrayList<ProposalPerson>();
         
         
         
         ProposalPerson person = new ProposalPerson();
         person.setProposalNumber("7");
         person.setProposalPersonNumber(1);
         person.setProposalPersonRoleId("PI");
         person.setPersonId("000000001");
         
         
         ProposalPerson person1 = new ProposalPerson();
         person1.setProposalNumber("7");
         person1.setProposalPersonNumber(2);
         person1.setProposalPersonRoleId("COI");
         person1.setRolodexId(1733);
         
         
         ProposalPerson person2 = new ProposalPerson();
         person2.setProposalNumber("7");
         person2.setProposalPersonNumber(3);
         person2.setProposalPersonRoleId("COI");
         person2.setPersonId("000000002");
         
         ProposalPerson person3= new ProposalPerson();
         person3.setProposalNumber("7");
         person3.setProposalPersonNumber(5);
         person3.setProposalPersonRoleId("KP");
         person3.setPersonId("000000005");
         
         
         ProposalPerson person4= new ProposalPerson();
         person4.setProposalNumber("7");
         person4.setProposalPersonNumber(4);
         person4.setProposalPersonRoleId("KP");
         person4.setRolodexId(1742);
         
         ProposalPersons.add(person);
         ProposalPersons.add(person1);
         ProposalPersons.add(person2);
         ProposalPersons.add(person3);
         ProposalPersons.add(person4);
         
         setBaseDocumentFields(document, "ProposalDevelopmentDocumentTest test doc", "005770", "project title", requestedStartDateInitial, requestedEndDateInitial, "1", "1", "000001");
         document.setProposalPersons(ProposalPersons);
         documentService.saveDocument(document);
         ProposalPersonRoleService prs = getService(ProposalPersonRoleService.class);
         String documentid=document.getDocumentHeader().getDocumentNumber();
         List<String> coinvestigator=prs.getProposalCoInvestigators(documentid);
         List<String> proposalinvestigator=prs.getProposalInvestigator(documentid);
         List<String> proposalinvestigators=prs.getProposalInvestigators(documentid);
         List<String> proposalpersons=prs.getProposalPersons(documentid);
         List<String> keypersons=prs.getProposalKeyPersons(documentid);
         assertEquals(proposalinvestigator.get(0),person.getPersonId());
         assertEquals(coinvestigator.get(0),person1.getRolodexId().toString());
         assertEquals(coinvestigator.get(1),person2.getPersonId());
         assertEquals(proposalinvestigators.get(0),person.getPersonId());
         assertEquals(proposalinvestigators.get(1),person1.getRolodexId().toString());
         assertEquals(proposalinvestigators.get(2),person2.getPersonId());
         assertEquals(proposalpersons.get(0),person.getPersonId());
         assertEquals(proposalpersons.get(1),person1.getRolodexId().toString());
         assertEquals(proposalpersons.get(2),person2.getPersonId());
         assertEquals(proposalpersons.get(3),person3.getPersonId());
         assertEquals(proposalpersons.get(4),person4.getRolodexId().toString());
         assertEquals(keypersons.get(0),person3.getPersonId());
         assertEquals(keypersons.get(1),person4.getRolodexId().toString());
         
    }
    
    
    
    
    
    private void setBaseDocumentFields(ProposalDevelopmentDocument document, String description, String sponsorCode, String title, Date requestedStartDateInitial, Date requestedEndDateInitial, String activityTypeCode, String proposalTypeCode, String ownedByUnit) {
        document.getDocumentHeader().setFinancialDocumentDescription(description);
        document.setSponsorCode(sponsorCode);
        document.setTitle(title);
        document.setRequestedStartDateInitial(requestedStartDateInitial);
        document.setRequestedEndDateInitial(requestedEndDateInitial);
        document.setActivityTypeCode(activityTypeCode);
        document.setProposalTypeCode(proposalTypeCode);
        document.setOwnedByUnitNumber(ownedByUnit);
    }
 
    
}





