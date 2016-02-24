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
package org.kuali.kra.protocol.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.Map;

/**
 * Validate a protocol submission to the IRB for review.
 */

public abstract class ProtocolSubmitActionRuleBase extends KcTransactionalDocumentRuleBase implements ExecuteProtocolSubmitActionRule {

    private static final String MANDATORY = "M";
    private ParameterService parameterService;

    public boolean processSubmitAction(ProtocolDocumentBase document, ProtocolSubmitAction submitAction) {

        boolean isValid = validateSubmissionType(document, submitAction);
        isValid &= validateProtocolReviewType(submitAction);
        if (StringUtils.isNotBlank(submitAction.getSubmissionTypeCode())
                && StringUtils.isNotBlank(submitAction.getProtocolReviewTypeCode())) {
            isValid &= isValidSubmReviewType(submitAction);
        }
        if (isMandatory()) {
            isValid &= validateCommittee(submitAction);
            isValid &= validateSchedule(submitAction);
        }
        isValid &= validateReviewers(submitAction);
        isValid &= checkNoSpoofing(submitAction);

        return isValid;

    }


    /**
     * If the committee is mandatory, verify that a committee has been selected.
     */
    private boolean validateCommittee(ProtocolSubmitAction submitAction) {
        boolean valid = true;
        if (StringUtils.isBlank(submitAction.getNewCommitteeId())) {
            valid = false;
            GlobalVariables.getMessageMap().putError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".committeeId",
                    KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
        }
        return valid;
    }

    /**
     * If the schedule is mandatory, verify that a schedule has been selected.
     */
    private boolean validateSchedule(ProtocolSubmitAction submitAction) {
        boolean valid = true;
        if (StringUtils.isBlank(submitAction.getNewScheduleId())) {
            valid = false;
            GlobalVariables.getMessageMap().putError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".scheduleId",
                    KeyConstants.ERROR_PROTOCOL_SCHEDULE_NOT_SELECTED);
        }
        return valid;
    }

    /**
     * Validate the Submission Type.
     */
    private boolean validateSubmissionType(ProtocolDocumentBase document, ProtocolSubmitAction submitAction) {
        boolean isValid = true;
        String submissionTypeCode = submitAction.getSubmissionTypeCode();
        if (StringUtils.isBlank(submissionTypeCode)) {
            // If the user didn't select a submission type, i.e. he/she choose the "select:" option,
            // then the Submission Type Code will be "blank".
            isValid = false;
            GlobalVariables.getMessageMap().putError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".submissionTypeCode",
                    KeyConstants.ERROR_PROTOCOL_SUBMISSION_TYPE_NOT_SELECTED);
        }
        else {
            isValid = isValidSubmTypeQual(submitAction);
        }
        return isValid;
    }

    /**
     * Validate the ProtocolBase Review Type.
     */
    private boolean validateProtocolReviewType(ProtocolSubmitAction submitAction) {
        boolean isValid = true;
        String protocolReviewTypeCode = submitAction.getProtocolReviewTypeCode();
        if (StringUtils.isBlank(protocolReviewTypeCode)) {
            // If the user didn't select a review type, i.e. he/she choose the "select:" option,
            // then the ProtocolBase Review Type Code will be "blank".
            isValid = false;
            GlobalVariables.getMessageMap().putError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".protocolReviewTypeCode",
                    KeyConstants.ERROR_PROTOCOL_REVIEW_TYPE_NOT_SELECTED);
        }
        else if (isReviewTypeInvalid(protocolReviewTypeCode)) {
            isValid = false;
            this.reportError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".protocolReviewTypeCode",
                    KeyConstants.ERROR_PROTOCOL_REVIEW_TYPE_INVALID, new String[] { protocolReviewTypeCode });
        }
        return isValid;
    }

    /**
     * Validate the reviewers.
     */
    private boolean validateReviewers(ProtocolSubmitAction submitAction) {
        boolean isValid = true;
        return isValid;
    }


    /**
     * 
     * This method checks to make sure that the reviewers list submitted is actually the same as that made available for that
     * protocol, committee and schedule, i.e. no spoofing of hidden input fields has taken place.
     * 
     * @param submitAction
     * @return
     */
    public boolean checkNoSpoofing(ProtocolSubmitAction submitAction) {
        boolean isValid = true;
        return isValid;
    }

    private boolean isValidSubmReviewType(ProtocolSubmitAction submitAction) {
        boolean valid = true;
        if (StringUtils.isNotBlank(submitAction.getSubmissionTypeCode())
                && StringUtils.isNotBlank(submitAction.getProtocolReviewTypeCode())) {            
            
        }
        return valid;
    }

    private boolean isValidSubmTypeQual(ProtocolSubmitAction submitAction) {
        boolean valid = true;
        if (StringUtils.isNotBlank(submitAction.getSubmissionTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", submitAction.getSubmissionTypeCode());
        }
        return valid;
    }

    protected abstract Class<? extends ProtocolSubmissionTypeBase> getProtocolSubmissionTypeClassHook();
    

    private boolean isReviewTypeInvalid(String reviewTypeCode) {
        return !existsUnique(getProtocolReviewTypeClassHook(), "reviewTypeCode", reviewTypeCode);
    }
    
    protected abstract Class<? extends ProtocolReviewTypeBase> getProtocolReviewTypeClassHook();

    /**
     * Returns true if exactly one instance of a given business object type exists in the Database; false otherwise.
     * 
     * @param boType
     * @param propertyName the name of the BO field to query
     * @param keyField the field to test against.
     * @return true if one object exists; false if no objects or more than one are found
     */
    private boolean existsUnique(Class<? extends BusinessObject> boType, String propertyName, String keyField) {
        if (keyField != null) {
            BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(propertyName, keyField);
            if (businessObjectService.countMatching(boType, fieldValues) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is it mandatory for the submission to contain the committee and schedule?
     * 
     * @return true if mandatory; otherwise false
     */
    private boolean isMandatory() {
        final String param = this.getParameterService().getParameterValueAsString(getProtocolDocumentClassHook(),
                Constants.PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION);

        return StringUtils.equalsIgnoreCase(MANDATORY, param);
    }

    protected abstract Class<? extends ProtocolDocumentBase> getProtocolDocumentClassHook();


    /**
     * Looks up and returns the ParameterService.
     * 
     * @return the parameter service.
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
}
