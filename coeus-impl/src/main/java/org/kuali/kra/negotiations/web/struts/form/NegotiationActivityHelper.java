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
package org.kuali.kra.negotiations.web.struts.form;

import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityAttachment;
import org.kuali.kra.negotiations.rules.NegotiationActivityAddRuleEvent;
import org.kuali.kra.negotiations.rules.NegotiationActivityAttachmentAddRuleEvent;
import org.kuali.kra.negotiations.rules.NegotiationActivityAttachmentRuleImpl;
import org.kuali.kra.negotiations.rules.NegotiationActivityRuleImpl;
import org.kuali.kra.negotiations.sorting.ActivitySortingType;
import org.kuali.kra.negotiations.sorting.AttachmentSortingType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Form helper to manage activities and attachments.
 */
public class NegotiationActivityHelper implements Serializable {
    

    private static final long serialVersionUID = 8707454680500119142L;
    private NegotiationForm form;
    private NegotiationActivity newActivity;
    private ActivitySortingType activitySortingType;
    private AttachmentSortingType attachmentSortingType;
    private List<NegotiationActivityAttachment> allAttachments;
    
    /**
     * 
     * Constructs a NegotiationActivityHelper.java.
     * @param form
     */
    public NegotiationActivityHelper(NegotiationForm form) {
        this.form = form;
        newActivity = new NegotiationActivity();
    }
    
    /**
     * Add the newActivity to the negotiation on the form also set as updated so during the
     * execute phase timestamps and last user update is updated.
     */
    public void addActivity() {
        NegotiationActivityAddRuleEvent event = new NegotiationActivityAddRuleEvent("NegotiationActivityAddRuleEvent", "negotiationActivityHelper.newActivity", 
                getForm().getDocument(), newActivity);
        if (new NegotiationActivityRuleImpl().processAddNegotiationActivityRule(event)) { 
            newActivity.setCreateDate(new java.sql.Date(new Date().getTime()));
            //set as updated so action code elsewhere will update references and modified timestamps
            newActivity.setUpdated(true);
            form.getNegotiationDocument().getNegotiation().getActivities().add(newActivity);
            newActivity = new NegotiationActivity();
        }
    }
    
    /**
     * Set the restricted flag on the activity at the activityIndex.
     * @param restricted
     * @param activityIndex
     */
    public void setRestrictedActivity(Boolean restricted, int activityIndex) {
        getActivity(activityIndex).setRestricted(restricted);
    }
    
    /**
     * Find the activity at the specified index on the negotiation. If the index is -1,
     * the newActivity is returned.
     * @param activityIndex
     * @return
     */
    public NegotiationActivity getActivity(int activityIndex) {
        if (activityIndex == -1) {
            return newActivity;
        } else {
            return form.getNegotiationDocument().getNegotiation().getActivities().get(activityIndex);
        }     
    }

    protected String getActivityPrefix(int activityIndex) {
        if (activityIndex == -1) {
            return "negotiationActivityHelper.newActivity";
        } else {
            return "document.negotiationList[0].activities[" + activityIndex + "]";
        }     
    }

    
    /**
     * Add the newAttachment on the activity found at activityIndex.
     * @param activityIndex
     */
    public void addAttachment(int activityIndex) {
        NegotiationActivity activity = getActivity(activityIndex);
        String activityPrefix = getActivityPrefix(activityIndex);
        NegotiationActivityAttachment attachment = activity.getNewAttachment();
        NegotiationActivityAttachmentAddRuleEvent event = new NegotiationActivityAttachmentAddRuleEvent("NegotiationActivityAddRuleEvent", activityPrefix + ".newAttachment", 
                getForm().getDocument(), activity, attachment);
        if (new NegotiationActivityAttachmentRuleImpl().processAddAttachmentRule(event)) {
            attachment.setFile(AttachmentFile.createFromFormFile(attachment.getNewFile()));
            attachment.setActivity(activity);
            activity.add(attachment);
            activity.setNewAttachment(new NegotiationActivityAttachment());
        }
    }
    
