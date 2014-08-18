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
