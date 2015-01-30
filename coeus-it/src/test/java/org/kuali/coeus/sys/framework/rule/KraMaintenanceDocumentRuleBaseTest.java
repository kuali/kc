/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.sys.framework.rule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.kra.maintenance.MaintenanceRuleTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
public class KraMaintenanceDocumentRuleBaseTest extends MaintenanceRuleTestBase {
    private KcMaintenanceDocumentRuleBase rule = null;

    @Before
    public void setUp() throws Exception {
        rule = new KcMaintenanceDocumentRuleBase();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }

    @Test
    public void testOK() throws Exception {

        
        Map pkMap = new HashMap();
        pkMap.put("costElement", "422311");
        assertTrue(rule.checkExistenceFromTable(CostElement.class, pkMap, "costElement", "Cost Element"));
    }

    /**
     * 
     * This method to test rate type does not exist.
     * @throws Exception
     */
    @Test
    public void testNotExist() throws Exception {

        Map pkMap = new HashMap();
        pkMap.put("costElement", "1x");
        assertFalse(rule.checkExistenceFromTable(CostElement.class, pkMap, "costElement", "Cost Element"));

    }


}
