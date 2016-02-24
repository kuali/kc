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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
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
            if(propNarrLoaded = narrativeType.getCode().equals(paramValue)){
                assertTrue(propNarrLoaded);
                break;
            }
        }
    }

}
