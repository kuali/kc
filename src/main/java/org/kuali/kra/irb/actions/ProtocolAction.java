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
package org.kuali.kra.irb.actions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.common.notification.bo.NotificationTypeRecipient;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAssociate;
import org.kuali.kra.irb.actions.print.QuestionnairePrintOption;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class manages all the attributes needed to maintain a protocol action.
 */
public class ProtocolAction extends ProtocolAssociate {

    private static final long serialVersionUID = -2148599171919464303L;

    private static final String NEXT_ACTION_ID_KEY = "actionId";

    //not thread safe cannot be static  
    private transient SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private Long protocolActionId;

    private Integer actionId;

    private Integer submissionNumber;

    private Long submissionIdFk;

    private String protocolActionTypeCode;

    private String comments;

    private Timestamp actualActionDate;

    private Timestamp actionDate;

    private String prevSubmissionStatusCode;

    private String submissionTypeCode;

    private String prevProtocolStatusCode;

    // This will be used an indicator whether there is a follow up action  
    private String followupActionCode;

    @SkipVersioning
    private transient ProtocolSubmission protocolSubmission;

    private ProtocolActionType protocolActionType;

    private List<ProtocolCorrespondence> protocolCorrespondences;

    @SkipVersioning
    private transient List<ProtocolSubmissionDoc> protocolSubmissionDocs;

    private transient boolean isInFilterView = true;

    private transient int answerHeadersCount = 0;

    private transient QuestionnairePrintOption questionnairePrintOption;
    private transient CommitteeService committeeService;

    public ProtocolAction() {
    }

    public ProtocolAction(Protocol protocol, ProtocolSubmission protocolSubmission, String protocolActionTypeCode) {
        setProtocolId(protocol.getProtocolId());
        setProtocolNumber(protocol.getProtocolNumber());
        setSequenceNumber(0);
        setActionId(protocol.getNextValue(NEXT_ACTION_ID_KEY));
        setActualActionDate(new Timestamp(System.currentTimeMillis()));
        setActionDate(new Timestamp(System.currentTimeMillis()));
        setProtocolActionTypeCode(protocolActionTypeCode);
        setProtocol(protocol);
        if (protocolSubmission != null) {
            setSubmissionIdFk(protocolSubmission.getSubmissionId());
            setSubmissionNumber(protocolSubmission.getSubmissionNumber());
        }
    }

    public Long getProtocolActionId() {
        return protocolActionId;
    }

