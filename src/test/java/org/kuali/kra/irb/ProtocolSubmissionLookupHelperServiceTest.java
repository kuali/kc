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
package org.kuali.kra.irb;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

public class ProtocolSubmissionLookupHelperServiceTest extends KcUnitTestBase {
    
    private static final String EDIT_URL ="../protocolProtocol.do?viewDocument=false&docId=101&submissionId=102&docTypeName=ProtocolDocument&methodToCall=docHandler&command=displayDocSearchView";
    private static final String VIEW_URL ="../protocolProtocol.do?viewDocument=true&docId=101&submissionId=102&docTypeName=ProtocolDocument&methodToCall=docHandler&command=displayDocSearchView";
    private static final String PROTOCOL_INQ_URL ="inquiry.do?businessObjectClassName=org.kuali.kra.irb.Protocol&methodToCall=start&protocolId=104";
    private static final String COMMITTEE_INQ_URL ="inquiry.do?businessObjectClassName=org.kuali.kra.committee.bo.Committee&methodToCall=start&id=103";
    private static final String PERSON_INQ_URL ="inquiry.do?businessObjectClassName=org.kuali.kra.bo.KcPerson&personId=10000000001&methodToCall=start";
    private static final String ROLODEX_INQ_URL ="inquiry.do?businessObjectClassName=org.kuali.kra.bo.Rolodex&rolodexId=1727&methodToCall=start";
    
    private ProtocolSubmissionLookupableHelperServiceImpl protocolSubmissionLookupableHelperServiceImpl;
    
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        protocolSubmissionLookupableHelperServiceImpl = new ProtocolSubmissionLookupableHelperServiceImpl();
        protocolSubmissionLookupableHelperServiceImpl.setBusinessObjectClass(ProtocolSubmission.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
   }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        
        protocolSubmissionLookupableHelperServiceImpl = null;
        GlobalVariables.setUserSession(null);
    }

    
    /**
     * 
     * This method to check the 'edit'/'view' links is correct
     */
    @Test
    public void testGetCustomActionUrls() {
 
        List pkNames = new ArrayList();
        pkNames.add("protocolId");
        final ProtocolSubmission protocolSubmission = initProtocolSubmission();

        final KraAuthorizationService kraAuthorizationService = context.mock(KraAuthorizationService.class);
        final String principalId = GlobalVariables.getUserSession().getPrincipalId();
        context.checking(new Expectations() {{
            one(kraAuthorizationService).hasPermission(principalId, protocolSubmission.getProtocol(), PermissionConstants.MODIFY_PROTOCOL);
            will(returnValue(true));
            one(kraAuthorizationService).hasPermission(principalId, protocolSubmission.getProtocol(), PermissionConstants.VIEW_PROTOCOL);
            will(returnValue(true));
        }});
        protocolSubmissionLookupableHelperServiceImpl.setKraAuthorizationService(kraAuthorizationService);

        
        List<HtmlData> actionUrls = protocolSubmissionLookupableHelperServiceImpl.getCustomActionUrls(protocolSubmission,pkNames);
        assertTrue(actionUrls.size()==2);
        assertTrue(actionUrls.get(0).getDisplayText().equals("edit"));
        assertEquals(((HtmlData.AnchorHtmlData) actionUrls.get(0)).getHref(), EDIT_URL);                
        assertTrue(actionUrls.get(1).getDisplayText().equals("view"));
        assertEquals(((HtmlData.AnchorHtmlData) actionUrls.get(1)).getHref(), VIEW_URL);                
    }
        
    /**
     * 
     * This method to test the manually created inquiry url is ok.
     */
    @Test
    public void testGetInquiryUrl() {
        final ProtocolSubmission protocolSubmission = initProtocolSubmission();
        
        final KcPersonService kcPersonService = context.mock(KcPersonService.class);
        final String principalId = GlobalVariables.getUserSession().getPrincipalId();
        context.checking(new Expectations() {{
            one(kcPersonService).getKcPersonByPersonId(principalId);
            will(returnValue(KcPerson.fromPersonId(principalId)));
        }});
        protocolSubmissionLookupableHelperServiceImpl.setKcPersonService(kcPersonService);
        
        HtmlData inquiryUrl = protocolSubmissionLookupableHelperServiceImpl.getInquiryUrl(protocolSubmission, "protocolNumber");
        assertEquals(((HtmlData.AnchorHtmlData) inquiryUrl).getHref(), PROTOCOL_INQ_URL);
        inquiryUrl = protocolSubmissionLookupableHelperServiceImpl.getInquiryUrl(protocolSubmission, "committeeId");
        assertEquals(((HtmlData.AnchorHtmlData) inquiryUrl).getHref(), COMMITTEE_INQ_URL);
        inquiryUrl = protocolSubmissionLookupableHelperServiceImpl.getInquiryUrl(protocolSubmission, "piName");
        assertEquals(((HtmlData.AnchorHtmlData) inquiryUrl).getHref(), PERSON_INQ_URL);
        ProtocolPerson protocolPerson = protocolSubmission.getProtocol().getProtocolPersons().get(0);
        protocolPerson.setPersonId("");
        protocolPerson.setRolodexId(new Integer(1727));
        protocolSubmission.getProtocol().getProtocolPersons().clear();
        protocolSubmission.getProtocol().getProtocolPersons().add(protocolPerson);
        inquiryUrl = protocolSubmissionLookupableHelperServiceImpl.getInquiryUrl(protocolSubmission, "piName");
        assertEquals(((HtmlData.AnchorHtmlData) inquiryUrl).getHref(), ROLODEX_INQ_URL);
    }
    
    private ProtocolSubmission initProtocolSubmission() {
        Protocol protocol = new Protocol();
        protocol.setLeadUnitNumber("000001");
        protocol.setProtocolId(104L);
        protocol.setPrincipalInvestigatorId("10000000001");
        ProtocolPerson protocolPerson = new ProtocolPerson();
        protocolPerson.setPersonId("10000000001");
        protocol.getProtocolPersons().add(protocolPerson);
        protocolPerson.setProtocolPersonRoleId("PI");
        protocol.setProtocolNumber("100");
        ProtocolDocument protocolDocument = new ProtocolDocument();
        protocolDocument.setDocumentNumber("101");
        protocol.setProtocolDocument(protocolDocument);
        ProtocolSubmission protocolSubmission = new ProtocolSubmission();
        List<ProtocolSubmission> submissions = new ArrayList<ProtocolSubmission>();
        protocolSubmission.setSubmissionId(102L);
        Committee committee = new Committee();
        committee.setCommitteeId("100");
        CommitteeDocument document = new CommitteeDocument();
        document.setDocumentNumber("101");
        committee.setCommitteeDocument(document);
        committee.setId(103L);
        protocolSubmission.setCommittee(committee);
        submissions.add(protocolSubmission);
        protocol.setProtocolSubmissions(submissions);
        protocolSubmission.setProtocol(protocol);

        return protocolSubmission;
        
    }

}
