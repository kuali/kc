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
package org.kuali.kra.award.web.struts.action;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.rice.core.api.util.type.KualiDecimal;


public class AwardHomeActionTest {
    
    AwardHomeAction awardHomeAction;
    Award award;
    AwardApprovedSubaward awardApprovedSubaward;
    public static final String MOCK_FORWARD_STRING = "FORWARD_STRING";
    public static final String MOCK_DOC_ID_REQUEST_PARAMETER = "21";
    public static final String MOCK_EXPECTED_RESULT_STRING = "FORWARD_STRING?docId=21";
    public static final int ZERO = 0;
    
    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        awardHomeAction = new AwardHomeAction();
        award = new Award();    
        //initialize BO's
        awardApprovedSubaward = new AwardApprovedSubaward();
        //initialize awardApprovedSubaward
        awardApprovedSubaward.setAmount(new KualiDecimal(10000));
        awardApprovedSubaward.setOrganizationName("test organization");
        
      
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        award = null;
        awardApprovedSubaward = null;
        awardHomeAction = null;
    }
    
    @Test
    public void testAddApprovedSubawardToAward(){
        Assert.assertTrue(awardHomeAction.addApprovedSubawardToAward(award, awardApprovedSubaward));
    }
    
    @Test
    public void testDeleteApprovedSubawardFromAward(){        
        awardHomeAction.addApprovedSubawardToAward(award, awardApprovedSubaward);        
        awardHomeAction.deleteApprovedSubawardFromAward(award, 0);
        Assert.assertEquals(ZERO, award.getAwardApprovedSubawards().size());        
    }
    

}

