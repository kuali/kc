/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.notifyiacuc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionQualifierType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.iacuc.actions.submit.IacucValidProtoSubRevType;
import org.kuali.kra.irb.actions.IrbActionsKeyValuesBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

public class SubmissionReviewTypeValuesFinder extends IrbActionsKeyValuesBase {
    
    private static final long serialVersionUID = 1525606389532878075L;

    /**
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
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
                keyValues.add(new ConcreteKeyValue(submRevType.getIacucProtocolReviewTypeCode(), 
                        submRevType.getIacucProtocolReviewType().getDescription()));
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
    private boolean isAllowed(IacucProtocolSubmissionQualifierType submissionQualifierType) {
        String typeCodes[] = { IacucProtocolSubmissionQualifierType.AE_UADE,
                IacucProtocolSubmissionQualifierType.ANNUAL_REPORT,
                IacucProtocolSubmissionQualifierType.ANNUAL_REPORT,
                IacucProtocolSubmissionQualifierType.COMPLAINT,
                IacucProtocolSubmissionQualifierType.DEVIATION,
                IacucProtocolSubmissionQualifierType.IACUC_PROTOCOL_RELATED_COI_PROJECT,
                IacucProtocolSubmissionQualifierType.ELIGIBILITY_EXCEPTIONS_PROTOCOL_DEVIATIONS,
                IacucProtocolSubmissionQualifierType.SELF_REPORT_FOR_NONCOMPLIANCE };
        
        for (String typeCode : typeCodes) {
            if (StringUtils.equals(typeCode, submissionQualifierType.getSubmissionQualifierTypeCode())) {
                return true;
            }
        }
        return false;
    }
}