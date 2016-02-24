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
