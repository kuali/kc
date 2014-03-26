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
package org.kuali.kra.award.home;

import org.kuali.kra.award.AwardAssociate;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

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

    private ScaleTwoDecimal anticipatedTotalAmount;

    private ScaleTwoDecimal antDistributableAmount;

    private Date finalExpirationDate;

    private Date currentFundEffectiveDate;

    private ScaleTwoDecimal amountObligatedToDate;

    private ScaleTwoDecimal obliDistributableAmount;

    private Date obligationExpirationDate;

    private boolean entryType;

    private boolean eomProcessFlag;

    private ScaleTwoDecimal anticipatedChange;

    private ScaleTwoDecimal obligatedChange;

    private ScaleTwoDecimal obligatedChangeDirect;

    private ScaleTwoDecimal obligatedChangeIndirect;

    private ScaleTwoDecimal anticipatedChangeDirect;

    private ScaleTwoDecimal anticipatedChangeIndirect;

    private ScaleTwoDecimal anticipatedTotalDirect;

    private ScaleTwoDecimal anticipatedTotalIndirect;

    private ScaleTwoDecimal obligatedTotalDirect;

    private ScaleTwoDecimal obligatedTotalIndirect;

    private Integer transactionDetailItemsLength;

    private Integer originatingAwardVersion;

    // private AwardBudgetInfo awardBudgetInfo;   
    // private AwardAmtFnaDistribution awardAmtFnaDistribution;   
    public AwardAmountInfo() {
        setAnticipatedTotalDirect(new ScaleTwoDecimal(0.00));
        setAnticipatedTotalIndirect(new ScaleTwoDecimal(0.00));
        setObligatedTotalDirect(new ScaleTwoDecimal(0.00));
        setObligatedTotalIndirect(new ScaleTwoDecimal(0.00));
        setAnticipatedTotalAmount(new ScaleTwoDecimal(0.00));
        setAmountObligatedToDate(new ScaleTwoDecimal(0.00));
        setObliDistributableAmount(new ScaleTwoDecimal(0.00));
        setAntDistributableAmount(new ScaleTwoDecimal(0.00));
    }

    public Long getAwardAmountInfoId() {
        return awardAmountInfoId;
    }

    public void setAwardAmountInfoId(Long awardAmountInfoId) {
        this.awardAmountInfoId = awardAmountInfoId;
    }

    public ScaleTwoDecimal getAnticipatedTotalAmount() {
        return anticipatedTotalAmount;
    }

    public void setAnticipatedTotalAmount(ScaleTwoDecimal anticipatedTotalAmount) {
        this.anticipatedTotalAmount = anticipatedTotalAmount;
        if (!(getAward() == null)) {
            if (getAward().getAwardAmountInfos().size() == 1 && getAward().getSequenceNumber() == 1) {
                setAntDistributableAmount(anticipatedTotalAmount);
            }
        }
    }

    public ScaleTwoDecimal getAntDistributableAmount() {
        return antDistributableAmount;
    }

    public void setAntDistributableAmount(ScaleTwoDecimal antDistributableAmount) {
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

    public ScaleTwoDecimal getAmountObligatedToDate() {
        return amountObligatedToDate;
    }

    public void setAmountObligatedToDate(ScaleTwoDecimal amountObligatedToDate) {
        this.amountObligatedToDate = amountObligatedToDate;
        if (!(getAward() == null)) {
            if (getAward().getAwardAmountInfos().size() == 1 && getAward().getSequenceNumber() == 1) {
                setObliDistributableAmount(amountObligatedToDate);
            }
        }
    }

    public ScaleTwoDecimal getObliDistributableAmount() {
        return obliDistributableAmount;
    }

    public void setObliDistributableAmount(ScaleTwoDecimal obliDistributableAmount) {
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

    public ScaleTwoDecimal getAnticipatedChange() {
        return anticipatedChange;
    }

    public void setAnticipatedChange(ScaleTwoDecimal anticipatedChange) {
        this.anticipatedChange = anticipatedChange;
    }

    public ScaleTwoDecimal getObligatedChange() {
        return obligatedChange;
    }

    public void setObligatedChange(ScaleTwoDecimal obligatedChange) {
        this.obligatedChange = obligatedChange;
    }

    public ScaleTwoDecimal getObligatedChangeDirect() {
        return obligatedChangeDirect;
    }

    public void setObligatedChangeDirect(ScaleTwoDecimal obligatedChangeDirect) {
        this.obligatedChangeDirect = obligatedChangeDirect;
    }

    public ScaleTwoDecimal getObligatedChangeIndirect() {
        return obligatedChangeIndirect;
    }

    public void setObligatedChangeIndirect(ScaleTwoDecimal obligatedChangeIndirect) {
        this.obligatedChangeIndirect = obligatedChangeIndirect;
    }

    public ScaleTwoDecimal getAnticipatedChangeDirect() {
        return anticipatedChangeDirect;
    }

    public void setAnticipatedChangeDirect(ScaleTwoDecimal anticipatedChangeDirect) {
        this.anticipatedChangeDirect = anticipatedChangeDirect;
    }

    public ScaleTwoDecimal getAnticipatedChangeIndirect() {
        return anticipatedChangeIndirect;
    }

    public void setAnticipatedChangeIndirect(ScaleTwoDecimal anticipatedChangeIndirect) {
        this.anticipatedChangeIndirect = anticipatedChangeIndirect;
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

    @Override
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
    
    public void resetChangeValues() {
        setObligatedChange(new ScaleTwoDecimal(0));
        setObligatedChangeDirect(new ScaleTwoDecimal(0));
        setObligatedChangeIndirect(new ScaleTwoDecimal(0));
        setAnticipatedChange(new ScaleTwoDecimal(0));
        setAnticipatedChangeDirect(new ScaleTwoDecimal(0));
        setAnticipatedChangeIndirect(new ScaleTwoDecimal(0));
    }

//TODO: For debugging, to be taken out eventually
public String toString() {
  return 
  "anticipatedTotalAmount = " +      anticipatedTotalAmount +         
  ", antDistributableAmount = " +      antDistributableAmount +         
  ", amountObligatedToDate = " +       amountObligatedToDate +          
  ", obliDistributableAmount = " +     obliDistributableAmount +        
  ", anticipatedChange = " +           anticipatedChange +              
  ", obligatedChange = " +             obligatedChange +                
  ", obligatedChangeDirect = " +       obligatedChangeDirect +          
  ", obligatedChangeIndirect = " +     obligatedChangeIndirect +        
  ", anticipatedChangeDirect = " +     anticipatedChangeDirect +        
  ", anticipatedChangeIndirect = " +   anticipatedChangeIndirect +      
  ", anticipatedTotalDirect = " +      anticipatedTotalDirect +         
  ", anticipatedTotalIndirect = " +    anticipatedTotalIndirect +       
  ", obligatedTotalDirect = " +        obligatedTotalDirect +           
  ", obligatedTotalIndirect = " +      obligatedTotalIndirect;
}

}
