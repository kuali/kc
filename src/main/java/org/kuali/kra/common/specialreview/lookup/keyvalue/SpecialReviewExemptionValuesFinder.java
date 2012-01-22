/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.common.specialreview.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.ExemptionType;
import org.kuali.kra.lookup.keyvalue.SortedValuesFinder;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;

/**
 * See {@link #getKeyValues()}.
 */
public class SpecialReviewExemptionValuesFinder extends KeyValuesBase {

    private final KeyValuesFinder finder;
    
    /**
     * Creates the ProtocolSpecialReviewValuesFinder setting any internal dependencies to defaults.
     */
    public SpecialReviewExemptionValuesFinder() {
        PersistableBusinessObjectValuesFinder boFinder = new PersistableBusinessObjectValuesFinder();
        boFinder.setBusinessObjectClass(ExemptionType.class);
        boFinder.setKeyAttributeName("exemptionTypeCode");
        boFinder.setLabelAttributeName("description");
        
        this.finder = new SortedValuesFinder(boFinder);
    }
    
    /**
     * Creates the ProtocolSpecialReviewValuesFinder setting the wrapped finder.
     * @param finder The finder
     * @throws NullPointerException if the finder is null
     */
    SpecialReviewExemptionValuesFinder(final KeyValuesFinder finder) {
        if (finder == null) {
            throw new NullPointerException("the finder is null");
        }
        
        this.finder = finder;
    }
    
    /**
     * Gets the keyvalue pair for {@link ExemptionType ExemptionType}.
     * The key is the exemptionTypeCode and the value is the description.
     * @return a list of {@link KeyValue KeyValue}
     */
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyPair = new ArrayList<KeyValue>();
        keyPair.add(new ConcreteKeyValue("", ""));
        keyPair.addAll(finder.getKeyValues());
        return keyPair;
    }
}

