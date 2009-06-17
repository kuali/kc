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
package org.kuali.kra.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AbstractSpecialReview;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.document.SpecialReviewHandler;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rule.event.AddSpecialReviewEvent;
import org.kuali.kra.service.SpecialReviewService;
import org.kuali.kra.web.struts.form.SpecialReviewFormBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KualiRuleService;

/**
 * This class for handling special review actions.
 */
@SuppressWarnings("unchecked")
public class SpecialReviewServiceImpl implements SpecialReviewService {

    private KualiRuleService kualiRuleService;
    
    private BusinessObjectService businessObjectService;
    
    /**
     * Invoke all related rules associated with the Add Special Review Action and 
     * add the BO to the list which is a part of SpecialReviewManager
     * @see org.kuali.kra.service.SpecialReviewService#addSpecialReview(org.kuali.kra.document.SpecialReviewHandler, 
     * org.kuali.kra.bo.AbstractSpecialReview)
     * 
     */
    public boolean addSpecialReview(SpecialReviewHandler specialReviewManager, SpecialReviewFormBase specialReviewForm) {
        AbstractSpecialReview newSpecialReview = specialReviewForm.getNewSpecialReview();
        String[] exemptionTypeCodes = specialReviewForm.getNewExemptionTypeCodes();
        if(exemptionTypeCodes!=null){
            for (String exemptionTypeCode : exemptionTypeCodes) {
                newSpecialReview.addSpecialReviewExemption(exemptionTypeCode);
            }
            newSpecialReview.setExemptionTypeCodes(exemptionTypeCodes);
        }
        ResearchDocumentBase docBase = specialReviewForm.getResearchDocument();
        AddSpecialReviewEvent addSpecialReviewEvent =new AddSpecialReviewEvent(Constants.EMPTY_STRING, 
                docBase, newSpecialReview); 
        boolean rulesPassed = getKualiRuleService().applyRules(addSpecialReviewEvent);
        if (rulesPassed) {
            if(docBase!=null){
                newSpecialReview.setSpecialReviewNumber(docBase.getDocumentNextValue(
                        Constants.SPECIAL_REVIEW_NUMBER));
            }
            specialReviewManager.addSpecialReview(newSpecialReview);
        }
        return rulesPassed;
    }
    

    /**
     * @see org.kuali.kra.service.SpecialReviewService#deleteSpecialReview(org.kuali.kra.document.SpecialReviewHandler, int)
     */
    public void deleteSpecialReview(SpecialReviewHandler specialReviewManager, int selectedIndex) {
        specialReviewManager.getSpecialReviews().remove(selectedIndex);
    }

    /**
     * Gets the kualiRuleService attribute. 
     * @return Returns the kualiRuleService.
     */
    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    /**
     * Sets the kualiRuleService attribute value.
     * @param kualiRuleService The kualiRuleService to set.
     */
    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }

    /**
     * Perform all required tasks before save. Re-iterate the exemptionCode array, create SpecialReviewExemption BOs and 
     * attach to the list in the SpecialReview BO.
     * @see org.kuali.kra.service.SpecialReviewService#processBeforeSaveSpecialReview(org.kuali.kra.document.SpecialReviewHandler)
     */
    public void processBeforeSaveSpecialReview(SpecialReviewHandler processSpecialReview) {
        List<AbstractSpecialReview> specialReviews = processSpecialReview.getSpecialReviews();
        
        for (AbstractSpecialReview abstractSpecialReview : specialReviews) {
            String[] exemptionCodes = abstractSpecialReview.getExemptionTypeCodes();
            if (exemptionCodes != null) {
                for (String exemptionTypeCode : exemptionCodes) {
                    AbstractSpecialReviewExemption specialReviewExemption = 
                        getSpecialReviewExemptionType(abstractSpecialReview,exemptionTypeCode);
                    if(specialReviewExemption==null){
                        abstractSpecialReview.addSpecialReviewExemption(exemptionTypeCode);
                    }
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
            if (exemptionCodes != null) {
                for (int i = 0; i < exemptionCodes.length; i++) {
                    selected = selected || exemptionCodes[i].equals(specialReviewExemption.getExemptionTypeCode());
                }
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

    /**
     * @see org.kuali.kra.service.SpecialReviewService#deleteExemptions(org.kuali.kra.document.SpecialReviewHandler, java.lang.Class)
     */
    public void deleteExemptions(SpecialReviewHandler specialReviewHandler, String specialReviewIdFieldName, Class specialReviewExemptionClass) {
        List<AbstractSpecialReview> specialReviews = specialReviewHandler.getSpecialReviews();
        for (AbstractSpecialReview specialReview : specialReviews) {
            Collection<AbstractSpecialReviewExemption> exemptions = getExemptionsInDatabase(specialReview.getSpecialReviewId(), specialReviewIdFieldName, specialReviewExemptionClass);
            for (AbstractSpecialReviewExemption exemption : exemptions) {
                if (!isInList(exemption, specialReview.getNewExemptionTypeCodes())) {
                    businessObjectService.delete(exemption);
                }
            }
            specialReview.clearExemptionTypeCodes();
        }
    }
    
    private Collection<AbstractSpecialReviewExemption> getExemptionsInDatabase(Long specialReviewId, String specialReviewIdFieldName, Class specialReviewExemptionClass) {
        Map<String, Long> fieldValues = new HashMap<String, Long>();
        fieldValues.put(specialReviewIdFieldName, specialReviewId);
        return businessObjectService.findMatching(specialReviewExemptionClass, fieldValues);
    }

    private boolean isInList(AbstractSpecialReviewExemption exemption, String[] exemptionTypeCodes) {
        if (exemptionTypeCodes != null) {
            for (String exemptionTypeCode : exemptionTypeCodes) {
                if (StringUtils.equals(exemption.getExemptionTypeCode(), exemptionTypeCode)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Inject the Business Object Service
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
