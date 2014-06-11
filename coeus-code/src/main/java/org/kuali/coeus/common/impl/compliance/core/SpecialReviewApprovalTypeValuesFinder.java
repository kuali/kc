/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.compliance.core;

import org.kuali.coeus.sys.framework.keyvalue.DataObjectValuesFinder;
import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.coeus.sys.framework.keyvalue.SortedValuesFinder;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * See {@link #getKeyValues()}.
 */
public class SpecialReviewApprovalTypeValuesFinder extends DataObjectValuesFinder {
    
    /**
     * Gets the keyvalue pair for {@link SpecialReviewApprovalType SpecialReviewApprovalType}.
     * The key is the specialReviewApprovalTypeCode and the value is the description.
     * 
     * @return a list of {@link KeyValue KeyValue}
     */
    @Override
    public List<KeyValue> getKeyValues() {
    	setKeyValueAttributes();
        final List<KeyValue> specialReviewApprovalTypes = super.getKeyValues();
        return specialReviewApprovalTypes;
    }

    protected void setKeyValueAttributes() {
        setDataObjectClass(SpecialReviewApprovalType.class);
        setKeyAttributeName("approvalTypeCode");
        setLabelAttributeName("description");
    }
    
}
