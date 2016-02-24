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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.irb.actions.IrbActionsKeyValuesBase;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionQualifierType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.irb.actions.submit.ValidProtoSubTypeQual;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.*;

/**
 * Finds the available set of Submission Qualifier Types for a Notify IRB request.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SubmissionQualifierTypeValuesFinder extends IrbActionsKeyValuesBase {

    @Override
    @SuppressWarnings("unchecked")
    public List<KeyValue> getKeyValues() {

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("submissionTypeCode", ProtocolSubmissionType.NOTIFY_IRB);
        List<ValidProtoSubTypeQual> validProtoSubTypeQuals = (List<ValidProtoSubTypeQual>) getBusinessObjectService().findMatching(
                ValidProtoSubTypeQual.class, fieldValues);
        if (validProtoSubTypeQuals.isEmpty()) {
            keyValues.add(new ConcreteKeyValue("", "select"));
            Collection<ProtocolSubmissionQualifierType> submissionQualifierTypes = this.getKeyValuesService().findAll(
                    ProtocolSubmissionQualifierType.class);
            for (ProtocolSubmissionQualifierType submissionQualifierType : submissionQualifierTypes) {
                if (isAllowed(submissionQualifierType)) {
                    keyValues.add(new ConcreteKeyValue(submissionQualifierType.getSubmissionQualifierTypeCode(),
                        submissionQualifierType.getDescription()));
                }
            }
        } else {
            for (ValidProtoSubTypeQual typeQual : validProtoSubTypeQuals) {
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
    private boolean isAllowed(ProtocolSubmissionQualifierType submissionQualifierType) {
        String typeCodes[] = { ProtocolSubmissionQualifierType.AE_UADE, ProtocolSubmissionQualifierType.DSMB_REPORT,
                ProtocolSubmissionQualifierType.ANNUAL_REPORT, ProtocolSubmissionQualifierType.COMPLAINT,
                ProtocolSubmissionQualifierType.DEVIATION, ProtocolSubmissionQualifierType.COI_REPORT,
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
