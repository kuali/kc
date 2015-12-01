/*
 * Copyright (c) 2014. Boston University
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND either express or
 * implied.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 */
package edu.bu.kuali.kra.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;


/**
 * This class represent transmission child fields for BU implementation (BUKC-00140.
 *
 * @author mkousheh
 */
public class AwardTransmissionChild extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -6299889040608863985L;

    private long transmissionChildId;
    private long transmissionId;
    private long awardId;
    private String parentDocumentNumber;
    private String childDocumentNumber;
    private String leadUnitNumber;
    private String childType;
    private String awardNumber;
    private String overheadKey;
    private String baseCode;
    private String offCampus;

    public long getTransmissionChildId() {
        return transmissionChildId;
    }

    public void setTransmissionChildId(long transmissionChildId) {
        this.transmissionChildId = transmissionChildId;
    }

    public long getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(long transmissionId) {
        this.transmissionId = transmissionId;
    }

    public long getAwardId() {
        return awardId;
    }

    public void setAwardId(long awardId) {
        this.awardId = awardId;
    }

    public String getParentDocumentNumber() {
        return parentDocumentNumber;
    }

    public void setParentDocumentNumber(String parentDocumentNumber) {
        this.parentDocumentNumber = parentDocumentNumber;
    }

    public String getChildDocumentNumber() {
        return childDocumentNumber;
    }

    public void setChildDocumentNumber(String childDocumentNumber) {
        this.childDocumentNumber = childDocumentNumber;
    }

    public String getLeadUnitNumber() {
        return leadUnitNumber;
    }

    public void setLeadUnitNumber(String leadUnitNumber) {
        this.leadUnitNumber = leadUnitNumber;
    }

    public String getChildType() {
        return childType;
    }

    public void setChildType(String childType) {
        this.childType = childType;
    }


    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public String getOverheadKey() {
        return overheadKey;
    }

    public void setOverheadKey(String overheadKey) {
        this.overheadKey = overheadKey;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getOffCampus() {
        return offCampus;
    }

    public void setOffCampus(String offCampus) {
        this.offCampus = offCampus;
    }

}
