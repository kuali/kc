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

package org.kuali.coeus.irb.api.dto;

import com.codiform.moo.annotation.CollectionProperty;
import com.codiform.moo.annotation.Property;

import java.util.List;

public class IrbProtocolDto {

    @Property(translate = false)
    String docNbr;
    String status;
    String principalInvestigatorId;
    String leadUnitNumber;
    String title;
    String referenceNumber1;
    @Property(source = "description")
    String summary;
    private String protocolTypeCode;
    @CollectionProperty(itemClass=IrbProtocolPersonDto.class)
    List<IrbProtocolPersonDto> protocolPersons;

    public String getPrincipalInvestigatorId() {
        return principalInvestigatorId;
    }

    public void setPrincipalInvestigatorId(String principalInvestigatorId) {
        this.principalInvestigatorId = principalInvestigatorId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getProtocolTypeCode() {
        return protocolTypeCode;
    }

    public void setProtocolTypeCode(String protocolTypeCode) {
        this.protocolTypeCode = protocolTypeCode;
    }

    public String getLeadUnitNumber() {
        return leadUnitNumber;
    }

    public void setLeadUnitNumber(String leadUnitNumber) {
        this.leadUnitNumber = leadUnitNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<IrbProtocolPersonDto> getProtocolPersons() {
        return protocolPersons;
    }

    public void setPersons(List<IrbProtocolPersonDto> protocolPersons) {
        this.protocolPersons = protocolPersons;
    }

    public String getReferenceNumber1() {
        return referenceNumber1;
    }

    public void setReferenceNumber1(String referenceNumber1) {
        this.referenceNumber1 = referenceNumber1;
    }

    public String getDocNbr() {
        return docNbr;
    }

    public void setDocNbr(String docNbr) {
        this.docNbr = docNbr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
