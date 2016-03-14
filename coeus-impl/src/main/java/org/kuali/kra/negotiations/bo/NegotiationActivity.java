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
package org.kuali.kra.negotiations.bo;

import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NegotiationActivity extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 1927288853033781994L;

    public static final long MILLISECS_PER_DAY = 24 * 60 * 60 * 1000;

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

    private transient KcPersonService kcPersonService;

    public NegotiationActivity() {
        restricted = Boolean.TRUE;
        attachments = new ArrayList<>();
        newAttachment = new NegotiationActivityAttachment();
    }

    /**
     * Calculates the number of days between the start date and either the end date when
     * available or the current date.

     */
    public String getNumberOfDays() {
        return getNumberOfDays(getStartDate(), getEndDate());
    }

    /**
     * 
     * This method Calculates the number of days between the start date and either the end date when available or the current date.
     */
    public static String getNumberOfDays(Date startDate, Date endDate) {
        if (startDate == null) {
            return "";
        } else {
            long start = startDate.getTime();
            final long end;
            if (endDate == null) {
                end = Calendar.getInstance().getTimeInMillis();
            } else {
                end = endDate.getTime();
            }
            Days days = Days.daysBetween(new DateTime(Long.valueOf(start), DateTimeZone.UTC).withTimeAtStartOfDay(),
                                            new DateTime(Long.valueOf(end), DateTimeZone.UTC).withTimeAtStartOfDay());
            return days.getDays() + "";
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
    
    public String getLastModifiedUserFullName() {
        KcPerson user = getLastModifiedUser();
        return user == null ? "" : user.getFullName();
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

    public List<NegotiationActivityAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<NegotiationActivityAttachment> attachments) {
        this.attachments = attachments;
    }

    /**
     * Add a new attachment to this activity.
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

    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }
}
