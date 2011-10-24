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
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.util.KualiDecimal;

public class SubAwardFundingSourceActionTest extends KcUnitTestBase {

    SubAwardFundingSourceAction subAwardFundingSourceAction;
    SubAward subAward;
    SubAwardFundingSource subContractFundingsource;

    @Before
    public void setUp() throws Exception {

        subAwardFundingSourceAction = new SubAwardFundingSourceAction();
        subAward = new SubAward();
        subContractFundingsource = new SubAwardFundingSource();

        subContractFundingsource.setAwardNumber("120000");
        subContractFundingsource.setAccountNumber("20001");
        subContractFundingsource.setStatusCode(1);
        subContractFundingsource.setSponsorCode("111222");
        subContractFundingsource.setAmountObligatedToDate(new KualiDecimal(5000));
        subContractFundingsource.setObligationExpirationDate(new Date(System.currentTimeMillis()));
    }

    @After
    public void tearDown() throws Exception {

        subAwardFundingSourceAction = null;
        subAward = null;
        subContractFundingsource = null;
    }

    @Test
    public void testAddFundingSourceToSubAward(){

        Assert.assertTrue(subAwardFundingSourceAction.addFundingSourceToSubAward(subAward, subContractFundingsource));


    }





}
