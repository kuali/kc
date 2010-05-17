/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.actions.notifyirb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.irb.actions.IrbActionsKeyValuesBase;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.rice.core.util.KeyLabelPair;

/**
 * Finds the available set of Submission Qualifier Types for
 * a Notify IRB request.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SubmissionQualifierTypeValuesFinder extends IrbActionsKeyValuesBase {
    
    /**
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List<KeyLabelPair> getKeyValues() {
       
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        
        Collection<ProtocolSubmissionQualifierType> submissionQualifierTypes = 
                            this.getKeyValuesService().findAll(ProtocolSubmissionQualifierType.class);
        for (ProtocolSubmissionQualifierType submissionQualifierType : submissionQualifierTypes) {
            if (isAllowed(submissionQualifierType)) {
                keyValues.add(new KeyLabelPair(submissionQualifierType.getSubmissionQualifierTypeCode(), 
                                               submissionQualifierType.getDescription()));
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
