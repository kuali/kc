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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardSponsorTerm;
import org.kuali.kra.award.rule.event.AwardSponsorTermRuleEvent;
import org.kuali.kra.award.rules.AwardSponsorTermRuleImpl;
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * This is a helper class for AwardPaymentReportsandTermsAction.java
 */
public class SponsorTermActionHelper {
    
    private static final String SPONSOR_TERM_CODE = "sponsorTermCode";
    private static final String SPONSOR_TERM_TYPE_CODE = "sponsorTermTypeCode";
    private static final String PERIOD = ".";
    private static final String NEW_AWARD_SPONSOR_TERM = "newAwardSponsorTerm";
    BusinessObjectService businessObjectService;
    private SponsorTermFormHelper localFormHelper;
    private HttpServletRequest localRequest;

    
    /**
     * This method is called when adding a new AwardSponsorTerm. It checks if the add is coming from a lookup or a hardcoded
     * SponsorTermCode from the user and calls the corresponding add method.
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addSponsorTerm(SponsorTermFormHelper formHelper, HttpServletRequest request) throws Exception {
        
        localFormHelper = formHelper;
        localRequest = request;
        boolean success;
        if(formHelper.getNewSponsorTerms().get(getSponsorTermTypeIndex(request)).getSponsorTermId() != null){
                 success = addSponsorTermFromLookup();
            }else {
               success = addSponsorTermFromDatabase();
               }
            return success;
        
    }
    
    /**
     * This method adds sponsor term with data from a lookup in User Interface.  Here you do not need to pull data from Database
     * to set fields in AwardSponsorTerm
     * @return
     */
    protected boolean addSponsorTermFromLookup() {
        AwardSponsorTerm newAwardSponsorTerm = new AwardSponsorTerm();
        newAwardSponsorTerm.setSponsorTermId(localFormHelper.getNewSponsorTerms().get(getSponsorTermTypeIndex(localRequest)).getSponsorTermId());
        newAwardSponsorTerm.setSponsorTerm(localFormHelper.getNewSponsorTerms().get(getSponsorTermTypeIndex(localRequest)));
        newAwardSponsorTerm.setAward(localFormHelper.getAwardDocument().getAward());
        return applyRulesToAwardSponsorTerm(newAwardSponsorTerm);
    }
    
    
    /**
     * This method adds sponsorTerm from a hardcoded sponsorCode in UI. Pulls the sponsorTerm data from database.
     * @return
     */
    @SuppressWarnings("unchecked")
    protected boolean addSponsorTermFromDatabase() {
        AwardSponsorTerm newAwardSponsorTerm = new AwardSponsorTerm();
        Collection<SponsorTerm> matchingSponsorTerms = getMatchingSponsorTermFromDatabase();
        for(SponsorTerm sponsorTerm : matchingSponsorTerms) {
            newAwardSponsorTerm.setSponsorTermId(sponsorTerm.getSponsorTermId());
            newAwardSponsorTerm.setSponsorTerm(sponsorTerm);
        }
        newAwardSponsorTerm.setAward(localFormHelper.getAwardDocument().getAward());
        return applyRulesToAwardSponsorTerm(newAwardSponsorTerm);
    }
    
    
    /**
     * This method applies the rules to the award before adding.
     * @param newAwardSponsorTerm
     * @return
     */
    protected boolean applyRulesToAwardSponsorTerm(AwardSponsorTerm newAwardSponsorTerm) {
        AwardSponsorTermRuleEvent event = 
            new AwardSponsorTermRuleEvent(NEW_AWARD_SPONSOR_TERM, localFormHelper.getAwardDocument(), newAwardSponsorTerm);
         boolean success = new AwardSponsorTermRuleImpl().processAddSponsorTermBusinessRules(event);
              if(success){
                  addAwardSponsorTerm(newAwardSponsorTerm);   
                  }
              return success;
    }
    
    /**
     * Helper method to add sponsorTerm to Award.
     * @param newAwardSponsorTerm
     */
    protected void addAwardSponsorTerm(AwardSponsorTerm newAwardSponsorTerm) {
        localFormHelper.getAwardDocument().getAward().
                setAwardSponsorTerms(addAwardSponsorTermToAward(localFormHelper.getAwardDocument().getAward(),newAwardSponsorTerm));            
        localFormHelper.getNewSponsorTerms().set(getSponsorTermTypeIndex(localRequest),new SponsorTerm());  
    }
    
    /**
     * 
     * This method adds the newAwardSponsorTerm to the list of <code>AwardSponsorTerms</code> objects.
     * @param award
     * @param newAwardSponsorTerms
     * @param reportClass
     * @return
     */
    protected List<AwardSponsorTerm> addAwardSponsorTermToAward(Award award, AwardSponsorTerm newAwardSponsorTerm){        
        award.getAwardSponsorTerms().add(newAwardSponsorTerm);
        return award.getAwardSponsorTerms();
    }
    
    /**
     * This method gets the Matching sponsorTerm from the database and returns a Collection of one SponsorTerm.
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Collection<SponsorTerm> getMatchingSponsorTermFromDatabase() {
        Map<String, Object> matchingSponsorTerm = new HashMap<String, Object>();
        matchingSponsorTerm.put(SPONSOR_TERM_CODE, localFormHelper.getNewSponsorTerms().get(getSponsorTermTypeIndex(localRequest)).getSponsorTermCode());
        matchingSponsorTerm.put(SPONSOR_TERM_TYPE_CODE, Integer.toString(getSponsorTermTypeIndex(localRequest)+1));
        return (Collection<SponsorTerm>) getKraBusinessObjectService().findMatching(SponsorTerm.class, matchingSponsorTerm);
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
            String sponsorTermTypeIndexString = StringUtils.substringBetween(parameterName, ".sponsorTermTypeIndex", PERIOD);
            sponsorTermTypeIndex = Integer.parseInt(sponsorTermTypeIndexString);
        }
        return sponsorTermTypeIndex;
    }
    
    /**
     * This is a convenience method to use jmock to set the businessObjectService for unit testing.
     * @param businessObjectService
     */
    void setBusinessObjectService(BusinessObjectService businessObjectService){
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * This method returns the Kra business object service.
     * @return
     */
    BusinessObjectService getKraBusinessObjectService() {
        if(businessObjectService == null){
            businessObjectService = (BusinessObjectService) KraServiceLocator.getService("businessObjectService");
        }
        return businessObjectService;
    }
}
