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
package org.kuali.kra.award.home;

import java.sql.Date;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 * AwardAmountInfo BO
 * 
 * @author Kuali Coeus development team (kc.dev@kuali.org)
 */
public class AwardAmountInfo extends AwardAssociate {

    private static final long serialVersionUID = 1L;

    private Long awardAmountInfoId;

    private Long transactionId;

    private String timeAndMoneyDocumentNumber;

    private KualiDecimal anticipatedTotalAmount;

    private KualiDecimal antDistributableAmount;

    private Date finalExpirationDate;

    private Date currentFundEffectiveDate;

    private KualiDecimal amountObligatedToDate;

    private KualiDecimal obliDistributableAmount;

    private Date obligationExpirationDate;

    private boolean entryType;

    private boolean eomProcessFlag;

    private KualiDecimal anticipatedChange;

    private KualiDecimal obligatedChange;

    private KualiDecimal obligatedChangeDirect;

    private KualiDecimal obligatedChangeIndirect;

    private KualiDecimal anticipatedChangeDirect;

    private KualiDecimal anticipatedChangeIndirect;

    private KualiDecimal anticipatedTotalDirect;

    private KualiDecimal anticipatedTotalIndirect;

    private KualiDecimal obligatedTotalDirect;

    private KualiDecimal obligatedTotalIndirect;

    private Integer transactionDetailItemsLength;

    private Integer originatingAwardVersion;

    // private AwardBudgetInfo awardBudgetInfo;   
    // private AwardAmtFnaDistribution awardAmtFnaDistribution;   
    public AwardAmountInfo() {
        setAnticipatedTotalDirect(new KualiDecimal(0.00));
        setAnticipatedTotalIndirect(new KualiDecimal(0.00));
        setObligatedTotalDirect(new KualiDecimal(0.00));
        setObligatedTotalIndirect(new KualiDecimal(0.00));
        setAnticipatedTotalAmount(new KualiDecimal(0.00));
        setAmountObligatedToDate(new KualiDecimal(0.00));
        setObliDistributableAmount(new KualiDecimal(0.00));
        setAntDistributableAmount(new KualiDecimal(0.00));
    }

    public Long getAwardAmountInfoId() {
        return awardAmountInfoId;
    }

    public void setAwardAmountInfoId(Long awardAmountInfoId) {
        this.awardAmountInfoId = awardAmountInfoId;
    }

    public KualiDecimal getAnticipatedTotalAmount() {
        return anticipatedTotalAmount;
    }

    public void setAnticipatedTotalAmount(KualiDecimal anticipatedTotalAmount) {
        this.anticipatedTotalAmount = anticipatedTotalAmount;
        if (!(getAward() == null)) {
            if (getAward().getAwardAmountInfos().size() == 1 && getAward().getSequenceNumber() == 1) {
                setAntDistributableAmount(anticipatedTotalAmount);
            }
        }
    }

    public KualiDecimal getAntDistributableAmount() {
        return antDistributableAmount;
    }

    public void setAntDistributableAmount(KualiDecimal antDistributableAmount) {
        this.antDistributableAmount = antDistributableAmount;
    }

    public Date getFinalExpirationDate() {
        return finalExpirationDate;
    }

    public void setFinalExpirationDate(Date finalExpirationDate) {
        this.finalExpirationDate = finalExpirationDate;
    }

    public Date getCurrentFundEffectiveDate() {
        return currentFundEffectiveDate;
    }

    public void setCurrentFundEffectiveDate(Date currentFundEffectiveDate) {
        this.currentFundEffectiveDate = currentFundEffectiveDate;
    }

    public KualiDecimal getAmountObligatedToDate() {
        return amountObligatedToDate;
    }

    public void setAmountObligatedToDate(KualiDecimal amountObligatedToDate) {
        this.amountObligatedToDate = amountObligatedToDate;
        if (!(getAward() == null)) {
            if (getAward().getAwardAmountInfos().size() == 1 && getAward().getSequenceNumber() == 1) {
                setObliDistributableAmount(amountObligatedToDate);
            }
        }
    }

    public KualiDecimal getObliDistributableAmount() {
        return obliDistributableAmount;
    }

    public void setObliDistributableAmount(KualiDecimal obliDistributableAmount) {
        this.obliDistributableAmount = obliDistributableAmount;
    }

    public Date getObligationExpirationDate() {
        return obligationExpirationDate;
    }

