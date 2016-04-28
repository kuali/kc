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
package org.kuali.kra.protocol.actions;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.protocol.ProtocolAssociateBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.notify.ProtocolActionAttachment;
import org.kuali.kra.protocol.actions.print.QuestionnairePrintOption;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.questionnaire.ProtocolSubmissionQuestionnaireHelper;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 
 * This class manages all the attributes needed to maintain a protocol action.
 */
public abstract class ProtocolActionBase extends ProtocolAssociateBase {

    private static final long serialVersionUID = -2148599171919464303L;

    protected static final String PROTOCOL_ACTION_ID_FIELD_KEY = "protocolActionId";
    protected static final String PROTOCOL_ACTION_TYPE_CODE_FIELD_KEY = "protocolActionTypeCode";
    protected static final String SUBMISSION_NUMBER_FIELD_KEY = "submissionNumber";
    protected static final String ACTION_ID_FIELD_KEY = "actionId";
    protected static final String PROTOCOL_NUMBER_FIELD_KEY = "protocolNumber";
    protected static final String COMMENT_PREFIX_RENEWAL = "Renewal-";
    protected static final String COMMENT_PREFIX_AMMENDMENT = "Amendment-";
    protected static final String COMMENT_PREFIX_FYI = "FYI-";

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
    private transient ProtocolSubmissionBase protocolSubmission;

    private ProtocolActionTypeBase protocolActionType;
    
    private List<ProtocolCorrespondence> protocolCorrespondences;

    private List<KcNotification> protocolNotifications;
    
    @SkipVersioning
    private transient List<ProtocolSubmissionDocBase> protocolSubmissionDocs;

    private transient boolean isInFilterView = true;
    
    private String createUser;
    
    private Timestamp createTimestamp;

    private ProtocolSubmissionQuestionnaireHelper questionnaireHelper;

    private transient CommitteeServiceBase committeeService;

    private transient QuestionnairePrintOption questionnairePrintOption;

    private transient ProtocolActionAttachment newActionAttachment;

    public ProtocolActionBase() {
    }

    public ProtocolActionBase(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission, String protocolActionTypeCode) {
        initializeProtocolAction(protocol, protocolSubmission, protocolActionTypeCode);
    }

    public ProtocolActionBase(ProtocolBase protocol, String protocolActionTypeCode) {
        initializeProtocolAction(protocol, null, protocolActionTypeCode);
    }
    
