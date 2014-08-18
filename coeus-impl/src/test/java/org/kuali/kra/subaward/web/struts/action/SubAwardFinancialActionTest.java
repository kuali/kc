/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.subaward.web.struts.action;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

public class SubAwardFinancialActionTest {

    SubAwardFinancialAction subAwardFinancialAction; 
    SubAward subAward;
    SubAwardAmountInfo amountInfo;  

    @Before
    public void setUp() throws Exception {

        subAwardFinancialAction = new SubAwardFinancialAction();
        subAward = new SubAward();
        amountInfo = new SubAwardAmountInfo();
        amountInfo.setAnticipatedAmount(new ScaleTwoDecimal(5));
        amountInfo.setAnticipatedChange(new ScaleTwoDecimal(8));
        amountInfo.setEffectiveDate(new Date(System.currentTimeMillis()));
        amountInfo.setObligatedAmount(new ScaleTwoDecimal(7));
        amountInfo.setObligatedChange(new ScaleTwoDecimal(6));
        amountInfo.setComments("Test Comments");
    }
    @After
    public void tearDown() throws Exception {

        subAwardFinancialAction = null;
        subAward = null;
        amountInfo = null;
    }

    @Test
    public void testAddAmountInfoToSubAward(){
        Assert.assertTrue(subAwardFinancialAction.addAmountInfoToSubAward(subAward, amountInfo));
    }

}
