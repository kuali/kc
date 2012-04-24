/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class AwardSubAwardTerms extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Integer awardSubAwardTermsId;

    private Integer awardId;

    private String mitAwardNumber;

    private Integer sequenceNumber;

    private Integer subAwardApprovalTypeCode;

    private Award award;

    public AwardSubAwardTerms() {
    }

	/**.
	 * This is the Getter Method for awardSubAwardTermsId
	 * @return Returns the awardSubAwardTermsId.
	 */
	public Integer getAwardSubAwardTermsId() {
		return awardSubAwardTermsId;
	}

	/**.
	 * This is the Setter Method for awardSubAwardTermsId
	 * @param awardSubAwardTermsId The awardSubAwardTermsId to set.
	 */
	public void setAwardSubAwardTermsId(Integer awardSubAwardTermsId) {
		this.awardSubAwardTermsId = awardSubAwardTermsId;
	}

	/**.
	 * This is the Getter Method for awardId
	 * @return Returns the awardId.
	 */
	public Integer getAwardId() {
		return awardId;
	}

	/**.
	 * This is the Setter Method for awardId
	 * @param awardId The awardId to set.
	 */
	public void setAwardId(Integer awardId) {
		this.awardId = awardId;
	}

	/**.
	 * This is the Getter Method for mitAwardNumber
	 * @return Returns the mitAwardNumber.
	 */
	public String getMitAwardNumber() {
		return mitAwardNumber;
	}

	/**.
	 * This is the Setter Method for mitAwardNumber
	 * @param mitAwardNumber The mitAwardNumber to set.
	 */
	public void setMitAwardNumber(String mitAwardNumber) {
		this.mitAwardNumber = mitAwardNumber;
	}

	/**.
	 * This is the Getter Method for sequenceNumber
	 * @return Returns the sequenceNumber.
	 */
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**.
	 * This is the Setter Method for sequenceNumber
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**.
	 * This is the Getter Method for subAwardApprovalTypeCode
	 * @return Returns the subAwardApprovalTypeCode.
	 */
	public Integer getSubAwardApprovalTypeCode() {
		return subAwardApprovalTypeCode;
	}

	/**.
	 * This is the Setter Method for subAwardApprovalTypeCode
	 * @param subAwardApprovalTypeCode The subAwardApprovalTypeCode to set.
	 */
	public void setSubAwardApprovalTypeCode(
	Integer subAwardApprovalTypeCode) {
		this.subAwardApprovalTypeCode = subAwardApprovalTypeCode;
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

}
