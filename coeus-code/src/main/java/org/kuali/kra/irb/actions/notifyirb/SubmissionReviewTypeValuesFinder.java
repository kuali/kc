/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.irb.actions.notifyirb;

import org.kuali.kra.irb.actions.IrbActionsKeyValuesBase;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ValidProtoSubRevType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmissionReviewTypeValuesFinder extends IrbActionsKeyValuesBase {
    
    @Override
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {
       
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("submissionTypeCode", ProtocolSubmissionType.NOTIFY_IRB);
        List<ValidProtoSubRevType> validProtoSubRevTypes = (List<ValidProtoSubRevType>) getBusinessObjectService().findMatching(
                ValidProtoSubRevType.class, fieldValues);
        if (validProtoSubRevTypes.isEmpty()) {
            List<ProtocolReviewType> reviewTypes = (List<ProtocolReviewType>) getBusinessObjectService().findAll(
                    ProtocolReviewType.class);
            for (ProtocolReviewType reviewType : reviewTypes) {
                keyValues.add(new ConcreteKeyValue(reviewType.getReviewTypeCode(), 
                        reviewType.getDescription()));
            }
            
        } else {
            for (ValidProtoSubRevType submRevType :  validProtoSubRevTypes) {
                keyValues.add(new ConcreteKeyValue(submRevType.getProtocolReviewTypeCode(), 
                        submRevType.getProtocolReviewType().getDescription()));
            }
        }
        
        return keyValues;
    }
}