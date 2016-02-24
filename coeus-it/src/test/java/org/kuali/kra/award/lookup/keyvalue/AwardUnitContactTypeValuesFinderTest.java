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
package org.kuali.kra.award.lookup.keyvalue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.service.KNSServiceLocator;

import java.util.List;
import static org.junit.Assert.*;
public class AwardUnitContactTypeValuesFinderTest extends KcIntegrationTestBase {

    protected AwardUnitContactTypeValuesFinder valuesFinder;
    
    @Before
    public void setUp() throws Exception {
        valuesFinder = new AwardUnitContactTypeValuesFinder();
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testValuesFinder() throws Exception {
        List<KeyValue> keyLabels = valuesFinder.getKeyValues();
        assertFalse(keyLabels.isEmpty());
        for (KeyValue pair : keyLabels) {
            UnitAdministratorType contactType = KNSServiceLocator.getBusinessObjectService().findBySinglePrimaryKey(UnitAdministratorType.class, pair.getKey());
            assertEquals("U", contactType.getDefaultGroupFlag());
        }
    }
}
