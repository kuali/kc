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
package org.kuali.kra.coi;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.sql.Date;
import java.sql.Timestamp;

public class CoiUserRole extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 7167435123831925213L;
    private Long coiUserRolesId; 
    private Long coiDisclosureId; 
    private String coiDisclosureNumber;
    private Integer sequenceNumber; 
    private String roleName;
    private String userId;
    private String reviewerCode;
    private Date dateAssigned;
    private Date dateCompleted;
    private boolean reviewCompleted;
    private String coiRecomendedTypeCode;
    private CoiRecomendedActionType coiRecomendedActionType;
    
    //transient fields used for display purposes
    private transient KcPerson person;
    private transient CoiReviewer coiReviewer;
    private transient boolean markedToCompleteReview;
    private boolean editable;
    private transient String oldCoiRecomendedTypeCode;
    
    public CoiUserRole() {
        setMarkedToCompleteReview(true);
        setEditable(false);
    }
    
    public Long getCoiUserRolesId() {
        return coiUserRolesId;
    }

    public void setCoiUserRolesId(Long coiUserRolesId) {
        this.coiUserRolesId = coiUserRolesId;
    }

    public Long getCoiDisclosureId() {
        return coiDisclosureId;
    }

    public void setCoiDisclosureId(Long coiDisclosureId) {
        this.coiDisclosureId = coiDisclosureId;
    }

    public String getCoiDisclosureNumber() {
        return coiDisclosureNumber;
    }

    public void setCoiDisclosureNumber(String coiDisclosureNumber) {
        this.coiDisclosureNumber = coiDisclosureNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReviewerCode() {
        return reviewerCode;
    }

    public void setReviewerCode(String reviewerCode) {
        this.reviewerCode = reviewerCode;
    }

    public KcPerson getPerson() {
        return person;
    }

    public void setPerson(KcPerson person) {
        this.person = person;
    }

    public CoiReviewer getCoiReviewer() {
        return coiReviewer;
    }

    public void setCoiReviewer(CoiReviewer coiReviewer) {
        this.coiReviewer = coiReviewer;
    }

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public boolean isReviewCompleted() {
        return reviewCompleted;
    }

    public void setReviewCompleted(boolean reviewCompleted) {
        this.reviewCompleted = reviewCompleted;
    }

    public String getCoiRecomendedTypeCode() {
        return coiRecomendedTypeCode;
    }

    public void setCoiRecomendedTypeCode(String coiRecomendedTypeCode) {
        this.coiRecomendedTypeCode = coiRecomendedTypeCode;
    }

    public CoiRecomendedActionType getCoiRecomendedActionType() {
        return coiRecomendedActionType;
    }

    public void setCoiRecomendedActionType(CoiRecomendedActionType coiRecomendedActionType) {
        this.coiRecomendedActionType = coiRecomendedActionType;
    }

    public boolean isMarkedToCompleteReview() {
        return markedToCompleteReview;
    }

    public void setMarkedToCompleteReview(boolean markedToCompleteReview) {
        this.markedToCompleteReview = markedToCompleteReview;
    }
    
    @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        if (updateTimestamp == null || getUpdateTimestamp() == null || isEditable()) {
            super.setUpdateTimestamp(updateTimestamp);
        }
    }

    @Override
    public void setUpdateUser(String updateUser) {
        if (updateUser == null || getUpdateUser() == null || isEditable() ) {
            super.setUpdateUser(updateUser);
        }
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public void postUpdate() {
        super.postUpdate();
        setEditable(false);
        setOldCoiRecomendedTypeCode(getCoiRecomendedTypeCode());
    }

    public String getOldCoiRecomendedTypeCode() {
        return oldCoiRecomendedTypeCode;
    }

    public void setOldCoiRecomendedTypeCode(String oldCoiRecomendedTypeCode) {
        this.oldCoiRecomendedTypeCode = oldCoiRecomendedTypeCode;
    }
    
}
