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
package org.kuali.kra.award;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

/**
 * This class processes audit rules (warnings) for the Report Information related
 * data of the AwardDocument.
 */
public class AwardReportAuditRule implements DocumentAuditRule {

    private static final String DOT = ".";
    private static final String REPORTS_AUDIT_ERRORS = "reportsAuditErrors";
    private static final int ZERO = 0;
    private List<AuditError> auditErrors;
    
    @Override
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
     * This method creates and adds the Audit Error to the <code>{@link List&lt;AuditError&gt;}</code> auditError.
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
            GlobalVariables.getAuditErrorMap().put(REPORTS_AUDIT_ERRORS, new AuditCluster(Constants.REPORTS_PANEL_NAME,
                                                                                          auditErrors, Constants.AUDIT_ERRORS));
        }
    }

}
