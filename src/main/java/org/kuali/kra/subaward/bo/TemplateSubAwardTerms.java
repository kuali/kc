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

import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class TemplateSubAwardTerms extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Integer templateSubAwardTermsId;

    private Integer templateCode;

    private Integer subAwardApprovalTypeCode;

    private SubAwardApprovalType subAwardApprovalType;

    private AwardTemplate awardTemplate;

    public TemplateSubAwardTerms() {
    }

    public Integer getTemplateSubAwardTermsId() {
        return templateSubAwardTermsId;
    }

    public void setTemplateSubAwardTermsId(Integer templateSubAwardTermsId) {
        this.templateSubAwardTermsId = templateSubAwardTermsId;
    }

    public Integer getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }

    public Integer getSubAwardApprovalTypeCode() {
        return subAwardApprovalTypeCode;
    }

    public void setSubAwardApprovalTypeCode(Integer subAwardApprovalTypeCode) {
        this.subAwardApprovalTypeCode = subAwardApprovalTypeCode;
    }

    public SubAwardApprovalType getSubAwardApprovalType() {
        return subAwardApprovalType;
    }

    public void setSubAwardApprovalType(SubAwardApprovalType subAwardApprovalType) {
        this.subAwardApprovalType = subAwardApprovalType;
    }

    public AwardTemplate getAwardTemplate() {
        return awardTemplate;
    }

    public void setAwardTemplate(AwardTemplate awardTemplate) {
        this.awardTemplate = awardTemplate;
    }
}
