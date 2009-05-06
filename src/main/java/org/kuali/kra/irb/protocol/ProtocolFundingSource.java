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
package org.kuali.kra.irb.protocol;

import java.util.LinkedHashMap;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.irb.ProtocolAssociate;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.springframework.util.StringUtils;

public class ProtocolFundingSource extends ProtocolAssociate {

    private Long protocolFundingSourceId;
    private Long protocolId;
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
    
    public ProtocolFundingSource() {
    }
    
    public ProtocolFundingSource(String fundingSource, FundingSourceType fundingSourceType,String fundingSourceName,String fundingSourceTitle) {
        this.fundingSource = fundingSource;
        this.fundingSourceType= fundingSourceType;
        this.fundingSourceName = fundingSourceName;
        this.fundingSourceTitle = fundingSourceTitle;
        if  (fundingSourceType != null) {
            this.fundingSourceTypeCode= fundingSourceType.getFundingSourceTypeCode();
        }
    }
    
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
        if (ProtocolFundingSourceServiceImpl.FundingSourceLookup.PROPOSAL_DEVELOPMENT.getFundingTypeCode()==getFundingSourceType().getFundingSourceTypeCode() 
                && StringUtils.hasText(getFundingSource())) {
            this.refreshReferenceObject("fundingProposal");
        }
        return fundingProposal;
    }

    public void setFundingProposal(ProposalDevelopmentDocument fundingProposal) {
        this.fundingProposal = fundingProposal;
    }

    public Award getFundingAward() {
        if (getFundingSourceType().getDescription().compareTo("Award")==0 
                && StringUtils.hasText(getFundingSource())) {
            this.refreshReferenceObject("fundingAward");
        }
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
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("protocolFundingSourceId", getProtocolFundingSourceId());
        hashMap.put("protocolId", getProtocolId());
        hashMap.put("fundingSourceTypeCode", getFundingSourceTypeCode());
        hashMap.put("fundingSource", getFundingSource());
        hashMap.put("fundingSourceName", getFundingSourceName());
        hashMap.put("fundingSourceTitle", getFundingSourceTitle());
        return hashMap;
    }

    public void init(Protocol protocol) {
        setProtocolFundingSourceId(null);
        setProtocolId(protocol.getProtocolId());
        setProtocol(protocol);
        setProtocolNumber(protocol.getProtocolNumber());
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean isEqual=true;
        if (obj==null || !(obj instanceof ProtocolFundingSource)) {
             return false;
        }
        
        ProtocolFundingSource protocolFundingSource = (ProtocolFundingSource) obj;
        if (!protocolFundingSource.getFundingSource().equalsIgnoreCase(getFundingSource())) {
            isEqual=false;
        } else if (protocolFundingSource.getFundingSourceTypeCode().intValue() != getFundingSourceTypeCode().intValue()) {
            isEqual=false;
        }
        
        
        return isEqual;
    }
}