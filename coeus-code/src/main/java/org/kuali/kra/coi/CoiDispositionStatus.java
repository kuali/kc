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

public class CoiDispositionStatus extends KcPersistableBusinessObjectBase {
    


    private static final long serialVersionUID = -7876291367875185011L;
    public static final String IN_PROGRESS = "100";
    public static final String SUBMITTED_FOR_REVIEW = "300";
    public static final String UNDER_REVIEW_BY_COI_REVIEWER = "301";
    public static final String BEST_PRACTICES_MEMO = "215";
    public static final String NO_FURTHER_ACTION = "211";
    public static final String DISCLOSED_INTERESTS_ELIMINATED = "220";
    public static final String DISCLOSED_INTERESTS_REDUCED = "230";
    public static final String DISCLOSED_INTERESTS_MANAGED = "240";
    public static final String NO_CONFLICT_EXISTS = "210";
    public static final String EXEMPT = "200";
    public static final String DISCLOSED_INTERESTS_UNMANAGEABLE = "400";
    private String coiDispositionCode; 
    private String description; 
    private boolean displayToReporter;
    
    private CoiDisclosure coiDisclosure; 
    private CoiDisclosureStatus coiDisclosureStatus;
    private String coiDisclosureStatusCode;
    
    public CoiDispositionStatus() { 

    } 
    
    public String getCoiDispositionCode() {
        return coiDispositionCode;
    }

    public void setCoiDispositionCode(String coiDispositionCode) {
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

    public boolean isDisplayToReporter() {
        return displayToReporter;
    }

    public void setDisplayToReporter(boolean displayToReporter) {
        this.displayToReporter = displayToReporter;
    }

}