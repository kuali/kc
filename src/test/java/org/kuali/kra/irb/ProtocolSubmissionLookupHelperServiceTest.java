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
package org.kuali.kra.irb;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.util.GlobalVariables;

public class ProtocolSubmissionLookupHelperServiceTest extends KraTestBase {

    ProtocolSubmissionLookupableHelperServiceImpl protocolSubmissionLookupableHelperServiceImpl;
    private static final String EDIT_URL ="../protocolProtocol.do?viewDocument=false&docId=101&submissionId=102&docTypeName=ProtocolDocument&methodToCall=docHandler&command=displayDocSearchView";
    private static final String VIEW_URL ="../protocolProtocol.do?viewDocument=true&docId=101&submissionId=102&docTypeName=ProtocolDocument&methodToCall=docHandler&command=displayDocSearchView";
    private Mockery context = new JUnit4Mockery();
    @Before
    public void setUp() throws Exception {
        super.setUp();
        protocolSubmissionLookupableHelperServiceImpl = (ProtocolSubmissionLookupableHelperServiceImpl)KraServiceLocator.getService("protocolSubmissionLookupableHelperService");
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
        final Protocol protocol = new Protocol();
        protocol.setProtocolNumber("100");
        ProtocolDocument document = new ProtocolDocument();
        document.setDocumentNumber("101");
        protocol.setProtocolDocument(document);
        ProtocolSubmission protocolSubmission = new ProtocolSubmission();
        List<ProtocolSubmission> submissions = new ArrayList<ProtocolSubmission>();
        protocolSubmission.setSubmissionId(102L);
        submissions.add(protocolSubmission);
        protocol.setProtocolSubmissions(submissions);
        protocolSubmission.setProtocol(protocol);

        final KraAuthorizationService kraAuthorizationService = context.mock(KraAuthorizationService.class);
        context.checking(new Expectations() {{
            one(kraAuthorizationService).hasPermission("10000000000", protocol, PermissionConstants.MODIFY_PROTOCOL);
            will(returnValue(true));
            one(kraAuthorizationService).hasPermission("10000000000", protocol, PermissionConstants.VIEW_PROTOCOL);
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
        

}
