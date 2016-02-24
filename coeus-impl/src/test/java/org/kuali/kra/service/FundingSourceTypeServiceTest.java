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
package org.kuali.kra.service;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.service.impl.FundingSourceTypeServiceImpl;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * This class unit test for FundingSourceTypeServiceImpl
 * @see FundingSourceTypeServiceImpl
 * 
 */
@RunWith(JMock.class)
public class FundingSourceTypeServiceTest {
    
    private static final String FUNDING_TYPE_ID = "fundingSourceTypeCode";
    private static final String FUNDING_TYPE_VALID_ID_VALUE = "6";
    private static final String FUNDING_TYPE_INVALID_ID_VALUE = "100001";

    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};

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
            ret.setFundingSourceTypeCode("6");

        } 
        return ret;
        
    }


}
