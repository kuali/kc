/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s.polling;

/**
 * 
 * This class stores the status of a task that is to be scheduled
 */
public class StatusInfo {
    private int code;
    private String value;

    /**
     * Getter for property code.
     * 
     * @return Value of property code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Setter for property code.
     * 
     * @param code New value of property code.
     */
    public void setCode(String code) {
        this.code = (code != null && code.trim().length() > 0) ? Integer.parseInt(code) : 0;
    }

    /**
     * Getter for property value.
     * 
     * @return Value of property value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter for property value.
     * 
     * @param value New value of property value.
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }
}

