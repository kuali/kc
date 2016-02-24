/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
