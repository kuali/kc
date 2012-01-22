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
package org.kuali.kra.coi;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class CoiDisclosureEventType extends KraPersistableBusinessObjectBase { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    public static final long serialVersionUID = -933728957419448190L;
    public static final String ANNUAL = "14";
    public static final String AWARD = "1";
    public static final String DEVELOPMENT_PROPOSAL = "2";
    public static final String INSTITUTIONAL_PROPOSAL = "10";
    public static final String IRB_PROTOCOL = "3";
    public static final String IACUC_PROTOCOL = "4";
    public static final String NEW = "5";
    public static final String UPDATE = "6";
    public static final String OTHER = "7";
    public static final String MANUAL_AWARD = "11";
    public static final String MANUAL_DEVELOPMENT_PROPOSAL = "12";
    public static final String MANUAL_IRB_PROTOCOL = "13";
    private String eventTypeCode; 
    private String description; 
    
    
    public CoiDisclosureEventType() { 

    }


    public String getEventTypeCode() {
        return eventTypeCode;
    }


    public void setEventTypeCode(String eventTypeCode) {
        this.eventTypeCode = eventTypeCode;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    } 
    

}