    public void deleteActivity(int activityIndex) {
        NegotiationActivity activity = getActivity(activityIndex);
        getForm().getNegotiationDocument().getNegotiation().getActivities().remove(activity);
    }
    
    /**
     * Delete the attachment on the activity at the specified indexes.
     * 
     * @param activityIndex
     * @param attachmentIndex
     */
    public void deleteAttachment(int activityIndex, int attachmentIndex) {
        NegotiationActivity activity = getActivity(activityIndex);
        activity.getAttachments().remove(attachmentIndex);
    }
    
    /**
     * Set the specified attachment's restricted flag.
     * @param restricted
     * @param activityIndex
     * @param attachmentIndex
     */
    public void setRestrictedAttachment(Boolean restricted, int activityIndex, int attachmentIndex) {
        NegotiationActivity activity = getActivity(activityIndex);
        NegotiationActivityAttachment attachment = activity.getAttachments().get(attachmentIndex);
        attachment.setRestricted(restricted);
    }
    
    public boolean hasPendingActivities() {
        boolean result = false;
        for (NegotiationActivity activity : getForm().getNegotiationDocument().getNegotiation().getActivities()) {
            if (activity.getEndDate() == null) {
                result = true;
            }
        }
        return result;
    }
    
    public void closeAllPendingActivities() {
        Negotiation negotiation = getForm().getNegotiationDocument().getNegotiation();
        for (NegotiationActivity activity : getForm().getNegotiationDocument().getNegotiation().getActivities()) {
            if (activity.getEndDate() == null) {
                activity.setEndDate(negotiation.getNegotiationEndDate());
            }
        }        
    }

    public NegotiationForm getForm() {
        return form;
    }
    public void setForm(NegotiationForm form) {
        this.form = form;
    }
    public NegotiationActivity getNewActivity() {
        return newActivity;
    }
    public void setNewActivity(NegotiationActivity newActivity) {
        this.newActivity = newActivity;
    }
    public ActivitySortingType getActivitySortingType() {
        return activitySortingType;
    }

    public void setActivitySortingType(ActivitySortingType activitySortingType) {
        this.activitySortingType = activitySortingType;
    }
    public void setActivitySortingTypeName(String activitySortingTypeName) {
        if (activitySortingTypeName == null) {
            this.activitySortingType = null;
        } else {
            this.activitySortingType = ActivitySortingType.valueOf(activitySortingTypeName);
        }
    }
    public String getActivitySortingTypeName() {
        return activitySortingType.name();
    }

    public AttachmentSortingType getAttachmentSortingType() {
        return attachmentSortingType;
    }

    public void setAttachmentSortingType(AttachmentSortingType attachmentSortingType) {
        this.attachmentSortingType = attachmentSortingType;
    }
    public void setAttachmentSortingTypeName(String attachmentSortingTypeName) {
        if (attachmentSortingTypeName == null) {
            this.attachmentSortingType = null;
        } else {
            this.attachmentSortingType = AttachmentSortingType.valueOf(attachmentSortingTypeName);
        }
    }
    public String getAttachmentSortingTypeName() {
        return attachmentSortingType.name();
    }
    
    public List<NegotiationActivityAttachment> getAllAttachments() {
        if (allAttachments == null) {
            generateAllAttachments();
        }
        return allAttachments;
    }
    
    public void generateAllAttachments() {
        allAttachments = new ArrayList<NegotiationActivityAttachment>();
        for (NegotiationActivity activity : getForm().getNegotiationDocument().getNegotiation().getActivities()) {
            allAttachments.addAll(activity.getAttachments());
        }
        if (attachmentSortingType != null) {
            Collections.sort(allAttachments, attachmentSortingType.getComparator());
        }
    }
    
    public void sortActivities() {
        if (getActivitySortingType() != null) {
            Collections.sort(getForm().getNegotiationDocument().getNegotiation().getActivities(), 
                    getActivitySortingType().getComparator());
        }
    }
}
