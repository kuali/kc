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
package org.kuali.kra.service.impl;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

public class VersionHistoryServiceImplTest extends KraTestBase {
    private static final String PROTOCOL_VERSION_NAME = "2001";
    private static final String AWARD_VERSION_NAME = "1001-001";
    private VersionHistoryService versioningHistoryService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        versioningHistoryService = KraServiceLocator.getService(VersionHistoryService.class);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testSavingAVersionHistory() {
        Award award = createAward(AWARD_VERSION_NAME, 1);
        VersionHistory vh = versioningHistoryService.createVersionHistory(award, VersionStatus.ACTIVE, "jtester");
        Assert.assertNotNull("VersionHistory was null", vh);
    }

    @Test
    public void testSavingVersionHistories() {
        versioningHistoryService.createVersionHistory(createAward(AWARD_VERSION_NAME, 1), VersionStatus.ACTIVE, "u1");
        versioningHistoryService.createVersionHistory(createProtocol(PROTOCOL_VERSION_NAME, 1), VersionStatus.ACTIVE, "u2");
        versioningHistoryService.createVersionHistory(createAward(AWARD_VERSION_NAME, 2), VersionStatus.ACTIVE, "u3");
        versioningHistoryService.createVersionHistory(createProtocol(PROTOCOL_VERSION_NAME, 2), VersionStatus.ACTIVE, "u4");
        versioningHistoryService.createVersionHistory(createAward(AWARD_VERSION_NAME, 3), VersionStatus.ACTIVE, "u5");
        
        List<VersionHistory> list = versioningHistoryService.loadVersionHistory(Award.class, AWARD_VERSION_NAME); 
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(VersionStatus.ARCHIVED, list.get(0).getStatus());
        Assert.assertEquals(VersionStatus.ARCHIVED, list.get(1).getStatus());
        Assert.assertEquals(VersionStatus.ACTIVE, list.get(2).getStatus());
        
        list = versioningHistoryService.loadVersionHistory(Protocol.class, PROTOCOL_VERSION_NAME); 
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(VersionStatus.ARCHIVED, list.get(0).getStatus());
        Assert.assertEquals(VersionStatus.ACTIVE, list.get(1).getStatus());
        
        VersionHistory activeVersion = versioningHistoryService.findActiveVersion(Award.class, AWARD_VERSION_NAME);
        Assert.assertEquals("u5", activeVersion.getUserId());
        
        activeVersion = versioningHistoryService.findActiveVersion(Protocol.class, PROTOCOL_VERSION_NAME);
        Assert.assertEquals("u4", activeVersion.getUserId());        
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