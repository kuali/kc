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
package org.kuali.kra.coi;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * This class represents the Coi Attachment Type.
 */
public class CoiAttachmentType extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 9039394358118993431L;

    private String code;

    private String description;

    /**
     * empty ctor to satisfy JavaBean convention.
     */
    public CoiAttachmentType() {
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
    public CoiAttachmentType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Gets the protocol attachment type code.
     * @return the protocol attachment type code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the protocol attachment type code.
     * @param code the protocol attachment type code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the protocol attachment type description.
     * @return the protocol attachment type description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the protocol attachment type description.
     * @param description the protocol attachment type description
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
        if (!(obj instanceof CoiAttachmentType)) {
            return false;
        }
        CoiAttachmentType other = (CoiAttachmentType) obj;
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
