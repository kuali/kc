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
package org.kuali.coeus.common.budget.impl.core;

import org.kuali.kra.infrastructure.OnOffCampusFlagConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

public class BudgetOnOffCampusValuesFinder extends UifKeyValuesFinderBase {
    
    /**
     * Constructs the list of Budget Fiscal Years.  Each entry
     * in the list is a &lt;key, value&gt; pair, where the "key" is the unique
     * status code and the "value" is the textual description that is viewed
     * by a user.
     * 
     * Until it's determined if the list should be obtained from a database,
     * the list will be canned, covering 2008 - 2010
     * 
     * @return the list of &lt;key, value&gt; pairs of abstract types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> KeyValues = new ArrayList<KeyValue>();

        for (OnOffCampusFlagConstants onOffCampusFlagConstants : OnOffCampusFlagConstants.values()) {
            KeyValues.add(new ConcreteKeyValue(onOffCampusFlagConstants.getCode(), onOffCampusFlagConstants.getDescription()));
        }
        
        return KeyValues; 
    }
    
}
