/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.protocol.ProtocolLookupHelperServiceTestBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class ProtocolSubmissionLookupHelperServiceTest extends ProtocolLookupHelperServiceTestBase {
    
    private static final String EDIT_URL ="../protocolProtocol.do?viewDocument=false&docId=101&submissionId=102&docTypeName=ProtocolDocument&methodToCall=docHandler&command=displayDocSearchView";
    private static final String VIEW_URL ="../protocolProtocol.do?viewDocument=true&docId=101&submissionId=102&docTypeName=ProtocolDocument&methodToCall=docHandler&command=displayDocSearchView";
    private static final String PROTOCOL_INQ_URL ="inquiry.do?businessObjectClassName=" + Protocol.class.getName() + "&methodToCall=start&protocolId=104";
    private static final String COMMITTEE_INQ_URL ="inquiry.do?businessObjectClassName=" + Committee.class.getName() + "&methodToCall=start&id=103";

    private ProtocolSubmissionLookupableHelperServiceImpl protocolSubmissionLookupableHelperServiceImpl;
    
    @Before
    public void setUp() throws Exception {
        protocolSubmissionLookupableHelperServiceImpl = new ProtocolSubmissionLookupableHelperServiceImpl();
        protocolSubmissionLookupableHelperServiceImpl.setBusinessObjectClass(ProtocolSubmission.class);
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        protocolSubmissionLookupableHelperServiceImpl = null;
        super.tearDown();
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

        final KcAuthorizationService kraAuthorizationService = context.mock(KcAuthorizationService.class);
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
        ProtocolPerson protocolPerson = (ProtocolPerson) protocolSubmission.getProtocol().getProtocolPersons().get(0);
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
        List<ProtocolSubmissionBase> submissions = new ArrayList<ProtocolSubmissionBase>();
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
