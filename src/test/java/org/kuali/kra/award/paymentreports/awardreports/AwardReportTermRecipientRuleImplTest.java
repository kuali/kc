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
package org.kuali.kra.award.paymentreports.awardreports;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

public class AwardReportTermRecipientRuleImplTest {

    AwardReportTermRecipient awardReportTermRecipient;
    AwardReportTermRecipientRuleImpl awardReportTermRecipientRuleImpl;
    List<AwardReportTermRecipient> awardReportTermRecipientItems;
    
    @Before
    public void setUp() throws Exception {
        awardReportTermRecipient = new AwardReportTermRecipient();
        awardReportTermRecipientRuleImpl = new AwardReportTermRecipientRuleImpl();
        awardReportTermRecipientItems = new ArrayList<AwardReportTermRecipient>();
        GlobalVariables.setErrorMap(new ErrorMap());
    }

    @After
    public void tearDown() throws Exception {
        awardReportTermRecipient = null;
        awardReportTermRecipientRuleImpl = null;
        awardReportTermRecipientItems = null;
    }
    
    @Test
    public final void testIsUnique(){
        AwardReportTermRecipient awardReportTermRecipientItem = new AwardReportTermRecipient();
        awardReportTermRecipientItem.setContactTypeCode("5");
        awardReportTermRecipientItem.setRolodexId(5);        
        awardReportTermRecipientItems.add(awardReportTermRecipientItem);        
        awardReportTermRecipientItem = new AwardReportTermRecipient();
        awardReportTermRecipientItem.setContactTypeCode("5");
        awardReportTermRecipientItem.setRolodexId(5);
        Assert.assertFalse(awardReportTermRecipientRuleImpl.isUnique(awardReportTermRecipientItems, awardReportTermRecipientItem));
        awardReportTermRecipientItem = new AwardReportTermRecipient();
        awardReportTermRecipientItem.setContactTypeCode("3");
        awardReportTermRecipientItem.setRolodexId(5);
        Assert.assertTrue(awardReportTermRecipientRuleImpl.isUnique(awardReportTermRecipientItems, awardReportTermRecipientItem));
    }

    @Test
    public final void testEvaluateRuleForContactType() {
        awardReportTermRecipient.setContactTypeCode("5");
        Assert.assertTrue(awardReportTermRecipientRuleImpl.isContactTypeFieldComplete(awardReportTermRecipient));
        awardReportTermRecipient.setContactTypeCode(null);
        Assert.assertFalse(awardReportTermRecipientRuleImpl.isContactTypeFieldComplete(awardReportTermRecipient));
    }

    @Test
    public final void testEvaluateRuleForRolodex() {
        awardReportTermRecipient.setRolodexId(5);
        Assert.assertTrue(awardReportTermRecipientRuleImpl.isOrganizationFieldComplete(awardReportTermRecipient));
        awardReportTermRecipient.setRolodexId(null);
        Assert.assertFalse(awardReportTermRecipientRuleImpl.isOrganizationFieldComplete(awardReportTermRecipient));
    }

}
