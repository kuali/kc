/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * This class represents SubAwardApproval...
 */
public class SubAwardApproval extends KraPersistableBusinessObjectBase {

    
    private static final long serialVersionUID = 1L;

    private Integer subAwardApprovalCode;

    private String description;

    private TemplateSubAwardTerms templateSubAwardTerms;

    private AwardSubAwardTerms awardSubAwardTerms;

    /**
     * Constructs a SubAwardApproval.java.
     */
    public SubAwardApproval() {
    }

    /**
     * This method using for getting subAwardApproval code...
     * @return subAwardApprovalCode
     */
       public Integer getSubAwardApprovalCode() {
        return subAwardApprovalCode;
    }

    /**
     * This method sets subAwardApprovalCode attribute value...
     * @param subAwardApprovalCode The subAwardApprovalCode to set
     */
    public void setSubAwardApprovalCode(Integer subAwardApprovalCode) {
        this.subAwardApprovalCode = subAwardApprovalCode;
    }
     
    /**
     * This method is for getting description...
     * @return description
     */
    public String getDescription() {
        return description;
    }
    /**
     * This method sets description attribute value...
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * This method is for getting templateSubAwardTerms...
     * @return templateSubAwardTerms
     */
    public TemplateSubAwardTerms getTemplateSubAwardTerms() {
        return templateSubAwardTerms;
    }
    /**
     * This method sets templateSubAwardTerms attribute value...
     * @param templateSubAwardTerms The templateSubAwardTerms to set
     */ 
    public void setTemplateSubAwardTerms(TemplateSubAwardTerms templateSubAwardTerms) {
        this.templateSubAwardTerms = templateSubAwardTerms;
    }
    /**
     * This method is for getting awardSubAwardTerms...
     * @return awardSubAwardTerms
     */
    public AwardSubAwardTerms getAwardSubAwardTerms() {
        return awardSubAwardTerms;
    }
    /**
     * This method sets awardSubAwardTerms attribute value...
     * @param awardSubAwardTerms The awardSubAwardTerms to set
     */ 
    public void setAwardSubAwardTerms(AwardSubAwardTerms awardSubAwardTerms) {
        this.awardSubAwardTerms = awardSubAwardTerms;
    }
}
