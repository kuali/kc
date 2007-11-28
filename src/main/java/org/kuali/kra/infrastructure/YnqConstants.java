/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.infrastructure;

import org.kuali.core.web.ui.KeyLabelPair;

public enum YnqConstants {
    YES ("Y", "Yes"),
    NO("N", "No"),
    NA("X", "N/A"),
    YES_NO("YN", "Yes,No"),
    YES_NO_NA("YNX", "Yes,No,N/A"),
    YES_NA("YX", "Yes,N/A"),
    NO_NA("NX", "No,N/A"),
    NONE(null, "None");

    private final String code;   
    private final String description; 
    YnqConstants(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String code()   
    { 
        return code; 
    }

    public String description() 
    { 
        return description; 
    }

}

