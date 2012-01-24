/*
 * Copyright 2005-2010 The Kuali Foundation
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

import static org.kuali.kra.infrastructure.Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT;
import static org.kuali.rice.coreservice.framework.parameter.ParameterConstants.DOCUMENT_COMPONENT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.bo.MailType;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.api.CoreServiceApiServiceLocator;
import org.kuali.rice.coreservice.api.parameter.ParameterQueryResults;
import org.kuali.rice.coreservice.api.parameter.ParameterRepositoryService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.dao.BusinessObjectDao;

/**
 * This class tests the KraServiceLocator
 */ 
public class KraServiceLocatorTest extends KcUnitTestBase {

    private ParameterRepositoryService parameterRepositoryService;
    
    @Before
    public void setUpServices() {
        this.parameterRepositoryService = CoreServiceApiServiceLocator.getParameterRepositoryService();
    }
    
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
        QueryByCriteria.Builder queryBuilder = QueryByCriteria.Builder.create();
        List<Predicate> predicates = new ArrayList<Predicate>();
        ParameterQueryResults parameterQueryResults = null;
        predicates.add(PredicateFactory.equal("namespaceCode", MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT));
        predicates.add(PredicateFactory.equal("componentCode", DOCUMENT_COMPONENT));
        queryBuilder.setPredicates(PredicateFactory.and(predicates.toArray(new Predicate[] {})));
        parameterQueryResults = parameterRepositoryService.findParameters(queryBuilder.build());

        assertNotNull(parameterQueryResults); 
        assertNotNull(parameterQueryResults.getResults());
        //We are using getResults().size() because parameterQueryResults.getTotalRowCount is null even when the results list is not and contains one or more records
        assertTrue(parameterQueryResults.getResults().size() > 0);
    }

}
