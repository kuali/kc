/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.lookup.keyvalues.KeyValuesFinder;
import org.kuali.core.lookup.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.kra.lookup.keyvalue.SortedValuesFinder;

/**
 * See {@link #getKeyValues()}.
 */
public class SpecialReviewApprovalTypeValuesFinder extends KeyValuesBase {
    
    private final KeyValuesFinder finder;
    
    /**
     * Creates the ExemptionTypeValuesFinder setting any internal dependencies to defaults
     */
    public SpecialReviewApprovalTypeValuesFinder() {
        PersistableBusinessObjectValuesFinder boFinder = new PersistableBusinessObjectValuesFinder();
        boFinder.setBusinessObjectClass(SpecialReviewApprovalType.class);
        boFinder.setKeyAttributeName("approvalTypeCode");
        boFinder.setLabelAttributeName("description");
        this.finder = new PrefixValuesFinder(new SortedValuesFinder(boFinder));
    }
    
    /**
     * Creates the ExemptionTypeValuesFinder setting the wrapped finder.
     * @param aFinder the finder
     * @throws NullPointerException if the finder is null
     */
    SpecialReviewApprovalTypeValuesFinder(final KeyValuesFinder aFinder) {
        if (aFinder == null) {
            throw new NullPointerException("the finder is null");
        }
        
        this.finder = aFinder;
    }
    
    /**
     * Gets the keyvalue pair for {@link SpecialReviewApprovalType SpecialReviewApprovalType}.
     * The key is the exemptionTypeCode and the value is the description.
     * 
     * @return a list of {@link KeyLabelPair KeyLabelPair}
     */
    public List<KeyLabelPair> getKeyValues() {
        @SuppressWarnings("unchecked")
        final List<KeyLabelPair> exemptionTypes = this.finder.getKeyValues();
        return exemptionTypes;
    }
}
