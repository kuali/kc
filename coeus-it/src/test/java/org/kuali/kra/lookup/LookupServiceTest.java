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
package org.kuali.kra.lookup;

import org.junit.Test;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.LookupService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
/**
 * This class tests KULRICE-984: Lookups - Relative Limit Gap
 * making sure that lookup resultSetLimits set in the DD for
 * a BO will override the system wide default.
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class LookupServiceTest extends KcIntegrationTestBase {

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
        formProps.put("sponsorName", "B*");
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
        formProps.put("sponsorName", "B*");
        Collection sponsor = lookupService.findCollectionBySearchHelper(Sponsor.class, formProps, true);
        int size = sponsor.size();
        assertTrue("# of sposnor should be > 200", size > 200);

        sponsor = null;
        sponsor = lookupService.findCollectionBySearchUnbounded(Sponsor.class, formProps);
        size = sponsor.size();
        assertTrue("# of sponsor should be > 200", size > 200);
    }
    
}
