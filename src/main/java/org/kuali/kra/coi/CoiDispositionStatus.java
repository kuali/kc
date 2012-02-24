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

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class CoiDispositionStatus extends KraPersistableBusinessObjectBase { 
    

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7876291367875185011L;
    public static final String IN_PROGRESS = "100";
    public static final String SUBMITTED_FOR_REVIEW = "200";
    public static final String UNDER_REVIEW_BY_COI_REVIEWER = "201";
    public static final String BEST_PRACTICES_MEMO = "300";
    public static final String NO_FURTHER_ACTION = "301";
    public static final String DISCLOSED_INTERESTS_ELIMINATED = "302";
    public static final String DISCLOSED_INTERESTS_REDUCED = "303";
    public static final String DISCLOSED_INTERESTS_MANAGED = "304";
    public static final String NO_CONFLICT_EXISTS = "305";
    public static final String EXEMPT = "306";
    public static final String DISCLOSED_INTERESTS_UNMANAGEABLE = "400";
    private Integer coiDispositionCode; 
    private String description; 
    
    private CoiDisclosure coiDisclosure; 
    private CoiDisclosureStatus coiDisclosureStatus;
    private String coiDisclosureStatusCode;
    
    public CoiDispositionStatus() { 

    } 
    
    public Integer getCoiDispositionCode() {
        return coiDispositionCode;
    }

    public void setCoiDispositionCode(Integer coiDispositionCode) {
        this.coiDispositionCode = coiDispositionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    public void setCoiDisclosureStatus(CoiDisclosureStatus coiDisclosureStatus) {
        this.coiDisclosureStatus = coiDisclosureStatus;
    }

    public CoiDisclosureStatus getCoiDisclosureStatus() {
        return coiDisclosureStatus;
    }

    public void setCoiDisclosureStatusCode(String coiDisclosureStatusCode) {
        this.coiDisclosureStatusCode = coiDisclosureStatusCode;
    }

    public String getCoiDisclosureStatusCode() {
        return coiDisclosureStatusCode;
    }

}