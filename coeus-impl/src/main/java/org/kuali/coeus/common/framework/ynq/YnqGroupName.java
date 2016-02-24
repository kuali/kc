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
package org.kuali.coeus.common.framework.ynq;

import org.kuali.rice.krad.bo.TransientBusinessObjectBase;

public class YnqGroupName extends TransientBusinessObjectBase implements Comparable<YnqGroupName> {
    
    /** the max length of a group name before truncation occurs. */
    public static final int GROUP_NAME_MAX_LENGTH = 87;
    
    private static final long serialVersionUID = 5914454462176363253L;
    private static final String TRAILING_STRING = "...";
    
    private String groupName;
    private String truncGroupName;

    /** gets the group name. */ 
    public String getGroupName() {
        return this.groupName;
    }

    /**
     * Sets the group name.  
     * 
     * <p>
     * If the group name exceeds {@link #GROUP_NAME_MAX_LENGTH GROUP_NAME_MAX_LENGTH}
     * then the group name truncated and a trailing ellipse is concatenated. This value is then assigned to
     * the truncated group name property.  If the group name does not need truncated then the entire group
     * name is assigned to the truncated group name property.
     * </p>
     *  
     * @param groupName the group name
     * @throws NullPointerException if the group name is null.
     */
    public void setGroupName(String groupName) {
        
        if (groupName == null) {
            throw new NullPointerException("the groupName is null");
        }
        
        this.groupName = groupName;
        /* truncate group name to display in tab */
        if(groupName.length() > GROUP_NAME_MAX_LENGTH) {
            this.truncGroupName = groupName.substring(0, GROUP_NAME_MAX_LENGTH).concat(TRAILING_STRING);
        }else {
            this.truncGroupName = groupName;
        }
    }

    /** gets the truncated group name */
    public String getTruncGroupName() {
        return this.truncGroupName;
    }

    /**
     * Compares by group name.
     */
    public int compareTo(YnqGroupName ynqGroupName) {
        return this.getGroupName().compareTo(ynqGroupName.getGroupName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
            + ((this.groupName == null) ? 0 : this.groupName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof YnqGroupName)) {
            return false;
        }
        YnqGroupName other = (YnqGroupName) obj;
        if (this.groupName == null) {
            if (other.groupName != null) {
                return false;
            }
        } else if (!this.groupName.equals(other.groupName)) {
            return false;
        }
        return true;
    }
}