    public void setObligationExpirationDate(Date obligationExpirationDate) {
        this.obligationExpirationDate = obligationExpirationDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public boolean getEntryType() {
        return entryType;
    }

    public void setEntryType(boolean entryType) {
        this.entryType = entryType;
    }

    public boolean getEomProcessFlag() {
        return eomProcessFlag;
    }

    public void setEomProcessFlag(boolean eomProcessFlag) {
        this.eomProcessFlag = eomProcessFlag;
    }

    public KualiDecimal getAnticipatedChange() {
        return anticipatedChange;
    }

    public void setAnticipatedChange(KualiDecimal anticipatedChange) {
        this.anticipatedChange = anticipatedChange;
    }

    public KualiDecimal getObligatedChange() {
        return obligatedChange;
    }

    public void setObligatedChange(KualiDecimal obligatedChange) {
        this.obligatedChange = obligatedChange;
    }

    public KualiDecimal getObligatedChangeDirect() {
        return obligatedChangeDirect;
    }

    public void setObligatedChangeDirect(KualiDecimal obligatedChangeDirect) {
        this.obligatedChangeDirect = obligatedChangeDirect;
    }

    public KualiDecimal getObligatedChangeIndirect() {
        return obligatedChangeIndirect;
    }

    public void setObligatedChangeIndirect(KualiDecimal obligatedChangeIndirect) {
        this.obligatedChangeIndirect = obligatedChangeIndirect;
    }

    public KualiDecimal getAnticipatedChangeDirect() {
        return anticipatedChangeDirect;
    }

    public void setAnticipatedChangeDirect(KualiDecimal anticipatedChangeDirect) {
        this.anticipatedChangeDirect = anticipatedChangeDirect;
    }

    public KualiDecimal getAnticipatedChangeIndirect() {
        return anticipatedChangeIndirect;
    }

    public void setAnticipatedChangeIndirect(KualiDecimal anticipatedChangeIndirect) {
        this.anticipatedChangeIndirect = anticipatedChangeIndirect;
    }

    public KualiDecimal getAnticipatedTotalDirect() {
        return anticipatedTotalDirect;
    }

    public void setAnticipatedTotalDirect(KualiDecimal anticipatedTotalDirect) {
        this.anticipatedTotalDirect = anticipatedTotalDirect;
    }

    public KualiDecimal getAnticipatedTotalIndirect() {
        return anticipatedTotalIndirect;
    }

    public void setAnticipatedTotalIndirect(KualiDecimal anticipatedTotalIndirect) {
        this.anticipatedTotalIndirect = anticipatedTotalIndirect;
    }

    public KualiDecimal getObligatedTotalDirect() {
        return obligatedTotalDirect;
    }

    public void setObligatedTotalDirect(KualiDecimal obligatedTotalDirect) {
        this.obligatedTotalDirect = obligatedTotalDirect;
    }

    public KualiDecimal getObligatedTotalIndirect() {
        return obligatedTotalIndirect;
    }

    public void setObligatedTotalIndirect(KualiDecimal obligatedTotalIndirect) {
        this.obligatedTotalIndirect = obligatedTotalIndirect;
    }

    //    public AwardBudgetInfo getAwardBudgetInfo() {  
    //        return awardBudgetInfo;  
    //    }  
    //  
    //    public void setAwardBudgetInfo(AwardBudgetInfo awardBudgetInfo) {  
    //        this.awardBudgetInfo = awardBudgetInfo;  
    //    }A  
    //  
    //    public AwardAmtFnaDistribution getAwardAmtFnaDistribution() {  
    //        return awardAmtFnaDistribution;  
    //    }  
    //  
    //    public void setAwardAmtFnaDistribution(AwardAmtFnaDistribution awardAmtFnaDistribution) {  
    //        this.awardAmtFnaDistribution = awardAmtFnaDistribution;  
    //    }  
    /**
     * Gets the originatingAwardVersion attribute. 
     * @return Returns the originatingAwardVersion.
     */
    public Integer getOriginatingAwardVersion() {
        return originatingAwardVersion;
    }

    /**
     * Sets the originatingAwardVersion attribute value.
     * @param originatingAwardVersion The originatingAwardVersion to set.
     */
    public void setOriginatingAwardVersion(Integer originatingAwardVersion) {
        this.originatingAwardVersion = originatingAwardVersion;
    }

    /**
     * Gets the transactionDetailItemsLength attribute. 
     * @return Returns the transactionDetailItemsLength.
     */
    public Integer getTransactionDetailItemsLength() {
        return transactionDetailItemsLength;
    }

    /**
     * Sets the transactionDetailItemsLength attribute value.
     * @param transactionDetailItemsLength The transactionDetailItemsLength to set.
     */
    public void setTransactionDetailItemsLength(Integer transactionDetailItemsLength) {
        this.transactionDetailItemsLength = transactionDetailItemsLength;
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardAmountInfoId = null;
    }

    /**
     * Gets the timeAndMoneyDocumentNumber attribute. 
     * @return Returns the timeAndMoneyDocumentNumber.
     */
    public String getTimeAndMoneyDocumentNumber() {
        return timeAndMoneyDocumentNumber;
    }

    /**
     * Sets the timeAndMoneyDocumentNumber attribute value.
     * @param timeAndMoneyDocumentNumber The timeAndMoneyDocumentNumber to set.
     */
    public void setTimeAndMoneyDocumentNumber(String timeAndMoneyDocumentNumber) {
        this.timeAndMoneyDocumentNumber = timeAndMoneyDocumentNumber;
    }
}
