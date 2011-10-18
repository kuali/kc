/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.award.paymentreports;

/**
 * Enum that displays the different types of regenerations available.
 */
public enum ReportRegenerationType {

    /**
     * Fully regenerate all pending entries.
     */
    REGEN("Regenerate"), 
    /**
     * Never remove entries, only add new ones.
     */
    ADDONLY("Only Add New"),
    /**
     * Do not regenerate or add entries for this report class type.
     */
    NONE("None");
    
    private String description;
    
    ReportRegenerationType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
