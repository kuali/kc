/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.negotiations.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * Negotiation Activity BO.
 */
public class NegotiationActivity extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1927288853033781994L;
    public static final long MILLISECS_PER_DAY = 24*60*60*1000;
    
    private Long activityId;
    private Long negotiationId;
    private Negotiation negotiation;
    private Long locationId;
    private NegotiationLocation location;
    private Long activityTypeId;
    private NegotiationActivityType activityType;
    private Date startDate;
    private Date endDate;
    private Date createDate;
    private Date followupDate;
    private String lastModifiedUsername;
    private Date lastModifiedDate;
    private String description;
    private Boolean restricted;
    private List<NegotiationActivityAttachment> attachments;
    
    private transient NegotiationActivityAttachment newAttachment;
    private transient boolean updated;
    
    public NegotiationActivity() {
        restricted = Boolean.TRUE;
        attachments = new ArrayList<NegotiationActivityAttachment>();
        newAttachment = new NegotiationActivityAttachment();
    }
    
    /**
     * Calculates the number of days between the start date and either the end date when
     * available or the current date.
     * @return
     */
    public String getNumberOfDays() {
        return getNumberOfDays(getStartDate(), getEndDate());
    }
    
    /**
     * 
     * This method Calculates the number of days between the start date and either the end date when available or the current date.
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getNumberOfDays(Date startDate, Date endDate) {
        if (startDate == null) {
            return "";
        } else {
            long start = startDate.getTime();
            long end = 0L;
            if (endDate == null) {
                end = Calendar.getInstance().getTimeInMillis();
            } else {
                end = endDate.getTime();
            }
            return (((end - start) / MILLISECS_PER_DAY) + 1) + " days";
        }
    }
    
    /**
     * This method should be called during the execute or before save to
     * update this BOs last updated by fields when something has changed.
     */
    public void updateActivity() {
        if (updated) {
            this.refreshReferenceObject("location");
            this.refreshReferenceObject("activityType");
            this.setLastModifiedDate(new Date(new java.util.Date().getTime()));
            this.setLastModifiedUsername(GlobalVariables.getUserSession().getPrincipalName());
            this.updated = false;
        }
    }

    public Long getActivityId() {
        return activityId;
    }



    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }



    public Long getNegotiationId() {
        return negotiationId;
    }



    public void setNegotiationId(Long negotiationId) {
        this.negotiationId = negotiationId;
    }



    public Negotiation getNegotiation() {
        return negotiation;
    }



    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }



    public Long getLocationId() {
        return locationId;
    }



    public void setLocationId(Long locationId) {
        if (!ObjectUtils.equals(this.locationId, locationId)) {
            updated = true;
        }
        this.locationId = locationId;
    }



    public NegotiationLocation getLocation() {
        return location;
    }



    public void setLocation(NegotiationLocation location) {
        if (!ObjectUtils.equals(this.location, location)) {
            updated = true;
        }
        this.location = location;
    }



    public Long getActivityTypeId() {
        return activityTypeId;
    }



    public void setActivityTypeId(Long activityTypeId) {
        if (!ObjectUtils.equals(this.activityTypeId, activityTypeId)) {
            updated = true;
        }
        this.activityTypeId = activityTypeId;
    }



    public NegotiationActivityType getActivityType() {
        return activityType;
    }



    public void setActivityType(NegotiationActivityType activityType) {
        this.activityType = activityType;
    }



    public Date getStartDate() {
        return startDate;
    }



    public void setStartDate(Date startDate) {
        if (!ObjectUtils.equals(this.startDate, startDate)) {
            updated = true;
        }
        this.startDate = startDate;
    }



    public Date getEndDate() {
        return endDate;
    }



    public void setEndDate(Date endDate) {
        if (!ObjectUtils.equals(this.endDate, endDate)) {
            updated = true;
        }        
        this.endDate = endDate;
    }



    public Date getCreateDate() {
        return createDate;
    }



    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }



    public Date getFollowupDate() {
        return followupDate;
    }



    public void setFollowupDate(Date followupDate) {
        if (!ObjectUtils.equals(this.followupDate, followupDate)) {
            updated = true;
        }
        this.followupDate = followupDate;
    }



    public String getLastModifiedUsername() {
        return lastModifiedUsername;
    }



    public void setLastModifiedUsername(String lastModifiedUsername) {
        this.lastModifiedUsername = lastModifiedUsername;
    }
    
    public KcPerson getLastModifiedUser() {
        if (this.getLastModifiedUsername() == null) {
            return null;
        } else {
            return getKcPersonService().getKcPersonByUserName(this.getLastModifiedUsername());
        }
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }



    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        if (!ObjectUtils.equals(this.description, description)) {
            updated = true;
        }
        this.description = description;
    }



    public Boolean getRestricted() {
        return restricted;
    }



    public void setRestricted(Boolean restricted) {
        if (!ObjectUtils.equals(this.restricted, restricted)) {
            updated = true;
        }        
        this.restricted = restricted;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("activityId", activityId);
        result.put("negotiationId", negotiationId);
        result.put("activityTypeId", activityTypeId);
        result.put("locationId", locationId);
        result.put("startDate", startDate);
        result.put("endDate", endDate);
        return result;
    }

    public List<NegotiationActivityAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<NegotiationActivityAttachment> attachments) {
        this.attachments = attachments;
    }
    
    /**
     * Add a new attachment to this activity.
     * @param attachment
     */
    public void add(NegotiationActivityAttachment attachment) {
        updated = true;
        this.attachments.add(attachment);
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public NegotiationActivityAttachment getNewAttachment() {
        return newAttachment;
    }

    public void setNewAttachment(NegotiationActivityAttachment newAttachment) {
        this.newAttachment = newAttachment;
    }
}
