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
package org.kuali.kra.service;

import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.service.impl.FundingSourceTypeServiceImpl;
import org.kuali.rice.kns.service.BusinessObjectService;


/**
 * 
 * This class unit test for FundingSourceTypeServiceImpl
 * @see FundingSourceTypeServiceImpl
 * 
 */
@RunWith(JMock.class)
public class FundingSourceTypeServiceTest {
    
    private static final String FUNDING_TYPE_ID = "fundingSourceTypeCode";
    private static final String FUNDING_TYPE_VALID_ID_VALUE = "1";
    private static final String FUNDING_TYPE_INVALID_ID_VALUE = "100001";

    private Mockery context = new JUnit4Mockery();

    /**
     * Verify that the correct FundingSourceType is returned if it is found.
     */
    @Test
    public void testFundingSourceTypeFound() {
        mockFundingSourceType(FUNDING_TYPE_VALID_ID_VALUE, true);
    }
    
    /**
     * Verify that null is returned if the FundingSourceType is not found.
     */
    @Test
    public void testFundingSourceTypeNotFound() {
        mockFundingSourceType(FUNDING_TYPE_INVALID_ID_VALUE, false);
    }
    
    /**
     * This method is to mock FundingSourceTypeServiceImpl
     * Test both valid and invalid FundingSourceType here
     * @param FundingSourceTypeIdValue
     * @param validFundingSourceType
     */
    private void mockFundingSourceType(String typeIdValue, boolean valid) {
        FundingSourceTypeServiceImpl funSrcTypeServiceImpl = new FundingSourceTypeServiceImpl();
        
        final Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(FUNDING_TYPE_ID, typeIdValue);
        final FundingSourceType fundingSourceType = getFundingSourceType(valid);
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        context.checking(new Expectations() {{
            one(businessObjectService).findByPrimaryKey(FundingSourceType.class, fieldValues); will(returnValue(fundingSourceType));
        }});
        funSrcTypeServiceImpl.setBusinessObjectService(businessObjectService);
        
        Assert.assertEquals(fundingSourceType, funSrcTypeServiceImpl.getFundingSourceType(typeIdValue));
    }
    
    private FundingSourceType getFundingSourceType(boolean isValid) {
        FundingSourceType ret = null;
        if (isValid) {
            ret =  new FundingSourceType();
            ret.setDescription("Award");
            ret.setFundingSourceTypeCode(1);

        } 
        return ret;
        
    }


}
