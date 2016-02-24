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

/**
 * 
 * This class is the bo class of coi disclosure history
 */
public class CoiDisclosureHistory extends KcPersistableBusinessObjectBase {
    


    private static final long serialVersionUID = 6373301098038646558L;
    private Long coiDisclosureHistoryId; 
    private Long coiDisclosureId; 
    private String coiDisclosureNumber; 
    private Integer sequenceNumber; 
    private String disclosureStatus; 
    private String disclosureDispositionStatus; 
    private CoiDisclosure coiDisclosure;
    
    
    public CoiDisclosureHistory() { 

    } 
    
    public String getCoiDisclosureNumber() {
        return coiDisclosureNumber;
    }

    public void setCoiDisclosureNumber(String coiDisclosureNumber) {
        this.coiDisclosureNumber = coiDisclosureNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getDisclosureStatus() {
        return disclosureStatus;
    }

    public void setDisclosureStatus(String disclosureStatus) {
        this.disclosureStatus = disclosureStatus;
    }

    public String getDisclosureDispositionStatus() {
        return disclosureDispositionStatus;
    }

    public void setDisclosureDispositionStatus(String disclosureDispositionStatus) {
        this.disclosureDispositionStatus = disclosureDispositionStatus;
    }

    public Long getCoiDisclosureHistoryId() {
        return coiDisclosureHistoryId;
    }

    public void setCoiDisclosureHistoryId(Long coiDisclosureHistoryId) {
        this.coiDisclosureHistoryId = coiDisclosureHistoryId;
    }

    public Long getCoiDisclosureId() {
        return coiDisclosureId;
    }

    public void setCoiDisclosureId(Long coiDisclosureId) {
        this.coiDisclosureId = coiDisclosureId;
    }
    
    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }
    
}
