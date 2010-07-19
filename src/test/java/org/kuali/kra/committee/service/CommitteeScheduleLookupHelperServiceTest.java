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
package org.kuali.kra.committee.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.lookup.CommitteeScheduleLookupableHelperServiceImpl;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.util.GlobalVariables;

public class CommitteeScheduleLookupHelperServiceTest extends KcUnitTestBase {
    private static final String EDIT_URL = "../meetingManagement.do?scheduleId=102&methodToCall=start&readOnly=false";
    private static final String VIEW_URL = "../meetingManagement.do?scheduleId=102&methodToCall=start&readOnly=true";
    private static final String COMMITTEE_INQ_URL ="inquiry.do?businessObjectClassName=org.kuali.kra.committee.bo.Committee&methodToCall=start&id=103";
    CommitteeScheduleLookupableHelperServiceImpl committeeScheduleLookupableHelperServiceImpl;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        committeeScheduleLookupableHelperServiceImpl = (CommitteeScheduleLookupableHelperServiceImpl) KraServiceLocator
                .getService("committeeScheduleLookupableHelperService");
        committeeScheduleLookupableHelperServiceImpl.setBusinessObjectClass(CommitteeSchedule.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        committeeScheduleLookupableHelperServiceImpl = null;
        GlobalVariables.setUserSession(null);
    }

    /**
     * 
     * This method to check the 'edit'/'view' links is correct
     */
    @Test
    public void testGetCustomActionUrl() {
        List pkNames = new ArrayList();
        pkNames.add("committeeId");
        // tried to use mock for taskAuthorizationService, but it did not work out

        List<HtmlData> actionUrls = committeeScheduleLookupableHelperServiceImpl.getCustomActionUrls(initCommitteeSchedule(),pkNames);
        assertTrue(actionUrls.size()==2);
        assertTrue(actionUrls.get(0).getDisplayText().equals("edit"));
        assertEquals(((HtmlData.AnchorHtmlData) actionUrls.get(0)).getHref(), EDIT_URL);                
        assertTrue(actionUrls.get(1).getDisplayText().equals("view"));
        assertEquals(((HtmlData.AnchorHtmlData) actionUrls.get(1)).getHref(), VIEW_URL);                
    
    }

    /**
     * 
     * This method is to test if inquiry url is set up properly for 'committeeId'
     */
    @Test
    public void testGetInquiryUrl() {
        HtmlData inquiryUrl = committeeScheduleLookupableHelperServiceImpl.getInquiryUrl(initCommitteeSchedule(), "committee.committeeId");
        assertEquals(((HtmlData.AnchorHtmlData) inquiryUrl).getHref(), COMMITTEE_INQ_URL);
    }

    private CommitteeSchedule initCommitteeSchedule() {
        Committee committee = new Committee();
        committee.setId(103L);
        committee.setCommitteeId("100");
        committee.setHomeUnitNumber("000001");
        CommitteeDocument document = new CommitteeDocument();
        document.setDocumentNumber("101");
        committee.setCommitteeDocument(document);
        final CommitteeSchedule committeeSchedule = new CommitteeSchedule();
        committeeSchedule.setId(102L);
        committeeSchedule.setCommittee(committee);
        return committeeSchedule;

    }
}
