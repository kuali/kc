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
package org.kuali.coeus.award.finance;

import java.sql.Date;
import java.util.List;

import org.kuali.coeus.award.api.AwardStatusDto;
import org.kuali.coeus.common.api.unit.UnitDto;
import org.kuali.coeus.common.framework.sponsor.SponsorDto;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.summary.InvestigatorDto;
import org.kuali.kra.award.customdata.AwardCustomDataDto;
import org.kuali.kra.award.home.AwardAmountInfoDto;
import org.kuali.kra.award.home.AwardTypeDto;

import com.codiform.moo.annotation.CollectionProperty;
import com.codiform.moo.annotation.Optionality;
import com.codiform.moo.annotation.Property;

public class AccountInformationDto {

	protected String awardNumber;
	@Property(translate = true)
	protected UnitDto leadUnit;
	protected String title;
	@Property(translate = true)
	protected SponsorDto sponsor;
	@Property(translate = true)
	protected SponsorDto primeSponsor;
	@Property(translate = true)
	private InvestigatorDto principalInvestigator;
	@CollectionProperty(source = "centralAdminContacts", itemClass=AwardUnitContactDto.class)
	protected List<AwardUnitContactDto> unitAdministrators;
	@Property(source = "beginDate")
	protected Date projectStartDate;	
	@Property(translate = true)
	protected AwardStatusDto awardStatus;
	@Property(translate = true)
	protected AwardTypeDto awardType;
	protected Integer accountTypeCode;
	protected String sponsorAwardNumber;
	@CollectionProperty(source = "awardFandaRate", itemClass=AwardFandARateDto.class)
	protected List<AwardFandARateDto> fAndARates;
    protected String cfdaNumber;
    @Property(source = "awardEffectiveDate")
    protected Date effectiveDate;
    @Property(source = "projectEndDate")
    protected Date expirationDate;
    @Property(source = "title")
    protected String purposeText;
    @Property(source = "awardNumber")
    protected String expenseGuidelineText;
    @Property(source = "principalInvestigator.personId")
    protected String principalId;
    @Property(source = "unitNumber")
    protected String unit;
    
	@Property(source = "awardAmountInfoForDisplay.finalExpirationDate")
	protected Date projectEndDate;
    @Property(source = "awardAmountInfoForDisplay.anticipatedTotalAmount")
    private ScaleTwoDecimal anticipatedTotalAmount;
    @Property(source = "awardAmountInfoForDisplay.amountObligatedToDate")
    private ScaleTwoDecimal amountObligatedToDate;
    @Property(source = "awardAmountInfoForDisplay.anticipatedTotalDirect")
    private ScaleTwoDecimal anticipatedTotalDirect;
    @Property(source = "awardAmountInfoForDisplay.anticipatedTotalIndirect")
    private ScaleTwoDecimal anticipatedTotalIndirect;
    @Property(source = "awardAmountInfoForDisplay.obligatedTotalDirect")
    private ScaleTwoDecimal obligatedTotalDirect;
    @Property(source = "awardAmountInfoForDisplay.obligatedTotalIndirect")
    private ScaleTwoDecimal obligatedTotalIndirect;
    
    @CollectionProperty(source = "awardCustomDataList", itemClass=AwardCustomDataDto.class)
    private List<AwardCustomDataDto> customData;
    
    @CollectionProperty(source = "awardAmountInfos", itemClass=AwardAmountInfoDto.class)
    private List<AwardAmountInfoDto> transactions;
	
