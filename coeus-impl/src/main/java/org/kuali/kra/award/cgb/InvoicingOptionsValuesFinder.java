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
package org.kuali.kra.award.cgb;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.EnumValuesFinder;

public class InvoicingOptionsValuesFinder extends EnumValuesFinder {

	public InvoicingOptionsValuesFinder() {
		super(AwardCgbConstants.InvoicingOptions.Types.class);
	}
	
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> labels = super.getKeyValues();
        labels.add(0, new ConcreteKeyValue("", "Select"));
        return labels;
    }
	
    @Override
    protected String getEnumKey(Enum enm) {
        return ((AwardCgbConstants.InvoicingOptions.Types)enm).getCode();
    }

    @Override
    protected String getEnumLabel(Enum enm) {
        return ((AwardCgbConstants.InvoicingOptions.Types)enm).getName();
    }

}
