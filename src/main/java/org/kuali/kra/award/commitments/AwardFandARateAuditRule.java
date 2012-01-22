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
package org.kuali.kra.award.commitments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

public class AwardFandARateAuditRule implements DocumentAuditRule {
    private static final String FANDA_AUDIT_ERRORS = "fandaAuditErrors";
    
    private List<AuditError> auditErrors;
    private KeyValuesFinder finder;
    private ParameterService parameterService;
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    public boolean processRunAuditBusinessRules(Document document) {
        boolean retval = true;
        AwardDocument awardDocument = (AwardDocument) document;
        if(StringUtils.equalsIgnoreCase(
                this.getParameterService().getParameterValueAsString(AwardDocument.class,
                        KeyConstants.ENABLE_AWARD_FNA_VALIDATION),
                        KeyConstants.ENABLED_PARAMETER_VALUE_ONE)){
            retval = isFandaRateInputInPairs(awardDocument.getAward().getAwardFandaRate());
        }        
        return retval;
    }
    
    /**
     * 
     * This method takes as the input a list of <code>AwardFandaRate</code>,
     * iterates through it twice to find out whether both on campus and off campus entries
     * are present for every indirectRateTypeCode. 
     * Returns true if they both are present.
     * @param awardFandaRateList
     * @return
     */
    protected boolean isFandaRateInputInPairs(List<AwardFandaRate> awardFandaRateList){
        
        HashMap<String,String> a1 = new HashMap<String,String>();
        HashMap<String,String> b1 = new HashMap<String,String>();
        
        createHashMapsForRuleEvaluation(awardFandaRateList,a1,b1);
        boolean valid = evaluateRule(awardFandaRateList,a1,b1);
        if(!valid) {
            reportAndCreateAuditCluster();
        }
        
        return valid;
    }
    
    /**
     * 
     * This method iterates through the awardFandaRateList and creates two hashmaps;
     * one with on campus values and other with off campus values in it.
     * @param awardFandaRateList
     * @param a1
     * @param b1
     */
    protected void createHashMapsForRuleEvaluation(List<AwardFandaRate> awardFandaRateList,
            HashMap<String,String> a1, HashMap<String,String> b1){
        
        for(AwardFandaRate awardFandaRate : awardFandaRateList){
            if(StringUtils.equalsIgnoreCase(awardFandaRate.getOnCampusFlag(),"N")){
                a1.put(awardFandaRate.getFandaRateTypeCode(), awardFandaRate.getOnCampusFlag());
            }else if(StringUtils.equalsIgnoreCase(awardFandaRate.getOnCampusFlag(),"F")){
                b1.put(awardFandaRate.getFandaRateTypeCode(), awardFandaRate.getOnCampusFlag());
            }
        }
        
    }
    
    /**
     * @param awardFandaRateList
     * @param a1
     * @param b1
     * @return
     */
    protected boolean evaluateRule(List<AwardFandaRate> awardFandaRateList, HashMap<String,String> a1, HashMap<String,String> b1){
        boolean valid = true;
        for(AwardFandaRate awardFandaRate : awardFandaRateList){            
            if((a1.containsKey(awardFandaRate.getFandaRateTypeCode()) 
                    && !b1.containsKey(awardFandaRate.getFandaRateTypeCode()))
                    ||(b1.containsKey(awardFandaRate.getFandaRateTypeCode()) 
                            && !a1.containsKey(awardFandaRate.getFandaRateTypeCode()))) {                
                valid = false;
                addAuditError(createAuditError(getFinder().getKeyLabel(awardFandaRate.getFandaRateTypeCode())));
            }
        }
        
        return valid;
    }

    private void addAuditError(AuditError auditError) {
        if(auditErrors == null) {
            auditErrors = new ArrayList<AuditError>();            
        }
        auditErrors.add(auditError);
    }

    private AuditError createAuditError(String rateType) {
        return new AuditError(Constants.REPORT_TERMS_AUDIT_RULES_ERROR_KEY, KeyConstants.INDIRECT_COST_RATE_NOT_IN_PAIR, createPageContext(), new String[]{rateType});
    }

    private String createPageContext() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.MAPPING_AWARD_COMMITMENTS_PAGE);
        sb.append(".");
        sb.append(Constants.FANDA_RATES_PANEL_ANCHOR);
        return sb.toString();
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > 0) {
            KNSGlobalVariables.getAuditErrorMap().put(FANDA_AUDIT_ERRORS, new AuditCluster(Constants.FANDA_RATES_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }
    }
    
    /**
     * This method prepares a finder
     * @return
     */
    KeyValuesFinder getFinder() {
        if(finder == null) {
            ExtendedPersistableBusinessObjectValuesFinder extendedFinder = new ExtendedPersistableBusinessObjectValuesFinder();
            extendedFinder = new ExtendedPersistableBusinessObjectValuesFinder();
            extendedFinder.setBusinessObjectClass(FandaRateType.class);
            extendedFinder.setKeyAttributeName("fandaRateTypeCode");
            extendedFinder.setLabelAttributeName("description");
            finder = extendedFinder;
        }
        return finder;
    }
    

    /**
     * This method allows a mockFinder to be set
     * @param mockFinder
     */
    void setFinder(KeyValuesFinder mockFinder) {
        finder = mockFinder;
    }
}
