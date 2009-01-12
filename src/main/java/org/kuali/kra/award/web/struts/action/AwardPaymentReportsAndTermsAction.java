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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.lookup.keyvalue.ReportClassValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder;
import org.kuali.kra.award.rule.event.AddAwardReportTermEvent;
import org.kuali.kra.award.rule.event.AddAwardReportTermRecipientEvent;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * 
 * This class represents the Struts Action for Payments, 
 * Reports & Terms page(AwardPaymentsReportsAndTerms.jsp)
 */
public class AwardPaymentReportsAndTermsAction extends AwardAction {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return super.execute(mapping, form, request, response);
    }
    
    
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
            awardForm.getNewAwardReportTerm().add(new AwardReportTerm());
        }
        for(int i=0;i<awardForm.getAwardDocument().getAward().getAwardReportTerms().size();i++){
            awardForm.getNewAwardReportTermRecipient().add(new AwardReportTerm());
        }
        awardForm.getAwardDocument().setReportClasses(reportClasses);
        awardForm.getAwardDocument().setReportCodes(reportCodes);
        Collections.sort(awardForm.getAwardDocument().getAward().getAwardReportTerms(),new AwardReportTermsComparator5());
        Collections.sort(awardForm.getAwardDocument().getAward().getAwardReportTerms(),new AwardReportTermsComparator4());
        Collections.sort(awardForm.getAwardDocument().getAward().getAwardReportTerms(),new AwardReportTermsComparator3());
        Collections.sort(awardForm.getAwardDocument().getAward().getAwardReportTerms(),new AwardReportTermsComparator2());
        Collections.sort(awardForm.getAwardDocument().getAward().getAwardReportTerms(),new AwardReportTermsComparator1());
        
        //Collections.sort(list, c)(awardForm.getAwardDocument().getAward().getAwardReportTerm());
        //awardForm.getAwardDocument().getAward().getAwardReportTerm()
        return actionForward;
    }
    
    public ActionForward addAwardReportTerm(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        awardForm.setAwardReportTermPanelNumber(new Integer(getReportClassCodeIndex(request)).toString());
        AwardReportTerm newAwardReportTerm = 
            awardForm.getNewAwardReportTerm().get(getReportClassCodeIndex(request));
        if(getKualiRuleService().applyRules(new AddAwardReportTermEvent(Constants.EMPTY_STRING,
                awardForm.getAwardDocument(), newAwardReportTerm))){
            newAwardReportTerm.setReportClassCode(getReportClass(request));
            newAwardReportTerm.setAwardNumber(awardForm.getAwardDocument().getAward().getAwardNumber());
            newAwardReportTerm.setSequenceNumber(awardForm.getAwardDocument().getAward().getSequenceNumber());
            addAwardReportTermToAward(awardForm.getAwardDocument().getAward(),newAwardReportTerm);            
            awardForm.getNewAwardReportTerm().set(getReportClassCodeIndex(request),new AwardReportTerm());
            AwardReportTerm awardReportTermForRecipients = new AwardReportTerm();
            awardReportTermForRecipients.setReportClassCode(newAwardReportTerm.getReportClassCode());
            awardReportTermForRecipients.setReportCode(newAwardReportTerm.getReportCode());
            awardReportTermForRecipients.setFrequencyCode(newAwardReportTerm.getFrequencyCode());
            awardReportTermForRecipients.setOspDistributionCode(newAwardReportTerm.getFrequencyBaseCode());
            awardReportTermForRecipients.setDueDate(newAwardReportTerm.getDueDate());
            awardForm.getNewAwardReportTermRecipient().add(new AwardReportTerm());
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward deleteAwardReportTerm(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        AwardReportTerm awardReportTermToBeDeleted = 
            awardDocument.getAward().getAwardReportTerms().get(getLineToDelete(request));
        
        for(AwardReportTerm awardReportTerm:determineAwardReportTermRecipientsToBeDeleted(
                awardDocument.getAward().getAwardReportTerms(), awardReportTermToBeDeleted)){
            awardDocument.getAward().getAwardReportTerms().remove(awardReportTerm);
        }
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    protected List<AwardReportTerm> determineAwardReportTermRecipientsToBeDeleted(
            List<AwardReportTerm> listAwardReportTerm, AwardReportTerm awardReportTermToBeDeleted){
        ArrayList<AwardReportTerm> indexForDeletion = new ArrayList<AwardReportTerm>();
        for(AwardReportTerm awardReportTerm: listAwardReportTerm){
            if(StringUtils.equalsIgnoreCase(awardReportTermToBeDeleted.getReportClassCode().toString(),
                    awardReportTerm.getReportClassCode().toString())){
                if(StringUtils.equalsIgnoreCase(
                        awardReportTermToBeDeleted.getReportCode().toString(),
                        awardReportTerm.getReportCode().toString()) 
                        && StringUtils.equalsIgnoreCase(
                                awardReportTermToBeDeleted.getFrequencyCode().toString(),
                                awardReportTerm.getFrequencyCode().toString())
                        && StringUtils.equalsIgnoreCase(
                                awardReportTermToBeDeleted.getFrequencyBaseCode().toString(),
                                awardReportTerm.getFrequencyBaseCode().toString())
                        && StringUtils.equalsIgnoreCase(
                                awardReportTermToBeDeleted.getDueDate().toString(),
                                awardReportTerm.getDueDate().toString())
                        && StringUtils.equalsIgnoreCase(
                                awardReportTermToBeDeleted.getOspDistributionCode().toString(),
                                awardReportTerm.getOspDistributionCode().toString())){
                    indexForDeletion.add(awardReportTerm);
                }
            }            
        }
        
        return indexForDeletion;
    }
    
    boolean addAwardReportTermToAward(Award award, AwardReportTerm awardReportTerm){
        return award.getAwardReportTerms().add(awardReportTerm);
    }
    
    public ActionForward addRecipient(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        awardForm.setAwardReportTermPanelNumber(new Integer(getRecipientIndex(request)).toString());
        AwardReportTerm newAwardReportTerm = awardForm.getNewAwardReportTermRecipient().
                                                get(getRecipientIndex(request));
        newAwardReportTerm.setAwardNumber(awardForm.getAwardDocument().getAward().getAwardNumber());
        newAwardReportTerm.setSequenceNumber(awardForm.getAwardDocument().getAward().getSequenceNumber());
        AwardDocument awardDocument = awardForm.getAwardDocument();
        if(getKualiRuleService().applyRules(new AddAwardReportTermRecipientEvent(Constants.EMPTY_STRING,
                awardDocument, newAwardReportTerm))){            
            
            addAwardReportTermToAward(awardDocument.getAward(),newAwardReportTerm);
        }
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward deleteRecipient(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        if(getRecipientSize(request)>1){
            awardDocument.getAward().getAwardReportTerms().remove(getLineToDelete(request));
        }else{
            awardDocument.getAward().getAwardReportTerms().get(
                    getLineToDelete(request)).setContactTypeCode(null);
            awardDocument.getAward().getAwardReportTerms().get(
                    getLineToDelete(request)).setNumberOfCopies(null);
            awardDocument.getAward().getAwardReportTerms().get(
                    getLineToDelete(request)).setRolodexId(null);
        }
        
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
    
    protected int getRecipientSize(HttpServletRequest request) {
        int recipientSize = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String recipientSizeString = StringUtils.substringBetween(parameterName, ".recipientSize", ".");
            recipientSize = Integer.parseInt(recipientSizeString);
        }        
        return recipientSize;
    }
    
}

class AwardReportTermsComparator1 implements Comparator<AwardReportTerm> {
    public int compare(AwardReportTerm awardReportTerm1, AwardReportTerm awardReportTerm2) {
        return  awardReportTerm1.getReportCode().compareTo(awardReportTerm2.getReportCode());
    }
  }

class AwardReportTermsComparator2 implements Comparator<AwardReportTerm> {
    public int compare(AwardReportTerm awardReportTerm1, AwardReportTerm awardReportTerm2) {
        return  awardReportTerm1.getFrequencyCode().compareTo(awardReportTerm2.getFrequencyCode());
    }
  }

class AwardReportTermsComparator3 implements Comparator<AwardReportTerm> {
    public int compare(AwardReportTerm awardReportTerm1, AwardReportTerm awardReportTerm2) {
        return  awardReportTerm1.getFrequencyBaseCode().compareTo(awardReportTerm2.getFrequencyBaseCode());
    }
  }

class AwardReportTermsComparator4 implements Comparator<AwardReportTerm> {
    public int compare(AwardReportTerm awardReportTerm1, AwardReportTerm awardReportTerm2) {
        return  awardReportTerm1.getOspDistributionCode().compareTo(awardReportTerm2.getOspDistributionCode());
    }
  }

class AwardReportTermsComparator5 implements Comparator<AwardReportTerm> {
    public int compare(AwardReportTerm awardReportTerm1, AwardReportTerm awardReportTerm2) {
        return  awardReportTerm1.getDueDate().compareTo(awardReportTerm2.getDueDate());
    }
  }