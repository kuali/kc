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

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class InvCoiDiscDetail extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Long invCoiDiscDetailsId;

    private Long personFinIntDisclosureId;

    private String coiDisclosureNumber;

    private String entityNumber;

    private Integer entitySequenceNumber;

    private Integer sequenceNumber;

    private Integer coiStatusCode;

    private Integer coiReviewerCode;

    private String description;

    private PersonFinIntDisclosure personFinIntDisclosure;

    // TODO : uncomment this when we start to work on this  
    //    private CoiReviewer coiReviewer;   
    public InvCoiDiscDetail() {
    }

    public Long getInvCoiDiscDetailsId() {
        return invCoiDiscDetailsId;
    }

    public void setInvCoiDiscDetailsId(Long invCoiDiscDetailsId) {
        this.invCoiDiscDetailsId = invCoiDiscDetailsId;
    }

    public Long getPersonFinIntDisclosureId() {
        return personFinIntDisclosureId;
    }

    public void setPersonFinIntDisclosureId(Long personFinIntDisclosureId) {
        this.personFinIntDisclosureId = personFinIntDisclosureId;
    }

    public String getCoiDisclosureNumber() {
        return coiDisclosureNumber;
    }

    public void setCoiDisclosureNumber(String coiDisclosureNumber) {
        this.coiDisclosureNumber = coiDisclosureNumber;
    }

    public String getEntityNumber() {
        return entityNumber;
    }

    public void setEntityNumber(String entityNumber) {
        this.entityNumber = entityNumber;
    }

    public Integer getEntitySequenceNumber() {
        return entitySequenceNumber;
    }

    public void setEntitySequenceNumber(Integer entitySequenceNumber) {
        this.entitySequenceNumber = entitySequenceNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getCoiStatusCode() {
        return coiStatusCode;
    }

    public void setCoiStatusCode(Integer coiStatusCode) {
        this.coiStatusCode = coiStatusCode;
    }

    public Integer getCoiReviewerCode() {
        return coiReviewerCode;
    }

    public void setCoiReviewerCode(Integer coiReviewerCode) {
        this.coiReviewerCode = coiReviewerCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PersonFinIntDisclosure getPersonFinIntDisclosure() {
        return personFinIntDisclosure;
    }

    public void setPersonFinIntDisclosure(PersonFinIntDisclosure personFinIntDisclosure) {
        this.personFinIntDisclosure = personFinIntDisclosure;
    }
}
