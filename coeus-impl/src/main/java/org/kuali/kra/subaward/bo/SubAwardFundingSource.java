/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.subaward.bo;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.sql.Date;

public class SubAwardFundingSource extends SubAwardAssociate { 

    private static final long serialVersionUID = 1L;

    private Integer subAwardFundingSourceId;
    private Long subAwardId; 
    private String subAwardCode;
    private Long awardId;

    private String accountNumber;

    private Integer statusCode;

    private String sponsorCode;
    
    private String sponsorName;

    private Award award;

    private ScaleTwoDecimal amountObligatedToDate;

    private Date obligationExpirationDate;

    private String awardNumber;

    private AwardAmountInfo awardAmountInfo;
    

    public SubAwardFundingSource() { 

    }
    
    /**
     * 
     * Constructs a SubAwardFundingSource.java.
     * @param award
     */
    public SubAwardFundingSource(Award award) { 
        this();
        this.award = award;
        this.award.setAwardNumber("");
    }


    /**.
	 * This is the Getter Method
	 * for subAwardFundingSourceId
	 * @return Returns the subAwardFundingSourceId.
	 */
	public Integer getSubAwardFundingSourceId() {
		return subAwardFundingSourceId;
	}

	/**.
	 * This is the Setter Method for subAwardFundingSourceId
	 * @param subAwardFundingSourceId The subAwardFundingSourceId to set.
	 */
	public void setSubAwardFundingSourceId(Integer subAwardFundingSourceId) {
		this.subAwardFundingSourceId = subAwardFundingSourceId;
	}

	/**.
	 * This is the Getter Method for subAwardId
	 * @return Returns the subAwardId.
	 */
	public Long getSubAwardId() {
		return subAwardId;
	}

	/**.
	 * This is the Setter Method for subAwardId
	 * @param subAwardId The subAwardId to set.
	 */
	public void setSubAwardId(Long subAwardId) {
		this.subAwardId = subAwardId;
	}

	/**.
	 * This is the Getter Method for subAwardCode
	 * @return Returns the subAwardCode.
	 */
	public String getSubAwardCode() {
		return subAwardCode;
	}

	/**.
	 * This is the Setter Method for subAwardCode
	 * @param subAwardCode The subAwardCode to set.
	 */
	public void setSubAwardCode(String subAwardCode) {
		this.subAwardCode = subAwardCode;
	}

	/**.
	 * This is the Getter Method for awardId
	 * @return Returns the awardId.
	 */
	public Long getAwardId() {
		return awardId;
	}

	/**.
	 * This is the Setter Method for awardId
	 * @param awardId The awardId to set.
	 */
	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}

	/**.
	 * This is the Getter Method for accountNumber
	 * @return Returns the accountNumber.
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**.
	 * This is the Setter Method for accountNumber
	 * @param accountNumber The accountNumber to set.
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**.
	 * This is the Getter Method for statusCode
	 * @return Returns the statusCode.
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**.
	 * This is the Setter Method for statusCode
	 * @param statusCode The statusCode to set.
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	/**.
	 * This is the Getter Method for sponsorCode
	 * @return Returns the sponsorCode.
	 */
	public String getSponsorCode() {
		return sponsorCode;
	}

	/**.
	 * This is the Setter Method for sponsorCode
	 * @param sponsorCode The sponsorCode to set.
	 */
	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}

	/**.
	 * This is the Getter Method for sponsorName
	 * @return Returns the sponsorName.
	 */
	public String getSponsorName() {
		return sponsorName;
	}

	/**.
	 * This is the Setter Method for sponsorName
	 * @param sponsorName The sponsorName to set.
	 */
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}



	/**.
	 * This is the Getter Method for award
	 * @return Returns the award.
	 */
	public Award getAward() {
		return award;
	}

	/**.
	 * This is the Setter Method for award
	 * @param award The award to set.
	 */
	public void setAward(Award award) {
		this.award = award;
	}

	/**.
	 * This is the Getter Method for amountObligatedToDate
	 * @return Returns the amountObligatedToDate.
	 */
	public ScaleTwoDecimal getAmountObligatedToDate() {
		return amountObligatedToDate;
	}

	/**.
	 * This is the Setter Method for amountObligatedToDate
	 * @param amountObligatedToDate The amountObligatedToDate to set.
	 */
	public void setAmountObligatedToDate(ScaleTwoDecimal amountObligatedToDate) {
		this.amountObligatedToDate = amountObligatedToDate;
	}

	/**.
	 * This is the Getter Method for obligationExpirationDate
	 * @return Returns the obligationExpirationDate.
	 */
	public Date getObligationExpirationDate() {
		return obligationExpirationDate;
	}

	/**.
	 * This is the Setter Method for obligationExpirationDate
	 * @param obligationExpirationDate The obligationExpirationDate to set.
	 */
	public void setObligationExpirationDate(Date obligationExpirationDate) {
		this.obligationExpirationDate = obligationExpirationDate;
	}

	/**.
	 * This is the Getter Method for awardNumber
	 * @return Returns the awardNumber.
	 */
	public String getAwardNumber() {
		return awardNumber;
	}

	/**.
	 * This is the Setter Method for awardNumber
	 * @param awardNumber The awardNumber to set.
	 */
	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}

	/**.
	 * This is the Getter Method for awardAmountInfo
	 * @return Returns the awardAmountInfo.
	 */
	public AwardAmountInfo getAwardAmountInfo() {
		return awardAmountInfo;
	}

	/**.
	 * This is the Setter Method for awardAmountInfo
	 * @param awardAmountInfo The awardAmountInfo to set.
	 */
	public void setAwardAmountInfo(AwardAmountInfo awardAmountInfo) {
		this.awardAmountInfo = awardAmountInfo;
	}

	@Override
    public void resetPersistenceState() {

        this.subAwardFundingSourceId = null;
    }
}