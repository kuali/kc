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

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * This class represents the ProtocolBase Attachment Status.
 */
public abstract class ProtocolAttachmentStatusBase extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 2053606476193782286L;

    public static final String DRAFT = "1";
    public static final String FINALIZED = "2";
    public static final String DELETED = "3";
    public static final String SUPERCEDED = "4";
    public static final String EXPIRED = "5";    
    
    private String code;

    private String description;


    /**
     * empty ctor to satisfy JavaBean convention.
     */
    protected ProtocolAttachmentStatusBase() {
        super();
    }

    /**
     * Convenience ctor to set the relevant properties of this class.
     * 
     * <p>
     * This ctor does not validate any of the properties.
     * </p>
     * 
     * @param code the code.
     * @param description the description.
     */
    public ProtocolAttachmentStatusBase(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Gets the protocol attachment status code.
     * @return the protocol attachment status code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the protocol attachment status code.
     * @param code the protocol attachment status code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the protocol attachment status description.
     * @return the protocol attachment status description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the protocol attachment status description.
     * @param description the protocol attachment status description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
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
        if (!(obj instanceof ProtocolAttachmentStatusBase)) {
            return false;
        }
        ProtocolAttachmentStatusBase other = (ProtocolAttachmentStatusBase) obj;
        if (this.code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!this.code.equals(other.code)) {
            return false;
        }
        if (this.description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!this.description.equals(other.description)) {
            return false;
        }
        return true;
    }
}
