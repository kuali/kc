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
package org.kuali.kra.award.rules;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.bo.AwardReportTermRecipient;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

public class AwardReportTermRecipientRuleTest {

    AwardReportTermRecipient awardReportTermRecipient;
    AwardReportTermRecipientRule awardReportTermRecipientRule;
    
    @Before
    public void setUp() throws Exception {
        awardReportTermRecipient = new AwardReportTermRecipient();
        awardReportTermRecipientRule = new AwardReportTermRecipientRule();
        GlobalVariables.setErrorMap(new ErrorMap());
    }

    @After
    public void tearDown() throws Exception {
        awardReportTermRecipient = null;
        awardReportTermRecipientRule = null;
    }

    @Test
    public final void testEvaluateRuleForContactType() {
        awardReportTermRecipient.setContactTypeCode("5");
        Assert.assertTrue(awardReportTermRecipientRule.evaluateRuleForContactType(awardReportTermRecipient, 0));
        awardReportTermRecipient.setContactTypeCode(null);
        Assert.assertFalse(awardReportTermRecipientRule.evaluateRuleForContactType(awardReportTermRecipient, 0));
    }

    @Test
    public final void testEvaluateRuleForRolodex() {
        awardReportTermRecipient.setRolodexId(5);
        Assert.assertTrue(awardReportTermRecipientRule.evaluateRuleForRolodex(awardReportTermRecipient, 0));
        awardReportTermRecipient.setRolodexId(null);
        Assert.assertFalse(awardReportTermRecipientRule.evaluateRuleForRolodex(awardReportTermRecipient, 0));
    }

}
