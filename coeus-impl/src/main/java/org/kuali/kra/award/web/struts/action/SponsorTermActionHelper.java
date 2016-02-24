/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.AwardSponsorTermRuleEvent;
import org.kuali.kra.award.AwardSponsorTermRuleImpl;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a helper class for AwardPaymentReportsandTermsAction.java
 */
public class SponsorTermActionHelper implements Serializable {
    
    private static final long serialVersionUID = -7294863297504587355L;
    private static final String SPONSOR_TERM_CODE = "sponsorTermCode";
    private static final String SPONSOR_TERM_TYPE_CODE = "sponsorTermTypeCode";
    private static final String PERIOD = ".";
    private static final String NEW_AWARD_SPONSOR_TERM = "newAwardSponsorTerm";
    private transient BusinessObjectService businessObjectService;
    
    /**
     * This method is called when adding a new AwardSponsorTerm. It checks if the add is coming from a lookup or a hardcoded
     * SponsorTermCode from the user and calls the corresponding add method.
     * @param formHelper
     * @return
     * @throws Exception
     */
    public AwardSponsorTerm addSponsorTerm(SponsorTermFormHelper formHelper, HttpServletRequest request) throws Exception {
        return addSponsorTermFromDatabase(formHelper, request);
    }
    
   
    
    public AwardSponsorTerm addSponsorTermFromMutiValueLookup(SponsorTermFormHelper formHelper, SponsorTerm sponsorTerm, HttpServletRequest request) {
        AwardSponsorTerm newAwardSponsorTerm = new AwardSponsorTerm(sponsorTerm.getSponsorTermId(), sponsorTerm);
        if (applyRulesToAwardSponsorTermFromMultiValueLookup(newAwardSponsorTerm, formHelper, request, sponsorTerm)) {
            return newAwardSponsorTerm;
        } else {
            return null;
        }
    }
    
    
    /**
     * This method adds sponsorTerm from a hardcoded sponsorCode in UI. Pulls the sponsorTerm data from database.
     * @return new AwardSponsorTerm on success, null otherwise
     */
    protected AwardSponsorTerm addSponsorTermFromDatabase(SponsorTermFormHelper formHelper, HttpServletRequest request) {
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
        
        if (applyRulesToAwardSponsorTerm(newAwardSponsorTerm, formHelper, request)) {        
            return newAwardSponsorTerm;
        } else {
            return null;
        }
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
    * @param AwardSponsorTerm, MessageMap
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
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
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
            businessObjectService = (BusinessObjectService) KcServiceLocator.getService("businessObjectService");
        }
        return businessObjectService;
    }
}
