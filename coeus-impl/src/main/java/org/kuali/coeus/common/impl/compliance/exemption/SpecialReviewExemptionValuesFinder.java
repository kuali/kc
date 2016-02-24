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
package org.kuali.coeus.common.impl.compliance.exemption;

import org.kuali.coeus.sys.framework.keyvalue.SortedValuesFinder;
import org.kuali.coeus.common.framework.compliance.exemption.ExemptionType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

/**
 * See {@link #getKeyValues()}.
 */
public class SpecialReviewExemptionValuesFinder extends UifKeyValuesFinderBase {

    private final KeyValuesFinder finder;
    
    /**
     * Creates the ProtocolSpecialReviewValuesFinder setting any internal dependencies to defaults.
     */
    public SpecialReviewExemptionValuesFinder() {
        PersistableBusinessObjectValuesFinder boFinder = new PersistableBusinessObjectValuesFinder();
        boFinder.setBusinessObjectClass(ExemptionType.class);
        boFinder.setKeyAttributeName("code");
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
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyPair = new ArrayList<KeyValue>();
        keyPair.add(new ConcreteKeyValue("", ""));
        keyPair.addAll(finder.getKeyValues());
        return keyPair;
    }
}

