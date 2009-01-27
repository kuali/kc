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
package org.kuali.kra.service.impl;

import java.util.List;

import org.kuali.core.service.KualiRuleService;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;
import org.kuali.kra.document.SpecialReviewManager;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.service.SpecialReviewService;
import org.kuali.kra.web.struts.form.SpecialReviewFormBase;
import org.kuali.rice.KNSServiceLocator;

/**
 * This class for handling special review actions.
 */
@SuppressWarnings("unchecked")
public class SpecialReviewServiceImpl implements SpecialReviewService {

    private KualiRuleService kualiRuleService;
    /**
     * Invoke all related rules associated with the Add Special Review Action and 
     * add the BO to the list which is a part of SpecialReviewManager
     * @see org.kuali.kra.service.SpecialReviewService#addSpecialReview(org.kuali.kra.document.SpecialReviewManager, 
     * org.kuali.kra.bo.AbstractSpecialReview)
     */
    public void addSpecialReview(SpecialReviewManager specialReviewManager, SpecialReviewFormBase specialReviewForm) {
        AbstractSpecialReview newSpecialReview = specialReviewForm.getNewSpecialReview();
        String[] exemptionTypeCodes = specialReviewForm.getNewExemptionTypeCodes();
        if(exemptionTypeCodes!=null){
            for (String exemptionTypeCode : exemptionTypeCodes) {
                newSpecialReview.addSpecialReviewExemption(exemptionTypeCode);
            }
            newSpecialReview.setExemptionTypeCodes(exemptionTypeCodes);
        }
        if(getKualiRuleService().applyRules(new AddSpecialReviewEvent(Constants.EMPTY_STRING, 
                specialReviewForm.getResearchDocument(), newSpecialReview))){
            newSpecialReview.setSpecialReviewNumber(specialReviewForm.getResearchDocument().getDocumentNextValue(
                    Constants.AWARD_SPECIALREVIEW_NUMBER));
            specialReviewManager.addSpecialReview(newSpecialReview);
        }
    }
    

    /**
     * @see org.kuali.kra.service.SpecialReviewService#deleteSpecialReview(org.kuali.kra.document.SpecialReviewManager, int)
     */
    public void deleteSpecialReview(SpecialReviewManager specialReviewManager, int selectedIndex) {
        specialReviewManager.getSpecialReviews().remove(selectedIndex);
    }

    /**
     * Gets the kualiRuleService attribute. 
     * @return Returns the kualiRuleService.
     */
    public KualiRuleService getKualiRuleService() {
        return KNSServiceLocator.getKualiRuleService();
    }

    /**
     * Sets the kualiRuleService attribute value.
     * @param kualiRuleService The kualiRuleService to set.
     */
    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }


    public void processBeforeSaveSpecialReview(SpecialReviewManager processSpecialReview) {
        List<AbstractSpecialReview> specialReviews = processSpecialReview.getSpecialReviews();
        
        for (AbstractSpecialReview abstractSpecialReview : specialReviews) {
//            abstractSpecialReview.getSpecialReviewExemptions().clear();
            String[] exemptionCodes = abstractSpecialReview.getExemptionTypeCodes();
            for (String exemptionTypeCode : exemptionCodes) {
                AbstractSpecialReviewExemption specialReviewExemption = 
                        getSpecialReviewExemptionType(abstractSpecialReview,exemptionTypeCode);
                if(specialReviewExemption==null){
                    abstractSpecialReview.addSpecialReviewExemption(exemptionTypeCode);
                }
            }
            removeUnselectedExemptions(abstractSpecialReview,exemptionCodes);
        }
    }


    /**
     * 
     * Helper method to remove all unselected special review BOs
     * @param abstractSpecialReview
     * @param exemptionCodes
     */
    private void removeUnselectedExemptions(AbstractSpecialReview abstractSpecialReview, String[] exemptionCodes) {
        List<AbstractSpecialReviewExemption> specialReviewExemptions = abstractSpecialReview.getSpecialReviewExemptions();
        for (int index = specialReviewExemptions.size()-1; index>=0 ; index--) {
            AbstractSpecialReviewExemption specialReviewExemption = specialReviewExemptions.get(index);
            boolean selected  = false;
            for (int i = 0; i < exemptionCodes.length; i++) {
                selected = selected || exemptionCodes[i].equals(specialReviewExemption.getExemptionTypeCode());
            }
            if(!selected){
                specialReviewExemptions.remove(specialReviewExemption);
            }
        }
        
    }

    /**
     * 
     * This method is to fetch the SpeciReviewExemptionType for the exemptionTypeCode
     * @param specialReview
     * @param exemptionTypeCode
     * @return AbstractSpecialReviewExemption
     */
    private AbstractSpecialReviewExemption getSpecialReviewExemptionType(AbstractSpecialReview specialReview,
            String exemptionTypeCode) {
        AbstractSpecialReviewExemption selectedExemption = null;
        List<AbstractSpecialReviewExemption> specialReviewExemptions = specialReview.getSpecialReviewExemptions();
        for (AbstractSpecialReviewExemption specialReviewExemption : specialReviewExemptions) {
            if(specialReviewExemption.getExemptionTypeCode().equals(exemptionTypeCode)){
                selectedExemption = specialReviewExemption;
                break;
            }
        }
        return selectedExemption;
    }

}
