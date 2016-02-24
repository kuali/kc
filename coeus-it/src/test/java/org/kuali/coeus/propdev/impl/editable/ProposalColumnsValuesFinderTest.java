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
package org.kuali.coeus.propdev.impl.editable;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.editable.ProposalColumnsValuesFinder;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProposalColumnsValuesFinderTest extends KcIntegrationTestBase {
    
    private ProposalColumnsValuesFinder finder = null;

    @Before
    public void setUp() throws Exception {
        finder = new ProposalColumnsValuesFinder();
    }
    
    @Test
    public void testProposalColumnsValuesFinderRuns() {
        List<KeyValue> values = finder.getKeyValues();
        assertTrue(values.size() > 0);
    }

    @After
    public void tearDown() throws Exception {
    }

}
