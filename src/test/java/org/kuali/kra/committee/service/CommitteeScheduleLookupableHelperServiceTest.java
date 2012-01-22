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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.lookup.CommitteeScheduleLookupableHelperServiceImpl;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

public class CommitteeScheduleLookupableHelperServiceTest extends KcUnitTestBase {
    private static final String COMMITTEE_ID = "committeeId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    
    private static final Long C1_LATEST_PK = 1L;
    private static final Long C2_LATEST_PK = 2L;
    private static final Long C3_LATEST_PK = 3L;
    private static final Long C4_LATEST_PK = 4L;
    private static final Long C1_OLD_PK = 11L;
    private static final Long C2_OLD_PK = 22L;
    private static final Long C3_OLD_PK = 33L;
    private static final Long C3_OLDER_PK = 333L;
    
    
    private static final String EDIT_URL = "../meetingManagement.do?scheduleId=102&methodToCall=start&readOnly=false";
    private static final String VIEW_URL = "../meetingManagement.do?scheduleId=102&methodToCall=start&readOnly=true";
    private static final String COMMITTEE_INQ_URL ="inquiry.do?businessObjectClassName=org.kuali.kra.committee.bo.Committee&methodToCall=start&id=103";
    
    private CommitteeScheduleLookupableHelperServiceImpl service;
    
    private Mockery context = new JUnit4Mockery();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        service = new CommitteeScheduleLookupableHelperServiceImpl();
        service.setBusinessObjectClass(CommitteeSchedule.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        
        service = null;
        GlobalVariables.setUserSession(null);
    }

    /**
     * Check if 'edit'/'view' urls are set up properly for 'committeeId'.
     */
    @Test
    public void testGetCustomActionUrl() {
        service.setTaskAuthorizationService(getMockTaskAuthorizationService());
        List<HtmlData> actionUrls = service.getCustomActionUrls(initCommitteeSchedule(), Collections.singletonList("committeeId"));
        assertEquals(2, actionUrls.size());
        assertTrue(actionUrls.get(0).getDisplayText().equals("edit"));
        assertEquals(((HtmlData.AnchorHtmlData) actionUrls.get(0)).getHref(), EDIT_URL);                
        assertTrue(actionUrls.get(1).getDisplayText().equals("view"));
        assertEquals(((HtmlData.AnchorHtmlData) actionUrls.get(1)).getHref(), VIEW_URL);                
    
        // need this because we are not using @RunWith(JMock.class)
        context.assertIsSatisfied();
    }
    
    private TaskAuthorizationService getMockTaskAuthorizationService() {
        final TaskAuthorizationService service = context.mock(TaskAuthorizationService.class);
        
        context.checking(new Expectations() {{
            one(service).isAuthorized(with(any(String.class)), with(any(Task.class)));
            will(returnValue(true));
        }});
        
        return service;
    }

   
    
