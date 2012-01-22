/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.commitments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.ValidRates;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class represents the AwardFandaRateRule
 */
public class AwardFandaRateRule  extends ResearchDocumentRuleBase implements AddFandaRateRule {
    private static final int FISCAL_YEAR_LENGTH = 4;
    private static final String FISCAL_YEAR_STRING = ".fiscalYear";
    private static final String NEW_AWARD_FANDA_RATE = "newAwardFandaRate";
    private static final String AWARD_FANDA_RATES_ARRAY = "document.awardList[0].awardFandaRate";
    private static final KualiDecimal KualiDecimal_THOUSAND = new KualiDecimal(1000);
    private static final String FANDA_RATES = "awardFandaRate";
    private static final String ON_CAMPUS= "On";
    private static final String OFF_CAMPUS = "Off";
    private static final String ON_CAMPUS_RATE = "onCampusRate";
    private static final String OFF_CAMPUS_RATE = "offCampusRate";
    private static final String FANDA_RATE_CLASS_TYPE = "O";
    private static final String RATE_CLASS_TYPE = "rateClassType";
    private static final String RATE_CLASS_TYPE_CODE = "fandaRateTypeCode";
    
    private ParameterService parameterService;
    BusinessObjectService businessObjectService;
    private boolean isPairChecked = false;
    private boolean isValidPair = false;
    
    /**
     * 
     * @see org.kuali.kra.award.commitments.AddFandaRateRule
     * #processAddFandaRateBusinessRules(org.kuali.kra.award.commitments.AddAwardFandaRateEvent)
     */
    public boolean processAddFandaRateBusinessRules(AddAwardFandaRateEvent
            addAwardFandaRateEvent) {
        AwardFandaRate awardFandaRate = 
            addAwardFandaRateEvent.getAwardFandaRate();        
        boolean rulePassed = true;
        rulePassed &= evaluateRuleForApplicableFandaRate(awardFandaRate, NEW_AWARD_FANDA_RATE);
        rulePassed &= evaluateRuleForFandaRateTypeCode(awardFandaRate, NEW_AWARD_FANDA_RATE);
        rulePassed &= evaluateRuleForFiscalYear(awardFandaRate, NEW_AWARD_FANDA_RATE);
        rulePassed &= evaluateRuleForStartAndEndDates(awardFandaRate, NEW_AWARD_FANDA_RATE);
        rulePassed &= checkValidFandARate(awardFandaRate,NEW_AWARD_FANDA_RATE);

        return rulePassed;
    }
    
