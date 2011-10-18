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
package org.kuali.kra.award.paymentreports;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.rice.core.util.KeyLabelPair;

/**
 * Report RegenerationType values finder class.
 */
public class ReportRegenerationTypeValuesFinder extends ExtendedPersistableBusinessObjectValuesFinder {
   
    /**
     * Get the report regeneration types and use the name as the key in the label.
     * @see org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues(){
        List<KeyLabelPair> labels = new ArrayList<KeyLabelPair>();
        for (ReportRegenerationType type : ReportRegenerationType.values()) {
            labels.add(new KeyLabelPair(type.name(), type.getDescription()));
        }
        return labels;
    }
}
