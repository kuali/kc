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
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.util.GlobalVariables;

public class VersionHistoryServiceImplTest extends KraTestBase {
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
        Award award = createAndSaveAward("1001", 1);
        VersionHistory vh = versioningHistoryService.createVersionHistory(award, "jtester");
        Assert.assertNotNull("VersionHistory was null", vh);
    }

    @Test
    public void testSavingVersionHistories() {
        versioningHistoryService.createVersionHistory(createAndSaveAward("1001", 1), "u1");
        versioningHistoryService.createVersionHistory(createAndSaveProtocol("2001", 1), "u2");
        versioningHistoryService.createVersionHistory(createAndSaveAward("1001", 2), "u3");
        versioningHistoryService.createVersionHistory(createAndSaveProtocol("2001", 2), "u4");
        versioningHistoryService.createVersionHistory(createAndSaveAward("1001", 3), "u5");
        
        List<VersionHistory> list = versioningHistoryService.loadVersionHistory(Award.class, "1001"); 
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(VersionStatus.ARCHIVED, list.get(0).getStatus());
        Assert.assertEquals(VersionStatus.ARCHIVED, list.get(1).getStatus());
        Assert.assertEquals(VersionStatus.ACTIVE, list.get(2).getStatus());
        
        list = versioningHistoryService.loadVersionHistory(Protocol.class, "2001"); 
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(VersionStatus.ARCHIVED, list.get(0).getStatus());
        Assert.assertEquals(VersionStatus.ACTIVE, list.get(1).getStatus());
        
        VersionHistory activeVersion = versioningHistoryService.findActiveVersion(Award.class, "1001");
        Assert.assertEquals("u5", activeVersion.getUserId());
        
        activeVersion = versioningHistoryService.findActiveVersion(Protocol.class, "2001");
        Assert.assertEquals("u4", activeVersion.getUserId());        
    }
    
    private Award createAndSaveAward(String versionName, Integer versionNumber) {
        Award award = new Award();
        award.setAwardNumber(versionName);
        award.setSequenceNumber(versionNumber);
        return award;
    }
    
    private Protocol createAndSaveProtocol(String versionName, Integer versionNumber) {
        Protocol protocol = new Protocol();
        protocol.setProtocolNumber(versionName);
        protocol.setSequenceNumber(versionNumber);
        return protocol;
    }
}