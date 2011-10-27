/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.coi;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.rice.kns.util.KualiDecimal;

public class CoiDisclProject extends KraPersistableBusinessObjectBase { 
    
    private static final long serialVersionUID = 1L;

    private Long coiDisclProjectsId; 
    private Long coiDisclosureId; 
    private String coiDisclosureNumber; 
    private Integer sequenceNumber; 
    private String coiProjectId; 
    private String coiProjectTitle; 
    private String coiProjectType; 
    private String coiProjectSponsor; 
    private Date coiProjectStartDate; 
    private Date coiProjectEndDate; 
    private KualiDecimal coiProjectFundingAmount; 
    private String coiProjectRole; 
    private boolean disclosureFlag;
    private ProposalType proposalType;
    
    private CoiDisclosure coiDisclosure; 
    // for UI purposes
    private List<CoiDiscDetail> coiDiscDetails; 

    public CoiDisclProject(String coiDisclosureNumber, Integer sequenceNumber) { 
        this.coiDisclosureNumber = coiDisclosureNumber;
        this.sequenceNumber = sequenceNumber;

    } 
    public CoiDisclProject() { 

    } 
   
    public Long getCoiDisclProjectsId() {
        return coiDisclProjectsId;
    }

    public void setCoiDisclProjectsId(Long coiDisclProjectsId) {
        this.coiDisclProjectsId = coiDisclProjectsId;
    }

    public Long getCoiDisclosureId() {
        return coiDisclosureId;
    }

    public void setCoiDisclosureId(Long coiDisclosureId) {
        this.coiDisclosureId = coiDisclosureId;
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

    public String getCoiProjectId() {
        return coiProjectId;
    }

    public void setCoiProjectId(String coiProjectId) {
        this.coiProjectId = coiProjectId;
    }

    public String getCoiProjectTitle() {
        return coiProjectTitle;
    }

    public void setCoiProjectTitle(String coiProjectTitle) {
        this.coiProjectTitle = coiProjectTitle;
    }

    public String getCoiProjectType() {
        return coiProjectType;
    }

    public void setCoiProjectType(String coiProjectType) {
        this.coiProjectType = coiProjectType;
    }

    public String getCoiProjectSponsor() {
        return coiProjectSponsor;
    }

    public void setCoiProjectSponsor(String coiProjectSponsor) {
        this.coiProjectSponsor = coiProjectSponsor;
    }

    public Date getCoiProjectStartDate() {
        return coiProjectStartDate;
    }

    public void setCoiProjectStartDate(Date coiProjectStartDate) {
        this.coiProjectStartDate = coiProjectStartDate;
    }

    public Date getCoiProjectEndDate() {
        return coiProjectEndDate;
    }

    public void setCoiProjectEndDate(Date coiProjectEndDate) {
        this.coiProjectEndDate = coiProjectEndDate;
    }

    public KualiDecimal getCoiProjectFundingAmount() {
        return coiProjectFundingAmount;
    }

    public void setCoiProjectFundingAmount(KualiDecimal coiProjectFundingAmount) {
        this.coiProjectFundingAmount = coiProjectFundingAmount;
    }

    public String getCoiProjectRole() {
        return coiProjectRole;
    }

    public void setCoiProjectRole(String coiProjectRole) {
        this.coiProjectRole = coiProjectRole;
    }

    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("coiDisclProjectsId", this.getCoiDisclProjectsId());
        hashMap.put("coiDisclosureId", this.getCoiDisclosureId());
        hashMap.put("coiDisclosureNumber", this.getCoiDisclosureNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("coiProjectId", this.getCoiProjectId());
        hashMap.put("coiProjectTitle", this.getCoiProjectTitle());
        hashMap.put("coiProjectType", this.getCoiProjectType());
        hashMap.put("coiProjectSponsor", this.getCoiProjectSponsor());
        hashMap.put("coiProjectStartDate", this.getCoiProjectStartDate());
        hashMap.put("coiProjectEndDate", this.getCoiProjectEndDate());
        hashMap.put("coiProjectFundingAmount", this.getCoiProjectFundingAmount());
        hashMap.put("coiProjectRole", this.getCoiProjectRole());
        return hashMap;
    }

    public ProposalType getProposalType() {
        return proposalType;
    }

    public void setProposalType(ProposalType proposalType) {
        this.proposalType = proposalType;
    }
    public boolean isDisclosureFlag() {
        return disclosureFlag;
    }
    public void setDisclosureFlag(boolean disclosureFlag) {
        this.disclosureFlag = disclosureFlag;
    }
    public List<CoiDiscDetail> getCoiDiscDetails() {
        return coiDiscDetails;
    }
    public void setCoiDiscDetails(List<CoiDiscDetail> coiDiscDetails) {
        this.coiDiscDetails = coiDiscDetails;
    }
    
}