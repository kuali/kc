/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.noteattachment;

import java.util.LinkedHashMap;

import org.kuali.kra.irb.personnel.ProtocolPerson;

/**
 * This class represents the Protocol Attachment Personnel.
 */
public class ProtocolAttachmentPersonnel extends ProtocolAttachmentBase {

    private static final long serialVersionUID = -7115904344245464654L;
    private static final String GROUP_CODE = "2";
    
    private String personId;
    private ProtocolPerson person;
    
    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public ProtocolAttachmentPersonnel() {
        super();
    }
    
    /**
     * Convenience ctor to set the protocol id.
     * 
     * <p>
     * This ctor does not validate any of the properties.
     * </p>
     * 
     * @param protocolId the protocol Id.
     */
    public ProtocolAttachmentPersonnel(final Long protocolId) {
        super(protocolId);
    }
    
    /**
     * Gets the Protocol Attachment Personnel Person Id.
     * @return the Protocol Attachment Personnel Person Id
     */
    public String getPersonId() {
        return this.personId;
    }

    /**
     * Sets the Protocol Attachment Personnel Person Id.
     * @param personId the Protocol Attachment Personnel Person Id
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    /**
     * Gets the Protocol Attachment Personnel Person.
     * @return the Protocol Attachment Personnel Person
     */
    public ProtocolPerson getPerson() {
        return this.person;
    }

    /**
     * Sets the Protocol Attachment Personnel Person.
     * @param person the Protocol Attachment Personnel Person
     */
    public void setPerson(ProtocolPerson person) {
        this.person = person;
    }

    /** {@inheritDoc} */
    @Override
    public String getGroupCode() {
        return GROUP_CODE;
    }
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("personId", this.getPersonId());
        return hashMap;
    }
}