    public void setProtocolActionId(Long protocolActionId) {
        this.protocolActionId = protocolActionId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getProtocolActionTypeCode() {
        return protocolActionTypeCode;
    }

    public void setProtocolActionTypeCode(String protocolActionTypeCode) {
        this.protocolActionTypeCode = protocolActionTypeCode;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public Long getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Long submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Timestamp getActualActionDate() {
        return actualActionDate;
    }

    public void setActualActionDate(Timestamp actualActionDate) {
        this.actualActionDate = actualActionDate;
    }

    public Timestamp getActionDate() {
        return actionDate;
    }

    public void setActionDate(Timestamp actionDate) {
        this.actionDate = actionDate;
    }

    public void setProtocolSubmission(ProtocolSubmission protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    /**
     * 
     * Refreshes the protocol submission (if it doesn't exist) and returns it.
     * @return
     */
    public ProtocolSubmission getProtocolSubmission() {
        if (submissionIdFk == null) {
            protocolSubmission = null;
        } else {
            refreshReferenceObject("protocolSubmission");
        }
        return protocolSubmission;
    }

    public void setProtocolActionType(ProtocolActionType protocolActionType) {
        this.protocolActionType = protocolActionType;
    }

    /**
     * Refreshes the protocol action type (if it doesn't exist) and returns it.
     * @return
     */
    public ProtocolActionType getProtocolActionType() {
        if (StringUtils.isBlank(protocolActionTypeCode)) {
            protocolActionType = null;
        } else {
            refreshReferenceObject("protocolActionType");
        }
        return protocolActionType;
    }

    /**
     * 
     * This method returns an empty string of the action date is null, otherwise it returns a formated date.
     * @return
     */
    public String getActualActionDateString() {
        if (getActualActionDate() == null) {
            return "";
        }
        return getDateFormat().format(getActualActionDate());
    }

    /**
     * 
     * This method returns an empty string of the action date is null, otherwise it returns a formated date.
     * @return
     */
    public String getActionDateString() {
        if (getActionDate() == null) {
            return "";
        }
        return getDateFormat().format(getActionDate());
    }

    /*
     * Simpledateformat cause serialization issue if it not static final, so make it transient.
     * Also, need to recreate if it is retrieved from serialized doc.
     */
    private SimpleDateFormat getDateFormat() {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        }
        return dateFormat;
    }

    /**
     * 
     * This method calculates and returns the submission status as a string.
     * @return
     */
    public String getSubmissionStatusString() {
        String status = "";
        if (protocolSubmission == null) {
            status = "";
        } else {
            if (protocolSubmission.getSubmissionStatus() == null && submissionIdFk != null) {
                protocolSubmission.refreshReferenceObject("submissionStatus");
            }
            if (protocolSubmission.getSubmissionStatus() == null) {
                status = "";
            } else if (protocolSubmission.getSubmissionStatus().getDescription() == null) {
                status = "";
            } else {
                status = protocolSubmission.getSubmissionStatus().getDescription();
            }
        }
        return status;
    }

    public String getPrevSubmissionStatusCode() {
        return prevSubmissionStatusCode;
    }

    public void setPrevSubmissionStatusCode(String prevSubmissionStatusCode) {
        this.prevSubmissionStatusCode = prevSubmissionStatusCode;
    }

    public String getSubmissionTypeCode() {
        return submissionTypeCode;
    }

    public void setSubmissionTypeCode(String submissionTypeCode) {
        this.submissionTypeCode = submissionTypeCode;
    }

    public String getPrevProtocolStatusCode() {
        return prevProtocolStatusCode;
    }

    public void setPrevProtocolStatusCode(String prevProtocolStatusCode) {
        this.prevProtocolStatusCode = prevProtocolStatusCode;
    }

    /**
     * 
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        protocolActionId = null;
        submissionIdFk = null;
    }

    /**
     * 
     * This resets the foreign keys if there is no protocol submission.
     */
    public void resetForeignKeys() {
        if (protocolSubmission != null) {
            submissionIdFk = protocolSubmission.getSubmissionId();
        }
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((protocolActionId == null) ? 0 : protocolActionId.hashCode());
        result = prime * result + ((protocolActionTypeCode == null) ? 0 : protocolActionTypeCode.hashCode());
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProtocolAction other = (ProtocolAction) obj;
        if (actionId == null) {
            if (other.actionId != null) {
                return false;
            }
        } else if (!actionId.equals(other.actionId)) {
            return false;
        }
        if (protocolActionTypeCode == null) {
            if (other.protocolActionTypeCode != null) {
                return false;
            }
        } else if (!protocolActionTypeCode.equals(other.protocolActionTypeCode)) {
            return false;
        }
        return true;
    }

    public List<ProtocolCorrespondence> getProtocolCorrespondences() {
        return protocolCorrespondences;
    }

    public void setProtocolCorrespondences(List<ProtocolCorrespondence> protocolCorrespondences) {
        this.protocolCorrespondences = protocolCorrespondences;
    }

    public List<ProtocolSubmissionDoc> getProtocolSubmissionDocs() {
        return protocolSubmissionDocs;
    }

    public void setProtocolSubmissionDocs(List<ProtocolSubmissionDoc> protocolSubmissionDocs) {
        this.protocolSubmissionDocs = protocolSubmissionDocs;
    }

    public boolean getIsInFilterView() {
        return isInFilterView;
    }

    public void setIsInFilterView(boolean isInFilterView) {
        this.isInFilterView = isInFilterView;
    }

    public String getFollowupActionCode() {
        return followupActionCode;
    }

    public void setFollowupActionCode(String followupActionCode) {
        this.followupActionCode = followupActionCode;
    }

    public QuestionnairePrintOption getQuestionnairePrintOption() {
        return questionnairePrintOption;
    }

    public void setQuestionnairePrintOption(QuestionnairePrintOption questionnairePrintOption) {
        this.questionnairePrintOption = questionnairePrintOption;
    }

    public void setQuestionnairePrintOptionFromHelper(ActionHelper actionHelper) {
        if (getSubmissionNumber() != null
                && !ProtocolActionType.SUBMIT_TO_IRB.equals(getProtocolActionTypeCode())) {
            setAnswerHeadersCount(actionHelper.getAnswerHeaderCount(CoeusSubModule.PROTOCOL_SUBMISSION, Integer.toString(getSubmissionNumber())));
            if (getAnswerHeadersCount() > 0) {
                setQuestionnairePrintOption(getQnPrintOptionForAction(getProtocolNumber(),
                                getSubmissionNumber().toString(), CoeusSubModule.PROTOCOL_SUBMISSION, this));
            }
        } else if (ProtocolActionType.SUBMIT_TO_IRB.equals(getProtocolActionTypeCode())
                && ("Submitted to IRB").equals(getComments())) {
            if (getProtocol().isAmendment() || getProtocol().isRenewal()) {
                setQuestionnairePrintOption(getQnPrintOptionForAction(getProtocolNumber(),
                                getSequenceNumber().toString(), CoeusSubModule.AMENDMENT_RENEWAL, this));

            } else {
                setQuestionnairePrintOption(getQnPrintOptionForAction(getProtocolNumber(),
                                getInitialSequence(this, ""), CoeusSubModule.ZERO_SUBMODULE, this));
            }
        } else if (ProtocolActionType.SUBMIT_TO_IRB.equals(getProtocolActionTypeCode()) && StringUtils.isNotBlank(getComments())
                                                            && (getComments().startsWith("Amendment-") || getComments().startsWith("Renewal-"))) {
            String amendmentRenewalNumber = getAmendmentRenewalNumber(getComments());
            setQuestionnairePrintOption(getQnPrintOptionForAction(getProtocolNumber() + amendmentRenewalNumber, 
                                        getInitialSequence(this, amendmentRenewalNumber), CoeusSubModule.AMENDMENT_RENEWAL, this));
        }
    }
    
    private QuestionnairePrintOption getQnPrintOptionForAction(String itemKey, String subItemKey, String subItemCode, ProtocolAction protocolAction) {

        protocolAction.setAnswerHeadersCount(getAnswerHeaderCount(subItemCode, itemKey, subItemKey));
        if (protocolAction.getAnswerHeadersCount() > 0) {
            QuestionnairePrintOption qnPrintOption = new QuestionnairePrintOption();
            qnPrintOption.setItemKey(itemKey);
            qnPrintOption.setSubItemCode(subItemCode);
            qnPrintOption.setSubItemKey(subItemKey);
            return qnPrintOption;
        } else {
            return null;
        }
    }

    public int getAnswerHeaderCount(String moduleSubItemCode, String moduleItemKey, String moduleSubItemKey) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleItemCode", CoeusModule.IRB_MODULE_CODE);
        fieldValues.put("moduleItemKey", moduleItemKey);
        if (!moduleItemKey.contains("A") && !moduleItemKey.contains("R") && !getProtocol().isAmendment() && !getProtocol().isRenewal()) {
            fieldValues.put("moduleSubItemCode", moduleSubItemCode);
        }
        fieldValues.put("moduleSubItemKey", moduleSubItemKey);
        return getBusinessObjectService().countMatching(AnswerHeader.class, fieldValues);
        
    }
    
    private String getAmendmentRenewalNumber(String comment) {
        String retVal="";
        if (comment.startsWith("Amendment-")) {
            retVal = "A" + comment.substring(10, 13);
        } else {
            retVal = "R" + comment.substring(8, 11);
                     
        }
        return retVal;
    }
    

    /*
     * get the sequence number of the protocol that the action initially created
     */
    private String getInitialSequence(ProtocolAction protocolAction, String amendmentRenewalNumber) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("protocolNumber", protocolAction.getProtocolNumber() + amendmentRenewalNumber);
        if (StringUtils.isBlank(amendmentRenewalNumber)) {
            fieldValues.put("actionId", protocolAction.getActionId().toString());
        }
        else {
            fieldValues.put("submissionNumber", protocolAction.getSubmissionNumber().toString());
        }
        fieldValues.put("protocolActionTypeCode", ProtocolActionType.SUBMIT_TO_IRB);
        return ((List<ProtocolAction>) getBusinessObjectService().findMatchingOrderBy(ProtocolAction.class, fieldValues,
                "protocolActionId", true)).get(0).getProtocol().getSequenceNumber().toString();
    }


