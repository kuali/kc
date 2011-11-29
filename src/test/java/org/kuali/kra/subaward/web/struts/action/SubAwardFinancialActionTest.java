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
package org.kuali.kra.subaward.web.struts.action;

import java.sql.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.util.KualiDecimal;

public class SubAwardFinancialActionTest extends KcUnitTestBase{

    SubAwardFinancialAction subAwardFinancialAction; 
    SubAward subAward;
    SubAwardAmountInfo amountInfo;  

    @Before
    public void setUp() throws Exception {

        subAwardFinancialAction = new SubAwardFinancialAction();
        subAward = new SubAward();
        amountInfo = new SubAwardAmountInfo();
        amountInfo.setAnticipatedAmount(new KualiDecimal(5));
        amountInfo.setAnticipatedChange(new KualiDecimal(8));
        amountInfo.setEffectiveDate(new Date(System.currentTimeMillis()));
        amountInfo.setObligatedAmount(new KualiDecimal(7));
        amountInfo.setObligatedChange(new KualiDecimal(6));
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
