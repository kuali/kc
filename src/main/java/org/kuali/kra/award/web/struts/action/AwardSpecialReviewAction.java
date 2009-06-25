/*
 * Copyright 2006-2008 The Kuali Foundation
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
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.award.specialreview.AwardSpecialReviewExemption;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.SpecialReviewService;

/**
 * 
 * This class represents the Struts Action for Special Review page(AwardSpecialReview.jsp)
 */
public class AwardSpecialReviewAction extends AwardAction {   
    /**
     * 
     * This method is for adding AwardSpecialReview
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        Award award = awardForm.getAwardDocument().getAward();
        getSpecialReviewService().addSpecialReview(award,awardForm);
        awardForm.setNewSpecialReview(new AwardSpecialReview());
        awardForm.setNewExemptionTypeCodes(null);
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    private SpecialReviewService<AwardSpecialReview,AwardSpecialReviewExemption> getSpecialReviewService() {
        return KraServiceLocator.getService(SpecialReviewService.class);
    }
    /**
     * 
     * This method deletes the SpecialReview from the list
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteSpecialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        Award award = awardForm.getAwardDocument().getAward();
        getSpecialReviewService().deleteSpecialReview(award,getLineToDelete(request));

        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AwardForm awardForm = (AwardForm)form;
        Award award = awardForm.getAwardDocument().getAward();
        getSpecialReviewService().processBeforeSaveSpecialReview(award);
        return super.save(mapping, form, request, response);
    }
    
}
