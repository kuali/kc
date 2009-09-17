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

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.AwardSponsorTermRuleEvent;
import org.kuali.kra.award.AwardSponsorTermRuleImpl;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * This is a helper class for AwardPaymentReportsandTermsAction.java
 */
public class SponsorTermActionHelper implements Serializable {
    
    private static final long serialVersionUID = -7294863297504587355L;
    private static final String SPONSOR_TERM_CODE = "sponsorTermCode";
    private static final String SPONSOR_TERM_TYPE_CODE = "sponsorTermTypeCode";
    private static final String PERIOD = ".";
    private static final String NEW_AWARD_SPONSOR_TERM = "newAwardSponsorTerm";
    BusinessObjectService businessObjectService;
    
    /**
     * This method is called when adding a new AwardSponsorTerm. It checks if the add is coming from a lookup or a hardcoded
     * SponsorTermCode from the user and calls the corresponding add method.
     * @param formHelper
     * @return
     * @throws Exception
     */
    public boolean addSponsorTerm(SponsorTermFormHelper formHelper, HttpServletRequest request) throws Exception {
        boolean success = addSponsorTermFromDatabase(formHelper, request);
        return success;
    }
    
   
    
    public boolean addSponsorTermFromMutiValueLookup(SponsorTermFormHelper formHelper, SponsorTerm sponsorTerm, HttpServletRequest request) {
        AwardSponsorTerm newAwardSponsorTerm = new AwardSponsorTerm(sponsorTerm.getSponsorTermId(), sponsorTerm);
        return applyRulesToAwardSponsorTermFromMultiValueLookup(newAwardSponsorTerm, formHelper, request, sponsorTerm);
    }
    
    
    /**
     * This method adds sponsorTerm from a hardcoded sponsorCode in UI. Pulls the sponsorTerm data from database.
     * @return
     */
    protected boolean addSponsorTermFromDatabase(SponsorTermFormHelper formHelper, HttpServletRequest request) {
        // sponsorTermCode is the value entered into the "Code" field by the user. sponsorTermTypeCode is the
        // index of the subpanel that contains the field.
        String sponsorTermCode = formHelper.getNewSponsorTerms().get(getSponsorTermTypeIndex(request)).getSponsorTermCode();
        int sponsorTermTypeCode = getSponsorTermTypeIndex(request) + 1;
        Collection<SponsorTerm> matchingSponsorTerms = getMatchingSponsorTermFromDatabase(formHelper, sponsorTermCode, sponsorTermTypeCode);
        
        Long sponsorTermId;
        SponsorTerm matchingSponsorTerm;
        if (matchingSponsorTerms.isEmpty()) {
            // no sponsor term or invalid, so set it to null
            matchingSponsorTerm = null;
            sponsorTermId = null;
        }
        else {
            matchingSponsorTerm = (SponsorTerm) matchingSponsorTerms.iterator().next();
            sponsorTermId = matchingSponsorTerm.getSponsorTermId();
        }
        AwardSponsorTerm newAwardSponsorTerm = new AwardSponsorTerm(sponsorTermId, matchingSponsorTerm);
        
        return applyRulesToAwardSponsorTerm(newAwardSponsorTerm, formHelper, request);
    }
    
    
    /**
     * This method applies the rules to the award before adding.
     * @param newAwardSponsorTerm
     * @return
     */
    protected boolean applyRulesToAwardSponsorTerm(AwardSponsorTerm newAwardSponsorTerm, SponsorTermFormHelper formHelper, HttpServletRequest request) {
        String sponsorTermCode = formHelper.getNewSponsorTerms().get(getSponsorTermTypeIndex(request)).getSponsorTermCode();
        int sponsorTermTypeCode = getSponsorTermTypeIndex(request) + 1;
        
        AwardSponsorTermRuleEvent event = 
            new AwardSponsorTermRuleEvent(NEW_AWARD_SPONSOR_TERM, formHelper.getAwardDocument(), newAwardSponsorTerm, sponsorTermCode, sponsorTermTypeCode);
        boolean success = new AwardSponsorTermRuleImpl().processAddSponsorTermBusinessRules(event);
        if (success) {
            addAwardSponsorTerm(newAwardSponsorTerm, formHelper, request);
        }
        return success;
    }
    
    /**
     * This method applies the rules to the Award Sponsor Term before adding.
     * @param newAwardSponsorTerm
     * @return
     */
    protected boolean applyRulesToAwardSponsorTermFromMultiValueLookup(AwardSponsorTerm newAwardSponsorTerm, SponsorTermFormHelper formHelper, HttpServletRequest request, SponsorTerm sponsorTerm) {
        boolean success = validateAwardSponsorTermNotDuplicate(newAwardSponsorTerm, formHelper.getAwardDocument().getAward().getAwardSponsorTerms());
        if (success) {
            addAwardSponsorTermFromMultiValueLookup(newAwardSponsorTerm, formHelper, request);
        }
        return success;
    }
    
    /**
    *
    * Validate that Award Sponsor term is not a duplicate.
    * @param AwardSponsorTerm, ErrorMap
    * @return Boolean
    */
    boolean validateAwardSponsorTermNotDuplicate(AwardSponsorTerm awardSponsorTerm, List<AwardSponsorTerm> awardSponsorTerms){
        boolean valid = true;
        for(AwardSponsorTerm tempAwardSponsorTerm : awardSponsorTerms){
            if (awardSponsorTerm.getSponsorTermId().equals(tempAwardSponsorTerm.getSponsorTermId())){
                valid = false;
            }
        }
        return valid;
    }
    
    /**
     * Helper method to add sponsorTerm to Award.
     * @param newAwardSponsorTerm
     */
    protected void addAwardSponsorTermFromMultiValueLookup(AwardSponsorTerm newAwardSponsorTerm, SponsorTermFormHelper formHelper, HttpServletRequest request) {
        formHelper.getAwardDocument().getAward().add(newAwardSponsorTerm);          
    }
    
    /**
     * Helper method to add sponsorTerm to Award.
     * @param newAwardSponsorTerm
     */
    protected void addAwardSponsorTerm(AwardSponsorTerm newAwardSponsorTerm, SponsorTermFormHelper formHelper, HttpServletRequest request) {
        formHelper.getAwardDocument().getAward().add(newAwardSponsorTerm);          
        formHelper.getNewSponsorTerms().set(getSponsorTermTypeIndex(request),new SponsorTerm());  
    }
    
    
    /**
     * This method gets the Matching sponsorTerm from the database and returns a Collection of one SponsorTerm.
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Collection<SponsorTerm> getMatchingSponsorTermFromDatabase(SponsorTermFormHelper formHelper, String sponsorTermCode, int sponsorTermTypeCode) {
        Map<String, Object> matchingSponsorTerm = new HashMap<String, Object>();
        matchingSponsorTerm.put(SPONSOR_TERM_CODE, sponsorTermCode);
        matchingSponsorTerm.put(SPONSOR_TERM_TYPE_CODE, Integer.toString(sponsorTermTypeCode));
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
