/*
 * Copyright 2005-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.dao.BusinessObjectDao;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
/**
 * This class tests NsfCodeValuesFinder.
 */
public class NarrativeTypeTest extends KcIntegrationTestBase {

    @Test public void testGetKeyValues() throws Exception {
        BusinessObjectDao businessObjectDao = (BusinessObjectDao) KcServiceLocator.getService(Constants.BUSINESS_OBJECT_DAO_NAME);

        String paramValue = getService(ParameterService.class).getParameterValueAsString(
                Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, Constants.PROPOSAL_NARRATIVE_TYPE_GROUP);
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
