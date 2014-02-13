/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.subaward.subawardrule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.subaward.bo.*;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

import java.sql.Date;


public class SubAwardDocumentRuleTest extends KcIntegrationTestBase {
    
    SubAward subAward;
    SubAwardDocumentRule subAwardDocumentRule;
    SubAwardAmountInfo subAwardAmountInfo;
    SubAwardAmountReleased subAwardAmountReleased;
    SubAwardContact subAwardContact;
    SubAwardCloseout subAwardCloseout;
    SubAwardFundingSource subAwardFundingSource;
    
    
    @Before
    public void setUp() throws Exception {              
        
        subAward = new SubAward();
        subAwardDocumentRule = new SubAwardDocumentRule();        
        
        subAward.setOrganizationId("000140");
        subAward.setStatusCode(1);
        subAward.setSubAwardTypeCode(1);
        subAward.setRequisitionerId("1");
        subAward.setPurchaseOrderNum("1"); 
        subAward.setTotalAmountReleased(KualiDecimal.ZERO);
        subAward.setTotalAnticipatedAmount(KualiDecimal.ZERO);
        subAward.setTotalAvailableAmount(KualiDecimal.ZERO);
        subAward.setTotalObligatedAmount(KualiDecimal.ZERO);
        
        subAwardAmountInfo = new SubAwardAmountInfo(); 
        subAwardAmountInfo.setEffectiveDate(new Date(System.currentTimeMillis()));  
        subAwardAmountInfo.setObligatedChange(new KualiDecimal(150));
        subAwardAmountInfo.setAnticipatedChange(new KualiDecimal(200));
        subAwardAmountReleased = new SubAwardAmountReleased();
        subAwardAmountReleased.setInvoiceNumber("1") ;
        subAwardAmountReleased.setStartDate(new Date(System.currentTimeMillis()));
        subAwardAmountReleased.setEndDate(new Date(System.currentTimeMillis()));
        subAwardAmountReleased.setEffectiveDate(new Date(System.currentTimeMillis()));
        subAwardAmountReleased.setAmountReleased(new KualiDecimal(100));  
        
        subAwardContact = new SubAwardContact();
        subAwardContact.setRolodexId(1);
        subAwardContact.setContactTypeCode("1");
        
        subAwardCloseout = new SubAwardCloseout();
        subAwardCloseout.setCloseoutTypeCode(1);
        subAwardCloseout.setDateRequested(new Date(2012, 1, 1));
        subAwardCloseout.setDateFollowup(new Date(2012, 2, 15));
        
        subAwardFundingSource = new SubAwardFundingSource();
        subAwardFundingSource.setAwardId(new Long("1183316613046"));        
        
    }
    
    @After
    public void tearDown() throws Exception {
       
        subAward = null;
        subAwardDocumentRule = null;        
        subAwardAmountInfo = null;
        subAwardAmountReleased = null;
        subAwardContact = null;
        subAwardCloseout= null;
        subAwardFundingSource = null;
    }
    
    @Test
    public void testprocessAddSubAwardBusinessRules() throws Exception {
        
        Assert.assertTrue(subAwardDocumentRule.processAddSubAwardBusinessRules(subAward));
    }
    
    @Test
    public void testProcessAddSubAwardAmountInfoBusinessRules() throws Exception {
       
        Assert.assertTrue(subAwardDocumentRule.processAddSubAwardAmountInfoBusinessRules(subAwardAmountInfo,subAward));
    }
        
    @Test
    public void testProcessAddSubAwardContactBusinessRules(){
        
        Assert.assertTrue(subAwardDocumentRule.processAddSubAwardContactBusinessRules(subAwardContact,subAward));
    }
    
    @Test
    public void testProcessAddSubAwardCloseoutBusinessRules(){
        
        Assert.assertTrue(subAwardDocumentRule.processAddSubAwardCloseoutBusinessRules(subAwardCloseout));
    }
    
    @Test
    public void testProcessAddSubAwardFundingSourceBusinessRules(){
        
        Assert.assertTrue(subAwardDocumentRule.processAddSubAwardFundingSourceBusinessRules(subAwardFundingSource,subAward));
    }    
       
}
