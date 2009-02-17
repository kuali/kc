/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.bo;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;

import java.util.LinkedHashMap;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

public class ProtocolFundingSource extends KraPersistableBusinessObjectBase {

    private Long protocolFundingSourceId;
    private Long protocolId;
    private String protocolNumber;
    private Integer sequenceNumber;
    private Integer fundingSourceTypeCode;
    private String fundingSource;

    private Protocol protocol;
    private FundingSourceType fundingSourceType;

    private String fundingSourceTitle;
    private String fundingSourceName;
    
    private Unit fundingUnit;
    private Sponsor fundingSponsor;
    private ProposalDevelopmentDocument fundingProposal;
    private Award fundingAward;
    
    
    public Unit getFundingUnit() {
        return fundingUnit;
    }

    public void setFundingUnit(Unit fundingUnit) {
        this.fundingUnit = fundingUnit;
    }

    public Sponsor getFundingSponsor() {
        return fundingSponsor;
    }

    public void setFundingSponsor(Sponsor fundingSponsor) {
        this.fundingSponsor = fundingSponsor;
    }

    public ProposalDevelopmentDocument getFundingProposal() {
        return fundingProposal;
    }

    public void setFundingProposal(ProposalDevelopmentDocument fundingProposal) {
        this.fundingProposal = fundingProposal;
    }

    public Award getFundingAward() {
        return fundingAward;
    }

    public void setFundingAward(Award fundingAward) {
        this.fundingAward = fundingAward;
    }

    public String getFundingSourceTitle() {
        return fundingSourceTitle;
    }

    public void setFundingSourceTitle(String fundingSourceTitle) {
        this.fundingSourceTitle = fundingSourceTitle;
    }

    public String getFundingSourceName() {
        return fundingSourceName;
    }

    public void setFundingSourceName(String fundingSourceName) {
        this.fundingSourceName = fundingSourceName;
    }

    public ProtocolFundingSource() {

    }

    public Long getProtocolFundingSourceId() {
        return protocolFundingSourceId;
    }

    public void setProtocolFundingSourceId(Long protocolFundingSourceId) {
        this.protocolFundingSourceId = protocolFundingSourceId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getFundingSourceTypeCode() {
        return fundingSourceTypeCode;
    }

    public void setFundingSourceTypeCode(Integer fundingSourceTypeCode) {
        this.fundingSourceTypeCode = fundingSourceTypeCode;

        // When the type code changes, the corresponding
        //  field must also be updated.  A refresh will
        // cause a read from the database. By
        // the magic of OJB, the data member is automatically updated.
        
        if (this.fundingSourceTypeCode == null || this.fundingSourceTypeCode.equals("")) {
            fundingSourceTypeCode = null;
        } else {
            this.refreshReferenceObject("fundingSourceType");
        }
        
    }

    public String getFundingSource() {
        return fundingSource;        
    }

    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public FundingSourceType getFundingSourceType() {
        return fundingSourceType;
    }

    public void setFundingSourceType(FundingSourceType fundingSourceType) {
        this.fundingSourceType = fundingSourceType;
    }

    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("protocolFundingSourceId", getProtocolFundingSourceId());
        hashMap.put("protocolId", getProtocolId());
        hashMap.put("protocolNumber", getProtocolNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("fundingSourceTypeCode", getFundingSourceTypeCode());
        hashMap.put("fundingSource", getFundingSource());
        return hashMap;
    }

}