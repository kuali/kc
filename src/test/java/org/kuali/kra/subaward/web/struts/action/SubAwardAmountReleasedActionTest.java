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
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.util.KualiDecimal;

public class SubAwardAmountReleasedActionTest extends KcUnitTestBase{

    SubAwardAmountReleasedAction  subAwardAmountReleasedAction; 
    SubAwardAmountReleased subAwardAmountReleased;
    SubAward subAward;



    @Before
    public void setUp() throws Exception {

        subAwardAmountReleasedAction = new SubAwardAmountReleasedAction();
        subAward = new SubAward();
        subAwardAmountReleased =new SubAwardAmountReleased();
        subAwardAmountReleased.setAmountReleased(new KualiDecimal(5000));
        subAwardAmountReleased.setApprovalComments("Test Approval comments");
        subAwardAmountReleased.setApprovalDate(new Date(System.currentTimeMillis()));
        subAwardAmountReleased.setComments("test comments");
    }

    @After
    public void tearDown() throws Exception {

        subAwardAmountReleasedAction = null;
        subAward = null;
        subAwardAmountReleased = null;
    }

    @Test
    public void testAddAmountReleasedToSubAward(){
        Assert.assertTrue(subAwardAmountReleasedAction.addAmountReleasedToSubAward(subAward, subAwardAmountReleased));
    }
}
