/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.dao.BusinessObjectDao;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;

/**
 * This class tests NsfCodeValuesFinder.
 */
public class NarrativeTypeTest extends KraTestBase {


    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test public void testGetKeyValues() throws Exception {
        BusinessObjectDao businessObjectDao = (BusinessObjectDao)KraServiceLocator.getService(Constants.BUSINESS_OBJECT_DAO_NAME);

        String paramValue = getService(KualiConfigurationService.class).getParameterValue(
                Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.PROPOSAL_NARRATIVE_TYPE_GROUP);
        assertNotNull(paramValue);
        assertNotSame("System Parameter for "+Constants.PROPOSAL_NARRATIVE_TYPE_GROUP+ " not loaded...","", paramValue);
        Collection<NarrativeType> narrativeTypes = businessObjectDao.findAll(NarrativeType.class);
        assertTrue("Narrative Types not loaded",narrativeTypes.size()>0);
        for (NarrativeType narrativeType : narrativeTypes) {
            boolean propNarrLoaded = false;
            if(propNarrLoaded = narrativeType.getNarrativeTypeCode().equals(paramValue)){
                assertTrue(propNarrLoaded);
                break;
            }
        }
    }

}
