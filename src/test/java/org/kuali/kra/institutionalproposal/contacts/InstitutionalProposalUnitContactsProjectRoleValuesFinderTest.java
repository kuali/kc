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
package org.kuali.kra.institutionalproposal.contacts;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.service.BusinessObjectService;

public class InstitutionalProposalUnitContactsProjectRoleValuesFinderTest extends KcUnitTestBase  {
    InstitutionalProposalUnitContactsProjectRoleValuesFinder ipucProjectRoleValuesFinder;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        ipucProjectRoleValuesFinder = this.getIpucProjectRoleValuesFinder();

    }
    
    private InstitutionalProposalUnitContactsProjectRoleValuesFinder getIpucProjectRoleValuesFinder() {
        return new InstitutionalProposalUnitContactsProjectRoleValuesFinder();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        ipucProjectRoleValuesFinder = null;
    }

    @Test
    public final void testGetKeyValues() {
        List<KeyLabelPair> roleKeys = ipucProjectRoleValuesFinder.getKeyValues();
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        
        for(KeyLabelPair keyLabelPair:roleKeys){
            Assert.assertNotNull(keyLabelPair.getKey());
            Assert.assertNotNull(keyLabelPair.getLabel());
            if(!StringUtils.equals(keyLabelPair.getLabel(), "select")){ 
                UnitAdministratorType aType = (UnitAdministratorType) boService.findBySinglePrimaryKey(UnitAdministratorType.class, keyLabelPair.getKey());
                Assert.assertEquals("U", aType.getDefaultGroupFlag());
            }
        }
        
    }
    
    
}
