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
package org.kuali.kra.irb.actions.notifyirb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.actions.IrbActionsKeyValuesBase;
import org.kuali.kra.irb.actions.submit.ProtocolReviewType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ValidProtoSubRevType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

public class SubmissionReviewTypeValuesFinder extends IrbActionsKeyValuesBase {
    
    /**
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
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

    /**
     * There are many submission qualifier types but only a few are available
     * for a Notify IRB request.
     * @param submissionQualifierType the submission qualifier type
     * @return true if applicable; otherwise false
     */
    private boolean isAllowed(ProtocolSubmissionQualifierType submissionQualifierType) {
        String typeCodes[] = { ProtocolSubmissionQualifierType.AE_UADE,
                               ProtocolSubmissionQualifierType.DSMB_REPORT,
                               ProtocolSubmissionQualifierType.ANNUAL_REPORT,
                               ProtocolSubmissionQualifierType.COMPLAINT,
                               ProtocolSubmissionQualifierType.DEVIATION,
                               ProtocolSubmissionQualifierType.COI_REPORT,
                               ProtocolSubmissionQualifierType.REQUEST_FOR_ELIGIBILITY_EX,
                               ProtocolSubmissionQualifierType.SELF_REPORT_NON_COMPLIANCE };
        
        for (String typeCode : typeCodes) {
            if (StringUtils.equals(typeCode, submissionQualifierType.getSubmissionQualifierTypeCode())) {
                return true;
            }
        }
        return false;
    }
}