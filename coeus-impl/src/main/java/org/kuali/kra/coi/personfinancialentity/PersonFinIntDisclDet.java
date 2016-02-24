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
package org.kuali.kra.coi.personfinancialentity;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * 
 * This class is for Person Int. FE details
 */
public class PersonFinIntDisclDet extends KcPersistableBusinessObjectBase {


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
