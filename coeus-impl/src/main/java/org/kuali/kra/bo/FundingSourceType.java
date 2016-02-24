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
package org.kuali.kra.bo;


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class FundingSourceType extends KcPersistableBusinessObjectBase {

    public static final String SPONSOR = "1";

    public static final String UNIT = "2";

    public static final String OTHER = "3";

    public static final String PROPOSAL_DEVELOPMENT = "4";

    public static final String INSTITUTIONAL_PROPOSAL = "5";

    public static final String AWARD = "6";

    private String fundingSourceTypeCode;

    private String description;

    private boolean fundingSourceTypeFlag;

    public FundingSourceType() {
    }

    public String getFundingSourceTypeCode() {
        return fundingSourceTypeCode;
    }

    public void setFundingSourceTypeCode(String fundingSourceTypeCode) {
        this.fundingSourceTypeCode = fundingSourceTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getFundingSourceTypeFlag() {
        return fundingSourceTypeFlag;
    }

    public void setFundingSourceTypeFlag(boolean fundingSourceTypeFlag) {
        this.fundingSourceTypeFlag = fundingSourceTypeFlag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((fundingSourceTypeCode == null) ? 0 : fundingSourceTypeCode.hashCode());
        result = prime * result + (fundingSourceTypeFlag ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FundingSourceType other = (FundingSourceType) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equalsIgnoreCase(other.description)) {
            return false;
        }
        if (fundingSourceTypeCode == null) {
            if (other.fundingSourceTypeCode != null) {
                return false;
            }
        } else if (!fundingSourceTypeCode.equals(other.fundingSourceTypeCode)) {
            return false;
        }
        if (fundingSourceTypeFlag != other.fundingSourceTypeFlag) {
            return false;
        }
        return true;
    }
}
