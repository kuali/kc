/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.lookup;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.core.service.LookupService;
import org.kuali.kra.KraTestBase;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;

import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
//import edu.sampleu.travel.bo.TravelAccount;

/*@PerTestUnitTestData(
        @UnitTestData(
                order = {UnitTestData.Type.SQL_STATEMENTS, UnitTestData.Type.SQL_FILES},
                sqlStatements = {
                        @UnitTestSql("delete from trv_acct where acct_fo_id between 101 and 301")
                        ,@UnitTestSql("delete from trv_acct_fo where acct_fo_id between 101 and 301")
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
public class LookupServiceTest extends KraTestBase {

    public LookupServiceTest() {}

    /**
     * This method tests lookup return limits
     *
     * @throws Exception
     */
    @Test
    public void testLookupReturnLimits() throws Exception {
        LookupService lookupService = KNSServiceLocator.getLookupService();
        Map formProps = new HashMap();
        Collection sponsor = lookupService.findCollectionBySearchHelper(Sponsor.class, formProps, false);
        assertEquals(50, sponsor.size());

        sponsor = null;
        sponsor = lookupService.findCollectionBySearch(Sponsor.class, formProps);
        assertEquals(50, sponsor.size());
    }

    /**
     * This method tests an unbounded lookup
     *
     * @throws Exception
     */
    @Test
    public void testLookupReturnDefaultUnbounded() throws Exception {
        LookupService lookupService = KNSServiceLocator.getLookupService();
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
