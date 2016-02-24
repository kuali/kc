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
package org.kuali.kra.irb.actions.submit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.submit.ExecuteProtocolSubmitActionRule;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBeanBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Validate a protocol submission to the IRB for review.
 */

public class ProtocolSubmitActionRule extends KcTransactionalDocumentRuleBase implements ExecuteProtocolSubmitActionRule {

    private static final String MANDATORY = "M";
    private ParameterService parameterService;
    private CommitteeService committeeService;

    /**
     * @see org.kuali.kra.irb.actions.submit.ExecuteProtocolSubmitActionRule#processSubmitAction(org.kuali.kra.irb.ProtocolDocument,
     *      org.kuali.kra.irb.actions.submit.ProtocolSubmitAction)
     */
    public boolean processSubmitAction(ProtocolDocumentBase document, org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction submitAction) {

        boolean isValid = validateSubmissionType((ProtocolDocument)document, (ProtocolSubmitAction) submitAction);
        isValid &= validateProtocolReviewType((ProtocolSubmitAction) submitAction);
        if (StringUtils.isNotBlank(submitAction.getSubmissionTypeCode())
                && StringUtils.isNotBlank(submitAction.getProtocolReviewTypeCode())) {
            isValid &= isValidSubmReviewType((ProtocolSubmitAction) submitAction);
        }
        if (isMandatory()) {
            isValid &= validateCommittee((ProtocolSubmitAction) submitAction);
            isValid &= validateSchedule((ProtocolSubmitAction) submitAction);
        }
        isValid &= validateCheckLists((ProtocolSubmitAction) submitAction);
        isValid &= validateReviewers((ProtocolSubmitAction) submitAction);
        isValid &= checkNoSpoofing((ProtocolSubmitAction) submitAction);

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
    private boolean validateSubmissionType(ProtocolDocument document, ProtocolSubmitAction submitAction) {
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
     * Validate the Protocol Review Type.
     */
    private boolean validateProtocolReviewType(ProtocolSubmitAction submitAction) {
        boolean isValid = true;
        String protocolReviewTypeCode = submitAction.getProtocolReviewTypeCode();
        if (StringUtils.isBlank(protocolReviewTypeCode)) {
            // If the user didn't select a review type, i.e. he/she choose the "select:" option,
            // then the Protocol Review Type Code will be "blank".
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
     * Validate the checklist. There must be at least one check list item selected if the review type is exempt or expedited.
     */
    private boolean validateCheckLists(ProtocolSubmitAction submitAction) {
        String protocolReviewTypeCode = submitAction.getProtocolReviewTypeCode();
        if (StringUtils.equals(protocolReviewTypeCode, ProtocolReviewType.EXEMPT_STUDIES_REVIEW_TYPE_CODE)) {
            List<ExemptStudiesCheckListItem> checkList = submitAction.getExemptStudiesCheckList();
            for (ExemptStudiesCheckListItem item : checkList) {
                if (item.getChecked()) {
                    return true;
                }
            }
            reportError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY, KeyConstants.ERROR_PROTOCOL_AT_LEAST_ONE_CHECKLIST_ITEM);
            return false;
        }
        else if (StringUtils.equals(protocolReviewTypeCode, ProtocolReviewType.EXPEDITED_REVIEW_TYPE_CODE)) {
            List<ExpeditedReviewCheckListItem> checkList = submitAction.getExpeditedReviewCheckList();
            for (ExpeditedReviewCheckListItem item : checkList) {
                if (item.getChecked()) {
                    return true;
                }
            }
            reportError(Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY, KeyConstants.ERROR_PROTOCOL_AT_LEAST_ONE_CHECKLIST_ITEM);
            return false;
        }
        return true;
    }


    /**
     * Validate the reviewers.
     */
    private boolean validateReviewers(ProtocolSubmitAction submitAction) {
        boolean isValid = true;
        List<ProtocolReviewerBeanBase> reviewers = submitAction.getReviewers();

        for (int i = 0; i < reviewers.size(); i++) {
            ProtocolReviewerBean reviewer = (ProtocolReviewerBean) reviewers.get(i);
            if (!isReviewerValid(reviewer, i)) {
                isValid = false;
            }
        }
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
        List<ProtocolReviewerBean> submittedReviewers = (List) submitAction.getReviewers();
        if (null != submittedReviewers && submittedReviewers.size() > 0) {
            if (StringUtils.isBlank(submitAction.getCommitteeId())) {
                isValid = false;
            }
            else {
                List<CommitteeMembership> actualReviewers = (List) submitAction.getProtocol().filterOutProtocolPersonnel(
                        getCommitteeService().getAvailableMembers(submitAction.getCommitteeId(), submitAction.getScheduleId()));
                for (int i = 0; i < submittedReviewers.size(); i++) {
                    ProtocolReviewerBean reviewer = submittedReviewers.get(i);
                    if (!isReviewerInList(reviewer, actualReviewers, i)) {
                        isValid = false;
                    }
                }
            }
        }
        return isValid;
    }


    private boolean isReviewerInList(ProtocolReviewerBean reviewer, List<CommitteeMembership> actualReviewers, int reviewerIndex) {
        boolean valid = false;
        for (CommitteeMembership member : actualReviewers) {
            if (!StringUtils.isBlank(member.getPersonId())) {
                if (StringUtils.equals(reviewer.getPersonId(), member.getPersonId())) {
                    valid = true;
                    break;
                }
            }
            else {
                if (StringUtils.equals(reviewer.getPersonId(), member.getRolodexId().toString())) {
                    valid = true;
                    break;
                }
            }
        }

        if (!valid) {
            String parameterName = Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".reviewer[" + reviewerIndex
                    + "].reviewerUnavailable";
            this.reportError(parameterName, KeyConstants.ERROR_PROTOCOL_REVIEWER_NOT_AVAILABLE, reviewer.getFullName());
        }

        return valid;
    }


    /**
     * This method tests if the fields for a given reviewer have legal values.
     * 
     * @param reviewer
     * @param reviewerIndex - the index of the reviewer in the list of reviewers that was sent to the client
     */
    private boolean isReviewerValid(ProtocolReviewerBean reviewer, int reviewerIndex) {
        boolean isValid = true;
        String reviewerTypeCode = reviewer.getReviewerTypeCode();

        String parameterName = Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".reviewer[" + reviewerIndex + "].reviewerTypeCode";

        // test if type code is valid
        if (!StringUtils.isBlank(reviewerTypeCode) && isReviewerTypeInvalid(reviewerTypeCode)) {
            isValid = false;
            this.reportError(parameterName, KeyConstants.ERROR_PROTOCOL_REVIEWER_TYPE_INVALID, reviewer.getFullName());
        }

        return isValid;
    }

    private boolean isValidSubmReviewType(ProtocolSubmitAction submitAction) {
        boolean valid = true;
        if (StringUtils.isNotBlank(submitAction.getSubmissionTypeCode())
                && StringUtils.isNotBlank(submitAction.getProtocolReviewTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", submitAction.getSubmissionTypeCode());
            List<ValidProtoSubRevType> validProtoSubRevTypes = (List<ValidProtoSubRevType>) getBusinessObjectService()
                    .findMatching(ValidProtoSubRevType.class, fieldValues);
            if (!validProtoSubRevTypes.isEmpty()) {
                List<String> reviewTypes = new ArrayList<String>();
                for (ValidProtoSubRevType validProtoSubRevType : validProtoSubRevTypes) {
                    reviewTypes.add(validProtoSubRevType.getProtocolReviewTypeCode());
                }
                if (!reviewTypes.contains(submitAction.getProtocolReviewTypeCode())
                        && !isReviewTypeInvalid(submitAction.getProtocolReviewTypeCode())) {
                    GlobalVariables.getMessageMap().putError(
                            Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".protocolReviewTypeCode",
                            KeyConstants.INVALID_SUBMISSION_REVIEW_TYPE,
                            new String[] {
                                    ((ProtocolSubmissionType) getBo(ProtocolSubmissionType.class, "submissionTypeCode",
                                            submitAction.getSubmissionTypeCode())).getDescription(),
                                    ((ProtocolReviewType) getBo(ProtocolReviewType.class, "reviewTypeCode",
                                            submitAction.getProtocolReviewTypeCode())).getDescription() });
                    valid = false;
                }

            }
        }
        return valid;
    }

    private boolean isValidSubmTypeQual(ProtocolSubmitAction submitAction) {
        boolean valid = true;
        if (StringUtils.isNotBlank(submitAction.getSubmissionTypeCode())) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put("submissionTypeCode", submitAction.getSubmissionTypeCode());
            List<ValidProtoSubTypeQual> validProtoSubTypeQuals = (List<ValidProtoSubTypeQual>) getBusinessObjectService()
                    .findMatching(ValidProtoSubTypeQual.class, fieldValues);
            if (!validProtoSubTypeQuals.isEmpty()) {
                List<String> typeQuals = new ArrayList<String>();
                for (ValidProtoSubTypeQual validProtoSubTypeQual : validProtoSubTypeQuals) {
                    typeQuals.add(validProtoSubTypeQual.getSubmissionTypeQualCode());
                }
                if (StringUtils.isBlank(submitAction.getSubmissionQualifierTypeCode())
                        || !typeQuals.contains(submitAction.getSubmissionQualifierTypeCode())) {
                    String desc = "";
                    ProtocolSubmissionQualifierType typeQual = (ProtocolSubmissionQualifierType) getBo(
                            ProtocolSubmissionQualifierType.class, "submissionQualifierTypeCode",
                            submitAction.getSubmissionQualifierTypeCode());
                    if (typeQual != null) {
                        desc = typeQual.getDescription();
                    }
                    GlobalVariables.getMessageMap().putError(
                            Constants.PROTOCOL_SUBMIT_ACTION_PROPERTY_KEY + ".submissionQualifierTypeCode",
                            KeyConstants.INVALID_SUBMISSION_TYPE_QUALIFIER,
                            new String[] {
                                    ((ProtocolSubmissionType) getBo(ProtocolSubmissionType.class, "submissionTypeCode",
                                            submitAction.getSubmissionTypeCode())).getDescription(), desc });
                    valid = false;
                }

            }
        }
        return valid;
    }

    private boolean isReviewTypeInvalid(String reviewTypeCode) {
        return !existsUnique(ProtocolReviewType.class, "reviewTypeCode", reviewTypeCode);
    }

    private boolean isReviewerTypeInvalid(String reviewerTypeCode) {
        return !existsUnique(ProtocolReviewerType.class, "reviewerTypeCode", reviewerTypeCode);
    }

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

    @SuppressWarnings("unchecked")
    private BusinessObject getBo(Class<? extends BusinessObject> boType, String propertyName, String keyField) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(propertyName, keyField);

        List<BusinessObject> results = (List<BusinessObject>) getBusinessObjectService().findMatching(boType, fieldValues);
        if (results.isEmpty()) {
            return null;
        }
        else {
            return results.get(0);
        }
    }

    /**
     * Is it mandatory for the submission to contain the committee and schedule?
     * 
     * @return true if mandatory; otherwise false
     */
    private boolean isMandatory() {
        final String param = this.getParameterService().getParameterValueAsString(ProtocolDocument.class,
                Constants.PARAMETER_IRB_COMM_SELECTION_DURING_SUBMISSION);

        return StringUtils.equalsIgnoreCase(MANDATORY, param);
    }

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

    private CommitteeService getCommitteeService() {
        if (null == this.committeeService) {
            this.committeeService = KcServiceLocator.getService(CommitteeService.class);
        }
        return this.committeeService;
    }

    /**
     * 
     * This method can be used in production for injecting a real committee service, and in testing it can be used for setting a
     * mock service.
     * 
     * @param committeeService
     */
    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }

}
