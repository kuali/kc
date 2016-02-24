/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.service.impl.AwardDirectFandADistributionServiceImpl;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.rice.kns.util.MessageList;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.web.form.UifFormManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * This class tests public methods in AwardDirectFandADistributionServiceImpl.
 */
public class AwardDirectFandADistributionServiceImplTest extends AwardDirectFandADistributionServiceImpl {

    private static final int TWO = 2;
    private static final int SIX = 6;
    private static final int THREE = 3;
    AwardDirectFandADistributionServiceImpl awardDirectFandADistributionServiceImpl;
    List<AwardDirectFandADistribution> awardDirectFandADistributions;
    Award award;
    
    
    @Before
    public void setUp() throws Exception {
        awardDirectFandADistributionServiceImpl = new AwardDirectFandADistributionServiceImpl();
        awardDirectFandADistributions = new ArrayList<AwardDirectFandADistribution>();
        award = new Award();
        MockGlobalVariableService globalVariableService = new MockGlobalVariableService();
        globalVariableService.setUserSession(new MockUserSession("quickstart"));
        award.getAwardAmountInfos().get(0).setGlobalVariableService(globalVariableService);
        
        Calendar calendar = Calendar.getInstance();
        award.setAwardEffectiveDate(new Date(calendar.getTime().getTime()));
        calendar.add(Calendar.YEAR, TWO);
        calendar.add(Calendar.MONTH, SIX);
        award.setProjectEndDate(new Date(calendar.getTime().getTime()));
        
    }

    @After
    public void tearDown() throws Exception {
        awardDirectFandADistributionServiceImpl = null;
        
    }

    @Test
    public final void testGenerateDefaultAwardDirectFandADistributionPeriods() {
        awardDirectFandADistributions = awardDirectFandADistributionServiceImpl.generateDefaultAwardDirectFandADistributionPeriods(award);
        Assert.assertTrue(awardDirectFandADistributions.size() == THREE);
    }

    public static class MockUserSession extends UserSession {

        public MockUserSession(String principalName) {
            super(principalName);
        }

        public String getPrincipalName() {
            return "quickstart";
        }

        @Override
        protected void initPerson(String principalName) {

        }

    }

    public static class MockGlobalVariableService implements GlobalVariableService {

        UserSession userSession;

        @Override
        public UserSession getUserSession() {
            return userSession;
        }

        @Override
        public void setUserSession(UserSession userSession) {
            this.userSession = userSession;
        }

        @Override
        public MessageMap getMessageMap() {
            return null;
        }

        @Override
        public void setMessageMap(MessageMap messageMap) {

        }

        @Override
        public MessageList getMessageList() {
            return null;
        }

        @Override
        public void setMessageList(MessageList messageList) {

        }

        @Override
        public Map<String, AuditCluster> getAuditErrorMap() {
            return null;
        }

        @Override
        public void setAuditErrorMap(Map<String, AuditCluster> auditMap) {

        }

        @Override
        public UifFormManager getUifFormManager() {
            return null;
        }

        @Override
        public void setUifFormManager(UifFormManager uifFormManager) {

        }

        @Override
        public <T> T doInNewGlobalVariables(Callable<T> callable) {
            return null;
        }

        @Override
        public <T> T doInNewGlobalVariables(UserSession userSession, Callable<T> callable) {
            return null;
        }
    }
}
