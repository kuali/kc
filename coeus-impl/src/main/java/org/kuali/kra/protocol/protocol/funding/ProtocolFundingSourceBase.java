/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.protocol.funding;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.protocol.ProtocolAssociateBase;

/**
 * This class provides fundamental elements of protocol funding source data for Protocols.
 */
public abstract class ProtocolFundingSourceBase extends ProtocolAssociateBase {

    private static final long serialVersionUID = 2732312377082408995L;

    private Long protocolFundingSourceId;

    private String fundingSourceTypeCode;

    private String fundingSourceNumber;

    private FundingSourceType fundingSourceType;

    private String fundingSourceName;

    private String fundingSourceTitle;

    /**
     * Empty constructor for database.
     */
    public ProtocolFundingSourceBase() {
    }

    /**
     * Constructs a ProtocolFundingSourceBase.
     * @param fundingSourceNumber The user-readable number of the funding source (often the same as fundingSource)
     * @param fundingSourceTypeCode The type code of the funding source
     * @param fundingSourceName The name of the funding source
     * @param fundingSourceTitle The title of the funding source
     */
    public ProtocolFundingSourceBase(String fundingSourceNumber, String fundingSourceTypeCode, String fundingSourceName, String fundingSourceTitle) {
        setFundingSourceNumber(fundingSourceNumber);
        setFundingSourceTypeCode(fundingSourceTypeCode);
        setFundingSourceName(fundingSourceName);
        setFundingSourceTitle(fundingSourceTitle);
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

    public String getFundingSourceTypeCode() {
        return fundingSourceTypeCode;
    }

    public void setFundingSourceTypeCode(String fundingSourceTypeCode) {
        this.fundingSourceTypeCode = fundingSourceTypeCode;
        // When the type code changes, the corresponding  
        //  field must also be updated.  A refresh will  
        // cause a read from the database. By  
        // the magic of OJB, the data member is automatically updated.  
        if (StringUtils.isNotBlank(fundingSourceTypeCode)) {
            refreshReferenceObject("fundingSourceType");
        }
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
        return getProtocolFundingSourceService().isLookupable(fundingSourceTypeCode);
    }

    protected ProtocolFundingSourceService getProtocolFundingSourceService() {
        return KcServiceLocator.getService(getProtocolFundingSourceServiceClassHook());
    }

    protected abstract Class<? extends ProtocolFundingSourceService> getProtocolFundingSourceServiceClassHook();

    @Override
    public void resetPersistenceState() {
        this.setProtocolFundingSourceId(null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((fundingSourceNumber == null) ? 0 : fundingSourceNumber.hashCode());
        result = prime * result + ((fundingSourceTypeCode == null) ? 0 : fundingSourceTypeCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProtocolFundingSourceBase other = (ProtocolFundingSourceBase) obj;
        if (fundingSourceNumber == null) {
            if (other.fundingSourceNumber != null) {
                return false;
            }
        } else if (!fundingSourceNumber.equalsIgnoreCase(other.fundingSourceNumber)) {
            return false;
        }
        if (fundingSourceTypeCode == null) {
            if (other.fundingSourceTypeCode != null) {
                return false;
            }
        } else if (!fundingSourceTypeCode.equals(other.fundingSourceTypeCode)) {
            return false;
        }
        return true;
    }
    
    public boolean isSponsorFunding() {
        return (StringUtils.equals(fundingSourceTypeCode, FundingSourceType.SPONSOR));
    }
}
