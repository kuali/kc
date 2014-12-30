package org.kuali.kra.award.cgb;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.math.BigDecimal;
import java.util.Date;

public class AwardCgb extends AwardAssociate {

	private static final long serialVersionUID = 8904526837856260707L;
	private Long awardId;
    private boolean additionalFormsRequired;
    private boolean autoApproveInvoice;
    private boolean stopWork;
    private BigDecimal minInvoiceAmount;
    private String invoicingOption;
    private String dunningCampaignId;
    private Date lastBilledDate;
    private Date previousLastBilledDate;
    private boolean finalBill;
    private ScaleTwoDecimal amountToDraw;
    private boolean letterOfCreditReviewIndicator;
    private String locCreationType;
    private String invoiceDocumentStatus;
    private boolean suspendInvoicing;
    
    public AwardCgb() { }
    
    public AwardCgb(Award award) {
    	setAward(award);
    }
    
	@Override
	public void setAward(Award award) {
		super.setAward(award);
		this.setAwardId(award.getAwardId());
	}
	
	@Override
	public void resetPersistenceState() {
		awardId = null;
	}


	public Long getAwardId() {
		return awardId;
	}

	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}

	public boolean isAdditionalFormsRequired() {
		return additionalFormsRequired;
	}

	public void setAdditionalFormsRequired(boolean additionalFormsRequired) {
		this.additionalFormsRequired = additionalFormsRequired;
	}

	public boolean isAutoApproveInvoice() {
		return autoApproveInvoice;
	}

	public void setAutoApproveInvoice(boolean autoApproveInvoice) {
		this.autoApproveInvoice = autoApproveInvoice;
	}

	public boolean isStopWork() {
		return stopWork;
	}

	public void setStopWork(boolean stopWork) {
		this.stopWork = stopWork;
	}

	public BigDecimal getMinInvoiceAmount() {
		return minInvoiceAmount;
	}

	public void setMinInvoiceAmount(BigDecimal minInvoiceAmount) {
		this.minInvoiceAmount = minInvoiceAmount;
	}

	public String getInvoicingOption() {
		return invoicingOption;
	}

	public void setInvoicingOption(String invoicingOption) {
		this.invoicingOption = invoicingOption;
	}

	public String getDunningCampaignId() {
		return dunningCampaignId;
	}

	public void setDunningCampaignId(String dunningCampaignId) {
		this.dunningCampaignId = dunningCampaignId;
	}

	public Date getLastBilledDate() {
		return lastBilledDate;
	}

	public void setLastBilledDate(Date lastBilledDate) {
		this.lastBilledDate = lastBilledDate;
	}

	public boolean isFinalBill() {
		return finalBill;
	}

	public void setFinalBill(boolean finalBill) {
		this.finalBill = finalBill;
	}

	public Date getPreviousLastBilledDate() {
		return previousLastBilledDate;
	}

	public void setPreviousLastBilledDate(Date previousLastBilledDate) {
		this.previousLastBilledDate = previousLastBilledDate;
	}

	public ScaleTwoDecimal getAmountToDraw() {
		return amountToDraw;
	}

	public void setAmountToDraw(ScaleTwoDecimal amountToDraw) {
		this.amountToDraw = amountToDraw;
	}

	public boolean isLetterOfCreditReviewIndicator() {
		return letterOfCreditReviewIndicator;
	}

	public void setLetterOfCreditReviewIndicator(
			boolean letterOfCreditReviewIndicator) {
		this.letterOfCreditReviewIndicator = letterOfCreditReviewIndicator;
	}

	public String getInvoiceDocumentStatus() {
		return invoiceDocumentStatus;
	}

	public void setInvoiceDocumentStatus(String invoiceDocumentStatus) {
		this.invoiceDocumentStatus = invoiceDocumentStatus;
	}

	public String getLocCreationType() {
		return locCreationType;
	}

	public void setLocCreationType(String locCreationType) {
		this.locCreationType = locCreationType;
	}

	public boolean isSuspendInvoicing() {
		return suspendInvoicing;
	}

	public void setSuspendInvoicing(boolean suspendInvoicing) {
		this.suspendInvoicing = suspendInvoicing;
	}
}
