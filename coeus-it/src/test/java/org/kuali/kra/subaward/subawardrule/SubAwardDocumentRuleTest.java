/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.subaward.subawardrule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.subaward.bo.*;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;


public class SubAwardDocumentRuleTest extends KcIntegrationTestBase {
    
    SubAward subAward;
    SubAwardDocumentRule subAwardDocumentRule;
    SubAwardAmountInfo subAwardAmountInfo;
    SubAwardAmountReleased subAwardAmountReleased;
    SubAwardContact subAwardContact;
    SubAwardCloseout subAwardCloseout;
    SubAwardFundingSource subAwardFundingSource;
    SubAwardFfataReporting subAwardFfataReporting;

    @Before
    public void setUp() throws Exception {              
        
        subAward = new SubAward();
        subAwardDocumentRule = new SubAwardDocumentRule();        
        
        subAward.setOrganizationId("000140");
        subAward.setStatusCode(1);
        subAward.setSubAwardTypeCode(1);
        subAward.setRequisitionerId("1");
        subAward.setPurchaseOrderNum("1"); 
        subAward.setTotalAmountReleased(ScaleTwoDecimal.ZERO);
        subAward.setTotalAnticipatedAmount(ScaleTwoDecimal.ZERO);
        subAward.setTotalAvailableAmount(ScaleTwoDecimal.ZERO);
        subAward.setTotalObligatedAmount(ScaleTwoDecimal.ZERO);
        
        subAwardAmountInfo = new SubAwardAmountInfo(); 
        subAwardAmountInfo.setEffectiveDate(new Date(System.currentTimeMillis()));  
        subAwardAmountInfo.setObligatedChange(new ScaleTwoDecimal(150));
        subAwardAmountInfo.setAnticipatedChange(new ScaleTwoDecimal(200));
        subAwardAmountInfo.setModificationTypeCode("RESBOOT1002");
        subAwardAmountInfo.setSubAwardAmountInfoId(999);

        subAwardAmountReleased = new SubAwardAmountReleased();
        subAwardAmountReleased.setInvoiceNumber("1") ;
        subAwardAmountReleased.setStartDate(new Date(System.currentTimeMillis()));
        subAwardAmountReleased.setEndDate(new Date(System.currentTimeMillis()));
        subAwardAmountReleased.setEffectiveDate(new Date(System.currentTimeMillis()));
        subAwardAmountReleased.setAmountReleased(new ScaleTwoDecimal(100));
        
        subAwardContact = new SubAwardContact();
        subAwardContact.setRolodexId(1);
        subAwardContact.setContactTypeCode("1");
        
        subAwardCloseout = new SubAwardCloseout();
        subAwardCloseout.setCloseoutTypeCode(1);
        subAwardCloseout.setDateRequested(new Date(2012, 1, 1));
        subAwardCloseout.setDateFollowup(new Date(2012, 2, 15));
        
        subAwardFundingSource = new SubAwardFundingSource();
        subAwardFundingSource.setAwardId(new Long("1183316613046"));

        subAwardFfataReporting = new SubAwardFfataReporting();
        subAwardFfataReporting.setSubAwardAmountInfoId(999);
        subAwardFfataReporting.setSubAwardAmountInfo(subAwardAmountInfo);
        subAwardFfataReporting.setSubmitterId("admin");
        subAwardFfataReporting.setDateSubmitted(subAwardAmountInfo.getEffectiveDate());
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

    @Test
    public void testProcessAddSubAwardFfataReportingBusinessRules_pass(){

        Assert.assertTrue(subAwardDocumentRule.processAddSubAwardFfataReportingBusinessRules(subAwardFfataReporting,subAward));
    }

    @Test
    public void testProcessAddSubAwardFfataReportingBusinessRules_fail_effective_date(){
        subAwardFfataReporting.setDateSubmitted(new Date(0));
        Assert.assertFalse(subAwardDocumentRule.processAddSubAwardFfataReportingBusinessRules(subAwardFfataReporting,subAward));
    }

    @Test
    public void testProcessAddSubAwardFfataReportingBusinessRules_fail_transaction_or_desc(){
        subAwardFfataReporting.setOtherTransactionDescription("A desc");
        Assert.assertFalse(subAwardDocumentRule.processAddSubAwardFfataReportingBusinessRules(subAwardFfataReporting,subAward));
    }
}
