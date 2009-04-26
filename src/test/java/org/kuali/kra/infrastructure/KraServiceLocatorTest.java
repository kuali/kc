/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.infrastructure;

import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.proposaldevelopment.bo.MailType;
import org.kuali.rice.kns.dao.BusinessObjectDao;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.KualiConfigurationService;

/**
 * This class tests the KraServiceLocator
 */
public class KraServiceLocatorTest extends KraTestBase {

    @Test public void testGetDataDictionaryService() throws Exception {
        DataDictionaryService dataDictionaryService = (DataDictionaryService)KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        assertNotNull(dataDictionaryService);

        assertEquals("ProposalDevelopmentDocument", dataDictionaryService.getDataDictionary().getDocumentEntry(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument.class.getName()).getDocumentTypeName());
    }

    @Test public void testGetDateTimeService() throws Exception {
        DateTimeService dateTimeService = (DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME);
        assertNotNull(dateTimeService);
        Date currentDate = dateTimeService.getCurrentDate();
        Date currentDate2 = new Date(System.currentTimeMillis());
        long diff = currentDate.getTime() - currentDate2.getTime();

        assertTrue("Should be less than one second difference between dates", diff < 1000);
    }

    @Test public void testBusinessObjectDaoService() throws Exception {
        BusinessObjectDao businessObjectDao = (BusinessObjectDao)KraServiceLocator.getService(Constants.BUSINESS_OBJECT_DAO_NAME);
        assertNotNull(businessObjectDao);

        Collection carrierTypes = businessObjectDao.findAll(MailType.class);
        assertEquals(3, carrierTypes.size());
    }

    @Test public void testProposalDevelopmentParameters() throws Exception {
        KualiConfigurationService configService = (KualiConfigurationService)KraServiceLocator.getService(KualiConfigurationService.class);

        Map<String, String> criteria = new HashMap<String, String>(2);
        criteria.put("parameterNamespaceCode", PARAMETER_MODULE_PROPOSAL_DEVELOPMENT);
        criteria.put("parameterDetailTypeCode", PARAMETER_COMPONENT_DOCUMENT);

        List parameters = configService.getParameters(criteria);
        assertNotNull(parameters);
        assertTrue(parameters.size() > 1);
    }

}
