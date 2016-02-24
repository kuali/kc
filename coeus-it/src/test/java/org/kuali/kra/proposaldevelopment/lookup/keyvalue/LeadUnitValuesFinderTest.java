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
import org.kuali.coeus.propdev.impl.basic.LeadUnitValuesFinder;
import org.kuali.kra.keyvalue.ValuesFinderTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * This class tests LeadUnitValuesFinder.
 */
public class LeadUnitValuesFinderTest extends ValuesFinderTestBase {

    @Override
    protected Class<LeadUnitValuesFinder> getTestClass() {
        return LeadUnitValuesFinder.class;
    }

    @Override
    protected List<KeyValue> getKeyValues() {
        final List<KeyValue> keylabel = new ArrayList<KeyValue>();
        
        keylabel.add(new ConcreteKeyValue("", "select"));
        keylabel.add(new ConcreteKeyValue("BL-IIDC", "BL-IIDC - IND INST ON DISABILITY/COMMNTY"));
        keylabel.add(new ConcreteKeyValue("IN-MDEP", "IN-MDEP - MEDICINE DEPT"));
        keylabel.add(new ConcreteKeyValue("IN-PED", "IN-PED - PEDIATRICS"));
        
        return keylabel;
    }
    
    @Test
    public void testGetKeyValues() throws InstantiationException, IllegalAccessException {
    	UserSession previousUserSession = GlobalVariables.getUserSession();
    	GlobalVariables.setUserSession(new UserSession("woods"));
    	try {
    		super.testGetKeyValues();
    	} finally {
    		GlobalVariables.setUserSession(previousUserSession);
    	}
    }

}