    public BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    public int getAnswerHeadersCount() {
        return answerHeadersCount;
    }

    public void setAnswerHeadersCount(int answerHeadersCount) {
        this.answerHeadersCount = answerHeadersCount;
    }
    
    // TODO : this might be a concern if the protocol has lots of correspondence, and we have to verify
    // for every single one.  We can't use a general check for this protocol because there is timing issue
    // for each actions.  Member may be active in some actions, but inactive for other actions.
    public boolean isActiveCommitteeMember() {
        boolean result = false;
        ProtocolSubmission submission = getSubmission();
        List<CommitteeMembership> committeeMembers = 
            getCommitteeService().getAvailableMembers(submission.getCommitteeId(),
                    submission.getScheduleId());
        if (CollectionUtils.isNotEmpty(committeeMembers)) {
            for (CommitteeMembership member : committeeMembers) {
                if (StringUtils.equals(GlobalVariables.getUserSession().getPrincipalId(), member.getPersonId())) {
                    result = true;
                    break;
                }
            }
        }        
        
        return result;
    }

    private ProtocolSubmission getSubmission() {
        ProtocolSubmission submission = null;
        this.refreshReferenceObject("protocolSubmission");
        if (protocolSubmission != null) {
            submission = protocolSubmission;
        } else {
            //  sort it by descending actionid
            // this sort 'protocol.getProtocolActions' messed up viewhistory. because the viewhistory is coming from action.
//            Collections.sort(getProtocol().getProtocolActions(), new Comparator<ProtocolAction>() {
//                public int compare(ProtocolAction action1, ProtocolAction action2) {
//                    return action1.getActionId().compareTo(action2.getActionId());
//                }
//            });
            for (ProtocolAction action : this.getProtocol().getSortedActions()) {
                if (action.getActionId() < this.actionId && action.getSubmissionNumber() != null) {
                    submission = getSubmissionForAction(action.getSubmissionNumber());
                }
            }
        }
        return submission;	
    }
    
    private ProtocolSubmission getSubmissionForAction(Integer submissionNumber) {

        for (ProtocolSubmission submission : getProtocol().getProtocolSubmissions()) {
            if (submission.getSubmissionNumber().equals(submissionNumber)) {
                return submission;
            }
        }
        return null;
        
        
    }
    
    public CommitteeService getCommitteeService() {
        if (committeeService == null) {
            committeeService = KraServiceLocator.getService(CommitteeService.class);
        }
        return committeeService;
    }

    public void setCommitteeService(CommitteeService committeeService) {
        this.committeeService = committeeService;
    }   
    

}
