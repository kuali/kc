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
package org.kuali.kra.institutionalproposal.contacts;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.List;

public class InstitutionalProposalUnitContactsProjectRoleValuesFinderTest extends KcIntegrationTestBase {
    InstitutionalProposalUnitContactsProjectRoleValuesFinder ipucProjectRoleValuesFinder;

    @Before
    public void setUp() throws Exception {
        ipucProjectRoleValuesFinder = this.getIpucProjectRoleValuesFinder();

    }
    
    private InstitutionalProposalUnitContactsProjectRoleValuesFinder getIpucProjectRoleValuesFinder() {
        return new InstitutionalProposalUnitContactsProjectRoleValuesFinder();
    }

    @After
    public void tearDown() throws Exception {
        ipucProjectRoleValuesFinder = null;
    }

    @Test
    public final void testGetKeyValues() {
        List<KeyValue> roleKeys = ipucProjectRoleValuesFinder.getKeyValues();
        BusinessObjectService boService = KcServiceLocator.getService(BusinessObjectService.class);
        
        for(KeyValue KeyValue:roleKeys){
            Assert.assertNotNull(KeyValue.getKey());
            Assert.assertNotNull(KeyValue.getValue());
            if(!StringUtils.equals(KeyValue.getValue(), "select")){ 
                UnitAdministratorType aType = (UnitAdministratorType) boService.findBySinglePrimaryKey(UnitAdministratorType.class, KeyValue.getKey());
                Assert.assertEquals(Constants.UNIT_CONTACTS_DEFAULT_GROUP_FLAG, aType.getDefaultGroupFlag());
            }
        }
        
    }
    
    
}
