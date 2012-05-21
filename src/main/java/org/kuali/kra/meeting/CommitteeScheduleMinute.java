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
package org.kuali.kra.meeting;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.onlinereview.ProtocolReviewable;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This is BO class for committee schedule minute. 
 */
public class CommitteeScheduleMinute extends ProtocolReviewable implements Cloneable {

    private static final long serialVersionUID = -2294619582524055884L;

    private static final String PERSON_NOT_FOUND_FORMAT_STRING = "%s (not found)";

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeScheduleMinute.class);

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

    private ProtocolContingency protocolContingency;

    private MinuteEntryType minuteEntryType;

    private CommScheduleActItem commScheduleActItem;

    private CommitteeSchedule committeeSchedule;

    private ProtocolReviewer protocolReviewer;

    private String createUser;

    private Timestamp createTimestamp;

    @SkipVersioning
    private transient ProtocolOnlineReview protocolOnlineReview;

    private String minuteEntry;

    // TODO : not sure how this protocols yet.  
    @SkipVersioning
    private List<Protocol> protocols;

    @SkipVersioning
    private Protocol protocol;

    private boolean generateAttendance = false;

    @SkipVersioning
    private transient String createUserFullName;

    @SkipVersioning
    private transient String updateUserFullName;

    private transient boolean displayReviewerName;

    /*
     * This comparator orders CommitteeScheduleMinute by entry type first and then by entry type detail (if available)
     */
    public static final Comparator<CommitteeScheduleMinute> entryTypeComparator = new Comparator<CommitteeScheduleMinute>() {

        public int compare(CommitteeScheduleMinute csm1, CommitteeScheduleMinute csm2) {
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
     * Constructs a CommitteeScheduleMinute.
     */
    public CommitteeScheduleMinute() {
    }

    /**
     * Constructs a CommitteeScheduleMinute with a default minute entry.
     * @param minuteEntryTypeCode the type code for the default minute entry
     */
    public CommitteeScheduleMinute(String minuteEntryTypeCode) {
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

    public ProtocolContingency getProtocolContingency() {
        if (StringUtils.isBlank(protocolContingencyCode)) {
            protocolContingency = null;
        } else if (protocolContingency == null || !StringUtils.equals(protocolContingencyCode, protocolContingency.getProtocolContingencyCode())) {
            refreshReferenceObject("protocolContingency");
        }
        return protocolContingency;
    }

    public void setProtocolContingency(ProtocolContingency protocolContingency) {
        this.protocolContingency = protocolContingency;
    }

    public MinuteEntryType getMinuteEntryType() {
        return minuteEntryType;
    }

    public void setMinuteEntryType(MinuteEntryType minuteEntryType) {
        this.minuteEntryType = minuteEntryType;
    }

    public CommScheduleActItem getCommScheduleActItem() {
        return commScheduleActItem;
    }

    public void setCommScheduleActItem(CommScheduleActItem commScheduleActItem) {
        this.commScheduleActItem = commScheduleActItem;
    }

    public List<Protocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<Protocol> protocols) {
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

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
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
    public ProtocolOnlineReview getProtocolOnlineReview() {
        return protocolOnlineReview;
    }

    /**
     * Sets the protocolReview attribute value.
     * @param protocolReview The protocolReview to set.
     */
    public void setProtocolOnlineReview(ProtocolOnlineReview protocolReview) {
        this.protocolOnlineReview = protocolReview;
    }

    /**
     * Equality is based on minute id, minute entry value, entry number(order position)
     * and whether or not it is private.
     * This function is used to determine if a minute needs to be updated on the DB.
     * @param o a CommitteeScheduleMinute object
     * @return boolean if the passed in minute is the same as THIS minute.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof CommitteeScheduleMinute) {
            CommitteeScheduleMinute csm = (CommitteeScheduleMinute) o;
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

    private boolean setUpdateIfModified() {
        boolean result = false;
        String updateUser = GlobalVariables.getUserSession().getPrincipalName();
        if (getCommScheduleMinutesId() != null) {
            HashMap<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("commScheduleMinutesId", getCommScheduleMinutesId().toString());
            CommitteeScheduleMinute committeeScheduleMinute = (CommitteeScheduleMinute) KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(this.getClass(), pkMap);
            //            if (!updateUser.equals(committeeScheduleMinute.getUpdateUser())) {  
            if (!StringUtils.equals(getMinuteEntry(), committeeScheduleMinute.getMinuteEntry()) || privateCommentFlag != committeeScheduleMinute.getPrivateCommentFlag() || finalFlag != committeeScheduleMinute.isFinalFlag() || isProtocolFieldChanged(committeeScheduleMinute)) {
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

    private boolean isProtocolFieldChanged(CommitteeScheduleMinute committeeScheduleMinute) {
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

    public CommitteeSchedule getCommitteeSchedule() {
        return committeeSchedule;
    }

    public void setCommitteeSchedule(CommitteeSchedule committeeSchedule) {
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
        return isIrbAdministrator(principalId) || StringUtils.equals(principalName, createUser) || (!getPrivateCommentFlag() && isFinalFlag());
    }

    private boolean isIrbAdministrator(String principalId) {
        RoleService roleService = KraServiceLocator.getService(RoleService.class);
        Collection<String> ids = roleService.getRoleMemberPrincipalIds(RoleConstants.DEPARTMENT_ROLE_TYPE, RoleConstants.IRB_ADMINISTRATOR, null);
        return ids.contains(principalId);
    }

    public CommitteeScheduleMinute getCopy() {
        CommitteeScheduleMinute copy = null;
        try {
            copy = (CommitteeScheduleMinute) this.clone();
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
    //            ProtocolOnlineReview protocolOnlineReview = getBusinessObjectService().findBySinglePrimaryKey(ProtocolOnlineReview.class, getProtocolOnlineReviewIdFk());  
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
}
