/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.eclipselink.PortableSequenceGenerator;

/**
 * This class represents a congressional district. A congressional district consists of a two-letter
 * state code and a district number, although it is represented by a single string.
 */
@Entity
@Table(name = "EPS_PROP_CONG_DISTRICT")
public class CongressionalDistrict extends KraPersistableBusinessObjectBase {

    public static final int DISTRICT_NUMBER_LENGTH = 3;

    private static final long serialVersionUID = 9043098848918407500L;

    @PortableSequenceGenerator(name = "SEQ_CONG_DISTRICT_ID_KRA")
    @GeneratedValue(generator = "SEQ_CONG_DISTRICT_ID_KRA")
    @Id
    @Column(name = "CONG_DISTRICT_ID")
    private Long congressionalDistrictId;

    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    @Column(name = "SITE_NUMBER")
    private Integer siteNumber;

    @Column(name = "CONG_DISTRICT")
    private String congressionalDistrict;

    public void setCongressionalDistrictId(Long congressionalDistrictId) {
        this.congressionalDistrictId = congressionalDistrictId;
    }

    public Long getCongressionalDistrictId() {
        return congressionalDistrictId;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setSiteNumber(Integer siteNumber) {
        this.siteNumber = siteNumber;
    }

    public Integer getSiteNumber() {
        return siteNumber;
    }

    public String getCongressionalDistrict() {
        return congressionalDistrict;
    }

    public void setCongressionalDistrict(String stateCode, String districtNumber) {
        setCongressionalDistrict(stateCode + "-" + districtNumber);
    }

    public void setCongressionalDistrict(String congressionalDistrict) {
        this.congressionalDistrict = congressionalDistrict;
    }
}