    public boolean processSaveFandaRateBusinessRules(AwardFandaRateSaveEvent awardFandaRateSaveEvent) {
        AwardFandaRate awardFandaRate = awardFandaRateSaveEvent.getAwardFandaRate();        
        boolean rulePassed = true;
        AwardDocument awardDocument = (AwardDocument)awardFandaRateSaveEvent.getDocument();
        String propertyPrefix = AWARD_FANDA_RATES_ARRAY + "[" + awardFandaRateSaveEvent.getFandaRateIndex() + "]";
        rulePassed &= evaluateRuleForApplicableFandaRate(awardFandaRate, propertyPrefix);
        rulePassed &= evaluateRuleForFandaRateTypeCode(awardFandaRate, propertyPrefix);
        rulePassed &= evaluateRuleForFiscalYear(awardFandaRate, propertyPrefix);
        rulePassed &= evaluateRuleForStartAndEndDates(awardFandaRate, propertyPrefix);
        rulePassed &= checkValidFandARate(awardFandaRate,propertyPrefix);
        
        if(rulePassed){
            if(StringUtils.equalsIgnoreCase(
                    getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, 
                            ParameterConstants.DOCUMENT_COMPONENT,
                            KeyConstants.ENABLE_AWARD_FNA_VALIDATION),
                            KeyConstants.ENABLED_PARAMETER_VALUE_ONE)){
                if(!isPairChecked){
                   rulePassed = isFandaRateInputInPairs(awardDocument.getAward().getAwardFandaRate());
                }
                          
            } else if(StringUtils.equalsIgnoreCase(
                    getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, 
                            ParameterConstants.DOCUMENT_COMPONENT,
                            KeyConstants.ENABLE_AWARD_FNA_VALIDATION),
                            KeyConstants.ENABLED_PARAMETER_VALUE_TWO)){
                rulePassed = checkValidFandARate(awardFandaRate,propertyPrefix);
            }
        }

        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for applicableFandaRate field.
     * @param awardFandaRate
     * @param propertyPrefix
     * @return
     */
    protected boolean evaluateRuleForApplicableFandaRate(AwardFandaRate awardFandaRate, String propertyPrefix){        
        String brokenRule = null;
        if (awardFandaRate.getApplicableFandaRate() == null || 
                StringUtils.isBlank(awardFandaRate.getApplicableFandaRate().toString())) {
            brokenRule =  KeyConstants.ERROR_REQUIRED_APPLICABLE_INDIRECT_COST_RATE;
        } else if (awardFandaRate.getApplicableFandaRate().isLessThan(KualiDecimal.ZERO)) {
            brokenRule = KeyConstants.ERROR_APPLICABLE_INDIRECT_COST_RATE_CAN_NOT_BE_NEGATIVE;
        } else if (awardFandaRate.getApplicableFandaRate().isGreaterEqual(KualiDecimal_THOUSAND)) {
            brokenRule = KeyConstants.ERROR_APPLICABLE_INDIRECT_COST_RATE_OUT_OF_RANGE;
        }
            
        if(brokenRule != null) {
            reportError(propertyPrefix+".applicableFandaRate", brokenRule);
            return false;
        }
        return true;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for fandaRateTypeCode field.
     * @param awardFandaRate
     * @param propertyPrefix
     * @return
     */
    protected boolean evaluateRuleForFandaRateTypeCode(AwardFandaRate awardFandaRate, String propertyPrefix){
        boolean rulePassed = !(awardFandaRate.getFandaRateTypeCode()==null 
                || StringUtils.isBlank(awardFandaRate.getFandaRateTypeCode().toString()));
        if(!rulePassed){            
            reportError(propertyPrefix+".fandaRateTypeCode"
                    , KeyConstants.ERROR_REQUIRED_INDIRECT_RATE_TYPE_CODE);
        }   
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for fiscalYear field.
     * @param awardFandaRate
     * @param propertyPrefix
     * @return
     */
    protected boolean evaluateRuleForFiscalYear(AwardFandaRate awardFandaRate, String propertyPrefix){
        boolean rulePassed = true;        
        if(awardFandaRate.getFiscalYear()==null 
                || StringUtils.isBlank(awardFandaRate.getFiscalYear())){
            rulePassed = false;
            reportError(propertyPrefix+FISCAL_YEAR_STRING
                    , KeyConstants.ERROR_REQUIRED_FISCAL_YEAR);
        }else if(awardFandaRate.getFiscalYear().length()!=FISCAL_YEAR_LENGTH){
            rulePassed = false;
            reportError(propertyPrefix+FISCAL_YEAR_STRING
                    , KeyConstants.ERROR_FISCAL_YEAR_INCORRECT_FORMAT, "Fiscal Year");
        }else{
            try{
                Integer.parseInt(awardFandaRate.getFiscalYear());
            }catch(NumberFormatException e){
                rulePassed = false;
                reportError(propertyPrefix+FISCAL_YEAR_STRING
                        , KeyConstants.ERROR_FISCAL_YEAR_INCORRECT_FORMAT);
            }
        }
        return rulePassed;
    }
    
    /**
     * 
     * This is a convenience method for evaluating the rule for startDate and endDate fields.
     * @param awardFandaRate
     * @param propertyPrefix
     * @return
     */
    protected boolean evaluateRuleForStartAndEndDates(AwardFandaRate awardFandaRate, String propertyPrefix){
        boolean rule1Passed = !(awardFandaRate.getStartDate()==null 
                || StringUtils.isBlank(awardFandaRate.getStartDate().toString()));
        final String[] DATE_PARAMS = {"Start Date","End Date"};
        
        if(!rule1Passed){            
            reportError(propertyPrefix+".startDate", KeyConstants.ERROR_REQUIRED_START_DATE);
        }
        boolean rule2Passed =  !(awardFandaRate.getEndDate() !=null 
                && awardFandaRate.getStartDate() != null 
                && awardFandaRate.getEndDate().before(awardFandaRate.getStartDate()));
        if (!rule2Passed) {            
            reportError(propertyPrefix+".endDate"
                    , KeyConstants.ERROR_END_DATE_BEFORE_START_DATE_INDIRECT_COST_RATE,DATE_PARAMS);
        }
        return rule1Passed && rule2Passed;
    }
    /**
     * 
     * This method checks whether FandaRares are available in valid Rate table or not;
     * @param awardFandaRateList
     */
    public boolean checkValidFandARate(AwardFandaRate awardFandaRate,String propertyPrefix){
        boolean valid = true;
        Collection<ValidRates> validRates = null;

        String onOFFCampusRate = null;
        if (awardFandaRate.getOnCampusFlag().equalsIgnoreCase("N")) {
            onOFFCampusRate = ON_CAMPUS;
            validRates =  getValidRatesForFandA(ON_CAMPUS_RATE, awardFandaRate.getApplicableFandaRate());
        } else {
            validRates =  getValidRatesForFandA(OFF_CAMPUS_RATE, awardFandaRate.getApplicableFandaRate());
            onOFFCampusRate = OFF_CAMPUS;
        }
        if (validRates.size() == 0) {
            valid = false;
            if (StringUtils.equalsIgnoreCase(
                    this.getParameterService().getParameterValueAsString(AwardDocument.class, KeyConstants.OPTION_WARNING_ERROR_AWARD_FANDA_VALIDATION),
                    KeyConstants.ERROR)) {
                reportError(propertyPrefix + ".applicableFandaRate", 
                        KeyConstants.ERROR_AWARD_FANDA_INVALID_RTAES_FOR_SINGLE_RATE,
                        awardFandaRate.getApplicableFandaRate().toString(),onOFFCampusRate);
            } else {
                valid = true;
                reportWarning(propertyPrefix + ".applicableFandaRate", 
                        KeyConstants.ERROR_AWARD_FANDA_INVALID_RTAES_FOR_SINGLE_RATE,
                        awardFandaRate.getApplicableFandaRate().toString(),onOFFCampusRate);
            }
        }
        return valid;
        }
    
    /**
     * This method returns the valid rates from valid rates table that match the rates attached to the award.
     * @return
     */
    @SuppressWarnings("unchecked")
    private Collection<ValidRates> getValidRatesForFandA(String rateTypeOnOrOff, KualiDecimal rate){
        Map<String, Object> rateValues = new HashMap<String, Object>();
        rateValues.put(rateTypeOnOrOff, rate);
        rateValues.put(RATE_CLASS_TYPE, FANDA_RATE_CLASS_TYPE);
        
        return (Collection<ValidRates>) 
                getKraBusinessObjectService().findMatching(ValidRates.class, rateValues);
    }
    /**
     * This method returns the Kra business object service.
     * @return
     */
    BusinessObjectService getKraBusinessObjectService() {
        if(businessObjectService == null){
            businessObjectService = 
                (BusinessObjectService) KraServiceLocator.getService("businessObjectService");
        }
        return businessObjectService;
    }
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
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
    
    DateFormat dateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN);
    createHashMapsForRuleEvaluation(awardFandaRateList,a1,b1);
    boolean valid = evaluateRule(awardFandaRateList,a1,b1);
    if(!valid) {
        GlobalVariables.getMessageMap().putError(FANDA_RATES
                , KeyConstants.INDIRECT_COST_RATE_NOT_IN_PAIR);
    }
    isPairChecked = true;
    if(valid){
        int i = 0;
        for(AwardFandaRate awardFandaRate1 :awardFandaRateList){
            if(StringUtils.equalsIgnoreCase(awardFandaRate1.getOnCampusFlag(),"N")){
                int j= 0;
                if(getValidRatesForFandA(ON_CAMPUS_RATE, awardFandaRate1.getApplicableFandaRate()).size() > 0){
                    for(AwardFandaRate awardFandaRate2 :awardFandaRateList){
                        if(StringUtils.equalsIgnoreCase(awardFandaRate2.getOnCampusFlag(),"F") &&
                                awardFandaRate1.getFandaRateTypeCode().equals(awardFandaRate2.getFandaRateTypeCode())){
                                if(getValidRates(awardFandaRate1.getApplicableFandaRate(), awardFandaRate2.getApplicableFandaRate()).size() == 0){
                                    valid=false;
                                    reportError(AWARD_FANDA_RATES_ARRAY+"[" + j + "].applicableFandaRate", KeyConstants.ERROR_AWARD_FANDA_INVALID_RTAES,getFandACodeTypeDescription(awardFandaRate2.getFandaRateTypeCode()),awardFandaRate2.getFiscalYear(),dateFormat.format(awardFandaRate2.getStartDate()));
                                }
                        }
                        j++;
                    }
                }else{
                    valid = false;
                    reportError(AWARD_FANDA_RATES_ARRAY+"[" + i + "].applicableFandaRate", KeyConstants.ERROR_AWARD_FANDA_INVALID_RTAES,getFandACodeTypeDescription(awardFandaRate1.getFandaRateTypeCode()),awardFandaRate1.getFiscalYear(),dateFormat.format(awardFandaRate1.getStartDate()));
                }
            }
            i++;
        }
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
    DateFormat dateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN);
     for(AwardFandaRate awardFandaRate : awardFandaRateList){            
        if((a1.containsKey(awardFandaRate.getFandaRateTypeCode()) 
                && !b1.containsKey(awardFandaRate.getFandaRateTypeCode()))
                ||(b1.containsKey(awardFandaRate.getFandaRateTypeCode()) 
                        && !a1.containsKey(awardFandaRate.getFandaRateTypeCode()))) {                
            valid = false;
        }
    }
    
    
    return valid;
}

