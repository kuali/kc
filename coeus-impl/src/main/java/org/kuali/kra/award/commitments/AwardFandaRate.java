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
package org.kuali.kra.award.commitments;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class represents the AwardFandaRate Business Object.
 */
public class AwardFandaRate extends AwardAssociate {


    private static final long serialVersionUID = 1L;

    private Long awardFandaRateId;

    private ScaleTwoDecimal applicableFandaRate;

    private String fandaRateTypeCode;

    private String fiscalYear;

    private String onCampusFlag;

    private ScaleTwoDecimal underrecoveryOfIndirectCost;

    private String sourceAccount;

    private String destinationAccount;

    private Date startDate;

    private Date endDate;

    public Long getAwardFandaRateId() {
        return awardFandaRateId;
    }

    public void setAwardFandaRateId(Long awardFandaRateId) {
        this.awardFandaRateId = awardFandaRateId;
    }

    public ScaleTwoDecimal getApplicableFandaRate() {
        return applicableFandaRate;
    }

    @Override
    public void resetPersistenceState() {
        this.awardFandaRateId = null;
    }

    public void setApplicableFandaRate(ScaleTwoDecimal applicableFandaRate) {
        this.applicableFandaRate = applicableFandaRate;
    }

    public String getFandaRateTypeCode() {
        return fandaRateTypeCode;
    }

    public void setFandaRateTypeCode(String fandaRateTypeCode) {
        this.fandaRateTypeCode = fandaRateTypeCode;
    }

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public String getOnCampusFlag() {
        return onCampusFlag;
    }

    public Boolean getOnOffCampusFlag() {
        return onCampusFlag.equals("N");
    }

    public void setOnCampusFlag(String onCampusFlag) {
        this.onCampusFlag = onCampusFlag;
    }

    public ScaleTwoDecimal getUnderrecoveryOfIndirectCost() {
        return underrecoveryOfIndirectCost;
    }
    
    public long getUnderrecoveryOfIndirectCostLongValue() {
        return underrecoveryOfIndirectCost != null ? underrecoveryOfIndirectCost.longValue() : 0;
    }

    public void setUnderrecoveryOfIndirectCost(ScaleTwoDecimal underrecoveryOfIndirectCost) {
        this.underrecoveryOfIndirectCost = underrecoveryOfIndirectCost;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((applicableFandaRate == null) ? 0 : applicableFandaRate.hashCode());
        result = prime * result + ((destinationAccount == null) ? 0 : destinationAccount.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((fandaRateTypeCode == null) ? 0 : fandaRateTypeCode.hashCode());
        result = prime * result + ((fiscalYear == null) ? 0 : fiscalYear.hashCode());
        result = prime * result + ((onCampusFlag == null) ? 0 : onCampusFlag.hashCode());
        result = prime * result + ((sourceAccount == null) ? 0 : sourceAccount.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((underrecoveryOfIndirectCost == null) ? 0 : underrecoveryOfIndirectCost.hashCode());
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
        if (!(obj instanceof AwardFandaRate)) {
            return false;
        }
        return equals((AwardFandaRate) obj);
    }

    /**
     * 
     * Convenience method to check equality of another AwardFandaRate
     * @param awardFandaRate
     * @return
     */
    public boolean equals(AwardFandaRate awardFandaRate) {
        if (!super.equals(awardFandaRate)) {
            return false;
        }
        if (applicableFandaRate == null) {
            if (awardFandaRate.applicableFandaRate != null) {
                return false;
            }
        } else if (!applicableFandaRate.equals(awardFandaRate.applicableFandaRate)) {
            return false;
        }
        if (destinationAccount == null) {
            if (awardFandaRate.destinationAccount != null) {
                return false;
            }
        } else if (!destinationAccount.equals(awardFandaRate.destinationAccount)) {
            return false;
        }
        if (endDate == null) {
            if (awardFandaRate.endDate != null) {
                return false;
            }
        } else if (!endDate.equals(awardFandaRate.endDate)) {
            return false;
        }
        if (fandaRateTypeCode == null) {
            if (awardFandaRate.fandaRateTypeCode != null) {
                return false;
            }
        } else if (!fandaRateTypeCode.equals(awardFandaRate.fandaRateTypeCode)) {
            return false;
        }
        if (fiscalYear == null) {
            if (awardFandaRate.fiscalYear != null) {
                return false;
            }
        } else if (!fiscalYear.equals(awardFandaRate.fiscalYear)) {
            return false;
        }
        if (onCampusFlag == null) {
            if (awardFandaRate.onCampusFlag != null) {
                return false;
            }
        } else if (!onCampusFlag.equals(awardFandaRate.onCampusFlag)) {
            return false;
        }
        if (sourceAccount == null) {
            if (awardFandaRate.sourceAccount != null) {
                return false;
            }
        } else if (!sourceAccount.equals(awardFandaRate.sourceAccount)) {
            return false;
        }
        if (startDate == null) {
            if (awardFandaRate.startDate != null) {
                return false;
            }
        } else if (!startDate.equals(awardFandaRate.startDate)) {
            return false;
        }
        if (underrecoveryOfIndirectCost == null) {
            if (awardFandaRate.underrecoveryOfIndirectCost != null) {
                return false;
            }
        } else if (!underrecoveryOfIndirectCost.equals(awardFandaRate.underrecoveryOfIndirectCost)) {
            return false;
        }
        return true;
    }

    public boolean equals(BudgetRate budgetRate) {
        if (applicableFandaRate == null) {
            if (budgetRate.getApplicableRate() != null) {
                return false;
            }
        } else if (!applicableFandaRate.equals(budgetRate.getApplicableRate())) {
            return false;
        }
        if (fandaRateTypeCode == null) {
            if (budgetRate.getRateTypeCode() != null) {
                return false;
            }
        } else if (!fandaRateTypeCode.toString().equals(budgetRate.getRateTypeCode())) {
            return false;
        }
        if (fiscalYear == null) {
            if (budgetRate.getFiscalYear() != null) {
                return false;
            }
        } else if (!fiscalYear.equals(budgetRate.getFiscalYear())) {
            return false;
        }
        if (onCampusFlag == null) {
            if (budgetRate.getOnOffCampusFlag() != null) {
                return false;
            }
        } else if (!getOnOffCampusFlag().equals(budgetRate.getOnOffCampusFlag())) {
            return false;
        }
        if (startDate == null) {
            if (budgetRate.getStartDate() != null) {
                return false;
            }
        } else if (!startDate.equals(budgetRate.getStartDate())) {
            return false;
        }
        return true;
    }

    /**
     * Gets the fandaRateType attribute. 
     * @return Returns the fandaRateType.
     */
    public RateType getFandaRateType() {
        BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        ParameterService parameterService = KcServiceLocator.getService(ParameterService.class);
        String rateClassCode = parameterService.getParameterValueAsString(AwardBudgetDocument.class, Constants.AWARD_BUDGET_DEFAULT_FNA_RATE_CLASS_CODE);
        Map<String, String> qMap = new HashMap<String, String>();
        qMap.put("rateClassCode", rateClassCode);
        qMap.put("rateTypeCode", getFandaRateTypeCode());
        return (RateType) businessObjectService.findByPrimaryKey(RateType.class, qMap);
    }
}
