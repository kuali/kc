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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardSponsorTerm;
import org.kuali.kra.award.rule.event.AwardSponsorTermRuleEvent;
import org.kuali.kra.award.rules.AwardSponsorTermRuleImpl;
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * This is a helper class for AwardPaymentReportsAction.java
 */
public class SponsorTermActionHelper {
    
    private static final String PERIOD = ".";

    
    /**
     * This method is called when adding a new AwardCostShare
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addSponsorTerm(SponsorTermFormHelper formHelper, 
                                    HttpServletRequest request) throws Exception {
        
        AwardSponsorTerm newAwardSponsorTerm = new AwardSponsorTerm();
        newAwardSponsorTerm.setSponsorTermId
                                    (formHelper.getNewSponsorTerms().
                                            get(getSponsorTermTypeIndex(request)).getSponsorTermId());
        newAwardSponsorTerm.setSponsorTerm
                                    (formHelper.getNewSponsorTerms().
                                            get(getSponsorTermTypeIndex(request)));
        newAwardSponsorTerm.setAward(formHelper.getAwardDocument().getAward());
        
        AwardSponsorTermRuleEvent event = new AwardSponsorTermRuleEvent(
                                                            "newAwardCostShare",
                                                            formHelper.getAwardDocument(),
                                                            newAwardSponsorTerm);
        boolean success = new AwardSponsorTermRuleImpl().processAddSponsorTermBusinessRules(event);
            if(success){
                formHelper.getAwardDocument().getAward().setAwardSponsorTerms(addAwardSponsorTermToAward(
                        formHelper.getAwardDocument().getAward(),newAwardSponsorTerm));            
                formHelper.getNewSponsorTerms().set(
                        getSponsorTermTypeIndex(request),new SponsorTerm());   
            }
            return success;
    }
    
    /**
     * 
     * This method adds the newAwardSponsorTerm to the list of <code>AwardSponsorTerms</code> objects.
     * @param award
     * @param newAwardSponsorTerms
     * @param reportClass
     * @return
     */
    protected List<AwardSponsorTerm> addAwardSponsorTermToAward(
                                                Award award,    
                                                    AwardSponsorTerm newAwardSponsorTerm){        
        award.getAwardSponsorTerms().add(newAwardSponsorTerm);
        return award.getAwardSponsorTerms();
    }
    
    /**
     * 
     * This method reads the awardSponsorTermsTypeIndex from the request.
     * It is specified in the tag file and is used for showing the validation errors while adding
     * a new AwardSponsor object.
     * @param request
     * @return
     */
    protected int getSponsorTermTypeIndex(HttpServletRequest request) {
        int sponsorTermTypeIndex = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String sponsorTermTypeIndexString = StringUtils.substringBetween(
                    parameterName, ".sponsorTermTypeIndex", PERIOD);
            sponsorTermTypeIndex = Integer.parseInt(sponsorTermTypeIndexString);
        }
        return sponsorTermTypeIndex;
    }
}
