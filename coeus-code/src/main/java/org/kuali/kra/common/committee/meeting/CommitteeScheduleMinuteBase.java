/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.common.committee.meeting;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.common.committee.bo.CommitteeScheduleBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewableBase;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * This is BO class for committee schedule minute. 
 */
public abstract class CommitteeScheduleMinuteBase<CSM extends CommitteeScheduleMinuteBase<CSM, CS>,
                                              CS extends CommitteeScheduleBase<CS, ?, ?, CSM>> 

                                              extends ProtocolReviewableBase<CS> implements Cloneable {

    private static final long serialVersionUID = -2294619582524055884L;

    private static final String PERSON_NOT_FOUND_FORMAT_STRING = "%s (not found)";

    private Long commScheduleMinutesId;

    private Long scheduleIdFk;

    private Integer entryNumber;

    private String minuteEntryTypeCode;

    private String protocolContingencyCode;

    private Long protocolIdFk;

    private Long commScheduleActItemsIdFk;

    private Long submissionIdFk;

    private boolean privateCommentFlag;

    private boolean finalFlag;

    private Long protocolReviewerIdFk;

    private Long protocolOnlineReviewIdFk;

    private ProtocolContingencyBase protocolContingency;

    private MinuteEntryType minuteEntryType;

    private CommScheduleActItemBase commScheduleActItem;

    private CS committeeSchedule;

    private ProtocolReviewer protocolReviewer;

    private String createUser;

    private Timestamp createTimestamp;

    @SkipVersioning
    private transient ProtocolOnlineReviewBase protocolOnlineReview;

    private String minuteEntry;

    // TODO : not sure how this protocols yet.  
    @SkipVersioning
    private List<ProtocolBase> protocols;

    @SkipVersioning
    private ProtocolBase protocol;
    
    private boolean generateAttendance = false;

    @SkipVersioning
    private transient String createUserFullName;

    @SkipVersioning
    private transient String updateUserFullName;

    private transient boolean displayReviewerName;
    
    private transient boolean readOnly = true;
    private transient KcPersonService kcPersonService;
    /*
     * This comparator orders CommitteeScheduleMinuteBase by entry type first and then by entry type detail (if available)
     */
    @SuppressWarnings("rawtypes")
    public static final Comparator<CommitteeScheduleMinuteBase> entryTypeComparator = new Comparator<CommitteeScheduleMinuteBase>() {

        public int compare(CommitteeScheduleMinuteBase csm1, CommitteeScheduleMinuteBase csm2) {
            int retVal = 0;
            // first sort by protocol number if possible  
            if ((csm1.getProtocolIdFk() != null) && (csm2.getProtocolIdFk() != null)) {
                retVal = csm1.getProtocol().getProtocolNumber().compareTo(csm2.getProtocol().getProtocolNumber());
            } else if ((csm1.getProtocolIdFk() == null) && (csm2.getProtocolIdFk() != null)) {
                retVal = -1;
            } else if ((csm1.getProtocolIdFk() != null) && (csm2.getProtocolIdFk() == null)) {
                retVal = 1;
            }
            // if still same, check entry type then timestamps  
            if (retVal == 0) {
                retVal = csm1.getMinuteEntryType().compareTo(csm2.getMinuteEntryType());
                // if not entry type then try time of entries  
                if ((retVal == 0) && (csm1.getUpdateTimestamp() != null) && (csm2.getUpdateTimestamp() != null)) {
                    retVal = csm1.getUpdateTimestamp().compareTo(csm2.getUpdateTimestamp());
                }
            }
            return retVal;
        }
    };

    /**
     * Constructs a CommitteeScheduleMinuteBase.
     */
    public CommitteeScheduleMinuteBase() {
    }

    /**
     * Constructs a CommitteeScheduleMinuteBase with a default minute entry.
     * @param minuteEntryTypeCode the type code for the default minute entry
     */
    public CommitteeScheduleMinuteBase(String minuteEntryTypeCode) {
        this.minuteEntryTypeCode = minuteEntryTypeCode;
    }

    public Long getScheduleIdFk() {
        return scheduleIdFk;
    }

    public void setScheduleIdFk(Long scheduleIdFk) {
        this.scheduleIdFk = scheduleIdFk;
    }

    public Integer getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    public String getMinuteEntryTypeCode() {
        return minuteEntryTypeCode;
    }

    public void setMinuteEntryTypeCode(String minuteEntryTypeCode) {
        this.minuteEntryTypeCode = minuteEntryTypeCode;
    }

    public Long getProtocolIdFk() {
        return protocolIdFk;
    }

    public void setProtocolIdFk(Long protocolIdFk) {
        this.protocolIdFk = protocolIdFk;
    }

    public Long getCommScheduleActItemsIdFk() {
        return commScheduleActItemsIdFk;
    }

    public void setCommScheduleActItemsIdFk(Long commScheduleActItemsIdFk) {
        this.commScheduleActItemsIdFk = commScheduleActItemsIdFk;
    }

    public Long getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Long submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
    }

    public boolean getPrivateCommentFlag() {
        return privateCommentFlag;
    }

    public void setPrivateCommentFlag(boolean privateCommentFlag) {
        this.privateCommentFlag = privateCommentFlag;
    }

    public String getProtocolContingencyCode() {
        return protocolContingencyCode;
    }

    public void setProtocolContingencyCode(String protocolContingencyCode) {
        this.protocolContingencyCode = protocolContingencyCode;
        if (!StringUtils.isBlank(protocolContingencyCode) && getProtocolContingency() != null) {
            setMinuteEntry(getProtocolContingency().getDescription());
        }
    }

    public String getMinuteEntry() {
        return minuteEntry;
    }

    public void setMinuteEntry(String minuteEntry) {
        this.minuteEntry = minuteEntry;
    }

    public Long getCommScheduleMinutesId() {
        return commScheduleMinutesId;
    }

    public void setCommScheduleMinutesId(Long commScheduleMinutesId) {
        this.commScheduleMinutesId = commScheduleMinutesId;
    }

    public ProtocolContingencyBase getProtocolContingency() {
        if (StringUtils.isBlank(protocolContingencyCode)) {
            protocolContingency = null;
        } else if (protocolContingency == null || !StringUtils.equals(protocolContingencyCode, protocolContingency.getProtocolContingencyCode())) {
            refreshReferenceObject("protocolContingency");
        }
        return protocolContingency;
    }

    public void setProtocolContingency(ProtocolContingencyBase protocolContingency) {
        this.protocolContingency = protocolContingency;
    }

    public MinuteEntryType getMinuteEntryType() {
        return minuteEntryType;
    }

    public void setMinuteEntryType(MinuteEntryType minuteEntryType) {
        this.minuteEntryType = minuteEntryType;
    }

    public CommScheduleActItemBase getCommScheduleActItem() {
        return commScheduleActItem;
    }

    public void setCommScheduleActItem(CommScheduleActItemBase commScheduleActItem) {
        this.commScheduleActItem = commScheduleActItem;
    }

    public List<ProtocolBase> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<ProtocolBase> protocols) {
        this.protocols = protocols;
    }

    public boolean isGenerateAttendance() {
        return generateAttendance;
    }

    public void setGenerateAttendance(boolean generateAttendance) {
        this.generateAttendance = generateAttendance;
    }

    public boolean isFinalFlag() {
        return finalFlag;
    }

    public void setFinalFlag(boolean finalFlag) {
        this.finalFlag = finalFlag;
    }

    public ProtocolBase getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
    }

    public Long getProtocolReviewerIdFk() {
        return protocolReviewerIdFk;
    }

    public void setProtocolReviewerIdFk(Long protocolReviewerIdFk) {
        this.protocolReviewerIdFk = protocolReviewerIdFk;
    }

    public ProtocolReviewer getProtocolReviewer() {
        return protocolReviewer;
    }

    public void setProtocolReviewer(ProtocolReviewer protocolReviewer) {
        this.protocolReviewer = protocolReviewer;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * Gets the protocolReviewIdFk attribute. 
     * @return Returns the protocolReviewIdFk.
     */
    public Long getProtocolOnlineReviewIdFk() {
        return protocolOnlineReviewIdFk;
    }

    /**
     * Sets the protocolReviewIdFk attribute value.
     * @param protocolOnlineReviewIdFk The protocolReviewIdFk to set.
     */
    public void setProtocolOnlineReviewIdFk(Long protocolOnlineReviewIdFk) {
        this.protocolOnlineReviewIdFk = protocolOnlineReviewIdFk;
    }

    /**
     * Gets the protocolReview attribute. 
     * @return Returns the protocolReview.
     */
    public ProtocolOnlineReviewBase getProtocolOnlineReview() {
        return protocolOnlineReview;
    }

    /**
     * Sets the protocolReview attribute value.
     * @param protocolReview The protocolReview to set.
     */
    public void setProtocolOnlineReview(ProtocolOnlineReviewBase protocolReview) {
        this.protocolOnlineReview = protocolReview;
    }

    /**
     * Equality is based on minute id, minute entry value, entry number(order position)
     * and whether or not it is private.
     * This function is used to determine if a minute needs to be updated on the DB.
     * @param o a CommitteeScheduleMinuteBase object
     * @return boolean if the passed in minute is the same as THIS minute.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && (o.getClass() == this.getClass())) {
            CSM csm = (CSM) o;
            return this.getCommScheduleMinutesId().equals(csm.getCommScheduleMinutesId()) && StringUtils.equals(this.getMinuteEntry(), csm.getMinuteEntry()) && this.getEntryNumber().equals(csm.getEntryNumber()) && this.getPrivateCommentFlag() == csm.getPrivateCommentFlag() && this.isFinalFlag() == csm.isFinalFlag();
        } else {
            return false;
        }
    }

    /*
     * beforeUpdate - only do actual update if a change has been made to the comment.
     */
    @Override
    protected void preUpdate() {
        if (setUpdateIfModified()) {
            super.preUpdate();
        }
    }
    
    
    
    /**
     * This method will try to obtain the pristine instance of this BO from the database using the schedule id as the primary key value.
     * It will return null if the schedule id is null or if the pristine instance cannot be loaded from the DB for whatever reason.
     * @return
     */
    @SuppressWarnings("unchecked")
    public CSM getPristineInstance() {
        CSM retVal = null; 
        Long primaryKey = this.getCommScheduleMinutesId();
        if(primaryKey != null) {
            HashMap<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("commScheduleMinutesId", primaryKey.toString());
            retVal = (CSM) KcServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(this.getClass(), pkMap);
        }
        return retVal;
    }
    
    /**
     * This method returns true if this BO instance's minuteEntry text or the private or final flag values or the protocol values 
     * have been updated by the user. It checks these fields by comparing this instance against the supplied pristine instance of this BO.
     * This method will return false if the supplied pristineInstance argument is null, or if its schedule id (primary key) is different
     * from this instance's schedule id.
     */
    public boolean isUpdateUserToBeRecorded(CSM pristineInstance) {
        boolean retVal = false;
        if ( (pristineInstance != null) && (this.getCommScheduleMinutesId().equals(pristineInstance.getCommScheduleMinutesId())) 
                &&
             (!StringUtils.equals(this.getMinuteEntry(), pristineInstance.getMinuteEntry()) 
               || this.getPrivateCommentFlag() != pristineInstance.getPrivateCommentFlag() 
               || this.isFinalFlag() != pristineInstance.isFinalFlag() 
               || isProtocolFieldChanged(pristineInstance)) ) {
                retVal = true;
        }
        return retVal;
    }

    private boolean setUpdateIfModified() {
        boolean result = false;
        String updateUser = GlobalVariables.getUserSession().getPrincipalName();
        if (getCommScheduleMinutesId() != null) {
            if (isUpdateUserToBeRecorded(this.getPristineInstance())) {
                this.setUpdateUser(updateUser);
                result = true;
            }
        } else {
            this.setUpdateUser(updateUser);
            result = true;
        }
        setUpdateUserSet(true);
        return result;
    }

    private boolean isProtocolFieldChanged(CSM committeeScheduleMinute) {
        boolean isChanged = false;
        // check for identical objects or both being null  
        if (protocolIdFk != committeeScheduleMinute.getProtocolIdFk()) {
            if (protocolIdFk != null) {
                isChanged &= !protocolIdFk.equals(committeeScheduleMinute.getProtocolIdFk());
            } else {
                isChanged &= !committeeScheduleMinute.getProtocolIdFk().equals(protocolIdFk);
            }
        }
        if (protocolContingencyCode != committeeScheduleMinute.getProtocolContingencyCode()) {
            if (protocolContingencyCode != null) {
                isChanged &= !protocolContingencyCode.equals(committeeScheduleMinute.getProtocolContingencyCode());
            } else {
                isChanged &= !committeeScheduleMinute.getProtocolContingencyCode().equals(protocolContingencyCode);
            }
        }
        return isChanged;
    }

    /**
     * 
     * This method returns true if the object has been saved to the database, and returns false if it has not.
     * @return a boolean
     */
    public boolean isPersisted() {
        return this.commScheduleMinutesId != null;
    }

    public Long getProtocolId() {
        Long protocolId = null;
        if (this.protocol != null) {
            protocolId = this.protocol.getProtocolId();
        } else {
            if (this.protocolIdFk != null) {
                this.refreshReferenceObject("protocol");
            }
            if (protocol != null) {
                protocolId = this.protocol.getProtocolId();
            } 
        }
        return protocolId;
        
    }

    /**
     * Gets the createUserFullName attribute. 
     * @return Returns the createUserFullName.
     */
    public String getCreateUserFullName() {
        if (createUserFullName == null && getCreateUser() != null) {
            KcPerson person = getKcPersonService().getKcPersonByUserName(getCreateUser());
            createUserFullName = person == null ? String.format(PERSON_NOT_FOUND_FORMAT_STRING, getCreateUser()) : person.getFullName();
        }
        return createUserFullName;
    }

    /**
     * Sets the createUserFullName attribute value.
     * @param createUserFullName The createUserFullName to set.
     */
    public void setCreateUserFullName(String createUserFullName) {
        this.createUserFullName = createUserFullName;
    }

    /**
     * Gets the updateUserFullName attribute. 
     * @return Returns the updateUserFullName.
     */
    public String getUpdateUserFullName() {
        if (updateUserFullName == null && getUpdateUser() != null) {
            KcPerson person = getKcPersonService().getKcPersonByUserName(getUpdateUser());
            updateUserFullName = person == null ? String.format(PERSON_NOT_FOUND_FORMAT_STRING, getUpdateUser()) : person.getFullName();
        }
        return updateUserFullName;
    }

    /**
     * Sets the updateUserFullName attribute value.
     * @param updateUserFullName The updateUserFullName to set.
     */
    public void setUpdateUserFullName(String updateUserFullName) {
        this.updateUserFullName = updateUserFullName;
    }

    public CS getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CS committeeSchedule) {
        this.committeeSchedule = committeeSchedule;
    }

    /**
     * Returns whether the current user can view this comment.
     * 
     * This is true either if 
     *   1) The current user has the role IRB Administrator
     *   2) The current user does not have the role IRB Administrator, but the current user is the comment creator
     *   3) The current user does not have the role IRB Administrator, the current user is not the comment creator, but the comment is public and final
     * @return whether the current user can view this comment
     */
    public boolean getCanView() {
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        String principalName = GlobalVariables.getUserSession().getPrincipalName();
        return isAdministrator(principalId) || StringUtils.equals(principalName, createUser) || (!getPrivateCommentFlag() && isFinalFlag());
    }

    
    protected abstract boolean isAdministrator(String principalId);    
    

    public CSM getCopy() {
        CSM copy = null;
        try {
            copy = (CSM) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return copy;
    }

    public boolean isDisplayReviewerName() {
        return displayReviewerName;
    }

    public void setDisplayReviewerName(boolean displayReviewerName) {
        this.displayReviewerName = displayReviewerName;
    }

    /**
     * 
     * This method is needed to determine whether schedule minute comments have been accepted by
     * the irb admin.  Only online review comments are subject to approval, all other minute types
     * are returned true by default.
     * @return false if it is an online review comment and not accepted, true otherwise.
     */
    //    public boolean isAccepted() {  
    //        boolean accepted = false;  
    //           
    //        if (getProtocolOnlineReviewIdFk() != null) {  
    //            ProtocolOnlineReviewBase protocolOnlineReview = getBusinessObjectService().findBySinglePrimaryKey(ProtocolOnlineReviewBase.class, getProtocolOnlineReviewIdFk());  
    //            if (protocolOnlineReview.isAdminAccepted()) {  
    //                accepted = true;  
    //            }  
    //        } else {  
    //            accepted = true;  
    //        }  
    //          
    //        return accepted;  
    //    }  
    //    private BusinessObjectService getBusinessObjectService() {  
    //        return KraServiceLocator.getService(BusinessObjectService.class);  
    //    }  
    public boolean isReviewComment() {
        // TODO Auto-generated method stub  
        return true;
    }

    @Override
    public boolean isFinal() {
        return isFinalFlag();
    }

    @Override
    public boolean isPrivate() {
        return getPrivateCommentFlag();
    }

    public boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }
}
