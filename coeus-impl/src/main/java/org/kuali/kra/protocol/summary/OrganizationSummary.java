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
package org.kuali.kra.protocol.summary;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;

import java.io.Serializable;

public class OrganizationSummary implements Serializable {

    private static final long serialVersionUID = -774036516393407380L;
    
    private String id;
    private String organizationId;
    private String name;
    private String type;
    private Integer contactId;
    private String contact;
    private String fwaNumber;
    
    private boolean idChanged;
    private boolean nameChanged;
    private boolean typeChanged;
    private boolean contactChanged;
    private boolean fwaNumberChanged;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getOrganizationId() {
        return organizationId;
    }
    
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getContactId() {
        return contactId;
    }
    
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(Rolodex rolodex) {
        StringBuffer buf = new StringBuffer();
        if (rolodex != null) {
            if (append(buf, null, rolodex.getLastName(), null)) {
                append(buf, ", ", rolodex.getFirstName(), null);
                buf.append(" : ");
            }
           
            if (append(buf, null, rolodex.getAddressLine1(), null)) {
                if (append(buf, ", ", rolodex.getAddressLine2(), null)) {
                    append(buf, ", ", rolodex.getAddressLine3(), null);
                }
                buf.append(", ");
            }
            append(buf, null, rolodex.getCity(), ", ");
            if (append(buf, null, rolodex.getState(), null)) {
                append(buf, " ", rolodex.getPostalCode(), null);
            }
        }
        contact = buf.toString();
    }
    
    private boolean append(StringBuffer buf, String prefix, String text, String suffix) {
        if (text == null) {
            return false;
        }
        if (prefix != null) {
            buf.append(prefix);
        }
        buf.append(text);
        if (suffix != null) {
            buf.append(suffix);
        }
        return true;
    }

    public String getFwaNumber() {
        return fwaNumber;
    }
    
    public void setFwaNumber(String fwaNumber) {
        this.fwaNumber = fwaNumber;
    }

    public void compare(ProtocolSummary other) {
        OrganizationSummary otherOrganization = other.findOrganization(organizationId);
        if (otherOrganization == null) {
            idChanged = true;
            nameChanged = true;
            typeChanged = true;
            contactChanged = true;
            fwaNumberChanged = true;
        }
        else {
            idChanged = !StringUtils.equals(id, otherOrganization.id);
            nameChanged = !StringUtils.equals(name, otherOrganization.name);
            typeChanged = !StringUtils.equals(type, otherOrganization.type);
            contactChanged = !StringUtils.equals(contact, otherOrganization.contact);
            fwaNumberChanged = !StringUtils.equals(fwaNumber, otherOrganization.fwaNumber);
        }
    }

    public boolean isIdChanged() {
        return idChanged;
    }

    public boolean isNameChanged() {
        return nameChanged;
    }

    public boolean isTypeChanged() {
        return typeChanged;
    }

    public boolean isContactChanged() {
        return contactChanged;
    }

    public boolean isFwaNumberChanged() {
        return fwaNumberChanged;
    }
}
