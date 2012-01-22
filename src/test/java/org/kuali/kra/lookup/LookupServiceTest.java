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
package org.kuali.kra.lookup;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.LookupService;

/*@PerTestUnitTestData(
        @UnitTestData(
                order = {UnitTestData.Type.SQL_STATEMENTS, UnitTestData.Type.SQL_FILES},
                sqlStatements = {
                        @UnitTestSql("delete from TRV_ACCT where acct_fo_id between 101 and 301")
                        ,@UnitTestSql("delete from TRV_ACCT_FO where acct_fo_id between 101 and 301")
                },
                sqlFiles = {
                        @UnitTestFile(filename = "classpath:testFiscalOfficers.sql", delimiter = ";")
                        , @UnitTestFile(filename = "classpath:testTravelAccounts.sql", delimiter = ";")
                }
        )
)*/
/**
 * This class tests KULRICE-984: Lookups - Relative Limit Gap
 * making sure that lookup resultSetLimits set in the DD for
 * a BO will override the system wide default.
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class LookupServiceTest extends KcUnitTestBase {

    public LookupServiceTest() {}

    /**
     * This method tests lookup return limits
     *
     * @throws Exception
     */
    @Test
    public void testLookupReturnLimits() throws Exception {
        LookupService lookupService = KRADServiceLocatorWeb.getLookupService();
        Map formProps = new HashMap();
        Collection sponsor = lookupService.findCollectionBySearchHelper(Sponsor.class, formProps, false);
        assertTrue(8000 >= sponsor.size());

        sponsor = null;
        sponsor = lookupService.findCollectionBySearch(Sponsor.class, formProps);
        assertTrue(8000 >= sponsor.size());
    }

    /**
     * This method tests an unbounded lookup
     *
     * @throws Exception
     */
    @Test
    public void testLookupReturnDefaultUnbounded() throws Exception {
        LookupService lookupService = KRADServiceLocatorWeb.getLookupService();
        Map formProps = new HashMap();
        Collection sponsor = lookupService.findCollectionBySearchHelper(Sponsor.class, formProps, true);
        int size = sponsor.size();
        assertTrue("# of sposnor should be > 200", size > 200);

        sponsor = null;
        sponsor = lookupService.findCollectionBySearchUnbounded(Sponsor.class, formProps);
        size = sponsor.size();
        assertTrue("# of sponsor should be > 200", size > 200);
    }
    
}
