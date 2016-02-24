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
package org.kuali.kra.award.commitments;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
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
            GlobalVariables.getAuditErrorMap().put(FANDA_AUDIT_ERRORS, new AuditCluster(Constants.FANDA_RATES_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }
    }
    
    /**
     * This method prepares a finder
     * @return
     */
    KeyValuesFinder getFinder() {
        if(finder == null) {
            PersistableBusinessObjectValuesFinder extendedFinder = new PersistableBusinessObjectValuesFinder();
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
