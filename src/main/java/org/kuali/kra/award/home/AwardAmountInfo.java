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
package org.kuali.kra.award.home;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.rice.kns.util.KualiDecimal;

// TODO gmcgrego - I created this class, but only needed to use finalExpirationDate.
// So everything else isn't necessarily correct (ie Long -> KualiDecimal?).

/**
 * AwardAmountInfo BO
 * 
 * @author Kuali Coeus development team (kc.dev@kuali.org)
 */
public class AwardAmountInfo extends AwardAssociate { 
    
    private static final long serialVersionUID = 1L;

    private Long awardAmountInfoId; 
    private Integer amountSequenceNumber; 
    private KualiDecimal anticipatedTotalAmount; 
    private KualiDecimal antDistributableAmount; 
    private Date finalExpirationDate; 
    private Date currentFundEffectiveDate; 
    private KualiDecimal amountObligatedToDate; 
    private KualiDecimal obliDistributableAmount; 
    private Date obligationExpirationDate; 
    private Long transactionId; 
    private boolean entryType; 
    private boolean eomProcessFlag; 
    private Long anticipatedChange; 
    private Long obligatedChange; 
    private Long obligatedChangeDirect; 
    private Long obligatedChangeIndirect; 
    private Long anticipatedChangeDirect; 
    private Long anticipatedChangeIndirect; 
    private KualiDecimal anticipatedTotalDirect; 
    private KualiDecimal anticipatedTotalIndirect; 
    private KualiDecimal obligatedTotalDirect; 
    private KualiDecimal obligatedTotalIndirect; 
     
    // private AwardBudgetInfo awardBudgetInfo; 
    // private AwardAmtFnaDistribution awardAmtFnaDistribution; 
    
    public AwardAmountInfo() {
        setAnticipatedTotalDirect(new KualiDecimal(0.00));
        setAnticipatedTotalIndirect(new KualiDecimal(0.00));
        setObligatedTotalDirect(new KualiDecimal(0.00));
        setObligatedTotalIndirect(new KualiDecimal(0.00));
        

    } 
    
    public Long getAwardAmountInfoId() {
        return awardAmountInfoId;
    }

    public void setAwardAmountInfoId(Long awardAmountInfoId) {
        this.awardAmountInfoId = awardAmountInfoId;
    }

    public Integer getAmountSequenceNumber() {
        return amountSequenceNumber;
    }

    public void setAmountSequenceNumber(Integer amountSequenceNumber) {
        this.amountSequenceNumber = amountSequenceNumber;
    }

    public KualiDecimal getAnticipatedTotalAmount() {
        return anticipatedTotalAmount;
    }

    public void setAnticipatedTotalAmount(KualiDecimal anticipatedTotalAmount) {
        this.anticipatedTotalAmount = anticipatedTotalAmount;
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

    public Long getAnticipatedChange() {
        return anticipatedChange;
    }

    public void setAnticipatedChange(Long anticipatedChange) {
        this.anticipatedChange = anticipatedChange;
    }

    public Long getObligatedChange() {
        return obligatedChange;
    }

    public void setObligatedChange(Long obligatedChange) {
        this.obligatedChange = obligatedChange;
    }

    public Long getObligatedChangeDirect() {
        return obligatedChangeDirect;
    }

    public void setObligatedChangeDirect(Long obligatedChangeDirect) {
        this.obligatedChangeDirect = obligatedChangeDirect;
    }

    public Long getObligatedChangeIndirect() {
        return obligatedChangeIndirect;
    }

    public void setObligatedChangeIndirect(Long obligatedChangeIndirect) {
        this.obligatedChangeIndirect = obligatedChangeIndirect;
    }

    public Long getAnticipatedChangeDirect() {
        return anticipatedChangeDirect;
    }

    public void setAnticipatedChangeDirect(Long anticipatedChangeDirect) {
        this.anticipatedChangeDirect = anticipatedChangeDirect;
    }

    public Long getAnticipatedChangeIndirect() {
        return anticipatedChangeIndirect;
    }

    public void setAnticipatedChangeIndirect(Long anticipatedChangeIndirect) {
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
//    }
//
//    public AwardAmtFnaDistribution getAwardAmtFnaDistribution() {
//        return awardAmtFnaDistribution;
//    }
//
//    public void setAwardAmtFnaDistribution(AwardAmtFnaDistribution awardAmtFnaDistribution) {
//        this.awardAmtFnaDistribution = awardAmtFnaDistribution;
//    }

    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("awardAmountInfoId", this.getAwardAmountInfoId());
        hashMap.put("awardNumber", this.getAwardNumber());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("amountSequenceNumber", this.getAmountSequenceNumber());
        hashMap.put("anticipatedTotalAmount", this.getAnticipatedTotalAmount());
        hashMap.put("antDistributableAmount", this.getAntDistributableAmount());
        hashMap.put("finalExpirationDate", this.getFinalExpirationDate());
        hashMap.put("currentFundEffectiveDate", this.getCurrentFundEffectiveDate());
        hashMap.put("amountObligatedToDate", this.getAmountObligatedToDate());
        hashMap.put("obliDistributableAmount", this.getObliDistributableAmount());
        hashMap.put("obligationExpirationDate", this.getObligationExpirationDate());
        hashMap.put("transactionId", this.getTransactionId());
        hashMap.put("entryType", this.getEntryType());
        hashMap.put("eomProcessFlag", this.getEomProcessFlag());
        hashMap.put("anticipatedChange", this.getAnticipatedChange());
        hashMap.put("obligatedChange", this.getObligatedChange());
        hashMap.put("obligatedChangeDirect", this.getObligatedChangeDirect());
        hashMap.put("obligatedChangeIndirect", this.getObligatedChangeIndirect());
        hashMap.put("anticipatedChangeDirect", this.getAnticipatedChangeDirect());
        hashMap.put("anticipatedChangeIndirect", this.getAnticipatedChangeIndirect());
        hashMap.put("anticipatedTotalDirect", this.getAnticipatedTotalDirect());
        hashMap.put("anticipatedTotalIndirect", this.getAnticipatedTotalIndirect());
        hashMap.put("obligatedTotalDirect", this.getObligatedTotalDirect());
        hashMap.put("obligatedTotalIndirect", this.getObligatedTotalIndirect());
        return hashMap;
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardAmountInfoId = null;
    }
}