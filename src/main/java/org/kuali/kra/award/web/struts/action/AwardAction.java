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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.document.Document;
import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.lookup.keyvalue.ReportClassValuesFinder;
import org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.rice.kns.util.KNSConstants;

import edu.iu.uis.eden.clientapp.IDocHandler;

/**
 * 
 * This class represents base Action class for all the Award pages.
 */
public class AwardAction extends KraTransactionalDocumentActionBase {
    
    /**
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;        
        ActionForward forward = handleDocument(mapping, form, request, response, awardForm);        
        awardForm.initializeFormOrDocumentBasedOnCommand();
        
        return forward;
    }
    
    /**
     * 
     * This method builds the string for the ActionForward 
     * @param forward
     * @param docIdRequestParameter
     * @return
     */
    public String buildForwardStringForActionListCommand(String forwardPath, String docIdRequestParameter){
        StringBuilder sb = new StringBuilder();
        sb.append(forwardPath);
        sb.append("?");
        sb.append(KNSConstants.PARAMETER_DOC_ID);
        sb.append("=");
        sb.append(docIdRequestParameter);
        return sb.toString();
    }
    /**
     * 
     * This method gets called upon navigation to Awards tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward home(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_AWARD_HOME_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Contacts tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward contacts(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_AWARD_CONTACTS_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Time & Money tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward timeAndMoney(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_AWARD_TIME_AND_MONEY_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Payment, Reports and Terms tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward paymentReportsAndTerms(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;
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
        
        return mapping.findForward(Constants.MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Special Review tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward specialReview(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_AWARD_SPECIAL_REVIEW_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Special Review tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward customData(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_AWARD_CUSTOM_DATA_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Custom Data tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward questions(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_AWARD_QUESTIONS_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Questions tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward permissions(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_AWARD_PERMISSIONS_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Permissions tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward notesAndAttachments(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_AWARD_NOTES_AND_ATTACHMENTS_PAGE);
    }
    
    /**
     * 
     * This method gets called upon navigation to Award Actions tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward awardActions(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {        
        return mapping.findForward(Constants.MAPPING_AWARD_ACTIONS_PAGE);
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param awardForm
     * @return
     * @throws Exception
     */
    ActionForward handleDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response, AwardForm awardForm) throws Exception {
        String command = awardForm.getCommand();
        ActionForward forward;        
        if (IDocHandler.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            awardForm.setDocument(retrievedDocument);
            request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);
            ActionForward baseForward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
            forward = new ActionForward(buildForwardStringForActionListCommand(
                    baseForward.getPath(),docIdRequestParameter));  
        } else {
        forward = super.docHandler(mapping, form, request, response);
        }
        return forward;
    }
    
    /**
     * 
     * @return
     */
    DocumentService getDocumentService() {
        return KNSServiceLocator.getDocumentService();
    }
    
    /**
     * 
     * This method is a helper method to retrieve KualiRuleService.
     * @return
     */
    protected KualiRuleService getKualiRuleService() {
        return KraServiceLocator.getService(KualiRuleService.class);
    }
}
