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
package org.kuali.kra.iacuc.actions.notifyiacuc;

import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.iacuc.actions.submit.IacucValidProtoSubRevType;
import org.kuali.kra.irb.actions.IrbActionsKeyValuesBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmissionReviewTypeValuesFinder extends IrbActionsKeyValuesBase {
    
    private static final long serialVersionUID = 1525606389532878075L;

    @Override
    public List<KeyValue> getKeyValues() {
       
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("submissionTypeCode", IacucProtocolSubmissionType.FYI);
        List<IacucValidProtoSubRevType> validProtoSubRevTypes = (List<IacucValidProtoSubRevType>) getBusinessObjectService().findMatching(
                IacucValidProtoSubRevType.class, fieldValues);
        if (validProtoSubRevTypes.isEmpty()) {
            List<IacucProtocolReviewType> reviewTypes = (List<IacucProtocolReviewType>) getBusinessObjectService().findAll(
                    IacucProtocolReviewType.class);
            for (IacucProtocolReviewType reviewType : reviewTypes) {
                keyValues.add(new ConcreteKeyValue(reviewType.getReviewTypeCode(), 
                        reviewType.getDescription()));
            }
            
        } else {
            for (IacucValidProtoSubRevType submRevType :  validProtoSubRevTypes) {
                keyValues.add(new ConcreteKeyValue(submRevType.getProtocolReviewTypeCode(), submRevType.getProtocolReviewType().getDescription()));
            }
        }
        
        return keyValues;
    }
}
