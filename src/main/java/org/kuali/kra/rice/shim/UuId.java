/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.rice.shim;

import java.io.Serializable;

import org.kuali.rice.kew.user.UserId;

/**
 * This class is a wrapper class for UUID
 * 
 * 
 */
public final class UuId implements UserId, Serializable {
    private static final long serialVersionUID = -8581055150173349593L;

    public static final UuId NOT_FOUND = new UuId("not found");

    private String uuId;

    /**
     * Constructor that takes in a string uuid
     * 
     * @param uuId
     */
    public UuId(String uuId) {
        setUuId(uuId);
    }

    /**
     * Empty constructor
     * 
     */
    public UuId() {
    }

    /**
     * simple getter for string uuid
     * 
     * @return
     */
    public String getUuId() {
        return uuId;
    }

    /**
     * simple setter for uuid
     * 
     * @param uuId
     */
    public void setUuId(String uuId) {
        this.uuId = (uuId == null ? null : uuId.trim());
    }
    
    public String getId() {
        return getUuId();
    }

    /**
     * Returns true if this userId has an empty value. Empty userIds can't be used as keys in a Hash, among other things.
     * 
     * @return true if this instance doesn't have a value
     */
    public boolean isEmpty() {
        return (uuId == null || uuId.trim().length() == 0);
    }

    /**
     * Override of equals that allows us to compare uuids If you make this class non-final, you must rewrite equals to work for
     * subclasses.
     */
    public boolean equals(Object obj) {
        boolean isEqual = false;

        if (obj != null && (obj instanceof UuId)) {
            UuId a = (UuId) obj;

            if (getUuId() == null) {
                return false;
            }

            return uuId.equals(a.uuId);
        }

        return false;
    }

    /**
     * override of hashCode since we overrode equals
     */
    public int hashCode() {
        return uuId == null ? 0 : uuId.hashCode();
    }

    /**
     * override of toString that will print uuid
     */
    public String toString() {
        return uuId;
    }
}