    protected void initializeProtocolAction(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission, String protocolActionTypeCode) {
        if (protocolSubmission != null) {
            setSubmissionIdFk(protocolSubmission.getSubmissionId());
            setSubmissionNumber(protocolSubmission.getSubmissionNumber());
        }
        setProtocolId(protocol.getProtocolId());
        setProtocolNumber(protocol.getProtocolNumber());
        setSequenceNumber(0);
        setActionId(protocol.getNextValue(ACTION_ID_FIELD_KEY));
        setActualActionDate(new Timestamp(System.currentTimeMillis()));
        setActionDate(new Timestamp(System.currentTimeMillis()));
        setProtocolActionTypeCode(protocolActionTypeCode);
        setProtocol(protocol);
        createUser = GlobalVariables.getUserSession().getPrincipalName();
        createTimestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
    	protocolCorrespondences = new ArrayList<ProtocolCorrespondence>();
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

    public void setProtocolSubmission(ProtocolSubmissionBase protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    /**
     * 
     * Refreshes the protocol submission (if it doesn't exist) and returns it.
     * @return
     */
    public ProtocolSubmissionBase getProtocolSubmission() {
        if (submissionIdFk == null) {
            protocolSubmission = null;
        } else {
            refreshReferenceObject("protocolSubmission");
        }
        return protocolSubmission;
    }

    public void setProtocolActionType(ProtocolActionTypeBase protocolActionType) {
        this.protocolActionType = protocolActionType;
    }

    /**
     * Refreshes the protocol action type (if it doesn't exist) and returns it.
     * @return
     */
    public ProtocolActionTypeBase getProtocolActionType() {
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

    @Override
    public void resetPersistenceState() {
        protocolActionId = null;
        submissionIdFk = null;
        for (KcNotification notification: getProtocolNotifications()) {
            notification.setOwningDocumentIdFk(null);
        }
    }

    /**
     * 
     * This resets the foreign keys if there is a protocol submission.
     */
    public void resetForeignKeys() {
        if (protocolSubmission != null) {
            submissionIdFk = protocolSubmission.getSubmissionId();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((protocolActionId == null) ? 0 : protocolActionId.hashCode());
        result = prime * result + ((protocolActionTypeCode == null) ? 0 : protocolActionTypeCode.hashCode());
        return result;
    }

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
        ProtocolActionBase other = (ProtocolActionBase) obj;
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

    public List<KcNotification> getProtocolNotifications() {
        if (protocolNotifications == null) {
            protocolNotifications = new ArrayList<KcNotification>();
        }
        return protocolNotifications;
    }

    public void setProtocolNotifications(List<KcNotification> notifications) {
        this.protocolNotifications = notifications;
    }

    public List<KcNotification> getFilteredProtocolNotifications() {
        List<KcNotification>unfilteredList = getProtocolNotifications();
        String currentUser = GlobalVariables.getUserSession().getPrincipalName().trim();
        if (!this.getProtocol().isUserNamedInProtocol(currentUser)) {
            return unfilteredList;
        } else {
            List<KcNotification>filteredList = new ArrayList<KcNotification>();
            for (KcNotification notification: unfilteredList) {
                if (currentUser.equals(notification.getCreateUser())) {
                    filteredList.add(notification);
                } else {
                    for (String recipient: notification.getRecipients().split(",")) {
                        if (currentUser.equals(recipient.trim())) {
                            filteredList.add(notification);
                            break;
                        }
                    }
                }
            }
            return filteredList;
        }
    }
    
    public void addProtocolNotification(KcNotification notification) {
        this.getProtocolNotifications().add(notification);
    }
    
    public List<ProtocolSubmissionDocBase> getProtocolSubmissionDocs() {
        return protocolSubmissionDocs;
    }

    public void setProtocolSubmissionDocs(List<ProtocolSubmissionDocBase> protocolSubmissionDocs) {
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

    public String getCreateUser() {
        return (createUser == null) ?getUpdateUser() : createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTimestamp() {
        return (createTimestamp == null) ? getUpdateTimestamp() : createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public int getAnswerHeaderCount(String moduleSubItemCode, String moduleItemKey, String moduleSubItemKey) {
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("moduleItemCode", getCoeusModule());
        fieldValues.put("moduleItemKey", moduleItemKey);
        if (!moduleItemKey.contains("A") && !moduleItemKey.contains("R") && !moduleItemKey.contains("F") && getProtocol().isNew()) {
            fieldValues.put("moduleSubItemCode", moduleSubItemCode);
        }
        fieldValues.put("moduleSubItemKey", moduleSubItemKey);
        return getBusinessObjectService().countMatching(AnswerHeader.class, fieldValues);
        
    }
    
    protected String getAmendmentRenewalNumber(String comment) {
        String retVal="";
        if (comment.startsWith(COMMENT_PREFIX_AMMENDMENT)) {
            retVal = "A" + comment.substring(10, 13);
        } else if (comments.startsWith(COMMENT_PREFIX_FYI)) {
            retVal = "F" + comment.substring(4,7);
        } else {
            retVal = "R" + comment.substring(8, 11);
                     
        }
        return retVal;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
    
    // TODO : this might be a concern if the protocol has lots of correspondence, and we have to verify
    // for every single one.  We can't use a general check for this protocol because there is timing issue
    // for each actions.  Member may be active in some actions, but inactive for other actions.
    public boolean isActiveCommitteeMember() {
        boolean result = false;
        ProtocolSubmissionBase submission = getSubmission();
        List<CommitteeMembershipBase> committeeMembers = 
            getCommitteeService().getAvailableMembers(submission.getCommitteeId(),
                    submission.getScheduleId());
        if (CollectionUtils.isNotEmpty(committeeMembers)) {
            for (CommitteeMembershipBase member : committeeMembers) {
                if (StringUtils.equals(GlobalVariables.getUserSession().getPrincipalId(), member.getPersonId())) {
                    result = true;
                    break;
                }
            }
        }        
        
        return result;
    }

    private ProtocolSubmissionBase getSubmission() {
        ProtocolSubmissionBase submission = null;
        this.refreshReferenceObject("protocolSubmission");
        if (protocolSubmission != null) {
            submission = protocolSubmission;
        } else {
            for (ProtocolActionBase action : this.getProtocol().getSortedActions()) {
                if (action.getActionId() < this.actionId && action.getSubmissionNumber() != null) {
                    submission = getSubmissionForAction(action.getSubmissionNumber());
                }
            }
        }
        return submission;  
    }
    
    private ProtocolSubmissionBase getSubmissionForAction(Integer submissionNumber) {

        for (ProtocolSubmissionBase submission : getProtocol().getProtocolSubmissions()) {
            if (submission.getSubmissionNumber().equals(submissionNumber)) {
                return submission;
            }
        }
        return null;
        
        
    }
    
    public CommitteeServiceBase getCommitteeService() {
        if (committeeService == null) {
            committeeService = KcServiceLocator.getService(getCommitteeServiceClassHook());
        }
        return committeeService;
    }
    
    protected abstract Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook();

    
    public void setCommitteeService(CommitteeServiceBase committeeService) {
        this.committeeService = committeeService;
    }   
    
    protected abstract String getCoeusModule();
    
    protected abstract ProtocolSubmissionQuestionnaireHelper getProtocolSubmissionQuestionnaireHelperHook(ProtocolBase protocol, String actionTypeCode, String submissionNumber);

    public void addNotification(KcNotification notification) {
        getProtocolNotifications().add(notification);       
    }

    public ProtocolSubmissionQuestionnaireHelper getQuestionnaireHelper() {
            setQuestionnaireHelper(getProtocolSubmissionQuestionnaireHelperHook(getProtocol(), protocolActionTypeCode, submissionNumber == null ? null : submissionNumber.toString()));
        questionnaireHelper.populateAnswers();
        return questionnaireHelper;
    }

    public void setQuestionnaireHelper(ProtocolSubmissionQuestionnaireHelper questionnaireHelper) {
        this.questionnaireHelper = questionnaireHelper;
    }
    
    public QuestionnairePrintOption getQuestionnairePrintOption() {
        return questionnairePrintOption;
    }

    public void setQuestionnairePrintOption(QuestionnairePrintOption questionnairePrintOption) {
        this.questionnairePrintOption = questionnairePrintOption;
    }

    protected QuestionnairePrintOption getQnPrintOptionForAction(String itemKey, String subItemKey, String subItemCode) {
    
        if (!getQuestionnaireHelper().getAnswerHeaders().isEmpty()) {
            QuestionnairePrintOption qnPrintOption = new QuestionnairePrintOption();
            qnPrintOption.setItemKey(itemKey);
            qnPrintOption.setSubItemCode(subItemCode);
            qnPrintOption.setSubItemKey(subItemKey);
            return qnPrintOption;
        } else {
            return null;
        }
    }

    public ProtocolActionAttachment getNewActionAttachment() {
        return newActionAttachment;
    }

    public void setNewActionAttachment(ProtocolActionAttachment newActionAttachment) {
        this.newActionAttachment = newActionAttachment;
    }
}
