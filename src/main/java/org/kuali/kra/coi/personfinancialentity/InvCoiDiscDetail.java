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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class InvCoiDiscDetail extends KraPersistableBusinessObjectBase {

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
