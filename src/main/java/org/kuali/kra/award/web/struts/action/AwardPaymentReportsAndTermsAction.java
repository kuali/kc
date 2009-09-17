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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseoutService;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KeywordsService;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.PersistenceService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * 
 * This class represents the Struts Action for Payments, 
 * Reports & Terms page(AwardPaymentsReportsAndTerms.jsp)
 */
public class AwardPaymentReportsAndTermsAction extends AwardAction {
    private static final String ROLODEX = "rolodex";
    private static final String PERIOD = ".";    
    private SponsorTermActionHelper sponsorTermActionHelper;
    
    public AwardPaymentReportsAndTermsAction() {
        sponsorTermActionHelper = new SponsorTermActionHelper();
    }

    public ActionForward addPaymentScheduleItem(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        ((AwardForm) form).getPaymentScheduleBean().addPaymentScheduleItem();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward deletePaymentScheduleItem(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) 
                                            throws Exception {
            (((AwardForm) form).getPaymentScheduleBean()).deletePaymentScheduleItem(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward generatePaymentSchedules(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        
        (((AwardForm) form).getPaymentScheduleBean()).generatePaymentSchedules();
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward addApprovedEquipmentItem(ActionMapping mapping, ActionForm form, 
                                                    HttpServletRequest request, HttpServletResponse response) 
                                                    throws Exception {
        ((AwardForm) form).getApprovedEquipmentBean().addApprovedEquipmentItem();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * Overriding the toggle tab method here. Before calling the super - report schedules are generated as part of the view only report tracking functionality.
     * 
     * Essentially we can not have another method by the name same as toggleTab - since this method is very specific to reporting functionality only; if we had
     * a method by name toggleTab - it would get called for java script not enabled browsers for every panel on this tab. We still want to open the tab after 
     * generating the report schedules, so calling the super toggleTab here. 
     * 
     * We are passing a parameter to innerTab in the UI; through which we will get this method to be called.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toggleTabReporting(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ((AwardForm) form).getAwardReportingBean().generateReportSchedules(getAwardReportTermIndex(request));    
        
        return super.toggleTab(mapping, form, request, response);
    }
    
    /**
     * 
     * This method deletes a report schedule item. 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteReportScheduleItem(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
                                                    throws Exception {
            (((AwardForm) form).getAwardReportingBean()).deleteReportScheduleItem(getAwardReportTermIndex(request), getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * This method adds a foreign travel trip
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addApprovedForeignTravel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                                throws Exception {
        ((AwardForm) form).getApprovedForeignTravelBean().addApprovedForeignTravel();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    
    
    
    
    
    /**
     * This method forces recalculation
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward recalculateSpecialApprovalTotals(ActionMapping mapping, ActionForm form, 
                                                    HttpServletRequest request, HttpServletResponse response) 
                                                    throws Exception {
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
           
    /**
     * 
     * This method gets called upon clicking of refresh pulldown menu buttons on the screen
     * to populate the drop down menus afresh based on other parameters.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward refreshPulldownOptions(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * Upon the return from look up on <code>Rolodex</code> (Organization) field, this method gets
     * executed; We need to display Organization name on the screen;
     * 
     * For this first we are adding all the AwardReportTermRecipient objects and the string "Rolodex"
     * to two lists and passing it to PersistenceService which then refreshes the Rolodex object in 
     * all of AwardReportTermRecipient objects in 1 single transation.
     *  
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#refresh(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {        
        super.refresh(mapping, form, request, response);
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = (AwardDocument) awardForm.getAwardDocument();
        
        awardForm.getApprovedForeignTravelBean().refreshTravelers();
        
        refreshAwardReportTermRecipients(awardForm, awardDocument);
        
        String lookupResultsBOClassName = request.getParameter(KNSConstants.LOOKUP_RESULTS_BO_CLASS_NAME);
        String lookupResultsSequenceNumber = request.getParameter(KNSConstants.LOOKUP_RESULTS_SEQUENCE_NUMBER);
        awardForm.setLookupResultsBOClassName(lookupResultsBOClassName);
        awardForm.setLookupResultsSequenceNumber(lookupResultsSequenceNumber);        
        try{
            // check to see if we are coming back from a lookup
            if (Constants.MULTIPLE_VALUE.equals(awardForm.getRefreshCaller())) {
                // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
                // Since URLs have a max length of 2000 chars, field conversions can not be done.
                String thisLookupResultsSequenceNumber = awardForm.getLookupResultsSequenceNumber();//implement MultiLookupFormSupport
                if (StringUtils.isNotBlank(thisLookupResultsSequenceNumber)) {
                    Class lookupResultsBOClass = Class.forName(awardForm.getLookupResultsBOClassName());
                    Collection<PersistableBusinessObject> rawValues = KNSServiceLocator.getLookupResultsService()
                        .retrieveSelectedResultBOs(thisLookupResultsSequenceNumber, lookupResultsBOClass, GlobalVariables.getUserSession().getPrincipalId());
                    if (lookupResultsBOClass.isAssignableFrom(SponsorTerm.class)) {
                        for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                            SponsorTerm sponsorTerm = (SponsorTerm) iter.next();
                            sponsorTermActionHelper.addSponsorTermFromMutiValueLookup(((AwardForm) form).getSponsorTermFormHelper(), sponsorTerm, request);
                        }
                    }
                }
            }   
        }catch(Exception ex){
            System.out.print(ex);
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#reload(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("all")
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm) form;
        
        ActionForward actionForward = super.reload(mapping, form, request, response);
        
        setReportsAndTermsOnAwardForm(awardForm);
        
        return actionForward;        
    }
    
    /**
     * 
     * This method adds a new AwardReportTerm object to the list of AwardReportTerm objects
     * inside Award.
     * For every added AwardReportTerm object; we are adding an empty AwardReportTerm object to
     * AwardForm.newAwardReportTermRecipients list - for recipients to be added.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addAwardReportTerm(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ((AwardForm) form).getAwardReportsBean().addAwardReportTermItem(getReportClass(request), getReportClassCodeIndex(request));
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method deletes a AwardReportTerm from the list of AwardReportTerm objects.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardReportTerm(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        (((AwardForm) form).getAwardReportsBean()).deleteAwardReportTermItem(getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method adds an AwardCloseout Item.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addAwardCloseout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ((AwardForm) form).getAwardCloseoutBean().addAwardCloseoutItem();
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method deletes an AwardCloseout Item.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardCloseout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                throws Exception {
        
        (((AwardForm) form).getAwardCloseoutBean()).deleteAwardCloseoutItem(getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    
       
    public ActionForward deleteApprovedEquipmentItem(ActionMapping mapping, ActionForm form, 
                                        HttpServletRequest request, HttpServletResponse response) 
                                                                        throws Exception {
            (((AwardForm) form).getApprovedEquipmentBean()).deleteApprovedEquipmentItem(getLineToDelete(request));
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward deleteApprovedForeignTravelTrip(ActionMapping mapping, ActionForm form, 
                                                        HttpServletRequest request, HttpServletResponse response) 
                                                        throws Exception {
        (((AwardForm) form).getApprovedForeignTravelBean()).deleteApprovedForeignTravelTrip(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method gets the newAwardReportTerm object from the form, evaluates the rules and adds it
     * to the list of AwardReportTerm objects.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addRecipient(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ((AwardForm) form).getAwardReportsBean().addAwardReportTermRecipientItem(getAwardReportTermIndex(request));
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method deletes a recipient.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteRecipient(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        (((AwardForm) form).getAwardReportsBean()).deleteAwardReportTermRecipientItem(getAwardReportTermIndex(request), getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method clears the rolodex (Organization/Name) selection.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward clearRolodex(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        
        if(clearRolodexRequestIsNotForAddLine(getLineToDelete(request))){
            clearRolodexIdField(awardDocument.getAward().getAwardReportTermItems().get(getAwardReportTermIndex(
                    request)).getAwardReportTermRecipients().get(getLineToDelete(request)));
        }else{
            clearRolodexIdField(awardForm.getAwardReportsBean().getNewAwardReportTermRecipients().get(getAwardReportTermIndex(
                    request)));
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This is a helper method to determine whether the clearRolodex request is coming from a
     * add line or already added line.
     * @param lineToDelete
     * @return
     */
    protected boolean clearRolodexRequestIsNotForAddLine(int lineToDelete){
        return lineToDelete!=-1;
    }
    
    /**
     * 
     * This method sets the rolodexId field to null in AwardReportTermRecipient BO
     * 
     * @param awardReportTermRecipient
     */
    protected void clearRolodexIdField(AwardReportTermRecipient awardReportTermRecipient){
        awardReportTermRecipient.setRolodexId(null);
        awardReportTermRecipient.setRolodex(null);
    }
    /**
     * 
     * This method reads the reportClass from the request.
     * @param request
     * @return
     */
    protected String getReportClass(HttpServletRequest request) {
        int reportClass = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String reportClassString = StringUtils.substringBetween(parameterName, ".reportClass", PERIOD);
            reportClass = Integer.parseInt(reportClassString);
        }

        return new Integer(reportClass).toString();
    }
    
    /**
     * 
     * This method reads the reportClassCodeIndex from the request.
     * It is specified in the tag file and is used for showing the validation errors while adding
     * a new AwardReportTerm object.
     * @param request
     * @return
     */
    protected int getReportClassCodeIndex(HttpServletRequest request) {
        int reportClassIndex = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String reportClassIndexString = StringUtils.substringBetween(
                    parameterName, ".reportClassIndex", PERIOD);
            reportClassIndex = Integer.parseInt(reportClassIndexString);
        }

        return reportClassIndex;
    }
    
    /**
     * 
     * This method reads the recipientIndex from the request.
     * It is specified in the tag file and is used for showing the validation errors while adding
     * a new AwardReportTerm object as a recipient. 
     * @param request
     * @return
     */
    protected int getAwardReportTermIndex(HttpServletRequest request) {
        int awardReportTermIndex = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String awardReportTermIndexString = StringUtils.substringBetween(parameterName, ".awardReportTerm", PERIOD);
            awardReportTermIndex= Integer.parseInt(awardReportTermIndexString);
        }

        return awardReportTermIndex;
    }
    
    /**
     * 
     * This method reads the recipient size from the request.
     * @param request
     * @return
     */    
    protected int getRecipientSize(HttpServletRequest request) {
        int recipientSize = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String recipientSizeString = StringUtils.substringBetween(parameterName, 
                                                                        ".recipientSize", PERIOD);
            recipientSize = Integer.parseInt(recipientSizeString);
        }        
        return recipientSize;
    }
    
    /**
     * 
     * This method adds a new AwardSponsorTerm object to the list of AwardSponosorTerm objects
     * inside Award.
     * For every added AwardReportTerm object; we are adding an empty AwardReportTerm object to
     * AwardForm.newAwardReportTermRecipients list - for recipients to be added.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addAwardSponsorTerm(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        sponsorTermActionHelper.addSponsorTerm(((AwardForm) form).getSponsorTermFormHelper(), request);
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method is a convenience method for adding an <code>AwardSponsorTerm</code> to
     * <code>Award</code> business object.This way the add functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param awardSponsorTerm
     * @return
     */
    boolean addAwardSponsorTermToAward(Award award, AwardSponsorTerm awardSponsorTerm){
        return award.getAwardSponsorTerms().add(awardSponsorTerm);
    }
    
    
    /**
     * 
     * This method deletes a AwardSponsorTerms from the list of AwardSponsorTerms objects.
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardSponsorTerm(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        
        awardDocument.getAward().getAwardSponsorTerms().remove(getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * @see org.kuali.kra.award.web.struts.action.AwardAction#save(org.apache.struts.action.ActionMapping,
     *               org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        getAwardCloseoutService().updateCloseoutDueDatesBeforeSave(((AwardForm) form).getAwardDocument().getAward());
        
        return super.save(mapping, form, request, response);
    }

    
    /**
     * 
     * This method is a convenience method for deleting an <code>AwardSponsorTerm</code> from
     * <code>Award</code> business object. This way the delete functionality can be tested
     * independently using a JUnit Test.
     * @param award
     * @param lineToDelete
     * @return
     */
    boolean deleteAwardSponsorTermFromAward(Award award, int lineToDelete){
        award.getAwardSponsorTerms().remove(lineToDelete);
        return true;
    }
    
    /**
     * 
     * This is a wrapper method for the retrieval of KualiConfigurationService.
     * 
     * @return
     */
    protected KualiConfigurationService getKualiConfigurationService(){
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }
    
    protected AwardCloseoutService getAwardCloseoutService(){
        return KraServiceLocator.getService(AwardCloseoutService.class);
    }
    
    /**
     * 
     * This is a wrapper method for the retrieval of PersistenceService.
     * 
     * @return
     */
    protected PersistenceService getPersistenceService(){
        return KraServiceLocator.getService(PersistenceService.class);
    }
    
    /**
     * 
     * This method adds all the AwardReportTermRecipient objects in the Award and does a retrieves the related Rolodex object for all of them in single 
     * transaction.
     * 
     * @param awardForm
     * @param awardDocument
     */
    private void refreshAwardReportTermRecipients(AwardForm awardForm, AwardDocument awardDocument) {
        List<AwardReportTermRecipient> persistableObjects = new ArrayList<AwardReportTermRecipient>();
        List<String> referenceObjectNames = new ArrayList<String>();
        
        for(AwardReportTermRecipient awardReportTermRecipient : awardForm.getAwardReportsBean().getNewAwardReportTermRecipients()){
            persistableObjects.add(awardReportTermRecipient);
            referenceObjectNames.add(ROLODEX);            
        }
        
        for(AwardReportTerm awardReportTerm : awardDocument.getAward().getAwardReportTermItems()){
            for(AwardReportTermRecipient awardReportTermRecipient : awardReportTerm.getAwardReportTermRecipients()){
                persistableObjects.add(awardReportTermRecipient);
                referenceObjectNames.add(ROLODEX);
            }
        }
        
        if(persistableObjects.size()>0 && referenceObjectNames.size()>0 ){
            getPersistenceService().retrieveReferenceObjects(persistableObjects, referenceObjectNames);
        }
    }
    
}