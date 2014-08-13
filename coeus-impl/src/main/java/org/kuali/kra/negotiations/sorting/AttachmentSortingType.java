/*
 * Copyright 2005-2014 The Kuali Foundation
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