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
package org.kuali.kra.award.detailsdates;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * Unit tests for AwardDetailsAndDatesRuleImpl
 * 
 * @author Kuali Coeus development team (kc.dev@kuali.org)
 */
public class AwardDetailsAndDatesRuleImplTest {
    
    Sponsor sponsor;
    Award award;
    AddAwardTransferringSponsorEvent event;
    AwardDetailsAndDatesRuleImpl awardDetailsAndDatesRule;
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    
    @Test
    public void testAddUnknownAwardTransferringSponsor() throws Exception {
        initializeDefaults();
        awardDetailsAndDatesRule.setBusinessObjectService(getMockBusinessObjectServiceReturnsNull());
        Assert.assertFalse(awardDetailsAndDatesRule.processAddAwardTransferringSponsorEvent(event));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_INVALID_AWARD_TRANSFERRING_SPONSOR));
    }
    
    @Test
    public void testAddDuplicateAwardTransferringSponsor() throws Exception {
        initializeDefaults();
        award.addAwardTransferringSponsor(sponsor);
        awardDetailsAndDatesRule.setBusinessObjectService(getMockBusinessObjectServiceReturnsSponsor());
        Assert.assertFalse(awardDetailsAndDatesRule.processAddAwardTransferringSponsorEvent(event));
        Assert.assertTrue(GlobalVariables.getMessageMap().containsMessageKey(KeyConstants.ERROR_DUPLICATE_AWARD_TRANSFERRING_SPONSOR));
    }
    
    @Test
    public void testAddValidAwardTransferringSponsor() throws Exception {
        initializeDefaults();
        awardDetailsAndDatesRule.setBusinessObjectService(getMockBusinessObjectServiceReturnsSponsor());
        Assert.assertTrue(awardDetailsAndDatesRule.processAddAwardTransferringSponsorEvent(event));
        Assert.assertTrue(GlobalVariables.getMessageMap().hasNoErrors());
    }
    
    // Setup the expected initial state
    private void initializeDefaults() {
        sponsor = new Sponsor();
        sponsor.setSponsorCode("1");
        award = new Award();
        awardDetailsAndDatesRule = new AwardDetailsAndDatesRuleImpl();
        awardDetailsAndDatesRule.setErrorReporter(new ErrorReporterImpl());
        event = new AddAwardTransferringSponsorEvent("", null, award, sponsor);
        GlobalVariables.setMessageMap(new MessageMap());
    }
    
    private BusinessObjectService getMockBusinessObjectServiceReturnsNull() {
        final BusinessObjectService MOCK_BO_SERVICE = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(MOCK_BO_SERVICE).retrieve(sponsor); 
            will(returnValue(null));
        }});
        return MOCK_BO_SERVICE;
    }
    
    private BusinessObjectService getMockBusinessObjectServiceReturnsSponsor() {
        final BusinessObjectService MOCK_BO_SERVICE = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(MOCK_BO_SERVICE).retrieve(sponsor); 
            will(returnValue(sponsor));
        }});
        return MOCK_BO_SERVICE;
    }
    
}
