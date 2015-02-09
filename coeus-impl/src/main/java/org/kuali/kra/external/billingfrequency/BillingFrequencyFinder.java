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
package org.kuali.kra.external.billingfrequency;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillingFrequencyFinder extends KeyValuesBase  {

    @Override
    public List<KeyValue> getKeyValues() {
        return new ArrayList<>(Collections2.transform(Arrays.asList(BillingFrequencyValue.values()), new Function<BillingFrequencyValue, KeyValue>() {
            @Nullable
            @Override
            public KeyValue apply(BillingFrequencyValue input) {
                return new ConcreteKeyValue(input.getFrequency(), input.getFrequencyDescription());
            }
        }));
    }
}
