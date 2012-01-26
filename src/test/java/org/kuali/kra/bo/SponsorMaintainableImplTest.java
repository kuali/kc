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
package org.kuali.kra.bo;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.coreservice.impl.parameter.ParameterServiceImpl;

public class SponsorMaintainableImplTest extends KcUnitTestBase {

    public static final String SPONSOR_DOC_TYPE_NAME = "SponsorMaintenanceDocument";
    
    protected SponsorMaintainableImpl sponsorMaintainableImpl;
    protected ParameterServiceMock parameterService;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        parameterService = new ParameterServiceMock();
        sponsorMaintainableImpl = new SponsorMaintainableImpl();
        sponsorMaintainableImpl.setBoClass(Sponsor.class);
        sponsorMaintainableImpl.setBusinessObject(new Sponsor());
        sponsorMaintainableImpl.setParameterService(parameterService);
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testGenerateDefaultValues() {
        parameterService.autoGenSponsorCode = true;
        sponsorMaintainableImpl.setGenerateDefaultValues(SPONSOR_DOC_TYPE_NAME);
        Sponsor sponsor = (Sponsor) sponsorMaintainableImpl.getBusinessObject();
        assertTrue(StringUtils.isNotBlank(sponsor.getSponsorCode()));
    }
    
    @Test
    public void testDefaultValues() {
        parameterService.autoGenSponsorCode = false;
        sponsorMaintainableImpl.setGenerateDefaultValues(SPONSOR_DOC_TYPE_NAME);
        Sponsor sponsor = (Sponsor) sponsorMaintainableImpl.getBusinessObject();
        assertTrue(StringUtils.isBlank(sponsor.getSponsorCode()));
    }

    class ParameterServiceMock extends ParameterServiceImpl {
        public boolean autoGenSponsorCode = true;
        public Boolean getParameterValueAsBoolean(String namespace, String detailCode, String parmValue) {
            assertEquals(Constants.KC_GENERIC_PARAMETER_NAMESPACE, namespace);
            assertEquals(Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, detailCode);
            assertEquals(SponsorMaintainableImpl.AUTO_GEN_SPONSOR_CODE_PARM, parmValue);
            return autoGenSponsorCode;
        }
    }
}
