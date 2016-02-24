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
package org.kuali.kra.protocol.noteattachment;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 
 * This class is a business object which represents the 
 * ProtocolBase Attachment Filter.  This filter is used to limit
 * and sort protocol attachments.
 */
public abstract class ProtocolAttachmentFilterBase implements Serializable {

    private static final long serialVersionUID = 53138457226971783L;
    
    protected String filterBy;
    protected String sortBy;
    
    public String getFilterBy() {
        return filterBy;
    }
    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }
    public String getSortBy() {
        return sortBy;
    }
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    
    /**
     * 
     * This method returns a comparator used to sort protocol attachments
     * @return a comparator used to sort protocol attachments
     */
    public Comparator<ProtocolAttachmentProtocolBase> getProtocolAttachmentComparator() {
        return new ProtocolAttachmentComparatorFactory().getProtocolAttachmentComparator(getSortBy());
    }
    
    


class ProtocolAttachmentComparatorFactory {
    public Comparator<ProtocolAttachmentProtocolBase> getProtocolAttachmentComparator(String sortBy) {
        if ("DESC".equalsIgnoreCase(sortBy)) {
            return new ProtocolAttachmentDescriptionComparator();
        } else if ("ATTP".equalsIgnoreCase(sortBy)) {
            return new ProtocolAttachmentAttachmentTypeComparator();
        } else if ("LAUP".equalsIgnoreCase(sortBy)) {
            return new ProtocolAttachmentLastUpdatedComparator();
        } else if ("UPBY".equalsIgnoreCase(sortBy)) {
            return new ProtocolAttachmentLastUpdatedByComparator();
        } else {
            return null;
        }
    }
}

    private class ProtocolAttachmentDescriptionComparator implements Comparator<ProtocolAttachmentProtocolBase>
    {
    
        public int compare(ProtocolAttachmentProtocolBase arg0, ProtocolAttachmentProtocolBase arg1) {
            return arg0.getDescription().compareTo(arg1.getDescription());
        }
        
    }
    
    private class ProtocolAttachmentAttachmentTypeComparator implements Comparator<ProtocolAttachmentProtocolBase>
    {
    
        public int compare(ProtocolAttachmentProtocolBase o1, ProtocolAttachmentProtocolBase o2) {
            return o1.getType().getDescription().compareTo(o2.getType().getDescription());
        }
        
    }
    
    private class ProtocolAttachmentLastUpdatedComparator implements Comparator<ProtocolAttachmentProtocolBase>
    {
    
        public int compare(ProtocolAttachmentProtocolBase o1, ProtocolAttachmentProtocolBase o2) {
            return o1.getUpdateTimestamp().compareTo(o2.getUpdateTimestamp());
        }
        
    }
    
    private class ProtocolAttachmentLastUpdatedByComparator implements Comparator<ProtocolAttachmentProtocolBase>
    {
    
        public int compare(ProtocolAttachmentProtocolBase o1, ProtocolAttachmentProtocolBase o2) {
            return o1.getUpdateUserFullName().compareTo(o2.getUpdateUserFullName());
        }
        
    }

}
