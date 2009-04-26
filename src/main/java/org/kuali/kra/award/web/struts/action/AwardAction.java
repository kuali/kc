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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.bo.AwardReportTermRecipient;
import org.kuali.kra.award.bo.ReportClass;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.common.customattributes.CustomDataAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.AwardDirectFandADistributionService;
import org.kuali.kra.service.AwardReportsService;
import org.kuali.kra.service.AwardSponsorTermService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

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
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ActionForward actionForward = super.execute(mapping, form, request, response);
        new AuditActionHelper().auditConditionally((AwardForm)form);
        return actionForward;
    }
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        AwardForm awardForm = (AwardForm) form;   
        ActionForward forward = super.save(mapping, form, request, response);
        
        if (isValidSave(awardForm)) {
            forward = super.save(mapping, form, request, response);
        }

        if (awardForm.getMethodToCall().equals("save") && awardForm.isAuditActivated()) {
            forward = mapping.findForward(Constants.MAPPING_AWARD_ACTIONS_PAGE);
        }

        return forward;
    }
    
    /**
     * Create the original set of Award Users for a new Award Document.
     * The creator the protocol is assigned to the AWARD_AGGREGATOR role.
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#initialDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void initialDocumentSave(KualiDocumentFormBase form) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument doc = awardForm.getAwardDocument();
        
        UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
        String username = user.getPersonUserIdentifier();
        KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
        kraAuthService.addRole(username, RoleConstants.AWARD_AGGREGATOR, doc.getAward());
    }
    
    /**
     * Can the Award be saved?  This method is normally overridden by
     * a subclass in order to invoke business rules to verify that the
     * Award can be saved.
     * @param awardForm the Award Form
     * @return true if the award can be saved; otherwise false
     */
    protected boolean isValidSave(AwardForm awardForm) {
        return true;
    }
    
    /**
     * Use the Kuali Rule Service to apply the rules for the given event.
     * @param event the event to process
     * @return true if success; false if there was a validation error
     */
    protected final boolean applyRules(KualiDocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }
    
    
    /**
     * 
     * This method builds the string for the ActionForward 
     * @param forwardPath
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
        AwardForm awardForm = (AwardForm) form;
        if(isNewAward(awardForm)){
            AwardDirectFandADistributionService awardDirectFandADistributionService = getAwardDirectFandADistributionService();
            awardForm.getAwardDocument().getAward().setAwardDirectFandADistributions
                                (awardDirectFandADistributionService.
                                        generateDefaultAwardDirectFandADistributionPeriods(awardForm.getAwardDocument().getAward()));
        }
        return mapping.findForward(Constants.MAPPING_AWARD_TIME_AND_MONEY_PAGE);
    }
    
    /**
     * This method tests if the award is new by checking the size of AwardDirectFandADistributions on the Award.
     * @param awardForm
     * @return
     */
    public boolean isNewAward(AwardForm awardForm) {
        return awardForm.getAwardDocument().getAward().getAwardDirectFandADistributions().size() == 0;
    }
    
    /**
     * 
     * This method is a helper method to retrieve AwardSponsorTermService.
     * @return
     */
    protected AwardDirectFandADistributionService getAwardDirectFandADistributionService() {
        return KraServiceLocator.getService(AwardDirectFandADistributionService.class);
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
    @SuppressWarnings("all")
    public ActionForward paymentReportsAndTerms(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) {
        AwardForm awardForm = (AwardForm) form;   
        
        setReportsAndTermsOnAwardForm(awardForm);
        
        return mapping.findForward(Constants.MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE);
    }
    
    @SuppressWarnings("unchecked")
    protected void setReportsAndTermsOnAwardForm(AwardForm awardForm) {
        AwardSponsorTermService awardSponsorTermService = getAwardSponsorTermService();
        List<KeyLabelPair> sponsorTermTypes = awardSponsorTermService.retrieveSponsorTermTypesToAwardFormForPanelHeaderDisplay();
        awardForm.getSponsorTermFormHelper().setSponsorTermTypes(sponsorTermTypes);
        awardForm.getSponsorTermFormHelper().setNewSponsorTerms(awardSponsorTermService.getEmptyNewSponsorTerms(sponsorTermTypes));
        
        AwardReportsService awardReportsService = KraServiceLocator.getService(AwardReportsService.class);  
        Map<String,Object> initializedObjects = awardReportsService.initializeObjectsForReportsAndPayments(
                                                    awardForm.getAwardDocument().getAward());
        awardForm.setReportClasses((List<KeyLabelPair>) initializedObjects.get(
                                      Constants.REPORT_CLASSES_KEY_FOR_INITIALIZE_OBJECTS));
        awardForm.setNewAwardReportTerm((List<AwardReportTerm>) initializedObjects.get(
                                          Constants.NEW_AWARD_REPORT_TERMS_LIST_KEY_FOR_INITIALIZE_OBJECTS));
        awardForm.setNewAwardReportTermRecipient((List<AwardReportTermRecipient>) initializedObjects.get(
                                                    Constants.NEW_AWARD_REPORT_TERM_RECIPIENTS_LIST_KEY_FOR_INITIALIZE_OBJECTS));
        awardForm.setReportClassForPaymentsAndInvoices((ReportClass) initializedObjects.get(
                                                        Constants.REPORT_CLASS_FOR_PAYMENTS_AND_INVOICES_PANEL));
        
    }
    
    /**
     * 
     * This method is a helper method to retrieve AwardSponsorTermService.
     * @return
     */
    protected AwardSponsorTermService getAwardSponsorTermService() {
        return KraServiceLocator.getService(AwardSponsorTermService.class);
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
        return CustomDataAction.customData(mapping, form, request, response);
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
        ((AwardForm)form).getPermissionsHelper().prepareView();
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
        if (KEWConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
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
    @Override
    protected DocumentService getDocumentService() {
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
