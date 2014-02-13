/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.List;

public class VersionHistoryServiceImplTest extends KcIntegrationTestBase {
    private static final String PROTOCOL_VERSION_NAME = "2001";
    private static final String AWARD_VERSION_NAME = "1001-001";
    private VersionHistoryService versioningHistoryService;
    
    @Before
    public void setUp() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        versioningHistoryService = KcServiceLocator.getService(VersionHistoryService.class);
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testSavingAVersionHistory() {
        Award award = createAward(AWARD_VERSION_NAME, 1);
        VersionHistory vh = versioningHistoryService.updateVersionHistory(award, VersionStatus.ACTIVE, "jtester");
        Assert.assertNotNull("VersionHistory was null", vh);
    }

    @Test
    public void testSavingVersionHistories() {
        versioningHistoryService.updateVersionHistory(createAward(AWARD_VERSION_NAME, 1), VersionStatus.ACTIVE, "u1");
        versioningHistoryService.updateVersionHistory(createProtocol(PROTOCOL_VERSION_NAME, 1), VersionStatus.ACTIVE, "u2");
        
        List<VersionHistory> list = versioningHistoryService.loadVersionHistory(Award.class, AWARD_VERSION_NAME); 
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(VersionStatus.ACTIVE, list.get(0).getStatus());
        
        list = versioningHistoryService.loadVersionHistory(Protocol.class, PROTOCOL_VERSION_NAME); 
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(VersionStatus.ACTIVE, list.get(0).getStatus());
        
        VersionHistory activeVersion = versioningHistoryService.findActiveVersion(Award.class, AWARD_VERSION_NAME);
        Assert.assertEquals("u1", activeVersion.getUserId());
        
        activeVersion = versioningHistoryService.findActiveVersion(Protocol.class, PROTOCOL_VERSION_NAME);
        Assert.assertEquals("u2", activeVersion.getUserId());        
    }
    
    private Award createAward(String versionName, Integer versionNumber) {
        Award award = new Award();
        award.setAwardNumber(versionName);
        award.setSequenceNumber(versionNumber);
        return award;
    }
    
    private Protocol createProtocol(String versionName, Integer versionNumber) {
        Protocol protocol = new Protocol();
        protocol.setProtocolNumber(versionName);
        protocol.setSequenceNumber(versionNumber);
        return protocol;
    }
}
