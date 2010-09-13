/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.protocol.funding;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.ProtocolAssociate;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceServiceImpl.FundingSourceLookup;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;

/**
 * 
 * This class provides fundamental elements of protocol funding source data for Protocols.
 */
public class ProtocolFundingSource extends ProtocolAssociate {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6137366673402413087L;
    
    private Long protocolFundingSourceId;
    private Integer fundingSourceTypeCode;
    private String fundingSource;
    private String fundingSourceNumber;
    private FundingSourceType fundingSourceType;
    private String fundingSourceName;
    private String fundingSourceTitle;
    
    private Unit fundingUnit;
    private Sponsor fundingSponsor;
    private DevelopmentProposal fundingDevelopmentProposal;
    private InstitutionalProposal fundingInstitutionalProposal;
    private Award fundingAward;
    
    /**
     * Empty constructor for database.
     */
    public ProtocolFundingSource() {
    }
    
    /**
     * Constructs a ProtocolFundingSource.
     * @param fundingSource The foreign key ID to the funding source
     * @param fundingSourceType The type of the funding source
     * @param fundingSourceName The name of the funding source
     * @param fundingSourceTitle The title of the funding source
     */
    public ProtocolFundingSource(String fundingSource, FundingSourceType fundingSourceType, String fundingSourceName, String fundingSourceTitle) {
        this(fundingSource, fundingSource, fundingSourceType, fundingSourceName, fundingSourceTitle);
    }
    
    /**
     * Constructs a ProtocolFundingSource.
     * @param fundingSource The foreign key ID to the funding source
     * @param fundingSourceNumber The user-readable number of the funding source (often the same as fundingSource)
     * @param fundingSourceType The type of the funding source
     * @param fundingSourceName The name of the funding source
     * @param fundingSourceTitle The title of the funding source
     */
    public ProtocolFundingSource(String fundingSource, String fundingSourceNumber, FundingSourceType fundingSourceType, String fundingSourceName, 
            String fundingSourceTitle) {
        this.fundingSource = fundingSource;
        this.fundingSourceNumber = fundingSourceNumber;
        this.fundingSourceType = fundingSourceType;
        this.fundingSourceName = fundingSourceName;
        this.fundingSourceTitle = fundingSourceTitle;
        if  (fundingSourceType != null) {
            this.fundingSourceTypeCode = fundingSourceType.getFundingSourceTypeCode();
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

    public DevelopmentProposal getFundingDevelopmentProposal() {
        if (FundingSourceLookup.PROPOSAL_DEVELOPMENT.getTypeCode() == getFundingSourceType().getFundingSourceTypeCode() 
                && StringUtils.isNotBlank(getFundingSource())) {
            this.refreshReferenceObject("fundingDevelopmentProposal");
        }
        return fundingDevelopmentProposal;
    }

    public void setFundingDevelopmentProposal(DevelopmentProposal fundingDevelopmentProposal) {
        this.fundingDevelopmentProposal = fundingDevelopmentProposal;
    }
    
    public InstitutionalProposal getFundingInstitutionalProposal() {
        if (FundingSourceLookup.INSTITUTIONAL_PROPOSAL.getTypeCode() == getFundingSourceType().getFundingSourceTypeCode() 
                && StringUtils.isNotBlank(getFundingSource())) {
            this.refreshReferenceObject("fundingInstitutionalProposal");
        }
        return fundingInstitutionalProposal;
    }

    public void setFundingInstitutionalProposal(InstitutionalProposal fundingInstitutionalProposal) {
        this.fundingInstitutionalProposal = fundingInstitutionalProposal;
    }

    public Award getFundingAward() {
        if (FundingSourceLookup.AWARD.getTypeCode() == getFundingSourceType().getFundingSourceTypeCode()         
                && StringUtils.isNotBlank(getFundingSource())) {
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
    
    public String getFundingSourceNumber() {
        return fundingSourceNumber;
    }
    
    public void setFundingSourceNumber(String fundingSourceNumber) {
        this.fundingSourceNumber = fundingSourceNumber;
    }

    public FundingSourceType getFundingSourceType() {
        return fundingSourceType;
    }

    public void setFundingSourceType(FundingSourceType fundingSourceType) {
        this.fundingSourceType = fundingSourceType;
    }
    
    public boolean isFundingSourceLookupable() {
        return getProtocolFundingSourceService().isLookupable(Integer.toString(this.fundingSourceTypeCode));
    }
    
    public boolean isViewableFundingSource() {
        return getProtocolFundingSourceService().isViewable(this.fundingSourceTypeCode);        
    }

    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
        hashMap.put("protocolFundingSourceId", getProtocolFundingSourceId());
        hashMap.put("fundingSourceTypeCode", getFundingSourceTypeCode());
        hashMap.put("fundingSource", getFundingSource());
        hashMap.put("fundingSourceNumber", getFundingSourceNumber());
        hashMap.put("fundingSourceName", getFundingSourceName());
        hashMap.put("fundingSourceTitle", getFundingSourceTitle());
        return hashMap;
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
        } else if (!protocolFundingSource.getFundingSourceTypeCode().equals(getFundingSourceTypeCode())) {
            isEqual=false;
        }
        return isEqual;
    }
        
    @Override
    public int hashCode() {
          final int PRIME = 31;
          int result = 1;
          result = PRIME * result + ((this.getFundingSource() == null) ? 0 : this.getFundingSource().hashCode());
          result = PRIME * result + ((this.getFundingSourceTypeCode() == null) ? 0 : this.getFundingSourceTypeCode().hashCode());
          return result;
    }
    
    protected ProtocolFundingSourceService getProtocolFundingSourceService() {
        return KraServiceLocator.getService(ProtocolFundingSourceService.class);
    }

    /** {@inheritDoc} */
    public void resetPersistenceState() {
        this.setProtocolFundingSourceId(null);
    }
}
