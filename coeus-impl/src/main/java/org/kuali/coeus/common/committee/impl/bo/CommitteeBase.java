/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.committee.impl.bo;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.kra.coi.CoiReviewType;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewTypeBase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a single committee within an institution.
 */
@SuppressWarnings("serial")
public abstract class CommitteeBase<CMT extends CommitteeBase<CMT, CD, CS>,
                                      CD extends CommitteeDocumentBase<CD, CMT, CS>, 
                                      CS extends CommitteeScheduleBase<CS, CMT, ?, ?>> 
    
                                      extends KcPersistableBusinessObjectBase
                                      
                                      implements Comparable<CMT>, SequenceOwner<CMT>, Permissionable {
    private Long id;
    private String committeeId;
    private Integer sequenceNumber;
    private String committeeName;
    private String homeUnitNumber;
    private String committeeDescription;
    private String scheduleDescription;
    private String committeeTypeCode;
    private Integer minimumMembersRequired;
    private Integer maxProtocols;
    private Integer advancedSubmissionDaysRequired;
    private String reviewTypeCode;
    private String coiReviewTypeCode;
    
    private Unit homeUnit;
    private CommitteeType committeeType;
    private ProtocolReviewTypeBase reviewType;
    private CoiReviewType coiReviewType;
    
    private CD committeeDocument;
    
    private List<CommitteeMembershipBase> committeeMemberships;
    private List<CS> committeeSchedules;
    
    private List<CommitteeResearchAreaBase> committeeResearchAreas;
   
    // transient lookup fields
    private static final String CHAIR_MEMBERSHIP_ROLE_CODE = "1";
    private String committeeChair;
    private String unitName;
    private Boolean printRooster;


    public CommitteeBase() {
        setSequenceNumber(1);
        setCommitteeResearchAreas(new ArrayList<CommitteeResearchAreaBase>());
        setCommitteeMemberships(new ArrayList<CommitteeMembershipBase>());
        setCommitteeSchedules(new ArrayList<CS>());
        setCommitteeTypeCode(getProtocolCommitteeTypeCodehook());
        setPrintRooster(false);
    }
    
    protected abstract String getProtocolCommitteeTypeCodehook();
    

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public String getHomeUnitNumber() {
        return homeUnitNumber;
    }

    public void setHomeUnitNumber(String homeUnitNumber) {
        this.homeUnitNumber = homeUnitNumber;
    }

    public String getCommitteeDescription() {
        return committeeDescription;
    }

    public void setCommitteeDescription(String committeeDescription) {
        this.committeeDescription = committeeDescription;
    }

    public String getScheduleDescription() {
        return scheduleDescription;
    }

    public void setScheduleDescription(String scheduleDescription) {
        this.scheduleDescription = scheduleDescription;
    }

    public String getCommitteeTypeCode() {
        return committeeTypeCode;
    }

    public void setCommitteeTypeCode(String committeeTypeCode) {
        this.committeeTypeCode = committeeTypeCode;
    }

    public Integer getMinimumMembersRequired() {
        return minimumMembersRequired;
    }

    public void setMinimumMembersRequired(Integer minimumMembersRequired) {
        this.minimumMembersRequired = minimumMembersRequired;
    }

    public Integer getMaxProtocols() {
        return maxProtocols;
    }

    public void setMaxProtocols(Integer maxProtocols) {
        this.maxProtocols = maxProtocols;
    }

    public Integer getAdvancedSubmissionDaysRequired() {
        return advancedSubmissionDaysRequired;
    }

    public void setAdvancedSubmissionDaysRequired(Integer advancedSubmissionDaysRequired) {
        this.advancedSubmissionDaysRequired = advancedSubmissionDaysRequired;
    }

    public String getReviewTypeCode() {
        return reviewTypeCode;
    }

    public void setReviewTypeCode(String reviewTypeCode) {
        this.reviewTypeCode = reviewTypeCode;
    }

    public String getCoiReviewTypeCode() {
        return coiReviewTypeCode;
    }

    public void setCoiReviewTypeCode(String coiReviewTypeCode) {
        this.coiReviewTypeCode = coiReviewTypeCode;
    }

    public Unit getHomeUnit() {
        return homeUnit;
    }

    public void setHomeUnit(Unit homeUnit) {
        this.homeUnit = homeUnit;
    }

    public CommitteeType getCommitteeType() {
        if(null == this.committeeType) {
            refreshReferenceObject("committeeType");
        }
        return this.committeeType;
    }

    public void setCommitteeType(CommitteeType committeeType) {
        this.committeeType = committeeType;
    }

    public ProtocolReviewTypeBase getReviewType() {
        return reviewType;
    }

    public void setReviewType(ProtocolReviewTypeBase reviewType) {
        this.reviewType = reviewType;
    }
    
    public CoiReviewType getCoiReviewType() {
        return coiReviewType;
    }

    public void setCoiReviewType(CoiReviewType coiReviewType) {
        this.coiReviewType = coiReviewType;
    }

    public CD getCommitteeDocument() {
        return committeeDocument;
    }

    public void setCommitteeDocument(CD committeeDocument) {
        this.committeeDocument = committeeDocument;
    }

    public List<CommitteeMembershipBase> getCommitteeMemberships() {
        return committeeMemberships;
    }

    public void setCommitteeMemberships(List<CommitteeMembershipBase> committeeMemberships) {
        this.committeeMemberships = committeeMemberships;
    }

    public void setCommitteeSchedules(List<CS> committeeSchedules) {
        this.committeeSchedules = committeeSchedules;
    }

    public List<CS> getCommitteeSchedules() {
        return committeeSchedules;
    }
    
    public List<CommitteeResearchAreaBase> getCommitteeResearchAreas() {
        return committeeResearchAreas;
    }

    public void setCommitteeResearchAreas(List<CommitteeResearchAreaBase> committeeResearchAreas) {
        this.committeeResearchAreas = committeeResearchAreas;
    }
    public Boolean getPrintRooster() {
        return printRooster;
    }

    public void setPrintRooster(Boolean printRooster) {
        this.printRooster = printRooster;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getCommitteeResearchAreas());
        managedLists.add(this.committeeSchedules);
        return managedLists;
    }

    public String getCommitteeChair() {
        if (StringUtils.isBlank(committeeChair) && CollectionUtils.isNotEmpty(getCommitteeMemberships())) {
            List<String> committeeChairs = new ArrayList<String>();
            for (CommitteeMembershipBase committeeMembership : getCommitteeMemberships()) {
                if (isChairPerson(committeeMembership)) {
                    committeeChairs.add(committeeMembership.getPersonName());
                }
                setCommitteeChair(committeeChairs.toString());
            }
        }
        return committeeChair;
    }

    private boolean isChairPerson(CommitteeMembershipBase committeeMembership) {
        
        boolean isChairRoleFound = false;
        Date currentDate = DateUtils.clearTimeFields(new Date(System.currentTimeMillis()));

        for (CommitteeMembershipRole committeeMembershipRole : committeeMembership.getMembershipRoles()) {
            if (committeeMembershipRole.getMembershipRoleCode().equals(CHAIR_MEMBERSHIP_ROLE_CODE)
                    && committeeMembershipRole.getStartDate() != null 
                    && committeeMembershipRole.getEndDate() != null 
                    && !currentDate.before(committeeMembershipRole.getStartDate()) && !currentDate.after(committeeMembershipRole.getEndDate())) {
                isChairRoleFound = true;
                break;
            }
        }
        return isChairRoleFound;
    }
    
    public void setCommitteeChair(String committeeChair) {
        this.committeeChair = committeeChair;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getOwnerSequenceNumber() {
        return null;
    }

    public void incrementSequenceNumber() {
        sequenceNumber++;
    }

    public CMT getSequenceOwner() {
        return getThisHook();
    }
    
    protected abstract CMT getThisHook();
    
    public void setSequenceOwner(CMT newOwner) {
        // do nothing - this is root sequence association
    }

    public void resetPersistenceState() {
        setId(null);
    }

    /**
     * The default comparator goes by the order of committeeId, sequenceNumber.
     * @param committee the CommitteeBase to be compared.
     * @return the value 0 if this CommitteeBase is equal to the argument CommitteeBase; 
     *         a value less than 0 if this CommitteeBase has a committeeId &amp; sequenceNumber pair that is less
     *         than the argument CommitteeBase; and a value greater than 0 if this CommitteeBase has a committeeId
     *         &amp; sequenceNumber pair that is greater than the argument CommitteeBase.
     */
    public int compareTo(CMT committee) {
        // java.lang.NullPointerException
        // at org.kuali.kra.common.committee.bo.CommitteeBase.compareTo(CommitteeBase.java:356)
        // at org.kuali.kra.common.committee.bo.CommitteeBase.compareTo(CommitteeBase.java:38)
        // Fixes https://github.com/rSmart/issues/issues/335
        if (committee == null) {
          return -1;
        }
        if (StringUtils.equals(this.getCommitteeId(), committee.getCommitteeId())) {
            return this.getSequenceNumber().compareTo(committee.getSequenceNumber());
        } else {
            return this.getCommitteeId().compareTo(committee.getCommitteeId());
        }
    }

    public String getVersionNameField() {
        return "committeeName";
    }

    @Override
    public String getDocumentKey() {
        return PermissionableKeys.COMMITTEE_KEY;
    }
    
    @Override
    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        qualifiedRoleAttributes.put(KcKimAttributes.COMMITTEE, this.getCommitteeId());
    }

    @Override
    public String getDocumentNumberForPermission() {
        return committeeId;
    }

    @Override
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();
        
        roleNames.add(getAdminRoleHook());
        roleNames.add(getProtocolReviewerRoleHook());
        return roleNames;
    }

    protected abstract String getProtocolReviewerRoleHook();
    protected abstract String getAdminRoleHook();    
    
    
    
    public String getNamespace() {
        return getModuleNamespaceCodeHook();
    }

    protected abstract String getModuleNamespaceCodeHook();
    
    

    public String getLeadUnitNumber() {
        return getHomeUnitNumber();
    }

    public String getDocumentRoleTypeCode() {
      //FIXME: verify role type
        return null;
    }
    
    /**
     * This method will return the committee membership instance representing the 
     * person given by personID. If no such membership is associated with the committee, 
     * then null is returned. Also returns null if the personId parameter is null.
     *
     * @param personId
     * @return
     */
    public CommitteeMembershipBase getCommitteeMembershipFor(String personId) {
        CommitteeMembershipBase retVal = null;
        for(CommitteeMembershipBase member : this.getCommitteeMemberships()) {
            if(member.isRepresentingPerson(personId)) {
                retVal = member;
                break;
            }
        }
        return retVal;
    }
    
    /**
     * This method will return a schedule in this committee's list that has 
     * the same schedule id as the supplied param. If no such schedule is found, it will return null.
     * @param scheduleId
     * @return
     */
    public CS getCommitteeSchedule(String scheduleId) {
        CS retVal = null;
        for (CS schedule : this.getCommitteeSchedules()) {
            if (StringUtils.equals(schedule.getScheduleId(), scheduleId)) {
                retVal = schedule;
                break;
            }
        }
        return retVal;
    }
    
}
