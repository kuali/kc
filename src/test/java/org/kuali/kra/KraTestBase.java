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

package org.kuali.kra;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.kuali.core.document.Document;
import org.kuali.core.service.DocumentService;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.lifecycle.Lifecycle;
import org.kuali.rice.test.data.PerSuiteUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.testharness.KNSTestCase;
import org.kuali.rice.testharness.TransactionalLifecycle;
import org.kuali.kra.infrastructure.KraServiceLocator;

@PerSuiteUnitTestData(
        @UnitTestData(
            sqlFiles = {
                @UnitTestFile(filename = "classpath:DefaultTestData.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_CATEGORY.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_CATEGORY_MAPPING.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_BUDGET_CATEGORY_MAPS.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_COST_ELEMENT.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_EPS_PROP_PER_DOC_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_RATE_CLASS.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_RATE_TYPE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_VALID_CALC_TYPES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_JOB_CODE.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_VALID_CE_JOB_CODES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_VALID_CE_RATE_TYPES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_abstract_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_activity_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_carrier_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_country_code.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_deadline_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_degree_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_eps_prop_person_role.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_fp_doc_group_t.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_fp_doc_type_t.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_unit.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/LOAD_INSTITUTE_LA_RATES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/LOAD_INSTITUTE_RATES.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_inv_credit_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_kim.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_mail_by.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_mail_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_narrative_status.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_narrative_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_notice_of_opportunity.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_nsf_codes.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_organization.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_person.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_person_editable_fields.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_proposal_response.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_proposal_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_rights.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_roles.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_role_rights.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_rolodex.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_science_keyword.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_sp_rev_approval_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_special_review.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_sponsor_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_sponsor.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_state_code.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_system_params.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_unit_administrator.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_user_roles.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_user_roles_all.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_valid_sp_rev_approval.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_budget_status.sql", delimiter = ";")
            }
        )
    )

public abstract class KraTestBase extends KNSTestCase {

    private TransactionalLifecycle transactionalLifecycle;
    private DocumentService documentService = null;

    @Before
    public void setUp() throws Exception {
        setContextName("/kra-dev");
        setRelativeWebappRoot("/src/main/webapp");
//        setSqlFilename("classpath:DefaultTestData.sql");
//        setSqlDelimiter(";");
        setXmlFilename("classpath:DefaultTestData.xml");
        setTestConfigFilename("classpath:META-INF/kra-test-config.xml");
        super.setUp();
        documentService = KNSServiceLocator.getDocumentService();
        GlobalVariables.setErrorMap(new ErrorMap());
        transactionalLifecycle = new TransactionalLifecycle();
        transactionalLifecycle.start();
    }

    @After
    public void tearDown() throws Exception {
        transactionalLifecycle.stop();
        GlobalVariables.setErrorMap(new ErrorMap());
        super.tearDown();
        documentService = null;
    }

    @Override
    protected String getModuleName() {
        return "";
    }


    @Override
    public List<Lifecycle> getSuiteLifecycles() {
        List<Lifecycle> lifeCycles= super.getSuiteLifecycles();
        lifeCycles.add(new KraSQLDataLoaderLifecycle());
        lifeCycles.add(new KraKEWXmlDataLoaderLifecycle());
        return lifeCycles;
    }

    public DocumentService getDocumentService() throws Exception {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    protected Document getDocument(String documentNumber) throws Exception {
        transactionalLifecycle.stop();
        Document doc=getDocumentService().getByDocumentHeaderId(documentNumber);
        transactionalLifecycle.start();
        return doc;

    }
    
    /**
     *  Delegate to <code>{@link KraServiceLocator#getService(Class)}</code>
     * @param <T>
     * @param serviceClass class of service to get instance for
     * @return Service instance
     */
    protected final <T> T getService(Class<T> serviceClass) {
        return KraServiceLocator.getService(serviceClass);
    }
}
