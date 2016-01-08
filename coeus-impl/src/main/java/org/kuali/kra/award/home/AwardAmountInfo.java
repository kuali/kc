/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.home;

import org.apache.commons.lang.ObjectUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;
import java.sql.Timestamp;

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

    private transient GlobalVariableService globalVariableService;

    public AwardAmountInfo() {
        anticipatedTotalAmount = new ScaleTwoDecimal(0.00);
        antDistributableAmount = new ScaleTwoDecimal(0.00);
        amountObligatedToDate = new ScaleTwoDecimal(0.00);
        obliDistributableAmount = new ScaleTwoDecimal(0.00);
        anticipatedChange = new ScaleTwoDecimal(0.00);
        obligatedChange = new ScaleTwoDecimal(0.00);
        obligatedChangeDirect = new ScaleTwoDecimal(0.00);
        obligatedChangeIndirect = new ScaleTwoDecimal(0.00);
        anticipatedChangeDirect = new ScaleTwoDecimal(0.00);
        anticipatedChangeIndirect = new ScaleTwoDecimal(0.00);
        anticipatedTotalDirect = new ScaleTwoDecimal(0.00);
        anticipatedTotalIndirect = new ScaleTwoDecimal(0.00);
        obligatedTotalDirect = new ScaleTwoDecimal(0.00);
        obligatedTotalIndirect = new ScaleTwoDecimal(0.00);
    }

    public Long getAwardAmountInfoId() {
        return awardAmountInfoId;
    }

    public void setAwardAmountInfoId(Long awardAmountInfoId) {
        this.awardAmountInfoId = awardAmountInfoId;
    }

    public ScaleTwoDecimal getAnticipatedTotalAmount() {
        if (anticipatedTotalAmount == null) {
            anticipatedTotalAmount = new ScaleTwoDecimal(0.00);
        }
        return anticipatedTotalAmount;
    }

    public void setAnticipatedTotalAmount(ScaleTwoDecimal anticipatedTotalAmount) {
        changeUpdateElements(this.anticipatedTotalAmount, anticipatedTotalAmount);

        this.anticipatedTotalAmount = anticipatedTotalAmount;
        if (!(getAward() == null)) {
            if (getAward().getAwardAmountInfos().size() == 1 && getAward().getSequenceNumber() == 1) {
                setAntDistributableAmount(anticipatedTotalAmount);
            }
        }
    }

    public ScaleTwoDecimal getAntDistributableAmount() {
        if (antDistributableAmount == null) {
            antDistributableAmount = new ScaleTwoDecimal(0.00);
        }
        return antDistributableAmount;
    }

    public void setAntDistributableAmount(ScaleTwoDecimal antDistributableAmount) {
        this.antDistributableAmount = antDistributableAmount;
    }

    public Date getFinalExpirationDate() {
        return finalExpirationDate;
    }

    public void setFinalExpirationDate(Date finalExpirationDate) {
        changeUpdateElements(finalExpirationDate, this.finalExpirationDate);
        this.finalExpirationDate = finalExpirationDate;
    }

    public Date getCurrentFundEffectiveDate() {
        return currentFundEffectiveDate;
    }

    public void setCurrentFundEffectiveDate(Date currentFundEffectiveDate) {
        this.currentFundEffectiveDate = currentFundEffectiveDate;
    }

    public ScaleTwoDecimal getAmountObligatedToDate() {
        if (amountObligatedToDate == null) {
            amountObligatedToDate = new ScaleTwoDecimal(0.00);
        }
        return amountObligatedToDate;
    }

    public void setAmountObligatedToDate(ScaleTwoDecimal amountObligatedToDate) {
        changeUpdateElements(amountObligatedToDate, this.amountObligatedToDate);

        this.amountObligatedToDate = amountObligatedToDate;
        if (!(getAward() == null)) {
            if (getAward().getAwardAmountInfos().size() == 1 && getAward().getSequenceNumber() == 1) {
                setObliDistributableAmount(amountObligatedToDate);
            }
        }
    }

    public ScaleTwoDecimal getObliDistributableAmount() {
        if (obliDistributableAmount == null) {
            obliDistributableAmount = new ScaleTwoDecimal(0.00);
        }
        return obliDistributableAmount;
    }

    public void setObliDistributableAmount(ScaleTwoDecimal obliDistributableAmount) {
        this.obliDistributableAmount = obliDistributableAmount;
    }

    public Date getObligationExpirationDate() {
        return obligationExpirationDate;
    }

    public void setObligationExpirationDate(Date obligationExpirationDate) {
        changeUpdateElements(obligationExpirationDate, this.obligationExpirationDate);
        this.obligationExpirationDate = obligationExpirationDate;
    }

    protected void changeUpdateElements(Object newObject, Object oldObject) {
        if (!ObjectUtils.equals(newObject, oldObject)) {
            super.setUpdateTimestamp(new Timestamp(new java.util.Date().getTime()));
            super.setUpdateUser(getGlobalVariableService().getUserSession().getPrincipalName());
        }
    }

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
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
        if (anticipatedChange == null) {
            anticipatedChange = new ScaleTwoDecimal(0.00);
        }
        return anticipatedChange;
    }

    public void setAnticipatedChange(ScaleTwoDecimal anticipatedChange) {
        this.anticipatedChange = anticipatedChange;
    }

    public ScaleTwoDecimal getObligatedChange() {
        if (obligatedChange == null) {
            obligatedChange = new ScaleTwoDecimal(0.00);
        }
        return obligatedChange;
    }

    public void setObligatedChange(ScaleTwoDecimal obligatedChange) {
        this.obligatedChange = obligatedChange;
    }

    public ScaleTwoDecimal getObligatedChangeDirect() {
        if (obligatedChangeDirect == null) {
            obligatedChangeDirect = new ScaleTwoDecimal(0.00);
        }
        return obligatedChangeDirect;
    }

    public void setObligatedChangeDirect(ScaleTwoDecimal obligatedChangeDirect) {
        this.obligatedChangeDirect = obligatedChangeDirect;
    }

    public ScaleTwoDecimal getObligatedChangeIndirect() {
        if (obligatedChangeIndirect == null) {
            obligatedChangeIndirect = new ScaleTwoDecimal(0.00);
        }
        return obligatedChangeIndirect;
    }

    public void setObligatedChangeIndirect(ScaleTwoDecimal obligatedChangeIndirect) {
        this.obligatedChangeIndirect = obligatedChangeIndirect;
    }

    public ScaleTwoDecimal getAnticipatedChangeDirect() {
        if (anticipatedChangeDirect == null) {
            anticipatedChangeDirect = new ScaleTwoDecimal(0.00);
        }
        return anticipatedChangeDirect;
    }

    public void setAnticipatedChangeDirect(ScaleTwoDecimal anticipatedChangeDirect) {
        this.anticipatedChangeDirect = anticipatedChangeDirect;
    }

    public ScaleTwoDecimal getAnticipatedChangeIndirect() {
        if (anticipatedChangeIndirect == null) {
            anticipatedChangeIndirect = new ScaleTwoDecimal(0.00);
        }
        return anticipatedChangeIndirect;
    }

    public void setAnticipatedChangeIndirect(ScaleTwoDecimal anticipatedChangeIndirect) {
        this.anticipatedChangeIndirect = anticipatedChangeIndirect;
    }

    public ScaleTwoDecimal getAnticipatedTotalDirect() {
        if (anticipatedTotalDirect == null) {
            anticipatedTotalDirect = new ScaleTwoDecimal(0.00);
        }
        return anticipatedTotalDirect;
    }

    public void setAnticipatedTotalDirect(ScaleTwoDecimal anticipatedTotalDirect) {
        this.anticipatedTotalDirect = anticipatedTotalDirect;
    }

    public ScaleTwoDecimal getAnticipatedTotalIndirect() {
        if (anticipatedTotalIndirect == null) {
            anticipatedTotalIndirect = new ScaleTwoDecimal(0.00);
        }
        return anticipatedTotalIndirect;
    }

    public void setAnticipatedTotalIndirect(ScaleTwoDecimal anticipatedTotalIndirect) {
        this.anticipatedTotalIndirect = anticipatedTotalIndirect;
    }

    public ScaleTwoDecimal getObligatedTotalDirect() {
        if (obligatedTotalDirect == null) {
            obligatedTotalDirect = new ScaleTwoDecimal(0.00);
        }
        return obligatedTotalDirect;
    }

    public void setObligatedTotalDirect(ScaleTwoDecimal obligatedTotalDirect) {
        this.obligatedTotalDirect = obligatedTotalDirect;
    }

    public ScaleTwoDecimal getObligatedTotalIndirect() {
        if (obligatedTotalIndirect == null) {
            obligatedTotalIndirect = new ScaleTwoDecimal(0.00);
        }
        return obligatedTotalIndirect;
    }

    public void setObligatedTotalIndirect(ScaleTwoDecimal obligatedTotalIndirect) {
        this.obligatedTotalIndirect = obligatedTotalIndirect;
    }

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

   @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        if (this.getUpdateTimestamp() == null) {
            super.setUpdateTimestamp(updateTimestamp);
        }
    }

    @Override
    public void setUpdateUser(String updateUser) {
        if (this.getUpdateUser() == null) {
            super.setUpdateUser(updateUser);
        }
    }

}
