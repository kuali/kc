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
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;

import javax.annotation.Nullable;
import java.util.*;

public class BillingFrequencyLookupable extends AbstractLookupableHelperServiceImpl {

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        Collection<BillingFrequencyValue> values = Arrays.asList(BillingFrequencyValue.values());
        if (fieldValues != null) {
            final String frequency = fieldValues.get("frequency");
            final String frequencyDescription = fieldValues.get("frequencyDescription");


            if (frequency != null) {
                values = Collections2.filter(values, new Predicate<BillingFrequencyValue>() {
                    @Override
                    public boolean apply(BillingFrequencyValue input) {
                        return input.getFrequency().matches(toRegex(frequency));
                    }
                });
            }

            if (frequencyDescription != null) {
                values = Collections2.filter(values, new Predicate<BillingFrequencyValue>() {
                    @Override
                    public boolean apply(BillingFrequencyValue input) {
                        return input.getFrequency().matches(toRegex(toRegex(frequencyDescription)));
                    }
                });
            }
        }
        return new ArrayList<>(Collections2.transform(values, new Function<BillingFrequencyValue, BillingFrequency>() {

            @Nullable
            @Override
            public BillingFrequency apply(BillingFrequencyValue input) {
                BillingFrequency bf = new BillingFrequency();
                bf.setFrequency(input.getFrequency());
                bf.setFrequencyDescription(input.getFrequencyDescription());
                return bf;
            }
        }));
    }

    protected String toRegex(String s) {
        return s.replaceAll("/*", ".*").replaceAll("%", ".*");
    }
}
