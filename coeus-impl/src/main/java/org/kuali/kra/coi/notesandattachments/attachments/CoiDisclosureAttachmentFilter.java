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
package org.kuali.kra.coi.notesandattachments.attachments;

import java.io.Serializable;
import java.util.Comparator;

public class CoiDisclosureAttachmentFilter implements Serializable {


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
            return o2.getUpdateTimestamp().compareTo(o1.getUpdateTimestamp());
        }
    }
    
    private class CoiDisclosureAttachmentLastUpdatedByComparator implements Comparator<CoiDisclosureAttachment> {
        public int compare(CoiDisclosureAttachment o1, CoiDisclosureAttachment o2) {
            return o1.getUpdateUser().compareTo(o2.getUpdateUser());
        }
    }
}
