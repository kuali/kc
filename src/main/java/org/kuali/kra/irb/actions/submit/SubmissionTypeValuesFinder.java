/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.submit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * Finds the available set of Submission Types when a protocol
 * is submitted for review by the IRB Committee.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SubmissionTypeValuesFinder extends KeyValuesBase {
    
    /**
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
       
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        
        KeyValuesService keyValuesService = (KeyValuesService) KraServiceLocator.getService("keyValuesService");
        Collection<ProtocolSubmissionType> submissionTypes = keyValuesService.findAll(ProtocolSubmissionType.class);
        for (ProtocolSubmissionType submissionType : submissionTypes) {
            if (isSubmitForReviewType(submissionType)) {
                keyValues.add(new KeyLabelPair(submissionType.getSubmissionTypeCode(), submissionType.getDescription()));
            }
        }
        
        return keyValues;
    }

    /**
     * There are many submission types but only a few are available
     * for a submission for a protocol that will be reviewed.
     * @param submissionType the submission type
     * @return true if applicable for a review submission; otherwise false
     */
    private boolean isSubmitForReviewType(ProtocolSubmissionType submissionType) {
        String typeCodes[] = { ProtocolSubmissionType.INITIAL_SUBMISSION,
                               ProtocolSubmissionType.RESPONSE_TO_PREV_IRB_NOTIFICATION,
                               ProtocolSubmissionType.AMENDMENT,
                               ProtocolSubmissionType.CONTINUATION,
                               ProtocolSubmissionType.CONTINUATION_WITH_AMENDMENT };
        
        for (String typeCode : typeCodes) {
            if (StringUtils.equals(typeCode, submissionType.getSubmissionTypeCode())) {
                return true;
            }
        }
        return false;
    }
}
