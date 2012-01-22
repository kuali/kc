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
package org.kuali.kra.bo;

import org.kuali.kra.irb.correspondence.CorrespondentType;

public class OrganizationCorrespondent extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Integer organizationCorrespondentId;

    private String organizationId;

    private Integer correspondentTypeCode;

    private String personId;

    private boolean nonEmployeeFlag;

    private String description;

    private Organization organization;

    private CorrespondentType correspondentType;

    public OrganizationCorrespondent() {
        super();
    }

    public Integer getOrganizationCorrespondentId() {
        return organizationCorrespondentId;
    }

    public void setOrganizationCorrespondentId(Integer organizationCorrespondentId) {
        this.organizationCorrespondentId = organizationCorrespondentId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getCorrespondentTypeCode() {
        return correspondentTypeCode;
    }

    public void setCorrespondentTypeCode(Integer correspondentTypeCode) {
        this.correspondentTypeCode = correspondentTypeCode;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public boolean getNonEmployeeFlag() {
        return nonEmployeeFlag;
    }

    public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public CorrespondentType getCorrespondentType() {
        return correspondentType;
    }

    public void setCorrespondentType(CorrespondentType correspondentType) {
        this.correspondentType = correspondentType;
    }

    public KcPerson getPerson() {
        return getKcPersonService().getKcPersonByPersonId(personId);
    }
}