private String getFandACodeTypeDescription(String rateTypeCode){
    String description = Constants.EMPTY_STRING;
    if(rateTypeCode != null){
        Map<String, Object> rateValues = new HashMap<String, Object>();
        rateValues.put(RATE_CLASS_TYPE_CODE, rateTypeCode.toString());
        Collection<FandaRateType> fandaRteTypes = (Collection<FandaRateType>) 
                getKraBusinessObjectService().findMatching(FandaRateType.class, rateValues);
        if(fandaRteTypes != null && fandaRteTypes.size() > 0){
            for(FandaRateType fandaRateType : fandaRteTypes){
                if(fandaRateType.getDescription() != null){
                    description = fandaRateType.getDescription();
                }
            }
        }
    }
   return description ;
}
@SuppressWarnings("unchecked")
Collection<ValidRates> getValidRates(KualiDecimal specialEbRateOnCampus, KualiDecimal specialEbRateOffCampus){
    Map<String, Object> rateValues = new HashMap<String, Object>();
    rateValues.put(ON_CAMPUS_RATE, specialEbRateOnCampus);
    rateValues.put(OFF_CAMPUS_RATE, specialEbRateOffCampus);
    rateValues.put(RATE_CLASS_TYPE, FANDA_RATE_CLASS_TYPE);
    return (Collection<ValidRates>) 
            getKraBusinessObjectService().findMatching(ValidRates.class, rateValues);
}
}