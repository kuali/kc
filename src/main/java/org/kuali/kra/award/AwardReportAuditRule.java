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
package org.kuali.kra.award;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

/**
 * This class processes audit rules (warnings) for the Report Information related
 * data of the AwardDocument.
 */
public class AwardReportAuditRule implements DocumentAuditRule {

    private static final String DOT = ".";
    private static final String REPORTS_AUDIT_ERRORS = "reportsAuditErrors";
    private static final int ZERO = 0;
    private List<AuditError> auditErrors;
    
    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    @SuppressWarnings("unchecked")
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        AwardDocument awardDocument = (AwardDocument)document;
        auditErrors = new ArrayList<AuditError>();
        
        boolean emptyAwardReportTerms = awardDocument.getAward().getAwardReportTermItems().size() == ZERO;
        if (emptyAwardReportTerms) {
            valid = false;
            addErrorToAuditErrors(KeyConstants.ERROR_EMPTY_REPORT_TERMS);
        } 
        valid &=processAwardReportTermAuditRules(awardDocument);
        reportAndCreateAuditCluster();
        
        return valid;
    }
    public boolean processAwardReportTermAuditRules(Document document) {
        boolean retval = true;
        int i=0;
        
        AwardDocument awardDocument = (AwardDocument) document;
        for (AwardReportTerm awardReportTerm : awardDocument.getAward().getAwardReportTermItems()) {
            retval &= evaluateAuditRuleForReportCodeField(awardReportTerm, i);
            if (StringUtils.isNotBlank(awardReportTerm.getFrequencyCode())) {
                retval &= evaluateAuditRuleForFrequencyCodeField(awardReportTerm, i);
                retval &= evaluateAuditRuleForFrequencyBaseCodeField(awardReportTerm, i);
            }
            retval &= evaluateAuditRuleForRecipients(awardReportTerm, i);
            i++;
        }
        return retval;
        
    }
    protected List<KeyValue> getReportCodes(String reportClassCode){
        ReportCodeValuesFinder reportCodeValuesFinder = new ReportCodeValuesFinder();
        reportCodeValuesFinder.setReportClassCode(reportClassCode);
        return reportCodeValuesFinder.getKeyValues();
    }
    
    protected List<KeyValue> getFrequencyCodes(String reportClassCode, String reportCode){
        FrequencyCodeValuesFinder frequencyCodeValuesFinder 
        = new FrequencyCodeValuesFinder(reportClassCode, reportCode);
        return frequencyCodeValuesFinder.getKeyValues();
    }
    
    protected List<KeyValue> getFrequencyBaseCodes(String frequencyCode){
        FrequencyBaseCodeValuesFinder frequencyBaseCodeValuesFinder
            = new FrequencyBaseCodeValuesFinder();        
        frequencyBaseCodeValuesFinder.setFrequencyCode(frequencyCode);        
        return frequencyBaseCodeValuesFinder.getKeyValues();
    }
    protected boolean isValidFrequencyBase(
            AwardReportTerm awardReportTerm, List<KeyValue> frequencyBaseCodes){
        boolean isValid = false;
        
        for(KeyValue KeyValue:frequencyBaseCodes){
            if(StringUtils.equalsIgnoreCase(KeyValue.getKey().toString(), 
                    awardReportTerm.getFrequencyBaseCode())) {
                isValid = true;                    
            }
        }
        return isValid;
    }
    protected boolean isValidReportCode(AwardReportTerm awardReportTerm, List<KeyValue> reportCodes){
        boolean isValid = false;
        for(KeyValue KeyValue:reportCodes){
            if(StringUtils.equalsIgnoreCase(KeyValue.getKey().toString(), 
                    awardReportTerm.getReportCode())) {
                isValid = true;                    
            }
        }        
        return isValid;    
    }

    protected boolean evaluateAuditRuleForReportCodeField(AwardReportTerm awardReportTerm, int index){
        boolean retval = isValidReportCode(awardReportTerm, getReportCodes(awardReportTerm.getReportClassCode()));
        if(!retval){
            addErrorToAuditErrors(KeyConstants.INVALID_REPORT_CODE_FOR_REPORT_CLASS);
        }
        return retval;        
    }
    protected boolean isValidFrequency(
            AwardReportTerm awardReportTerm, List<KeyValue> frequencyCodes){
        boolean isValid = false;
        for(KeyValue KeyValue:frequencyCodes){
            if(StringUtils.equalsIgnoreCase(KeyValue.getKey().toString(), 
                    awardReportTerm.getFrequencyCode())) {
                isValid = true;                    
            }
        }
        return isValid;
    }
    
    protected boolean evaluateAuditRuleForFrequencyCodeField(AwardReportTerm awardReportTerm, int index){
        boolean retval = isValidFrequency(awardReportTerm,getFrequencyCodes(
                awardReportTerm.getReportClassCode(),awardReportTerm.getReportCode()));
        if(!retval){
            addErrorToAuditErrors(KeyConstants.INVALID_FREQUENCY_FOR_REPORT_CLASS_AND_TYPE);
        }
        return retval;
    }
    protected boolean evaluateAuditRuleForFrequencyBaseCodeField(
            AwardReportTerm awardReportTerm, int index){
        boolean retval = isValidFrequencyBase(awardReportTerm, getFrequencyBaseCodes(
                awardReportTerm.getFrequencyCode()));
        if(!retval){
            addErrorToAuditErrors(KeyConstants.INVALID_FREQUENCY_BASE_FOR_FREQUENCY);
        }
        return retval;
    }
    protected boolean evaluateAuditRuleForRecipients(AwardReportTerm awardReportTerm, int index) {
        boolean isValid = true;

        List<AwardReportTermRecipient> recipients = awardReportTerm.getAwardReportTermRecipients();
        for (int i=0; i<recipients.size(); i++) {
            AwardReportTermRecipient recipient = recipients.get(i);
            if (recipient.getRolodexId() == null) {
                addErrorToAuditErrors(KeyConstants.ERROR_REQUIRED_ORGANIZATION,new String[]{new Integer(i+1).toString()});

                isValid = false;
            }
        }
        
        return isValid;
    }
    
    /**
     * This method creates and adds the Audit Error to the <code>{@link List<AuditError>}</code> auditError.
     * @param description
     */
    protected void addErrorToAuditErrors(String errorKey,String[] params) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE);
        sb.append(DOT);
        sb.append(Constants.REPORTS_PANEL_ANCHOR);
        if(params!=null && params.length>0){
            auditErrors.add(new AuditError(Constants.REPORT_TERMS_AUDIT_RULES_ERROR_KEY, 
                    errorKey, 
                    sb.toString(),params));   
        }else{
            auditErrors.add(new AuditError(Constants.REPORT_TERMS_AUDIT_RULES_ERROR_KEY, 
                    errorKey, 
                    sb.toString()));   
        }
        
    }
    protected void addErrorToAuditErrors(String errorKey) {
        addErrorToAuditErrors(errorKey, null);
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > ZERO) {
            KNSGlobalVariables.getAuditErrorMap().put(REPORTS_AUDIT_ERRORS, new AuditCluster(Constants.REPORTS_PANEL_NAME,
                                                                                          auditErrors, Constants.AUDIT_ERRORS));
        }
    }

}
