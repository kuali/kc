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

import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class TemplateSubAwardTerms extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Integer templateSubAwardTermsId;

    private Integer templateCode;

    private Integer subAwardApprovalTypeCode;

    private SubAwardApprovalType subAwardApprovalType;

    private AwardTemplate awardTemplate;

    /**
     * Constructs a TemplateSubAwardTerms.java.
     */
    public TemplateSubAwardTerms() {
    }

    /**.
    * This is the Getter Method for templateSubAwardTermsId
    * @return Returns the templateSubAwardTermsId.
    */
    public Integer getTemplateSubAwardTermsId() {
     return templateSubAwardTermsId;
   }

	/**.
	 * This is the Setter Method for templateSubAwardTermsId
	 * @param templateSubAwardTermsId The templateSubAwardTermsId to set.
	 */
	public void setTemplateSubAwardTermsId(Integer templateSubAwardTermsId) {
		this.templateSubAwardTermsId = templateSubAwardTermsId;
	}

	/**.
	 * This is the Getter Method for templateCode
	 * @return Returns the templateCode.
	 */
	public Integer getTemplateCode() {
		return templateCode;
	}

	/**.
	 * This is the Setter Method for templateCode
	 * @param templateCode The templateCode to set.
	 */
	public void setTemplateCode(Integer templateCode) {
		this.templateCode = templateCode;
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
	public void setSubAwardApprovalTypeCode(Integer subAwardApprovalTypeCode) {
		this.subAwardApprovalTypeCode = subAwardApprovalTypeCode;
	}

	/**.
	 * This is the Getter Method for subAwardApprovalType
	 * @return Returns the subAwardApprovalType.
	 */
	public SubAwardApprovalType getSubAwardApprovalType() {
		return subAwardApprovalType;
	}

	/**.
	 * This is the Setter Method for subAwardApprovalType
	 * @param subAwardApprovalType The subAwardApprovalType to set.
	 */
	public void setSubAwardApprovalType(SubAwardApprovalType subAwardApprovalType) {
		this.subAwardApprovalType = subAwardApprovalType;
	}

	/**.
	 * This is the Getter Method for awardTemplate
	 * @return Returns the awardTemplate.
	 */
	public AwardTemplate getAwardTemplate() {
		return awardTemplate;
	}

	/**.
	 * This is the Setter Method for awardTemplate
	 * @param awardTemplate The awardTemplate to set.
	 */
	public void setAwardTemplate(AwardTemplate awardTemplate) {
		this.awardTemplate = awardTemplate;
	}

 
}
