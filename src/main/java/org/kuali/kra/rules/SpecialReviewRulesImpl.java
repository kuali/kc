/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.rules;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.SpecialReviewRule;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;

/**
 * This class validates all rules associated with SpecialReview
 */
public class SpecialReviewRulesImpl extends ResearchDocumentRuleBase implements SpecialReviewRule {

    private String rootErrorPath = "newSpecialReview";
    
    /**
     * Constructs a SpecialReviewRulesImpl.java.
     */
    public SpecialReviewRulesImpl() {
        
    }
   
    /**
     * Constructs a SpecialReviewRulesImpl.java.
     * @param rootErrorPath
     */
    public SpecialReviewRulesImpl(String rootErrorPath) {
        this.rootErrorPath = rootErrorPath;
    }
    
    /**
     * @see org.kuali.kra.rule.SpecialReviewRule#processAddSpecialReviewEvent(org.kuali.kra.rule.event.AddSpecialReviewEvent)
     */
    @SuppressWarnings("unchecked")
    public boolean processAddSpecialReviewEvent(AddSpecialReviewEvent addSpecialReviewEvent) {
        AbstractSpecialReview specialReview = addSpecialReviewEvent.getSpecialReview();
        boolean rulePassed = true;
        String[] dateParams = {"Approval Date","Application Date"};

        if(StringUtils.isBlank(specialReview.getApprovalTypeCode())){
            rulePassed = false;
            reportError(rootErrorPath+".approvalTypeCode", KeyConstants.ERROR_REQUIRED_SELECT_APPROVAL_STATUS);
        }
        if(StringUtils.isBlank(specialReview.getSpecialReviewCode())){
            rulePassed = false;
            reportError(rootErrorPath+".specialReviewCode", KeyConstants.ERROR_REQUIRED_SELECT_SPECIAL_REVIEW_CODE);
        }
        if (specialReview.getApplicationDate() !=null && specialReview.getApprovalDate() != null && 
                specialReview.getApprovalDate().before(specialReview.getApplicationDate())) {
            rulePassed = false;
            reportError(rootErrorPath+".approvalDate", KeyConstants.ERROR_APPROVAL_DATE_BEFORE_APPLICATION_DATE_SPECIALREVIEW,dateParams);
        }
        return rulePassed;
    }

    /**
     * Validate the current list of special reviews.  Users are allowed to
     * modify existing special review entries and thus they need to be validated
     * on save.
     * @param specialReviews the list of special reviews
     * @return true if valid; otherwise false
     */
    public boolean processSpecialReviewSaveRules(List<? extends AbstractSpecialReview<?>> specialReviews) {
        boolean rulePassed = true;
        String[] dateParams = {"Approval Date","Application Date"};
        for (int i = 0; i < specialReviews.size(); i++) {
            AbstractSpecialReview<?> specialReview = specialReviews.get(i);
            if (specialReview.getApplicationDate() != null && specialReview.getApprovalDate() != null && 
                    specialReview.getApprovalDate().before(specialReview.getApplicationDate())) {
                rulePassed = false;
                reportError(rootErrorPath+"[" + i + "].approvalDate", KeyConstants.ERROR_APPROVAL_DATE_BEFORE_APPLICATION_DATE_SPECIALREVIEW, dateParams);
            }
        }
        return rulePassed;
    }
}
