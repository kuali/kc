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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardReportTerms;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.lookup.keyvalue.ReportClassValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * 
 * This class represents the Struts Action for Payments, 
 * Reports & Terms page(AwardPaymentsReportsAndTerms.jsp)
 */
public class AwardPaymentReportsAndTermsAction extends AwardAction {
    
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        
        ActionForward actionForward = super.reload(mapping, form, request, response);

        ReportClassValuesFinder reportClassValuesFinder = new ReportClassValuesFinder();
        ReportCodeValuesFinder reportCodeValuesFinder = new ReportCodeValuesFinder();
        List<KeyLabelPair> reportClasses = new ArrayList<KeyLabelPair>();
        List<KeyLabelPair> reportCodes = new ArrayList<KeyLabelPair>();
        reportClasses = reportClassValuesFinder.getKeyValues();
        reportCodes = reportCodeValuesFinder.getKeyValues();
        for(int i=0;i<reportClasses.size();i++){
            awardForm.getNewAwardReportTerms().add(new AwardReportTerms());
        }
        for(int i=0;i<awardForm.getAwardDocument().getAward().getAwardReportTerms().size();i++){
            awardForm.getNewAwardReportTermsRecipients().add(new AwardReportTerms());
        }
        awardForm.getAwardDocument().setReportClasses(reportClasses);
        awardForm.getAwardDocument().setReportCodes(reportCodes);
        return actionForward;
    }
    
    public ActionForward addAwardReportTerms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardReportTerms newAwardReportTerm = awardForm.getNewAwardReportTerms().get(getReportClassCodeIndex(request));
        /*if(getKualiRuleService().applyRules(new AddAwardFandaRateEvent(Constants.EMPTY_STRING, 
                awardForm.getAwardDocument(), newAwardReportTerm))){*/
            newAwardReportTerm.setReportClassCode(getReportClass(request));
            newAwardReportTerm.setAwardNumber(awardForm.getAwardDocument().getAward().getAwardNumber());
            newAwardReportTerm.setSequenceNumber(awardForm.getAwardDocument().getAward().getSequenceNumber());
            addAwardReportTermToAward(awardForm.getAwardDocument().getAward(),newAwardReportTerm);            
            awardForm.getNewAwardReportTerms().set(getReportClassCodeIndex(request),new AwardReportTerms());
        //}
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward deleteAwardReportTerms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        awardDocument.getAward().getAwardReportTerms().remove(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    boolean addAwardReportTermToAward(Award award, AwardReportTerms awardReportTerms){
        return award.getAwardReportTerms().add(awardReportTerms);
    }
    
    public ActionForward addRecipients(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;        
        AwardReportTerms newAwardReportTerm = awardForm.getNewAwardReportTermsRecipients().get(getRecipientIndex(request));
        AwardReportTerms newAwardReportTermRecipients = new AwardReportTerms();
        AwardDocument awardDocument = awardForm.getAwardDocument();
        int i=0;
        boolean addNew = false;
        for(AwardReportTerms awardReportTerms: awardDocument.getAward().getAwardReportTerms()){
            if(awardReportTerms.getReportCode().equals(getReportCode(request))){
                if(awardReportTerms.getContactTypeCode()!=null){
                    newAwardReportTermRecipients.setAwardNumber(awardReportTerms.getAwardNumber());
                    newAwardReportTermRecipients.setSequenceNumber(awardReportTerms.getSequenceNumber());
                    newAwardReportTermRecipients.setReportClassCode(awardReportTerms.getReportClassCode());
                    newAwardReportTermRecipients.setReportCode(awardReportTerms.getReportCode());
                    newAwardReportTermRecipients.setFrequencyCode(awardReportTerms.getFrequencyCode());
                    newAwardReportTermRecipients.setFrequencyBaseCode(awardReportTerms.getFrequencyBaseCode());
                    newAwardReportTermRecipients.setOspDistributionCode(awardReportTerms.getOspDistributionCode());
                    newAwardReportTermRecipients.setDueDate(awardReportTerms.getDueDate());
                    newAwardReportTermRecipients.setContactTypeCode(newAwardReportTerm.getContactTypeCode());
                    newAwardReportTermRecipients.setNumberOfCopies(newAwardReportTerm.getNumberOfCopies());
                    newAwardReportTermRecipients.setRolodexId(newAwardReportTerm.getRolodexId());
                    addNew = true;
                }else{                    
                    awardDocument.getAward().getAwardReportTerms().get(i).setContactTypeCode(newAwardReportTerm.getContactTypeCode());
                    awardDocument.getAward().getAwardReportTerms().get(i).setNumberOfCopies(newAwardReportTerm.getNumberOfCopies());
                    awardDocument.getAward().getAwardReportTerms().get(i).setRolodexId(newAwardReportTerm.getRolodexId());
                }                
            }
            i++;
        }
        if(addNew){
            awardDocument.getAward().getAwardReportTerms().add(newAwardReportTermRecipients);
        }
        
        awardForm.getNewAwardReportTermsRecipients().set(getRecipientIndex(request),new AwardReportTerms());
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    protected int getReportClass(HttpServletRequest request) {
        int reportClass = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String reportClassString = StringUtils.substringBetween(parameterName, ".reportClass", ".");
            reportClass = Integer.parseInt(reportClassString);
        }

        return reportClass;
    }
    
    protected int getReportCode(HttpServletRequest request) {
        int reportCode = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String reportCodeString = StringUtils.substringBetween(parameterName, ".reportCode", ".");
            reportCode = Integer.parseInt(reportCodeString);
        }

        return reportCode;
    }
    
    protected int getReportClassCodeIndex(HttpServletRequest request) {
        int reportClassIndex = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String reportClassIndexString = StringUtils.substringBetween(parameterName, ".reportClassIndex", ".");
            reportClassIndex = Integer.parseInt(reportClassIndexString);
        }

        return reportClassIndex;
    }
    
    protected int getRecipientIndex(HttpServletRequest request) {
        int recipientIndex = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String recipientIndexString = StringUtils.substringBetween(parameterName, ".recipientIndex", ".");
            recipientIndex = Integer.parseInt(recipientIndexString);
        }

        return recipientIndex;
    }
    
}