    /**
     * This method unit tests a private method using reflection, usually we would unit test only 
     * the public contract of a class, but in this case it seems important to unit test a helper private 
     * method, and there does not seem to be an easy way to test it via the methods in the public contract.
     * 
     * @throws Exception
     */
    @Test
    public void testIsCurrentVersion() throws Exception {
        // create 4 committees as follows:
        // two with two versions, 
        // one with three versions, 
        // and one with one version
        
        // two versions
        Committee committee1 = new Committee();
        committee1.setCommitteeId("c1");
        committee1.setId(C1_LATEST_PK);
        
        Committee committee1Old = new Committee();
        committee1Old.setCommitteeId("c1");
        committee1Old.setId(C1_OLD_PK);
        
        final List<Committee> c1Versionslist = new ArrayList<Committee>();
        c1Versionslist.add(committee1);
        c1Versionslist.add(committee1Old);

        
        
        // two versions
        Committee committee2 = new Committee();
        committee2.setCommitteeId("c2");
        committee2.setId(C2_LATEST_PK);
        
        Committee committee2Old = new Committee();
        committee2Old.setCommitteeId("c2");
        committee2Old.setId(C2_OLD_PK);
        
        final List<Committee> c2Versionslist = new ArrayList<Committee>();
        c2Versionslist.add(committee2);
        c2Versionslist.add(committee2Old);
        
        
        
        // three versions
        Committee committee3 = new Committee();
        committee3.setCommitteeId("c3");
        committee3.setId(C3_LATEST_PK);
        
        Committee committee3Old = new Committee();
        committee3Old.setCommitteeId("c3");
        committee3Old.setId(C3_OLD_PK);
        
        Committee committee3Older = new Committee();
        committee3Older.setCommitteeId("c3");
        committee3Older.setId(C3_OLDER_PK);
        
        final List<Committee> c3Versionslist = new ArrayList<Committee>();
        c3Versionslist.add(committee3);
        c3Versionslist.add(committee3Old);
        c3Versionslist.add(committee3Older);
        
        
        
        // one version
        Committee committee4 = new Committee();
        committee4.setCommitteeId("c4");
        committee4.setId(C4_LATEST_PK);
        
        final List<Committee> c4Versionslist = new ArrayList<Committee>();
        c4Versionslist.add(committee4);        
   
        //matchers for parameters to BOService
        final HashMap<String, String> hm1 = new HashMap<String, String>();
        hm1.put(COMMITTEE_ID, "c1");        
        final HashMap<String, String> hm2 = new HashMap<String, String>();
        hm2.put(COMMITTEE_ID, "c2");
        final HashMap<String, String> hm3 = new HashMap<String, String>();
        hm3.put(COMMITTEE_ID, "c3");
        final HashMap<String, String> hm4 = new HashMap<String, String>();
        hm4.put(COMMITTEE_ID, "c4");
        
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            // exactly one invocation (and not more) because of the optimization obtained by maintaining active/inactive PK lists 
            oneOf(businessObjectService).findMatchingOrderBy(Committee.class, hm1, SEQUENCE_NUMBER, false);
            will(returnValue(c1Versionslist));
            
            oneOf(businessObjectService).findMatchingOrderBy(Committee.class, hm2, SEQUENCE_NUMBER, false);
            will(returnValue(c2Versionslist));
            
            oneOf(businessObjectService).findMatchingOrderBy(Committee.class, hm3, SEQUENCE_NUMBER, false);
            will(returnValue(c3Versionslist));
            
            oneOf(businessObjectService).findMatchingOrderBy(Committee.class, hm4, SEQUENCE_NUMBER, false);
            will(returnValue(c4Versionslist));
        }});        
        
        service = new CommitteeScheduleLookupableHelperServiceImpl();
        service.setBusinessObjectService(businessObjectService);
        
        Assert.assertTrue(invoke_isCurrentVersion(committee1));
        Assert.assertTrue(invoke_isCurrentVersion(committee2));
        Assert.assertTrue(invoke_isCurrentVersion(committee3));
        Assert.assertTrue(invoke_isCurrentVersion(committee4));
        
        Assert.assertFalse(invoke_isCurrentVersion(committee1Old));
        Assert.assertFalse(invoke_isCurrentVersion(committee2Old));
        Assert.assertFalse(invoke_isCurrentVersion(committee3Old));
        Assert.assertFalse(invoke_isCurrentVersion(committee3Older));
        
        // need this because we are not using @RunWith(JMock.class)
        context.assertIsSatisfied();
    }

    private boolean invoke_isCurrentVersion(Committee committee) throws Exception {      
        Method m = service.getClass().getDeclaredMethod("isCurrentVersion", Committee.class);
        m.setAccessible(true);       
        return (Boolean) m.invoke(service, committee);    
    }
    
    
    
    /**
     * Check if inquiry url is set up properly for 'committeeId'.
     */
    @Test
    public void testGetInquiryUrl() {
        HtmlData inquiryUrl = service.getInquiryUrl(initCommitteeSchedule(), "committee.committeeId");
        assertEquals(((HtmlData.AnchorHtmlData) inquiryUrl).getHref(), COMMITTEE_INQ_URL);
        // need this because we are not using @RunWith(JMock.class)
        context.assertIsSatisfied();
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