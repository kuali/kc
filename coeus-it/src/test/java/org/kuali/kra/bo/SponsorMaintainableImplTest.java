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
package org.kuali.kra.bo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.impl.parameter.ParameterServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import static org.junit.Assert.*;

public class SponsorMaintainableImplTest extends KcIntegrationTestBase {

    public static final String SPONSOR_DOC_TYPE_NAME = "SponsorMaintenanceDocument";
    
    protected SponsorMaintainableImpl sponsorMaintainableImpl;
    protected ParameterServiceMock parameterService;
    
    @Before
    public void setUp() throws Exception {
        parameterService = new ParameterServiceMock();
        sponsorMaintainableImpl = new SponsorMaintainableImpl();
        sponsorMaintainableImpl.setBoClass(Sponsor.class);
        sponsorMaintainableImpl.setBusinessObject(new Sponsor());
        sponsorMaintainableImpl.setParameterService(parameterService);
        sponsorMaintainableImpl.setSponsorCodeIncrementer(new DataFieldMaxValueIncrementer() {
            int i = 0;
            @Override
            public int nextIntValue() throws DataAccessException {
                return i++;
            }

            @Override
            public long nextLongValue() throws DataAccessException {
                return i++;
            }

            @Override
            public String nextStringValue() throws DataAccessException {
                return String.valueOf(i++);
            }
        });
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
