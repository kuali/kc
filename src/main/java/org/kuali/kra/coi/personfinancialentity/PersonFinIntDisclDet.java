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
package org.kuali.kra.coi.personfinancialentity;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class is for Person Int. FE details
 */
public class PersonFinIntDisclDet extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1121307383084809439L;
    private Long perFinIntDisclDetId; 
    private String entityNumber; 
    private Integer sequenceNumber; 
    private String columnName; 
    private String columnValue; 
    private String relationshipTypeCode; 
    private String comments; 
    private Long personFinIntDisclosureId; 

    private FinEntitiesDataMatrix finEntitiesDataMatrix; 
    private PersonFinIntDisclosure personFinIntDisclosure;
    private FinIntEntityRelType finIntEntityRelType;
    
    public PersonFinIntDisclDet() { 

    } 
    
    public Long getPerFinIntDisclDetId() {
        return perFinIntDisclDetId;
    }

    public void setPerFinIntDisclDetId(Long perFinIntDisclDetId) {
        this.perFinIntDisclDetId = perFinIntDisclDetId;
    }

    public String getEntityNumber() {
        return entityNumber;
    }

    public void setEntityNumber(String entityNumber) {
        this.entityNumber = entityNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public String getRelationshipTypeCode() {
        return relationshipTypeCode;
    }

    public void setRelationshipTypeCode(String relationshipTypeCode) {
        this.relationshipTypeCode = relationshipTypeCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public FinEntitiesDataMatrix getFinEntitiesDataMatrix() {
        return finEntitiesDataMatrix;
    }

    public void setFinEntitiesDataMatrix(FinEntitiesDataMatrix finEntitiesDataMatrix) {
        this.finEntitiesDataMatrix = finEntitiesDataMatrix;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("perFinIntDisclDetId", this.getPerFinIntDisclDetId());
        hashMap.put("entityNumber", this.getEntityNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("columnName", this.getColumnName());
        hashMap.put("columnValue", this.getColumnValue());
        hashMap.put("relationshipTypeCode", this.getRelationshipTypeCode());
        hashMap.put("comments", this.getComments());
        return hashMap;
    }

    public Long getPersonFinIntDisclosureId() {
        return personFinIntDisclosureId;
    }

    public void setPersonFinIntDisclosureId(Long personFinIntDisclosureId) {
        this.personFinIntDisclosureId = personFinIntDisclosureId;
    }

    public PersonFinIntDisclosure getPersonFinIntDisclosure() {
        return personFinIntDisclosure;
    }

    public void setPersonFinIntDisclosure(PersonFinIntDisclosure personFinIntDisclosure) {
        this.personFinIntDisclosure = personFinIntDisclosure;
    }

    public FinIntEntityRelType getFinIntEntityRelType() {
        if (StringUtils.isNotBlank(relationshipTypeCode) && finIntEntityRelType == null) {
            this.refreshReferenceObject("finIntEntityRelType");
        }
        return finIntEntityRelType;
    }

    public void setFinIntEntityRelType(FinIntEntityRelType finIntEntityRelType) {
        this.finIntEntityRelType = finIntEntityRelType;
    }
    
}