	@Property(optionality=Optionality.OPTIONAL)
    protected String accountName;
	@Property(optionality=Optionality.OPTIONAL)
    protected String accountNumber;
	@Property(optionality=Optionality.OPTIONAL)
    protected String adminContactAddressCityName;
	@Property(optionality=Optionality.OPTIONAL)
    protected String adminContactAddressStateCode;
	@Property(optionality=Optionality.OPTIONAL)
    protected String adminContactAddressStreetAddress;
	@Property(optionality=Optionality.OPTIONAL)
    protected String adminContactAddressZipCode;    
    @Property(optionality=Optionality.OPTIONAL)
    protected String defaultAddressCityName;
    @Property(optionality=Optionality.OPTIONAL)
    protected String defaultAddressStateCode;
    @Property(optionality=Optionality.OPTIONAL)
    protected String defaultAddressStreetAddress;
    @Property(optionality=Optionality.OPTIONAL)
    protected String defaultAddressZipCode;
    @Property(optionality=Optionality.OPTIONAL)
    protected String higherEdFunctionCode;
    @Property(optionality=Optionality.OPTIONAL)
    protected String incomeGuidelineText;
    @Property(optionality=Optionality.OPTIONAL)
    protected String indirectCostRate;
    @Property(optionality=Optionality.OPTIONAL)
    protected String indirectCostTypeCode;
    @Property(optionality=Optionality.OPTIONAL)
    protected boolean offCampusIndicator;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String value) {
        this.accountName = value;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    public String getAdminContactAddressCityName() {
        return adminContactAddressCityName;
    }

    public void setAdminContactAddressCityName(String value) {
        this.adminContactAddressCityName = value;
    }

    public String getAdminContactAddressStateCode() {
        return adminContactAddressStateCode;
    }

    public void setAdminContactAddressStateCode(String value) {
        this.adminContactAddressStateCode = value;
    }

    public String getAdminContactAddressStreetAddress() {
        return adminContactAddressStreetAddress;
    }

    public void setAdminContactAddressStreetAddress(String value) {
        this.adminContactAddressStreetAddress = value;
    }

    public String getAdminContactAddressZipCode() {
        return adminContactAddressZipCode;
    }

    public void setAdminContactAddressZipCode(String value) {
        this.adminContactAddressZipCode = value;
    }

    public String getCfdaNumber() {
        return cfdaNumber;
    }

    public void setCfdaNumber(String value) {
        this.cfdaNumber = value;
    }

    public String getDefaultAddressCityName() {
        return defaultAddressCityName;
    }

    public void setDefaultAddressCityName(String value) {
        this.defaultAddressCityName = value;
    }

    public String getDefaultAddressStateCode() {
        return defaultAddressStateCode;
    }

    public void setDefaultAddressStateCode(String value) {
        this.defaultAddressStateCode = value;
    }

    public String getDefaultAddressStreetAddress() {
        return defaultAddressStreetAddress;
    }

    public void setDefaultAddressStreetAddress(String value) {
        this.defaultAddressStreetAddress = value;
    }

    public String getDefaultAddressZipCode() {
        return defaultAddressZipCode;
    }

    public void setDefaultAddressZipCode(String value) {
        this.defaultAddressZipCode = value;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date value) {
        this.effectiveDate = value;
    }

    public String getExpenseGuidelineText() {
        return expenseGuidelineText;
    }

    public void setExpenseGuidelineText(String value) {
        this.expenseGuidelineText = value;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date value) {
        this.expirationDate = value;
    }

    public String getHigherEdFunctionCode() {
        return higherEdFunctionCode;
    }

    public void setHigherEdFunctionCode(String value) {
        this.higherEdFunctionCode = value;
    }

    public String getIncomeGuidelineText() {
        return incomeGuidelineText;
    }

    public void setIncomeGuidelineText(String value) {
        this.incomeGuidelineText = value;
    }

    public String getIndirectCostRate() {
        return indirectCostRate;
    }

    public void setIndirectCostRate(String value) {
        this.indirectCostRate = value;
    }

    public String getIndirectCostTypeCode() {
        return indirectCostTypeCode;
    }

    public void setIndirectCostTypeCode(String value) {
        this.indirectCostTypeCode = value;
    }

    public boolean isOffCampusIndicator() {
        return offCampusIndicator;
    }

    public void setOffCampusIndicator(boolean value) {
        this.offCampusIndicator = value;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String value) {
        this.principalId = value;
    }

    public String getPurposeText() {
        return purposeText;
    }

    public void setPurposeText(String value) {
        this.purposeText = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String value) {
        this.unit = value;
    }

	public String getAwardNumber() {
		return awardNumber;
	}

	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}

	public UnitDto getLeadUnit() {
		return leadUnit;
	}

	public void setLeadUnit(UnitDto leadUnit) {
		this.leadUnit = leadUnit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SponsorDto getSponsor() {
		return sponsor;
	}

	public void setSponsor(SponsorDto sponsor) {
		this.sponsor = sponsor;
	}

	public SponsorDto getPrimeSponsor() {
		return primeSponsor;
	}

	public void setPrimeSponsor(SponsorDto primeSponsor) {
		this.primeSponsor = primeSponsor;
	}

	public InvestigatorDto getPrincipalInvestigator() {
		return principalInvestigator;
	}

	public void setPrincipalInvestigator(InvestigatorDto principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}

	public List<AwardUnitContactDto> getUnitAdministrators() {
		return unitAdministrators;
	}

	public void setUnitAdministrators(List<AwardUnitContactDto> unitAdministrators) {
		this.unitAdministrators = unitAdministrators;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Date getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public AwardStatusDto getAwardStatus() {
		return awardStatus;
	}

	public void setAwardStatus(AwardStatusDto awardStatus) {
		this.awardStatus = awardStatus;
	}

	public List<AwardFandARateDto> getfAndARates() {
		return fAndARates;
	}

	public void setfAndARates(List<AwardFandARateDto> fAndARates) {
		this.fAndARates = fAndARates;
	}

	public String getSponsorAwardNumber() {
		return sponsorAwardNumber;
	}

	public void setSponsorAwardNumber(String sponsorAwardNumber) {
		this.sponsorAwardNumber = sponsorAwardNumber;
	}

	public Integer getAccountTypeCode() {
		return accountTypeCode;
	}

	public void setAccountTypeCode(Integer accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	public ScaleTwoDecimal getAnticipatedTotalAmount() {
		return anticipatedTotalAmount;
	}

	public void setAnticipatedTotalAmount(ScaleTwoDecimal anticipatedTotalAmount) {
		this.anticipatedTotalAmount = anticipatedTotalAmount;
	}

	public ScaleTwoDecimal getAmountObligatedToDate() {
		return amountObligatedToDate;
	}

	public void setAmountObligatedToDate(ScaleTwoDecimal amountObligatedToDate) {
		this.amountObligatedToDate = amountObligatedToDate;
	}

	public ScaleTwoDecimal getAnticipatedTotalDirect() {
		return anticipatedTotalDirect;
	}

	public void setAnticipatedTotalDirect(ScaleTwoDecimal anticipatedTotalDirect) {
		this.anticipatedTotalDirect = anticipatedTotalDirect;
	}

	public ScaleTwoDecimal getAnticipatedTotalIndirect() {
		return anticipatedTotalIndirect;
	}

	public void setAnticipatedTotalIndirect(ScaleTwoDecimal anticipatedTotalIndirect) {
		this.anticipatedTotalIndirect = anticipatedTotalIndirect;
	}

	public ScaleTwoDecimal getObligatedTotalDirect() {
		return obligatedTotalDirect;
	}

	public void setObligatedTotalDirect(ScaleTwoDecimal obligatedTotalDirect) {
		this.obligatedTotalDirect = obligatedTotalDirect;
	}

	public ScaleTwoDecimal getObligatedTotalIndirect() {
		return obligatedTotalIndirect;
	}

	public void setObligatedTotalIndirect(ScaleTwoDecimal obligatedTotalIndirect) {
		this.obligatedTotalIndirect = obligatedTotalIndirect;
	}

	public List<AwardCustomDataDto> getCustomData() {
		return customData;
	}

	public void setCustomData(List<AwardCustomDataDto> customData) {
		this.customData = customData;
	}

	public List<AwardAmountInfoDto> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<AwardAmountInfoDto> transactions) {
		this.transactions = transactions;
	}

	public AwardTypeDto getAwardType() {
		return awardType;
	}

	public void setAwardType(AwardTypeDto awardType) {
		this.awardType = awardType;
	}

}

