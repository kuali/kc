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
package org.kuali.kra.award.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;

/**
 * This class represents the Struts Action for Special Review page(AwardSpecialReview.jsp).
 */
public class AwardSpecialReviewAction extends AwardAction {
    
    private static final String NEW_SPECIAL_REVIEW = "newSpecialReview";
    
    /**
     * This method is for adding AwardSpecialReview to the list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument document = awardForm.getAwardDocument();
        AwardSpecialReview newSpecialReview = awardForm.getNewSpecialReview();
        
        if (applyRules(new AddSpecialReviewEvent<AwardSpecialReview>(NEW_SPECIAL_REVIEW, document, newSpecialReview))) {
            newSpecialReview.setSpecialReviewNumber(document.getDocumentNextValue(Constants.SPECIAL_REVIEW_NUMBER));
            document.getAward().getSpecialReviews().add(newSpecialReview);
            awardForm.setNewSpecialReview(new AwardSpecialReview());
        }

        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    /**
     * This method deletes the SpecialReview from the list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument document = awardForm.getAwardDocument();
        
        document.getAward().getSpecialReviews().remove(getLineToDelete(request));

        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
}