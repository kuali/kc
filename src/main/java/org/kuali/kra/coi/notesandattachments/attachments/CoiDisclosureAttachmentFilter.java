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
package org.kuali.kra.coi.notesandattachments.attachments;

import java.io.Serializable;
import java.util.Comparator;

import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;

public class CoiDisclosureAttachmentFilter implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4725272230342475909L;
    private String filterBy;
    private String sortBy;
    
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public Comparator<? super CoiDisclosureAttachment> getCoiDisclosureAttachmentComparator() {
        return new CoiDisclosureAttachmentComparatorFactory().getCoiDisclosureAttachmentComparator(getSortBy());
    }

    class CoiDisclosureAttachmentComparatorFactory {
        public Comparator<CoiDisclosureAttachment> getCoiDisclosureAttachmentComparator(String sortByCriteria) {
            if ("DESC".equalsIgnoreCase(sortByCriteria)) {
                return new CoiDisclosureAttachmentDescriptionComparator();
            } else if ("LAUP".equalsIgnoreCase(sortByCriteria)) {
                return new CoiDisclosureAttachmentLastUpdatedComparator();
            } else if ("UPBY".equalsIgnoreCase(sortByCriteria)) {
                return new CoiDisclosureAttachmentLastUpdatedByComparator();
            } else {
                return null;
            }
        }
    }

    private class CoiDisclosureAttachmentDescriptionComparator implements Comparator<CoiDisclosureAttachment> {
        public int compare(CoiDisclosureAttachment arg0, CoiDisclosureAttachment arg1) {
            return arg0.getDescription().compareTo(arg1.getDescription());
        }
    }
    
    private class CoiDisclosureAttachmentLastUpdatedComparator implements Comparator<CoiDisclosureAttachment> {
        public int compare(CoiDisclosureAttachment o1, CoiDisclosureAttachment o2) {
            return o1.getUpdateTimestamp().compareTo(o2.getUpdateTimestamp());
        }
    }
    
    private class CoiDisclosureAttachmentLastUpdatedByComparator implements Comparator<CoiDisclosureAttachment> {
        public int compare(CoiDisclosureAttachment o1, CoiDisclosureAttachment o2) {
            return o1.getUpdateUser().compareTo(o2.getUpdateUser());
        }
    }
}
