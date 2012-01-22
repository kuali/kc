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
package org.kuali.kra.award.paymentreports.closeout;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

/**
 * 
 * This class tests the AwardCloseoutRuleImpl.java
 */
public class AwardCloseoutRuleImplTest {

    public static final String CLOSE_OUT_REPORT_CODE_A = "A";
    public static final String CLOSE_OUT_REPORT_CODE_B = "B";
    public static final String CLOSE_OUT_REPORT_NAME = "FINANCIAL";
    
    AwardCloseoutRuleImpl awardCloseoutRuleImpl;
    AwardCloseout newCloseoutItem;
    AwardCloseout closeoutItem;
    List<AwardCloseout> closeoutItems;
    
    /**
     * 
     * This method gets executed before every unit test.
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        awardCloseoutRuleImpl = new AwardCloseoutRuleImpl();
        newCloseoutItem = new AwardCloseout();
        closeoutItem = new AwardCloseout();
        closeoutItem.setCloseoutReportCode(CLOSE_OUT_REPORT_CODE_A);
        closeoutItem.setCloseoutReportName(CLOSE_OUT_REPORT_NAME);
        closeoutItems = new ArrayList<AwardCloseout>();
        closeoutItems.add(closeoutItem);
        GlobalVariables.setMessageMap(new MessageMap());
    }

    /**
     * 
     * This method gets executed after every unit test.
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        awardCloseoutRuleImpl = null;
        closeoutItem = null;
        newCloseoutItem = null;
        closeoutItems = null;
        GlobalVariables.setMessageMap(null);
    }

    /**
     * 
     * This method tests the isUnique method.
     * 
     */
    @Test
    public final void testIsUniqueSuccess() {
        newCloseoutItem.setCloseoutReportCode(CLOSE_OUT_REPORT_CODE_B);
        newCloseoutItem.setCloseoutReportName(CLOSE_OUT_REPORT_NAME);
        Assert.assertTrue(awardCloseoutRuleImpl.isUnique(closeoutItems, newCloseoutItem));
    }
    
    /**
     * 
     * This method tests the isUnique method.
     * 
     */
    @Test
    public final void testIsUniqueFailure() {        
        newCloseoutItem.setCloseoutReportCode(CLOSE_OUT_REPORT_CODE_A);
        newCloseoutItem.setCloseoutReportName(CLOSE_OUT_REPORT_NAME);
        Assert.assertFalse(awardCloseoutRuleImpl.isUnique(closeoutItems, newCloseoutItem));
    }
    
    /**
     * 
     * This method tests the areRequiredFieldsComplete method.
     * 
     */
    @Test
    public final void testAreRequiredFieldsCompleteFailure() {
        Assert.assertFalse(awardCloseoutRuleImpl.areRequiredFieldsComplete(newCloseoutItem));        
    }
    
    /**
     * 
     * This method tests the areRequiredFieldsComplete method.
     * 
     */
    @Test
    public final void testAreRequiredFieldsCompleteSuccess() {
        Assert.assertTrue(awardCloseoutRuleImpl.areRequiredFieldsComplete(closeoutItem));        
    }

}
