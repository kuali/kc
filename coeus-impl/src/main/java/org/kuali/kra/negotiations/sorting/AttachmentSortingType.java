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
package org.kuali.kra.negotiations.sorting;

import org.kuali.kra.negotiations.bo.NegotiationActivityAttachment;

import java.util.Comparator;

/**
 * Enum that lists the various ways of sorting the list of attachments and includes the appropriate
 * comparator for sorting the list.
 */
public enum AttachmentSortingType {
    START("Activity Start Date", new ActivityStartDateAttachmentComparator()),
    FILE("File", new FilenameAttachmentComparator()),
    DESC("Attachment Description", new DescriptionAttachmentComparator()),
    LOC("Location", new LocationAttachmentComparator()),
    TYPE("Activity Type", new ActivityTypeAttachmentComparator());
    
    private String desc;
    private Comparator<NegotiationActivityAttachment> comparator;
    
    private AttachmentSortingType(String desc, Comparator<NegotiationActivityAttachment> comparator) {
        this.desc = desc;
        this.comparator = comparator;
    }
    
    public String getDesc() {
        return desc;
    }
    public Comparator<NegotiationActivityAttachment> getComparator() {
        return comparator;
    }

}

class ActivityStartDateAttachmentComparator implements Comparator<NegotiationActivityAttachment> {
    @Override
    public int compare(NegotiationActivityAttachment o1, NegotiationActivityAttachment o2) {
        return o1.getActivity().getStartDate().compareTo(o2.getActivity().getStartDate());
    }
}
class FilenameAttachmentComparator implements Comparator<NegotiationActivityAttachment> {
    @Override
    public int compare(NegotiationActivityAttachment o1, NegotiationActivityAttachment o2) {
        return o1.getFile().getName().compareToIgnoreCase(o2.getFile().getName());
    }
}
class DescriptionAttachmentComparator implements Comparator<NegotiationActivityAttachment> {
    @Override
    public int compare(NegotiationActivityAttachment o1, NegotiationActivityAttachment o2) {
        return o1.getDescription().compareToIgnoreCase(o2.getDescription());
    }
}
class LocationAttachmentComparator implements Comparator<NegotiationActivityAttachment> {
    @Override
    public int compare(NegotiationActivityAttachment o1, NegotiationActivityAttachment o2) {
        return o1.getActivity().getLocation().getDescription().compareTo(o2.getActivity().getLocation().getDescription());
    }
}
class ActivityTypeAttachmentComparator implements Comparator<NegotiationActivityAttachment> {
    @Override
    public int compare(NegotiationActivityAttachment o1, NegotiationActivityAttachment o2) {
        return o1.getActivity().getActivityType().getDescription().compareTo(o2.getActivity().getActivityType().getDescription());
    }
}
