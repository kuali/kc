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
package org.kuali.kra.iacuc.actions.notifyiacuc;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.iacuc.actions.IacucActionsKeyValuesBase;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionQualifierType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.iacuc.actions.submit.IacucValidProtoSubTypeQual;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.*;

/**
 * Finds the available set of Submission Qualifier Types for a Notify IRB request.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SubmissionQualifierTypeValuesFinder extends IacucActionsKeyValuesBase {

    @Override
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("submissionTypeCode", IacucProtocolSubmissionType.FYI);
        List<IacucValidProtoSubTypeQual> validProtoSubTypeQuals = (List<IacucValidProtoSubTypeQual>) getBusinessObjectService().findMatching(
                IacucValidProtoSubTypeQual.class, fieldValues);
        if (validProtoSubTypeQuals.isEmpty()) {
            keyValues.add(new ConcreteKeyValue("", "select"));
            Collection<IacucProtocolSubmissionQualifierType> submissionQualifierTypes = this.getKeyValuesService().findAll(
                    IacucProtocolSubmissionQualifierType.class);
            for (IacucProtocolSubmissionQualifierType submissionQualifierType : submissionQualifierTypes) {
                if (isAllowed(submissionQualifierType)) {
                    keyValues.add(new ConcreteKeyValue(submissionQualifierType.getSubmissionQualifierTypeCode(),
                        submissionQualifierType.getDescription()));
                }
            }
        } else {
            for (IacucValidProtoSubTypeQual typeQual : validProtoSubTypeQuals) {
                keyValues.add(new ConcreteKeyValue(typeQual.getSubmissionTypeQualCode(), typeQual.getSubmissionTypeQualifier()
                        .getDescription()));
            }
        }

        return keyValues;
    }

    /**
     * There are many submission qualifier types but only a few are available for a Notify IRB request.
     * 
     * @param submissionQualifierType the submission qualifier type
     * @return true if applicable; otherwise false
     */
    private boolean isAllowed(IacucProtocolSubmissionQualifierType submissionQualifierType) {
        String typeCodes[] = { IacucProtocolSubmissionQualifierType.AE_UADE, IacucProtocolSubmissionQualifierType.ANNUAL_REPORT,
                IacucProtocolSubmissionQualifierType.ANNUAL_REPORT, IacucProtocolSubmissionQualifierType.COMPLAINT,
                IacucProtocolSubmissionQualifierType.DEVIATION, IacucProtocolSubmissionQualifierType.IACUC_PROTOCOL_RELATED_COI_PROJECT,
                IacucProtocolSubmissionQualifierType.ELIGIBILITY_EXCEPTIONS_PROTOCOL_DEVIATIONS,
                IacucProtocolSubmissionQualifierType.SELF_REPORT_FOR_NONCOMPLIANCE };

        for (String typeCode : typeCodes) {
            if (StringUtils.equals(typeCode, submissionQualifierType.getSubmissionQualifierTypeCode())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    protected Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook() {
        return IacucCommitteeService.class;
    }
}
