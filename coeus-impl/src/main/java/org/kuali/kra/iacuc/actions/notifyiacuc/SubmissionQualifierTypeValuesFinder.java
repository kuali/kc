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
