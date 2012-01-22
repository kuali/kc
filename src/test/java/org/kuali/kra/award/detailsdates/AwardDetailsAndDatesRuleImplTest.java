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
package org.kuali.kra.award.detailsdates;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Sponsor;
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
    
    private Mockery context = new JUnit4Mockery();
    
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
